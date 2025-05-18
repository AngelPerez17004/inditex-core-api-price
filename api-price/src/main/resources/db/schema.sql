


DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS price_rate;

CREATE TABLE price_rate (
    price_rate_id BIGINT PRIMARY KEY,
    code VARCHAR(3) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(18, 4) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE price (
  id BIGINT  PRIMARY KEY AUTO_INCREMENT,
  brand_id INT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_rate_id INT NOT NULL,
  product_id INT NOT NULL,
  priority INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT NULL,
   CONSTRAINT fk_price_rate FOREIGN KEY (price_rate_id) REFERENCES price_rate(price_rate_id)
);

CREATE INDEX idx_prices_product_brand_date
ON price (product_id, brand_id, start_date, end_date, priority);