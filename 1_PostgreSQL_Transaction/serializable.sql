-- Session A
BEGIN ISOLATION LEVEL SERIALIZABLE;

UPDATE bank_account SET balance = balance + 100 WHERE name = 'Alice';
-- Không commit ngay


--- Session B
BEGIN ISOLATION LEVEL SERIALIZABLE;

UPDATE bank_account SET balance = balance + 200 WHERE name = 'Alice';
-- Khi COMMIT sẽ bị lỗi do xung đột serialize
COMMIT;
