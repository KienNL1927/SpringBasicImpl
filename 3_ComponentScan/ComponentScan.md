# @ComponentScan in Spring / Spring Boot

## 1. What is @ComponentScan?

`@ComponentScan` is a Spring annotation that tells the Spring container where to look for classes annotated with stereotypes like:

- `@Component`
- `@Service`
- `@Repository`
- `@Controller`
- `@RestController`

Spring will automatically detect these classes and register them as beans in the application context.

---

## 2. Typical Usage

In a Spring Boot application, the main class often looks like this:

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

`@SpringBootApplication` includes `@ComponentScan`, so normally you don’t need to declare it manually unless you want to customize scanning.

If you want to explicitly configure it:

```java
@Configuration
@ComponentScan(basePackages = {"com.example.app", "com.example.lib"})
public class AppConfig {
}
```

---

## 3. Default Behavior

If you do not explicitly specify `@ComponentScan`, Spring Boot:

- Automatically scans the **package of the class annotated with `@SpringBootApplication` and all its sub-packages**.

For example:

- If `MyApplication` is in `com.example`, Spring will scan `com.example.*`.

---

## 4. Attributes of @ComponentScan

Here are the main attributes defined in the annotation:

```java
@ComponentScan(
    value = {},                // Alias for basePackages
    basePackages = {},         // Packages to scan
    basePackageClasses = {},   // Classes whose packages will be scanned
    nameGenerator = BeanNameGenerator.class, // Custom bean name strategy
    scopeResolver = ScopeMetadataResolver.class, // Bean scope resolver
    scopedProxy = ScopedProxyMode.DEFAULT, // Proxy mode for scoped beans
    resourcePattern = "**/*.class", // Classpath scanning pattern
    useDefaultFilters = true, // Whether to include default stereotype annotations
    includeFilters = {},      // Filters to explicitly include
    excludeFilters = {},      // Filters to explicitly exclude
    lazyInit = false          // Whether to lazy-initialize detected beans
)
```

---

### 4.1 `basePackages` / `value`

- **Purpose:** List of packages to scan.
- **Default:** Empty (falls back to the package of the annotated class).
- **Example:**
  ```java
  @ComponentScan(basePackages = {"com.example.services", "com.example.repositories"})
  ```
- `value` is an alias:
  ```java
  @ComponentScan("com.example.services")
  ```

---

### 4.2 `basePackageClasses`

- **Purpose:** Provide classes so Spring can use their packages for scanning.
- **Example:**
  ```java
  @ComponentScan(basePackageClasses = {MyService.class})
  ```
- **Advantage:** Safer than hardcoding package names (works well with refactoring).

---

### 4.3 `nameGenerator`

- **Purpose:** Customize how Spring generates bean names.
- **Default:** `AnnotationBeanNameGenerator`.
- **Example:**
  ```java
  @ComponentScan(nameGenerator = MyCustomBeanNameGenerator.class)
  ```

---

### 4.4 `scopeResolver` and `scopedProxy`

- **scopeResolver:** Define custom logic to determine bean scopes.
- **scopedProxy:** Options are `DEFAULT`, `NO`, `INTERFACES`, `TARGET_CLASS`.

---

### 4.5 `resourcePattern`

- **Purpose:** Pattern for scanning classpath resources.
- **Default:** `**/*.class`.

---

### 4.6 `useDefaultFilters`

- **Default:** `true`.
- If set to `false`, the default stereotype annotations (`@Component`, `@Service`, etc.) are not considered automatically. You must then define filters explicitly.

---

### 4.7 `includeFilters` and `excludeFilters`

- **Purpose:** Fine-tune scanning with filters.

Example:

```java
@ComponentScan(
    basePackages = "com.example",
    includeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX, pattern = ".*Service"
    ),
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION, classes = Controller.class
    )
)
```

**Filter types:**

- `ANNOTATION`
- `ASSIGNABLE_TYPE`
- `ASPECTJ`
- `REGEX`
- `CUSTOM`

---

### 4.8 `lazyInit`

- If `true`, all detected beans will be lazy-initialized (created only when first used).

---

## 5. Relationship with @SpringBootApplication

```java
@SpringBootApplication
= @Configuration
+ @EnableAutoConfiguration
+ @ComponentScan
```

This means Spring Boot automatically performs component scanning from the main application package downward.

---

## 6. Example: Custom Scan

```java
@Configuration
@ComponentScan(
    basePackages = {"com.example.service", "com.example.repository"},
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        classes = Controller.class
    )
)
public class CustomScanConfig {
}
```

This configuration:

- Scans two specific packages
- Excludes any class annotated with `@Controller`.

---

## 7. Best Practices

1. **Place your main class in a root package** to avoid additional configuration.
2. Use `basePackageClasses` instead of hardcoded strings where possible.
3. Use `includeFilters` and `excludeFilters` for fine-grained control only when needed.
4. Avoid setting `useDefaultFilters = false` unless you explicitly want full control over what gets scanned.
