# @Query Annotation in Spring Data JPA

## 1. What is @Query?

`@Query` is an annotation in **Spring Data JPA** that allows you to define custom SQL or JPQL queries directly on repository methods.

- It is placed on repository methods in interfaces that extend `JpaRepository`, `CrudRepository`, etc.
- It provides flexibility when derived query methods (method name parsing) are not sufficient.

---

## 2. Basic Usage

### Example with JPQL:

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

}
```

This uses **JPQL** (Java Persistence Query Language).  
`?1` refers to the first method parameter.

---

## 3. Attributes of @Query

The full annotation signature:

```java
@Query(
    value = "",              // JPQL or native query string
    nativeQuery = false,     // true = use native SQL, false = JPQL (default)
    countQuery = ""          // Custom count query for pagination
)
```

### 3.1 `value`

- **Type:** `String`
- **Description:** The actual query (JPQL or SQL).
- **Example:**
  ```java
  @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
  List<User> findActiveUsers();
  ```

---

### 3.2 `nativeQuery`

- **Type:** `boolean`
- **Default:** `false`
- **Description:** When set to `true`, the query will be treated as a **native SQL query** instead of JPQL.
- **Example:**
  ```java
  @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
  User findByEmailNative(String email);
  ```

---

### 3.3 `countQuery`

- **Type:** `String`
- **Description:** Provides a separate **count query** when using pagination with `Pageable`.
- **When needed:** If the default count query (derived from `value`) is complex or inefficient.
- **Example:**
  ```java
  @Query(
      value = "SELECT u FROM User u WHERE u.status = 'ACTIVE'",
      countQuery = "SELECT count(u) FROM User u WHERE u.status = 'ACTIVE'"
  )
  Page<User> findActiveUsers(Pageable pageable);
  ```

---

## 4. Parameters in @Query

### 4.1 Positional Parameters (`?1`, `?2`, ...)

- **Example:**
  ```java
  @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.age = ?2")
  List<User> findByNameAndAge(String name, int age);
  ```

### 4.2 Named Parameters (`:name`)

- **Example:**
  ```java
  @Query("SELECT u FROM User u WHERE u.name = :name AND u.age = :age")
  List<User> findByNameAndAge(@Param("name") String name, @Param("age") int age);
  ```

---

## 5. SpEL (Spring Expression Language) in @Query

You can use SpEL in queries for dynamic values.

Example:

```java
@Query("SELECT u FROM User u WHERE u.id = :#{#userId}")
User findByIdSpEL(@Param("userId") Long userId);
```

---

## 6. Modifying Queries

Use `@Modifying` together with `@Query` for **UPDATE** or **DELETE** operations.

Example:

```java
@Modifying
@Query("UPDATE User u SET u.status = 'INACTIVE' WHERE u.lastLogin < :cutoff")
int deactivateOldUsers(@Param("cutoff") LocalDate cutoff);
```

Make sure the method is inside a **transaction** (e.g., annotated with `@Transactional`).

---

## 7. Examples

### 7.1 Custom SELECT with native SQL

```java
@Query(value = "SELECT * FROM users WHERE status = ?1", nativeQuery = true)
List<User> findByStatusNative(String status);
```

### 7.2 Custom pagination

```java
@Query(
    value = "SELECT u FROM User u WHERE u.role = :role",
    countQuery = "SELECT count(u) FROM User u WHERE u.role = :role"
)
Page<User> findByRole(@Param("role") String role, Pageable pageable);
```

---

## 8. Best Practices

1. Use **JPQL** when possible; use `nativeQuery = true` only for database-specific SQL.
2. Prefer **named parameters** (`:name`) over positional (`?1`) for clarity.
3. Provide a `countQuery` for complex paginated queries.
4. Use `@Modifying` only for update/delete queries and ensure `@Transactional` is present.
5. Keep queries simple; for very complex logic, consider using Spring Data Specifications or QueryDSL.

