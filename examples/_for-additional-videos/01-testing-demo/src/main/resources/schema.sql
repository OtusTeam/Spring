create table cities (
    id bigserial,
    name varchar(255),
    latin_name varchar(255),
    primary key (id)
);

create table weather_measurements(
    id bigserial,
    measurement_time timestamp,
    temp double precision,
    feels_like double precision,
    temp_min double precision,
    temp_max double precision,
    pressure integer,
    humidity integer,
    wind_speed double precision,
    wind_degree integer,
    wind_direction varchar(2),
    city_id bigint references cities (id)
);