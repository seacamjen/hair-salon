import org.sql2o.*;

public class Client {
  private int id;
  private int stylist_id;
  private String name;
  private String phone;

  public Client(int stylist_id, String name, String phone) {
    this.stylist_id = stylist_id;
    this.name = name;
    this.phone = phone;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }
}
