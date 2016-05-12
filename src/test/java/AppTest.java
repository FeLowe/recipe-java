import org.sql2o.*; //for DB support
import org.junit.*; // for @Before and @After
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
    public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Cookbook");
    }

  @Test
  public void recipeIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a Recipe"));
    fill("#recipe_name").with("Chicken Pot Pie");
    fill("#instruction").with("Bake that bird"); //dropdown fill?
    fill("#rating").with("4"); //dropdown fill? Index??
    submit(".btn");
    assertThat(pageSource()).contains("Chicken Pot Pie");
    assertThat(pageSource()).contains("Bake that bird");
    assertThat(pageSource()).contains("4");
  }

   @Test
    public void ingredientIsCreatedTest() {
      Ingredient testIngredient = new Ingredient("Potatoes");
      testIngredient.save();
      String url = String.format("http://localhost:4567/ingredient/%d", testIngredient.getId());
      goTo(url);
      fill("#ingredient").with("Beef");
      submit(".btn");
      fill("#ingredient").with("Stew");
      submit(".btn");
      assertThat(pageSource()).contains("Beef");
      assertThat(pageSource()).contains("Stew");
  }

  // @Test
  // public void recipeShowPageDisplaysName() {
  //   Recipe testRecipe = new Recipe("Paulo Coelho");
  //   testRecipe.save();
  //   String url = String.format("http://localhost:4567/recipes/%d", testRecipe.getId());
  //   goTo(url);
  //   assertThat(pageSource()).contains("Paulo Coelho");
  // }

  // @Test
  // public void ingredientShowPageDisplaysName() {
  //   Ingredient testIngredient = new Ingredient("The Alchemist");
  //   testIngredient.save();
  //   String url = String.format("http://localhost:4567/ingredients/%d", testIngredient.getId());
  //   goTo(url);
  //   assertThat(pageSource()).contains("The Alchemist");
  // }
  //
  // @Test
  // public void ingredientIsAddedToRecipe() {
  //   Recipe testRecipe = new Recipe("Paulo Coelho");
  //   testRecipe.save();
  //   Ingredient testIngredient = new Ingredient("The Alchemist");
  //   testIngredient.save();
  //   String url = String.format("http://localhost:4567/recipes/%d", testRecipe.getId());
  //   goTo(url);
  //   fillSelect("#ingredient_id").withText("The Alchemist");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("<li>");
  //   assertThat(pageSource()).contains("The Alchemist");
  // }
  //
  // @Test
  // public void recipeIsAddedToIngredient() {
  //   Recipe testRecipe = new Recipe("Paulo Coelho");
  //   testRecipe.save();
  //   Ingredient testIngredient = new Ingredient("The Alchemist");
  //   testIngredient.save();
  //   String url = String.format("http://localhost:4567/ingredients/%d", testIngredient.getId());
  //   goTo(url);
  //   fillSelect("#recipe_id").withText("Paulo Coelho");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("<li>");
  //   assertThat(pageSource()).contains("Paulo Coelho");
  // }
  // @Test
  // public void ingredientUpdate() {
  //   Ingredient myIngredient = new Ingredient("Veronica Decides To Die");
  //   myIngredient.save();
  //   String ingredientPath = String.format("http://localhost:4567/ingredients/%d", myIngredient.getId());
  //   goTo(ingredientPath);
  //   click("a", withText("Edit this ingredient"));
  //   fill("#ingredient-update").with("Veronica Decides To Live");
  //   submit("#update-ingredient");
  //   assertThat(pageSource()).doesNotContain("Veronica Decides To Die");
  // }
  //
  // @Test
  // public void ingredientDelete() {
  //   Ingredient myIngredient = new Ingredient("Veronica Decides To Die");
  //   myIngredient.save();
  //   String ingredientPath = String.format("http://localhost:4567/ingredients/%d", myIngredient.getId());
  //   goTo(ingredientPath);
  //   click("a", withText("Delete this ingredient"));
  //   String allIngredientsPath = String.format("http://localhost:4567/ingredients/");
  //   goTo(allIngredientsPath);
  //   assertThat(pageSource()).doesNotContain("Veronica Decides To Die");
  // }
  // @Test
  // public void recipeUpdate() {
  //   Recipe myRecipe = new Recipe("Paulo Coelho");
  //   myRecipe.save();
  //   String recipePath = String.format("http://localhost:4567/recipes/%d", myRecipe.getId());
  //   goTo(recipePath);
  //   click("a", withText("Edit this recipe"));
  //   fill("#recipe-update").with("Paulo De Coelho");
  //   submit("#update-recipe");
  //   assertThat(pageSource()).doesNotContain("Paulo Coelho");
  // }
  //
  // @Test
  // public void recipeDelete() {
  //   Recipe myRecipe = new Recipe("Paulo Coelho");
  //   myRecipe.save();
  //   String recipePath = String.format("http://localhost:4567/recipes/%d", myRecipe.getId());
  //   goTo(recipePath);
  //   click("a", withText("Delete this recipe"));
  //   String allRecipesPath = String.format("http://localhost:4567/recipes/");
  //   goTo(allRecipesPath);
  //   assertThat(pageSource()).doesNotContain("Paulo Coelho");
  // }
  //
  // @Test
  // public void ingredientSearchByRecipeName() {
  //   Recipe testRecipe = new Recipe("Paulo Coelho");
  //   testRecipe.save();
  //   Ingredient testIngredient = new Ingredient("The Alchemist");
  //   testIngredient.save();
  //   String url = String.format("http://localhost:4567/ingredients/%d", testIngredient.getId());
  //   goTo(url);
  //   fillSelect("#recipe_id").withText("Paulo Coelho");
  //   submit(".btn");
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Search for a ingredient"));
  //   fill("#ingredient-search").with("Paulo Coelho");
  //   submit("#search-button");
  //   assertThat(pageSource()).contains("The Alchemist");
  // }
}
