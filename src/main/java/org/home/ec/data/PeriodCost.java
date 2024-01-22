package org.home.ec.data;

import java.math.BigDecimal;

public class PeriodCost {
	
	private double periodConsumption;
	private double minConsumption;
	private double maxConsumption;
	private BigDecimal averagePrice;
	private BigDecimal periodCost;
	
	public PeriodCost() {
		super();
	}

	public PeriodCost(double periodConsumption, double minConsumption, double maxConsumption, BigDecimal averagePrice,
			BigDecimal periodCost) {
		super();
		this.periodConsumption = periodConsumption;
		this.minConsumption = minConsumption;
		this.maxConsumption = maxConsumption;
		this.averagePrice = averagePrice;
		this.periodCost = periodCost;
	}

	public double getPeriodConsumption() {
		return periodConsumption;
	}

	public void setPeriodConsumption(double periodConsumption) {
		this.periodConsumption = periodConsumption;
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

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getPeriodCost() {
		return periodCost;
	}

	public void setPeriodCost(BigDecimal periodCost) {
		this.periodCost = periodCost;
	}
	
	
}
