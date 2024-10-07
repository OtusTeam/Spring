insert into cities (name, latin_name)
values ('Москва', 'Moscow'), ('Саратов', 'Saratov');


insert into weather_measurements(
    measurement_time, temp, feels_like, temp_min, temp_max,
    pressure, humidity, wind_speed, wind_degree, wind_direction, city_id
)
values (
    '2024-06-09 23:24:53.000', 27.3, 33, 18.23, 38.3, 1001, 47, 30, 360, 'N', 2
)