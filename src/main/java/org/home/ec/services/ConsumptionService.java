package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;

public interface ConsumptionService {
	public List<Consumption> getConsumption(Date fromDate,Date toDate);
	public void updateData(Date fromDate,Date toDate);
}
