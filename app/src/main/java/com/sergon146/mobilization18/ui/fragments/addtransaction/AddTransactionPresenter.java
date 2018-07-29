package com.sergon146.mobilization18.ui.fragments.addtransaction;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.AddTransactionUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.dialog.BaseDialogPresenter;

@InjectViewState
public class AddTransactionPresenter extends BaseDialogPresenter<AddTransactionView> {

    private final AddTransactionUseCase useCase;

    public AddTransactionPresenter(MainRouter router,
                                   AddTransactionUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        bind(onUi(useCase.getWallets())
                .subscribe(wallets -> getViewState().showWallets(wallets)));
    }


    @Override
    protected String getScreenTag() {
        return "AddTransactionPresenter";
    }

    public void addTransaction(Transaction transaction) {
        useCase.addTransaction(transaction);
    }
}
