create table accounts
(
    id          bigserial primary key not null,
    holder_name varchar(256)          not null,
    number      varchar(20) unique    not null,
    balance     bigint                not null
);

create table transfer
(
    id             bigserial primary key                  not null,
    tx_id          bigint                                 not null,
    source_id      bigint references accounts (id) not null,
    destination_id bigint references accounts (id) not null,
    cash_value     bigint                                 not null,
    datetime       timestamp with time zone               not null,
    status         varchar(8)                             not null
);
