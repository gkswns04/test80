package bitcamp.pms.domain;

public class Member {

  private String name;
  private String post;
  private String baseAddr;
  private String memAddr;
  private String password;
  private String tel;
  private String email;
  private String role;
  private int mno;
  private int pno;
  
  public Member() {}

  public Member(String name, String post, String baseAddr, 
      String memAddr, String password, String tel,
      String email, String role, int mno, int pno) {
    
    super();
    this.name = name;
    this.post = post;
    this.baseAddr = baseAddr;
    this.memAddr = memAddr;
    this.password = password;
    this.tel = tel;
    this.email = email;
    this.role = role;
    this.mno = mno;
    this.pno = pno;
 
  }

  @Override
  public String toString() {
    return "Member [name=" + name + ", post=" + post + 
        ", baseAddr=" + baseAddr + ", memAddr=" + memAddr
        + ", password=" + password + ", tel=" + tel + ", email=" + email + 
        ", role=" + role + ", mno=" + mno + ", pno="
        + pno + "]";
  }

 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public String getBaseAddr() {
    return baseAddr;
  }

  public void setBaseAddr(String baseAddr) {
    this.baseAddr = baseAddr;
  }

  public String getMemAddr() {
    return memAddr;
  }

  public void setMemAddr(String memAddr) {
    this.memAddr = memAddr;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
  }

  public int getPno() {
    return pno;
  }

  public void setPno(int pno) {
    this.pno = pno;
  }  
}