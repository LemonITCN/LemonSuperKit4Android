package net.lemonsoft.lemonkit.adapter.LKUITableView;

import net.lemonsoft.lemonkit.delegate.LKUITableView.LKUITableViewDelegate;
import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKUITableViewRowAction;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableView;

import java.util.ArrayList;
import java.util.List;

/**
 * LKUITableView的代理适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKUITableViewDelegateAdapter implements LKUITableViewDelegate {

    @Override
    public Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
        return 140;
    }

    @Override
    public Integer heightForHeaderInSection(LKUITableView tableView, Integer section) {
        return 80;
    }

    @Override
    public Integer heightForFooterInSection(LKUITableView tableView, Integer section) {
        return 80;
    }

    @Override
    public void didSelectRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
    }

    @Override
    public List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
        return new ArrayList<>();
    }
}
