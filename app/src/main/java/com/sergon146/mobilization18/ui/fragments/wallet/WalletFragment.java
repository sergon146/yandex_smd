package com.sergon146.mobilization18.ui.fragments.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.wallet.adapter.TransactionAdapter;
import com.sergon146.mobilization18.util.CurrencyUtils;
import com.sergon146.mobilization18.util.ItemUtils;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletFragment extends BaseMvpFragment<WalletPresenter>
        implements WalletView {
    private static final String UUID_KEY = "UUID_KEY";
    @Inject
    @InjectPresenter
    WalletPresenter presenter;

    @BindView(R.id.transactions_recycler)
    RecyclerView transactionsRecycler;
    @BindView(R.id.wallet_title)
    TextView title;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.type)
    TextView type;

    private TransactionAdapter transactionAdapter;

    public static WalletFragment newInstance(UUID walletUuid) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(UUID_KEY, walletUuid);
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    @ProvidePresenter
    protected WalletPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            UUID uuid = (UUID) getArguments().getSerializable(UUID_KEY);
            presenter.setUuid(uuid);
        }

        View root = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, root);
        prepareView();
        return root;
    }

    private void prepareView() {
        transactionsRecycler.setHasFixedSize(true);
        transactionsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionAdapter = new TransactionAdapter();
        transactionsRecycler.setAdapter(transactionAdapter);
    }

    @Override
    public void showWallet(Wallet wallet) {
        if (wallet == null) {
            return;
        }

        title.setText(wallet.getName());
        amount.setText(getResources().getString(R.string.amount,
                CurrencyUtils.getAmoutText(wallet.getAmount()),
                wallet.getCurrency().getSymbol()));
        type.setText(ItemUtils.getWalletTypeTitle(getContext(), wallet));
    }

    @Override
    public void showTransactions(List<Transaction> transactions) {
        transactionAdapter.setTransactions(transactions);
    }

    @Override
    public String getLogName() {
        return "WalletFragment";
    }
}
