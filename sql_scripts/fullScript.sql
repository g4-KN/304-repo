/* Database Script - Coffee Shop Application*/

/* Create Tables */
CREATE TABLE PostalCodeReference
(PostalCode CHAR(6), City VARCHAR(50), Province VARCHAR(50), PRIMARY KEY (PostalCode));
CREATE TABLE Address
(HouseNo INTEGER, Street VARCHAR(50), PostalCode CHAR(6),
PRIMARY KEY (HouseNo, Street, PostalCode),
FOREIGN KEY (PostalCode) REFERENCES PostalCodeReference(PostalCode));

CREATE TABLE Store
(StoreId INTEGER, HouseNo INTEGER, Street VARCHAR(50), PostalCode CHAR(6), PRIMARY KEY (StoreId),
FOREIGN KEY (HouseNo, Street, PostalCode) REFERENCES Address(HouseNo, Street, PostalCode));

CREATE TABLE Employee
(SinNo INTEGER, Salary DECIMAL(6,2), Name VARCHAR(50), Phone BIGINT, StoreId INTEGER, Since DATE, HouseNo INTEGER, PostalCode CHAR(6), Street VARCHAR(50), PRIMARY KEY (SinNo),
FOREIGN KEY (StoreId) REFERENCES Store(StoreId),
FOREIGN KEY (HouseNo, Street, PostalCode) REFERENCES Address(HouseNo, Street, PostalCode));

CREATE TABLE Manager
(SinNo INTEGER, StoreId INTEGER, ManagerSince DATE, PRIMARY KEY (SinNo),
FOREIGN KEY (SinNo) REFERENCES Employee(SinNo), FOREIGN KEY (StoreId) REFERENCES Store(StoreId));

CREATE TABLE Member
(MemberId INTEGER, PointBalance INTEGER, Name VARCHAR(50), Phone BIGINT, HouseNo INTEGER, Street VARCHAR(50), PostalCode CHAR(6),
PRIMARY KEY (MemberId),
FOREIGN KEY (HouseNo, Street, PostalCode) REFERENCES Address(HouseNo, Street, PostalCode));

CREATE TABLE Food
(FoodName VARCHAR(50), price DECIMAL(6,2), pointCost INTEGER, pointReward INTEGER, PRIMARY KEY (FoodName));

CREATE TABLE Drink
(DrinkName VARCHAR(50), Size VARCHAR(50), price DECIMAL(6,2), pointCost INTEGER, pointReward INTEGER,
PRIMARY KEY (DrinkName, Size));

CREATE TABLE FoodMenu
(StoreId INTEGER, FoodName VARCHAR(50),
PRIMARY KEY (StoreId, FoodName),
FOREIGN KEY (StoreId) REFERENCES Store(StoreId), FOREIGN KEY (FoodName) REFERENCES Food(FoodName));

CREATE TABLE DrinkMenu
(StoreId INTEGER, DrinkName VARCHAR(50), Size VARCHAR(50), PRIMARY KEY (StoreId, DrinkName, Size),
FOREIGN KEY (StoreId) REFERENCES Store(StoreId),
FOREIGN KEY (DrinkName, Size) REFERENCES Drink(DrinkName, Size));

CREATE TABLE Transaction
(TransactionNo INTEGER, StoreId INTEGER, pointsGenerated INTEGER, Date DATE, MemberId INTEGER, SinNo INTEGER, moneyCost DECIMAL(6,2), pointCost INTEGER,
/*CHECK (pointsGenerated > 0), */PRIMARY KEY (TransactionNo),
FOREIGN KEY (StoreId) REFERENCES Store(StoreId),
FOREIGN KEY (MemberId) REFERENCES Member(MemberId),
FOREIGN KEY (SinNo) REFERENCES Employee(SinNo));

CREATE TABLE ContainsFood
(TransactionNo INTEGER, FoodName VARCHAR(50), quantity INTEGER, PRIMARY KEY (TransactionNo, FoodName),
FOREIGN KEY (TransactionNo) REFERENCES Transaction(TransactionNo) ON DELETE CASCADE, FOREIGN KEY (FoodName) REFERENCES Food(FoodName));

CREATE TABLE ContainsDrink
(TransactionNo INTEGER, DrinkName VARCHAR(50), Size VARCHAR(50), quantity INTEGER, PRIMARY KEY (TransactionNo, DrinkName, Size),
FOREIGN KEY (TransactionNo) REFERENCES Transaction(TransactionNo),
FOREIGN KEY (DrinkName, Size) REFERENCES Drink(DrinkName, Size));

/* Populate Tables */

