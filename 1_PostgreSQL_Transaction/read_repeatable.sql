-- Session A
BEGIN ISOLATION LEVEL REPEATABLE READ;

SELECT * FROM bank_account WHERE name = 'Bob';
-- giữ nguyên ảnh chụp tại thời điểm bắt đầu

--Sesion B
BEGIN;

UPDATE bank_account SET balance = balance + 500 WHERE name = 'Bob';
COMMIT;

--Back to session A
SELECT * FROM bank_account WHERE name = 'Bob';
-- Không thấy sự thay đổi của Session B
COMMIT;
