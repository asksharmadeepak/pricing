create table trade
(
   transactionId integer auto_increment not null,
   tradeTimestamp varchar(255) not null,
   quantity integer not null,
   buySellIndicator integer not null,
   price float not null,
   primary key(transactionId)
);


create table oil
(
   id varchar(255) not null,
   type varchar(255) not null,
   barrelValue float not null,
   fixedRevenue float not null,
   variableRevenue float not null,
);