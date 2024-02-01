SELECT TOP 100 to_char(key_date, 'MM') as key_month, to_char(key_date,'D') as key_weekday, cast(to_char(key_date,'HH') as int) as key_hour 
FROM t_price
WHERE
key_date='2023-11-05'

SELECT * FROM t_transfer_period;

select transfer_id from t_transfer_period where months_applied like '%11%' and weekdays_applied like '%1%' and 12 between hour_start and hour_end

insert into t_transfer_period(id,transfer_id,months_applied,weekdays_applied,hour_start,hour_end) 
values(5,1,'11,12,01,02,03','1',0,23)

update t_transfer_period set transfer_id=2 where id=5;


select
	s.id,
	s.description,
	a.consumption as consumption_kwh,
	s.price as unit_price_ct,
	(a.consumption*s.price)/100 as transfer_cost_eur 
from
(
select
	h.transfer_id as transfer_id,
	sum(h.hour_consumption) as consumption
from
(
select 
	c.key_date,
	c.key_hour,
	sum(c.consumption) as hour_consumption,
	t.transfer_id as transfer_id
from t_consumption c
left join t_transfer_period t
on t.months_applied like '%'||to_char(c.key_date,'MM')||'%'
and t.weekdays_applied like '%'||to_char(c.key_date,'D')||'%'
and c.key_hour between t.hour_start and t.hour_end
where c.key_date between '2023-11-01' and '2023-11-30'
group by c.key_date, c.key_hour,t.transfer_id
) h
group by h.transfer_id
) a
join t_transfer s
on s.id=a.transfer_id;





select
	h.transfer_id,
	h.key_date,
	h.key_hour
from
(
select 
	c.key_date,
	c.key_hour,
	sum(c.consumption) as hour_consumption,
	t.transfer_id as transfer_id
from t_consumption c
left join t_transfer_period t
on t.months_applied like '%'||to_char(c.key_date,'MM')||'%'
and t.weekdays_applied like '%'||to_char(c.key_date,'D')||'%'
and c.key_hour between t.hour_start and t.hour_end
where c.key_date between '2023-11-01' and '2023-11-30'
group by c.key_date, c.key_hour,t.transfer_id
) h
where h.transfer_id is null;