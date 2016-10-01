package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKUITableViewRowAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LKUITableView,把iOS的UITableView带到安卓开发中！
 * Created by lemonsoft on 16-9-27.
 */
public class LKUITableView extends ScrollView {

    private Context context;

    public DataSource dataSource;// 数据源
    public Delegate delegate;// 代理

    private LKIndexPath slidedCell;// 已经侧滑的cell的路径，如果没有，则为null

    /**
     * 类型记录池，记录池value格式：type_section_row，header:h,footer:f,cell:c,header和footer的row永远为0
     */
    private HashMap<Integer, String> typeRecorder;
    private List<Integer> startLocationRecorder;// 起始位置记录表

    private HashMap<LKIndexPath, HorizontalScrollView> cellScrollContainerPool;

    private RelativeLayout contentView;

    private boolean autoRun = true;

    public LKUITableView(Context context) {
        super(context);
        this.context = context;
        initTableView();// 初始化tableView
    }

    public void initTableView() {
        this.contentView = new RelativeLayout(this.context);
        typeRecorder = new HashMap<>();// 创建一个y坐标记录池
        startLocationRecorder = new ArrayList<>();// 创建一个y底部坐标记录池
        cellScrollContainerPool = new HashMap<>();// 创建一个根据LKIndexPath索引cellScrollView的池
        this.addView(this.contentView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (autoRun)
            reloadData();
        autoRun = false;
    }

    /**
     * 向记录池中添加一条记录，记录池是为了复用机制动态计算游标而用的
     *
     * @param type    类型，h/f/c
     * @param section 分区号
     * @param row     行号
     * @param y       y坐标
     * @param height  行高
     */
    private void recorderAdd(String type, Integer section, Integer row, Integer y, Integer height) {
        if (height > 0) {
            // 生成行标识字符串，然后放到类型记录池中
            typeRecorder.put(startLocationRecorder.size(), String.format("%s_%d_%d", type, section, row));
            startLocationRecorder.add(y);
        }
    }

    /**
     * 刷新数据，该函数会清空所有的控件并完全重新加载，包括重新调用数据源和代理函数进行高度等数值计算
     */
    public void reloadData() {
        contentView.removeAllViews();
        typeRecorder.clear();// 移除所有类型记录
        startLocationRecorder.clear();// 移除所有开始游标记录
        if (dataSource != null) {
            // 指定了dataSource
            Integer sectionCount = dataSource.numberOfSectionsInTableView(this);// 调用数据源的对应函数，获取section的数量
            Integer contentHeight = 0;// 初始化高度统计变量
            for (int si = 0; si < sectionCount; si++) {// 根据section的数量进行依次调用获取row的数量
                Integer rowCount = dataSource.numberOfRowsInSection(this, si);// 加上section的header的高度
                Integer headerHeight = delegate.heightForHeaderInSection(this, si);
                recorderAdd("h", si, 0, contentHeight, headerHeight);// 把header加入游标记录中，准备复用机制的使用
                contentHeight += headerHeight;
                initHeader(si);
                for (int ri = 0; ri < rowCount; ri++) {// 根据row的数量依次调用代理函数获取每行的高度
                    LKIndexPath indexPath = LKIndexPath.make(si, ri);
                    Integer cellHeight = delegate.heightForRowAtIndexPath(this, indexPath);
                    recorderAdd("c", si, ri, contentHeight, cellHeight);// 把cell加入游标记录中，准备复用机制的使用
                    initCell(indexPath, cellHeight, contentHeight);// 初始化cell行控件
                    contentHeight += cellHeight;
                }
                Integer footerHeight = delegate.heightForFooterInSection(this, si);
                recorderAdd("f", si, 0, contentHeight, footerHeight);// 把footer加入游标记录中，准备复用机制的使用
                contentHeight += footerHeight;// 加上section的footer的高度
                initFooter(si);
            }
            initContentView(contentHeight);// 初始化容器控件
            lastBottomIndex = spaceOfLocation(getHeight());// 初始化计算底部所处索引
        }
    }

    /**
     * 初始化容器控件
     *
     * @param contentHeight 容器控件的应有高度
     */
    private void initContentView(Integer contentHeight) {
        this.contentView.setX(0);
        this.contentView.setY(0);
        FrameLayout.LayoutParams contentViewLayoutParams =
                new FrameLayout.LayoutParams(getWidth(), contentHeight);// 整个tableView控件大小的params
        this.contentView.setLayoutParams(contentViewLayoutParams);
    }

    /**
     * 初始化cell
     *
     * @param indexPath 当前初始化行的位置信息
     */
    private void initCell(final LKIndexPath indexPath, Integer cellHeight, Integer y) {
        final RelativeLayout cellContainerView = new RelativeLayout(context);
        cellContainerView.setX(0);
        cellContainerView.setY(0);
        Integer actionWidth = 0;
        List<LKUITableViewRowAction> actions = delegate.editActionsForRowAtIndexPath(this, indexPath);
        if (actions != null) {
            for (final LKUITableViewRowAction action : actions) {
                RelativeLayout actionItemLayout = new RelativeLayout(context);
                actionItemLayout.setLayoutParams(new RelativeLayout.LayoutParams(action.getWidth(), cellHeight));// 初始化action控件大小
                action.getContainerView().setLayoutParams(new RelativeLayout.LayoutParams(action.getWidth(), cellHeight));// 设置action控件高度为cell高度
                action.getContainerView().setX(0);
                action.getContainerView().setY(0);
                actionItemLayout.addView(action.getContainerView());
                actionItemLayout.setX(actionWidth + getWidth());
                actionItemLayout.setBackgroundColor(action.getBackgroundColor());
                actionItemLayout.setY(0);
                actionItemLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 调用action点击事件
                        action.getTouchAction().onTouch(LKUITableView.this, action, indexPath);
                        closeCurrentSlide();
                    }
                });
                cellContainerView.addView(actionItemLayout);
                actionWidth += action.getWidth();
            }
        }
        RelativeLayout.LayoutParams cellContainerParams
                = new RelativeLayout.LayoutParams(getWidth() + actionWidth, cellHeight);// 使用等同于控件的宽度，用户设置的高度
        cellContainerView.setLayoutParams(cellContainerParams);

        int[] colors = {Color.BLUE, Color.YELLOW, Color.RED};
        cellContainerView.setBackgroundColor(colors[indexPath.row]);

        RelativeLayout.LayoutParams cellSizeParams
                = new RelativeLayout.LayoutParams(getWidth(), cellHeight);// 使用等同于控件的宽度，用户设置的高度
        final LKHorizontalScrollView scrollView = new LKHorizontalScrollView(context);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setLayoutParams(cellSizeParams);
        scrollView.setX(0);// 最左边开始
        scrollView.setY(y);// 顶部开始

        final Integer finalActionWidth = actionWidth;
        scrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == event.ACTION_MOVE && !indexPath.equals(slidedCell)) {
                    closeCurrentSlide();// 关闭当前侧滑
                }
                if (event.getAction() == event.ACTION_UP) {// 判断是触摸抬起的时候
                    if (scrollView.getScrollX() < finalActionWidth / 2)
                        scrollView.smoothScrollTo(0, 0);// 平滑回到0
                    else {
                        scrollView.smoothScrollTo(finalActionWidth, 0);// 平滑移动到完全打开
                    }
                    return true;// 这里返回true，否则smoothScrollTo不可用
                }
                return false;
            }
        });
        scrollView.setOnScrollListener(new LKHorizontalScrollView.ScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (l == finalActionWidth)
                    slidedCell = indexPath;
                else if (l == 0 && slidedCell != null && slidedCell.equals(indexPath))
                    slidedCell = null;
            }
        });
        scrollView.addView(cellContainerView);// 把整个容器控件放入到横向scrollView中

        // 初始化cellContentView，即控件主显示内容，不包括rowAction相关的内容
        RelativeLayout cellContentView = new RelativeLayout(context);
        cellContentView.setLayoutParams(cellSizeParams);
        cellContentView.setX(0);
        cellContentView.setY(0);
        cellContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCurrentSlide();// 点击内容时候让其自动关闭已经侧滑的cell
                delegate.didSelectRowAtIndexPath(LKUITableView.this, indexPath);// 调用代理中的行点击事件
            }
        });
        LKUITableViewCell cell = dataSource.cellForRowAtIndexPath(this, indexPath);
        cell.setLayoutParams(cellSizeParams);
        cellContentView.addView(cell);// 把LKTableViewCell控件实际放入到contentView中显示

        cellContainerView.addView(cellContentView);
        contentView.addView(scrollView);
        cellScrollContainerPool.put(indexPath, scrollView);
    }

    /**
     * 初始化section的header控件
     *
     * @param section 当前要初始化的控件的section索引
     */
    private void initHeader(Integer section) {

    }

    /**
     * 初始化section的footer控件
     *
     * @param section 当前要初始化的控件的section索引
     */
    private void initFooter(Integer section) {

    }

    /**
     * 当前所在的位置处于哪段索引控件位置之上
     *
     * @param current 当前的位置
     * @return 所在的控件的索引
     */
    public Integer spaceOfLocation(Integer current) {
        if (startLocationRecorder.size() > 0) {
            for (int i = 0; i < startLocationRecorder.size() - 1; i++) {
                if (current >= startLocationRecorder.get(i) && current < startLocationRecorder.get(i + 1))// 在此区段之间
                    return i;
            }
            return startLocationRecorder.size() - 1;
        }
        return 0;
    }

    private int lastTopIndex = 0;
    private int lastBottomIndex;
    private int lastScrollY = 0;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int currentTopIndex = spaceOfLocation(t);
        int currentBottomIndex = spaceOfLocation(t + getHeight());
        if (currentTopIndex == lastTopIndex && currentBottomIndex == lastBottomIndex)
            return;
        if (t > lastScrollY) {
            // tableView向上滚动
            if (currentTopIndex > lastTopIndex)// 元素被从屏幕上方滚动出屏幕
                scrollOutScreen(typeRecorder.get(lastTopIndex));
            if (currentBottomIndex > lastBottomIndex)// 元素从屏幕底部滚动进入屏幕
                scrollIntoScreen(typeRecorder.get(currentBottomIndex));
        } else if (t < lastScrollY) {
            // tableView向下滚动
            if (currentTopIndex < lastTopIndex)// 元素被从屏幕上方滚动进入屏幕
                scrollIntoScreen(typeRecorder.get(currentTopIndex));
            if (currentBottomIndex < lastBottomIndex)// 元素从屏幕底部滚动移出去了
                scrollOutScreen(typeRecorder.get(lastBottomIndex));
        }
        lastTopIndex = currentTopIndex;
        lastBottomIndex = currentBottomIndex;
        lastScrollY = t;// 刷新lastScrollY
    }

    /**
     * 滚动进入屏幕
     */
    private void scrollIntoScreen(String pathInfo) {
        System.out.println("----> IN" + pathInfo);
    }

    /**
     * 滚出屏幕
     */
    private void scrollOutScreen(String pathInfo) {
        System.out.println("----> OUT" + pathInfo);
        LKIndexPath indexPath = getIndexPathWithPathInfoString(pathInfo);
        if (indexPath != null && indexPath.equals(slidedCell))// 移除屏幕的cell要关闭侧滑
            closeCurrentSlide();
    }

    /**
     * 关闭当前已经侧滑的cell侧滑
     */
    public void closeCurrentSlide() {
        if (slidedCell != null)
            cellScrollContainerPool.get(slidedCell).smoothScrollTo(0, 0);
    }

    /**
     * 通过路径信息字符串得到LKIndexPath
     *
     * @param pathInfo 路径信息字符串
     * @return 路径信息字符串对应的IndexPath
     */
    public LKIndexPath getIndexPathWithPathInfoString(String pathInfo) {
        String[] pathItems = pathInfo.split("_");
        if (pathItems[0].equals("c")) {// 是cell
            return LKIndexPath.make(Integer.parseInt(pathItems[1]), Integer.parseInt(pathItems[2]));
        }
        return null;// 不是cell
    }

    /**
     * 数据源接口
     */
    public interface DataSource {
        // required
        Integer numberOfRowsInSection(LKUITableView tableView, Integer section);

        LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

        // optional
        Integer numberOfSectionsInTableView(LKUITableView tableView);

        String titleForHeaderInSection(LKUITableView tableView, Integer section);

        String titleForFooterInSection(LKUITableView tableView, Integer section);

    }

    /**
     * 代理接口
     */
    public interface Delegate {
        // Variable height support
        Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

        Integer heightForHeaderInSection(LKUITableView tableView, Integer section);

        Integer heightForFooterInSection(LKUITableView tableView, Integer section);

        void didSelectRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

        List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);
    }

}
