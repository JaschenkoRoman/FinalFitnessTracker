
CREATE TABLE user
(
  id       INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login    VARCHAR(20) NOT NULL UNIQUE,
  password CHAR(64)    NOT NULL,
  name     varchar(30) NOT NULL,
  surname  varchar(30) NOT NULL,
  birthdate date       NOT NULL,
  role     ENUM ('ADMIN','TRAINER', 'CLIENT') NOT NULL
);

/*CREATE TABLE payment
(
  id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
*//*  client_id INTEGER NOT NULL,*//*
  payment_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  duration SMALLINT NOT NULL DEFAULT 30,
  amount DECIMAL NOT NULL DEFAULT 200.0
*//*  CONSTRAINT payment_client_id_foreign_key FOREIGN KEY (client_id) REFERENCES user(id) ON DELETE CASCADE*//*
);*/

CREATE TABLE program
(
  id          INTEGER    NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        TEXT(100)  NOT NULL,
  client_id   INTEGER    NOT NULL,
  trainer_id  INTEGER    NOT NULL,
  calories    SMALLINT(5),
  duration    SMALLINT   NOT NULL,
  cost        DECIMAL    NOT NULL,
--  actual      TINYINT(1) DEFAULT 0,//creation_time
  creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  payment_time  DATETIME,
  CONSTRAINT program_client_id_foreign_key FOREIGN KEY (client_id) REFERENCES user(id) ON DELETE CASCADE,
  CONSTRAINT program_trainer_id_foreign_key FOREIGN KEY (trainer_id) REFERENCES user(id) ON DELETE CASCADE/*,
  CONSTRAINT program_payment_id_foreign_key FOREIGN KEY (payment_id) REFERENCES payment(id) ON DELETE CASCADE,
  CONSTRAINT unique_program UNIQUE (payment_id )*/
);

CREATE TABLE exercise
(
  id            INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  program_id    INTEGER     NOT NULL,
  description   TEXT(300)   NOT NULL,
  approaches    SMALLINT(2) NOT NULL,
  repetitions   SMALLINT(3) NOT NULL,
  day           SMALLINT(2) NOT NULL,
  CONSTRAINT training_program_id_foreign_key FOREIGN KEY (program_id) REFERENCES program(id) ON DELETE CASCADE
);

/*
CREATE TABLE training
(
  program_id            INTEGER NOT NULL PRIMARY KEY,
  exercise_description  TEXT(300)    NOT NULL,
  day                   SMALLINT(2) NOT NULL,
  approaches             SMALLINT(2) NOT NULL,
  repetitions            SMALLINT(3) NOT NULL,
  CONSTRAINT training_program_id_foreign_key FOREIGN KEY (program_id) REFERENCES program(id) ON DELETE CASCADE
);*/
