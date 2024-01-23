package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public interface IDayCost {
	public Date getKeyDate();
	public double getDayConsumption();
	public double getMinConsumption();
	public double getMaxConsumption();
	public BigDecimal getMinHourPrice();
	public BigDecimal getMaxHourPrice();
	public BigDecimal getAverageHourPrice();
	public BigDecimal getDayCost();
	
}
