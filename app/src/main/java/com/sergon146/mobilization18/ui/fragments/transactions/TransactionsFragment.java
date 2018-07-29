package com.sergon146.mobilization18.ui.fragments.transactions;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.Transaction;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.wallet.adapter.TransactionAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 10.04.2018
 */
public class TransactionsFragment extends BaseMvpFragment<TransactionsPresenter>
        implements TransactionsView {

    @Inject
    @InjectPresenter
    TransactionsPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private TransactionAdapter adapter;

    public static TransactionsFragment getInstance() {
        return new TransactionsFragment();
    }

    @Override
    @ProvidePresenter
    protected TransactionsPresenter providePresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transactions, container, false);
        ButterKnife.bind(this, root);
        setActionBarTitle(R.string.tab_feed);
        prepareView();
        return root;
    }

    private void prepareView() {
        adapter = new TransactionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showTransactions(List<Transaction> transactions) {
        adapter.setTransactions(transactions);
    }

    @Override
    public String getLogName() {
        return "TransactionsFragment";
    }
}
