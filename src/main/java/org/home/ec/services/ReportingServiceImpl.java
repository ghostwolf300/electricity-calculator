package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.ConsumptionRepository;
import org.home.ec.data.DayCost;
import org.home.ec.data.HourCost;
import org.home.ec.data.IDayCost;
import org.home.ec.data.IHourCost;
import org.home.ec.data.IPeriodCost;
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
	public IPeriodCost getPeriodCost(long locationId, Date fromDate, Date toDate) {
		IPeriodCost periodCost=repository.calculatePeriodConsumptionAndCost(locationId, fromDate, toDate);
		return periodCost;
	}

}
