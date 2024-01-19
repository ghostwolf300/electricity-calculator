package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Price;

public interface PriceService {
	public List<Price> getPrices(Date fromDate, Date toDate);
	public void updateData(Date fromDate, Date toDate);
}
