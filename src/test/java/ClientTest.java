import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true(){
    Client testClient = new Client(1, "Barbara", "234-987-2342");
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getStylistId_instantiatesWithStylistId_true(){
    Client testClient = new Client(1, "Barbara", "234-987-2342");
    assertEquals(1, testClient.getStylistId());
  }

  @Test
  public void getName_instantiatesWithName_true(){
    Client testClient = new Client(1, "Barbara", "234-987-2342");
    assertEquals("Barbara", testClient.getName());
  }

  @Test
  public void getPhone_instantiatesWithPhone_true(){
    Client testClient = new Client(1, "Barbara", "234-987-2342");
    assertEquals("234-987-2342", testClient.getPhone());
  }


}
