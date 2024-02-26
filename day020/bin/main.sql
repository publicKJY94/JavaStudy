CREATE TABLE MEMBER(
	MID VARCHAR(15) PRIMARY KEY,
	MPW VARCHAR(20) NOT NULL,
	NAME VARCHAR(15) NOT NULL
);

INSERT INTO MEMBER VALUES();

SELECT * FROM MEMBER;

DROP TABLE MEMBER;

/*MemberDTO
	mid
	mpw
	name
BoardDTO
	bid
	title
	writer
	content
	regdate
로그인
로그아웃
글 작성
	- 반드시 로그인해야함
글 목록 출력
	- 작성자 검색
	- 글 제목 검색
	- 시간순 정렬
	- 글 작성자의 이름을 출력
글 내용 보기
	- 내 글이라면 변경 및 삭제 가능
글 내용 변경
	- 내 글만 변경 가능
글 삭제
	- 내 글만 삭제 가능
*/
----------------------------------------------------

CREATE TABLE BOARD(
	BID INT PRIMARY KEY,
	TITLE VARCHAR(50) NOT NULL,
	WRITER VARCHAR(15) NOT NULL,
	CONTENT VARCHAR(200),
	REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_WRITER FOREIGN KEY(WRITER) REFERENCES MEMBER(MID)
);

INSERT INTO BOARD VALUES(BID,TITLE,WRITER,CONTENT) VALUES((SELECT NVL(MAX(BID),10) + 1 FROM BOARD),'제목','admin','내용');

SELECT * FROM BOARD;

DROP TABLE BOARD;