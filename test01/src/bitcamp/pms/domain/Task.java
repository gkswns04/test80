package bitcamp.pms.domain;

import java.sql.Date;

public class Task {
  
  private String taskName;
  private String content;
  private Date startDate;
  private Date endDate;
  private int tno;
  private int pno;
  
  public Task() {}
  
  public Task(String taskName, String content, Date startDate, 
      Date endDate, int tno, int pno) {    
    this.taskName = taskName;
    this.content = content;
    this.startDate = startDate;
    this.endDate = endDate;
    this.tno = tno;
    this.pno = pno;
  }

  @Override
  public String toString() {
    return "Task [taskName=" + taskName + ", "
        + "content=" + content + ", startDate=" + startDate
        + ", endDate=" + endDate + ", tno=" + tno + ", pno=" + pno + "]";
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getTno() {
    return tno;
  }

  public void setTno(int tno) {
    this.tno = tno;
  }

  public int getPno() {
    return pno;
  }

  public void setPno(int pno) {
    this.pno = pno;
  }  
}
