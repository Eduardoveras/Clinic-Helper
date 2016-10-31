INSERT INTO clinic (clinic_id, account_type, clinic_name, clinic_prefix, registered_date) VALUES ('CH-PLATINUM-JASC', 3, 'JASC', 'JASC', '2016-10-30');

INSERT INTO staff (clinic_clinic_id, contact_id, first_name, last_name, email, birth_date) VALUES ('CH-PLATINUM-JASC', 'JASC-STAFF-ADMIN', 'administrator', 'ADMIN', 'djidjellypas92@gmail.com', '2016-11-4');

INSERT INTO users (user_id, birth_date, email, first_name, gender, last_name, password, role, clinic_clinic_id) VALUES ('CH-JASC-YOLO', '1980-02-03', 'djidjellypas92@gmail.com', 'djijdelly', 0, 'SICLAIT', '1234', 0, 'CH-PLATINUM-JASC');

INSERT INTO patients (
clinic_clinic_id,
patient_id,
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
('CH-PLATINUM-JASC', 'JASC-P-BVN309WJHN32', 'Stripper', 'Villa Olga', '1992-10-31', 'Santiago',
'829xxxyyyy', 'RD', 'djidjellypas92@gmail.com', 'Djidjelly', 'M', '1234rfg 323',
'Siclait', 'Haitiano', '2015-10-23', '809xxxyyyy');

INSERT INTO patients (
clinic_clinic_id,
patient_id,
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
('CH-PLATINUM-JASC', 'JASC-P-BVN3DF43WED32', 'Hobo', 'Queens', '1996-11-01', 'NYC',
'829xxxyyyy', 'USA', 'eduardo2@gmail.com', 'Eduardo', 'M', '12343eg 323',
'Veras', 'Dominicana', '2011-10-31', '809xxxyyyy');

INSERT INTO patients (
clinic_clinic_id,
patient_id,
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
('CH-PLATINUM-JASC', 'JASC-P-BVN309WDHN32', 'Crazy cat lady', 'Sience', '1982-04-11', 'Paris',
'829xxxyyyy', 'FR', 'eva@gmail.com', 'Eva', 'F', '12oih7g 323',
'Soraya', 'Dominicana', '2015-10-28', '809xxxyyyy');

INSERT INTO staff (clinic_clinic_id, contact_id, first_name, last_name, email, birth_date) VALUES ('CH-PLATINUM-JASC', 'JASC-STAFF-PAULINA', 'Paulina', 'Reyes', 'p@gmail.com', '1985-03-23');
INSERT INTO staff (clinic_clinic_id, contact_id, first_name, last_name, email, birth_date) VALUES ('CH-PLATINUM-JASC', 'JASC-STAFF-SICARD', 'Julio', 'Sicard', 'js@gmail.com', '1980-11-03');
INSERT INTO staff (clinic_clinic_id, contact_id, first_name, last_name, email, birth_date) VALUES ('CH-PLATINUM-JASC', 'JASC-STAFF-ADELSO', 'Adelso', 'Tejada', 'at@gmail.com', '1990-02-02');
INSERT INTO staff (clinic_clinic_id, contact_id, first_name, last_name, email, birth_date) VALUES ('CH-PLATINUM-JASC', 'JASC-STAFF-ASTRID', 'Astrid', 'Incarnacion', 'ai@gmail.com', '1990-10-31');

INSERT INTO appointments(clinic_clinic_id, appointment_id, appointment_date, appointment_time, patient_patient_id, appointment_description, appointment_access_from, appointment_type) VALUES (
'CH-PLATINUM-JASC',
'JASC123456',
'2016-10-31',
'2016-10-31 10:10:00',
'JASC-P-BVN309WJHN32',
'cita para bala bla bla bla',
'web',
0);

INSERT INTO meetings (
clinic_clinic_id,
meeting_id,
meeting_date,
meeting_objective,
meeting_place,
meeting_time,
meeting_title) VALUES
('CH-PLATINUM-JASC', 'JASC-M-NY934WHRC3', '2016-10-31', 'Make plans for charity event', 'Meeting Room 102', '2016-10-28 08:00:00', 'JASC Cancer Charity');

INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-PAULINA');
INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-ASTRID');
INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-SICARD');
INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934WHRC3', 'JASC-STAFF-ADELSO');

INSERT INTO meetings (
clinic_clinic_id,
meeting_id,
meeting_date,
meeting_objective,
meeting_place,
meeting_time,
meeting_title) VALUES
('CH-PLATINUM-JASC', 'JASC-M-NY934UINRC3', '2016-10-31', 'Prepare for seminar', 'Main Office', '2016-10-28 16:30:00', 'Seminar Prep');

INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934UINRC3', 'JASC-STAFF-SICARD');
INSERT INTO  meetings_attendees (meeting_meeting_id, attendees_contact_id) VALUES ('JASC-M-NY934UINRC3', 'JASC-STAFF-ADELSO');

INSERT INTO chore (clinic_clinic_id, chore_id, description, title, type) VALUES ('CH-PLATINUM-JASC', 'JASC-M-9URHFDN', 'By more Bras', 'Inventory refill', 4);
INSERT INTO chore (clinic_clinic_id, chore_id, description, title, type) VALUES ('CH-PLATINUM-JASC', 'JASC-M-9UR23DN', 'Call Accountant', 'Finance', 6);
