-- Moguci tipovi placanja

insert into moguci_tipovi_placanja (naziv) values ("PAYPAL");
insert into moguci_tipovi_placanja (naziv) values ("BITCOIN");
insert into moguci_tipovi_placanja (naziv) values ("BANKA");

-- Tip placanja - naziv casopisa
insert into tip_placanja_model (naziv_casopisa) values("Top Speed");
insert into tip_placanja_model (naziv_casopisa) values("Casopis");
insert into tip_placanja_model (naziv_casopisa) values("Svet kompjutera");

-- Tipovi placanja za izabrani casopis
insert into tipovi_placanja(id,tip_placanja) values (1,"PAYPAL");
insert into tipovi_placanja(id,tip_placanja) values (1,"BITCOIN");
insert into tipovi_placanja(id,tip_placanja) values (2,"PAYPAL");
insert into tipovi_placanja(id,tip_placanja) values (2,"BANKA");
insert into tipovi_placanja(id,tip_placanja) values (3,"BANKA");

-- select * from moguci_tipovi_placanja;

-- banka secret
insert into banka_secret(id,casopis_username,client_id,client_password) values (1,"Casopis","4124","pass");
insert into banka_secret(id,casopis_username,client_id,client_password) values (2,"Svet kompjutera","1234","pass1");
