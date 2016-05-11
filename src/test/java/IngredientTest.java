import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class IngredientTest {

@Rule
 public DatabaseRule database = new DatabaseRule();

}

//
//
// @Test
// public void Book_instantiatesCorrectly_true(){
//   Book myBook = new Book("Mow the lawn");
//   assertEquals(true, myBook instanceof Book);
// }
//
// @Test
//  public void getName_bookWithName_String(){
//    Book myBook = new Book("Mow the lawn");
//    assertEquals("Mow the lawn", myBook.getName());
//  }
//
//  @Test
//  public void all_emptyAtFirst() {
//    assertEquals(Book.all().size(), 0);
//  }
//
//  @Test
//  public void equals_returnsTrueIfNamesAreTheSame() {
//    Book firstBook = new Book("Mow the lawn");
//    Book secondBook = new Book("Mow the lawn");
//    assertTrue(firstBook.equals(secondBook));
//  }
//
//  @Test
//  public void save_returnsTrueIfNamesAretheSame() {
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    assertTrue(Book.all().get(0).equals(myBook));
//  }
//
//  @Test
//  public void save_assignsIdToObject() {
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    Book savedBook = Book.all().get(0);
//    assertEquals(myBook.getId(), savedBook.getId());
//  }
//
//  @Test
//  public void find_findsBooksInDatabase_True() {
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    Book savedBook = Book.find(myBook.getId());
//    assertTrue(myBook.equals(savedBook));
//  }
//
//  @Test
//  public void addAuthor_addsAuthorToBook() {
//    Author myAuthor = new Author("Household chores");
//    myAuthor.save();
//    Book myBook = new Book("Mow the lawn");
//    myBook.save();
//    myBook.addAuthor(myAuthor);
//    Author savedAuthor = myBook.getAuthors().get(0);
//    assertTrue(myAuthor.equals(savedAuthor));
//  }
//
//  @Test
//  public void getAuthors_returnsAllAuthors_List() {
//    Author myAuthor = new Author("Household chores");
//    myAuthor.save();
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
