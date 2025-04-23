CREATE TABLE usuarios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    nome_usuario VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    token VARCHAR(64),
    expiracao_token TIMESTAMP,
    refresh_token VARCHAR(64),
    expiracao_refresh_token TIMESTAMP,
    verificado BOOLEAN NOT NULL DEFAULT 1,
    COLUMN ativo BOOLEAN NOT NULL DEFAULT 1
);