insert into PostalCodeReference (PostalCode , City , Province) values
('V1P1D7', 'Vancouver', 'BC'),
('V2P1E5', 'Vancouver', 'BC'),
('V3P1F7', 'Vancouver', 'BC'),
('V4L1G9', 'Vancouver', 'BC'),
('V5R4F3', 'Vancouver', 'BC'),
('V2P1D7', 'Vancouver', 'BC'),
('V3P1E5', 'Vancouver', 'BC'),
('V4P1F7', 'Vancouver', 'BC'),
('V5L1G9', 'Vancouver', 'BC'),
('V6R4F3', 'Vancouver', 'BC'),
('V3P1D7', 'Vancouver', 'BC'),
('V4P1E5', 'Vancouver', 'BC'),
('V5P1F7', 'Vancouver', 'BC'),
('V6L1G9', 'Vancouver', 'BC'),
('V7R4F3', 'Vancouver', 'BC');

insert into Address (HouseNo, Street, PostalCode) values
(1234, 'Vine Street', 'V1P1D7'),
(2345, 'Yew Street', 'V2P1E5'),
(3456, 'Balsam Street', 'V3P1F7'),
(4567, 'Granville Street', 'V4L1G9'),
(5678, 'Oak Street', 'V5R4F3'),
(6789, 'Vine Street', 'V2P1D7'),
(7890, 'Yew Street', 'V3P1E5'),
(8901, 'Balsam Street', 'V4P1F7'),
(9012, 'Granville Street', 'V5L1G9'),
(9876, 'Oak Street', 'V6R4F3'),
(8765, 'Vine Street', 'V3P1D7'),
(7654, 'Yew Street', 'V4P1E5'),
(6543, 'Balsam Street', 'V5P1F7'),
(5432, 'Granville Street', 'V6L1G9'),
(4321, 'Oak Street', 'V7R4F3');

insert into Store (StoreId, HouseNo , Street, PostalCode) values
(1, 1234, 'Vine Street', 'V1P1D7'),
(2, 2345, 'Yew Street', 'V2P1E5'),
(3, 3456, 'Balsam Street', 'V3P1F7'),
(4, 4567, 'Granville Street', 'V4L1G9'),
(5, 5678, 'Oak Street', 'V5R4F3');

insert into Employee (SinNo, Salary , Name , Phone, StoreId, Since, HouseNo, PostalCode , Street) values
(111, 3000, 'Ken', 7781111111, 1, '2005-5-23', 6789, 'V2P1D7', 'Vine Street'),
(222, 3000, 'Jen', 7781111112, 2, '2006-5-23', 8765, 'V3P1D7', 'Vine Street'),
(333, 3000, 'Ben', 7781111113, 3, '2007-5-23', 7654, 'V4P1E5', 'Yew Street'),
(444, 3000, 'Chris', 7781111114, 4, '2008-5-23', 6543, 'V5P1F7', 'Balsam Street'),
(555, 3000, 'Rane', 7781111115, 5, '2009-5-23',  5432, 'V6L1G9', 'Granville Street'),
(666, 3000, 'Bob', 7781111116, 1, '2009-5-23',  6789, 'V2P1D7', 'Vine Street');

insert into Manager (SinNo, StoreId, ManagerSince)
values
(111, 1,  '2005-5-23'),
(222, 2,  '2006-5-23'),
(333, 3,  '2007-5-23'),
(444, 4,  '2008-5-23'),
(555, 5,  '2009-5-23');

insert into Member ( MemberId, PointBalance, Name, Phone , HouseNo, Street, PostalCode)
values
(1, 200, 'Peter', 7782222222, 7890, 'Yew Street', 'V3P1E5'),
(2, 350, 'James', 7783333333, 8901, 'Balsam Street', 'V4P1F7'),
(3, 400, 'Cindy', 7784444444, 9012, 'Granville Street', 'V5L1G9'),
(4, 550, 'Simon', 7785555555, 9876, 'Oak Street', 'V6R4F3'),
(5, 600, 'Eric', 7786666666, 8765, 'Vine Street', 'V3P1D7');

insert into Food ( FoodName, price, pointCost, pointReward)
values
('Muffin', 3.00, 30, 3),
('Cookie', 1.00, 10, 1),
('Scon', 3.00, 30, 3),
('Sandwich', 4.00, 40, 4),
('Hot Dog', 5.00, 50, 5);

