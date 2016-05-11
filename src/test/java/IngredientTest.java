import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class IngredientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Ingredient_instantiatesCorrectly_true() {
    Ingredient newIngredient = new Ingredient("Carrot");
    assertEquals(true, newIngredient instanceof Ingredient);
  }
  @Test
  public void getName_IngredientInstantiatesWithNameInstructionAndRating_String() {
    Ingredient newIngredient = new Ingredient("Carrot");
    assertEquals("Carrot", newIngredient.getName());

  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Ingredient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfIngredient_Name_AretheSame() {
    Ingredient fernandaWroteAndSentToDB = new Ingredient("Carrot");
    Ingredient fernandaSavedInTheDatabaseThenRetrievedObject = new Ingredient("Carrot");
    assertTrue(fernandaWroteAndSentToDB.equals(fernandaSavedInTheDatabaseThenRetrievedObject));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Ingredient newIngredient = new Ingredient("Carrot");
    newIngredient.save();
    assertTrue(Ingredient.all().get(0).equals(newIngredient));
  }

  @Test
  public void save_assignsIdToObject() {
    Ingredient newIngredient = new Ingredient("Carrot");
    newIngredient.save();
    Ingredient savedIngredient = Ingredient.all().get(0);
    assertEquals(newIngredient.getId(), savedIngredient.getId());
  }

  @Test
  public void find_findIngredientInDatabase_true() {
    Ingredient newIngredient = new Ingredient("Carrot");
    newIngredient.save();
    Ingredient savedIngredient = Ingredient.find(newIngredient.getId());
    assertTrue(newIngredient.equals(savedIngredient));
  }
   @Test
   public void addRecipe_addsRecipeToIngredient() {
     Ingredient newIngredient = new Ingredient("Carrot");
     newIngredient.save();
     Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
     newRecipe.save();
     newRecipe.addRecipe(newRecipe);
     Recipe savedRecipe = newRecipe.getRecipes().get(0);
     assertTrue(newRecipe.equals(savedRecipe));
   }
}
//
//  @Test
//  public void getAuthors_returnsAllAuthors_List() {
//    Author newAuthor = new Author("Household chores");
//    newAuthor.save();
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    myBook.addAuthor(myAuthor);
//    List savedAuthors = myBook.getAuthors();
//    assertEquals(1, savedAuthors.size());
//  }
//
//  @Test
//  public void delete_deletesAllBooksAndAuthorsAssociations() {
//    Author myAuthor = new Author("Household chores");
//    myAuthor.save();
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    myBook.addAuthor(myAuthor);
//    myBook.delete();
//    assertEquals(0, myAuthor.getBooks().size());
//  }
//
