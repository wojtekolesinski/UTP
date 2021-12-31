connect 'jdbc:derby:utpdb;create=true';

drop table Offer;
drop table Place;

create table Place (
        idPlace integer not null generated by default as identity,
        englishName varchar(32) not null,
        PRIMARY KEY(idPlace)
);

create table Offer (
        idOffer integer not null generated by default as identity,
        countryISO varchar(3) not null,
        startDate date not null,
        endDate date not null,
        idPlace integer not null,
        PRIMARY KEY(idOffer),
        FOREIGN KEY(idPlace) REFERENCES Place(idPlace)
);
