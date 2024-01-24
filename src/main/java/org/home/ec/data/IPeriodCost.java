package org.home.ec.data;

import java.math.BigDecimal;

public interface IPeriodCost {
	public int getDaysInPeriod();
	public double getPeriodConsumption();
	public double getMinDayConsumption();
	public double getMaxDayConsumption();
	public double getAverageDayConsumption();
	public double getMinHourConsumption();
	public double getMaxHourConsumption();
	public BigDecimal getMinHourPrice();
	public BigDecimal getMaxHourPrice();
	public BigDecimal getPeriodCostEUR();
}
