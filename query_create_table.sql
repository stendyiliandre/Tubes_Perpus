CREATE TABLE Members (
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	address VARCHAR(255),
	birth_date DATE,
	membership_date DATE,
	expired_date DATE,
	PRIMARY KEY(id)
);

CREATE TABLE Books (
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	price int,
	author VARCHAR(255),
	age_restriction int,
	published_year YEAR,
	status VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE BorrowedBy(
	id int NOT NULL AUTO_INCREMENT,
	borrow_date DATE,
	return_date DATE,
	book_id int,
	member_id int,
	PRIMARY KEY(id),
	FOREIGN KEY (book_id) REFERENCES Books(id),
	FOREIGN KEY (member_id) REFERENCES Members(id)
);

CREATE TABLE Genres (
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE BookGenre (
	genre_id int,
	book_id int,
	PRIMARY KEY(genre_id, book_id),
	FOREIGN KEY (book_id) REFERENCES Books(id),
	FOREIGN KEY (genre_id) REFERENCES Genres(id)
);
