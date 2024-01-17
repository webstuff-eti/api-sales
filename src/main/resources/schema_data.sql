CREATE TABLE client(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR(11)
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
    description VARCHAR(200),
    data_demand TIMESTAMP,
    included_date TIMESTAMP,
    update_Date TIMESTAMP,
    client_id INTEGER REFERENCES client (id),
);