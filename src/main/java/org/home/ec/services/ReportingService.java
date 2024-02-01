package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.DayCost;
import org.home.ec.data.HourCost;
import org.home.ec.data.IDayCost;
import org.home.ec.data.IHourCost;
import org.home.ec.data.IPeriodCostEnergy;
import org.home.ec.data.PeriodCost;

public interface ReportingService {
	public List<IHourCost> getHourlyCost(long locationId,Date fromDate,Date toDate);
	public List<IDayCost> getDailyCost(long locationId,Date fromDate,Date toDate);
	public IPeriodCostEnergy getPeriodCost(long locationId, Date fromDate,Date toDate);
}
