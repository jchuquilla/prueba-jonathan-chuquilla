CREATE TABLE GSTPROD.clients (
    client_id NUMBER(19) PRIMARY KEY,
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    identification VARCHAR2(50),
    age NUMBER(3),
    registration_date DATE,
    email VARCHAR2(100)
);

CREATE SEQUENCE GSTPROD.clients_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE GSTPROD.accounts (
    id NUMBER(19) PRIMARY KEY,
    account_number VARCHAR2(50),
    balance NUMBER(19,2),
    created_date DATE,
    client_id NUMBER(19) NOT NULL,
    CONSTRAINT fk_clients_accounts FOREIGN KEY (client_id)
        REFERENCES GSTPROD.clients(client_id)
);

CREATE SEQUENCE GSTPROD.accounts_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
