-- Inserts na tabela students
INSERT INTO students (name, email, created_at, updated_at)
VALUES ('João da Silva', 'joao.silva@email.com', CURRENT_DATE(), CURRENT_DATE());

INSERT INTO students (name, email, created_at, updated_at)
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', CURRENT_DATE(), CURRENT_DATE());

-- Inserts na tabela courses
INSERT INTO courses (title, description, total_number_of_classes, created_at, updated_at) VALUES
('English Basics', 'Introductory course covering basic English grammar and vocabulary.', 60, CURRENT_DATE(), CURRENT_DATE());

-- Inserts na tabela lessons (associadas ao curso)
INSERT INTO lessons (course_id, title, duration_seconds, created_at, updated_at) VALUES
(1, 'Introduction to English', 600, NOW(), NOW()),
(1, 'Basic Grammar Rules', 900, NOW(), NOW()),
(1, 'Everyday Vocabulary', 1200, NOW(), NOW());

-- Matrícula dos alunos no curso
INSERT INTO student_courses (student_id, course_id, enrolled_at, status) VALUES
(1, 1, NOW(), 'enrolled'),
(2, 1, NOW(), 'enrolled');

-- Progresso em lições
INSERT INTO student_lessons_progress (student_id, lesson_id, percentage_of_progress, completed_at) VALUES
(1, 1, 5, NOW()),
(1, 2, 2, NOW()),
(2, 1, 20, NOW());

-- Progresso geral no curso
INSERT INTO student_progress (student_id, course_id, progress_percent, last_updated) VALUES
(1, 1, 66.67, NOW()),
(2, 1, 33.33, NOW());

INSERT INTO course_completion_details (
    student_lesson_progress_id,
    total_classes,
    completed_classes,
    remaining_classes,
    percentage_completed,
    percentage_remaining
) VALUES
(1, 20, 10, 10, 50.0, 50.0),
(2, 15, 15, 0, 100.0, 0.0),
(3, 10, 4, 6, 40.0, 60.0);