insert into Drink (DrinkName, Size, price, pointCost, pointReward)
values
('Tea', 'Large', 4.00, 40, 4),
('Coffee', 'Large', 3.00, 30, 3),
('Hot Chocolate', 'Large', 4.00, 40, 4),
('Smoothy', 'Large', 4.00, 40, 4),
('Expresso', 'Small', 3.00, 30, 3),
('Bepsi', 'Small', 1.00, 10, 1),
('Beer', 'Large', 3.00, 30, 3),
('Dk Pepper', 'Large', 1.00, 10, 1),
('Green Tea', 'Large', 2.00, 20, 2),
('Orange Pop', 'Small', 1.00, 10, 1);

insert into FoodMenu (StoreId, FoodName)
values
(1, 'Muffin'),
(1, 'Cookie'),
(1, 'Scon'),
(1, 'Sandwich'),
(1, 'Hot Dog'),
(2, 'Muffin'),
(2, 'Cookie'),
(2, 'Scon'),
(2, 'Sandwich'),
(2, 'Hot Dog'),
(3, 'Muffin'),
(3, 'Cookie'),
(3, 'Scon'),
(3, 'Sandwich'),
(3, 'Hot Dog'),
(4, 'Muffin'),
(4, 'Cookie'),
(4, 'Scon'),
(4, 'Sandwich'),
(4, 'Hot Dog'),
(5, 'Muffin'),
(5, 'Cookie'),
(5, 'Scon'),
(5, 'Sandwich'),
(5, 'Hot Dog');

insert into DrinkMenu (StoreId, DrinkName, Size)
values
(1, 'Tea', 'Large'),
(1, 'Beer', 'Large'),
(1, 'Dk Pepper', 'Large'),
(1, 'Bepsi', 'Small'),
(2, 'Coffee', 'Large'),
(2, 'Orange Pop', 'Small'),
(2, 'Green Tea', 'Large'),
(2, 'Bepsi', 'Small'),
(3, 'Hot Chocolate', 'Large'),
(3, 'Dk Pepper', 'Large'),
(3, 'Bepsi', 'Small'),
(3, 'Coffee', 'Large'),
(4, 'Smoothy', 'Large'),
(4, 'Orange Pop', 'Small'),
(4, 'Green Tea', 'Large'),
(4, 'Bepsi', 'Small'),
(5, 'Expresso', 'Small'),
(5, 'Beer', 'Large'),
(5, 'Dk Pepper', 'Large'),
(5, 'Bepsi', 'Small'),
(5, 'Coffee', 'Large');

insert into Transaction (TransactionNo, StoreId, pointsGenerated, Date, MemberId, SinNo, moneyCost, pointCost)
values
 (1, 1, 3, '2015-2-13', 1, 111, 3.00, 0),
 (2, 1, 3, '2015-2-13', 4, 111, 0.00, 30),
 (3, 1, 3, '2015-2-13', 1, 111, 3.00, 0),
 (4, 1, 3, '2015-2-13', 2, 111, 3.00, 0),
 (5, 1, 3, '2015-2-13', 2, 111, 3.00, 0),
 (6, 1, 4, '2015-2-23', 3, 111, 4.00, 0),
 (7, 1, 4, '2015-2-23', 5, 111, 0, 40),
 (8, 2, 4, '2015-2-23', 4, 222, 4.00, 0),
 (9, 2, 4, '2015-2-23', 2, 222, 4.00, 0),
 (10, 2, 4, '2015-3-13', 5, 222, 4.00, 0),
 (11, 2, 1, '2015-3-14', 1, 222, 1.00, 0),
 (12, 2, 3, '2015-3-14', 4, 222, 3.00, 0),
 (13, 2, 8, '2015-3-14', 3, 222, 8.00, 0),
 (14, 2, 8, '2015-3-14', 1, 222, 0.00, 80),
 (15, 2, 10, '2015-3-15', 1, 222, 10.00, 0),
 (16, 3, 8, '2015-3-15', 1, 333, 8.00, 0),
 (17, 3, 15, '2015-3-18', 3, 333, 15.00, 0),
 (18, 5, 6, '2015-3-18', 2, 555, 0.00, 60),
 (19, 2, 4, '2015-3-24', 4, 222, 4.00, 0),
 (20, 1, 18, '2015-3-24', 2, 666, 18.00, 0),
 (21, 1, 11, '2015-3-24', 1, 111, 11.00, 0),
 (22, 1, 33, '2015-3-24', 5, 111, 0.00, 330),
 (23, 2, 15, '2015-3-24', 3, 222, 15.00, 0),
 (24, 3, 53, '2015-3-24', 5, 333, 53.00, 0),
 (25, 3, 3, '2015-3-26', 4, 333, 3.00, 0),
 (26, 4, 30, '2015-3-26', 1, 444, 30.00, 0),
 (27, 1, 18, '2015-3-26', 3, 111, 0, 180),
 (28, 4, 60, '2015-3-27', 4, 444, 60.00, 0),
 (29, 1, 1, '2015-3-27', 2, 666, 1.00, 0),
 (30, 1, 8, '2015-3-28', 5, 111, 8.00, 0),
 (31, 3, 8, '2015-3-28', 1, 333, 8.00, 0),
 (32, 5, 15, '2015-3-29', 4, 555, 0.00, 150),
 (33, 1, 10, '2015-3-29', 1, 111, 10.00, 0),
 (34, 2, 8, '2015-3-30', 3, 222, 8.00, 0),
 (35, 3, 7, '2015-3-30', 4, 333, 7.00, 0),
 (36, 1, 4, '2015-3-30', 3, 111, 4.00, 0),
 (37, 2, 3, '2015-3-30', 2, 222, 0, 30),
 (38, 2, 1, '2015-4-01', 4, 222, 1.00, 0),
 (39, 1, 15, '2015-4-01', 2, 111, 15.00, 0),
 (40, 5, 9, '2015-4-01', 1, 555, 9.00, 0);

