-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.21 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para joaorodrigo_saep_db
CREATE DATABASE IF NOT EXISTS `joaorodrigo_saep_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `joaorodrigo_saep_db`;

-- Copiando estrutura para tabela joaorodrigo_saep_db.atividade
CREATE TABLE IF NOT EXISTS `atividade` (
                                           `id_atividade` int NOT NULL AUTO_INCREMENT,
                                           `desc_atividade` varchar(100) DEFAULT NULL,
    `turma_atividade` int DEFAULT NULL,
    PRIMARY KEY (`id_atividade`),
    KEY `FK_ativ_turma` (`turma_atividade`),
    CONSTRAINT `FK_ativ_turma` FOREIGN KEY (`turma_atividade`) REFERENCES `turma` (`id_turma`) ON DELETE CASCADE
    ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela joaorodrigo_saep_db.atividade: ~27 rows (aproximadamente)
INSERT INTO `atividade` (`id_atividade`, `desc_atividade`, `turma_atividade`) VALUES
                                                                                  (3, 'Lista de Portugol', 5),
                                                                                  (4, 'Lista de Árabe', 5),
                                                                                  (5, 'Atividade de Números Complexos', 5),
                                                                                  (6, 'Lista de Espanhol', 6),
                                                                                  (7, 'Lista de Java', 6),
                                                                                  (8, 'Atividade de Análise Combinatória', 6),
                                                                                  (9, 'Lista de Go', 7),
                                                                                  (10, 'Lista de Inglês', 7),
                                                                                  (11, 'Atividade de Geometria Analítica', 7),
                                                                                  (12, 'Lista de Rust', 8),
                                                                                  (13, 'Lista de Russo', 8),
                                                                                  (14, 'Atividade de Funções Quadráticas', 8),
                                                                                  (15, 'Lista de C++', 9),
                                                                                  (16, 'Lista de Francês', 9),
                                                                                  (17, 'Atividade de Estatística', 9),
                                                                                  (18, 'Lista de Italiano', 10),
                                                                                  (19, 'Lista de Python', 10),
                                                                                  (20, 'Atividade de Matrizes', 10),
                                                                                  (21, 'Lista de Húngaro', 11),
                                                                                  (22, 'Lista de C#', 11),
                                                                                  (23, 'Atividade de Funções de 1º grau', 11),
                                                                                  (25, 'Lista de Alemão', 12),
                                                                                  (26, 'Lista de Javascript', 12),
                                                                                  (27, 'Atividade de Trigonometria', 12),
                                                                                  (28, 'Lista de Hebraico', 13),
                                                                                  (29, 'Lista de Haskell', 13),
                                                                                  (30, 'Atividade de Conjuntos Numéricos', 13);

-- Copiando estrutura para tabela joaorodrigo_saep_db.professor
CREATE TABLE IF NOT EXISTS `professor` (
                                           `id_professor` int NOT NULL AUTO_INCREMENT,
                                           `nome_professor` varchar(50) DEFAULT NULL,
    `senha_professor` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id_professor`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela joaorodrigo_saep_db.professor: ~3 rows (aproximadamente)
INSERT INTO `professor` (`id_professor`, `nome_professor`, `senha_professor`) VALUES
                                                                                  (1, 'joao', 'dccd96c256bc7dd39bae41a405f25e43'),
                                                                                  (2, 'admin', '21232f297a57a5a743894a0e4a801fc3'),
                                                                                  (3, 'ewerton', '38598fe84388058562483fc1926543dd');

-- Copiando estrutura para tabela joaorodrigo_saep_db.professor_turma
CREATE TABLE IF NOT EXISTS `professor_turma` (
                                                 `id_professor` int DEFAULT NULL,
                                                 `id_turma` int DEFAULT NULL,
                                                 `id_professor_turma` int NOT NULL AUTO_INCREMENT,
                                                 PRIMARY KEY (`id_professor_turma`),
    KEY `FK_professor` (`id_professor`),
    KEY `FK_turma` (`id_turma`),
    CONSTRAINT `FK_professor` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_professor`),
    CONSTRAINT `FK_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela joaorodrigo_saep_db.professor_turma: ~9 rows (aproximadamente)
INSERT INTO `professor_turma` (`id_professor`, `id_turma`, `id_professor_turma`) VALUES
                                                                                     (1, 5, 6),
                                                                                     (1, 6, 7),
                                                                                     (1, 7, 8),
                                                                                     (3, 8, 9),
                                                                                     (3, 9, 10),
                                                                                     (3, 10, 11),
                                                                                     (2, 11, 12),
                                                                                     (2, 12, 13),
                                                                                     (2, 13, 14);

-- Copiando estrutura para tabela joaorodrigo_saep_db.turma
CREATE TABLE IF NOT EXISTS `turma` (
                                       `id_turma` int NOT NULL AUTO_INCREMENT,
                                       `nome_turma` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id_turma`)
    ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela joaorodrigo_saep_db.turma: ~9 rows (aproximadamente)
INSERT INTO `turma` (`id_turma`, `nome_turma`) VALUES
                                                   (5, 'Turma do João'),
                                                   (6, 'Turma do João 2'),
                                                   (7, 'Turma do João 3'),
                                                   (8, 'Turma do Éwerton'),
                                                   (9, 'Turma do Éwerton 2'),
                                                   (10, 'Turma do Éwerton 3'),
                                                   (11, 'Turma do admin'),
                                                   (12, 'Turma do admin 2'),
                                                   (13, 'Turma do admin 3');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
