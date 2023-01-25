-- MySQL Script generated by MySQL Workbench
-- Wed Dec  7 08:34:55 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `amiguapp` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `amiguapp` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `amiguapp` ;

-- -----------------------------------------------------
-- Table `Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Cliente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NULL,
  `email` VARCHAR(100) NULL,
  `telefone` VARCHAR(20) NULL,
  `senha` VARCHAR(20) NULL,
  `cpf` VARCHAR(15) NULL,
  `rua` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NULL,
  `complemento` VARCHAR(10) NULL,
  `cep` INT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Produto` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `preco` DECIMAL(6,2) NULL,
  `tamanho` DECIMAL(6,2) NULL,
  `descricao` VARCHAR(300) NULL,
  `imagem` LONGBLOB NULL,
  `fkIdVendedor` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `fk_Produto_Vendedor`
    FOREIGN KEY (`fkIdVendedor`)
    REFERENCES `Vendedor` (`idVendedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Vendedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Vendedor` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Vendedor` (
  `idVendedor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NULL,
  `email` VARCHAR(100) NULL,
  `telefone` VARCHAR(20) NULL,
  `senha` VARCHAR(20) NULL,
  `imagem` LONGBLOB NULL,
  PRIMARY KEY (`idVendedor`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;