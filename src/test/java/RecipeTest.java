import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class RecipeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Recipe_instantiatesCorrectly_true() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    assertEquals(true, newRecipe instanceof Recipe);
  }

  @Test
  public void getName_recipeInstantiatesWithNameInstructionAndRating_String() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    assertEquals("Chicken Pot Pie", newRecipe.getName());
    assertEquals("Bake that bird", newRecipe.getInstruction());
    assertEquals(5, newRecipe.getRating());
    //  if we MUST use an Integer, generally don't do this:
    // Integer expected = 5;
    // assertEquals(expected, newRecipe.getRating());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Recipe.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfRecipe_Name_Instruction_Rating_AretheSame() {
    Recipe fernandaWroteAndSentToDB = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    Recipe fernandaSavedInTheDatabaseThenRetrievedObject = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    assertTrue(fernandaWroteAndSentToDB.equals(fernandaSavedInTheDatabaseThenRetrievedObject));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    Recipe taesRecipe = new Recipe("Thanksgiving Turkey", "Bake that bird", 4);
    newRecipe.save();
    taesRecipe.save();
    assertTrue(Recipe.all().get(0).equals(newRecipe));
    assertTrue(Recipe.all().get(1).equals(taesRecipe));
  }

  @Test
  public void save_assignsIdToObject() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    newRecipe.save();
    Recipe savedRecipe = Recipe.all().get(0);
    assertEquals(newRecipe.getId(), savedRecipe.getId());
  }

  @Test
  public void find_findRecipeInDatabase_true() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    newRecipe.save();
    Recipe savedRecipe = Recipe.find(newRecipe.getId());
    assertTrue(newRecipe.equals(savedRecipe));
  }
  @Test
  public void add_addIngredientToRecipe__true() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    newRecipe.save();
    Ingredient newIngredient = new Ingredient("Carrot");
    newIngredient.save();
    newRecipe.addIngredient(newIngredient);
    Ingredient savedIngredient = newRecipe.getIngredients().get(0);
    assertTrue(newIngredient.equals(savedIngredient));
  }
  @Test
  public void getIngredients_returnsAllRecipes_List() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    newRecipe.save();
    Ingredient newIngredient = new Ingredient("Carrot");
    newIngredient.save();
    newRecipe.addIngredient(newIngredient);
    List savedIngredients = newRecipe.getIngredients();
    assertEquals(1, savedIngredients.size());
  }

  @Test
  public void update_updatesRecipeName_true() {
    Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
    newRecipe.save();
    newRecipe.update("Beef Pot Pie", "Bake that moo", 5);
    assertEquals("Beef Pot Pie", Recipe.find(newRecipe.getId()).getName());
    assertEquals("Bake that moo", Recipe.find(newRecipe.getId()).getInstruction());
    assertEquals(5, Recipe.find(newRecipe.getId()).getRating());
  }

  // @Test
  // public void delete_deletesRecipe_0() {
  //   Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
  //   newRecipe.save();
  //   Ingredient newIngredient = new Ingredient("Carrot");
  //   newIngredient.save();
  //   newRecipe.addIngredient(newIngredient);
  //   newRecipe.delete();
  //   assertEquals(0, newIngredient.getRecipes().size());
  //   assertEquals("Carrot", newIngredient.getName());
  // }
}
