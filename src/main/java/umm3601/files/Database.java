package umm3601.files;


import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class Database {

  private User[] allUsers;
  private Todo[] allTodos;

  public Database(String data) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(data);

    if (data.contains("todos")) {
      allTodos = gson.fromJson(reader, Todo[].class);
    } else if (data.contains("users")) {
      allUsers = gson.fromJson(reader, User[].class);
    } else {
      throw new IOException("Data file does not exist.");
    }
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public User getUser(String id) {
    return Arrays.stream(allUsers).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get a to-do specified by the given ID. Return
   * `null` if there is no entries with that ID.
   *
   * @param id the ID of the desired entries
   * @return the user with the given ID, or null if there is no entries
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public User[] listUsers(Map<String, String[]> queryParams) {
    User[] filteredUsers = allUsers;

    // Filter age if defined
    if (queryParams.containsKey("age")) {
      int targetAge = Integer.parseInt(queryParams.get("age")[0]);
      filteredUsers = filterUsersByAge(filteredUsers, targetAge);
    }
    // Process other query parameters here...

    return filteredUsers;
  }

  /**
   * Get an array of all the todos satisfying the queries in the params
   *
   * @param queryParams map of all the required key-value pairs for the query
   * @return an array of all users matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter owner if defined
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    // Filter status if defined
    if (queryParams.containsKey("status")) {
      Boolean targetStatus = Boolean.parseBoolean(queryParams.get("status")[0]);
      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }

    // Filter content if defined
    if (queryParams.containsKey("content")) {
      String targetContent = queryParams.get("content")[0];
      filteredTodos = filterTodosByContent(filteredTodos, targetContent);
    }

    // Filter category if defined
    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    // Filter limit of todos if defined
    if (queryParams.containsKey("limit")) {
      filteredTodos = Arrays.stream(filteredTodos).limit(Integer.parseInt(queryParams.get("limit")[0])).toArray(Todo[]::new);
    }
    
    return filteredTodos;
  }

  /**
   * Get an array of all the todos having the target owner
   *
   * @param todos       the list of todos to filter by owner
   * @param targetOwner an owner to look for
   * @return an array of all todos that have the target owner
   */
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.contains(targetOwner)).toArray(Todo[]::new);
  }

  /**
   * Get an array of all todos having the target status
   *
   * @param todos        the list of todos to filter by status
   * @param targetStatus status to look for
   * @return an array of all todos from the given list that have the target status
   */
  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param users     the list of users to filter by age
   * @param targetAge the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public User[] filterUsersByAge(User[] users, int targetAge) {
    return Arrays.stream(users).filter(x -> x.age == targetAge).toArray(User[]::new);
  }

  /**
   * Get an array of all todos having the target content
   *
   * @param todos         the list of todos to filter by content
   * @param targetContent a content string to look for
   * @return an array of all todos form the list that have that content in their bodies
   */
  public Todo[] filterTodosByContent(Todo[] todos, String targetContent) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetContent)).toArray(Todo[]::new);
  }

  /**
   * Get an array of all todos having the target category
   *
   * @param todos          the list of todos to filter by category
   * @param targetCategory a category to look for
   * @return an array of all todos form the list that have that category
   */
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

}
