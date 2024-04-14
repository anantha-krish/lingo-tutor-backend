INSERT INTO language (id,name)
VALUES (1001L, 'French');

INSERT INTO section (id,language_id,name)
VALUES (2001L,1001L, 'Good Morning');

INSERT INTO section (id,language_id,name)
VALUES (2002L,1001L, 'Introduce yourself');

INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3001L,2001L,'lorem ipsemalkdkslaasd',' ','text','lorem ipsim...', 'First Lesson');
INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3002L,2001L,'lorem ipsemalkdkslaasd',' ','text','lorem ipsim...', 'Second Lesson');


INSERT INTO article (id,section_id,description,media_link,media_type,short_description,title)
VALUES (3003L,2002L,'lorem ipsemalkdkslaasd','https://www.youtube.com/watch?v=5c5P8YQeymI ','video','lorem ipsim...', 'Second Lesson');