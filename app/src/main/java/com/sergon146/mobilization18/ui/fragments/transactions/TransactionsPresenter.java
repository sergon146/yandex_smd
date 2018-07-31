package com.sergon146.mobilization18.ui.fragments.transactions;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.TransactionsUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class TransactionsPresenter extends BasePresenter<TransactionsView> {

    private final TransactionsUseCase useCase;

    public TransactionsPresenter(MainRouter router, TransactionsUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        bind(onUi(useCase.getTransactions()).subscribe(transactions ->
                getViewState().showTransactions(transactions)));
    }

    @Override
    protected String getScreenTag() {
        return "TransactionsPresenter";
    }
}
