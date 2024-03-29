
-- GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) 
create table CATEGORIES (
	ID bigint not null, 
	NAME varchar(50) not null, 
	PRIMARY KEY (ID)
);

create table QUESTIONS (
	ID bigint generated by default as identity (start with 1) not null,
	CATEGORY_ID bigint not null,
	TEXT varchar(150) not null,
	PRIMARY KEY (ID),
);

ALTER TABLE QUESTIONS ADD CONSTRAINT QUESTIONS_CATEGORIES_FK 
	FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES (ID);
	
CREATE INDEX QUESTIONS_CATEGORIES_FK_I ON QUESTIONS(CATEGORY_ID ASC);


create table COUPLES (
	ID bigint not null, 
	SCORE integer not null, 
	PRIMARY KEY (ID)
);

create table PARTNERS (
	ID bigint not null,
	COUPLE_ID bigint not null,
	USER_ID varchar(50) not null,
	PRIMARY KEY (ID)
);

ALTER TABLE PARTNERS ADD CONSTRAINT PARTNERS_COUPLES_FK 
	FOREIGN KEY (COUPLE_ID) REFERENCES COUPLES (ID);

create table ANSWERS (
	PARTNER_ID bigint not null,
	QUESTION_ID bigint not null, 
	ANSWER varchar(50) not null
);

ALTER TABLE ANSWERS ADD CONSTRAINT ANSWERS_PARTNERS_FK 
	FOREIGN KEY (PARTNER_ID) REFERENCES PARTNERS (ID);

ALTER TABLE ANSWERS ADD CONSTRAINT ANSWERS_QUESTIONS_FK 
	FOREIGN KEY (QUESTION_ID) REFERENCES QUESTIONS (ID);

