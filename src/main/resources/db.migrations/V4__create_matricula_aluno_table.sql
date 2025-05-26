CREATE TABLE matricula_aluno (
    id BIGINT AUTO_INCREMENT PRIMARYL KEY,
    aluno_id BIGINT,
    disciplina_id BIGINT,
    nota1 DOUBLE,
    nota2 DOUBLE,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);