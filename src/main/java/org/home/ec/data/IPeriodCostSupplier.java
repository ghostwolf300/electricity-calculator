package org.home.ec.data;

import java.math.BigDecimal;

public interface IPeriodCostSupplier {
	public double getConsumption();
	public BigDecimal getMarginCost();
	public BigDecimal getTransferCost();
}
