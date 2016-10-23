INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-ADMIN', 'administrator', 'ADMIN', 'djidjellypas92@gmail.com', '1', '2016-10-23');

INSERT INTO users (username, password, role, staff_jasc_id) VALUES ('admin', 'admin', 'ADMIN', 'JASC-STAFF-ADMIN');