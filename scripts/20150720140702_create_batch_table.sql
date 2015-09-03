-- // create batch table
-- Migration SQL that makes the change goes here.

CREATE TABLE batches (
  batch_id    VARCHAR(48) NOT NULL PRIMARY KEY,
  client_name         VARCHAR(48) NOT NULL,
  status      VARCHAR(48) NOT NULL,
  last_update TIMESTAMP   NOT NULL
)
;

INSERT INTO batches (batch_id, client_name, status, last_update)
VALUES
  ('b-fc4', 'Clark Genetico', 'PENDING', now()),
  ('b-ic5', 'Dynadigital', 'PENDING', now()),
  ('b-fma', 'Gas Medical', 'PENDING', now()),
  ('b-a1s', 'Humanoinnovations', 'PENDING', now()),
  ('b-aqz', 'Memory Service Tactical', 'PENDING', now()),
  ('b-9w8', 'Alternative Brokerage', 'PENDING', now())
;

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE batches
;
