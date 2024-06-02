-- 创建 user_info 表
CREATE TABLE user_info(
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status INT NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- 创建 administrator_info 表
CREATE TABLE administrator_info (
    id VARCHAR(255) PRIMARY KEY,
    id_card VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- 创建 supervisor_info 表
CREATE TABLE supervisor_info (
    id VARCHAR(255) PRIMARY KEY,
    id_card VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- 创建 gridManager_info 表
CREATE TABLE gridManager_info (
     id VARCHAR(255) PRIMARY KEY,
     zone VARCHAR(255) NOT NULL,
     id_card VARCHAR(255) NOT NULL,
     name VARCHAR(255) NOT NULL
);


-- 插入测试数据
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114512392495034368', 'user01', 'user01', 1, 'Administrator');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720256', 'user02', 'user02', 1, 'Administrator');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720257', 'user03', 'user03', 0, 'Administrator');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720258', 'user04', 'user04', 1, 'Administrator');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720259', 'user05', 'user05', 1, 'Administrator');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720260', 'user06', 'user06', 1, 'Supervisor');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720261', 'user07', 'user07', 1, 'Supervisor');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720262', 'user08', 'user08', 0, 'Supervisor');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720263', 'user09', 'user09', 1, 'Supervisor');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720264', 'user10', 'user10', 1, 'Supervisor');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720265', 'user11', 'user11', 1, 'GridManager');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720266', 'user12', 'user12', 1, 'GridManager');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720267', 'user13', 'user13', 0, 'GridManager');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114515468794720268', 'user14', 'user14', 1, 'GridManager');
INSERT INTO user_info(id, username, password, status, role) VALUES ('1114512319153434669', 'user15', 'user15', 1, 'GridManager');

INSERT INTO administrator_info(id, id_card, name) VALUE ('1114512392495034368', '450902200001010012', 'test01');
INSERT INTO administrator_info(id, id_card, name) VALUE ('1114515468794720256', '450902200001010013', 'test02');
INSERT INTO administrator_info(id, id_card, name) VALUE ('1114515468794720257', '450902200001010014', 'test03');
INSERT INTO administrator_info(id, id_card, name) VALUE ('1114515468794720258', '450902200001010015', 'test04');
INSERT INTO administrator_info(id, id_card, name) VALUE ('1114515468794720259', '450902200001010016', 'test05');

INSERT INTO supervisor_info(id, id_card, name) VALUE ('1114515468794720260', '450902200001010017', 'test06');
INSERT INTO supervisor_info(id, id_card, name) VALUE ('1114515468794720261', '450902200001010018', 'test07');
INSERT INTO supervisor_info(id, id_card, name) VALUE ('1114515468794720262', '450902200001010019', 'test08');
INSERT INTO supervisor_info(id, id_card, name) VALUE ('1114515468794720263', '450902200001010020', 'test09');
INSERT INTO supervisor_info(id, id_card, name) VALUE ('1114515468794720264', '450902200001010021', 'test10');

INSERT INTO gridManager_info(id, zone, id_card, name) VALUE ('1114515468794720265', 'shenyang', '450902200001010022', 'test11');
INSERT INTO gridManager_info(id, zone, id_card, name) VALUE ('1114515468794720266', 'shenyang', '450902200001010023', 'test12');
INSERT INTO gridManager_info(id, zone, id_card, name) VALUE ('1114515468794720267', 'shenyang', '450902200001010024', 'test13');
INSERT INTO gridManager_info(id, zone, id_card, name) VALUE ('1114515468794720268', 'shenyang', '450902200001010025', 'test14');
INSERT INTO gridManager_info(id, zone, id_card, name) VALUE ('1114515468794720269', 'shenyang', '450902200001010026', 'test15');
