package com.sergon146.mobilization18.ui.fragments.balance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.Balance;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceFragment extends BaseMvpFragment<BalancePresenter>
        implements BalanceView, Injectable {

    @Inject
    @InjectPresenter
    BalancePresenter presenter;

    @BindView(R.id.wallet_recycler)
    RecyclerView walletRecycler;


    public static BalanceFragment getInstance() {
        return new BalanceFragment();
    }

    @Override
    @ProvidePresenter
    protected BalancePresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.balance_fragment, container, false);
        ButterKnife.bind(this, root);
        prepareViews();
        return root;
    }

    private void prepareViews() {
        BalanceAdapter adapter = new BalanceAdapter();
        walletRecycler.setHasFixedSize(true);
        walletRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        walletRecycler.setAdapter(adapter);
    }

    @Override
    public void showBalance(List<Balance> balances) {
        if (walletRecycler.getAdapter() instanceof BalanceAdapter) {
            ((BalanceAdapter) walletRecycler.getAdapter()).setBalanceList(balances);
        } else {
            throw new RuntimeException("Wrong adapter");
        }
    }

    @Override
    public String getLogName() {
        return "BalanceFragment";
    }
}
