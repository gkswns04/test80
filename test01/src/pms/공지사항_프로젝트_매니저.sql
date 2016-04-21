/*매니저 데이터*/
insert into MANAGERS(TNM,TEL,EMAIL) values ('김매니저','111-1111','kim@test.com');
insert into MANAGERS(TNM,TEL,EMAIL) values ('이매니저','111-1112','lee@test.com');
insert into MANAGERS(TNM,TEL,EMAIL) values ('박매니저','111-1113','park@test.com');
insert into MANAGERS(TNM,TEL,EMAIL) values ('명매니저','111-1114','myeung@test.com');
insert into MANAGERS(TNM,TEL,EMAIL) values ('송매니저','111-1115','song@test.com');


/*공지사항 데이터*/
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-1-1','1월입니다','공지사항1','김매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-2-1','2월입니다','공지사항2','이매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-3-1','3월입니다','공지사항3','박매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-4-1','4월입니다','공지사항4','명매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-5-1','5월입니다','공지사항5','송매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-6-1','6월입니다','공지사항6','김매니저');
insert into BOARDS(CR_DT,CONTS,TITLE,WRT) values ('2015-7-1','7월입니다','공지사항7','이매니저');

/*프로젝트 데이터*/

insert into PROJECTS(PNM,TNM,DESCT,ST_DT,ED_DT,STAT)
  values ('실무형 웹앱 프로젝트','실무형팀','실무형 웹앱 프로젝트를 만든다','2016-1-1','2016-3-30','Y');
insert into PROJECTS(PNM,TNM,DESCT,ST_DT,ED_DT,STAT)
  values ('웹앱 보안 프로그래밍','보안팀','웹앱 보안 프로그래밍을 한다','2016-2-1','2016-4-30','Y');
insert into PROJECTS(PNM,TNM,DESCT,ST_DT,ED_DT,STAT)
  values ('Model2 Programming','Model2 팀','Model2 Programming을 한다','2016-3-1','2016-5-30','Y');
insert into PROJECTS(PNM,TNM,DESCT,ST_DT,ED_DT,STAT)
  values ('JAVA프로젝트','JAVA','JAVA 프로젝트를 만든다','2016-4-1','2016-6-30','Y');
insert into PROJECTS(PNM,TNM,DESCT,ST_DT,ED_DT,STAT)
  values ('Window프로젝트','Window팀','Window 프로젝트를 만든다','2016-5-1','2016-7-30','Y');
