CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario varchar(50) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE telefones_usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id INT NOT NULL,
    numero VARCHAR(20) NOT NULL,
    tipo varchar(50) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE enderecos_usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id INT NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100) NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE unidades_saude (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    tipo varchar(50) NOT NULL,
    horario_inicio_atendimento VARCHAR(5) NOT NULL,
    horario_fim_atendimento VARCHAR(5) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE servicos_saude (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    horario_inicio varchar(5) NOT NULL,
    horario_fim varchar(5) NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE telefones_unidades (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unidade_id INT NOT NULL,
    numero VARCHAR(20) NOT NULL,
    tipo varchar(50) NOT NULL,
    FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE enderecos_unidades (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unidade_id INT NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100) NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    latitude DECIMAL(9,6) NULL,
    longitude DECIMAL(9,6) NULL,
    cep VARCHAR(10) NOT NULL,
    FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE medicos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    crm VARCHAR(20) UNIQUE NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    unidade_saude_id INT NOT NULL,
    data_plantao varchar(50) NOT NULL,
    horario_inicio varchar(5) NOT NULL,
    horario_fim varchar(5) NOT NULL,
    tipo varchar(50) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE medicamentos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    quantidade INT NOT NULL,
    tipo_medicamento varchar(50) NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE calendario_vacinacao (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vacina VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status varchar(50) NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE noticias (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    administrador_id INT NOT NULL,
    FOREIGN KEY (administrador_id) REFERENCES usuarios(id) ON DELETE CASCADE
);