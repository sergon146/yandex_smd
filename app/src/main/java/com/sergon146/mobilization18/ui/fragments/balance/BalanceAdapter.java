package com.sergon146.mobilization18.ui.fragments.balance;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergon146.business.model.Balance;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.util.CurrencyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> {

    private List<Balance> balanceList = new ArrayList<>();

    @NonNull
    @Override
    public BalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_item, parent, false);
        return new BalanceViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHolder holder, int position) {
        holder.bind(balanceList.get(position));
    }

    @Override
    public int getItemCount() {
        return balanceList.size();
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList.clear();
        this.balanceList.addAll(balanceList);
        notifyDataSetChanged();
    }

    class BalanceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.value)
        TextView value;
        @BindView(R.id.currency)
        TextView currency;

        public BalanceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Balance balance) {
            value.setText(itemView.getResources().getString(R.string.currency_value,
                    CurrencyUtils.round(balance.getValue(), 2) + "",
                    balance.getCurrency().getSymbol()));

            currency.setText(balance.getCurrency().getShortName());
        }
    }
}
