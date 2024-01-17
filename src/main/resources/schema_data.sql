CREATE TABLE client(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    birth_data TIMESTAMP,
    included_date TIMESTAMP,
    update_Date TIMESTAMP
);

CREATE TABLE product(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    unit_price NUMERIC(20,2)
);

CREATE TABLE demand(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES client (id),
    data_demand TIMESTAMP,
    total NUMERIC(20, 2)
);

CREATE TABLE demand_items(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    demand_id INTEGER REFERENCES demand (id),
    product_id INTEGER REFERENCES product (id),
    quantity INTEGER
);


CREATE TABLE document(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    type_document enum('CPF', 'RG', 'CNH', 'CNPJ') NOT NULL,
    identification_number VARCHAR(20),
    included_date TIMESTAMP,
    update_Date TIMESTAMP,
    client_id INTEGER REFERENCES client (id),
);