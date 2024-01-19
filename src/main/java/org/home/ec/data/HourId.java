package org.home.ec.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class HourId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5336190173280721205L;
	
	@Column(name="key_date")
	private Date keyDate;
	
	@Column(name="key_hour")
	private int keyHour;
	
	public HourId() {
		super();
	}
	
	public HourId(Date keyDate, int keyHour) {
		this.keyDate=keyDate;
		this.keyHour=keyHour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyDate, keyHour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HourId other = (HourId) obj;
		return Objects.equals(keyDate, other.keyDate) && keyHour == other.keyHour;
	}

	public Date getKeyDate() {
		return keyDate;
	}

	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}

	public int getKeyHour() {
		return keyHour;
	}

	public void setKeyHour(int keyHour) {
		this.keyHour = keyHour;
	}

}
