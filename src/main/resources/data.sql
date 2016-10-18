INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id) VALUES ('JASC-STAFF-ADMIN', 'administrator', 'ADMIN', 'djidjellypas92@gmail.com', '1');

INSERT INTO users (username, password, role, staff_jasc_id) VALUES ('admin', 'admin', 'ADMIN', 'JASC-STAFF-ADMIN');