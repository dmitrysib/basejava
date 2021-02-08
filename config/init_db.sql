/* ---

## POSTGRESQL

DROP TABLE IF EXISTS resume;
DROP TABLE IF EXISTS contact;

CREATE TABLE resume (
    uuid VARCHAR(36) NOT NULL,
    full_name VARCHAR(256) NOT NULL,
    PRIMARY KEY(uuid)
);

CREATE TABLE contact (
   id SERIAL,
   type VARCHAR(256) NOT NULL,
   value VARCHAR(256) NOT NULL,
   resume_uuid VARCHAR(36) NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_resume_uuid
      FOREIGN KEY(resume_uuid)
	  REFERENCES resume(uuid)
	  ON DELETE CASCADE
);


## MYSQL

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(256) NOT NULL,
  `value` varchar(256) NOT NULL,
  `resume_uuid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tpe` (`type`),
  KEY `resume_uuid` (`resume_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `resume`;
CREATE TABLE IF NOT EXISTS `resume` (
  `uuid` varchar(36) NOT NULL,
  `full_name` varchar(256) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `contact`
  ADD CONSTRAINT `fk_resume_uuid` FOREIGN KEY (`resume_uuid`) REFERENCES `resume` (`uuid`) ON DELETE CASCADE;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

--- */