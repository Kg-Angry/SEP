-- Moguci tipovi placanja

insert into moguci_tipovi_placanja (naziv) values ("PAYPAL");
insert into moguci_tipovi_placanja (naziv) values ("BITCOIN");
insert into moguci_tipovi_placanja (naziv) values ("BANKA");

-- Tip placanja - naziv casopisa
insert into tip_placanja_model (naziv_casopisa) values("Top Speed");
insert into tip_placanja_model (naziv_casopisa) values("Casopis");

-- Tipovi placanja za izabrani casopis
insert into tipovi_placanja(id,tip_placanja) values (1,"PAYPAL");
insert into tipovi_placanja(id,tip_placanja) values (1,"BITCOIN");
insert into tipovi_placanja(id,tip_placanja) values (2,"PAYPAL");
insert into tipovi_placanja(id,tip_placanja) values (2,"BANKA");

-- Secret
insert into secret(id,naziv_casopisa,merchant_id,merchant_pass) VALUES (1,"Casopis",21,"Sifra");

-- select * from moguci_tipovi_placanja;

