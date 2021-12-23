CREATE TABLE IF NOT EXISTS utenti(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    password VARCHAR(128) NOT NULL,
    comune VARCHAR(30),
    nazione VARCHAR(30),
    data_nascita date,
    codice_fiscale CHAR(16), 
    sesso ENUM('F', 'M'),
    tipo ENUM('U', 'I') NOT NULL,
    CONSTRAINT CHECK_TIPO CHECK(
        (
            tipo = 'U'
            AND comune <> NULL
            AND nazione <> NULL
            AND data_nascita <> NULL
            AND codice_fiscale <> NULL
            AND sesso <> NULL
        ) OR tipo = 'I'
    )
);
