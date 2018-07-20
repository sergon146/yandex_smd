package com.sergon146.business.repository;

import com.sergon146.business.model.Balance;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface BalanceRepository {

    Observable<List<Balance>> getBalance();
}
