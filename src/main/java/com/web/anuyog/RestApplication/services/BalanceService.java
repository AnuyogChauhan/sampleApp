package com.web.anuyog.RestApplication.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.anuyog.RestApplication.domain.Balance;
import com.web.anuyog.RestApplication.domain.User;

public interface BalanceService extends CrudRepository<Balance, Integer> {

	Balance save(Balance user);
	List<Balance> findByUser(User user);
}

