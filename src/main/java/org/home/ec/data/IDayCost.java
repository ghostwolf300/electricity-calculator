package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public interface IDayCost {
	public Date getKeyDate();
	public double getDayConsumption();
	public double getMinHourConsumption();
	public double getMaxHourConsumption();
	public double getAverageHourConsumption();
	public BigDecimal getMinHourPrice();
	public BigDecimal getMaxHourPrice();
	public BigDecimal getAverageHourPrice();
	public BigDecimal getDayCostC();
	public BigDecimal getDayCostEUR();
	
}
