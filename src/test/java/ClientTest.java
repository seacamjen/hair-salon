import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

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
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getStylistId_instantiatesWithStylistId_true(){
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    assertEquals(1, testClient.getStylistId());
  }

  @Test
  public void getName_instantiatesWithName_true(){
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    assertEquals("Barbara", testClient.getName());
  }

  @Test
  public void getPhone_instantiatesWithPhone_true(){
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    assertEquals("234-987-2342", testClient.getPhone());
  }

  @Test
  public void save_returnsTrueIfClientsAreTheSame() {
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client clientOne = new Client("Barbara", "234-987-2342", 1);
    Client clientTwo = new Client("Jane", "234-237-2332", 1);
    assertTrue(clientOne.equals(clientTwo));
  }

  @Test
  public void all_returnsAllSavedClients_true() {
    Client clientOne = new Client("Barbara", "234-987-2342", 1);
    clientOne.save();
    Client clientTwo = new Client("Jane", "234-237-2332", 1);
    clientTwo.save();
    assertEquals(true, Client.all().get(0).equals(clientOne));
    assertEquals(true, Client.all().get(1).equals(clientTwo));
  }

  @Test
  public void getId_getClientId_1() {
    Client testClient = new Client("Barbara", "234-987-2342", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_ratingTwo() {
    Client clientOne = new Client("Barbara", "234-987-2342", 1);
    clientOne.save();
    Client clientTwo = new Client("Jane", "234-237-2332", 1);
    clientTwo.save();
    assertEquals(Client.find(clientTwo.getId()), clientTwo);
  }



}
