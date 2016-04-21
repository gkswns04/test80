package bitcamp.pms.domain;

public class Manager {
  private String mano;
  private String tel;
  private String email;
  private String managerName;
  
  
  public Manager(String mano, String tel, String email, String managerName) {
    super();
    this.mano = mano;
    this.tel = tel;
    this.email = email;
    this.managerName = managerName;
  }


  @Override
  public String toString() {
    return "Manager [mano=" + mano + ", tel=" + tel + ", "
        + "email=" + email + ", managerName=" + managerName + "]";
  }


  public String getMano() {
    return mano;
  }


  public void setMano(String mano) {
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
