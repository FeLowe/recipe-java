import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Recipe {
  private int id;
  private String name;
  private String instruction;
  private int rating;

  public Recipe(String name, String instruction, int rating) {
    this.name = name;
    this.instruction = instruction;
    this.rating = rating;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getInstruction() {
    return instruction;
  }

  public int getRating() {
    return rating;
  }

  public static List<Recipe> all() {
    String sql = "SELECT id, name, instruction, rating FROM recipes";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Recipe.class);
    }
  }

  @Override
  public boolean equals(Object otherRecipe) {
    if (!(otherRecipe instanceof Recipe)) {
      return false;
    } else {
      Recipe newRecipe = (Recipe) otherRecipe;
      return this.getName().equals(newRecipe.getName()) &&
      this.getId() == newRecipe.getId() &&
      this.getInstruction().equals(newRecipe.getInstruction());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes (name, instruction, rating) VALUES (:name, :instruction, :rating)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("instruction", this.instruction)
      .addParameter("rating", this.rating)
      .executeUpdate()
      .getKey();
    }
  }

  public static Recipe find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes where id=:id";
      Recipe recipe = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Recipe.class);
      return recipe;
    }
  }

  public void addIngredient(Ingredient ingredient) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes_ingredients (recipe_id, ingredient_id) VALUES (:recipe_id, :ingredient_id)";
      con.createQuery(sql)
      .addParameter("recipe_id", this.getId())//the "recipe_id" and the :recipe_id are the same. "recipe_id" is the key and this.getId() is the value.
      .addParameter("ingredient_id", ingredient.getId())
      .executeUpdate();
    }
  }

  public List<Ingredient> getIngredients() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT ingredient_id FROM recipes_ingredients WHERE recipe_id = :recipe_id";
      List<Integer> ingredientIds = con.createQuery(joinQuery)
      .addParameter("recipe_id", this.getId())
      .executeAndFetch(Integer.class);

      List<Ingredient> ingredients = new ArrayList<Ingredient>();

      for (Integer ingredientId : ingredientIds) {
        String ingredientQuery = "SELECT * FROM ingredients WHERE id = :ingredientId";
        Ingredient ingredient = con.createQuery(ingredientQuery)
        .addParameter("ingredientId", ingredientId)
        .executeAndFetchFirst(Ingredient.class);
        ingredients.add(ingredient);
      }
      return ingredients;
    }
  }
  public void update(String newRecipeName, String newRecipeInstruction, int  newRecipeRating) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE recipes SET name = :name, instruction = :instruction, rating = :rating WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("name", newRecipeName)
      .addParameter("instruction", newRecipeInstruction)
      .addParameter("rating", newRecipeRating)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM recipes WHERE id = :id;";
      con.createQuery(deleteQuery)
      .addParameter("id", this.getId())
      .executeUpdate();

      String joinDeleteQuery = "DELETE FROM recipes_ingredients WHERE recipe_id = :recipeId";
      con.createQuery(joinDeleteQuery)
      .addParameter("recipeId", this.getId())
      .executeUpdate();
    }
  }
}
