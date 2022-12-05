
/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee (
   employee_id          serial not null,
   identification       varchar(10)          not null,
   names                varchar(50)          not null,
   surnames             varchar(50)          not null,
   email                varchar(50)          not null,
   birthdate            date                 null,
   address              varchar(200)         null,
   phone                varchar(10)          null,
   vaccination_status   bool                 null,
   constraint pk_employee primary key (employee_id)
);

/*==============================================================*/
/* Index: employee_pk                                           */
/*==============================================================*/
create unique index employee_pk on employee (
employee_id
);

/*==============================================================*/
/* Table: record_vaccination                                    */
/*==============================================================*/
create table record_vaccination (
   record_id            serial not null,
   vaccine_id           int4                 not null,
   employee_id          int4                 not null,
   vaccination_date     date                 not null,
   number_doses         int4                 not null,
   constraint pk_record_vaccination primary key (record_id)
);

/*==============================================================*/
/* Index: record_vaccination_pk                                 */
/*==============================================================*/
create unique index record_vaccination_pk on record_vaccination (
record_id
);

/*==============================================================*/
/* Index: record_vaccination_type_fk                            */
/*==============================================================*/
create  index record_vaccination_type_fk on record_vaccination (
vaccine_id
);

/*==============================================================*/
/* Index: record_vaccination_employee_fk                        */
/*==============================================================*/
create  index record_vaccination_employee_fk on record_vaccination (
employee_id
);

/*==============================================================*/
/* Table: type_vaccine                                          */
/*==============================================================*/
create table type_vaccine (
   vaccine_id           serial not null,
   vaccine_name         varchar(15)          not null
      constraint ckc_vaccine_name_type_vac check (vaccine_name in ('Pfizer','Jhonson&Jhonson','Sputnik','AstraZeneca')),
   constraint pk_type_vaccine primary key (vaccine_id)
);

/*==============================================================*/
/* Index: type_vaccine_pk                                       */
/*==============================================================*/
create unique index type_vaccine_pk on type_vaccine (
vaccine_id
);

/*==============================================================*/
/* Table: user_vaccination                                      */
/*==============================================================*/
create table user_vaccination (
   user_id              serial not null,
   employee_id          int4                 not null,
   username             varchar(30)          not null,
   password             varchar(100)          not null,
   role                 varchar(8)           not null,
   constraint pk_user_vaccination primary key (user_id)
);

/*==============================================================*/
/* Index: user_vaccination_pk                                   */
/*==============================================================*/
create unique index user_vaccination_pk on user_vaccination (
user_id
);

/*==============================================================*/
/* Index: employee_user_fk                                      */
/*==============================================================*/
create  index employee_user_fk on user_vaccination (
employee_id
);

alter table record_vaccination
   add constraint fk_record_v_record_va_employee foreign key (employee_id)
      references employee (employee_id)
      on delete restrict on update restrict;

alter table record_vaccination
   add constraint fk_record_v_record_va_type_vac foreign key (vaccine_id)
      references type_vaccine (vaccine_id)
      on delete restrict on update restrict;

alter table user_vaccination
   add constraint fk_user_vac_employee__employee foreign key (employee_id)
      references employee (employee_id)
      on delete restrict on update restrict;


INSERT INTO employee VALUES (0, '1150409397', 'Diana', 'Arévalo', 'diana@gmail.com', '1997-07-15', 'Ecuador', '0985766202', true);

INSERT INTO user_vaccination VALUES (0, 0,'admin', '$2a$10$rsPyATKEYLDYo7e1kgli9eKVVQ.LbCzOqeqTXbKNn0YDWvg.rDn6y', 'ADMIN');

INSERT INTO type_vaccine VALUES (1, 'Pfizer');
INSERT INTO type_vaccine VALUES (2, 'Jhonson&Jhonson');
INSERT INTO type_vaccine VALUES (3, 'Sputnik');
INSERT INTO type_vaccine VALUES (4, 'AstraZeneca');

INSERT INTO record_vaccination VALUES (0, 1 , 1 , '2022-12-05', 1);
