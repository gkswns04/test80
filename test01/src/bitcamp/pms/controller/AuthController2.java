package bitcamp.pms.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ManagerDao;
import bitcamp.pms.domain.Manager;
import bitcamp.pms.util.CommandUtil;
import bitcamp.pms.util.Session;

@Controller
public class AuthController2 {  
  Scanner keyScan;
  ManagerDao managerDao;  
  Session session;
  
  public void setManagerDao(ManagerDao managerDao) {
    this.managerDao = managerDao;
  }
  
  public void setSession(Session session) {
    this.session = session;
  }

  public void setScanner(Scanner keyScan) {
    this.keyScan = keyScan;
    
  }
  
  @RequestMapping("logout")
  public void logOut(Session se) {
    if (CommandUtil.confirm(keyScan, "정말 로그아웃 하시겠습니까??")) {
      try {        
        System.out.println("안녕히 가세요!");
        session.setAttribute("state", false);
      } catch (Exception e) {
        
      }
    }
  }
  @RequestMapping("unsubscribe")
  public void unsubscribe(Session se) {
    if (CommandUtil.confirm(keyScan, "정말 탈퇴하시겠습니까?")) {
      try {
        Manager loginUser = (Manager)se.getAttribute("loginUser");
        managerDao.delete(loginUser.getNo());        
        System.out.println("매니저정보를 삭제하였습니다. 안녕히 가세요!");
        session.setAttribute("state", false);
      } catch (Exception e) {
        
      }
    }
  }
  public void service() {
    System.out.println("1) 로그인");    
    System.out.println("2) 매니저가입");
    System.out.println("9) 종료");
    String input = null;
    while (true) {
      System.out.print("선택> ");    
      input = keyScan.nextLine();
     
      switch (input) {
      case "1"  :
        if (doLogin()) {
          return;
        } break;
      case "2"  :
        doSignUp(); break;
      case "9"  :
        System.out.println("시스템을 종료합니다.");
        System.exit(0);
      default   :
        System.out.println("올바르지 않은 번호 입니다."); break;
      }      
    }
  }

  private boolean doLogin() {
    System.out.print("Email : ");
    String email = keyScan.nextLine();
    
    System.out.print("Password : ");
    String password = keyScan.nextLine();
    
    Manager manager = managerDao.selectOneByEmail(email);
    
    if(manager == null) {
      System.out.println("등록되지 않은 사용자 입니다.");
      return false;
    } else if (!(manager.getPassword().equals(password))){
      System.out.println("암호가 맞지 않습니다.");
      return false;
    } 
    //로그인 성공한 매니저 정보를 세션에 보관한다.
    //Why? 다른 컨트롤러가 사용할수 있도록
    session.setAttribute("loginUser", manager);
    session.setAttribute("state", true);
    System.out.printf("환영합니다! %s님\n", manager.getName());
    return true;   
    
  }

  private void doSignUp() {
    Manager manager = new Manager();
    System.out.print("이름: ");
    manager.setName(keyScan.nextLine());
    
    String value = null;
    while (true) {
      System.out.print("이메일: ");
      value = keyScan.nextLine();
      if (value.matches("[a-zA-Z][\\w\\.]*@([\\w]+\\.)[a-zA-Z]{2,}")) {
        break;
      }  
      System.out.println("이메일 형식에 맞지 않습니다. 예) aaa.aaa@bbb.com");
    }
    manager.setEmail(value);    
    
    String regex = null;
    Pattern pattern = null;
    Matcher matcher = null;
    while (true) {     
      System.out.print("암호: ");
      value = keyScan.nextLine();
      if (value.length() < 4 || value.length() > 10) {
        System.out.println("암호는 4 ~ 10자 까지만 가능합니다.");
      }
      
      regex = String.format
          ("(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@?])([a-zA-Z0-9!@?]){%d}", value.length());      
      pattern = Pattern.compile(regex);          
      matcher = pattern.matcher(value); // 2)입력 문자열을 정규식에 따라 분석할 객체를 얻는다
      if (matcher.find()) {// 3) 분석 시작
        break; // 찾은 값을 리턴
      }
      System.out.println("최소 알파벳1개, 숫자1개, 특수문자(?,!,@)1개 반드시 포함.");
    }
    manager.setPassword(value);    
    
    while (true) {
      System.out.print("전화: ");
      value = keyScan.nextLine();
      if (value.matches("(\\d{2,4}-)?\\d{3,4}-\\d{4}")) {
        break;
      }
      System.out.println("전화 형식에 맞지 않습니다. 예) 02-123-1234");
    }
    manager.setTel(value);
    
    try{
      managerDao.insert(manager);
      System.out.printf("매니저이 되신걸 환영합니다! %s님\n", manager.getName());
    } catch (Exception e) {
        System.out.println("매니저 등록에 실패했습니다.");
    }
    
  }  
}
