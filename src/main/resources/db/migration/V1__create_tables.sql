CREATE TYPE tipo_usuario AS ENUM ('cidadão', 'administrador');
CREATE TYPE tipo_telefone_usuario AS ENUM ('Celular', 'Residencial', 'Comercial');
CREATE TYPE tipo_unidade_saude AS ENUM ('UBS', 'Hospital', 'Farmácia');
CREATE TYPE tipo_telefone_unidade AS ENUM ('Fixo', 'WhatsApp', 'Emergência');
CREATE TYPE tipo_atendimento AS ENUM ('Plantão', 'Atendimento Normal', 'Emergência');
CREATE TYPE tipo_medicamento AS ENUM ('Genérico', 'De Marca');
CREATE TYPE status_vacinacao AS ENUM ('Ativo', 'Concluído');


CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario tipo_usuario NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE telefones_usuarios (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    numero VARCHAR(20) NOT NULL,
    tipo tipo_telefone_usuario NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE enderecos_usuarios (
    id SERIAL PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo tipo_unidade_saude NOT NULL,
    horario_inicio_atendimento VARCHAR(5) NOT NULL,
    horario_fim_atendimento VARCHAR(5) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE servicos_saude (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    horario_inicio varchar(5) NOT NULL,
    horario_fim varchar(5) NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE telefones_unidades (
    id SERIAL PRIMARY KEY,
    unidade_id INT NOT NULL,
    numero VARCHAR(20) NOT NULL,
    tipo tipo_telefone_unidade NOT NULL,
    FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE enderecos_unidades (
    id SERIAL PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    crm VARCHAR(20) UNIQUE NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    unidade_saude_id INT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE atendimento_hospitalar (
    id SERIAL PRIMARY KEY,
    medico_id INT NOT NULL,
    unidade_saude_id INT NOT NULL,
    data_plantao DATE NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    tipo tipo_atendimento NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES medicos(id) ON DELETE CASCADE,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE medicamentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    quantidade INT NOT NULL,
    tipo_medicamento tipo_medicamento NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE calendario_vacinacao (
    id SERIAL PRIMARY KEY,
    vacina VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status status_vacinacao NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE noticias (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    administrador_id INT NOT NULL,
    FOREIGN KEY (administrador_id) REFERENCES usuarios(id) ON DELETE CASCADE
);