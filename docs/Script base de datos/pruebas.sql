select * from Tipos_cuerpo_agua;

-- INSERCIONES EN TABLA DE TIPOS DE CUERPO DE AGUA
--insert into tipos_cuerpo_agua(descripcion) values("Laguna");
--insert into tipos_cuerpo_agua(descripcion) values("Rio");

select * from Cuerpos_de_agua;

select C.nombre, C.id_cuerpo_agua, TC.descripcion, TA.descripcion, C.numero_clasificacion 
from Cuerpos_de_agua C
inner join Tipos_cuerpo_agua TC
on C.id_cuerpo_agua = TC.id_tipo_cuerpo_agua
inner join Tipos_de_agua TA
on C.id_tipo_agua = TA.id_tipo_agua;
 
-- INSERCIONES EN TABLA TIPOS DE AGUA
-- insert into tipos_de_agua(descripcion) values("Dulce");
-- insert intotipos_de_agua(descripcion) values("salado);
select * from Tipos_de_agua;

select count(*) from Cuerpos_de_agua;

select count(*) from Tipos_de_agua where descripcion in("DULCE", "SALADA");

select id_cuerpo_agua, nombre, min(numero_clasificacion) as "numero_clasificacion" from Cuerpos_de_agua;
-- insert into Cuerpos_de_agua(id_tipo_cuerpo_agua, id_tipo_agua, nombre, municipio, numero_clasificacion, clasificacion_IRCA) values(1, 1, "Mallorquin", "Barranquilla", "30", "MEDIO");

select descripcion from Tipos_cuerpo_agua;

select descripcion from Tipos_de_agua
