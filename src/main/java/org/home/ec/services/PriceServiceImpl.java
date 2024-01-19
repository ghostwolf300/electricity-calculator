package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Price;
import org.home.ec.data.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
	
	@Autowired
	private PriceRepository repository;
	
	@Override
	public List<Price> getPrices(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub

	}

}
