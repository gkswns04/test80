package bitcamp.pms.domain;

import java.sql.Date;

public class Project {
  
  private String projectName;
  private String teamName;
  private String description;
  private Date startDate;
  private Date endDate;
  private int state;
  private int score;
  private int pno;
  private int mano;
  private int pmno;
  
  public Project() {}

  public Project(String projectName, String teamName, 
      String description, Date startDate, Date endDate,
      int state, int score, int pno, int mano, int pmno) {
    
    super();
    
    this.projectName = projectName;
    this.teamName = teamName;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.state = state;
    this.score = score;
    this.pno = pno;
    this.mano = mano;
    this.pmno = pmno;
  }
  
  
  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getPno() {
    return pno;
  }

  public void setPno(int pno) {
    this.pno = pno;
  }

  public int getMano() {
    return mano;
  }

  public void setMano(int mano) {
    this.mano = mano;
  }

  public int getPmno() {
    return pmno;
  }

  public void setPmno(int pmno) {
    this.pmno = pmno;
  }

  @Override
  public String toString() {
    return "Project [projectName=" + projectName + 
        ", teamName=" + teamName + ", description="
        + description + ", startDate=" + startDate + ", endDate=" + 
        endDate + ", state=" + state + ", score=" + score
        + ", pno=" + pno + ", mano=" + mano + ", pmno=" + pmno + "]";
  }
}



