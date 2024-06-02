CREATE TABLE airData_info(
    id VARCHAR(255) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    pm25 DOUBLE NOT NULL,
    pm10 DOUBLE NOT NULL,
    so2 DOUBLE NOT NULL,
    no2 DOUBLE NOT NULL,
    co DOUBLE NOT NULL,
    o3 DOUBLE NOT NULL,
);

CREATE TABLE report_info(
    id VARCHAR(255) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    status INT NOT NULL,
    created_time DATETIME NOT NULL,
    description VARCHAR(1023) NOT NULL,
    image_url VARCHAR(1023) NOT NULL
);

CREATE TABLE task_info (
    id VARCHAR(255) PRIMARY KEY,
    appointer_id VARCHAR(255) NOT NULL,
    appointee_id VARCHAR(255) NOT NULL,
    created_time DATETIME NOT NULL,
    submitted_time DATETIME NOT NULL,
    status INT NOT NULL,
    description VARCHAR(1023) NOT NULL,
    related_air_data VARCHAR(255) NOT NULL,
    image_url VARCHAR(1023) NOT NULL
);

INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114512392495034368', 'shenyang', '2023-01-01', 10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720256', 'shenyang', '2023-01-02', 11.0, 21.0, 31.0, 41.0, 51.0, 61.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720257', 'shenyang', '2023-01-03', 12.0, 22.0, 32.0, 42.0, 52.0, 62.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720258', 'shenyang', '2023-01-04', 13.0, 23.0, 33.0, 43.0, 53.0, 63.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720259', 'shenyang', '2023-01-05', 14.0, 24.0, 34.0, 44.0, 54.0, 64.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720260', 'shenyang', '2023-01-06', 15.0, 25.0, 35.0, 45.0, 55.0, 65.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720261', 'shenyang', '2023-01-07', 16.0, 26.0, 36.0, 46.0, 56.0, 66.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720262', 'shenyang', '2023-01-08', 17.0, 27.0, 37.0, 47.0, 57.0, 67.0);
INSERT INTO airData_info(id, location, date, pm25, pm10, so2, no2, co, o3) VALUES ('1114515468794720263', 'shenyang', '2023-01-09', 18.0, 28.0, 38.0, 48.0, 58.0, 68.0);