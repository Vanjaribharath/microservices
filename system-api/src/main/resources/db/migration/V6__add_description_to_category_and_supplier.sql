ALTER TABLE categories
ADD COLUMN description VARCHAR(255);

ALTER TABLE suppliers
ADD COLUMN description VARCHAR(255);

UPDATE categories
SET description = name;

UPDATE suppliers
SET description = name;