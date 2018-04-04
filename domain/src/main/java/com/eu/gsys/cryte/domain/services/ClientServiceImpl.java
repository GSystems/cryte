package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.transformers.ClientTransformer;
import com.eu.gsys.cryte.domain.util.FeePercEnum;
import com.eu.gsys.cryte.domain.util.GeneralConstants;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;

@Service
public class ClientServiceImpl implements ClientService {

	private DepositService depositService;
	private ClientRepository clientRepository;

	@Autowired
	public void setDepositService(DepositService depositService) {
		this.depositService = depositService;
	}

	@Autowired
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client saveClient(Client client) {
		return ClientTransformer.toClientFromEntity(clientRepository.save(ClientTransformer.fromClientToEntity(client)));
	}

	@Override
	public Iterable<Client> listAllClients() {
		return ClientTransformer.toClientListFromEntity(clientRepository.findAll());
	}

	@Override
	public Client getClientById(Integer id) {
		return ClientTransformer.toClientFromEntity(clientRepository.findById(id).get());
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

		GenericDeposit genericDeposit = depositService.updateDeposit(client, buySellOperation);
		client.getDeposits().replace(genericDeposit.getCoinId(), genericDeposit);

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

		if (OperationType.SELL.getCode().equals(buySellOperation.getOperationType().getCode())) {



			client.setProfitCtv(client.getProfitCtv());
		}

		return profit;
	}



	private Double roundDoubleValue(Double value) {
		DecimalFormat df = new DecimalFormat(GeneralConstants.DOUBLE_ROUNDING_FORMAT);
		String formate = df.format(value);

		Double finalValue = 0.0;

		try {
			finalValue = (Double) df.parse(formate);
		} catch (ParseException e){
			e.printStackTrace();
		} catch (ClassCastException e) {
			return value;
		}
		return finalValue;
	}
}
