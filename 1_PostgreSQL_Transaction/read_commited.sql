--SESSION A
BEGIN ISOLATION LEVEL READ COMMITTED;

UPDATE bank_account SET balance = balance - 100 WHERE name = 'Alice';

-- Chưa COMMIT
SELECT * FROM bank_account WHERE name = 'Alice';

-- Không thấy update của Session A vì chưa commit
SELECT * FROM bank_account WHERE name = 'Alice';


--SESSION B
-- Không thấy update của Session A vì chưa commit
SELECT * FROM bank_account WHERE name = 'Alice';
