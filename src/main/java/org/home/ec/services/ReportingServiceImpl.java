package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.ConsumptionRepository;
import org.home.ec.data.DayCost;
import org.home.ec.data.HourCost;
import org.home.ec.data.PeriodCost;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportingServiceImpl implements ReportingService {
	
	@Autowired
	private ConsumptionRepository repository;
	
	@Override
	public List<HourCost> getHourlyCost(long locationId, Date fromDate, Date toDate) {
		List<HourCost> hourlyCost=repository.getHourCost(locationId, fromDate, toDate);
		return hourlyCost;
	}

	@Override
	public List<DayCost> getDailyCost(long locationId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodCost getPeriodCost(long locationId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
