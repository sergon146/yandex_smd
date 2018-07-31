package com.sergon146.mobilization18.ui.fragments.balance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.ui.fragments.wallet.WalletFragment;

import java.util.ArrayList;
import java.util.List;

public class WalletFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Wallet> wallets = new ArrayList<>();

    public WalletFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (wallets.isEmpty()) {
            return null;
        }

        Wallet wallet = wallets.get(position);
        return WalletFragment.newInstance(wallet.getUuid());
    }

    @Override
    public int getCount() {
        return wallets.size();
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets.clear();
        this.wallets.addAll(wallets);
        notifyDataSetChanged();
    }
}
