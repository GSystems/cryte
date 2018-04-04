package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.cryte.domain.transformers.CryteTransformer;
import com.eu.gsys.cryte.domain.util.FeePercEnum;
import com.eu.gsys.cryte.domain.util.GeneralConstants;
import com.eu.gsys.cryte.domain.util.OperationEnum;
import com.eu.gsys.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client saveClient(Client client) {
		return CryteTransformer.toClientFromEntity(clientRepository.save(CryteTransformer.fromClientToEntity(client)));
	}

	@Override
	public Iterable<Client> listAllClients() {
		return CryteTransformer.toClientListFromEntity(clientRepository.findAll());
	}

	@Override
	public Client getClientById(Integer id) {
		return CryteTransformer.toClientFromEntity(clientRepository.findById(id).get());
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}

	@Override
	public void addOperation(Client client, BuySellOperation buySellOperation) {
		BuySellOperation updatedOperation = calculatePricesAndFee(buySellOperation);
		client.getBuySellOperations().add(updatedOperation);

		Client updatedClient = processOperation(client, updatedOperation);

		saveClient(updatedClient);
	}

	protected BuySellOperation calculatePricesAndFee(BuySellOperation buySellOperation) {
		BuySellOperation buySellOperationForSave = buySellOperation;

		Double coinPrice = buySellOperation.getOperationCtv() / buySellOperation.getCoinQty();
		Double feeCtv = buySellOperation.getOperationCtv() * FeePercEnum.valueOf(buySellOperation.getCoinId()).getFeePerc() / 100;
		Double minimumWithdrawalCoinPrice = (buySellOperation.getOperationCtv() + 2 * feeCtv) * coinPrice / buySellOperation.getOperationCtv();

		buySellOperationForSave.setCoinPrice(roundDoubleValue(coinPrice));
		buySellOperationForSave.setFeeCtv(roundDoubleValue(feeCtv));
		buySellOperationForSave.setMinimumWithdrawalCoinPrice(roundDoubleValue(minimumWithdrawalCoinPrice));

		return buySellOperationForSave;
	}

	protected Client processOperation(Client client, BuySellOperation buySellOperation) {
		Client updatedClient = client;

		Deposit deposit = updateDeposit(client, buySellOperation);
		client.getDeposits().replace(deposit.getCoinId(), deposit);

		Double totalFeesCtv = calculateTotalFeeCtv(client, buySellOperation);
		client.setPayedFeesCtv(totalFeesCtv);

		Double profit = calculateProfit(client, buySellOperation);
		client.setProfitCtv(profit);

		return client;
	}

	protected Double calculateTotalFeeCtv(Client client, BuySellOperation buySellOperation) {
		return client.getPayedFeesCtv() + buySellOperation.getFeeCtv();
	}

	protected Double calculateProfit(Client client, BuySellOperation buySellOperation) {
		Double profit = 0.0;

		if (OperationEnum.SELL.getCode().equals(buySellOperation.getOperationType())) {
			client.setProfitCtv(client.getProfitCtv());
		}

		return profit;
	}

	protected Deposit updateDeposit(Client client, BuySellOperation buySellOperation) {
		Deposit deposit = client.getDeposits().get(buySellOperation.getCoinId());
		deposit.setCtv(deposit.getCtv() + buySellOperation.getOperationCtv());

		return deposit;
	}

	private Double roundDoubleValue(Double value) {
		DecimalFormat df = new DecimalFormat(GeneralConstants.DOUBLE_ROUNDING_FORMAT);
		String formate = df.format(value);

		Double finalValue = 0.0;

		try {
			finalValue = (Double) df.parse(formate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalValue;
	}
}
