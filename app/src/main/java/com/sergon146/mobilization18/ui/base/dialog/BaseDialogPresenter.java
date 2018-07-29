package com.sergon146.mobilization18.ui.base.dialog;

import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BaseSchedulablePresenter;

import io.reactivex.disposables.Disposable;

public abstract class BaseDialogPresenter<View extends BaseDialogMvpView>
        extends BaseSchedulablePresenter<View> {

    protected final String screenTag;
    protected final MainRouter router;

    public BaseDialogPresenter(MainRouter router) {
        super();
        screenTag = getScreenTag();
        this.router = router;
    }

    protected void bind(Disposable disposable) {
        bind(disposable, LifeLevel.PER_VIEW);
    }

    protected abstract String getScreenTag();
}
