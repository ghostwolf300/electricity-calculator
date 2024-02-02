package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.ConsumptionRepository;
import org.home.ec.data.DayCost;
import org.home.ec.data.HourCost;
import org.home.ec.data.IDayCost;
import org.home.ec.data.IHourCost;
import org.home.ec.data.IPeriodCostEnergy;
import org.home.ec.data.ITransferCost;
import org.home.ec.data.PeriodCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingServiceImpl implements ReportingService {
	
	@Autowired
	private ConsumptionRepository repository;
	
	@Override
	public List<IHourCost> getHourlyCost(long locationId, Date fromDate, Date toDate) {
		List<IHourCost> hourlyCost=repository.calculateHourlyConsumptionAndCost(locationId, fromDate, toDate);
		return hourlyCost;
	}

	@Override
	public List<IDayCost> getDailyCost(long locationId, Date fromDate, Date toDate) {
		List<IDayCost> dayCost=repository.calculateDailyConsumptionAndCost(locationId, fromDate, toDate);
		return dayCost;
	}

	@Override
	public IPeriodCostEnergy getPeriodCost(long locationId, Date fromDate, Date toDate) {
		IPeriodCostEnergy periodCostEnergy=repository.calculatePeriodCostSpot(locationId, fromDate, toDate);
		return periodCostEnergy;
	}

	@Override
	public List<ITransferCost> getTransferCost(long locationId, Date fromDate, Date toDate) {
		List<ITransferCost> transferCosts=repository.calculateTransferCost(locationId, fromDate, toDate);
		return transferCosts;
	}

}
