package net.lemonsoft.lemonkit4android.example;

import android.app.Activity;
import android.app.backup.RestoreObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKUITableViewRowAction;
import net.lemonsoft.lemonkit.ui.view.LKUITableView;
import net.lemonsoft.lemonkit.ui.view.LKUITableViewCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemonsoft on 16-9-27.
 */

public class TableViewTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setX(0);
        relativeLayout.setY(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(relativeLayout, layoutParams);

        LKUITableView tableView = new LKUITableView(this);
        tableView.setLayoutParams(layoutParams);
        tableView.setLayoutParams(layoutParams);
        relativeLayout.addView(tableView);
        tableView.setBackgroundColor(Color.argb(255, 238, 238, 238));

        tableView.dataSource = new LKUITableView.DataSource() {
            @Override
            public Integer numberOfRowsInSection(LKUITableView tableView, Integer section) {
                return 3;
            }

            @Override
            public LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                LKUITableViewCell cell = new LKUITableViewCell(TableViewTestActivity.this);
                TextView textView = new TextView(getApplicationContext());
                textView.setText("SECTION:" + indexPath.section + " , ROW:" + indexPath.row);
                textView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setTextColor(Color.BLACK);
                cell.addView(textView);
                if (indexPath.row == 1)
                    cell.setBackgroundColor(Color.CYAN);
                return cell;

            }

            @Override
            public Integer numberOfSectionsInTableView(LKUITableView tableView) {
                return 3;
            }

            @Override
            public String titleForHeaderInSection(LKUITableView tableView, Integer section) {
                return "这是第" + section + "个sectionHeader";
            }

            @Override
            public String titleForFooterInSection(LKUITableView tableView, Integer section) {
                return "这是第" + section + "个sectionFooter";
            }
        };

        tableView.delegate = new LKUITableView.Delegate() {
            @Override
            public Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                return 300;
            }

            @Override
            public Integer heightForHeaderInSection(LKUITableView tableView, Integer section) {
                return 0;
            }

            @Override
            public Integer heightForFooterInSection(LKUITableView tableView, Integer section) {
                return 80;
            }

            @Override
            public void didSelectRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                System.out.println("卧槽，被点急了：" + indexPath.section + " r : " + indexPath.row);
            }

            @Override
            public List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                List<LKUITableViewRowAction> actions = new ArrayList<>();
                actions.add(new LKUITableViewRowAction(TableViewTestActivity.this, "删除",
                        Color.WHITE, Color.RED, new LKUITableViewRowAction.TouchAction() {
                    @Override
                    public void onTouch(LKUITableView tableView1, LKUITableViewRowAction rowAction, LKIndexPath indexPath) {
                        System.out.println("我靠删除了：" + indexPath.section + " r : " + indexPath.row);
                    }
                }));
                actions.add(new LKUITableViewRowAction(TableViewTestActivity.this, "置顶消息",
                        Color.WHITE, Color.GRAY, new LKUITableViewRowAction.TouchAction() {
                    @Override
                    public void onTouch(LKUITableView tableView1, LKUITableViewRowAction rowAction, LKIndexPath indexPath) {
                        System.out.println("我靠置顶了：" + indexPath.section + " r : " + indexPath.row);
                    }
                }));
                return actions;
            }
        };
    }
}
