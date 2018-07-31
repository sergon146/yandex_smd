package com.sergon146.mobilization18.ui.fragments.wallet;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.WalletUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

import java.util.UUID;

@InjectViewState
public class WalletPresenter extends BasePresenter<WalletView> {
    private final WalletUseCase useCase;
    private UUID uuid;

    public WalletPresenter(MainRouter router, WalletUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "WalletPresenter";
    }

    public void setUuid(UUID uuid) {
        if (uuid.equals(this.uuid)) {
            return;
        }

        this.uuid = uuid;
        bind(onUi(useCase.getWallet(uuid))
                .subscribe(wallet -> getViewState().showWallet(wallet)));

        bind(onUi(useCase.getWalletTransactions(uuid)).subscribe(transactions -> {
            getViewState().showTransactions(transactions);
        }));
    }
}
