package org.home.ec.data;

import java.math.BigDecimal;

public interface ITransferCost {
	public long getId();
	public String getDescription();
	public double getConsumption();
	public BigDecimal getUnitPriceCt();
	public BigDecimal getTransferCostEUR();
}
