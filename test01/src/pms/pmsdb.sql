-- 클래스
DROP TABLE IF EXISTS CLASS RESTRICT;

-- 프로젝트
DROP TABLE IF EXISTS PROJECTS RESTRICT;

-- 멤버
DROP TABLE IF EXISTS MEMBERS RESTRICT;

-- 프로젝트매니저
DROP TABLE IF EXISTS MANAGERS RESTRICT;

-- 팀
DROP TABLE IF EXISTS TEAMS RESTRICT;

-- 평가
DROP TABLE IF EXISTS SCORES RESTRICT;

-- 강의실
DROP TABLE IF EXISTS ROOMS RESTRICT;

-- 과정
DROP TABLE IF EXISTS LECTURES RESTRICT;

-- 프로젝트매니저배치
DROP TABLE IF EXISTS MA_ASSIGN RESTRICT;

-- 주소
DROP TABLE IF EXISTS ADDRS RESTRICT;

-- 작업
DROP TABLE IF EXISTS TASKS RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS BOARDS RESTRICT;

-- 클래스
CREATE TABLE CLASS (
  PMNO   INTEGER      NOT NULL COMMENT '클래스번호', -- 클래스번호
  RNO    INTEGER      NULL     COMMENT '강의장코드', -- 강의장코드
  CNM    VARCHAR(100) NOT NULL COMMENT '과정명', -- 과정명
  NUM    VARCHAR(100) NOT NULL COMMENT '기수', -- 기수
  TOT_DT INTEGER      NULL     COMMENT '총기간' -- 총기간
)
COMMENT '클래스';

-- 클래스
ALTER TABLE CLASS
  ADD CONSTRAINT PK_CLASS -- 클래스 기본키
    PRIMARY KEY (
      PMNO -- 클래스번호
    );

ALTER TABLE CLASS
  MODIFY COLUMN PMNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '클래스번호';

-- 프로젝트
CREATE TABLE PROJECTS (
  PNO   INTEGER      NOT NULL COMMENT '프로젝트번호', -- 프로젝트번호
  MANO  INTEGER      NULL     COMMENT '매니저번호', -- 매니저번호
  PMNO  INTEGER      NULL     COMMENT '클래스번호', -- 클래스번호
  PNM   VARCHAR(100) NOT NULL COMMENT '프로젝트명', -- 프로젝트명
  TNM   VARCHAR(100) NOT NULL COMMENT '팀명', -- 팀명
  DESCT TEXT         NOT NULL COMMENT '프로젝트내용', -- 프로젝트내용
  ST_DT DATE         NOT NULL COMMENT '프로젝트시작일', -- 프로젝트시작일
  ED_DT DATE         NOT NULL COMMENT '프로젝트종료일', -- 프로젝트종료일
  STAT  CHAR(1)      NOT NULL COMMENT '프로젝트상태', -- 프로젝트상태
  SCOR  INTEGER      NULL     COMMENT '점수' -- 점수
)
COMMENT '프로젝트';

-- 프로젝트
ALTER TABLE PROJECTS
  ADD CONSTRAINT PK_PROJECTS -- 프로젝트 기본키
    PRIMARY KEY (
      PNO -- 프로젝트번호
    );

-- 프로젝트 유니크 인덱스
CREATE UNIQUE INDEX UIX_PROJECTS
  ON PROJECTS ( -- 프로젝트
    PNM ASC, -- 프로젝트명
    TNM ASC  -- 팀명
  );

ALTER TABLE PROJECTS
  MODIFY COLUMN PNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '프로젝트번호';

-- 멤버
CREATE TABLE MEMBERS (
  MNO      INTEGER      NOT NULL COMMENT '멤버번호', -- 멤버번호
  PNO      INTEGER      NULL     COMMENT '프로젝트번호', -- 프로젝트번호
  MNM      VARCHAR(100) NOT NULL COMMENT '이름', -- 이름
  POSNO    VARCHAR(10)  NULL     COMMENT '우편번호', -- 우편번호
  BAS_ADDR VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
  M_ADDR   VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  TEL      VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
  EMAIL    VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  ROLE     VARCHAR(100) NULL     COMMENT '역할' -- 역할
)
COMMENT '멤버';

-- 멤버
ALTER TABLE MEMBERS
  ADD CONSTRAINT PK_MEMBERS -- 멤버 기본키
    PRIMARY KEY (
      MNO -- 멤버번호
    );

-- 멤버 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBERS
  ON MEMBERS ( -- 멤버
    EMAIL ASC -- 이메일
  );

ALTER TABLE MEMBERS
  MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '멤버번호';

-- 프로젝트매니저
CREATE TABLE MANAGERS (
  MANO  INTEGER      NOT NULL COMMENT '매니저번호', -- 매니저번호
  TEL   VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
  EMAIL VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  MANM  VARCHAR(100) NOT NULL COMMENT '이름' -- 이름
)
COMMENT '프로젝트매니저';

-- 프로젝트매니저
ALTER TABLE MANAGERS
  ADD CONSTRAINT PK_MANAGERS -- 프로젝트매니저 기본키
    PRIMARY KEY (
      MANO -- 매니저번호
    );

