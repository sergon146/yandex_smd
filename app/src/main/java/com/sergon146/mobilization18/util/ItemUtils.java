package com.sergon146.mobilization18.util;

import android.content.Context;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.R;

public class ItemUtils {

    public static String getWalletTypeTitle(Context context, Wallet wallet) {
        String type;
        switch (wallet.getType()) {
            case CASH:
                type = context.getString(R.string.wallet_cash);
                break;
            case DEBIT_CARD:
                type = context.getString(R.string.wallet_debit_card);
                break;
            case CREDIT_CARD:
                type = context.getString(R.string.wallet_credit_card);
                break;
            default:
                type = "";
        }
        return type;
    }

    public static String getTrasactionCategoryTitle(Context context, Transaction transaction) {
        String category;
        switch (transaction.getCategory()) {
            case CLOTHING:
                category = context.getResources().getString(R.string.clothing);
                break;
            case COMMUNAL:
                category = context.getResources().getString(R.string.communal);
                break;
            case EATING:
                category = context.getResources().getString(R.string.eating);
                break;
            case PRODUCTS:
                category = context.getResources().getString(R.string.products);
                break;
            case TRANSPORT:
                category = context.getResources().getString(R.string.transport);
                break;
            case OTHER:
                category = context.getResources().getString(R.string.other);
                break;
            default:
                category = "";
        }
        return category;
    }
}
