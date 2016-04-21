package bitcamp.pms.controller;

import java.util.List;
import java.util.Scanner;

import bitcamp.pms.Test01;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.annotation.StartUp;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.util.CommandUtil;
import bitcamp.pms.util.PatternChecker;
import bitcamp.pms.util.Session;

@Controller
@RequestMapping("member/")
public class MemberController {
  Session session;
  Scanner keyScan;
  private MemberDao memberDao;
  
  public void setSession(Session session) {
    this.session = session;
  }
  public void setScanner(Scanner keyScan) {
    this.keyScan = keyScan;    
  }
  public void setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @RequestMapping("delete.do")  
  public void delete(Scanner keyScan) {
    if((boolean)session.getPosition("managerState")) {
      try {      
        this.list();
        System.out.print("삭제할 회원 번호는? ");
        int no = Integer.parseInt(keyScan.nextLine());
        
        if (CommandUtil.confirm(keyScan, "정말 삭제하시겠습니까?")) {
          int count =  memberDao.delete(no);
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
    System.out.println("회원 리스트를 로딩합니다.");
    try {
      List<Member> members = memberDao.selectList();      
      for (Member member : members) {        
        System.out.printf("%d, %d, %s, %s, %s\n", member.getMno(), member.getPno(), 
            member.getName(), member.getEmail(), member.getTel());
      }
    } catch (Exception e) {
      throw new RuntimeException("회원 데이터 로딩 실패!", e);
    }    
  }
  
  public Member search(String email) {
    try {
      List<Member> members = memberDao.selectList();    
      for (Member member : members) {        
        if (email.equals(member.getEmail())) {
          return member;
        } 
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("회원 데이터 로딩 실패!", e);
    }        
  } 
  
  @RequestMapping("update.do")
  public void update(Scanner keyScan) {    
    try {
      this.list();
      System.out.print("변경할 회원 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());      
      Member member = memberDao.selectOne(no);      
      System.out.printf("이름(%s)? ", member.getName());
      member.setName(keyScan.nextLine());
      System.out.printf("이메일(%s)? ", member.getEmail());
      member.setEmail(keyScan.nextLine());
      System.out.printf("암호(%s)? ", member.getPassword());
      member.setPassword(keyScan.nextLine());
      System.out.printf("전화(%s)? ", member.getTel());
      member.setTel(keyScan.nextLine());
      System.out.printf("우편번호(%s)? ", member.getPost());
      member.setPost(keyScan.nextLine());
      System.out.printf("기본주소(%s)? ", member.getBaseAddr());
      member.setBaseAddr(keyScan.nextLine());
      System.out.printf("상세주소(%s)? ", member.getMemAddr());
      member.setMemAddr(keyScan.nextLine());
      System.out.printf("역할(%s)? ", member.getRole());
      member.setRole(keyScan.nextLine());
      System.out.printf("프로젝트번호(%s)? ", member.getPno());
      member.setPno(Integer.parseInt(keyScan.nextLine()));
      
      if (CommandUtil.confirm(keyScan, "변경하시겠습니까?")) {        
        int count =  memberDao.update(member);
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
