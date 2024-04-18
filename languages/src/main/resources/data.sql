INSERT INTO language (id,name)
VALUES (1001, 'French');

INSERT INTO section (id,language_id,name)
VALUES (2001,1001, 'Good Morning');

INSERT INTO section (id,language_id,name)
VALUES (2002,1001, 'Introduce yourself');

INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3001,2001,'lorem ipsemalkdkslaasd',' ','text','lorem ipsim...', 'First Lesson');
INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3001,2001,'lorem ipsemalkdkslaasd',' ','text','lorem ipsim...', 'Second Lesson');


INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3003,2002,'lorem ipsemalkdkslaasd','https://www.youtube.com/watch?v=5c5P8YQeymI ','video','lorem ipsim...', 'Second Lesson');