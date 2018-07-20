package com.sergon146.business.repository;

import com.sergon146.business.model.Balance;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface BalanceRepository {

    Observable<Balance> getBalance();
}
