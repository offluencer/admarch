--- create database
create database admarch;

CREATE TABLE `Actions` (
  `actionId` varchar(12) NOT NULL DEFAULT '',
  `registerNumber` varchar(255) DEFAULT NULL,
  `actionDateTime` datetime DEFAULT NULL,
  `deviceId` varchar(255) DEFAULT NULL,
  `actionEarning` int DEFAULT NULL,
  PRIMARY KEY (`actionId`),
  KEY `registerNumber` (`registerNumber`),
  CONSTRAINT `actions_ibfk_1` FOREIGN KEY (`registerNumber`) REFERENCES `Influencer` (`regNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Campaign` (
  `campaignId` varchar(255) NOT NULL DEFAULT '',
  `regNumber` varchar(255) NOT NULL DEFAULT '',
  `tinyUrl` varchar(255) DEFAULT NULL,
  `urlId` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`campaignId`,`urlId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Influencer` (
  `regNumber` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `education` text,
  `monthlyIncome` int DEFAULT NULL,
  `monthlyExpense` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  `dailyIncome` int DEFAULT NULL,
  `dailyExpense` int DEFAULT NULL,
  `noOfDependents` int DEFAULT NULL,
  `loanAmount` int DEFAULT NULL,
  `registerDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`regNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Rides` (
  `rideId` varchar(255) NOT NULL DEFAULT '',
  `rideSource` varchar(255) DEFAULT NULL,
  `rideDestination` varchar(255) DEFAULT NULL,
  `rideFare` int DEFAULT NULL,
  `rideDuration` int DEFAULT NULL,
  `regNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `rideStartTime` timestamp NULL DEFAULT NULL,
  `rideEndTime` timestamp NULL DEFAULT NULL,
  `isActive` int DEFAULT NULL,
  PRIMARY KEY (`rideId`),
  KEY `regNumber` (`regNumber`),
  CONSTRAINT `rides_ibfk_1` FOREIGN KEY (`regNumber`) REFERENCES `Influencer` (`regNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Viewer` (
  `viewerId` varchar(255) NOT NULL DEFAULT '',
  `rideId` varchar(255) NOT NULL DEFAULT '',
  `ageRange` varchar(255) DEFAULT NULL,
  `avgAge` int DEFAULT NULL,
  `gender` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`viewerId`),
  KEY `rideId` (`rideId`),
  CONSTRAINT `viewer_ibfk_1` FOREIGN KEY (`rideId`) REFERENCES `Rides` (`rideId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
