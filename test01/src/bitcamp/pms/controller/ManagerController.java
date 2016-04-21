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

@Controller
@RequestMapping("manager/")
public class ManagerController {
  
  private ManagerDao managerDao;
 
  public void setManagerDao(ManagerDao managerDao) {
    this.managerDao = managerDao;
  }

  @RequestMapping("add.do")
  public void add(Scanner keyScan) {    
    Manager manager = new Manager();
    System.out.print("이름? ");
    manager.setName(keyScan.nextLine());
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
  } 
  
  @RequestMapping("delete.do")
  public void delete(Scanner keyScan) {    
    try {      
      this.list();
      System.out.print("삭제할 회원 번호는? ");
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
  }
  
  @RequestMapping("list.do")
  public void list() {   
    System.out.println("회원 리스트를 로딩합니다.");
    try {
      List<Manager> managers = managerDao.selectList();      
      for (Manager manager : managers) {        
        System.out.printf("%d, %s, %s, %s\n", manager.getNo(), manager.getName(), manager.getEmail(), manager.getTel());
      }
    } catch (Exception e) {
      throw new RuntimeException("회원 데이터 로딩 실패!", e);
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
      throw new RuntimeException("회원 데이터 로딩 실패!", e);
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
          no = manager.getNo();
          break;
        }
      }  
      if (CommandUtil.confirm(keyScan, "정말 탈퇴하시겠습니까?")) {
        int count =  managerDao.delete(no);
        if (count > 0) {
          System.out.println("회원 정보를 삭제하였습니다.");
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
  
  @StartUp("2")
  public void doSignIn(Scanner keyScan) {
    Manager manager = new Manager();
    String check;
    System.out.print("이름? ");
    manager.setName(keyScan.nextLine());
    while (true) {
      System.out.print("이메일? ");    
      check = keyScan.nextLine();
      if (PatternChecker.isEmail(check)) {
        manager.setEmail(check);
        break;
      } else {
        System.out.println("이메일 형식이 맞지 않습니다.");
        System.out.println("이메일은 xxx@xxx.com 형식입니다.");
      }
    }
    while (true) {
      System.out.print("암호? ");    
      check = keyScan.nextLine();
      if (PatternChecker.isPassword(check)) {
        manager.setPassword(check);
        break;
      } else {
        System.out.println("암호 형식이 맞지 않습니다. 암호는 4~10자로 이루어지며");
        System.out.println("숫자1개이상 특수문자(?,!,@)를 1개이상 포함해야 합니다");
      }
    }
    while (true) {
      System.out.print("전화? ");    
      check = keyScan.nextLine();
      if (PatternChecker.isTel(check)) {
        manager.setTel(check);
        break;
      } else if (PatternChecker.isCellPhone(check)) {
        manager.setTel(check);
        break;
      } else {
        System.out.println("전화 형식이 맞지 않습니다.");
        System.out.println("xxx-xxxx-xxxx or xxxx-xxxx 형식으로 입력하세요");
      }
    }      
    try {
      managerDao.insert(manager);
      System.out.printf("%s님 회원 가입을 환영합니다.\n", manager.getName());
      System.out.println("-----------------------------------");      
    } catch (Exception e) {
      System.out.println("회원 가입을 실패하였습니다.");
    }    
  }
  
  @StartUp("1")
  public void doLogin(Scanner keyScan) {    
    Manager manager;
    while (true) {
      System.out.print("Email : ");
      String input = keyScan.nextLine();
      if (search(input) != null) {
        manager = search(input);
        while (true) {
          System.out.print("Password : ");
          input = keyScan.nextLine();
          if (input.equals(manager.getPassword())) {
            break;
          } else {
            System.out.println("암호가 맞지 않습니다.");
          }
        }
        break;
      } else {
        System.out.println("등록되지 않은 사용자입니다.");
      }
    } 
    System.out.printf("환영합니다 %s님!\n", manager.getName());
    Test01.state = 1;
  }
  
  @RequestMapping("update.do")
  public void update(Scanner keyScan) {    
    try {
      this.list();
      System.out.print("변경할 회원 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());      
      Manager manager = managerDao.selectOne(no);      
      System.out.printf("이름(%s)? ", manager.getName());
      manager.setName(keyScan.nextLine());
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
  }
}
