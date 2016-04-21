package bitcamp.pms.domain;

public class Manager {
  private int mano;
  private String tel;
  private String email;
  private String managerName;
  private String password;
  
  public Manager() {}
  
  public Manager(int mano, String tel, String email, String managerName, String password) {  
    this.mano = mano;
    this.tel = tel;
    this.email = email;
    this.managerName = managerName;
    this.password = password;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Manager [mano=" + mano + ", tel=" + tel + ", "
        + "email=" + email + ", managerName=" + managerName + "]";
  }


  public int getMano() {
    return mano;
  }


  public void setMano(int mano) {
    this.mano = mano;
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


  public String getManagerName() {
    return managerName;
  }


  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }  
}
