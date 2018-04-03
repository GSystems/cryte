package com.eu.gsys.cryte.web.setup;

import com.eu.gsys.cryte.domain.services.OperationService;
import com.eu.gsys.cryte.web.mappers.OperationMapper;
import com.eu.gsys.cryte.web.models.OperationModel;
import com.eu.gsys.cryte.web.util.CoinEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OperationLoader implements ApplicationListener<ContextRefreshedEvent> {

	private OperationService operationService;
	private Logger log = Logger.getLogger(OperationLoader.class);

	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		OperationModel first = new OperationModel();

		first.setId(1L);
		first.setBuy(true);
		first.setCoinId(CoinEnum.BITCOIN.getCode());
		first.setCoinName(CoinEnum.BITCOIN.name());
		first.setCoinQty(1.0);
		first.setOperationCtv(5700.0);
		first.setCoinPrice(5700.0);
		first.setDate(LocalDate.now());

		operationService.saveOperation(OperationMapper.toOperationFromModel(first));

		log.info("Saved operation - id: " + first.getId());

		OperationModel second = new OperationModel();

		second.setId(2L);
		second.setSell(true);
		second.setCoinId(CoinEnum.ETHEREUM.getCode());
		second.setCoinName(CoinEnum.ETHEREUM.name());
		second.setCoinQty(2.0);
		second.setOperationCtv(600.0);
		second.setCoinPrice(1200.0);
		second.setDate(LocalDate.now());

		operationService.saveOperation(OperationMapper.toOperationFromModel(second));

		log.info("Saved operation - id: " + second.getId());
	}
}

