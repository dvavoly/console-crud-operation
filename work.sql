DROP TABLE IF EXISTS developer;
CREATE TABLE developer (
    id INT NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);
insert into developer (first_name, last_name)
values ('Chaim', 'Beacock');
insert into developer (first_name, last_name)
values ('Collie', 'McLugaish');
insert into developer (first_name, last_name)
values ('Isa', 'Juschke');
insert into developer (first_name, last_name)
values ('Tabbitha', 'Snufflebottom');
insert into developer (first_name, last_name)
values ('Chico', 'Honeyghan');
insert into developer (first_name, last_name)
values ('Lesya', 'Christon');
insert into developer (first_name, last_name)
values ('Ronica', 'Pien');
insert into developer (id, first_name, last_name)
values (8, 'Terrye', 'Fretter');
insert into developer (id, first_name, last_name)
values (9, 'Sammy', 'Pentony');
insert into developer (id, first_name, last_name)
values (10, 'Syman', 'Eckh');
insert into developer (id, first_name, last_name)
values (11, 'Elana', 'McGeachie');
insert into developer (id, first_name, last_name)
values (12, 'Korney', 'Hollingsby');
insert into developer (id, first_name, last_name)
values (13, 'Reuben', 'Ailmer');
insert into developer (id, first_name, last_name)
values (14, 'Brod', 'Shimoni');
insert into developer (id, first_name, last_name)
values (15, 'Armand', 'Gelling');
insert into developer (id, first_name, last_name)
values (16, 'Rurik', 'McCloughlin');
insert into developer (id, first_name, last_name)
values (17, 'Beilul', 'Jeafferson');
insert into developer (id, first_name, last_name)
values (18, 'Lizbeth', 'Covill');
insert into developer (id, first_name, last_name)
values (19, 'Cesar', 'Daughtry');
insert into developer (id, first_name, last_name)
values (20, 'Warren', 'Maasze');
insert into developer (id, first_name, last_name)
values (21, 'Emmit', 'Coulthard');
insert into developer (id, first_name, last_name)
values (22, 'Joanne', 'Keiley');
insert into developer (id, first_name, last_name)
values (23, 'Abdul', 'Joannet');
insert into developer (id, first_name, last_name)
values (24, 'Vivia', 'Brim');
insert into developer (id, first_name, last_name)
values (25, 'Harper', 'Anderson');
DROP TABLE IF EXISTS skill;
CREATE TABLE skill (
    id INT NOT NULL PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL
);
insert into skill (id, skill_name)
values (1, 'Business Objects');
insert into skill (id, skill_name)
values (2, 'Snooker');
insert into skill (id, skill_name)
values (3, 'CPLD');
insert into skill (id, skill_name)
values (4, 'ITIL v3 Foundations Certified');
insert into skill (id, skill_name)
values (5, 'MPBN');
insert into skill (id, skill_name)
values (6, 'WHIMS');
insert into skill (id, skill_name)
values (7, 'Real Estate License');
insert into skill (id, skill_name)
values (8, 'Skin');
insert into skill (id, skill_name)
values (9, 'Contractual Agreements');
insert into skill (id, skill_name)
values (10, 'WFA');
insert into skill (id, skill_name)
values (11, 'CADS RC');
insert into skill (id, skill_name)
values (12, 'VPLS');
insert into skill (id, skill_name)
values (13, 'Kinesiology');
insert into skill (id, skill_name)
values (14, 'Account Management');
insert into skill (id, skill_name)
values (15, 'GPGPU');
insert into skill (id, skill_name)
values (16, 'Kinematics');
insert into skill (id, skill_name)
values (17, 'MCSA');
insert into skill (id, skill_name)
values (18, 'Banquets');
insert into skill (id, skill_name)
values (19, 'CBI');
insert into skill (id, skill_name)
values (20, 'Physical Therapy');
insert into skill (id, skill_name)
values (21, 'GFAAS');
insert into skill (id, skill_name)
values (22, 'FTPS');
insert into skill (id, skill_name)
values (23, 'Algorithms');
insert into skill (id, skill_name)
values (24, 'Tds');
insert into skill (id, skill_name)
values (25, 'MPR');
DROP TABLE IF EXISTS specialty;
CREATE TABLE specialty (
    id INT NOT NULL PRIMARY KEY,
    specialty_name VARCHAR(255) NOT NULL
);
insert into specialty (id, specialty_name)
values (1, 'Sales');
insert into specialty (id, specialty_name)
values (2, 'Legal');
insert into specialty (id, specialty_name)
values (3, 'Business Development');
insert into specialty (id, specialty_name)
values (4, 'Training');
insert into specialty (id, specialty_name)
values (5, 'Legal');
insert into specialty (id, specialty_name)
values (6, 'Business Development');
insert into specialty (id, specialty_name)
values (7, 'Training');
insert into specialty (id, specialty_name)
values (8, 'Legal');
insert into specialty (id, specialty_name)
values (9, 'Support');
insert into specialty (id, specialty_name)
values (10, 'Training');
insert into specialty (id, specialty_name)
values (11, 'Legal');
insert into specialty (id, specialty_name)
values (12, 'Business Development');
insert into specialty (id, specialty_name)
values (13, 'Business Development');
insert into specialty (id, specialty_name)
values (14, 'Business Development');
insert into specialty (id, specialty_name)
values (15, 'Product Management');
insert into specialty (id, specialty_name)
values (16, 'Support');
insert into specialty (id, specialty_name)
values (17, 'Human Resources');
insert into specialty (id, specialty_name)
values (18, 'Services');
insert into specialty (id, specialty_name)
values (19, 'Sales');
insert into specialty (id, specialty_name)
values (20, 'Product Management');
insert into specialty (id, specialty_name)
values (21, 'Product Management');
insert into specialty (id, specialty_name)
values (22, 'Support');
insert into specialty (id, specialty_name)
values (23, 'Product Management');
insert into specialty (id, specialty_name)
values (24, 'Product Management');
insert into specialty (id, specialty_name)
values (25, 'Research and Development');
DROP TABLE IF EXISTS developer_skill;
CREATE TABLE developer_skill (
    developer_id INT NOT NULL,
    skill_id INT NOT NULL
);
insert into developer_skill
values (1, 2);
insert into developer_skill
values (1, 4);
insert into developer_skill
values (1, 22);
insert into developer_skill
values (1, 14);
insert into developer_skill
values (2, 14);
insert into developer_skill
values (2, 15);
insert into developer_skill
values (2, 16);
insert into developer_skill
values (2, 9);
insert into developer_skill
values (3, 1);
insert into developer_skill
values (3, 5);
insert into developer_skill
values (3, 17);
insert into developer_skill
values (3, 10);
insert into developer_skill
values (3, 12);
insert into developer_skill
values (3, 1);
DROP TABLE IF EXISTS developer_specialty;
CREATE TABLE developer_specialty (
    developer_id INT NOT NULL,
    specialty_id INT NOT NULL
);
insert into developer_specialty
values (1, 1);
insert into developer_specialty
values (2, 2);
insert into developer_specialty
values (3, 22);
insert into developer_specialty
values (4, 3);
insert into developer_specialty
values (5, 21);