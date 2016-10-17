package net.lemonsoft.lemonkit.adapter.LKUITableView;

import net.lemonsoft.lemonkit.delegate.LKUITableView.LKUITableViewDataSource;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableView;

/**
 * LKUITableView的数据源适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKUITableViewDataSourceAdapter implements LKUITableViewDataSource {

    @Override
    public Integer numberOfSectionsInTableView(LKUITableView tableView) {
        return 1;
    }

    @Override
    public String titleForHeaderInSection(LKUITableView tableView, Integer section) {
        return null;// 返回null表示不显示header
    }

    @Override
    public String titleForFooterInSection(LKUITableView tableView, Integer section) {
        return null;// 返回null表示不显示footer
    }
}
