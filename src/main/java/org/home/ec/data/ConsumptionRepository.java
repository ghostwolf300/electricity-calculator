package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
//@SqlResultSetMapping(
//		name="HourlyCostResult",
//		classes= {
//				@ConstructorResult(
//						targetClass=HourCost.class,
//						columns= {
//								@ColumnResult(name="keyDate", type=Date.class),
//								@ColumnResult(name="keyHour", type=Integer.class),
//								@ColumnResult(name="hourConsumption", type=Double.class),
//								@ColumnResult(name="minConsumption", type=Double.class),
//								@ColumnResult(name="maxConsumption", type=Double.class),
//								@ColumnResult(name="price", type=BigDecimal.class),
//								@ColumnResult(name="averagePrice", type=BigDecimal.class),
//								@ColumnResult(name="hourCost", type=BigDecimal.class)
//						})
//		})
//@NamedNativeQuery(
//		name="ConsumptionRepository.getHourCost",
//		query="select c.key_date, c.key_hour, sum(c.consumption) hour_consumption,min(c.consumption) min_consumption,max(c.consumption) max_consumption, p.price, avg(p.price) avg_price,(sum(c.consumption)*price) hour_cost"
//				+ "from t_consumption c left join t_price p"
//				+ "on c.key_date=p.key_date and c.key_hour=p.key_hour"
//				+ "where location_id=:locationId and c.key_date between :fromDate and :toDate"
//				+ "group by c.key_date,c.key_hour",
//		resultSetMapping="HourlyCostResult"
//		)
public interface ConsumptionRepository extends JpaRepository<Consumption, HourId>,JpaSpecificationExecutor<Consumption> {
	public List<Consumption> findByIdLocationIdAndIdKeyDateBetween(long locationId,Date dateFrom,Date dateTo);
	
	@Query("select c.id.keyDate as keyDate, c.id.keyHour as keyHour, sum(c.consumption) as hourConsumption,min(c.consumption) as minConsumption,max(c.consumption) as maxConsumption, p.price as price, avg(p.price) as averagePrice,(sum(c.consumption)*price) as hourCost "
			+ "from Consumption as c left join Price as p "
			+ "on c.id.keyDate=p.id.keyDate and c.id.keyHour=p.id.keyHour "
			+ "where c.id.locationId=:locationId and c.id.keyDate between :fromDate and :toDate "
			+ "group by c.id.keyDate,c.id.keyHour")
	public List<IHourCost> calculateHourlyConsumptionAndCost(@Param("locationId") long locationId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	
	@Query("select h.keyDate as keyDate, sum(h.hourConsumption) as dayConsumption,min(h.hourConsumption) as minConsumption,max(h.hourConsumption) as maxConsumption,min(h.price) as minHourPrice,max(h.price) as maxHourPrice,avg(h.price) as averageHourPrice,(sum(h.hourCost)/100) as dayCost "
			+ "from "
			+ "(select c.id.keyDate as keyDate, c.id.keyHour as keyHour, sum(c.consumption) as hourConsumption, p.price as price,(sum(c.consumption)*price) as hourCost "
			+ "from Consumption as c left join Price as p "
			+ "on c.id.keyDate=p.id.keyDate and c.id.keyHour=p.id.keyHour "
			+ "where c.id.locationId=:locationId and c.id.keyDate between :fromDate and :toDate "
			+ "group by c.id.keyDate,c.id.keyHour) as h "
			+ "group by h.keyDate")
	public List<IDayCost> calculateDailyConsumptionAndCost(@Param("locationId") long locationId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
}
