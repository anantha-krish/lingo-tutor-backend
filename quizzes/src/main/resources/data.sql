
INSERT INTO QUIZ (id,level,name)
VALUES (1001L,'Easy','Quiz1: Foundation');


INSERT INTO MULTI_CHOICE_QUESTION (id,quiz_id,question)
VALUES (2001L,1001L,'What do you say when you meet someone for first time in morning?');

INSERT INTO CHOICE (id,mcq_id,choice_label,choice_value)
values (3001L,2001L,'Good morning','cmd_good_morning');
INSERT INTO CHOICE (id,mcq_id,choice_label,choice_value)
values (3002L,2001L,'Good afternoon','cmd_good_afternoon');
INSERT INTO CHOICE (id,mcq_id,choice_label,choice_value)
values (3003L,2001L,'Good bye','cmd_good_bye');

INSERT INTO answer_key (id,quiz_id,mcq_id,choice_id)
VALUES (4001L,1001L,2001L,3001L);