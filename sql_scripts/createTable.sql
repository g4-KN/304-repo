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
PRIMARY KEY (TransactionNo),
FOREIGN KEY (StoreId) REFERENCES Store(StoreId),
FOREIGN KEY (MemberId) REFERENCES Member(MemberId),
FOREIGN KEY (SinNo) REFERENCES Employee(SinNo));

CREATE TABLE ContainsFood
(TransactionNo INTEGER, FoodName VARCHAR(50), quantity INTEGER, PRIMARY KEY (TransactionNo, FoodName),
FOREIGN KEY (TransactionNo) REFERENCES Transaction(TransactionNo) ON DELETE CASCADE, FOREIGN KEY (FoodName) REFERENCES Food(FoodName));

CREATE TABLE ContainsDrink
(TransactionNo INTEGER, DrinkName VARCHAR(50), Size VARCHAR(50), quantity INTEGER, PRIMARY KEY (TransactionNo, DrinkName, Size),
FOREIGN KEY (TransactionNo) REFERENCES Transaction(TransactionNo) ON DELETE CASCADE,
FOREIGN KEY (DrinkName, Size) REFERENCES Drink(DrinkName, Size));

