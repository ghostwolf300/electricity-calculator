package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
@SqlResultSetMapping(
		name="HourlyCostResult",
		classes= {
				@ConstructorResult(
						targetClass=HourCost.class,
						columns= {
								@ColumnResult(name="keyDate", type=Date.class),
								@ColumnResult(name="keyHour", type=Integer.class),
								@ColumnResult(name="hourConsumption", type=Double.class),
								@ColumnResult(name="minConsumption", type=Double.class),
								@ColumnResult(name="maxConsumption", type=Double.class),
								@ColumnResult(name="price", type=BigDecimal.class),
								@ColumnResult(name="averagePrice", type=BigDecimal.class),
								@ColumnResult(name="hourCost", type=BigDecimal.class)
						})
		})
@NamedNativeQuery(
		name="ConsumptionRepository.getHourCost",
		query="select c.key_date, c.key_hour, sum(c.consumption) hour_consumption,min(c.consumption) min_consumption,max(c.consumption) max_consumption, p.price, avg(p.price) avg_price,(sum(c.consumption)*price) hour_cost"
				+ "from t_consumption c left join t_price p"
				+ "on c.key_date=p.key_date and c.key_hour=p.key_hour"
				+ "where location_id=:locationId and c.key_date between :fromDate and :toDate"
				+ "group by c.key_date,c.key_hour",
		resultSetMapping="HourlyCostResult"
		)
public interface ConsumptionRepository extends JpaRepository<Consumption, HourId>,JpaSpecificationExecutor<Consumption> {
	public List<Consumption> findByIdLocationIdAndIdKeyDateBetween(long locationId,Date dateFrom,Date dateTo);
	
	public List<HourCost> getHourCost(@Param("locationId") long locationId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
}
