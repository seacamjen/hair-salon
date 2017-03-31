import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Stylist {
  private String name;
  private String phone;
  private int id;

  public Stylist(String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, phone) VALUES (:name, :phone);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
      // && this.getId() == newStylist.getId();
    }
  }

  // public List<Client> getClients() {
  // try(Connection con = DB.sql2o.open()) {
  //   String sql = "SELECT * FROM clients WHERE stylist_id=:id;";
  //   return con.createQuery(sql)
  //     .addParameter("id", this.id)
  //     .executeAndFetch(Client.class);
  //   }
  // } 
}
