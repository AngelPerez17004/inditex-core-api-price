-- Tarifas
INSERT INTO price_rate (price_rate_id, code, description, price, currency, created_at, updated_at) VALUES (1, 'PRE', 'Tarifa Premium'   ,35.50, 'EUR','2025-05-14 00:00:00',null);
INSERT INTO price_rate (price_rate_id, code, description, price, currency, created_at, updated_at) VALUES (2, 'STA', 'Tarifa Standard'  ,25.45, 'EUR','2025-05-14 00:00:00',null);
INSERT INTO price_rate (price_rate_id, code, description, price, currency, created_at, updated_at) VALUES (3, 'REG', 'Tarifa Regular'   ,30.50, 'EUR','2025-05-14 00:00:00',null);
INSERT INTO price_rate (price_rate_id, code, description, price, currency, created_at, updated_at) VALUES (4, 'PRO', 'Tarifa Promocion' ,38.95, 'EUR','2025-05-14 00:00:00',null);

-- Precios
INSERT INTO price (brand_id, start_date, end_date, price_rate_id, product_id, priority, created_at, updated_at)
VALUES
(1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0 ,'2025-05-14 00:00:00',null),
(1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1 ,'2025-05-14 00:00:00',null),
(1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1 ,'2025-05-14 00:00:00',null),
(1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1 ,'2025-05-14 00:00:00',null);