insert into ContainsFood (TransactionNo, FoodName, quantity)
values
(1, 'Muffin', 1),
(2, 'Muffin', 1),
(3, 'Muffin', 1),
(4, 'Muffin', 1),
(5, 'Muffin', 1),
(11, 'Cookie', 1),
(12, 'Scon', 1),
(13, 'Sandwich', 2),
(14, 'Sandwich', 1),
(14, 'Scon', 1),
(14, 'Hot Dog', 1),
(15, 'Muffin', 1),
(15, 'Cookie', 2),
(15, 'Sandwich', 1),
(16, 'Hot Dog', 1),
(17, 'Cookie', 1),
(17, 'Sandwich', 1),
(17, 'Scon', 1),
(17, 'Muffin', 1),
(18, 'Muffin', 1),
(19, 'Scon', 1),
(20, 'Cookie', 10),
(21, 'Sandwich', 1),
(21, 'Scon', 1),
(21, 'Muffin', 1),
(22, 'Muffin', 1),
(23, 'Muffin', 1),
(23, 'Cookie', 4),
(24, 'Muffin', 1),
(24, 'Hot Dog', 10),
(25, 'Muffin', 1),
(26, 'Scon', 2),
(26, 'Sandwich', 2),
(26, 'Muffin', 2),
(26, 'Hot Dog', 2),
(27, 'Muffin', 1),
(27, 'Cookie', 10),
(28, 'Scon', 20);

insert into ContainsDrink (TransactionNo, DrinkName, Size, quantity)
values
(6, 'Tea', 'Large', 1),
(7, 'Tea', 'Large', 1),
(8, 'Tea', 'Large', 1),
(9, 'Tea', 'Large', 1),
(10, 'Tea', 'Large', 1),
(16, 'Coffee', 'Large', 1),
(17, 'Hot Chocolate', 'Large', 1),
(18, 'Expresso', 'Small', 1),
(19, 'Orange Pop', 'Small', 1),
(20, 'Tea', 'Large', 2),
(21, 'Bepsi', 'Small', 1),
(22, 'Beer', 'Large', 10),
(23, 'Green Tea', 'Large', 4),
(27, 'Dk Pepper', 'Large', 1),
(27, 'Tea', 'Large', 1),
(29, 'Bepsi', 'Small', 1),
(30, 'Tea', 'Large', 1),
(30, 'Beer', 'Large', 1),
(30, 'Bepsi', 'Small', 1),
(31, 'Hot Chocolate', 'Large', 1),
(31, 'Coffee', 'Large', 1),
(31, 'Bepsi', 'Small', 1),
(32, 'Expresso', 'Small', 5),
(33, 'Bepsi', 'Small', 10),
(34, 'Green Tea', 'Large', 1),
(34, 'Coffee', 'Large', 2),
(35, 'Coffee', 'Large', 1),
(35, 'Hot Chocolate', 'Large', 1),
(35, 'Dk Pepper', 'Large', 1),
(36, 'Beer', 'Large', 1),
(36, 'Dk Pepper', 'Large', 1),
(37, 'Bepsi', 'Small', 1),
(37, 'Green Tea', 'Large', 1),
(38, 'Orange Pop', 'Small', 1),
(39, 'Beer', 'Large', 5),
(40, 'Beer', 'Large', 1),
(40, 'Expresso', 'Small', 1),
(40, 'Coffee', 'Large', 1);