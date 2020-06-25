create table OWNERS
(
    ID               BIGINT       not null primary key,
    EMAIL            VARCHAR(255),
    JURIDICAL_PERSON BOOLEAN      not null,
    NAME             VARCHAR(255) not null,
    SURNAME          VARCHAR(255) not null,
    constraint UK_dw1w2xj1axp1le5oionrjfk7t unique (email)
);
create table ADDRESS
(
    ID     BIGINT not null primary key,
    CITY   VARCHAR(255),
    NUMBER VARCHAR(255),
    STREET VARCHAR(255)
);
create table BUILDING_RECORDS
(
    ID            BIGINT not null primary key,
    MARKET_VALUE  DOUBLE,
    PROPERTY_TYPE VARCHAR(10),
    SIZE_M2       INTEGER,
    ADDRESS_ID    BIGINT not null,
    OWNER_ID      BIGINT not null,
    constraint UK_9dr5ikdsimnl4pxdvsiqtlj18 unique (ADDRESS_ID),
    constraint FK6aupty8s0in4qr75vwe0luhj0
        foreign key (OWNER_ID) references owners,
    constraint FKrq28aa88ewy46imiwgmlo4ddn
        foreign key (ADDRESS_ID) references address
);