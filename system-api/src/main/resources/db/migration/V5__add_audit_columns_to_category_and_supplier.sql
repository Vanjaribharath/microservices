ALTER TABLE categories
ADD COLUMN created_at TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP,
ADD COLUMN description VARCHAR(255);

ALTER TABLE suppliers
ADD COLUMN created_at TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP,
ADD COLUMN description VARCHAR(255);

UPDATE categories
SET created_at = NOW(),
    updated_at = NOW(),
    description = name
WHERE created_at IS NULL;

UPDATE suppliers
SET created_at = NOW(),
    updated_at = NOW(),
    description = name
WHERE created_at IS NULL;