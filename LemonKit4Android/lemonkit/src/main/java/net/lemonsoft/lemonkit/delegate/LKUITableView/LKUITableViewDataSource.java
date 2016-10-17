package net.lemonsoft.lemonkit.delegate.LKUITableView;

import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableView;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableViewCell;


/**
 * 数据源接口
 */
public interface LKUITableViewDataSource {
    // required
    Integer numberOfRowsInSection(LKUITableView tableView, Integer section);

    LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

    // optional
    Integer numberOfSectionsInTableView(LKUITableView tableView);

    String titleForHeaderInSection(LKUITableView tableView, Integer section);

    String titleForFooterInSection(LKUITableView tableView, Integer section);

}