-- 프로젝트매니저 유니크 인덱스
CREATE UNIQUE INDEX UIX_MANAGERS
  ON MANAGERS ( -- 프로젝트매니저
    EMAIL ASC -- 이메일
  );

ALTER TABLE MANAGERS
  MODIFY COLUMN MANO INTEGER NOT NULL AUTO_INCREMENT COMMENT '매니저번호';

-- 팀
CREATE TABLE TEAMS (
  TNO  INTEGER      NOT NULL COMMENT '팀번호', -- 팀번호
  PMNO INTEGER      NOT NULL COMMENT '클래스번호', -- 클래스번호
  PNO  INTEGER      NULL     COMMENT '프로젝트번호', -- 프로젝트번호
  TNM  VARCHAR(100) NOT NULL COMMENT '팀명' -- 팀명
)
COMMENT '팀';

-- 팀
ALTER TABLE TEAMS
  ADD CONSTRAINT PK_TEAMS -- 팀 기본키
    PRIMARY KEY (
      TNO -- 팀번호
    );

-- 팀 유니크 인덱스
CREATE UNIQUE INDEX UIX_TEAMS
  ON TEAMS ( -- 팀
    TNM ASC -- 팀명
  );

-- 평가
CREATE TABLE SCORES (
  SNO INTEGER NOT NULL COMMENT '평가번호', -- 평가번호
  PNO INTEGER NOT NULL COMMENT '프로젝트번호' -- 프로젝트번호
)
COMMENT '평가';

-- 평가
ALTER TABLE SCORES
  ADD CONSTRAINT PK_SCORES -- 평가 기본키
    PRIMARY KEY (
      SNO -- 평가번호
    );

-- 강의실
CREATE TABLE ROOMS (
  RNO INTEGER      NOT NULL COMMENT '강의장코드', -- 강의장코드
  RNM VARCHAR(100) NOT NULL COMMENT '강의실명' -- 강의실명
)
COMMENT '강의실';

-- 강의실
ALTER TABLE ROOMS
  ADD CONSTRAINT PK_ROOMS -- 강의실 기본키
    PRIMARY KEY (
      RNO -- 강의장코드
    );

-- 강의실 유니크 인덱스
CREATE UNIQUE INDEX UIX_ROOMS
  ON ROOMS ( -- 강의실
    RNM ASC -- 강의실명
  );

-- 과정
CREATE TABLE LECTURES (
  LNO INTEGER NOT NULL COMMENT '과정번호', -- 과정번호
  RNO INTEGER NULL     COMMENT '강의장코드' -- 강의장코드
)
COMMENT '과정';

-- 과정
ALTER TABLE LECTURES
  ADD CONSTRAINT PK_LECTURES -- 과정 기본키
    PRIMARY KEY (
      LNO -- 과정번호
    );

-- 프로젝트매니저배치
CREATE TABLE MA_ASSIGN (
  MAMNO INTEGER NOT NULL COMMENT '매니저배치번호', -- 매니저배치번호
  MANO  INTEGER NOT NULL COMMENT '매니저번호', -- 매니저번호
  TNO   INTEGER NOT NULL COMMENT '팀번호', -- 팀번호
  ST_DT DATE    NULL     COMMENT '프로젝트시작일', -- 프로젝트시작일
  ED_DT DATE    NULL     COMMENT '프로젝트종료일', -- 프로젝트종료일
  DESCT TEXT    NULL     COMMENT '프로젝트내용', -- 프로젝트내용
  HR    INTEGER NULL     COMMENT '프로젝트시간' -- 프로젝트시간
)
COMMENT '프로젝트매니저배치';

-- 프로젝트매니저배치
ALTER TABLE MA_ASSIGN
  ADD CONSTRAINT PK_MA_ASSIGN -- 프로젝트매니저배치 기본키
    PRIMARY KEY (
      MAMNO -- 매니저배치번호
    );

-- 주소
CREATE TABLE ADDRS (
  COL INTEGER NOT NULL COMMENT '주소번호' -- 주소번호
)
COMMENT '주소';

-- 주소
ALTER TABLE ADDRS
  ADD CONSTRAINT PK_ADDRS -- 주소 기본키
    PRIMARY KEY (
      COL -- 주소번호
    );

-- 작업
CREATE TABLE TASKS (
  TNO   INTEGER      NOT NULL COMMENT '작업번호', -- 작업번호
  PNO   INTEGER      NULL     COMMENT '프로젝트번호', -- 프로젝트번호
  TNM   VARCHAR(255) NOT NULL COMMENT '작업명', -- 작업명
  CONT  TEXT         NULL     COMMENT '작업내용', -- 작업내용
  ST_DT DATE         NULL     COMMENT '시작일', -- 시작일
  ED_DT DATE         NULL     COMMENT '종료일' -- 종료일
)
COMMENT '작업';

-- 작업
ALTER TABLE TASKS
  ADD CONSTRAINT PK_TASKS -- 작업 기본키
    PRIMARY KEY (
      TNO -- 작업번호
    );

