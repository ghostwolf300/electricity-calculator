package org.home.ec.data;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_transfer_period")
public class TransferPeriod {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="months_applied")
	private String monthsApplied;
	@Column(name="weekdays_applied")
	private String weekDaysApplied;
	@Column(name="hour_start")
	private int hourStart;
	@Column(name="hour_end")
	private int hourEnd;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Transfer transfer;
	
	public TransferPeriod() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMonthsApplied() {
		return monthsApplied;
	}

	public void setMonthsApplied(String monthsApplied) {
		this.monthsApplied = monthsApplied;
	}

	public String getWeekDaysApplied() {
		return weekDaysApplied;
	}

	public void setWeekDaysApplied(String weekDaysApplied) {
		this.weekDaysApplied = weekDaysApplied;
	}

	public int getHourStart() {
		return hourStart;
	}

	public void setHourStart(int hourStart) {
		this.hourStart = hourStart;
	}

	public int getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(int hourEnd) {
		this.hourEnd = hourEnd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TransferPeriod))
			return false;
		TransferPeriod other = (TransferPeriod) obj;
		return id == other.id;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
}
