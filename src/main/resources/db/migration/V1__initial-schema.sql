-- Tabela: users (mantido em inglês pois já está em uso no seu sistema)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome_usuario VARCHAR(100) NOT NULL UNIQUE,
    verificado BOOLEAN DEFAULT FALSE,
    token VARCHAR(255),
    expiracao_token DATETIME,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela: profiles
CREATE TABLE profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Associação usuários <-> perfis
CREATE TABLE usuarios_perfis (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES profiles(id) ON DELETE CASCADE
);

-- Tabela: practitioners (médicos)
CREATE TABLE practitioners (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    numero_licenca VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100),
    nome_clinica VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tabela: patients (pacientes)
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    plano_saude VARCHAR(255),
    contato_emergencia VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tabela: medical_records (prontuários)
CREATE TABLE medical_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    diagnostico TEXT,
    tratamento TEXT,
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY prontuario_unico (paciente_id, medico_id),
    FOREIGN KEY (paciente_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (medico_id) REFERENCES practitioners(id) ON DELETE CASCADE
);
