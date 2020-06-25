INSERT INTO fitness.user (login, password, name, surname, birthdate, role)
VALUES
('coleman', '123', 'Alex', 'Hunter', '2001-09-10', 'admin'),
('yates', '1020', 'Roy', 'Beasley', '1976-11-12', 'admin'),
('cutler', '1357', 'Denis', 'Ivanov', '1992-03-03', 'admin'),
('frank', '112233', 'Pavel', 'Popov', '1998-09-10', 'client'),
('john', '506070', 'Carlos', 'Marcos', '1988-01-01', 'client'),
('harry', '5432', 'Viktor', 'Petrov', '1995-03-11', 'client'),
('david', '9091', 'Mark', 'Mayer', '1984-05-07', 'client'),
('walter', '454545', 'Ryan', 'Walter', '2002-11-11', 'trainer'),
('steve', '890890', 'Steven', 'Ford', '1990-09-08', 'trainer'),
('ole', '121212', 'Ole', 'Kjaer', '1994-01-08', 'client');

/*INSERT INTO fitness.payment (duration, amount)
VALUES
(30, 200),
(30, 200),
(30, 200),
(30, 200),
(30, 200),
(30, 200),
(30, 200);*/

INSERT INTO fitness.program (name, client_id, trainer_id, calories, duration, cost)
 VALUES
('Super Mass Gain', 4, 8, 3000, 30, 400),
('Strong Muscles', 5, 9, 2000, 45, 600),
('Fat Burning', 6, 8, 2800, 60, 800),
('Endurance increasing', 7, 9, 3200, 30, 400),
('Muscle Gain', 4, 8, 3500, 45, 600),
('Super Fat Burning', 5, 9, 1800, 45, 600),
('Basic Training For Beginners', 6, 8, 2200, 60, 800),
('Advanced Muscle Gain', 10, 8, 4200, 30, 400);

INSERT INTO fitness.exercise (program_id, description, approaches, repetitions, day)
 VALUES
(1, 'pull ups', 3, 8, 1),
(2, 'push ups', 4, 12, 2),
(3, 'barbell squats', 4, 20, 3),
(4, 'barbell bench press', 4, 15, 4),
(5, 'sit ups', 6, 40, 5),
(6, 'pull ups with weight', 3, 8, 1),
(7, 'push ups with weight', 4, 12, 2),
(8, 'push ups with weight', 4, 12, 2),
(1, 'push ups', 4, 12, 2);

