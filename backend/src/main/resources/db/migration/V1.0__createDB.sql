create sequence hibernate_sequence start with 1 increment by 1;
create table copy (id bigint not null, owned_by_username varchar(255) not null, pokemon_poke_dex_id bigint, primary key (id));
create table item (poke_dex_id bigint not null, description varchar(500), name varchar(128), type varchar(128), value bigint not null, primary key (poke_dex_id));
create table user (username varchar(255) not null, available_lootboxes bigint not null, email varchar(255), enabled boolean not null, hashed_password varchar(255), in_game_cash bigint not null, name varchar(128), surname varchar(128), primary key (username));
create table user_roles (user_username varchar(255) not null, roles varchar(255));
alter table item add constraint UK_lcsp6a1tpwb8tfywqhrsm2uvg unique (name);
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table copy add constraint FKoa2q2pr9mtt4xi0taylwkccvh foreign key (owned_by_username) references user;
alter table copy add constraint FKf244l0xp1b4a03beppgbvbqnv foreign key (pokemon_poke_dex_id) references item;
alter table user_roles add constraint FK1misndtpfm9hx3ttvixdus8d1 foreign key (user_username) references user;