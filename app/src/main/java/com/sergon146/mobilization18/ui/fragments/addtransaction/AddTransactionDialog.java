package com.sergon146.mobilization18.ui.fragments.addtransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.model.types.TransactionCategory;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.dialog.BaseDialogMvpFragment;
import com.sergon146.mobilization18.util.ItemUtils;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;

import org.angmarch.views.NiceSpinner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTransactionDialog extends BaseDialogMvpFragment<AddTransactionPresenter>
        implements AddTransactionView, Injectable {

    @Inject
    @InjectPresenter
    AddTransactionPresenter presenter;

    @BindView(R.id.radio_expanse)
    RadioButton expaseRadio;
    @BindView(R.id.radio_income)
    RadioButton incomeRadio;
    @BindView(R.id.currency_spinner)
    NiceSpinner currencySpinner;
    @BindView(R.id.wallet_spinner)
    NiceSpinner walletSpinner;
    @BindView(R.id.category_spinner)
    NiceSpinner categorySpinner;
    @BindView(R.id.money_edit)
    EasyMoneyEditText moneyEdit;

    public static AddTransactionDialog getInstance() {
        return new AddTransactionDialog();
    }

    @Override
    @ProvidePresenter
    public AddTransactionPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_add_transaction, container, false);
        ButterKnife.bind(this, root);
        prepareView();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void prepareView() {
        List<String> currencyList = new ArrayList<>();
        for (Currency cur : Currency.values()) {
            currencyList.add(getString(R.string.details, cur.getShortName(), cur.getSymbol()));
        }
        currencySpinner.attachDataSource(currencyList);
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                moneyEdit.setCurrency(Currency.values()[i].getSymbol());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        int index = currencySpinner.getSelectedIndex();
        moneyEdit.setCurrency(Currency.values()[index].getSymbol());


        List<String> categoryList = new ArrayList<>();
        for (TransactionCategory category : TransactionCategory.values()) {
            categoryList.add(ItemUtils.getCategoryTitle(getContext(), category));
        }
        categorySpinner.attachDataSource(categoryList);
    }

    @Override
    public void showWallets(List<Wallet> wallets) {
        List<String> walletsTitles = new ArrayList<>();
        for (Wallet wallet : wallets) {
            walletsTitles.add(getString(R.string.details, wallet.getName(),
                    ItemUtils.getWalletTypeTitle(getContext(), wallet)));
        }
        walletSpinner.attachDataSource(walletsTitles);
    }

    @OnClick(R.id.accept)
    void onAcceptClick() {
        if (moneyEdit.getValueString().equals("0")) {
            Toast.makeText(getContext(), getString(R.string.empty_amount_toast),
                    Toast.LENGTH_SHORT).show();
        } else {
            OperationType operType = expaseRadio.isChecked()
                    ? OperationType.EXPENSE
                    : OperationType.INCOME;
            Currency currency = Currency.values()[currencySpinner.getSelectedIndex()];
            BigDecimal amount = BigDecimal.valueOf(Double.valueOf(moneyEdit.getValueString()));
            BigDecimal exchange = BigDecimal.valueOf(35.6);
            Transaction transaction = new Transaction(operType, currency, amount, exchange);
            getPresenter().addTransaction(transaction);
            showGotcha();
            dismiss();
        }
    }

    private void showGotcha() {
        Toast toast = new Toast(getContext());
        ImageView view = new ImageView(getContext());
        view.setImageResource(R.drawable.ic_gotcha);
        toast.setView(view);
        toast.show();
    }

    @OnClick(R.id.cancel)
    void onCancelClick() {
        dismiss();
    }

    @Override
    public String getLogName() {
        return "AddTransactionDialog";
    }
}
