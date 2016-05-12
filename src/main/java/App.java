import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;

public class App {
  public static void main (String[] args){

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/recipes/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // Recipe newRecipe = new Recipe (String, String, int);
      model.put("recipes", Recipe.all());
      model.put("template", "templates/recipes-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/recipes/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("recipe_name");
      String instruction = request.queryParams("instruction");
      int rating = Integer.parseInt(request.queryParams("rating"));
      Recipe newRecipe = new Recipe(name, instruction, rating);
      newRecipe.save();
      response.redirect("/recipes/new");
      return null;
    });

    // get("/recipes", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("books", Recipe.all());
    //   model.put("template", "templates/books.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    //
    // post("/recipes", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String name = request.queryParams("name");
    //   Recipe newRecipe = new Recipe(name);
    //   newRecipe.save(); // ***ADDED FOR DB VERSION***
    //   response.redirect("/recipes");
    //   return null;
    // });
  }
}
    // // SHOW SEARCH BOOKS FORM
    // get("/books/search", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //   model.put("allRecipes", Recipe.all());
    //   model.put("template", "templates/book-search.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // // PROCESSES SEARCH FORM
    // post("/books/search", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   // List<String> searchedRecipeId = new ArrayList<String>();
    //   String searchedByRecipeName = request.queryParams("book-search");
    //   int recipeIdThatBeingSearched = Integer.parseInt(request.queryParams("recipe_id"));
    //   Recipe recipeSearched = Recipe.getBooks.(Recipe.find(recipeIdThatBeingSearched));
    //   System.out.println(recipeSearched);
    //   //recipeSearched.getBooks();
    //   model.put("books", searchedRecipeId);
    //   response.redirect("/books/search");
    //   return null;
    // });
    //
    // get("/books/:id", (request,response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Recipe book_id = Integer.parseInt(request.params(":id"));
    //   Book book = Book.find(book_id);
    //   model.put("book", book);
    //   model.put("allRecipes", Recipe.all());
    //   model.put("template", "templates/book.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/recipes/:id", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Recipe recipe = Recipe.find(Integer.parseInt(request.params(":id")));
    //   model.put("recipe", recipe);
    //   model.put("allBooks", Book.all());
    //   model.put("template", "templates/recipe.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/add_books", (request, response) -> {
    //   int bookId = Integer.parseInt(request.queryParams("book_id"));
    //   int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
    //   Recipe recipe = Recipe.find(recipeId);
    //   Book book = Book.find(bookId);
    //   recipe.addBook(book);
    //   response.redirect("/recipes/" + recipeId);
    //   return null;
    // });
    //
    // post("/add_recipes", (request, response) -> {
    //   int bookId = Integer.parseInt(request.queryParams("book_id"));
    //   int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
    //   Recipe recipe = Recipe.find(recipeId);
    //   Book book = Book.find(bookId);
    //   book.addRecipe(recipe);
    //   response.redirect("/books/" + bookId);
    //   return null;
    // });
    // // SHOW UPDATE BOOKS FORM - CLICK ON "a tag(href)"
    // get("/books/:id/edit", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Book book = Book.find(Integer.parseInt(request.params(":id")));
    //   model.put("book", book);
    //   model.put("template", "templates/book-update.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    // // PROCESSES UPDATE BOOKS FORM
    // post("/books/:id/edit", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Book oldBook = Book.find(Integer.parseInt(request.params(":id")));
    //   String newBook = request.queryParams("book-update");
    //   oldBook.update(newBook);
    //   response.redirect("/books");
    //   return null;
    // });
    //
    // // DO THE DELETE BOOK ACTION
    // get("/books/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Book book = Book.find(Integer.parseInt(request.params(":id")));
    //   book.delete();
    //   response.redirect("/books");
    //   return null;
    // });
    //
    // // SHOW UPDATE AUTHORS FORM - CLICK ON "a tag(href)"
    // get("/recipes/:id/edit", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Recipe recipe = Recipe.find(Integer.parseInt(request.params(":id")));
    //   model.put("recipe", recipe);
    //   model.put("template", "templates/recipe-update.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    // // PROCESSES UPDATE AUTHORS FORM
    // post("/recipes/:id/edit", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Recipe oldRecipe = Recipe.find(Integer.parseInt(request.params(":id")));
    //   String newRecipe = request.queryParams("recipe-update");
    //   oldRecipe.update(newRecipe);
    //   response.redirect("/recipes");
    //   return null;
    // });
    //
    // // DO THE DELETE AUTHOR ACTION
    // get("/recipes/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Recipe recipe = Recipe.find(Integer.parseInt(request.params(":id")));
    //   recipe.delete();
    //   response.redirect("/recipes");
    //   return null;
    // });
//
//
//
//   }
// }
