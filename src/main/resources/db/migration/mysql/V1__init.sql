CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `wallet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL,
  `value` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users_wallet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `wallet_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `users_wallet` ADD CONSTRAINT `fk_users_wallet_wallet`
  FOREIGN KEY (`wallet_id`)
  REFERENCES `wallet` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `users_wallet` ADD CONSTRAINT `fk_users_wallet_users`
  FOREIGN KEY (`users_id`)
  REFERENCES `users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `wallet_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `wallet_id` INT NOT NULL,
  `date` DATETIME NULL,
  `type` VARCHAR(2) NULL,
  `description` VARCHAR(500) NULL,
  `value` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `wallet_items` ADD CONSTRAINT `fk_wallet_items_wallet`
  FOREIGN KEY (`wallet_id`)
  REFERENCES `wallet` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
