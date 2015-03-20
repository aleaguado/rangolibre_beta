CREATE DATABASE  IF NOT EXISTS `rlv1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rlv1`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: rlv1
-- ------------------------------------------------------
-- Server version	5.5.41-0+wheezy1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `idstatus` varchar(15) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `tabela` varchar(45) NOT NULL,
  PRIMARY KEY (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('A','PEDIDO ABERTO E POSSUI ITENS','PEDIDO'),('C','PEDIDO CRIADO E SEM ITENS','PEDIDO/PEDICAR'),('E','PEDIDO ENCERRADO E PAGO','PEDIDO'),('F','ITEM FATURADO','PEDICAR'),('I','MESA INATIVA','MESA'),('L','MESA LIVRE','MESA'),('N','PRECO ANTIGO','CARDPRECO'),('O','MESA OCUPADA','MESA'),('P','ITEM PREPARADO','PEDICAR'),('T','PEDIDO TRANSFERIDO P/ OUTRO','PEDIDO'),('V','PREÇO VALIDO','CARDPRECO');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreforne`
--

DROP TABLE IF EXISTS `ingreforne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingreforne` (
  `idingre` int(11) NOT NULL,
  `idfornecedor` int(11) NOT NULL,
  PRIMARY KEY (`idingre`,`idfornecedor`),
  KEY `fk_ingrediente_has_fornecedor_fornecedor1` (`idfornecedor`),
  KEY `fk_ingrediente_has_fornecedor_ingrediente1` (`idingre`),
  CONSTRAINT `fk_ingrediente_has_fornecedor_fornecedor1` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingrediente_has_fornecedor_ingrediente1` FOREIGN KEY (`idingre`) REFERENCES `ingrediente` (`idingre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreforne`
--

LOCK TABLES `ingreforne` WRITE;
/*!40000 ALTER TABLE `ingreforne` DISABLE KEYS */;
INSERT INTO `ingreforne` VALUES (1,0),(3,0),(4,0),(0,1),(1,1),(2,1);
/*!40000 ALTER TABLE `ingreforne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrecar`
--

DROP TABLE IF EXISTS `ingrecar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingrecar` (
  `idingre` int(11) NOT NULL,
  `idcardapio` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`idingre`,`idcardapio`),
  KEY `fk_ingrediente_has_cardapio_cardapio2` (`idcardapio`),
  KEY `fk_ingrediente_has_cardapio_ingrediente2` (`idingre`),
  KEY `fk_cardapio` (`idcardapio`),
  CONSTRAINT `fk_cardapio` FOREIGN KEY (`idcardapio`) REFERENCES `cardapio` (`idcardapio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingrediente_has_cardapio_ingrediente2` FOREIGN KEY (`idingre`) REFERENCES `ingrediente` (`idingre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrecar`
--

LOCK TABLES `ingrecar` WRITE;
/*!40000 ALTER TABLE `ingrecar` DISABLE KEYS */;
INSERT INTO `ingrecar` VALUES (0,14,1),(0,17,1),(1,1,2),(1,11,2),(1,13,3),(1,15,2),(1,16,2),(1,17,2),(1,27,2),(2,0,0),(2,1,1),(2,11,1),(2,13,2),(2,14,2),(2,15,1),(2,16,1),(2,17,1),(2,27,1),(3,0,0),(3,2,3),(3,11,3),(3,12,2),(3,15,2),(3,17,3),(3,27,1),(4,11,1),(4,16,1);
/*!40000 ALTER TABLE `ingrecar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_car`
--

DROP TABLE IF EXISTS `cat_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_car` (
  `idcatcar` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`idcatcar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_car`
--

LOCK TABLES `cat_car` WRITE;
/*!40000 ALTER TABLE `cat_car` DISABLE KEYS */;
INSERT INTO `cat_car` VALUES (0,'SALADA BÁSICA','PRATOS'),(1,'T-BONE','PRATOCARNE'),(3,'SORRENTINO TRADICIONAL','PRATOS'),(4,'SORRENTINO COGUMELO','PRATOS'),(5,'COCA-COLA','BEBIDAS'),(6,'GUARANÁ ANTARCTICA','BEBIDAS'),(7,'FANTA','BEBIDAS'),(8,'CERVEJA STELA','BEBIDAS'),(9,'CERVEJA QUILMES','BEBIDAS'),(10,'CARNE PICANTE','EMPANADAS'),(11,'CARNE','EMPANADAS'),(12,'QUEIJO','EMPANADAS'),(13,'FRIJOLES','EMPANADAS');
/*!40000 ALTER TABLE `cat_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardapio`
--

DROP TABLE IF EXISTS `cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardapio` (
  `idcardapio` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `idusuario` varchar(10) NOT NULL,
  `padrao` tinyint(1) NOT NULL,
  `pontoCarne` varchar(15) DEFAULT NULL,
  `idcatcar` int(11) NOT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`idcardapio`),
  KEY `fk_cardapio_usuario1` (`idusuario`),
  KEY `fk_cardapio_cat_car1` (`idcatcar`),
  CONSTRAINT `fk_cardapio_cat_car1` FOREIGN KEY (`idcatcar`) REFERENCES `cat_car` (`idcatcar`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cardapio_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardapio`
--

LOCK TABLES `cardapio` WRITE;
/*!40000 ALTER TABLE `cardapio` DISABLE KEYS */;
INSERT INTO `cardapio` VALUES (0,'SALADA BÁSICA','AGUADO',1,'',0,'Y'),(1,'T-BONE','AGUADO',1,'PONTO',1,'Y'),(2,'T-BONE PONTOMENOS','AGUADO',0,'PONTOMENOS',1,'Y'),(3,'SORRENTINO TRADICIONAL','AGUADO',1,'',3,'Y'),(4,'SORRENTINO COGUMELO','AGUADO',1,NULL,4,'Y'),(5,'SORRENTINO COGUMELO S/ CARNE','AGUADO',0,NULL,4,'Y'),(6,'SORRENTINO COGUMELO C/ TOMATE','AGUADO',0,'',4,''),(7,'T-BONE C/ ALFAC; C/ PICAN; S/ TOMAT;MAL PASSADA','aguado',0,'MAL PASSADA',0,'Y'),(8,'T-BONE S/ TOMAT; C/ PICAN;MAL PASSADO','aguado',0,'MAL PASSADO',0,'Y'),(9,'T-BONE S/ RUCUL; C/ PICAN;MAL PASSADA','aguado',0,'MAL PASSADA',0,'Y'),(10,'T-BONE C/ PICAN; S/ TOMAT; C/ COCA-;EBEM','aguado',0,'EBEM',0,'Y'),(11,'T-BONE C/ COCA-; C/ PICAN;SEI LA','aguado',0,'SEI LA',0,'Y'),(12,'T-BONE S/ RUCUL; S/ TOMAT; C/ PICAN;MAL PASSADA','aguado',0,'MAL PASSADA',1,'Y'),(13,'SORRENTINO TRADICIONAL C/ RUCUL; C/ TOMAT;','aguado',0,'',3,'Y'),(14,'SORRENTINO TRADICIONAL C/ TOMAT; C/ ALFAC;','aguado',0,'',3,'Y'),(15,'T-BONE C/ PICAN;','aguado',0,'',1,'Y'),(16,'T-BONE C/ COCA-;PONTOMAISMAIS','aguado',0,'PONTOMAISMAIS',1,'Y'),(17,'T-BONE C/ ALFAC; C/ PICAN;MENOS','aguado',0,'MENOS',1,'Y'),(18,'COCA-COLA','AGUADO',1,'',5,'Y'),(19,'GUARANÁ ANTARCTICA','AGUADO',1,'',6,'Y'),(20,'FANTA','AGUADO',1,'',7,'Y'),(21,'CERVEJA STELA','AGUADO',1,'',8,'Y'),(22,'CERVEJA QUILMES','AGUADO',1,'',9,'Y'),(23,'CARNE PICANTE','AGUADO',1,'',10,'Y'),(24,'CARNE','AGUADO',1,'',11,'Y'),(25,'QUEIJO','AGUADO',1,'',12,'Y'),(26,'FRIJOLES','AGUADO',1,'',13,'Y'),(27,'T-BONE C/ PICAN;','aguado',0,'',1,'Y');
/*!40000 ALTER TABLE `cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedicar`
--

DROP TABLE IF EXISTS `pedicar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedicar` (
  `idpedido` varchar(16) NOT NULL,
  `idcardapio` int(11) NOT NULL,
  `idcriador` varchar(10) NOT NULL,
  `idpreparo` varchar(10) DEFAULT NULL,
  `idstatus` varchar(15) NOT NULL,
  `horacria` datetime NOT NULL,
  `horapreparo` datetime DEFAULT NULL,
  `idcliente` int(11) NOT NULL,
  `pago` varchar(1) NOT NULL,
  `idcaixa` varchar(10) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idpedido`,`idcardapio`,`idcliente`,`horacria`),
  KEY `idcriador` (`idcriador`),
  KEY `idpreparo` (`idpreparo`),
  KEY `idstatus` (`idstatus`),
  KEY `fk_pedicar_1` (`idcliente`),
  KEY `cardapio_ibfk_1` (`idcardapio`),
  KEY `pedicar_ibfk_6` (`idcaixa`),
  CONSTRAINT `cardapio_ibfk_1` FOREIGN KEY (`idcardapio`) REFERENCES `cardapio` (`idcardapio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedicar_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedicar_ibfk_1` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`),
  CONSTRAINT `pedicar_ibfk_3` FOREIGN KEY (`idcriador`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `pedicar_ibfk_4` FOREIGN KEY (`idpreparo`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `pedicar_ibfk_5` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `pedicar_ibfk_6` FOREIGN KEY (`idcaixa`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedicar`
--

LOCK TABLES `pedicar` WRITE;
/*!40000 ALTER TABLE `pedicar` DISABLE KEYS */;
INSERT INTO `pedicar` VALUES ('0102150116350013',2,'aguado','aguado','P','2015-02-02 01:06:43','2015-02-02 17:24:21',0,'F',NULL,''),('0102150116350013',2,'aguado','aguado','P','2015-02-01 01:25:26','2015-02-02 00:20:50',1,'V','aguado',''),('0102150116350013',7,'aguado','aguado','P','2015-02-02 00:56:00','2015-02-02 00:56:26',0,'F',NULL,''),('0102150116350013',12,'aguado','aguado','P','2015-02-01 01:25:15','2015-02-02 00:20:59',2,'F',NULL,''),('0202150014060021',2,'aguado','aguado','P','2015-02-02 00:18:49','2015-02-02 00:20:55',2,'V','aguado',''),('0202150014060021',2,'aguado','aguado','P','2015-02-02 00:19:06','2015-02-02 00:20:57',2,'V','aguado',''),('0202150014060021',2,'aguado','aguado','P','2015-02-02 00:19:45','2015-02-02 00:21:00',2,'V','aguado',''),('0202150014060021',2,'aguado','aguado','P','2015-02-02 00:19:56','2015-02-02 00:21:02',2,'V','aguado',''),('0202150014060021',7,'aguado','aguado','P','2015-02-02 00:15:30','2015-02-02 00:21:14',1,'V','aguado',''),('0202150014060021',7,'aguado','aguado','P','2015-02-02 00:15:38','2015-02-02 00:21:16',1,'V','aguado',''),('0202150014060021',10,'aguado','aguado','P','2015-02-02 00:14:32','2015-02-02 00:21:17',2,'V','aguado',''),('0202151723400050',18,'aguado','aguado','P','2015-02-02 17:24:08','2015-02-02 18:45:26',0,'V','aguado',''),('0202151723400050',23,'aguado','aguado','P','2015-02-02 17:24:08','2015-02-02 17:24:31',0,'V','aguado',''),('0202151723400050',26,'aguado','aguado','P','2015-02-02 17:24:08','2015-02-02 17:24:33',0,'V','aguado',''),('0202151840190006',18,'aguado',NULL,'C','2015-02-02 18:44:33',NULL,1,'F',NULL,''),('0202151840190006',23,'aguado','aguado','P','2015-02-02 18:44:33','2015-02-02 18:45:37',1,'F',NULL,''),('0202151858360014',2,'aguado',NULL,'C','2015-02-02 18:59:13',NULL,1,'V','aguado',''),('0202151858360014',13,'aguado',NULL,'C','2015-02-02 18:58:57',NULL,2,'V','aguado',''),('0202151858360014',22,'aguado',NULL,'C','2015-02-02 18:58:57',NULL,2,'V','aguado',''),('1901152203010001',0,'AGUADO','aguado','P','2015-01-20 13:45:42','2015-01-29 22:54:01',2,'F','HUGO',NULL),('1901152203010001',0,'AGUADO','aguado','P','2015-01-20 13:45:53','2015-01-29 22:54:15',3,'F','HUGO',NULL),('1901152203010001',1,'aguado','aguado','P','2015-01-28 03:04:10','2015-01-29 22:20:01',2,'F',NULL,''),('1901152203010001',1,'aguado','aguado','P','2015-01-28 03:04:37','2015-01-29 22:20:21',2,'F',NULL,''),('1901152203010001',2,'AGUADO','ANTONIO','P','2015-01-20 13:24:29','2015-01-20 13:27:17',2,'F','HUGO',NULL),('1901152203010001',2,'AGUADO','ANTONIO','P','2015-01-20 13:25:00','2015-01-20 13:29:08',2,'V','HUGO',NULL),('1901152203010001',2,'aguado','aguado','P','2015-01-28 03:03:52','2015-01-29 22:19:51',2,'F',NULL,''),('1901152203010001',2,'aguado','aguado','P','2015-01-28 03:04:10','2015-01-29 22:20:14',2,'F',NULL,''),('1901152203010001',2,'aguado','aguado','P','2015-01-28 03:04:37','2015-01-29 22:20:24',2,'F',NULL,''),('1901152203010001',2,'aguado','aguado','P','2015-01-28 02:55:28','2015-01-29 22:19:46',3,'F',NULL,'S/ Arroz'),('1901152203010001',3,'aguado','aguado','P','2015-01-28 03:03:52','2015-01-29 22:53:47',2,'F',NULL,'S/ CARNE'),('1901152203010001',3,'aguado','aguado','P','2015-01-28 03:04:10','2015-01-29 22:53:49',2,'F',NULL,'S/ CARNE'),('1901152203010001',3,'aguado','aguado','P','2015-01-28 03:04:37','2015-01-29 22:53:51',2,'F',NULL,'S/ CARNE'),('1901152203010001',3,'aguado','aguado','P','2015-01-28 02:55:28','2015-01-29 22:54:12',3,'F',NULL,'c/ queijo'),('1901152203010001',3,'aguado','aguado','P','2015-01-28 11:53:02','2015-01-29 22:53:25',5,'F',NULL,'S/ CARNE'),('1901152203010001',5,'aguado','aguado','P','2015-01-28 03:04:37','2015-01-29 22:53:33',1,'F',NULL,'C/ PESTO AZEITONA'),('1901152203010001',6,'aguado','aguado','P','2015-01-28 03:04:37','2015-01-29 22:54:03',1,'F',NULL,''),('1901152203010001',12,'aguado','aguado','P','2015-01-29 01:01:42','2015-01-29 22:20:46',2,'F',NULL,''),('2701150205340030',10,'aguado',NULL,'C','2015-02-02 18:48:13',NULL,0,'F',NULL,''),('2701150205340030',13,'aguado',NULL,'C','2015-02-02 18:48:13',NULL,0,'F',NULL,''),('2801150305130005',2,'aguado','aguado','P','2015-01-28 03:05:48','2015-01-29 22:20:27',0,'F',NULL,''),('2801150305130005',3,'aguado','aguado','P','2015-01-28 03:05:48','2015-01-29 22:54:26',0,'F',NULL,'C/ QUEIJO'),('2901150005110011',2,'aguado','aguado','P','2015-01-29 00:05:48','2015-01-29 22:20:29',0,'F',NULL,''),('2901150005110011',6,'aguado','aguado','P','2015-01-29 00:05:48','2015-01-29 22:54:28',0,'F',NULL,''),('2901150005110011',6,'aguado','aguado','P','2015-01-29 01:24:43','2015-01-29 22:54:04',1,'F',NULL,''),('2901150005110011',12,'aguado','aguado','P','2015-01-29 01:08:10','2015-01-29 22:21:20',1,'F',NULL,''),('2901150105430022',2,'aguado','aguado','P','2015-01-29 01:07:40','2015-01-29 22:21:08',2,'V','aguado','BEM SALGADA'),('2901150105430022',2,'aguado','aguado','P','2015-01-29 01:08:10','2015-01-29 22:21:12',2,'V','aguado','BEM SALGADA'),('2901150105430022',13,'aguado','aguado','P','2015-01-29 01:07:11','2015-01-29 22:53:56',1,'V','aguado',''),('2901150105430022',13,'aguado','aguado','P','2015-01-29 01:07:40','2015-01-29 22:53:58',1,'V','aguado',''),('2901150105430022',13,'aguado','aguado','P','2015-01-29 01:08:10','2015-01-29 22:54:10',1,'V','aguado',''),('2901150113530012',12,'aguado','aguado','P','2015-01-29 01:15:35','2015-01-29 22:21:28',2,'F',NULL,''),('2901150113530012',12,'aguado','aguado','P','2015-01-29 01:19:14','2015-01-29 22:21:31',2,'F',NULL,''),('2901150113530012',13,'aguado','aguado','P','2015-01-29 01:19:15','2015-01-29 22:54:17',1,'V','aguado',''),('2901150123020013',5,'aguado','aguado','P','2015-01-29 01:24:18','2015-01-29 22:54:13',2,'V','aguado',''),('2901150123020013',6,'aguado','aguado','P','2015-01-29 01:23:38','2015-01-29 22:54:07',1,'V','aguado',''),('2901150123020013',12,'aguado','aguado','P','2015-01-29 01:23:38','2015-01-29 22:21:37',1,'V','aguado',''),('2901150123020013',13,'aguado','aguado','P','2015-01-29 01:24:18','2015-01-29 22:54:23',2,'V','aguado','COM MATO'),('2901152055590007',5,'aguado','aguado','P','2015-01-29 20:58:17','2015-01-29 22:54:21',1,'F',NULL,''),('2901152055590007',14,'aguado','aguado','P','2015-01-29 20:58:17','2015-01-29 22:54:30',1,'F',NULL,'BEM QUENTE!');
/*!40000 ALTER TABLE `pedicar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `idmesa` varchar(4) NOT NULL,
  `capacidade` int(11) NOT NULL,
  `idstatus` varchar(15) NOT NULL,
  `ativo` varchar(45) NOT NULL,
  PRIMARY KEY (`idmesa`),
  KEY `fk_mesa_status1` (`idstatus`),
  CONSTRAINT `fk_mesa_status1` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
INSERT INTO `mesa` VALUES ('0001',4,'O','Y'),('0002',2,'O','N'),('0003',4,'O','Y'),('0004',4,'O','Y'),('0005',4,'O','Y'),('0006',4,'O','Y'),('0007',4,'O','Y'),('0008',4,'O','Y'),('0009',4,'L','Y'),('0010',4,'O','Y'),('0011',4,'O','Y'),('0012',4,'O','Y'),('0013',4,'O','Y'),('0014',4,'L','Y'),('0015',4,'L','Y'),('0016',4,'O','Y'),('0017',4,'O','Y'),('0018',4,'O','Y'),('0019',4,'L','Y'),('0020',4,'O','Y'),('0021',4,'L','Y'),('0022',4,'L','Y'),('0023',4,'L','Y'),('0024',4,'O','Y'),('0025',4,'L','Y'),('0026',4,'L','Y'),('0027',4,'L','Y'),('0028',4,'L','Y'),('0029',4,'L','Y'),('0030',4,'O','Y'),('0031',4,'L','Y'),('0032',4,'L','Y'),('0033',4,'L','Y'),('0034',4,'L','Y'),('0035',4,'L','Y'),('0036',4,'L','Y'),('0037',4,'L','Y'),('0038',4,'L','Y'),('0039',4,'L','Y'),('0040',4,'L','Y'),('0041',4,'L','Y'),('0042',4,'L','Y'),('0043',4,'L','Y'),('0044',4,'L','Y'),('0045',4,'L','Y'),('0046',4,'L','Y'),('0047',4,'L','Y'),('0048',4,'L','Y'),('0049',4,'L','Y'),('0050',4,'L','Y'),('0101',4,'O','Y'),('0102',4,'O','Y');
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardpreco`
--

DROP TABLE IF EXISTS `cardpreco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardpreco` (
  `idcardapio` int(11) NOT NULL,
  `preco` double NOT NULL,
  `idstatus` varchar(15) NOT NULL,
  `dtin` datetime NOT NULL,
  `dtfim` datetime DEFAULT NULL,
  PRIMARY KEY (`idcardapio`,`preco`,`idstatus`,`dtin`),
  KEY `idpreco` (`preco`),
  KEY `idstatus` (`idstatus`),
  KEY `fk_cardapio_ibflk_1` (`idcardapio`),
  CONSTRAINT `cardpreco_ibfk_3` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `fk_cardapio_ibflk_1` FOREIGN KEY (`idcardapio`) REFERENCES `cardapio` (`idcardapio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardpreco`
--

LOCK TABLES `cardpreco` WRITE;
/*!40000 ALTER TABLE `cardpreco` DISABLE KEYS */;
INSERT INTO `cardpreco` VALUES (0,15.5,'V','2015-01-20 13:13:16',NULL),(1,34.4,'V','2015-01-20 13:14:49',NULL),(2,34.4,'V','2015-01-20 13:19:44',NULL),(3,50,'V','2015-01-20 13:19:44',NULL),(4,52,'V','0000-00-00 00:00:00',NULL),(5,51.56,'V','2015-01-20 13:19:44','0000-00-00 00:00:00'),(6,59,'V','0000-00-00 00:00:00',NULL),(7,34.4,'V','2015-01-29 00:43:11',NULL),(8,34.4,'V','2015-01-29 00:44:21',NULL),(9,34.4,'V','2015-01-29 00:48:30',NULL),(10,34.4,'V','2015-01-29 00:50:37',NULL),(11,34.4,'V','2015-01-29 00:51:35',NULL),(12,34.4,'V','2015-01-29 00:59:58',NULL),(13,50,'V','2015-01-29 01:06:38',NULL),(14,50,'V','2015-01-29 20:57:30',NULL),(15,34.4,'V','2015-02-02 00:45:38',NULL),(16,34.4,'V','2015-02-02 01:02:36',NULL),(17,34.4,'V','2015-02-02 01:02:59',NULL),(18,50,'V','2015-02-02 17:17:26',NULL),(19,50,'V','2015-02-02 17:17:26',NULL),(20,50,'V','2015-02-02 17:17:26',NULL),(21,50,'V','2015-02-02 17:17:26',NULL),(22,50,'V','2015-02-02 17:17:26',NULL),(23,50,'V','2015-02-02 17:17:26',NULL),(24,50,'V','2015-02-02 17:17:26',NULL),(25,50,'V','2015-02-02 17:17:26',NULL),(26,50,'V','2015-02-02 17:17:26',NULL),(27,34.4,'V','2015-02-02 18:42:59',NULL);
/*!40000 ALTER TABLE `cardpreco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `preparosimples`
--

DROP TABLE IF EXISTS `preparosimples`;
/*!50001 DROP VIEW IF EXISTS `preparosimples`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `preparosimples` (
  `TIPO` tinyint NOT NULL,
  `DESCRICAO` tinyint NOT NULL,
  `PONTOCARNE` tinyint NOT NULL,
  `OBSERVACAO` tinyint NOT NULL,
  `MESA` tinyint NOT NULL,
  `CLIENTE` tinyint NOT NULL,
  `GARÇON` tinyint NOT NULL,
  `HORA` tinyint NOT NULL,
  `IDPEDIDO` tinyint NOT NULL,
  `IDCLIENTE` tinyint NOT NULL,
  `IDCARDAPIO` tinyint NOT NULL,
  `STATUS` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `itens_em_aberto`
--

DROP TABLE IF EXISTS `itens_em_aberto`;
/*!50001 DROP VIEW IF EXISTS `itens_em_aberto`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `itens_em_aberto` (
  `MESA` tinyint NOT NULL,
  `PEDIDO` tinyint NOT NULL,
  `CLIENTE` tinyint NOT NULL,
  `IDCLIENTE` tinyint NOT NULL,
  `QTY` tinyint NOT NULL,
  `ITEM` tinyint NOT NULL,
  `IDTEM` tinyint NOT NULL,
  `PRECO UNIDADE` tinyint NOT NULL,
  `PREÇO TOTAL` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` varchar(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(256) NOT NULL,
  `idperfil` int(11) NOT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_perfil1` (`idperfil`),
  CONSTRAINT `fk_usuario_perfil1` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`idperfil`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('aguado','Ale','775f614248676b781457b6d1f8d91eb5',60,'Y'),('antonio','Antonio','775f614248676b781457b6d1f8d91eb5',30,'Y'),('david','David','775f614248676b781457b6d1f8d91eb5',20,'Y'),('hugo','Hugo','775f614248676b781457b6d1f8d91eb5',50,'Y'),('jessica','Jessica','775f614248676b781457b6d1f8d91eb5',10,'Y'),('xavier','Xavier dos Santos','LaBoca',30,'Y'),('zecarlos','Xavier dos Santos','LaBoca',30,'Y');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissoes`
--

DROP TABLE IF EXISTS `permissoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissoes` (
  `idpermissao` varchar(10) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`idpermissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissoes`
--

LOCK TABLES `permissoes` WRITE;
/*!40000 ALTER TABLE `permissoes` DISABLE KEYS */;
INSERT INTO `permissoes` VALUES ('ADM_CARD','ADMINISTRACAO DE CARDAPIO'),('ADM_MES','ADMINISTRAÇÃO DE MESAS'),('ADM_PED','ADMINISTRACAO DE PEDIDOS'),('ADM_PREC','ADMINISTRAÇÃO DE PREÇOS'),('ADM_USR','ADMINISTRAÇÃO DE USUARIOS E PERMI.'),('CAIXA','FUNÇÕES DE CAIXA'),('MES','VISUALIZAÇÃO DE MESAS'),('PED_ADD','ADICIONAR ITEM A PEDIDO'),('PED_CRIAR','CRIAR PEDIDO'),('PED_EDIT','EDITAR ITEM DE PEDIDO'),('PED_ENCER','ENCERRAR PEDIDO'),('PED_EXCLU','EXCLUIR PEDIDO'),('PED_REM','REMOVER ITEM DE PEDIDO'),('PREP_BEB','PREPARAR BEBIDAS'),('PREP_EMP','PREPARAR EMPANADAS'),('PREP_PRAT','PREPARAR PRATOS');
/*!40000 ALTER TABLE `permissoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_ingre`
--

DROP TABLE IF EXISTS `cat_ingre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_ingre` (
  `idcat` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`idcat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_ingre`
--

LOCK TABLES `cat_ingre` WRITE;
/*!40000 ALTER TABLE `cat_ingre` DISABLE KEYS */;
INSERT INTO `cat_ingre` VALUES (0,'CARNES'),(1,'REFRIGERANTES'),(2,'VEGERAIS');
/*!40000 ALTER TABLE `cat_ingre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idpedido` varchar(16) NOT NULL,
  `horain` datetime NOT NULL,
  `horaout` datetime DEFAULT NULL,
  `idmesa` varchar(4) NOT NULL,
  `idusuario` varchar(10) NOT NULL,
  `idstatus` varchar(15) NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `idusuario` (`idusuario`),
  KEY `idstatus` (`idstatus`),
  KEY `pedido_ibfk_1` (`idmesa`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`idmesa`) REFERENCES `mesa` (`idmesa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES ('0102150116350013','2015-02-01 01:16:35',NULL,'0013','aguado','A'),('0202150014060021','2015-02-02 00:14:06','2015-02-02 00:43:57','0021','aguado','E'),('0202151701290020','2015-02-02 17:01:29',NULL,'0020','aguado','C'),('0202151723400050','2015-02-02 17:23:40','2015-02-02 17:24:58','0050','aguado','E'),('0202151840190006','2015-02-02 18:40:19',NULL,'0006','aguado','A'),('0202151858360014','2015-02-02 18:58:36','2015-02-02 19:00:17','0014','aguado','E'),('15011921580102','2015-01-19 21:58:19',NULL,'0102','AGUADO','C'),('1901152203010001','2015-01-19 22:03:01','0000-00-00 00:00:00','0001','AGUADO','A'),('2701150031170016','2015-01-27 00:31:17',NULL,'0016','aguado','C'),('2701150032220017','2015-01-27 00:32:22',NULL,'0017','aguado','C'),('2701150034020018','2015-01-27 00:34:02',NULL,'0018','aguado','C'),('2701150205340030','2015-01-27 02:05:34',NULL,'0030','aguado','A'),('2701151506210024','2015-01-27 15:06:21',NULL,'0024','aguado','C'),('2801150305130005','2015-01-28 03:05:13',NULL,'0005','aguado','A'),('2901150005110011','2015-01-29 00:05:11',NULL,'0011','aguado','A'),('2901150007210008','2015-01-29 00:07:21',NULL,'0008','aguado','C'),('2901150042300010','2015-01-29 00:42:30',NULL,'0010','aguado','C'),('2901150105430022','2015-01-29 01:05:43','2015-01-31 11:17:43','0022','aguado','E'),('2901150113530012','2015-01-29 01:13:53',NULL,'0012','aguado','A'),('2901150123020013','2015-01-29 01:23:02','2015-01-31 12:05:26','0013','aguado','E'),('2901152055590007','2015-01-29 20:55:59',NULL,'0007','aguado','A');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `mesas_status`
--

DROP TABLE IF EXISTS `mesas_status`;
/*!50001 DROP VIEW IF EXISTS `mesas_status`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `mesas_status` (
  `IDMESA` tinyint NOT NULL,
  `CAPACIDADE` tinyint NOT NULL,
  `STATUS` tinyint NOT NULL,
  `PEDIDO` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `idfornecedor` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `idusuario` varchar(10) NOT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`idfornecedor`),
  KEY `fk_fornecedor_usuario1` (`idusuario`),
  CONSTRAINT `fk_fornecedor_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (0,'Jose Carlos M.E','309745678-33','Rua Itapemirim, 10','Americana','SP','Brasil','19-98154-9116','AGUADO','Y'),(1,'Maria Ap. Mion LTDA.','309745638-33','Rua Itapemirim, 10','Americana','SP','Brasil','19-98154-9116','AGUADO','Y');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingrediente` (
  `idingre` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `idcat` int(11) NOT NULL,
  `idusuario` varchar(10) NOT NULL,
  `ativo` varchar(45) NOT NULL,
  PRIMARY KEY (`idingre`),
  KEY `fk_ingrediente_cat_ingre` (`idcat`),
  KEY `fk_ingrediente_usuario1` (`idusuario`),
  CONSTRAINT `fk_ingrediente_cat_ingre` FOREIGN KEY (`idcat`) REFERENCES `cat_ingre` (`idcat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingrediente_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` VALUES (0,'ALFACE CRESPA',9,2,'AGUADO','Y'),(1,'RUCULA',-13,2,'AGUADO','Y'),(2,'TOMATES',-6,2,'AGUADO','Y'),(3,'PICANHA ARGENTINA',-30,0,'AGUADO','Y'),(4,'COCA-COLA LATA',100,1,'AGUADO','Y');
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `idperfil` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`idperfil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (10,'GARÇOM'),(20,'PREPARADOR DE PRATOS'),(30,'PREPARADOR DE EMPANADAS'),(40,'PREPARADOR DE BEBIDAS'),(50,'CAIXA'),(60,'ADMINISTRADOR');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfilpermi`
--

DROP TABLE IF EXISTS `perfilpermi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfilpermi` (
  `idperfil` int(11) NOT NULL,
  `idpermissao` varchar(10) NOT NULL,
  PRIMARY KEY (`idperfil`,`idpermissao`),
  KEY `fk_perfil_has_permissoes_permissoes1` (`idpermissao`),
  KEY `fk_perfil_has_permissoes_perfil1` (`idperfil`),
  CONSTRAINT `fk_perfil_has_permissoes_perfil1` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`idperfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_has_permissoes_permissoes1` FOREIGN KEY (`idpermissao`) REFERENCES `permissoes` (`idpermissao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfilpermi`
--

LOCK TABLES `perfilpermi` WRITE;
/*!40000 ALTER TABLE `perfilpermi` DISABLE KEYS */;
INSERT INTO `perfilpermi` VALUES (10,'PED_ADD'),(10,'PED_CRIAR'),(10,'PED_EDIT'),(10,'PED_ENCER'),(10,'PED_REM'),(20,'PREP_PRAT'),(30,'PREP_EMP'),(40,'PREP_BEB'),(50,'ADM_PREC'),(50,'CAIXA'),(50,'PED_ADD'),(50,'PED_CRIAR'),(50,'PED_EDIT'),(50,'PED_ENCER'),(50,'PED_EXCLU'),(50,'PED_REM'),(60,'ADM_CARD'),(60,'ADM_MES'),(60,'ADM_PED'),(60,'ADM_PREC'),(60,'ADM_USR'),(60,'CAIXA'),(60,'PED_ADD'),(60,'PED_CRIAR'),(60,'PED_EDIT'),(60,'PED_ENCER'),(60,'PED_EXCLU'),(60,'PED_REM'),(60,'PREP_BEB'),(60,'PREP_EMP'),(60,'PREP_PRAT');
/*!40000 ALTER TABLE `perfilpermi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `idpedido` varchar(16) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `RG` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`idcliente`,`idpedido`),
  KEY `cliente_ibfk_2` (`idpedido`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`),
  CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (0,'0102150116350013','PADRAO',NULL,NULL),(0,'0202150014060021','PADRAO',NULL,NULL),(0,'0202151701290020','PADRAO',NULL,NULL),(0,'0202151723400050','PADRAO',NULL,NULL),(0,'0202151840190006','PADRAO',NULL,NULL),(0,'0202151858360014','PADRAO',NULL,NULL),(0,'15011921580102','PADRAO',NULL,NULL),(0,'1901152203010001','PADRAO',NULL,NULL),(0,'2701150031170016','PADRAO',NULL,NULL),(0,'2701150032220017','PADRAO',NULL,NULL),(0,'2701150034020018','PADRAO',NULL,NULL),(0,'2701150205340030','PADRAO',NULL,NULL),(0,'2701151506210024','PADRAO',NULL,NULL),(0,'2801150305130005','PADRAO',NULL,NULL),(0,'2901150005110011','PADRAO',NULL,NULL),(0,'2901150007210008','PADRAO',NULL,NULL),(0,'2901150042300010','PADRAO',NULL,NULL),(0,'2901150105430022','PADRAO',NULL,NULL),(0,'2901150113530012','PADRAO',NULL,NULL),(0,'2901150123020013','PADRAO',NULL,NULL),(0,'2901152055590007','PADRAO',NULL,NULL),(1,'0102150116350013','Ale',NULL,NULL),(1,'0202150014060021','Jose',NULL,NULL),(1,'0202151701290020','Hugo',NULL,NULL),(1,'0202151840190006','NOE',NULL,NULL),(1,'0202151858360014','Hugo',NULL,NULL),(1,'1901152203010001','CARLOS',NULL,NULL),(1,'2901150005110011','SILVANA',NULL,NULL),(1,'2901150105430022','GABRIELA',NULL,NULL),(1,'2901150113530012','Alexandre',NULL,NULL),(1,'2901150123020013','Ale',NULL,NULL),(1,'2901152055590007','GABRIELA',NULL,NULL),(2,'0102150116350013','Gabi',NULL,NULL),(2,'0202150014060021','Maria',NULL,NULL),(2,'0202151701290020','Jose',NULL,NULL),(2,'0202151840190006','LUCA',NULL,NULL),(2,'0202151858360014','Vanessa',NULL,NULL),(2,'1901152203010001','ROGERIO',NULL,NULL),(2,'2901150105430022','ALEXANDRE',NULL,NULL),(2,'2901150113530012','Gabriela',NULL,NULL),(2,'2901150123020013','Gabi',NULL,NULL),(2,'2901152055590007','ALEXANDRE',NULL,NULL),(3,'1901152203010001','ANDRESSA',NULL,NULL),(4,'1901152203010001','Alexandre',NULL,NULL),(5,'1901152203010001','GABRIELA',NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `preparosimples`
--

/*!50001 DROP TABLE IF EXISTS `preparosimples`*/;
/*!50001 DROP VIEW IF EXISTS `preparosimples`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `preparosimples` AS select `cc`.`tipo` AS `TIPO`,`c`.`descricao` AS `DESCRICAO`,`c`.`pontoCarne` AS `PONTOCARNE`,`ped`.`observacao` AS `OBSERVACAO`,`p`.`idmesa` AS `MESA`,`cli`.`nome` AS `CLIENTE`,`ped`.`idcriador` AS `GARÇON`,`ped`.`horacria` AS `HORA`,`p`.`idpedido` AS `IDPEDIDO`,`cli`.`idcliente` AS `IDCLIENTE`,`c`.`idcardapio` AS `IDCARDAPIO`,`ped`.`idstatus` AS `STATUS` from ((((`pedicar` `ped` join `pedido` `p`) join `cardapio` `c`) join `cat_car` `cc`) join `cliente` `cli`) where ((`ped`.`idpedido` = `p`.`idpedido`) and (`ped`.`idcardapio` = `c`.`idcardapio`) and (`c`.`idcatcar` = `cc`.`idcatcar`) and (`ped`.`idcliente` = `cli`.`idcliente`) and (`ped`.`idpedido` = `cli`.`idpedido`) and (`ped`.`idstatus` = 'C')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `itens_em_aberto`
--

/*!50001 DROP TABLE IF EXISTS `itens_em_aberto`*/;
/*!50001 DROP VIEW IF EXISTS `itens_em_aberto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `itens_em_aberto` AS select `ped`.`idmesa` AS `MESA`,`ped`.`idpedido` AS `PEDIDO`,`cli`.`nome` AS `CLIENTE`,`cli`.`idcliente` AS `IDCLIENTE`,count(`car`.`descricao`) AS `QTY`,`car`.`descricao` AS `ITEM`,`car`.`idcardapio` AS `IDTEM`,`pre`.`preco` AS `PRECO UNIDADE`,sum(`pre`.`preco`) AS `PREÇO TOTAL` from ((((`pedicar` `pc` join `pedido` `ped`) join `cliente` `cli`) join `cardapio` `car`) join `cardpreco` `pre`) where ((`pc`.`idpedido` = `ped`.`idpedido`) and (`cli`.`idcliente` = `pc`.`idcliente`) and (`cli`.`idpedido` = `pc`.`idpedido`) and (`car`.`idcardapio` = `pc`.`idcardapio`) and (`pre`.`idcardapio` = `car`.`idcardapio`) and (`pre`.`idstatus` = 'V') and (`pc`.`pago` = 'F')) group by `ped`.`idmesa`,`ped`.`idpedido`,`cli`.`nome`,`car`.`descricao` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mesas_status`
--

/*!50001 DROP TABLE IF EXISTS `mesas_status`*/;
/*!50001 DROP VIEW IF EXISTS `mesas_status`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mesas_status` AS select `m`.`idmesa` AS `IDMESA`,`m`.`capacidade` AS `CAPACIDADE`,`m`.`idstatus` AS `STATUS`,`p`.`idpedido` AS `PEDIDO` from (`mesa` `m` left join `pedido` `p` on((`m`.`idmesa` = `p`.`idmesa`))) where ((`m`.`ativo` = 'Y') and ((`p`.`idstatus` = 'C') or (`p`.`idstatus` = 'A'))) order by field(`m`.`idstatus`,'L') desc,`m`.`idmesa` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-20  9:55:15
