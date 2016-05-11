import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Recipe {
  private String name;

  public Recipe(String name) {
    this.name = name;
  }
}


// private int id;
// private String name;
//
// public Recipe(String name, String instructions, int rating) {
//   this.name = name;
// }
//
// public String getName() {
//   return name;
// }
//
// public int getId() {
//   return id;
// }
//
// public static List<Recipe> all() {
//   String sql = "SELECT id, name FROM authors";
//   try(Connection con = DB.sql2o.open()) {
//     return con.createQuery(sql).executeAndFetch(Recipe.class);
//   }
// }
//
// @Override
// public boolean equals(Object otherRecipe) {
//   if (!(otherRecipe instanceof Recipe)) {
//     return false;
//   } else {
//     Recipe newRecipe = (Recipe) otherRecipe;
//     return this.getName().equals(newRecipe.getName()) &&
//            this.getId() == newRecipe.getId();
//   }
// }
//
// public void save() {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO authors(name) VALUES (:name)";
//     this.id = (int) con.createQuery(sql, true)
//       .addParameter("name", this.name)
//       .executeUpdate()
//       .getKey();
//   }
// }
//
// public static Recipe find(int id) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "SELECT * FROM authors where id=:id";
//     Recipe author = con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetchFirst(Recipe.class);
//     return author;
//   }
// }
//
//
// // public static List<String> findRecipeByName(String name) {
// //   try(Connection con = DB.sql2o.open()) {
// //     String sql = "SELECT id FROM authors WHERE name=:name ";
// //     List<Integer> authorIds = con.createQuery(sql)
// //       .addParameter("name", name)
// //       .executeAndFetch(Integer.class);
// //
// //       List<Integer> bookIds = new ArrayList<>();
// //       for (Integer authorId : authorIds) {
// //         String authorQuery = "SELECT book_id FROM authors_books WHERE id = :authorId";
// //         Integer idForRecipe = con.createQuery(authorQuery)
// //           .addParameter("authorId", authorId)
// //           .executeAndFetchFirst(Integer.class);
// //         bookIds.add(idForRecipe);
// //       }
// //       List<String> booksName = new ArrayList<String>();
// //       for (Integer bookId : bookIds) {
// //         String bookQuery = "SELECT name FROM books WHERE id = :bookId";
// //         String bookNameById = con.createQuery(bookQuery)
// //           .addParameter("bookId", bookId)
// //           .executeAndFetchFirst(String.class);
// //         booksName.add(bookNameById);
// //       }
// //       System.out.println(booksName);
// //     return booksName ;
// //   }
// // }
//
// public void addBook(Book book) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO authors_books (author_id, book_id) VALUES (:author_id, :book_id)";
//     con.createQuery(sql)
//       .addParameter("author_id", this.getId())
//       .addParameter("book_id", book.getId())
//       .executeUpdate();
//   }
// }
//
// public List<Book> getBooks() {
//   try(Connection con = DB.sql2o.open()){
//     String joinQuery = "SELECT book_id FROM authors_books WHERE author_id = :author_id";
//     List<Integer> bookIds = con.createQuery(joinQuery)
//       .addParameter("author_id", this.getId())
//       .executeAndFetch(Integer.class);
//
//     List<Book> books = new ArrayList<Book>();
//
//     for (Integer bookId : bookIds) {
//       String bookQuery = "SELECT * FROM books WHERE id = :bookId";
//       Book book = con.createQuery(bookQuery)
//         .addParameter("bookId", bookId)
//         .executeAndFetchFirst(Book.class);
//       books.add(book);
//     }
//     return books;
//   }
// }
//
//
//
// public void delete() {
//   try(Connection con = DB.sql2o.open()) {
//     String deleteQuery = "DELETE FROM authors WHERE id = :id;";
//       con.createQuery(deleteQuery)
//         .addParameter("id", this.getId())
//         .executeUpdate();
//
//     String joinDeleteQuery = "DELETE FROM authors_books WHERE author_id = :authorId";
//       con.createQuery(joinDeleteQuery)
//         .addParameter("authorId", this.getId())
//         .executeUpdate();
//     }
//   }
//   public void update(String newRecipe) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE authors SET name = :name WHERE id = :id";
//       con.createQuery(sql)
//         .addParameter("name", newRecipe)
//         .addParameter("id", this.id)
//         .executeUpdate();
//     }
//   }
