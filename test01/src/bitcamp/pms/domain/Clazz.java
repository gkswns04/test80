package bitcamp.pms.domain;

import java.sql.Date;

public class Clazz {
  
  private int cno;
  private int rno;
  private String classname;
  private String number;
  private Date totalDate;
  public Clazz() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Clazz(int cno, int rno, String classname, String number, Date totalDate) {
    super();
    this.cno = cno;
    this.rno = rno;
    this.classname = classname;
    this.number = number;
    this.totalDate = totalDate;
  }
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public int getRno() {
    return rno;
  }
  public void setRno(int rno) {
    this.rno = rno;
  }
  public String getClassname() {
    return classname;
  }
  public void setClassname(String classname) {
    this.classname = classname;
  }
  public String getNumber() {
    return number;
  }
  public void setNumber(String number) {
    this.number = number;
  }
  public Date getTotalDate() {
    return totalDate;
  }
  public void setTotalDate(Date totalDate) {
    this.totalDate = totalDate;
  }
  @Override
  public String toString() {
    return "Clazz [cno=" + cno + ", rno=" + rno + ", classname=" + classname + ", number=" + number + ", totalDate="
        + totalDate + "]";
  }
 
}
