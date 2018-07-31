package com.sergon146.business.repository;

import com.sergon146.business.model.Balance;

import io.reactivex.Observable;


public interface BalanceRepository {

    Observable<Balance> getBalance();
}
