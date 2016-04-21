package bitcamp.pms.domain;

import java.sql.Date;

public class Board {
  private int bno;
  private int mano;
  private Date createdDate;
  private int views;
  private String content;
  private String title;
  private String writer;
  
  
  public Board(int bno, int mano, Date createdDate, int views, 
      String content, String title, String writer) {
    super();
    this.bno = bno;
    this.mano = mano;
    this.createdDate = createdDate;
    this.views = views;
    this.content = content;
    this.title = title;
    this.writer = writer;
  }


  @Override
  public String toString() {
    return "Board [bno=" + bno + ", mano=" + mano + ", createdDate=" 
        + createdDate + ", views=" + views + ", content="
        + content + ", title=" + title + ", writer=" + writer + "]";
  }


  public int getBno() {
    return bno;
  }


  public void setBno(int bno) {
    this.bno = bno;
  }


  public int getMano() {
    return mano;
  }


  public void setMano(int mano) {
    this.mano = mano;
  }


  public Date getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


  public int getViews() {
    return views;
  }


  public void setViews(int views) {
    this.views = views;
  }


  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public String getWriter() {
    return writer;
  }


  public void setWriter(String writer) {
    this.writer = writer;
  }
}
