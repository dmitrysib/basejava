/* ---
DROP TABLE IF EXISTS resume;
DROP TABLE IF EXISTS contact;

CREATE TABLE resume (
    uuid VARCHAR(36) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(uuid)
);

CREATE TABLE contact (
   id SERIAL,
   type VARCHAR(255) NOT NULL,
   value VARCHAR(255) NOT NULL,
   resume_uuid VARCHAR(36) NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_resume_uuid
      FOREIGN KEY(resume_uuid)
	  REFERENCES resume(uuid)
	  ON DELETE CASCADE
);
--- */