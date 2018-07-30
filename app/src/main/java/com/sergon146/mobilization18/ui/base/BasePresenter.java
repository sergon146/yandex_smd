package com.sergon146.mobilization18.ui.base;import android.content.Context;import android.content.Intent;import com.sergon146.mobilization18.App;import com.sergon146.mobilization18.navigation.MainRouter;import com.sergon146.mobilization18.util.NetworkUtil;import io.reactivex.disposables.Disposable;/** * @author Sergon146 (sergon146@gmail.com) * @since 08.04.2018. */public abstract class BasePresenter<View extends BaseMvpView>        extends BaseSchedulablePresenter<View> {    protected final String screenTag;    private final MainRouter router;    public BasePresenter(MainRouter router) {        this.router = router;        screenTag = getScreenTag();    }    protected void bind(Disposable disposable) {        bind(disposable, LifeLevel.PER_VIEW);    }    @Override    protected void bind(Disposable disposable, LifeLevel level) {        if (NetworkUtil.isLostConnection(App.newInstance().getApplicationContext())) {            getViewState().showConnectionError();            return;        }        super.bind(disposable, level);    }    protected MainRouter getRouter() {        return router;    }    public void goBack() {        getRouter().back();    }    protected abstract String getScreenTag();    protected final void onReceive(Context context, Intent intent) {        if (NetworkUtil.isLostConnection(context)) {            getViewState().connectionLost();        } else {            getViewState().connectionRestore();        }    }}