/*
Caio Lizeo Soares - 87809
Isabela Bianca Correa de Macedo - 88493
Jonatan Jacó Mascalhusk de Oliverira Souza - 88221
Lucas Amorim Marques Pereira - 84659
Rodrigo Gonzalo Barbosa Segura - 83954
*/

/*
    DROP TABLE t_c4f_associacao CASCADE CONSTRAINTS;
    DROP TABLE t_c4f_reserva CASCADE CONSTRAINTS;
    DROP TABLE t_c4f_produto CASCADE CONSTRAINTS;
    DROP TABLE t_c4f_estabelecimento CASCADE CONSTRAINTS;
*/

CREATE TABLE t_c4f_associacao (
    cd_assoc        NUMBER(4)       NOT NULL,
    ds_cnpj         VARCHAR2(18)    NOT NULL,
    nm_associacao   VARCHAR2(30)    NOT NULL,
    ds_cep          VARCHAR2(9)     NOT NULL,
    ds_endereco     VARCHAR2(256)   NOT NULL,
    nr_telefone     NUMBER(11)      NOT NULL,
    ds_email        VARCHAR2(256)   NOT NULL,
    ds_senha        VARCHAR2(6)     NOT NULL,
    nm_responsavel  VARCHAR2(60)    NOT NULL
);

CREATE TABLE t_c4f_estabelecimento (
    cd_estab            NUMBER(3)       NOT NULL,
    ds_cnpj             VARCHAR2(18)    NOT NULL,
    nm_estabelecimento  VARCHAR2(30)    NOT NULL,
    ds_cep              VARCHAR2(9)     NOT NULL,
    ds_endereco         VARCHAR2(256)   NOT NULL,
    nr_telefone         NUMBER(11)      NOT NULL,
    ds_email            VARCHAR2(256)   NOT NULL,
    ds_senha            VARCHAR2(6)     NOT NULL,
    ds_tipo             NUMBER(1)       NOT NULL
);

CREATE TABLE t_c4f_produto (
    cd_produto   NUMBER(5)      NOT NULL,
    cd_estab     NUMBER(3)      NOT NULL,
    nm_produto   VARCHAR2(50)   NOT NULL,
    ds_tipo      NUMBER(1)      NOT NULL,
    ds_marca     VARCHAR2(50),
    dt_validade  DATE           NOT NULL,
    dt_entrada   DATE NOT NULL,
    qt_estoque   NUMBER(4)      NOT NULL
);

CREATE TABLE t_c4f_reserva (
    cd_reserva    NUMBER(6)     NOT NULL,
    cd_produto    NUMBER(5)     NOT NULL,
    cd_assoc      NUMBER(4)     NOT NULL,
    qt_reservada  NUMBER(4)     NOT NULL
);

CREATE SEQUENCE cd_estab_c4f
      START WITH 100        
    INCREMENT BY 1        
        MAXVALUE 999      
        NOCACHE           
        NOCYCLE;

CREATE SEQUENCE cd_assoc_c4f
      START WITH 1000        
    INCREMENT BY 1        
        MAXVALUE 9999      
        NOCACHE           
        NOCYCLE;

CREATE SEQUENCE cd_produto_c4f
      START WITH 10000        
    INCREMENT BY 1        
        MAXVALUE 99999      
        NOCACHE           
        NOCYCLE;

CREATE SEQUENCE cd_reserva_c4f
      START WITH 100000        
    INCREMENT BY 1        
        MAXVALUE 999999      
        NOCACHE           
        NOCYCLE;

ALTER TABLE t_c4f_associacao ADD CONSTRAINT pk_c4f_assoc_cd PRIMARY KEY ( cd_assoc );

ALTER TABLE t_c4f_produto ADD CONSTRAINT pk_c4f_prod_cd PRIMARY KEY ( cd_produto );

ALTER TABLE t_c4f_estabelecimento ADD CONSTRAINT pk_c4f_estab_cd PRIMARY KEY ( cd_estab );

ALTER TABLE t_c4f_reserva ADD CONSTRAINT pk_c4f_reserva_cd PRIMARY KEY ( cd_reserva );

ALTER TABLE t_c4f_produto ADD CONSTRAINT ck_c4f_produto_entrada CHECK ( dt_validade >= dt_entrada );

ALTER TABLE t_c4f_associacao ADD CONSTRAINT un_c4f_assoc_email UNIQUE ( ds_email );

ALTER TABLE t_c4f_estabelecimento ADD CONSTRAINT un_c4f_estab_email UNIQUE ( ds_email );

ALTER TABLE t_c4f_produto
    ADD CONSTRAINT fk_c4f_prod_estabel_cnpj FOREIGN KEY ( cd_estab )
        REFERENCES t_c4f_estabelecimento ( cd_estab );

ALTER TABLE t_c4f_reserva
    ADD CONSTRAINT fk_c4f_reser_assoc_cnpj FOREIGN KEY ( cd_assoc )
        REFERENCES t_c4f_associacao ( cd_assoc );

ALTER TABLE t_c4f_reserva
    ADD CONSTRAINT fk_c4f_reser_prod_cd FOREIGN KEY ( cd_produto )
        REFERENCES t_c4f_produto ( cd_produto );
