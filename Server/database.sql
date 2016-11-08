CREATE DATABASE test;

use test;

CREATE TABLE `student` (
  `snumber` int(10) NOT NULL ,
  `sname` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `sclass` varchar(8) NOT NULL,
  PRIMARY KEY (`snumber`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8