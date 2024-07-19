CREATE DATABASE  IF NOT EXISTS `study_cow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `study_cow`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: study_cow
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_attend`
--

DROP TABLE IF EXISTS `t_attend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attend` (
  `USER_ID` int NOT NULL,
  `ROOM_ID` bigint NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `IDX_ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `t_attend_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_attend_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `t_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attend`
--

LOCK TABLES `t_attend` WRITE;
/*!40000 ALTER TABLE `t_attend` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_attend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_bgm`
--

DROP TABLE IF EXISTS `t_bgm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_bgm` (
  `BGM_INDEX` int NOT NULL,
  `ROOM_ID` bigint NOT NULL,
  `BGM_LINK` varchar(500) NOT NULL,
  `BGM_IN_DATE` timestamp NOT NULL,
  `BGM_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`,`BGM_INDEX`),
  CONSTRAINT `t_bgm_ibfk_1` FOREIGN KEY (`ROOM_ID`) REFERENCES `t_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bgm`
--

LOCK TABLES `t_bgm` WRITE;
/*!40000 ALTER TABLE `t_bgm` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_bgm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category` (
  `CAT_CODE` tinyint NOT NULL AUTO_INCREMENT,
  `SUB_CODE` tinyint NOT NULL,
  `CAT_NAME` varchar(30) DEFAULT NULL,
  `CAT_STATUS` tinyint NOT NULL DEFAULT '0',
  `CAT_IN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CAT_CODE`),
  KEY `SUB_CODE` (`SUB_CODE`),
  CONSTRAINT `t_category_ibfk_1` FOREIGN KEY (`SUB_CODE`) REFERENCES `t_subject_code` (`SUB_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_chat_log`
--

DROP TABLE IF EXISTS `t_chat_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_chat_log` (
  `CHAT_ID` bigint NOT NULL AUTO_INCREMENT,
  `ROOM_ID` bigint NOT NULL,
  `USER_ID` int NOT NULL,
  `CHAT_CONTENT` varchar(200) NOT NULL,
  `CHAT_IN_DATE` timestamp NOT NULL,
  PRIMARY KEY (`CHAT_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `IDX_ROOM_ID` (`ROOM_ID`),
  KEY `IDX_CHAT_IN_DATE` (`CHAT_IN_DATE`),
  CONSTRAINT `t_chat_log_ibfk_1` FOREIGN KEY (`ROOM_ID`) REFERENCES `t_room` (`ROOM_ID`),
  CONSTRAINT `t_chat_log_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_chat_log`
--

LOCK TABLES `t_chat_log` WRITE;
/*!40000 ALTER TABLE `t_chat_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_chat_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_exp_log`
--

DROP TABLE IF EXISTS `t_exp_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_exp_log` (
  `EXP_ID` bigint NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `GET_AMOUNT` int NOT NULL,
  `GET_DATE` timestamp NOT NULL,
  PRIMARY KEY (`EXP_ID`),
  KEY `IDX_USER_ID` (`USER_ID`),
  CONSTRAINT `t_exp_log_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exp_log`
--

LOCK TABLES `t_exp_log` WRITE;
/*!40000 ALTER TABLE `t_exp_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_exp_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_freind`
--

DROP TABLE IF EXISTS `t_freind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_freind` (
  `USER_ID1` int NOT NULL,
  `USER_ID2` int NOT NULL,
  `FREIND_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID1`,`USER_ID2`),
  KEY `USER_ID2` (`USER_ID2`),
  CONSTRAINT `t_freind_ibfk_1` FOREIGN KEY (`USER_ID1`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_freind_ibfk_2` FOREIGN KEY (`USER_ID2`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_freind`
--

LOCK TABLES `t_freind` WRITE;
/*!40000 ALTER TABLE `t_freind` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_freind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_freind_request`
--

DROP TABLE IF EXISTS `t_freind_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_freind_request` (
  `FREIND_REQUEST_ID` bigint NOT NULL AUTO_INCREMENT,
  `FROM_USER_ID` int NOT NULL,
  `TO_USER_ID` int NOT NULL,
  `REQUEST_STATUS` tinyint NOT NULL DEFAULT '0',
  `REQUEST_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REQUEST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FREIND_REQUEST_ID`),
  KEY `FROM_USER_ID` (`FROM_USER_ID`),
  KEY `TO_USER_ID` (`TO_USER_ID`),
  CONSTRAINT `t_freind_request_ibfk_1` FOREIGN KEY (`FROM_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_freind_request_ibfk_2` FOREIGN KEY (`TO_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_freind_request`
--

LOCK TABLES `t_freind_request` WRITE;
/*!40000 ALTER TABLE `t_freind_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_freind_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_grade_code`
--

DROP TABLE IF EXISTS `t_grade_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_grade_code` (
  `GRADE_CODE` tinyint NOT NULL AUTO_INCREMENT,
  `GRADE_NAME` varchar(10) NOT NULL,
  `MIN_EXP` int NOT NULL,
  `MAX_EXP` int NOT NULL,
  PRIMARY KEY (`GRADE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_grade_code`
--

LOCK TABLES `t_grade_code` WRITE;
/*!40000 ALTER TABLE `t_grade_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_grade_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_in_log`
--

DROP TABLE IF EXISTS `t_in_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_in_log` (
  `IN_LOG_ID` bigint NOT NULL AUTO_INCREMENT,
  `ROOM_ID` bigint NOT NULL,
  `USER_ID` int NOT NULL,
  `STUDY_TIME` int NOT NULL DEFAULT '0',
  `STUDY_DATE` date NOT NULL,
  `IN_DATE` timestamp NOT NULL,
  `OUT_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`IN_LOG_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `t_in_log_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_in_log_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `t_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_in_log`
--

LOCK TABLES `t_in_log` WRITE;
/*!40000 ALTER TABLE `t_in_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_in_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_plan`
--

DROP TABLE IF EXISTS `t_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_plan` (
  `PLAN_ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `SUB_CODE` tinyint NOT NULL,
  `PLAN_DATE` date NOT NULL,
  `PLAN_CONTENT` text,
  `PLAN_STUDY_TIME` int NOT NULL DEFAULT '0',
  `PLAN_IN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PLAN_STATUS` tinyint NOT NULL DEFAULT '0',
  `PLAN_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PLAN_SUM_TIME` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`PLAN_ID`),
  KEY `IDX_USER_ID` (`USER_ID`),
  KEY `IDX_PLAN_DATE` (`PLAN_DATE`),
  KEY `IDX_SUB_CODE` (`SUB_CODE`),
  CONSTRAINT `t_plan_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_plan_ibfk_2` FOREIGN KEY (`SUB_CODE`) REFERENCES `t_subject_code` (`SUB_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_plan`
--

LOCK TABLES `t_plan` WRITE;
/*!40000 ALTER TABLE `t_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_proc_room_study`
--

DROP TABLE IF EXISTS `t_proc_room_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_proc_room_study` (
  `PROC_ROOM_ID` bigint NOT NULL AUTO_INCREMENT,
  `ROOM_ID` bigint NOT NULL,
  `PROC_DATE` date NOT NULL,
  `SUM_ROOM_TIME` int NOT NULL,
  PRIMARY KEY (`PROC_ROOM_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  KEY `IDX_PROC_DATE` (`PROC_DATE`),
  CONSTRAINT `t_proc_room_study_ibfk_1` FOREIGN KEY (`ROOM_ID`) REFERENCES `t_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_proc_room_study`
--

LOCK TABLES `t_proc_room_study` WRITE;
/*!40000 ALTER TABLE `t_proc_room_study` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_proc_room_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_proc_user_study`
--

DROP TABLE IF EXISTS `t_proc_user_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_proc_user_study` (
  `PROC_USER_ID` bigint NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `PROC_DATE` date NOT NULL,
  `SUM_STUDY_TIME` int NOT NULL,
  PRIMARY KEY (`PROC_USER_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `IDX_PROC_DATE` (`PROC_DATE`),
  CONSTRAINT `t_proc_user_study_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_proc_user_study`
--

LOCK TABLES `t_proc_user_study` WRITE;
/*!40000 ALTER TABLE `t_proc_user_study` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_proc_user_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_room`
--

DROP TABLE IF EXISTS `t_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_room` (
  `ROOM_ID` bigint NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `ROOM_TITLE` varchar(50) NOT NULL,
  `ROOM_MAX_PERSON` tinyint NOT NULL,
  `ROOM_NOW_PERSON` tinyint NOT NULL,
  `ROOM_CREATE_DATE` date NOT NULL,
  `ROOM_END_DATE` date NOT NULL,
  `ROOM_STATUS` tinyint NOT NULL DEFAULT '0',
  `ROOM_UPDATE_DATE` timestamp NOT NULL,
  `ROOM_CONTENT` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `IDX_ROOM_TITLE` (`ROOM_TITLE`),
  KEY `IDX_ROOM_STATUS` (`ROOM_STATUS`),
  CONSTRAINT `t_room_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_room`
--

LOCK TABLES `t_room` WRITE;
/*!40000 ALTER TABLE `t_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_score`
--

DROP TABLE IF EXISTS `t_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_score` (
  `SCORE_ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `SUB_CODE` tinyint NOT NULL,
  `TEST_DATE` date NOT NULL,
  `TEST_SCORE` tinyint NOT NULL DEFAULT '0',
  `TEST_GRADE` tinyint DEFAULT NULL,
  `SCORE_UPDATE_DATE` timestamp NOT NULL,
  PRIMARY KEY (`SCORE_ID`),
  KEY `IDX_USER_ID` (`USER_ID`),
  KEY `IDX_TEST_DATE` (`TEST_DATE`),
  KEY `IDX_SUB_CODE` (`SUB_CODE`),
  CONSTRAINT `t_score_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `t_score_ibfk_2` FOREIGN KEY (`SUB_CODE`) REFERENCES `t_subject_code` (`SUB_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_score`
--

LOCK TABLES `t_score` WRITE;
/*!40000 ALTER TABLE `t_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_subject_code`
--

DROP TABLE IF EXISTS `t_subject_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_subject_code` (
  `SUB_CODE` tinyint NOT NULL AUTO_INCREMENT,
  `SUB_NAME` varchar(20) DEFAULT NULL,
  `SUB_MAX_SCORE` int NOT NULL,
  `SUB_STATUS` tinyint NOT NULL,
  `SUB_IN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SUB_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_subject_code`
--

LOCK TABLES `t_subject_code` WRITE;
/*!40000 ALTER TABLE `t_subject_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_subject_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `USER_EMAIL` varchar(30) NOT NULL,
  `USER_PASSWORD` varchar(200) NOT NULL,
  `USER_PUBLIC` tinyint NOT NULL DEFAULT '0',
  `USER_THUMB` varchar(100) DEFAULT NULL,
  `GRADE_CODE` tinyint NOT NULL DEFAULT '0',
  `USER_EXP` int NOT NULL DEFAULT '0',
  `USER_JOIN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_NICKNAME` varchar(20) NOT NULL,
  `USER_BIRTHDAY` date NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `IDX_USER_EMAIL` (`USER_EMAIL`),
  KEY `GRADE_CODE` (`GRADE_CODE`),
  KEY `IDX_USER_NICKNAME` (`USER_NICKNAME`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`GRADE_CODE`) REFERENCES `t_grade_code` (`GRADE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_token`
--

DROP TABLE IF EXISTS `t_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_token` (
  `USER_ID` int NOT NULL,
  `REFRESH_TOKEN` varchar(1000) DEFAULT NULL,
  `END_DATE` timestamp NULL DEFAULT NULL,
  `IN_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `t_user_token_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_token`
--

LOCK TABLES `t_user_token` WRITE;
/*!40000 ALTER TABLE `t_user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wrong_detail`
--

DROP TABLE IF EXISTS `t_wrong_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_wrong_detail` (
  `WRONG_DETAIL_ID` int NOT NULL AUTO_INCREMENT,
  `SCORE_ID` int NOT NULL,
  `CAT_CODE` tinyint NOT NULL,
  `WRONG_CNT` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`WRONG_DETAIL_ID`),
  KEY `CAT_CODE` (`CAT_CODE`),
  KEY `IDX_SCORE_ID` (`SCORE_ID`),
  CONSTRAINT `t_wrong_detail_ibfk_1` FOREIGN KEY (`SCORE_ID`) REFERENCES `t_score` (`SCORE_ID`),
  CONSTRAINT `t_wrong_detail_ibfk_2` FOREIGN KEY (`CAT_CODE`) REFERENCES `t_category` (`CAT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wrong_detail`
--

LOCK TABLES `t_wrong_detail` WRITE;
/*!40000 ALTER TABLE `t_wrong_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_wrong_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-19 11:56:51
