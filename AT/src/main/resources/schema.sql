-- no terminal do powerSsheel
    -- 1 - conecte com o postgreSql
        -- psql -U postgres
    -- 2 - informe a senha do posgress
        -- admin
    -- 3 - cria o banco, cria o usuario, da permissoes no banco, sair do sql
-- CREATE DATABASE database_at;
-- CREATE USER at WITH ENCRYPTED PASSWORD 'password';
-- GRANT ALL PRIVILEGES ON DATABASE database_at TO at;
-- \q




CREATE TABLE IF NOT EXISTS tb_aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    email VARCHAR(255),
    telefone VARCHAR(20),
    endereco VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tb_disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS aluno_disciplina (
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    PRIMARY KEY (aluno_id, disciplina_id),
    FOREIGN KEY (aluno_id) REFERENCES tb_aluno(id) ON DELETE CASCADE,
    FOREIGN KEY (disciplina_id) REFERENCES tb_disciplina(id) ON DELETE CASCADE
);