create table activity_types (id bigint auto_increment, name varchar(255), primary key (id))
create table app_users (id bigint auto_increment, email varchar(255), name varchar(255), primary key (id))

create table app_users_activity (id bigint  auto_increment, activity_time timestamp, app_user_id bigint, activity_type_id bigint, primary key (id))
alter table app_users_activity add constraint app_users_activity_user_id_fk foreign key (app_user_id) references app_users
alter table app_users_activity add constraint app_users_activity_activity_type_id_fk foreign key (activity_type_id) references activity_types

create table activity_stat (id bigint  auto_increment, app_user_name varchar(255), activity_type varchar(255), activities_count bigint, primary key (id))
