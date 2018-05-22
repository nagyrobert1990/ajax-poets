/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS poets;

CREATE TABLE poets (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    published_date INT NOT NULL,
    poet_id INT NOT NULL,
	FOREIGN KEY (poet_id) REFERENCES poets(id)
);

INSERT INTO poets (name, email, password) VALUES
	('Ady Endre','ady@endre.com', 'csinszka'), -- 1
	('József Attila','joseph@attila.hu', 'mama'), -- 2
	('Petőfi Sándor','petofi@sandor.hu', 'duna'); -- 3

INSERT INTO works (title, content, published_date, poet_id) VALUES
	('Héja nász az avaron','the conents place',1905,1),   -- 1
	('Kocsi-út az éjszakában','the conents place',1909,1),  -- 2
	('Nagyon fáj','the conents place',1936,2), -- 3
	('Óda','the conents place',1933,2),   -- 4
	('Szeptember végén','the conents place',1847,3),   -- 5
	('Mit nem beszél az a német','the conents place',1845,3);   --6
