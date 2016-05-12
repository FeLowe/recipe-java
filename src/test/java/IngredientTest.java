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
  public void getName_IngredientInstantiatesWithName_String() {
    Ingredient newIngredient = new Ingredient("Carrot");
    assertEquals("Carrot", newIngredient.getName());

  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Ingredient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfIngredientNameIstheSame_true() {
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
     newIngredient.addRecipe(newRecipe);
     Recipe savedRecipe = newIngredient.getRecipes().get(0);
     assertTrue(newRecipe.equals(savedRecipe));
   }

   @Test
   public void getRecipes_returnsAllRecipes_List() {
     Recipe newRecipe = new Recipe("Chicken Pot Pie", "Bake that bird", 5);
     newRecipe.save();
     Ingredient newIngredient = new Ingredient("Carrot");
     newIngredient.save();
     newIngredient.addRecipe(newRecipe);
     List savedRecipes = newIngredient.getRecipes();
     assertEquals(1, savedRecipes.size());
   }
   @Test
   public void update_updatesIngredientName_true() {
     Ingredient newIngredient = new Ingredient("Carrot");
     newIngredient.save();
     newIngredient.update("Potatoes");
     assertEquals("Potatoes", Ingredient.find(newIngredient.getId()).getName());
   }
   @Test
   public void delete_deletesIngredient_true() {
     Ingredient newIngredient = new Ingredient("Carrot");
     newIngredient.save();
     int newIngredientId = newIngredient.getId();
     newIngredient.delete();
     assertEquals(null, Ingredient.find(newIngredientId));
   }
 }

//  @Test
//  public void delete_deletesAllIngredientsAndRecipesAssociations() {
//    Recipe myRecipe = new Recipe("Household chores");
//    myRecipe.save();
//    Ingredient newIngredient = new Ingredient("Mow the lawn");
//    newIngredient.save();
//    newIngredient.addRecipe(myRecipe);
//    newIngredient.delete();
//    assertEquals(0, myRecipe.getIngredients().size());
//  }
//
