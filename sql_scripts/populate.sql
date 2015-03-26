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
(1, 100, 'Peter', 7782222222, 7890, 'Yew Street', 'V3P1E5'),
(2, 150, 'James', 7783333333, 7890, 'Yew Street', 'V3P1E5'),
(3, 200, 'Cindy', 7784444444, 7890, 'Yew Street', 'V3P1E5'),
(4, 250, 'Simon', 7785555555, 7890, 'Yew Street', 'V3P1E5'),
(5, 300, 'Eric', 7786666666, 7890, 'Yew Street', 'V3P1E5');

insert into Food ( FoodName, price, pointCost, pointReward)
values
('Muffin', 3, 30, 3),
('Cookie', 3, 30, 3),
('Scon', 3, 30, 3),
('Sandwich', 4, 40, 4),
('Hot Dog', 4, 40, 4);

insert into Drink (DrinkName, Size, price, pointCost, pointReward)
values
('Tea', 'Large', 4, 40, 4),
('Coffee', 'Large', 3, 30, 3),
('Hot Chocolate', 'Large', 4, 40, 4),
('Smoothy', 'Large', 4, 40, 4),
('Expresso', 'Small', 3, 30, 3);

insert into FoodMenu (StoreId, FoodName)
values
(1, 'Muffin'),
(2, 'Cookie'),
(3, 'Scon'),
(4, 'Sandwich'),
(5, 'Hot Dog');
 
insert into DrinkMenu (StoreId, DrinkName, Size)
values
(1, 'Tea', 'Large'),
(2, 'Coffee', 'Large'),
(3, 'Hot Chocolate', 'Large'),
(4, 'Smoothy', 'Large'),
(5, 'Expresso', 'Small');
 
insert into Transaction (TransactionNo, StoreId, pointsGenerated, Date, MemberId, SinNo, moneyCost, pointCost)
values
 (1, 1, 3, '2015-2-13', 1, 111, 3, 0),
 (2, 1, 3, '2015-2-13', 1, 111, 0, 3),
 (3, 1, 3, '2015-2-13', 1, 111, 3, 0),
 (4, 1, 3, '2015-2-13', 2, 111, 3, 0),
 (5, 1, 3, '2015-2-13', 2, 111, 3, 0),
 (6, 1, 4, '2015-2-13', 2, 111, 4, 0),
 (7, 1, 4, '2015-2-13', 2, 111, 0, 4),
 (8, 1, 4, '2015-2-13', 2, 111, 4, 0),
 (9, 1, 4, '2015-2-13', 2, 111, 4, 0),
 (10, 2, 4, '2015-3-23', 2, 111, 4, 0),
 (11, 2, 1, '2015-3-23', 1, 112, 5, 0),
 (12, 2, 2, '2015-3-28', 2, 112, 5, 0),
 (13, 2, 1, '2015-3-28', 2, 112, 5, 3),
 (14, 2, 4, '2015-4-15', 2, 112, 5, 3),
 (15, 2, 4, '2015-4-15', 2, 112, 6, 4),
 (16, 2, 3, '2015-4-15', 2, 112, 6, 0),
 (17, 3, 5, '2015-4-15', 2, 112, 6, 0),
 (18, 3, 5, '2015-4-30', 1, 112, 12, 1),
 (19, 3, 5, '2015-4-30', 2, 121, 13, 1),
 (20, 3, 5, '2015-5-14', 2, 121, 12, 0),
 (21, 3, 5, '2015-5-17', 2, 121, 11, 0),
 (22, 3, 5, '2015-5-18', 2, 121, 10, 4),
 (23, 4, 2, '2015-5-19', 2, 121, 14, 2),
 (24, 4, 2, '2015-7-14', 2, 121, 14, 2),
 (24, 4, 2, '2015-7-22', 1, 131, 3, 5),
 (26, 4, 2, '2015-8-11', 2, 131, 9, 0),
 (27, 4, 2, '2015-10-10', 2, 311, 8, 0),
 (28, 5, 1, '2015-10-23', 2, 311, 4, 5),
 (29, 5, 1, '2015-11-17', 2, 118, 7, 4),
 (30, 5, 1, '2015-12-18', 2, 118, 4, 5),
 (31, 6, 1, '2015-2-25', 2, 118, 7, 5);

insert into ContainsFood (TransactionNo, FoodName, quantity)
values
(1, 'Muffin', 1),
(2, 'Muffin', 1),
(3, 'Muffin', 1),
(4, 'Muffin', 1),
(5, 'Muffin', 1);

insert into ContainsDrink (TransactionNo, DrinkName, Size, quantity)
values
(6, 'Tea', 'Large', 1),
(7, 'Tea', 'Large', 1),
(8, 'Tea', 'Large', 1),
(9, 'Tea', 'Large', 1),
(10, 'Tea', 'Large', 1);
