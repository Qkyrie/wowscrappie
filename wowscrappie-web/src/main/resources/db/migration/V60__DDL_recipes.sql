CREATE TABLE recipes (
  id              BIGINT(20)   NOT NULL AUTO_INCREMENT,
  name            VARCHAR(100) NOT NULL,
  creates_item_id BIGINT(20)   NOT NULL,
  creates_amount  INT          NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  FOREIGN KEY (creates_item_id) REFERENCES item (id)
);

CREATE TABLE recipe_item (
  id        BIGINT(20) NOT NULL AUTO_INCREMENT,
  item_id   BIGINT(20) NOT NULL,
  amount    INT        NOT NULL DEFAULT 1,
  recipe_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (item_id) REFERENCES item (id)
);