-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Nov 04, 2011 as 03:58 
-- Versão do Servidor: 5.5.8
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
CREATE TABLE `elivros`
--
-- Banco de Dados: `elivros`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `emprestimo`
--

CREATE TABLE IF NOT EXISTS `emprestimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `devolvido` tinyint(1) NOT NULL DEFAULT '0',
  `Livro_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Livro_id`),
  KEY `fk_Emprestimo_Livro` (`Livro_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estante`
--

CREATE TABLE IF NOT EXISTS `estante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros`
--

CREATE TABLE IF NOT EXISTS `livros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `autor` varchar(90) NOT NULL,
  `descricao` text,
  `anoLancamento` date DEFAULT NULL,
  `Estante_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Estante_id`),
  KEY `fk_Livro_Estante1` (`Estante_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD CONSTRAINT `fk_Emprestimo_Livro` FOREIGN KEY (`Livro_id`) REFERENCES `livros` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `livros`
--
ALTER TABLE `livros`
  ADD CONSTRAINT `fk_Livro_Estante1` FOREIGN KEY (`Estante_id`) REFERENCES `estante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
