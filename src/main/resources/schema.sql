CREATE TABLE ITEM
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    quality INT          NOT NULL,
    sell_in INT          NOT NULL
);