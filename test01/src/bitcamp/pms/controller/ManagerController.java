package bitcamp.pms.controller;

import java.util.List;
import java.util.Scanner;

import bitcamp.pms.Test01;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.annotation.StartUp;
import bitcamp.pms.dao.ManagerDao;
import bitcamp.pms.domain.Manager;
import bitcamp.pms.util.CommandUtil;
import bitcamp.pms.util.PatternChecker;
import bitcamp.pms.util.Session;

@Controller
@RequestMapping("manager/")
public class ManagerController {
  Session session;
  Scanner keyScan;
  private ManagerDao managerDao;
  
  public void setSession(Session session) {
    this.session = session;
  }

  public void setScanner(Scanner keyScan) {
    this.keyScan = keyScan;    
  }
 
  public void setManagerDao(ManagerDao managerDao) {
    this.managerDao = managerDao;
  }

  @RequestMapping("add.do")  
  public void add(Scanner keyScan) {
    if((boolean)session.getPosition("managerState")) {
      Manager manager = new Manager();
      System.out.print("이름? ");
      manager.setManagerName(keyScan.nextLine());
      System.out.print("이메일? ");
      manager.setEmail(keyScan.nextLine()); 
      System.out.print("암호? ");
      manager.setPassword(keyScan.nextLine());
      System.out.print("전화? ");
      manager.setTel(keyScan.nextLine());
      if (CommandUtil.confirm(keyScan, "저장하시겠습니까?")) {
        try {
          managerDao.insert(manager);
          System.out.println("저장하였습니다.");
          System.out.println("-----------------------------------");      
        } catch (Exception e) {
          System.out.println("데이터 저장을 실패하였습니다.");
        }
      } else {
        System.out.println("저장을 취소하였습니다.");
        System.out.println("-----------------------------------");
      }      
    } else {
      System.out.println("권한이 없습니다.");
    }
  } 
  
  @RequestMapping("delete.do")
  public void delete(Scanner keyScan) {   
    if((boolean)session.getPosition("managerState")) {
      try {      
        this.list();
        System.out.print("삭제할 매니저 번호는? ");
        int no = Integer.parseInt(keyScan.nextLine());
        
        if (CommandUtil.confirm(keyScan, "정말 삭제하시겠습니까?")) {
          int count =  managerDao.delete(no);
          if (count > 0) {
            System.out.println("삭제하였습니다.");
            System.out.println("-----------------------------------");
            this.list();
          } else {
            System.out.println("유효하지 않은 번호이거나, 이미 삭제된 항목입니다.");          
          }
        } else {
          System.out.println("취소하였습니다.");
          System.out.println("-----------------------------------");
        }
      } catch (Exception e) {
        System.out.println("데이터 처리에 실패했습니다.");
      }      
    } else {
      System.out.println("권한이 없습니다.");
    }
  }
  
  @RequestMapping("list.do")
  public void list() {   
    System.out.println("매니저 리스트를 로딩합니다.");
    try {
      List<Manager> managers = managerDao.selectList();      
      for (Manager manager : managers) {        
        System.out.printf("%d, %s, %s, %s\n", manager.getMano(), manager.getManagerName(), manager.getEmail(), manager.getTel());
      }
    } catch (Exception e) {
      throw new RuntimeException("매니저 데이터 로딩 실패!", e);
    }    
  }
  
  public Manager search(String email) {
    try {
      List<Manager> managers = managerDao.selectList();    
      for (Manager manager : managers) {        
        if (email.equals(manager.getEmail())) {
          return manager;
        } 
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("매니저 데이터 로딩 실패!", e);
    }        
  }
  
  @RequestMapping("unsubscribe.do")
  public void doUnsubscribe(Scanner keyScan) {
    try { 
      Manager manager;
      int no;
      while (true) {
        System.out.println("탈퇴 할 계정 정보를 확인합니다");
        System.out.print("email : ");
        String input = keyScan.nextLine();
        if (search(input) != null) {
          manager = search(input);
          no = manager.getMano();
          break;
        }
      }  
      if (CommandUtil.confirm(keyScan, "정말 탈퇴하시겠습니까?")) {
        int count =  managerDao.delete(no);
        if (count > 0) {
          System.out.println("매니저 정보를 삭제하였습니다.");
          System.out.println("-----------------------------------");
          Test01.state = 0;
        } else {
          System.out.println("취소하였습니다.");
          System.out.println("-----------------------------------");
          Test01.state = 1;
        } 
      }      
    } catch (Exception e) {
        System.out.println("데이터 처리에 실패했습니다.");
    }    
  }
  
  @RequestMapping("update.do")
  public void update(Scanner keyScan) {    
    if((boolean)session.getPosition("managerState")) {
      try {
        this.list();
        System.out.print("변경할 매니저 번호는? ");
        int no = Integer.parseInt(keyScan.nextLine());      
        Manager manager = managerDao.selectOne(no);      
        System.out.printf("이름(%s)? ", manager.getManagerName());
        manager.setManagerName(keyScan.nextLine());
        System.out.printf("이메일(%s)? ", manager.getEmail());
        manager.setEmail(keyScan.nextLine());
        System.out.printf("암호(%s)? ", manager.getPassword());
        manager.setPassword(keyScan.nextLine());
        System.out.printf("전화(%s)? ", manager.getTel());
        manager.setTel(keyScan.nextLine());
        if (CommandUtil.confirm(keyScan, "변경하시겠습니까?")) {        
          int count =  managerDao.update(manager);
          if (count > 0) {
            System.out.println("변경 하였습니다.");
            System.out.println("-----------------------------------");
            this.list();
          } else {
            System.out.println("유효하지 않은 번호이거나, 이미 삭제된 항목입니다.");
          }
        } else {
          System.out.println("취소하였습니다.");
          System.out.println("-----------------------------------");
        }
      } catch (Exception e) {
        System.out.println("데이터 로딩 또는 저장중 오류가 발생했습니다.");
      }      
    } else {
      System.out.println("권한이 없습니다.");
    }
  }
}
