package com.sergon146.mobilization18.ui.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Базовый класс для любого презентера. Реализует:
 * - процесс наблюдения и отписки от потока {@link #bind}
 * - управление потоком вывода результата {@link #onUi}
 * Created by Sergon146 on 08.04.2018.
 * <sergon146@gmail.com>
 */
public abstract class BaseSchedulablePresenter<View extends MvpView> extends MvpPresenter<View> {
    private final CompositeDisposable viewDisposable = new CompositeDisposable();
    private final CompositeDisposable uiDisposable = new CompositeDisposable();
    private final CompositeDisposable presenterDisposable = new CompositeDisposable();

    protected void bind(Disposable disposable, LifeLevel level) {
        switch (level) {
            case PER_VIEW:
                viewDisposable.add(disposable);
                break;
            case PER_PRESENTER:
                presenterDisposable.add(disposable);
                break;
            case PER_UI:
                uiDisposable.add(disposable);
            default:
        }
    }

    @Override
    public void detachView(final View view) {
        super.detachView(view);
        if (getAttachedViews().size() == 0) {
            viewDisposable.clear();
        }
    }

    @Override
    public void destroyView(View view) {
        super.destroyView(view);
        uiDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getAttachedViews().size() == 0) {
            presenterDisposable.clear();
        }
    }

    protected <T> Single<T> onUi(final Single<T> single) {
        return single.observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> Observable<T> onUi(final Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    protected Completable onUi(final Completable completable) {
        return completable.observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> Flowable<T> onUi(final Flowable<T> flowable) {
        return flowable.observeOn(AndroidSchedulers.mainThread());
    }

    public enum LifeLevel {
        PER_VIEW,
        PER_PRESENTER,
        PER_UI
    }
}
