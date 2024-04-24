-- DDL 데이터가 들어갈 공간을 정의할때 사용하는 언어
--             == 테이블
-- DML 데이터를 조작하는 언어 ◀

-- CREATE
--  테이블을 생성할때 사용
--  "제약조건" : 해당 컬럼이 ~~해야한다라는 '강제'사항
CREATE TABLE PRODUCT(
	PID		INT				PRIMARY KEY,
	NAME	VARCHAR(15)		NOT NULL,
	PRICE	INT,
	CNT		INT				DEFAULT 10
);

-- DROP
--  테이블을 삭제할때 사용
DROP TABLE PRODUCT;


-- INSERT
--  데이터를 테이블에 추가할때 사용
INSERT INTO PRODUCT VALUES(1,'콜라',1100,1);
INSERT INTO PRODUCT VALUES(1002,'사이다',1200,3);
INSERT INTO PRODUCT (PID,NAME,PRICE) VALUES(1003,'환타',1500);
INSERT INTO PRODUCT VALUES(4,'환타포도',1500,0);

-- SELECT
--  테이블의 데이터를 출력할때 사용
SELECT * FROM PRODUCT;
SELECT PID,NAME FROM PRODUCT;
SELECT PID,NAME FROM PRODUCT WHERE PID>=1002;
SELECT PID,NAME FROM PRODUCT WHERE 3<=CNT AND CNT<=5;
SELECT PID,NAME FROM PRODUCT ORDER BY PID ASC;
SELECT PID,NAME FROM PRODUCT ORDER BY PID DESC;
SELECT PID,NAME FROM PRODUCT WHERE PID>=1002 ORDER BY PID DESC;
SELECT PID,NAME FROM PRODUCT WHERE NAME LIKE '%환타%';

-- UPDATE
--  테이블의 데이터를 변경할때 사용
SELECT PID,NAME,CNT FROM PRODUCT WHERE PID=1001;
UPDATE PRODUCT SET CNT=0 WHERE PID=1001;
UPDATE PRODUCT SET CNT=CNT-1 WHERE PID=1001;

-- DELETE
--  테이블의 데이터를 삭제할때 사용
DELETE FROM PRODUCT WHERE PID=1001;






