INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-ADMIN', 'administrator', 'ADMIN', 'djidjellypas92@gmail.com', '1', '2016-11-4');

INSERT INTO users (username, password, role, staff_jasc_id) VALUES ('admin', 'admin', 'ADMIN', 'JASC-STAFF-ADMIN');

INSERT INTO patients (jasc_id,
  occupation,
  patient_address,
  patient_birth_date,
  patient_city,
  patient_contact_telephone_number,
  patient_country,
  patient_email,
  patient_first_name,
  patient_gender,
  patient_id_card,
  patient_last_name,
  patient_nationality,
  patient_registered_date,
  patient_telephone_number) VALUES
('JASC-P-BVN309WJHN32', 'Stripper', 'Villa Olga', '1992-10-28', 'Santiago',
'829xxxyyyy', 'RD', 'djidjellypas92@gmail.com', 'Djidjelly', 'M', '1234rfg 323',
'Siclait', 'Haitiano', '2015-10-23', '809xxxyyyy');

INSERT INTO patients (jasc_id,
  occupation,
  patient_address,
  patient_birth_date,
  patient_city,
  patient_contact_telephone_number,
  patient_country,
  patient_email,
  patient_first_name,
  patient_gender,
  patient_id_card,
  patient_last_name,
  patient_nationality,
  patient_registered_date,
  patient_telephone_number) VALUES
('JASC-P-BVN3DF43WED32', 'Hobo', 'Queens', '1996-11-01', 'NYC',
'829xxxyyyy', 'USA', 'eduardo2@gmail.com', 'Eduardo', 'M', '12343eg 323',
'Veras', 'Dominicana', '2011-10-28', '809xxxyyyy');

INSERT INTO patients (jasc_id,
  occupation,
  patient_address,
  patient_birth_date,
  patient_city,
  patient_contact_telephone_number,
  patient_country,
  patient_email,
  patient_first_name,
  patient_gender,
  patient_id_card,
  patient_last_name,
  patient_nationality,
  patient_registered_date,
  patient_telephone_number) VALUES
('JASC-P-BVN309WDHN32', 'Crazy cat lady', 'Sience', '1982-04-11', 'Paris',
'829xxxyyyy', 'FR', 'eva@gmail.com', 'Eva', 'F', '12oih7g 323',
'Soraya', 'Dominicana', '2015-10-28', '809xxxyyyy');

INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-PAULINA', 'Paulina', 'Reyes', 'p@gmail.com', '2', '1985-03-23');
INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-SICARD', 'Julio', 'Sicard', 'js@gmail.com', '3', '1980-11-3');
INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-ADELSO', 'Adelso', 'Tejada', 'at@gmail.com', '4', '1990-02-02');
INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-ASTRID', 'Astrid', 'Incarnacion', 'ai@gmail.com', '5', '1990-02-02');

INSERT INTO users (username, password, role, staff_jasc_id) VALUES ('plin', '1234', 'ASSISTANT', 'JASC-STAFF-PAULINA');
INSERT INTO users (username, password, role, staff_jasc_id) VALUES ('jsicard', '1234', 'MEDIC', 'JASC-STAFF-SICARD');

INSERT INTO meetings (jasc_id,
meeting_date,
meeting_objective,
meeting_place,
meeting_time,
meeting_title) VALUES
('JASC-M-NY934WHRC3', '2016-10-28', 'Make plans for charity event', 'Meeting Room 102', '2016-10-28 08:00:00', 'JASC Cancer Charity');

INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-PAULINA');
INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-ASTRID');
INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-SICARD');
INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-ADELSO');


INSERT INTO meetings (jasc_id,
meeting_date,
meeting_objective,
meeting_place,
meeting_time,
meeting_title) VALUES
('JASC-M-NY934UINRC3', '2016-10-28', 'Prepare for seminar', 'Main Office', '2016-10-28 16:30:00', 'Seminar Prep');

INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934UINRC3', 'JASC-STAFF-SICARD');
INSERT INTO  meetings_attendees (meeting_jasc_id, attendees_jasc_id) VALUES ('JASC-M-NY934UINRC3', 'JASC-STAFF-ADELSO');

INSERT INTO chore (jasc_id, description, title, type) VALUES ('JASC-M-9URHFDN', 'By more Bras', 'Inventory refill', 4);
INSERT INTO chore (jasc_id, description, title, type) VALUES ('JASC-M-9UR23DN', 'Call Accountant', 'Finance', 6);