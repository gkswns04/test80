package bitcamp.pms.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ProjectDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Project;
import bitcamp.pms.util.CommandUtil;
import bitcamp.pms.util.Session;

@Controller
@RequestMapping("project/")
public class ProjectController {
  private ProjectDao projectDao;
  Session session;
  
  public void setProjectDao(ProjectDao projectDao) throws InstantiationException, IllegalAccessException {
    this.projectDao = projectDao;
  }
  
  public void setSession(Session session) {
    this.session = session;
  }

  @RequestMapping("add.do")
  public void add(Scanner keyScan) {       
    
    Project project = new Project();    
    System.out.print("프로젝트명? ");
    project.setProjectName(keyScan.nextLine());
    System.out.print("팀명? ");
    project.setTeamName(keyScan.nextLine());
    System.out.print("시작일? ");
    project.setStartDate(Date.valueOf(keyScan.nextLine()));
    System.out.print("종료일? ");
    project.setEndDate(Date.valueOf(keyScan.nextLine()));
    System.out.print("설명? ");
    project.setDescription(keyScan.nextLine());
    
    if (CommandUtil.confirm(keyScan, "저장하시겠습니까?")) {
      try {
        projectDao.insert(project);
        System.out.println("저장하였습니다.");
        System.out.println("---------------------------------");
      } catch (Exception e) {
        System.out.println("데이터 처리중 오류 발생");
        e.printStackTrace();
      }
    } else {
      System.out.println("취소하였습니다.");
      System.out.println("---------------------------------");
    }
  }
  
  @RequestMapping("delete.do")
  public void delete(Scanner keyScan) {    
    try {
      System.out.print("삭제하고 싶은 프로젝트 번호를 입력하세요. ");
      int no = Integer.parseInt(keyScan.nextLine());
      if (CommandUtil.confirm(keyScan, "삭제 하시겠습니까?")) {
        int count = projectDao.delete(no);
          if (count > 0) {
            System.out.println("저장하였습니다.");
            System.out.println("---------------------------------");    
          } else {
            System.out.println("유효하지 않은 번호이거나 이미 삭제된 번호입니다.");
            System.out.println("---------------------------------");
          }
      } else {
        System.out.println("취소되었습니다.");
        System.out.println("---------------------------------");
      }      
    } catch (Exception e) {
      System.out.println("에러 발생");
    }
  }
  
  @RequestMapping("list.do")
  public void list(Scanner keyScan) {
    if ((boolean)session.getPosition("memberState")) {
      Member loginUser = (Member)session.getAttribute("Member");
      try {        
        Project project = projectDao.selectOneByNumber(loginUser.getPno());
        
        System.out.printf("%d번 %s, %s, %s, %s, %s, %s, %s\n", project.getPno(),
            project.getProjectName(), project.getTeamName(), 
            project.getStartDate(), project.getEndDate(),
            project.getDescription(), project.getState(), project.getScore());
        
      } catch (Exception e) {
        System.out.println("데이터 로딩중 오류 발생");
        e.printStackTrace();
      }
    } else {
      try {
        List<Project> projects = projectDao.selectList();
        for (Project project : projects) {
          System.out.printf("%d번: %s", project.getPno(), project.getProjectName());
        }
        System.out.print("조회할 프로젝트 번호는? ");
        int no = Integer.parseInt(keyScan.nextLine());
        Project project = projectDao.selectOneByNumber(no);
        
        System.out.printf("%d번 %s, %s, %s, %s, %s, %s, %s\n", project.getPno(),
            project.getProjectName(), project.getTeamName(), 
            project.getStartDate(), project.getEndDate(),
            project.getDescription(), project.getState(), project.getScore());
       
      } catch (Exception e) {
        System.out.println("데이터 로딩중 오류 발생");
        e.printStackTrace();
      }
    }
    
  }
  
  @RequestMapping("update.do")
  public void update(Scanner keyScan) {   
    try {
      System.out.print("변경하고 싶은 프로젝트 번호를 입력하세요. ");
      int no = Integer.parseInt(keyScan.nextLine());      
      Project project = projectDao.selectOne(no);
      System.out.printf("매니저번호(%s)? ", project.getMano());
      project.setProjectName(keyScan.nextLine());
      System.out.printf("클래스번호(%s)? ", project.getCno());
      project.setCno(Integer.parseInt(keyScan.nextLine()));
      System.out.printf("프로젝트명(%s)? ", project.getProjectName());
      project.setProjectName(keyScan.nextLine());
      System.out.printf("팀명(%s)? ", project.getTeamName());
      project.setTeamName(keyScan.nextLine());
      System.out.printf("시작일(%s)? ", project.getStartDate());
      project.setStartDate(Date.valueOf(keyScan.nextLine()));
      System.out.printf("종료일(%s)? ", project.getEndDate());
      project.setEndDate(Date.valueOf(keyScan.nextLine()));
      System.out.printf("설명(%s)? ", project.getDescription()); 
      project.setDescription(keyScan.nextLine());
      System.out.printf("상태(%s)? ", project.getState()); 
      project.setState(Integer.parseInt(keyScan.nextLine()));
      System.out.printf("점수(%s)? ", project.getScore()); 
      project.setScore(Integer.parseInt(keyScan.nextLine()));
      if (CommandUtil.confirm(keyScan, "변경 하시겠습니까?")) {
        int count = projectDao.update(project);
          if (count > 0) {
            System.out.println("저장하였습니다.");
            System.out.println("---------------------------------");
          } else {
            System.out.println("유효하지 않은 번호이거나 이미 삭제된 번호입니다.");
            System.out.println("---------------------------------");
          }
      } else {
        System.out.println("취소되었습니다.");
        System.out.println("---------------------------------");
      }
    } catch (Exception e) {
        System.out.println("에러 발생");
    }
  }  
}
