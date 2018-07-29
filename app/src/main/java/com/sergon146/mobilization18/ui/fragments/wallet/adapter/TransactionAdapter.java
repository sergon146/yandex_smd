package com.sergon146.mobilization18.ui.fragments.wallet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.util.ItemUtils;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.BalanceViewHolder> {

    private List<Transaction> transactionList = new ArrayList<>();
    private List<Wallet> walletList = new ArrayList<>();

    @NonNull
    @Override
    public BalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new BalanceViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHolder holder, int position) {
        holder.bind(transactionList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactionList.clear();
        this.transactionList.addAll(transactions);
        notifyDataSetChanged();
    }

    class BalanceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.amount)
        MoneyTextView amount;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.card)
        TextView card;

        BalanceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Transaction item) {
            title.setText(itemView.getContext().getString(
                    item.getType() == OperationType.INCOME
                            ? R.string.income
                            : R.string.expense));

            int coef;
            int color;
            if (item.getType() == OperationType.INCOME) {
                coef = 1;
                color = itemView.getResources().getColor(R.color.income);
            } else {
                coef = -1;
                color = itemView.getResources().getColor(R.color.expense);
            }

            amount.setBaseColor(color);
            amount.setDecimalsColor(color);
            amount.setSymbolColor(color);

            amount.setAmount(item.getAmount().floatValue() * coef);
            amount.setSymbol(item.getCurrency().getSymbol()
            );
            date.setText(SimpleDateFormat.getDateTimeInstance().format(item.getDate()));
            category.setText(ItemUtils.getTransactionCategoryTitle(itemView.getContext(), item));

            card.setVisibility(View.GONE);

            if (item.getWallet() != null) {
                card.setText(item.getWallet().getName());
                card.setVisibility(View.VISIBLE);
            }
        }
    }
}
