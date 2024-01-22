package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.DayCost;
import org.home.ec.data.HourCost;
import org.home.ec.data.PeriodCost;

public interface ReportingService {
	public List<HourCost> getHourlyCost(long locationId,Date fromDate,Date toDate);
	public List<DayCost> getDailyCost(long locationId,Date fromDate,Date toDate);
	public PeriodCost getPeriodCost(long locationId, Date fromDate,Date toDate);
}
