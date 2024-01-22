package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public class DayCost {
	
	private Date keyDate;
	private double dayConsumption;
	private double minConsumption;
	private double maxConsumption;
	private BigDecimal averageHourlyPrice;
	private BigDecimal dayCost;

	public DayCost() {
		super();
	}

	public DayCost(Date keyDate, double dayConsumption, double minConsumption, double maxConsumption,
			BigDecimal averageHourlyPrice, BigDecimal dayCost) {
		super();
		this.keyDate = keyDate;
		this.dayConsumption = dayConsumption;
		this.minConsumption = minConsumption;
		this.maxConsumption = maxConsumption;
		this.averageHourlyPrice = averageHourlyPrice;
		this.dayCost = dayCost;
	}

	public Date getKeyDate() {
		return keyDate;
	}

	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}

	public double getDayConsumption() {
		return dayConsumption;
	}

	public void setDayConsumption(double dayConsumption) {
		this.dayConsumption = dayConsumption;
	}

	public double getMinConsumption() {
		return minConsumption;
	}

	public void setMinConsumption(double minConsumption) {
		this.minConsumption = minConsumption;
	}

	public double getMaxConsumption() {
		return maxConsumption;
	}

	public void setMaxConsumption(double maxConsumption) {
		this.maxConsumption = maxConsumption;
	}

	public BigDecimal getAverageHourlyPrice() {
		return averageHourlyPrice;
	}

	public void setAverageHourlyPrice(BigDecimal averageHourlyPrice) {
		this.averageHourlyPrice = averageHourlyPrice;
	}

	public BigDecimal getDayCost() {
		return dayCost;
	}

	public void setDayCost(BigDecimal dayCost) {
		this.dayCost = dayCost;
	}

	
	
	
}
