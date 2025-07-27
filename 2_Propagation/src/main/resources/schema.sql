
CREATE TABLE IF NOT EXISTS account
(
    id      SERIAL       PRIMARY KEY,
    owner   VARCHAR(100) NOT NULL,
    balance INTEGER      NOT NULL,
    created_at TIMESTAMP DEFAULT now()
    );

CREATE OR REPLACE VIEW v_account_log AS
SELECT id, owner, balance, created_at
FROM account
ORDER BY id;

INSERT INTO account (owner, balance) VALUES
    ('alice', 500),
    ('bob',   400),
    ('carol', 300);


