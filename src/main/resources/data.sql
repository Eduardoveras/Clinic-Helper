INSERT INTO staff (jasc_id, staff_first_name, staff_last_name, staff_email, staff_clinic_id, staff_birth_date) VALUES ('JASC-STAFF-ADMIN', 'administrator', 'ADMIN', 'djidjellypas92@gmail.com', '1', '2016-10-23');

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
('JASC-P-BVN309WJHN32', 'Stripper', 'Villa Olga', '1992-07-21', 'Santiago',
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
('JASC-P-BVN3DF43WED32', 'Hobo', 'Queens', '1996-03-02', 'NYC',
'829xxxyyyy', 'USA', 'eduardo2@gmail.com', 'Eduardo', 'M', '12343eg 323',
'Veras', 'Dominicana', '2011-01-23', '809xxxyyyy');

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
'Soraya', 'Dominicana', '2015-10-23', '809xxxyyyy');
