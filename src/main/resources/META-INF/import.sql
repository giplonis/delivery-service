-- PackageType info
insert into PACKAGE_TYPE (ID, TITLE, DESCRIPTION) values(1, 'Document', 'Send a document');
insert into PACKAGE_TYPE (ID, TITLE, DESCRIPTION) values(2, 'Package', 'Send a regular package');

-- Address info
insert into ADDRESS (ID, CITY, STREET) values (1, 'Vilnius', 'Antakalnio g., 32');

-- PackageSize info
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (1, 'Small Box', 450, 350, 160, 2000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (2, 'Medium Box', 610, 460, 460, 20000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (3, 'Large Box', 1500, 1200, 800, 30000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (4, 'Small Letter', 240, 165, 5, 100);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (5, 'Large Letter', 353, 250, 25, 750);

-- Document options
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (1, 1, 4, 80);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (2, 1, 5, 120);

-- Package options
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (3, 2, 1, 150);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE, FRAGILE) values (4, 2, 2, 500, FALSE);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE, FRAGILE) values (5, 2, 3, 1000, FALSE);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE, FRAGILE) values (6, 2, 1, 300, TRUE);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE, FRAGILE) values (7, 2, 2, 650, TRUE);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE, FRAGILE) values (8, 2, 3, 1150, TRUE);

-- Orders
-- todo: write order seed

-- broken (below)
-- insert into `ORDER` (ID, CREATED_AT, STATUS, PICKUP_DATE_TIME, TOTAL_PRICE, PACKAGE_OPTION_ID, SENDER_INFO_ID, RECIPIENT_INFO_ID, USER_ID) values (1,'2021-04-29T14:26:16+00:00','NEW','2021-04-29T14:26:16+00:00',420.69,1,1,1,1);