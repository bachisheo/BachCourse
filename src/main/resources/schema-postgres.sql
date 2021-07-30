create table if not exists contractor(
    id serial primary key,
    inn varchar(12),
    kpp varchar(9),
    bic varchar(9),
    accountNumber varchar(20),
    nomination varchar(20)
)
