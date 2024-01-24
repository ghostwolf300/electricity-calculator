package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public interface IHourCost {
	public Date getKeyDate();
	public int getKeyHour();
	public double getHourConsumption();
	public double getMinQuartConsumption();
	public double getMaxQuartConsumption();
	public double getAverageQuartConsumption();
	public BigDecimal getPrice();
	public BigDecimal getAveragePrice();
	public BigDecimal getHourCostSpot();
	public BigDecimal getMargin();
	public BigDecimal getHourCostTotal();
}
