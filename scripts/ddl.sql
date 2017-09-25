CREATE DATABASE theatre;
Use theatre;
CREATE TABLE movie(
 id INT(10) NOT NULL AUTO_INCREMENT,
 title varchar(255) NOT NULL,
 rated varchar(255) DEFAULT NULL,
 released varchar(255) DEFAULT NULL,
 runtime  varchar(255) DEFAULT NULL,
 lang varchar(255) DEFAULT NULL,
 Poster   varchar(255) DEFAULT NULL,
 Primary key(id)
);
ALTER TABLE movie DROP COLUMN Poster;
ALTER TABLE movie ADD COLUMN poster TEXT;
ALTER TABLE movie ADD COLUMN description TEXT;
ALTER TABLE movie ADD COLUMN releaseDate datetime;

CREATE TABLE genre(
	id INT(10) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    Primary key(id)
);

CREATE TABLE actor(
	id INT(10)NOT NULL auto_increment,
    name varchar(255) NOT NULL,
    Primary key(id)
);

CREATE TABLE movie_genre(
   id INT(10) NOT NULL AUTO_INCREMENT,
   movie_id INT(10) NOT NULL,
   genre_id INT(10) NOT NULL,
   UNIQUE (id),
   foreign key(movie_id) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
   foreign key(genre_id) REFERENCES genre(id) ON DELETE CASCADE ON UPDATE CASCADE,
   primary key(movie_id,genre_id)
   );
   
   
CREATE TABLE movie_actor(
   id INT(10) NOT NULL AUTO_INCREMENT,
   movie_id INT(10) NOT NULL,
   actor_id INT(10) NOT NULL,
   UNIQUE (id),
   foreign key(movie_id) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
   foreign key(actor_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE,
   primary key(movie_id,actor_id)
   );
   
CREATE TABLE users(
	id INT(10) NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(200) DEFAULT 'password',
    email VARCHAR(200) NOT NULL,
    phone VARCHAR(200) NOT NULL,
    address VARCHAR(1000) NOT NULL,
    city VARCHAR(100) NOT NULL,
    zip  VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
	isAdmin boolean default false,
    loggedIn boolean default false,
    Primary key (id)
    );
   
CREATE TABLE movie_ratings(
	id INT(10) NOT NULL AUTO_INCREMENT,
    user_id INT(10) NOT NULL,
    movie_id INT(10) NOT NULL,
    comment  TEXT,
    rating INT(10) NOT NULL,
    Primary key(id),
    foreign key(movie_id) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE);
   