DROP DATABASE IF EXISTS ProyectoMixII;
CREATE DATABASE ProyectoMixII CHARACTER SET utf8mb4;
USE ProyectoMixII;
CREATE TABLE Civilization_stats (
    civilization_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    wood_amount INT DEFAULT 0,
    iron_amount INT DEFAULT 0,
    food_amount INT DEFAULT 0,
    mana_amount INT DEFAULT 0,
    magicTower_counter INT DEFAULT 0,
    church_counter INT DEFAULT 0,
    farm_counter INT DEFAULT 0,
    smithy_counter INT DEFAULT 0,
    carpentry_counter INT DEFAULT 0,
    technology_defense_level INT DEFAULT 0,
    technology_attack_level INT DEFAULT 0,
    battles_counter INT DEFAULT 0
);
CREATE TABLE attack_units_stats (
    civilization_id INT NOT NULL,
    unit_id INT NOT NULL ,
    type ENUM('Swordsman','Spearman','Crossbow','Cannon') NOT NULL,
    armor INT DEFAULT 0,
    base_damage INT DEFAULT 0,
    experience INT DEFAULT 0,
    sanctified BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (civilization_id, unit_id),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE defense_units_stats (
    civilization_id INT NOT NULL,
    unit_id INT NOT NULL,
    type ENUM('ArrowTower','Catapult','RocketLauncher Tower') NOT NULL,
    armor INT DEFAULT 0,
    base_damage INT DEFAULT 0,
    experience INT DEFAULT 0,
    PRIMARY KEY (civilization_id, unit_id),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE special_units_stats (
    civilization_id INT NOT NULL,
    unit_id INT NOT NULL,
    type ENUM('Magician','Priest') NOT NULL,
    armor INT DEFAULT 0,
    base_damage INT DEFAULT 0,
    experience INT DEFAULT 0,
    sanctified BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (civilization_id, unit_id),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Civilization_attack_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Civilization_defense_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Civilization_special_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Enemy_attack_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type ENUM('Swordsman','Spearman','Crossbow','Cannon') NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Battle_resources (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    wood_acquired INT DEFAULT 0,
    iron_acquired INT DEFAULT 0,
    PRIMARY KEY (civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
CREATE TABLE Battle_log (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    num_line INT NOT NULL,
    log_entry TEXT NOT NULL,
    PRIMARY KEY (civilization_id, num_battle, num_line),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);

