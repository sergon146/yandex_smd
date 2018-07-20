/**
 * Copyright 2015 Eugene Matsyuk (matzuk2@mail.ru)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in
 * compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.sergon146.mobilization18.util.pagination;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sergon146.mobilization18.util.BackgroundExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
public class PaginationTool<T> {
    // for first start of items loading then on RecyclerView there are not items and no scrolling
    private static final int EMPTY_LIST_ITEMS_COUNT = 0;
    // default limit for requests
    private static final int UPDATE_LIMIT = 4;
    // default max attempts to retry loading request
    private static final int MAX_ATTEMPTS_TO_RETRY_LOADING = 3;

    private RecyclerView recyclerView;
    private PagingListener<T> pagingListener;
    private LoadedItemsCounter itemsCounter;
    private long total;
    private int emptyListCount;
    private int retryCount;
    private boolean emptyListCountPlusToOffset;

    private PaginationTool() {
    }

    public static <T> Builder<T> buildPagingObservable(RecyclerView recyclerView,
                                                       PagingListener<T> pagingListener) {
        return new Builder<>(recyclerView, pagingListener);
    }

    public static <T> Builder<T> buildPagingObservable(RecyclerView recyclerView,
                                                       PagingListener<T> pagingListener,
                                                       LoadedItemsCounter itemsCounter) {
        return new Builder<>(recyclerView, pagingListener, itemsCounter);
    }

    public Observable<T> getPagingObservable() {
        int startNumberOfRetryAttempt = 0;
        return getScrollObservable(recyclerView, emptyListCount)
            .subscribeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(s -> pagingListener.showThrobber())
            .doOnTerminate(() -> pagingListener.hideThrobber())
            .observeOn(Schedulers.from(BackgroundExecutor.getSafeBackgroundExecutor()))
            .switchMap(offset ->
                getPagingObservable(pagingListener, pagingListener.onNextPage(offset),
                    startNumberOfRetryAttempt, offset, retryCount));
    }

    private Observable<T> getPagingObservable(PagingListener<T> listener,
                                              Observable<T> observable,
                                              int numberOfAttemptToRetry,
                                              int offset,
                                              int retryCount) {
        return observable.onErrorResumeNext(throwable -> {
            // retry to load new data portion if error occurred
            if (numberOfAttemptToRetry < retryCount) {
                int attemptToRetryInc = numberOfAttemptToRetry + 1;
                return getPagingObservable(listener, listener.onNextPage(offset),
                    attemptToRetryInc, offset, retryCount);
            } else {
                return Observable.error(throwable);
            }
        });
    }

    private Observable<Integer> getScrollObservable(RecyclerView recyclerView,
                                                    int emptyListCount) {
        return Observable.create(emitter -> {
            RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    onScrolledAction(emitter, emptyListCount);
                }
            };

            onScrolledAction(emitter, emptyListCount);
            recyclerView.addOnScrollListener(sl);
            emitter.setCancellable(() -> recyclerView.removeOnScrollListener(sl));

            if ((itemsCounter != null && itemsCounter.getItems() == emptyListCount) ||
                recyclerView.getAdapter().getItemCount() == emptyListCount) {
                int offset;
                if (itemsCounter != null) {
                    offset = emptyListCountPlusToOffset
                        ? itemsCounter.getItems()
                        : itemsCounter.getItems() - emptyListCount;
                } else {
                    offset = emptyListCountPlusToOffset
                        ? recyclerView.getAdapter().getItemCount()
                        : recyclerView.getAdapter().getItemCount() - emptyListCount;
                }
                emitter.onNext(offset);
            }
        });
    }

    private void onScrolledAction(ObservableEmitter<Integer> emitter, int emptyListCount) {
        if (emitter.isDisposed()) {
            return;
        }

        int position = getLastVisibleItemPosition(recyclerView);
        int updatePosition = recyclerView.getAdapter().getItemCount() - 4 - (UPDATE_LIMIT / 2);
        if (position >= updatePosition
            && (total == 0 || position < total) && total != itemsCounter.getItems()) {
            int offset;
            if (itemsCounter != null) {
                offset = emptyListCountPlusToOffset
                    ? itemsCounter.getItems()
                    : itemsCounter.getItems() - emptyListCount;
            } else {
                offset = emptyListCountPlusToOffset
                    ? recyclerView.getAdapter().getItemCount()
                    : recyclerView.getAdapter().getItemCount() - emptyListCount;
            }
            emitter.onNext(offset);
        }
    }

    private int getLastVisibleItemPosition(RecyclerView recyclerView) {
        Class recyclerViewLMClass = recyclerView.getLayoutManager().getClass();
        if (recyclerViewLMClass == LinearLayoutManager.class
            || LinearLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            LinearLayoutManager linearLayoutManager
                = (LinearLayoutManager) recyclerView.getLayoutManager();
            return linearLayoutManager.findLastVisibleItemPosition();
        } else if (recyclerViewLMClass == StaggeredGridLayoutManager.class
            || StaggeredGridLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            StaggeredGridLayoutManager staggeredGridLayoutManager
                = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            int[] into = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            List<Integer> intoList = new ArrayList<>();
            for (int i : into) {
                intoList.add(i);
            }
            return Collections.max(intoList);
        }
        throw new PagingException("Unknown LayoutManager class: "
            + recyclerViewLMClass.toString());
    }

    public interface LoadedItemsCounter {
        int getItems();
    }

    public static class Builder<T> {

        private RecyclerView recyclerView;
        private PagingListener<T> pagingListener;
        private LoadedItemsCounter itemsCounter;
        private long total;
        private int emptyListCount = EMPTY_LIST_ITEMS_COUNT;
        private int retryCount = MAX_ATTEMPTS_TO_RETRY_LOADING;
        private boolean emptyListCountPlusToOffset = false;

        private Builder(RecyclerView recyclerView,
                        PagingListener<T> pagingListener) {
            if (recyclerView == null) {
                throw new PagingException("null recyclerView");
            }
            if (recyclerView.getAdapter() == null) {
                throw new PagingException("null recyclerView adapter");
            }
            if (pagingListener == null) {
                throw new PagingException("null pagingListener");
            }
            this.recyclerView = recyclerView;
            this.pagingListener = pagingListener;
            this.itemsCounter = null;
        }

        private Builder(RecyclerView recyclerView,
                        PagingListener<T> pagingListener,
                        LoadedItemsCounter itemsCounter) {
            if (recyclerView == null) {
                throw new PagingException("null recyclerView");
            }
            if (recyclerView.getAdapter() == null) {
                throw new PagingException("null recyclerView adapter");
            }
            if (pagingListener == null) {
                throw new PagingException("null pagingListener");
            }
            if (itemsCounter == null) {
                throw new PagingException("null itemsCounter");
            }
            this.recyclerView = recyclerView;
            this.pagingListener = pagingListener;
            this.itemsCounter = itemsCounter;
        }

        public Builder<T> setTotal(long total) {
            if (total <= 0) {
                throw new PagingException("total must be greater then 0");
            }
            this.total = total;
            return this;
        }

        public Builder<T> setEmptyListCount(int emptyListCount) {
            if (emptyListCount < 0) {
                throw new PagingException("emptyListCount must be not less then 0");
            }
            this.emptyListCount = emptyListCount;
            return this;
        }

        public Builder<T> setRetryCount(int retryCount) {
            if (retryCount < 0) {
                throw new PagingException("retryCount must be not less then 0");
            }
            this.retryCount = retryCount;
            return this;
        }

        public Builder<T> setEmptyListCountPlusToOffset(boolean emptyListCountPlusToOffset) {
            this.emptyListCountPlusToOffset = emptyListCountPlusToOffset;
            return this;
        }

        public PaginationTool<T> build() {
            PaginationTool<T> paginationTool = new PaginationTool<>();
            paginationTool.recyclerView = this.recyclerView;
            paginationTool.pagingListener = pagingListener;
            paginationTool.itemsCounter = itemsCounter;
            paginationTool.emptyListCount = emptyListCount;
            paginationTool.retryCount = retryCount;
            paginationTool.emptyListCountPlusToOffset = emptyListCountPlusToOffset;
            paginationTool.total = total;
            return paginationTool;
        }

    }

}
