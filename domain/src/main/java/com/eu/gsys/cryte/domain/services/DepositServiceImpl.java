package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.cryte.domain.transformers.CryteTransformer;
import com.eu.gsys.infrastructure.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DepositServiceImpl implements DepositService {

	private DepositRepository depositRepository;

	@Autowired
	public void setDepositRepository(DepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}

	@Override public Deposit saveDeposit(Deposit deposit) {
		return CryteTransformer
				.toDepositFromEntity(depositRepository.save(CryteTransformer.fromDepositToEntity(deposit)));
	}

	@Override public Iterable<Deposit> listAllDeposits() {
		return CryteTransformer.toDepositListFromEntity(depositRepository.findAll());
	}

	@Override public Deposit getDepositById(Integer id) {
		return CryteTransformer.toDepositFromEntity(depositRepository.findById(id).get());
	}

	@Override public void deleteDeposit(Integer id) {
		depositRepository.deleteById(id);
	}
}
