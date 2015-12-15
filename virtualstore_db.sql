-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 15-Dez-2015 às 23:28
-- Versão do servidor: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `virtualstore_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `books`
--

CREATE TABLE `books` (
  `books_id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `descricao` varchar(445) NOT NULL,
  `preco` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `books`
--

INSERT INTO `books` (`books_id`, `nome`, `autor`, `descricao`, `preco`) VALUES
(1, 'Como Cozinhar', 'Filho da Ana Maria Braga', 'Livro de receitas', '20'),
(2, 'Laranja Mecânica', 'Anthony Burgess', 'Laranja Mecânica (A Clockwork Orange) é um romance distópico de Anthony Burgess publicado em 1962.', '30'),
(12, '1984', 'George Orwell', 'Nineteen Eighty-Four é um romance distópico clássico do autor britânico George Orwell', '15'),
(15, 'Testado', 'Teste', 'Teste', '12');

-- --------------------------------------------------------

--
-- Estrutura da tabela `electronics`
--

CREATE TABLE `electronics` (
  `electronics_id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `descricao` varchar(445) NOT NULL,
  `preco` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `electronics`
--

INSERT INTO `electronics` (`electronics_id`, `nome`, `marca`, `descricao`, `preco`) VALUES
(2, 'TV 31''''', 'Sony', 'Televisão de 31 polegadas', '1000'),
(3, 'Celular', 'Motorola', 'Celular Motorola', '500');

-- --------------------------------------------------------

--
-- Estrutura da tabela `login_details`
--

CREATE TABLE `login_details` (
  `login_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `admin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login_details`
--

INSERT INTO `login_details` (`login_id`, `username`, `password`, `admin`) VALUES
(3, 'administrador', '123456', 'admin'),
(55, 'teste', 'teste', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`books_id`);

--
-- Indexes for table `electronics`
--
ALTER TABLE `electronics`
  ADD PRIMARY KEY (`electronics_id`);

--
-- Indexes for table `login_details`
--
ALTER TABLE `login_details`
  ADD PRIMARY KEY (`login_id`,`username`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `books_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `electronics`
--
ALTER TABLE `electronics`
  MODIFY `electronics_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `login_details`
--
ALTER TABLE `login_details`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
