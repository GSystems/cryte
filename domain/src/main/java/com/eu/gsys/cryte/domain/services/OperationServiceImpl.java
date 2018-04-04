package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.GenericDeposit;
import com.eu.gsys.cryte.domain.transformers.OperationTransformer;
import com.eu.gsys.cryte.domain.util.FeePercEnum;
import com.eu.gsys.cryte.domain.util.GeneralConstants;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;

@Service
public class OperationServiceImpl implements OperationService {

    private DepositService depositService;
    private OperationRepository operationRepository;

    @Autowired
    public void setDepositService(DepositService depositService) {
        this.depositService = depositService;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation saveOperation(Operation operation) {
        Operation operationForSave = calculatePricesAndFee(operation);

        return OperationTransformer
                .toOperationFromEntity(operationRepository.save(OperationTransformer.fromOperationToEntity(operationForSave)));
    }

    @Override
    public Iterable<Operation> listAllOperations() {
        return OperationTransformer.toOperationListFromEntity(operationRepository.findAll());
    }

    @Override
    public Operation getOperationById(Integer id) {
        return OperationTransformer.toOperationFromEntity(operationRepository.findById(id).get());
    }

    @Override
    public void deleteOperation(Integer id) {
        operationRepository.deleteById(id);
    }

    @Override
    public void addOperation(Client client, Operation operation) {
        Operation updatedOperation = calculatePricesAndFee(operation);
        client.getOperations().add(updatedOperation);

        Client updatedClient = processOperation(client, updatedOperation);

    }

    protected Operation calculatePricesAndFee(Operation operation) {
        Operation operationForSave = operation;

        Double coinPrice = operation.getOperationCtv() / operation.getCoinQty();
        Double feeCtv = operation.getOperationCtv() * FeePercEnum.valueOf(operation.getCoinType().getCode()).getFeePerc() / 100;

        Double minimumWithdrawalCoinPrice = 0.0;

        if (OperationType.BUY.equals(operation.getOperationType())) {
            minimumWithdrawalCoinPrice = (operation.getOperationCtv() + 2 * feeCtv) * coinPrice / operation.getOperationCtv();
        }

        operationForSave.setCoinPrice(roundDoubleValue(coinPrice));
        operationForSave.setFeeCtv(roundDoubleValue(feeCtv));
        operationForSave.setMinimumWithdrawalCoinPrice(roundDoubleValue(minimumWithdrawalCoinPrice));

        return operationForSave;
    }

    protected Client processOperation(Client client, Operation operation) {
        Client updatedClient = client;

        GenericDeposit genericDeposit = depositService.updateDeposit(client, operation);
        client.getDeposits().replace(genericDeposit.getCoinType(), genericDeposit);

        Double totalFeesCtv = calculateTotalFeeCtv(client, operation);
        client.setPayedFeesCtv(totalFeesCtv);

        Double profit = calculateProfit(client, operation);
        client.setProfitCtv(profit);

        return client;
    }

    protected Double calculateTotalFeeCtv(Client client, Operation operation) {
        return client.getPayedFeesCtv() + operation.getFeeCtv();
    }

    protected Double calculateProfit(Client client, Operation operation) {
        Double profit = 0.0;

        if (OperationType.SELL.getCode().equals(operation.getOperationType().getCode())) {



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
