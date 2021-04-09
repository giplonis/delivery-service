-- PackageType info
insert into PACKAGE_TYPE (ID, TITLE, DESCRIPTION) values(1, 'Document', 'Send a document');
insert into PACKAGE_TYPE (ID, TITLE, DESCRIPTION) values(2, 'Package', 'Send a regular package');

-- Address info
insert into ADDRESS (ID, CITY, STREET) values (1, 'Vilnius', 'Antakalnio g., 32');

-- PackageSize info
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (1, 'S', 450, 350, 160, 2000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (2, 'M', 610, 460, 460, 20000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (3, 'L', 1500, 1200, 800, 30000);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (4, 'S', 240, 165, 5, 100);
insert into PACKAGE_SIZE (ID, TITLE, HEIGHT, LENGTH, WIDTH, MAX_WEIGHT) values (5, 'L', 353, 250, 25, 750);

-- Document options
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (1, 1, 4, 80);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (2, 1, 5, 120);

-- Package options
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (3, 2, 1, 150);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (4, 2, 2, 500);
insert into PACKAGE_OPTION (ID, PACKAGE_TYPE_ID, PACKAGE_SIZE_ID, PRICE) values (5, 2, 3, 1000);
