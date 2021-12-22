CREATE TABLE IF NOT EXISTS utenti(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    password VARCHAR(128) NOT NULL,
    comune VARCHAR(30) NOT NULL,
    nazione VARCHAR(30) NOT NULL,
    data_nascita date NOT NULL,
    codice_fiscale CHAR(16),
    sesso ENUM('F', 'M') NOT NULL,
    verified BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS impiegati(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    password VARCHAR(128) NOT NULL
);

INSERT INTO utenti(nome, cognome, password, tipo) VALUES('Ceo', 'Stronzo', 'prova', 'utente');
