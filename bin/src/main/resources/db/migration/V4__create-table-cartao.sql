CREATE TABLE `cartao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `numero_cartao` VARCHAR(19) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
  `data_vencimento` DATE NOT NULL,
  `anuidade` DOUBLE NOT NULL,
  `tipo` VARCHAR(10) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
  `nome` VARCHAR(45) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
  `bandeira` VARCHAR(45) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
  `ativo` TINYINT DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `numero_unico` (`numero_cartao`)
) COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB;