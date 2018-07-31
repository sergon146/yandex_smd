package com.sergon146.mobilization18.ui.fragments.transactions;

import com.sergon146.business.model.Transaction;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

public interface TransactionsView extends BaseMvpView {
    void showTransactions(List<Transaction> transactions);
}
