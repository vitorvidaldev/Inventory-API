drop table if exists product;
drop table if exists sale;
drop table if exists stock;
drop table if exists `user`;

CREATE TABLE user (
  user_id char(36) NOT NULL,
   email VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   creation_date datetime NULL,
   last_update_date datetime NULL,
   CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE sale (
  sale_id char(36) NOT NULL,
   creation_date datetime NULL,
   product_id char(36) NULL,
   buyer_id char(36) NULL,
   CONSTRAINT pk_sale PRIMARY KEY (sale_id)
);

CREATE TABLE stock (
  stock_id char(36) NOT NULL,
   stock INT NOT NULL,
   creation_date datetime NULL,
   last_update_date datetime NULL,
   product_product_id char(36) NOT NULL,
   CONSTRAINT pk_stock PRIMARY KEY (stock_id)
);

CREATE TABLE product (
  product_id char(36) NOT NULL,
   product_name VARCHAR(255) NULL,
   product_brand VARCHAR(255) NULL,
   product_price DECIMAL NOT NULL,
   created_at datetime NULL,
   is_active BIT(1) NOT NULL,
   updated_at datetime NULL,
   user_id char(36) NOT NULL,
   CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE user ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);

ALTER TABLE stock ADD CONSTRAINT uc_stock_product_product UNIQUE (product_product_id);

ALTER TABLE stock ADD CONSTRAINT FK_STOCK_ON_PRODUCT_PRODUCT FOREIGN KEY (product_product_id) REFERENCES product (product_id);