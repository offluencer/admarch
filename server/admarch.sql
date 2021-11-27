--- create database
create database admarch;

CREATE TABLE `Actions` (
  `actionId` int NOT NULL AUTO_INCREMENT,
  `registerNumber` varchar(255) DEFAULT NULL,
  `actionDateTime` datetime DEFAULT NULL,
  `deviceId` varchar(255) DEFAULT NULL,
  `actionEarning` int DEFAULT NULL,
  `campaignId` int NOT NULL,
  PRIMARY KEY (`actionId`),
  KEY `registerNumber` (`registerNumber`),
  KEY `dev_` (`registerNumber`),
  CONSTRAINT `actions_ibfk_1` FOREIGN KEY (`registerNumber`) REFERENCES `Influencer` (`regNumber`)
);

CREATE TABLE `Campaign` (
  `campaignId` int NOT NULL AUTO_INCREMENT,
  `tinyUrl` varchar(255) NOT NULL,
  `actualUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`campaignId`)
);

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
);

CREATE TABLE `Rides` (
  `rideId` varchar(255) NOT NULL DEFAULT '',
  `rideSource` varchar(255) DEFAULT NULL,
  `rideDestination` varchar(255) DEFAULT NULL,
  `rideFare` int DEFAULT NULL,
  `rideDuration` int DEFAULT NULL,
  `regNumber` varchar(255) NOT NULL DEFAULT '',
  `rideStartTime` timestamp NULL DEFAULT NULL,
  `rideEndTime` timestamp NULL DEFAULT NULL,
  `isActive` int DEFAULT NULL,
  PRIMARY KEY (`rideId`),
  KEY `regNumber` (`regNumber`),
  CONSTRAINT `rides_ibfk_1` FOREIGN KEY (`regNumber`) REFERENCES `Influencer` (`regNumber`)
);

CREATE TABLE `Viewer` (
  `viewerId` varchar(255) NOT NULL DEFAULT '',
  `rideId` varchar(255) NOT NULL DEFAULT '',
  `ageRange` varchar(255) DEFAULT NULL,
  `avgAge` int DEFAULT NULL,
  `gender` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`viewerId`),
  KEY `rideId` (`rideId`),
  CONSTRAINT `viewer_ibfk_1` FOREIGN KEY (`rideId`) REFERENCES `Rides` (`rideId`)
);

CREATE TABLE `QRCodeInfo` (
  `id` varchar(20) NOT NULL,
  `infuencerRegNo` varchar(255) NOT NULL,
  `campaignId` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `QRCodeInfo_influencer_regNo` FOREIGN KEY (`infuencerRegNo`) REFERENCES `Influencer` (`regNumber`),
  CONSTRAINT `QRCodeInfo_influencer_campaignIf` FOREIGN KEY (`campaignId`) REFERENCES `Campaign` (`campaignId`)
);

CREATE TABLE `UserInfo` (
  `userId` varchar(255) NOT NULL,
  `ipAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

insert into Influencer (regNumber) values ("8123825521");

insert into Campaign (campaignId,tinyUrl,actualUrl) values (1,"https://in.search.yahoo.com/?fr2=inr","https://in.search.yahoo.com/?fr2=inr")
insert into QRCodeInfo values (1,"8123825521",1);
