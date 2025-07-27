-- Tạo bảng đơn giản
DROP TABLE IF EXISTS bank_account;

CREATE TABLE bank_account (
                              id SERIAL PRIMARY KEY,
                              name TEXT NOT NULL,
                              balance INTEGER NOT NULL
);

-- Dữ liệu mẫu
INSERT INTO bank_account (name, balance) VALUES
                                             ('Alice', 1000),
                                             ('Bob', 1000);
