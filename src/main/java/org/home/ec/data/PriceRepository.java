package org.home.ec.data;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, HourId> {
	public List<Price> findByIdKeyDateBetween(Date dateFrom,Date dateTo);
}