ALTER TABLE TASKS
  MODIFY COLUMN TNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '작업번호';

-- 공지사항
CREATE TABLE BOARDS (
  BNO   INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  MANO  INTEGER      NULL     COMMENT '매니저번호', -- 매니저번호
  CR_DT DATE         NOT NULL COMMENT '생성일', -- 생성일
  VIEWS INTEGER      NULL     COMMENT '조회수', -- 조회수
  CONTS TEXT         NOT NULL COMMENT '내용', -- 내용
  TITLE VARCHAR(100) NOT NULL COMMENT '제목', -- 제목
  WRT   VARCHAR(100) NOT NULL COMMENT '생성자' -- 생성자
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE BOARDS
  ADD CONSTRAINT PK_BOARDS -- 공지사항 기본키
    PRIMARY KEY (
      BNO -- 게시판번호
    );

ALTER TABLE BOARDS
  MODIFY COLUMN BNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

-- 클래스
ALTER TABLE CLASS
  ADD CONSTRAINT FK_ROOMS_TO_CLASS -- 강의실 -> 클래스
    FOREIGN KEY (
      RNO -- 강의장코드
    )
    REFERENCES ROOMS ( -- 강의실
      RNO -- 강의장코드
    );

-- 프로젝트
ALTER TABLE PROJECTS
  ADD CONSTRAINT FK_MANAGERS_TO_PROJECTS -- 프로젝트매니저 -> 프로젝트
    FOREIGN KEY (
      MANO -- 매니저번호
    )
    REFERENCES MANAGERS ( -- 프로젝트매니저
      MANO -- 매니저번호
    );

-- 프로젝트
ALTER TABLE PROJECTS
  ADD CONSTRAINT FK_CLASS_TO_PROJECTS -- 클래스 -> 프로젝트
    FOREIGN KEY (
      PMNO -- 클래스번호
    )
    REFERENCES CLASS ( -- 클래스
      PMNO -- 클래스번호
    );

-- 멤버
ALTER TABLE MEMBERS
  ADD CONSTRAINT FK_PROJECTS_TO_MEMBERS -- 프로젝트 -> 멤버
    FOREIGN KEY (
      PNO -- 프로젝트번호
    )
    REFERENCES PROJECTS ( -- 프로젝트
      PNO -- 프로젝트번호
    );

-- 팀
ALTER TABLE TEAMS
  ADD CONSTRAINT FK_CLASS_TO_TEAMS -- 클래스 -> 팀
    FOREIGN KEY (
      PMNO -- 클래스번호
    )
    REFERENCES CLASS ( -- 클래스
      PMNO -- 클래스번호
    );

-- 팀
ALTER TABLE TEAMS
  ADD CONSTRAINT FK_PROJECTS_TO_TEAMS -- 프로젝트 -> 팀
    FOREIGN KEY (
      PNO -- 프로젝트번호
    )
    REFERENCES PROJECTS ( -- 프로젝트
      PNO -- 프로젝트번호
    );

-- 평가
ALTER TABLE SCORES
  ADD CONSTRAINT FK_PROJECTS_TO_SCORES -- 프로젝트 -> 평가
    FOREIGN KEY (
      PNO -- 프로젝트번호
    )
    REFERENCES PROJECTS ( -- 프로젝트
      PNO -- 프로젝트번호
    );

-- 과정
ALTER TABLE LECTURES
  ADD CONSTRAINT FK_ROOMS_TO_LECTURES -- 강의실 -> 과정
    FOREIGN KEY (
      RNO -- 강의장코드
    )
    REFERENCES ROOMS ( -- 강의실
      RNO -- 강의장코드
    );

-- 프로젝트매니저배치
ALTER TABLE MA_ASSIGN
  ADD CONSTRAINT FK_MANAGERS_TO_MA_ASSIGN -- 프로젝트매니저 -> 프로젝트매니저배치
    FOREIGN KEY (
      MANO -- 매니저번호
    )
    REFERENCES MANAGERS ( -- 프로젝트매니저
      MANO -- 매니저번호
    );

-- 프로젝트매니저배치
ALTER TABLE MA_ASSIGN
  ADD CONSTRAINT FK_TEAMS_TO_MA_ASSIGN -- 팀 -> 프로젝트매니저배치
    FOREIGN KEY (
      TNO -- 팀번호
    )
    REFERENCES TEAMS ( -- 팀
      TNO -- 팀번호
    );

-- 작업
ALTER TABLE TASKS
  ADD CONSTRAINT FK_PROJECTS_TO_TASKS -- 프로젝트 -> 작업
    FOREIGN KEY (
      PNO -- 프로젝트번호
    )
    REFERENCES PROJECTS ( -- 프로젝트
      PNO -- 프로젝트번호
    );

-- 공지사항
ALTER TABLE BOARDS
  ADD CONSTRAINT FK_MANAGERS_TO_BOARDS -- 프로젝트매니저 -> 공지사항
    FOREIGN KEY (
      MANO -- 매니저번호
    )
    REFERENCES MANAGERS ( -- 프로젝트매니저
      MANO -- 매니저번호
    );