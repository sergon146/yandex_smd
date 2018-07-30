package com.sergon146.mobilization18.ui.fragments.balance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bhargavms.podslider.PodSlider;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.core.utils.Const;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.components.ZoomOutPageTransformer;
import com.sergon146.mobilization18.ui.fragments.balance.adapter.WalletFragmentAdapter;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceFragment extends BaseMvpFragment<BalancePresenter>
        implements BalanceView, Injectable {

    private static final String CURRENT_ITEM_POS = "CURRENT_ITEM_POS";

    @Inject
    @InjectPresenter
    BalancePresenter presenter;

    @BindView(R.id.balance)
    MoneyTextView balanceView;
    @BindView(R.id.additional_balance)
    MoneyTextView additionalBalance;
    @BindView(R.id.wallet_pager)
    ViewPager walletPager;
    @BindView(R.id.pager_slider)
    PodSlider indicator;

    private WalletFragmentAdapter walletAdapter;
    private int currentItemPos = Const.NONE;

    public static BalanceFragment newInstance() {
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
        View root = inflater.inflate(R.layout.fragment_balance, container, false);
        ButterKnife.bind(this, root);
        setActionBarTitle(R.string.total_balance);
        prepareViews();
        return root;
    }

    private void prepareViews() {
        walletAdapter = new WalletFragmentAdapter(getChildFragmentManager());
        walletPager.setAdapter(walletAdapter);
        walletPager.setPageTransformer(true, new ZoomOutPageTransformer());
        walletPager.setOffscreenPageLimit(3);
        walletPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItemPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showBalance(Balance balance) {
        balanceView.setAmount(balance.getAmount().floatValue());
        balanceView.setSymbol(balance.getCurrency().getSymbol());

        Map<Currency, BigDecimal> exchangeMap = balance.getExchangeMap();
        BigDecimal additional = exchangeMap.get(Currency.DOLLAR);
        additionalBalance.setAmount(additional.floatValue());
        additionalBalance.setSymbol(Currency.DOLLAR.getSymbol());
    }

    @Override
    public void showWallets(List<Wallet> wallets) {
        walletAdapter.setWallets(wallets);
        indicator.setUpWithViewPager(walletPager);

        if (currentItemPos != Const.NONE) {
            walletPager.setCurrentItem(currentItemPos);
            indicator.postDelayed(() ->
                    indicator.setCurrentlySelectedPodAndAnimate(currentItemPos), 100);
            indicator.invalidate();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_ITEM_POS, currentItemPos);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            currentItemPos = savedInstanceState.getInt(CURRENT_ITEM_POS);
        }
    }

    @Override
    public String getLogName() {
        return "BalanceFragment";
    }
}
