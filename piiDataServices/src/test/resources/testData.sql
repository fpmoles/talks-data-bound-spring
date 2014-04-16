truncate table EMAIL_ADDRESS;
truncate table ADDRESS;
truncate table PHONE_NUMBER;
delete from PERSON;

insert into PERSON (PERSON_ID, PREFIX, FIRST_NAME, MIDDLE_NAME, LAST_NAME, SUFFIX) values ('8824b797-2b05-4166-8b79-3cd479d7ba29', 'MR', 'John', 'Wayne', 'Doe', 'JR');
insert into PERSON (PERSON_ID, PREFIX, FIRST_NAME, MIDDLE_NAME, LAST_NAME, SUFFIX) values ('28221c10-5643-4d9f-907a-2531b9fac74e', 'MRS', 'Jane', 'Samantha', 'Doe', NULL);
insert into PERSON (PERSON_ID, PREFIX, FIRST_NAME, MIDDLE_NAME, LAST_NAME, SUFFIX) values ('a6743c55-56fa-4e5c-b734-f885db9f1c74', 'MISS', 'Alexandra', 'Arista', 'Doe', NULL);



