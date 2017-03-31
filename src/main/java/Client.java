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
      return this.getStylistId() == newClient.getStylistId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (stylist_id, name, phone) VALUES (:stylist_id, :name, :phone);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stylist_id", stylist_id)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .executeUpdate()
      .getKey();
    }
  }

}
