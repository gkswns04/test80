package bitcamp.pms.domain;

import java.sql.Date;

public class Clazz {
  
  private int pmno;
  private int rno;
  private String classname;
  private String number;
  private Date totalDate;
 
  public Clazz(int pmno, int rno, String classname, 
      String number, Date totalDate) {
    
    super();
    this.pmno = pmno;
    this.rno = rno;
    this.classname = classname;
    this.number = number;
    this.totalDate = totalDate;
  }

  @Override
  public String toString() {
    return "Clazz [pmno=" + pmno + ", rno=" + rno + ", "
        + "classname=" + classname + ", number=" + number + ", totalDate="
        + totalDate + "]";
  }

  public int getPmno() {
    return pmno;
  }

  public void setPmno(int pmno) {
    this.pmno = pmno;
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

}
