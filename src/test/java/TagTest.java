import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class TagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Tag_instantiatesCorrectly_true() {
    Tag newTag = new Tag("Comfort Food");
    assertEquals(true, newTag instanceof Tag);
  }
  @Test
  public void getName_TagInstantiatesWithName_String() {
    Tag newTag = new Tag("Comfort Food");
    assertEquals("Comfort Food", newTag.getName());

  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Tag.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTagNameIstheSame_True() {
    Tag fernandaWroteAndSentToDB = new Tag("Comfort Food");
    Tag fernandaSavedInTheDatabaseThenRetrievedObject = new Tag("Comfort Food");
    assertTrue(fernandaWroteAndSentToDB.equals(fernandaSavedInTheDatabaseThenRetrievedObject));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Tag newTag = new Tag("Comfort Food");
    newTag.save();
    assertTrue(Tag.all().get(0).equals(newTag));
  }

  @Test
  public void save_assignsIdToObject() {
    Tag newTag = new Tag("Comfort Food");
    newTag.save();
    Tag savedTag = Tag.all().get(0);
    assertEquals(newTag.getId(), savedTag.getId());
  }

  @Test
  public void find_findTagInDatabase_true() {
    Tag newTag = new Tag("Comfort Food");
    newTag.save();
    Tag savedTag = Tag.find(newTag.getId());
    assertTrue(newTag.equals(savedTag));
  }
   @Test
   public void addRecipe_addsRecipeToTag() {
     Tag newTag = new Tag("Comfort Food");
     newTag.save();
     Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
     newRecipe.save();
     newTag.addRecipe(newRecipe);
     Recipe savedRecipe = newTag.getRecipes().get(0);
     assertTrue(newRecipe.equals(savedRecipe));
   }

   @Test
   public void getRecipes_returnsAllRecipes_List() {
     Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
     newRecipe.save();
     Tag newTag = new Tag("Comfort Food");
     newTag.save();
     newTag.addRecipe(newRecipe);
     List savedRecipes = newTag.getRecipes();
     assertEquals(1, savedRecipes.size());
   }
   @Test
   public void update_updatesTagName_true() {
     Tag newTag = new Tag("Comfort Food");
     newTag.save();
     newTag.update("Baked");
     assertEquals("Baked", Tag.find(newTag.getId()).getName());
   }
   @Test
   public void delete_deletesTag_true() {
     Tag newTag = new Tag("Comfort Food");
     newTag.save();
     int newTagId = newTag.getId();
     newTag.delete();
     assertEquals(null, Tag.find(newTagId));
   }
 }
