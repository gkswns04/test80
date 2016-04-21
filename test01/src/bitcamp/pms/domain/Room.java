package bitcamp.pms.domain;

public class Room {
  private int rno;
  private String roomName;
  
  
  public Room(int rno, String roomName) {
    super();
    this.rno = rno;
    this.roomName = roomName;
  }


  @Override
  public String toString() {
    return "Room [rno=" + rno + ", roomName=" + roomName + "]";
  }


  public int getRno() {
    return rno;
  }


  public void setRno(int rno) {
    this.rno = rno;
  }


  public String getRoomName() {
    return roomName;
  }


  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }
}
