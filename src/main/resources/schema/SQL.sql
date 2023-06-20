
DROP DATABASE MobileParadise;

CREATE DATABASE MobileParadise;

USE MobileParadise;

CREATE TABLE employeeDTO
(
    employee_id    VARCHAR(45) PRIMARY KEY,
    first_name     VARCHAR(45)        NOT NULL,
    last_name      VARCHAR(45)        NOT NULL,
    street         TEXT               NOT NULL,
    city           TEXT               NOT NULL,
    lane           TEXT,
    role           ENUM ('Admin','Cashier','Repairer','Salesman','Other'),
    date           DATE,
    nic            VARCHAR(20) UNIQUE NOT NULL,
    email          VARCHAR(30) UNIQUE NOT NULL,
    contact_number VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE employee_attendance
(
    employee_id VARCHAR(45),
    time        TIME,
    date        DATE,
    CONSTRAINT FOREIGN KEY employee_attendance (employee_id) REFERENCES employeeDTO (employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE salaryDTO
(
    employee_id VARCHAR(45),
    salary_id   VARCHAR(45) PRIMARY KEY,
    salaryDTO      DECIMAL(6, 2) NOT NULL,
    monthly_Attendance_count INT,
    date        DATE,
    time        TIME,
    CONSTRAINT FOREIGN KEY salaryDTO (employee_id) REFERENCES employeeDTO (employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE userDTO
(
    employee_id VARCHAR(45),
    user_name   VARCHAR(12),
    password    VARCHAR(12),
    role        ENUM ('Admin','Cashier'),
    CONSTRAINT FOREIGN KEY userDTO (employee_id) REFERENCES employeeDTO (employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE customerDTO
(
    customer_id    VARCHAR(45) PRIMARY KEY,
    first_name     VARCHAR(45)        NOT NULL,
    last_name      VARCHAR(45)        NOT NULL,
    street         TEXT               NOT NULL,
    city           TEXT               NOT NULL,
    lane           TEXT,
    date           DATE,
    nic            VARCHAR(20),
    email          VARCHAR(30) UNIQUE NOT NULL,
    contact_number VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE repairDTO
(
    repair_id    VARCHAR(45) PRIMARY KEY,
    customer_id  VARCHAR(45),
    status       ENUM ('Warranty','Repair'),
    imei_number  VARCHAR(50) UNIQUE NOT NULL,
    model_number VARCHAR(15) NOT NULL,
    error_type   VARCHAR(40) NOT NULL,
    given_date DATE,
    return_give_date DATE,
    CONSTRAINT FOREIGN KEY userDTO (customer_id) REFERENCES customerDTO (customer_id) ON UPDATE CASCADE ON DELETE CASCADE

);
CREATE TABLE customer_order
(
    customer_order_id VARCHAR(20) PRIMARY KEY,
    customer_id       VARCHAR(45),
    time              TIME,
    date              DATE,
    payment           DECIMAL NOT NULL,
    CONSTRAINT FOREIGN KEY customer_order (customer_id) REFERENCES customerDTO (customer_id) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE itemDTO
(
    item_code    VARCHAR(45) PRIMARY KEY ,
    item_name   VARCHAR(50) NOT NULL,
    category    VARCHAR(20) NOT NULL,
    unit_price  DECIMAL     NOT NULL,
    qty    INT         NOT NULL,
    brand       VARCHAR(20) NOT NULL,
    description TEXT
);

CREATE TABLE customer_order_detail
(
    item_id     VARCHAR(45),
    customer_order_id VARCHAR(20),
    qty int not null ,
    price decimal,
    constraint foreign key(item_id) REFERENCES itemDTO(item_code) ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT FOREIGN KEY (customer_order_id)REFERENCES customer_order(customer_order_id)ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplierDTO
(
    supplier_id    VARCHAR(45) PRIMARY KEY,
    company_name   VARCHAR(40)        NOT NULL,
    contact_number VARCHAR(15) UNIQUE NOT NULL,
    email          VARCHAR(30) UNIQUE NOT NULL,
    location       TEXT               NOT NULL
);

CREATE TABLE supplier_order
(
    supplier_order_id VARCHAR(45) PRIMARY KEY,
    supplier_id       VARCHAR(45),
    time              TIME,
    date              DATE,
    payment           DECIMAL,
    CONSTRAINT FOREIGN KEY supplier_order (supplier_id) REFERENCES supplierDTO (supplier_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE supplier_order_details
(

    item_code           VARCHAR(20),
    supplier_order_id VARCHAR(45),
    quantity          INT     NOT NULL,
    unit_price        DECIMAL NOT NULL,
    CONSTRAINT FOREIGN KEY (item_code) REFERENCES itemDTO (item_code) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (supplier_order_id) REFERENCES supplier_order (supplier_order_id) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE customer_order ADD repair_id varchar(45);

ALTER TABLE itemDTO ADD Warranty_month int ;


INSERT INTO MobileParadise.itemDTO (item_code, item_name, category, unit_price, qty, brand, description, Warranty_month) VALUES ('I001', 'bn', 'fer', 150, 50, 'scsc', 'wwd', 45);
INSERT INTO MobileParadise.itemDTO (item_code, item_name, category, unit_price, qty, brand, description, Warranty_month) VALUES ('I002', 'wfre', 'ecer', 150, 12, 'erfr', '3f34', 12);
INSERT INTO MobileParadise.itemDTO (item_code, item_name, category, unit_price, qty, brand, description, Warranty_month) VALUES ('I003', 'wef', 'fw', 200, 10, 'sdcs', 'wfw', 12);
INSERT INTO MobileParadise.itemDTO (item_code, item_name, category, unit_price, qty, brand, description, Warranty_month) VALUES ('I004', 'dfwe', 'sdfwe', 0, 0, 'sdc', 'wef', 12);
INSERT INTO MobileParadise.itemDTO (item_code, item_name, category, unit_price, qty, brand, description, Warranty_month) VALUES ('I005', 'as', 'asq', 0, 0,'xa','s',21);


INSERT INTO MobileParadise.customerDTO (customer_id, first_name, last_name, street, city, lane, date, nic, email, contact_number) VALUES ('C001', 'def', 'erf', 'rfr', 'ferf', 'rf', '2023-04-13', 'frf', 'frfrf', 'rfrf');
INSERT INTO MobileParadise.customerDTO (customer_id, first_name, last_name, street, city, lane, date, nic, email, contact_number) VALUES ('C002', 'Sithum', 'Imesh', 'wkejnwe', 'sjnfkwje', 'wjdwkjend', '2023-04-22', 'sdsnfkjs', 'snk f', 'ifwnf');
INSERT INTO MobileParadise.customerDTO (customer_id, first_name, last_name, street, city, lane, date, nic, email, contact_number) VALUES ('C003', 'rfqw', 'wefqw', 'Galle', 'Hikkaduwa', 'wwee', '2023-04-23', '200212703312', 'sithum@gmail.com','0784562361');

