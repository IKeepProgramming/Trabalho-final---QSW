drop database if exists sistemaacademico;
create database if not exists sistemaacademico;
use sistemaacademico;

create table if not exists Disciplina(
	idDisciplina int primary key auto_increment not null,
    nomeDisciplina varchar(100) not null,
    requisitoDisciplina boolean,
    horarioInicioDisciplina Time not null,
    horarioFimDisciplina Time not null,
    quantidadeMaximoAlunosDisciplina int not null,
    numeroAulasDisciplina int not null
)ENGINE = InnoDB;

create table if not exists Aluno(
	idAluno int primary key auto_increment not null,
    nomeAluno varchar(150) not null,
	prontuarioAluno int not null,
    iraAluno float not null,
    cpfAluno varchar(11) not null,
    emailAluno varchar(100)
)ENGINE = InnoDB;

create table if not exists listaEspera(
	idListaEspera int primary key auto_increment not null,
    idDisciplina int not null,
    nomeDisciplina varchar(100) not null,
    foreign key (idDisciplina) references Disciplina(idDisciplina),
    idAluno int not null,
    foreign key (idAluno) references Aluno(idAluno)
)ENGINE = InnoDB;

create table if not exists AlunoDisciplina(	
    idAlunoDisciplina int primary key auto_increment,
    nomeDisciplina varchar(100) not null,
    idDisciplina int not null,
    foreign key (idDisciplina) references Disciplina(idDisciplina),
    idAluno int not null,
    foreign key (idAluno) references Aluno(idAluno)
)ENGINE = InnoDB;

create table if not exists Turma(
	idTurma int primary key auto_increment,
    professor varchar(100) not null,
    sala varchar(4) not null
)ENGINE = InnoDB;

create table if not exists turmaDisciplina(
	idTurmaDisciplina int primary key auto_increment,
    idDisciplina int not null,
    foreign key (idDisciplina) references Disciplina(idDisciplina),
    idTurma int not null,
    foreign key (idTurma) references Turma(idTurma)
)ENGINE = InnoDB;

INSERT INTO Disciplina (nomeDisciplina, requisitoDisciplina, horarioInicioDisciplina, horarioFimDisciplina, quantidadeMaximoAlunosDisciplina, numeroAulasDisciplina) VALUES
    ('Introdução à Programação', false, '09:00:00', '10:30:00', 0, 4),
    ('Algoritmos e Estruturas de Dados', false, '11:00:00', '12:30:00', 40, 4),
    ('Banco de Dados', false, '14:00:00', '15:30:00', 35, 2),
    ('Redes de Computadores', false, '10:00:00', '11:30:00', 25, 2),
    ('Sistemas Operacionais', false, '08:00:00', '09:30:00', 30, 2),
    ('Engenharia de Software', false, '13:00:00', '14:30:00', 40, 4),
    ('Desenvolvimento Web', false, '15:00:00', '16:30:00', 50, 4),
    ('Inteligência Artificial', false, '16:00:00', '17:30:00', 20, 2),
    ('Computação Gráfica', false, '12:00:00', '13:30:00', 25, 2),
    ('Segurança da Informação', false, '17:00:00', '18:30:00', 30, 4);
    
INSERT INTO ALUNO VALUES (1,'Carlos Eduardo do Amaral',3030091,9.5,'42376752892','emailteste@gmail.com'),
						 (2,'Lucas',3029905,9.5,'42376752892','emailteste@gmail.com');

INSERT INTO Turma (idTurma, professor, sala) VALUES (1,'Romario dos Santos Cardoso','B403'),
																  (2,'Luiz Gustavo', 'A504'),
                                                                  (3,'Talita', 'A509'),
                                                                  (4,'André', 'A505'),
                                                                  (5,'Flávio', 'B408'),
                                                                  (6,'Elisandra', 'A506'),
                                                                  (7,'Ana Paula', 'B407'),
                                                                  (8,'Rosalvo', 'A501'),
                                                                  (9,'Vendramel', 'C504'),
                                                                  (10,'Roberto', 'B507');

INSERT INTO turmaDisciplina (idTurmaDisciplina, idDisciplina, idTurma) VALUES (1,2,1),
																			  (2,1,2),
                                                                              (3,3,3),
                                                                              (4,5,4),
                                                                              (5,4,5),
                                                                              (6,8,6),
                                                                              (7,9,7),
                                                                              (8,7,8),
                                                                              (9,6,9),
                                                                              (10,10,10);
                                                                              
