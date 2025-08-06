-- Inserts na tabela students
INSERT INTO students (name, email, created_at, updated_at)
VALUES ('João da Silva', 'joao.silva@email.com', CURRENT_DATE(), CURRENT_DATE());

INSERT INTO students (name, email, created_at, updated_at)
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', CURRENT_DATE(), CURRENT_DATE());


-- Inserts na tabela courses
INSERT INTO courses (title, description, created_at, updated_at) VALUES
('English Basics', 'Introductory course covering basic English grammar and vocabulary.', CURRENT_DATE(), CURRENT_DATE());

-- Inserts na tabela lessons (associadas ao curso)
INSERT INTO lessons (course_id, title, duration_seconds, created_at, updated_at) VALUES
(1, 'Introduction to English', 600, CURRENT_DATE(), CURRENT_DATE()),
(1, 'Basic Grammar Rules', 900, CURRENT_DATE(), CURRENT_DATE()),
(1, 'Everyday Vocabulary', 1200, CURRENT_DATE(), CURRENT_DATE());

-- Matrícula dos alunos no curso
INSERT INTO student_courses (student_id, course_id, enrolled_at, status) VALUES
(1, 1, NOW(), 'enrolled'),
(2, 1, NOW(), 'enrolled');

-- Progresso em lições
INSERT INTO student_lesson_progress (student_id, lesson_id, percentage_of_progress, completed_at) VALUES
(1, 1, 1, NOW()),
(1, 2, 2, NOW()),
(2, 1, 1, NOW());

-- Progresso geral no curso
INSERT INTO student_progress (student_id, course_id, progress_percent, last_updated) VALUES
(1, 1, 66.67, NOW()),
(2, 1, 33.33, NOW());