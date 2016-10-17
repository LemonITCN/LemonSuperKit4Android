package net.lemonsoft.lemonkit.delegate.LKUITableView;

import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKUITableViewRowAction;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableView;

import java.util.List;

/**
 * 代理接口
 */
public interface LKUITableViewDelegate {
    // Variable height support
    Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

    Integer heightForHeaderInSection(LKUITableView tableView, Integer section);

    Integer heightForFooterInSection(LKUITableView tableView, Integer section);

    void didSelectRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

    List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);
}