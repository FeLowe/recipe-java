import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Ingredient {
  private int id;
  private String name;

    public Ingredient(String name){
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public int getId() {
      return id;
    }


    public static List<Ingredient> all() {
      String sql = "SELECT id, name FROM ingredients";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Ingredient.class);
      }
    }

    @Override
    public boolean equals(Object otherIngredient) {
      if (!(otherIngredient instanceof Ingredient)) {
        return false;
      } else {
        Ingredient newIngredient =  (Ingredient) otherIngredient;
        return this.getName().equals(newIngredient.getName()) &&
               this.getId() == newIngredient.getId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO ingredients (name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .executeUpdate()
          .getKey();
      }
    }

    public static Ingredient find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM ingredients WHERE id=:id";
        Ingredient ingredient = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Ingredient.class);
        return ingredient;
      }
    }

  public void update(String newIngredient) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE ingredients SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", newIngredient)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM ingredients WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", this.getId())
        .executeUpdate();

      String joinDeleteQuery = "DELETE FROM recipes_ingredients WHERE ingredient_id = :ingredientId";
        con.createQuery(joinDeleteQuery)
          .addParameter("ingredientId", this.getId())
          .executeUpdate();
    }
  }

  public void addRecipe(Recipe recipe) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO recipes_ingredients (recipe_id, ingredient_id) VALUES (:recipe_id, :ingredient_id)";
    con.createQuery(sql)
      .addParameter("recipe_id", recipe.getId())
      .addParameter("ingredient_id", this.getId())
      .executeUpdate();
  }
}

  public List<Recipe> getRecipes() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT recipe_id FROM recipes_ingredients WHERE ingredient_id = :ingredient_id";
      List<Integer> recipeIds = con.createQuery(joinQuery)
        .addParameter("ingredient_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Recipe> recipes = new ArrayList<Recipe>();

      for (Integer recipeId : recipeIds) {
        String ingredientQuery = "SELECT * FROM recipes WHERE id = :recipeId";
        Recipe recipe = con.createQuery(ingredientQuery)
          .addParameter("recipeId", recipeId)
          .executeAndFetchFirst(Recipe.class);
        recipes.add(recipe);
      }
      return recipes;
    }
  }
}
