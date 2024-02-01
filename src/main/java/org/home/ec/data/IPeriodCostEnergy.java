package org.home.ec.data;

import java.math.BigDecimal;

public interface IPeriodCostEnergy {
	public String getContractId();
	public int getDaysInPeriod();
	public double getPeriodConsumption();
	public double getAverageHourConsumption();
	public double getMinHourConsumption();
	public double getMaxHourConsumption();
	public BigDecimal getMinHourPrice();
	public BigDecimal getMaxHourPrice();
	public BigDecimal getSpotCostEUR();
	public BigDecimal getEnergyCostEUR();
	public BigDecimal getTransferCostEUR();
}
