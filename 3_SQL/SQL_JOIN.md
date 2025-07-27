# SQL Joins 

## Overview

SQL joins are used to combine rows from two or more tables based on a related column between them. 

## Sample Tables


### employees
```
| id | name    | department_id |
|----|---------|---------------|
| 1  | Alice   | 1             |
| 2  | Bob     | 2             |
| 3  | Charlie | 1             |
| 4  | Diana   | NULL          |
```

### departments
```
| id | name        |
|----|-------------|
| 1  | Engineering |
| 2  | Marketing   |
| 3  | Sales       |
```

## Types of Joins

### 1. INNER JOIN

Returns only rows that have matching values in both tables.

```sql
SELECT e.name, d.name AS department
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
```

**Result:**
```
| name    | department  |
|---------|-------------|
| Alice   | Engineering |
| Bob     | Marketing   |
| Charlie | Engineering |
```

### 2. LEFT JOIN (LEFT OUTER JOIN)

Returns all rows from the left table and matching rows from the right table. NULL values for non-matching rows from the right table.

```sql
SELECT e.name, d.name AS department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;
```

**Result:**
```
| name    | department  |
|---------|-------------|
| Alice   | Engineering |
| Bob     | Marketing   |
| Charlie | Engineering |
| Diana   | NULL        |
```

### 3. RIGHT JOIN (RIGHT OUTER JOIN)

Returns all rows from the right table and matching rows from the left table. NULL values for non-matching rows from the left table.

```sql
SELECT e.name, d.name AS department
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.id;
```

**Result:**
```
| name    | department  |
|---------|-------------|
| Alice   | Engineering |
| Charlie | Engineering |
| Bob     | Marketing   |
| NULL    | Sales       |
```

### 4. FULL OUTER JOIN

Returns all rows from both tables. NULL values where there are no matches.

```sql
SELECT e.name, d.name AS department
FROM employees e
FULL OUTER JOIN departments d ON e.department_id = d.id;
```

**Result:**
```
| name    | department  |
|---------|-------------|
| Alice   | Engineering |
| Bob     | Marketing   |
| Charlie | Engineering |
| Diana   | NULL        |
| NULL    | Sales       |
```

### 5. CROSS JOIN

Returns the Cartesian product of both tables (every row from first table combined with every row from second table).

```sql
SELECT e.name, d.name AS department
FROM employees e
CROSS JOIN departments d;
```

**Result:** 12 rows (4 employees × 3 departments)
```
| name    | department  |
|---------|-------------|
| Alice   | Engineering |
| Alice   | Marketing   |
| Alice   | Sales       |
| Bob     | Engineering |
| Bob     | Marketing   |
| Bob     | Sales       |
| ...     | ...         |
```

### 6. SELF JOIN

Joins a table with itself. Useful for hierarchical data or comparing rows within the same table.

```sql
-- Example: Find employees in the same department
SELECT e1.name AS employee1, e2.name AS employee2
FROM employees e1
INNER JOIN employees e2 ON e1.department_id = e2.department_id
WHERE e1.id < e2.id;
```

**Result:**
```
| employee1 | employee2 |
|-----------|-----------|
| Alice     | Charlie   |
```

## Quick Reference

| Join Type    | Description                                          | When to Use                                    |
|--------------|------------------------------------------------------|------------------------------------------------|
| INNER        | Only matching rows from both tables                 | When you need data that exists in both tables |
| LEFT         | All rows from left table + matching from right      | When you need all records from the main table |
| RIGHT        | All rows from right table + matching from left      | Less common; usually LEFT JOIN is preferred   |
| FULL OUTER   | All rows from both tables                            | When you need complete data from both tables   |
| CROSS        | All possible combinations                            | Rarely used; be careful of large result sets  |
| SELF         | Table joined with itself                             | Hierarchical data or intra-table comparisons  |

## Best Practices

1. **Always specify join conditions** to avoid accidental cross joins
2. **Use table aliases** (e.g., `employees e`) for cleaner, readable queries
3. **Choose the right join type** based on your data requirements
4. **Consider performance** - INNER JOINs are typically fastest
5. **Test with sample data** to ensure you get expected results

## Common Pitfalls

- Forgetting the ON clause (creates a cross join)
- Mixing up LEFT and RIGHT joins
- Not handling NULL values properly
- Creating unintentional many-to-many relationships

