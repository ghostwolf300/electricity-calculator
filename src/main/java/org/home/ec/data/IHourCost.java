package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public interface IHourCost {
	public Date getKeyDate();
	public int getKeyHour();
	public double getHourConsumption();
	public double getMinConsumption();
	public double getMaxConsumption();
	public BigDecimal getPrice();
	public BigDecimal getAveragePrice();
	public BigDecimal getHourCost();
}
