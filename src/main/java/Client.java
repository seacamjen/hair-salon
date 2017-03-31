import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Client {
  private int id;
  private String name;
  private String phone;
  private int stylist_id;

  public Client(String name, String phone, int stylist_id) {
    this.name = name;
    this.phone = phone;
    this.stylist_id = stylist_id;
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

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, phone, stylist_id) VALUES (:name, :phone, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stylist_id", stylist_id)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id;";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
            this.getPhone().equals(newClient.getPhone()) &&
            this.getStylistId() == newClient.getStylistId();
    }
  }

  public void update(String name, String phone, int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, phone = :phone, stylist_id = :stylist_id WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
   try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
      }
   }


}
