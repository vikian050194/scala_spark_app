DROP TABLE IF EXISTS "limits_per_hour";
CREATE TABLE "public"."limits_per_hour" (
    "limit_name" character varying NOT NULL,
    "limit_value" integer NOT NULL,
    "effective_date" date NOT NULL
) WITH (oids = false);

INSERT INTO "limits_per_hour" ("limit_name", "limit_value", "effective_date") VALUES
('min',	1024,	'2020-04-19'),
('max',	1073741824,	'2020-04-19');