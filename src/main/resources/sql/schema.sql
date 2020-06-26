create table ADDRESS
(
    ID     BIGINT not null primary key,
    CITY   VARCHAR(100),
    NUMBER VARCHAR(5),
    STREET VARCHAR(100)
);
create table OWNERS
(
    ID               BIGINT       not null primary key,
    EMAIL            VARCHAR(50),
    JURIDICAL_PERSON BOOLEAN      not null,
    NAME             VARCHAR(50) not null,
    SURNAME          VARCHAR(50) not null,
    constraint UK_dw1w2xj1axp1le5oionrjfk7t unique (email)
);
create table BUILDING_RECORDS
(
    ID            BIGINT not null primary key,
    MARKET_VALUE  DOUBLE,
    PROPERTY_TYPE CHAR,
    SIZE_M2       INTEGER,
    ADDRESS_ID    BIGINT not null,
    OWNER_ID      BIGINT not null,
    constraint UK_9dr5ikdsimnl4pxdvsiqtlj18 unique (ADDRESS_ID),
    constraint FK6aupty8s0in4qr75vwe0luhj0
        foreign key (OWNER_ID) references owners,
    constraint FKrq28aa88ewy46imiwgmlo4ddn
        foreign key (ADDRESS_ID) references address
);
-- create sequence ADDRESS_SEQ;
-- create sequence BUILDING_RECORD_SEQ;
-- create sequence OWNER_SEQ;



