import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Ashley", "805-466-1234");
    assertTrue(newStylist instanceof Stylist);
  }

  @Test
  public void getName_instantiatesCorrectlyWithName_String() {
    Stylist newStylist = new Stylist("Ashley", "805-466-1234");
    assertEquals("Ashley", newStylist.getName());
  }

  @Test
  public void getPhone_instantiatesCorrectlyWithName_String() {
    Stylist newStylist = new Stylist("Ashley", "805-466-1234");
    assertEquals("805-466-1234", newStylist.getPhone());
  }

  @Test
  public void all_allReturnsAllInstancesOfStylists_true() {
    Stylist newStylistOne = new Stylist("Ashley", "805-466-1234");
    Stylist newStylistTwo = new Stylist("Marissa", "805-232-1244");
    newStylistOne.save();
    newStylistTwo.save();
    assertTrue(Stylist.all().get(0).equals(newStylistOne));
    assertTrue(Stylist.all().get(1).equals(newStylistTwo));
  }

  @Test
  public void save_assignsIdToStylist_true() {
    Stylist newStylist = new Stylist("Ashley", "805-466-1234");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }

  @Test
    public void getId_getStylistId_1() {
    Stylist newStylist = new Stylist("Ashley", "805-466-1234");
    newStylist.save();
    assertTrue(newStylist.getId() > 0);
  }

  @Test
   public void find_findReturnsStylistWithSameId_true() {
     Stylist newStylistOne = new Stylist("Ashley", "805-466-1234");
     Stylist newStylistTwo = new Stylist("Marissa", "805-232-1244");
     newStylistOne.save();
     newStylistTwo.save();
     assertEquals(Stylist.find(newStylistTwo.getId()), newStylistTwo);
   }

   @Test
   public void equals_reuturnsTrueIfIdsAretheSame() {
     Stylist newStylistOne = new Stylist("Ashley", "805-466-1234");
     Stylist newStylistTwo = new Stylist("Ashley", "805-466-1234");
     assertTrue(newStylistOne.equals(newStylistTwo));
   }

   @Test
   public void getClients_retrievesAllClientsFromDatabase_list(){
     Stylist newStylistOne = new Stylist("Ashley", "805-466-1234");
     newStylistOne.save();
     Client clientOne = new Client("Barbara", "234-987-2342", newStylistOne.getId());
     clientOne.save();
     Client clientTwo = new Client("Jane", "234-237-2332", newStylistOne.getId());
     clientTwo.save();
     Client[] clients = new Client[] {clientOne, clientTwo};
     assertTrue(newStylistOne.getClients().containsAll(Arrays.asList(clients)));
   }

   @Test
   public void update_updatesStylist_true() {
     Stylist newStylist = new Stylist("Ashley", "805-466-1234");
     newStylist.save();
     newStylist.update("Ashley Smith", "805-466-9876");
     assertEquals("Ashley Smith", Stylist.find(newStylist.getId()).getName());
     assertEquals("805-466-9876", Stylist.find(newStylist.getId()).getPhone());
   }

   @Test
    public void delete_deleteStylist_true(){
      Stylist myStylist = new Stylist("Ashley", "805-466-1234");
      myStylist.save();
      int myStylistId = myStylist.getId();
      myStylist.delete();
      assertEquals(null, Stylist.find(myStylistId));
    }

}
