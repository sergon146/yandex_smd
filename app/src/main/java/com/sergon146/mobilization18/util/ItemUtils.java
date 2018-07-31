package com.sergon146.mobilization18.util;

import android.content.Context;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.TransactionCategory;
import com.sergon146.mobilization18.R;

public class ItemUtils {

    public static String getWalletTypeTitle(Context context, Wallet wallet) {
        int titleId;
        switch (wallet.getType()) {
            case CASH:
                titleId = R.string.wallet_cash;
                break;
            case DEBIT_CARD:
                titleId = R.string.wallet_debit_card;
                break;
            case CREDIT_CARD:
                titleId = R.string.wallet_credit_card;
                break;
            default:
                return "";
        }
        return context.getString(titleId);
    }

    public static String getTransactionCategoryTitle(Context context, Transaction transaction) {
        return getCategoryTitle(context, transaction.getCategory());
    }

    public static String getCategoryTitle(Context context,
                                          TransactionCategory transactionCategory) {
        int titleId;
        switch (transactionCategory) {
            case CLOTHING:
                titleId = R.string.clothing;
                break;
            case COMMUNAL:
                titleId = R.string.communal;
                break;
            case EATING:
                titleId = R.string.eating;
                break;
            case PRODUCTS:
                titleId = R.string.products;
                break;
            case TRANSPORT:
                titleId = R.string.transport;
                break;
            case OTHER:
                titleId = R.string.other;
                break;
            default:
                return "";
        }
        return context.getString(titleId);
    }
}
