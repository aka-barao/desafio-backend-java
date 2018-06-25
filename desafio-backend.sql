CREATE DATABASE desafio_backend;

USE desafio_backend;

CREATE TABLE tb_customer_account(
	id_customer BIGINT NOT NULL AUTO_INCREMENT,
	cpf_cnpj VARCHAR(20),
	nm_customer VARCHAR(70),
	is_active TINYINT,
	vl_total DOUBLE,
	PRIMARY KEY (id_customer));