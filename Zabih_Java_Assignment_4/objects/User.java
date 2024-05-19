package objects;

public class User {
  private String name;
  private String email;
  private String phone;
  private String SelectDayOfWeek = "";
 
  public User(String name, String email, String phone, String SelectDayOfWeek) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    //this.SelectDayOfWeek = SelectDayOfWeek;

  } // constructor

  //===============>>
  // GETTERS
  public String getName() {
    return this.name;
  }
  public String getEmail() {
    return this.email;
  }
  public String getPhone() {
    return this.phone;
  }
  public String getSelectDayOfWeek() {
    return this.SelectDayOfWeek;
  }

  //===============>>
  // SETTERS
  public void setName(String name) {
    this.name = name;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
    public void setSecletDayOfWeek(String [] hours, int day) {
        String theDay = hours[day -1];
        this.SelectDayOfWeek = theDay;
  }

  
} // class