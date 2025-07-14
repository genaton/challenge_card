CREATE TABLE `contrato` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `status` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
  `cliente_id` BIGINT NOT NULL,
  `cartao_id` BIGINT NOT NULL,
  `ativo` TINYINT DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_contrato_cliente_idx` (`cliente_id`),
  INDEX `fk_contrato_cartao_idx` (`cartao_id`),
  CONSTRAINT `fk_contrato_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_cartao`
    FOREIGN KEY (`cartao_id`)
    REFERENCES `cartao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB;