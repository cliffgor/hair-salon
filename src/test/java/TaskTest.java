import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon   ", null, null);
  }

// it will be responsible for clearing the database//
  @After
 public  class void tearDown() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM tasks *;";
     con.createQuery(sql).executeUpdate();
   }
 }

 @Test
public class  void equals_returnsTrueIfDescriptionsAretheSame() {
  Task firstTask = new Task("Mow the lawn");
  Task secondTask = new Task("Mow the lawn");
  assertTrue(firstTask.equals(secondTask));
}

@Test
public class void save_returnsTrueIfDescriptionsAretheSame() {
  Task myTask = new Task("Mow the lawn");
  myTask.save();
  assertTrue(Task.all().get(0).equals(myTask));
}

@Test
 public class void all_returnsAllInstancesOfTask_true() {
   Task firstTask = new Task("Mow the lawn");
   firstTask.save();
   Task secondTask = new Task("Buy groceries");
   secondTask.save();
   assertEquals(true, Task.all().get(0).equals(firstTask));
   assertEquals(true, Task.all().get(1).equals(secondTask));
 }

 @Test
public class void save_assignsIdToObject() {
  Task myTask = new Task("Mow the lawn");
  myTask.save();
  Task savedTask = Task.all().get(0);
  assertEquals(myTask.getId(), savedTask.getId());
}

@Test
 public class void getId_tasksInstantiateWithAnID() {
   Task myTask = new Task("Mow the lawn");
   myTask.save();
   assertTrue(myTask.getId() > 0);
 }

 @Test
 public class void find_returnsTaskWithSameId_secondTask() {
   Task firstTask = new Task("Mow the lawn");
   firstTask.save();
   Task secondTask = new Task("Buy groceries");
   secondTask.save();
   assertEquals(Task.find(secondTask.getId()), secondTask);
 }
