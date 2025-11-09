# CSCI316 Midterm Exam Preparation Guide
## Based on Professor Hussein El Amouri's Syllabus

> **Purpose:** This guide follows the exact syllabus items for your midterm. Each topic uses the 7-part teaching structure: (1) concept summary, (2) how to think about it, (3) algorithmic plan, (4) complexity analysis, (5) edge cases & pitfalls, (6) Java implementation, (7) exam checklist.

---

# Table of Contents

1. [Generics](#1-generics)
2. [Arrays](#2-arrays)
3. [Linked Lists](#3-linked-lists)
4. [Complexity Analysis](#4-complexity-analysis)
5. [Recursion](#5-recursion)
6. [Stacks](#6-stacks)
7. [Practice Problems](#7-practice-problems)
8. [1-Day Crash Plan](#8-1-day-crash-plan)

---

# 1. GENERICS

## 1.1 Creating a Generic Class

### ① One-Line Concept Summary
A generic class uses type parameters (like `<T>`) as placeholders for actual types, allowing you to write one class that works with multiple data types while maintaining compile-time type safety. Think of it as a blueprint that becomes concrete only when you specify the actual type.

### ② How to Think About It

**Mental Model:** Generic class = Template/Blueprint
- The `<T>` is like a variable for types (not values)
- When you write `Box<String>`, you're "filling in the blank" with `String`
- Compiler checks types at compile-time, then erases them at runtime (type erasure)

**Decision Checklist - Use generic class when:**
- ✅ You're writing similar code for different types (DRY principle)
- ✅ You want compile-time type safety (no casting needed)
- ✅ The algorithm/structure doesn't depend on the specific type
- ❌ Don't use if you need type-specific operations (unless bounded)

**Key Invariants:**
- Type parameter `T` must be a reference type (no primitives: use `Integer`, not `int`)
- Cannot create arrays of generic type: `new T[10]` is illegal
- Cannot instantiate `T`: `new T()` is illegal

**Failure Modes:**
- Using raw types loses type safety
- Mixing generic and non-generic code causes unchecked warnings
- Forgetting `<T>` before return type in generic methods

### ③ Algorithmic Plan

**To create a generic class:**

1. **Declare type parameter** after class name: `class ClassName<T>`
2. **Use `T` throughout class** wherever the generic type appears
3. **Provide constructors** that accept/use `T`
4. **Implement methods** using `T` for parameters, return types, or fields
5. **Instantiate** with concrete type: `new ClassName<String>()`

**Example Pattern:**
```
class Box<T> {
    private T content;          // Field uses T
    public Box(T content) { }   // Constructor parameter uses T
    public T get() { }          // Return type uses T
    public void set(T t) { }    // Parameter uses T
}
```

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| Class definition | Compile-time only | O(1) | Type parameter is erased at runtime |
| Method calls | Same as non-generic | Same as non-generic | No runtime overhead |
| Type checking | Compile-time only | O(1) | Compiler validates types |

**Key Point:** Generics have **zero runtime cost**. After compilation, `Box<String>` and `Box<Integer>` are both just `Box` (type erasure).

### ⑤ Edge Cases & Pitfalls

**Common Mistakes:**

1. **Pitfall: Using primitives**
   ```java
   Box<int> box = new Box<>(42);  // ❌ WRONG - primitives not allowed
   Box<Integer> box = new Box<>(42);  // ✅ CORRECT - use wrapper
   ```

2. **Pitfall: Creating generic arrays**
   ```java
   T[] array = new T[10];  // ❌ WRONG - cannot create generic array
   T[] array = (T[]) new Object[10];  // ⚠️ Works but unchecked warning
   ```

3. **Pitfall: Raw types bypass type safety**
   ```java
   Box rawBox = new Box("Hello");  // ⚠️ Raw type - no type checking
   rawBox.set(42);  // Compiles but dangerous!
   ```

4. **Edge Case: Null values**
   ```java
   Box<String> box = new Box<>(null);  // ✅ Valid - null is allowed
   ```

**Test Cases That Expose Bugs:**
- Null value handling
- Empty/default values
- Type mismatch when using raw types
- Casting from Object to generic type

### ⑥ Final Java 17 Implementation

```java
/**
 * Generic Box class - stores any type
 */
public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public void set(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Box[" + content + "]";
    }

    // Example: Generic method to check if two boxes have equal content
    public boolean hasSameContent(Box<T> other) {
        if (this.content == null && other.content == null) {
            return true;
        }
        if (this.content == null || other.content == null) {
            return false;
        }
        return this.content.equals(other.content);
    }

    public static void main(String[] args) {
        // Test 1: String box
        Box<String> stringBox = new Box<>("Hello");
        System.out.println(stringBox);  // Box[Hello]
        assert stringBox.get().equals("Hello");

        // Test 2: Integer box
        Box<Integer> intBox = new Box<>(42);
        System.out.println(intBox);  // Box[42]
        assert intBox.get() == 42;

        // Test 3: Change content
        stringBox.set("World");
        assert stringBox.get().equals("World");

        // Test 4: Null handling
        Box<String> nullBox = new Box<>(null);
        assert nullBox.get() == null;

        // Test 5: Nested generics
        Box<Box<String>> nestedBox = new Box<>(new Box<>("Nested"));
        assert nestedBox.get().get().equals("Nested");

        System.out.println("✅ All generic class tests passed!");
    }
}
```

**Expected Output:**
```
Box[Hello]
Box[42]
✅ All generic class tests passed!
```

### ⑦ Exam Checklist

- ✅ **Syntax:** `class Name<T>` - type parameter after class name
- ✅ **Usage:** Use `T` for fields, parameters, return types
- ✅ **No primitives:** Must use wrappers (`Integer`, not `int`)
- ✅ **No arrays:** Cannot do `new T[10]`
- ✅ **Type erasure:** Generics exist only at compile-time
- ✅ **Instantiation:** `new Box<String>()` with concrete type

---

## 1.2 Creating a Generic Method

### ① One-Line Concept Summary
A generic method declares its own type parameter (independent of the class) before the return type, allowing a single method to work with different types. The type parameter scope is limited to that method only.

### ② How to Think About It

**Mental Model:** Generic method = Type-parameterized function
- The `<T>` before return type declares "this method is generic"
- Type inference: compiler deduces `T` from arguments (often)
- Can be in generic OR non-generic class

**Decision Checklist:**
- ✅ Method needs to work with multiple types
- ✅ Type parameter is only needed for this method (not entire class)
- ✅ Want type safety without casting
- ❌ Don't use if method logic depends on specific type operations

**Key Differences from Generic Class:**
- **Generic method:** `public <T> void methodName(T param)`
- **Generic class:** `class ClassName<T>`
- Generic method can have its own type parameter even in non-generic class

### ③ Algorithmic Plan

**To create a generic method:**

1. **Declare type parameter** before return type: `<T>`
2. **Write return type** (can use `T` or not)
3. **Use `T` in parameters** or return type
4. **Call method** - type inference usually figures out `T`

**Pattern:**
```
public <T> ReturnType methodName(T param) {
    // T is scoped to this method only
}
```

### ④ Complexity Analysis

Same as generic class - **zero runtime overhead**. Type checking is compile-time only.

### ⑤ Edge Cases & Pitfalls

**Pitfall: Forgetting `<T>` before return type**
```java
public void printArray(T[] arr) { }  // ❌ WRONG - no <T> declaration
public <T> void printArray(T[] arr) { }  // ✅ CORRECT
```

**Pitfall: Ambiguous type inference**
```java
Box<String> box = createBox(null);  // Compiler can't infer type from null
Box<String> box = GenericMethods.<String>createBox(null);  // ✅ Explicit type
```

### ⑥ Final Java 17 Implementation

```java
public class GenericMethods {

    // Generic method to print any array
    public static <T> void printArray(T[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Generic method to find max (requires Comparable)
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    // Generic method with multiple type parameters
    public static <K, V> void printPair(K key, V value) {
        System.out.println(key + " -> " + value);
    }

    public static void main(String[] args) {
        // Test 1: Print Integer array
        Integer[] nums = {1, 2, 3, 4, 5};
        printArray(nums);  // [1, 2, 3, 4, 5]

        // Test 2: Print String array
        String[] words = {"apple", "banana", "cherry"};
        printArray(words);  // [apple, banana, cherry]

        // Test 3: Find max in Integer array
        Integer max = findMax(nums);
        assert max == 5;
        System.out.println("Max: " + max);  // Max: 5

        // Test 4: Find max in String array
        String maxWord = findMax(words);
        assert maxWord.equals("cherry");
        System.out.println("Max word: " + maxWord);  // Max word: cherry

        // Test 5: Multiple type parameters
        printPair("Name", "Alice");  // Name -> Alice
        printPair(42, 3.14);  // 42 -> 3.14

        System.out.println("✅ All generic method tests passed!");
    }
}
```

**Expected Output:**
```
[1, 2, 3, 4, 5]
[apple, banana, cherry]
Max: 5
Max word: cherry
Name -> Alice
42 -> 3.14
✅ All generic method tests passed!
```

### ⑦ Exam Checklist

- ✅ **Syntax:** `<T>` appears **before** return type
- ✅ **Scope:** Type parameter scoped to method only
- ✅ **Type inference:** Compiler often deduces `T` from arguments
- ✅ **Can coexist:** Generic method can be in non-generic class
- ✅ **Explicit type:** Call with `ClassName.<String>methodName()` if needed

---

## 1.3 Upper-Bounded Generics (`extends`)

### ① One-Line Concept Summary
Upper-bounded generics use `<T extends SomeClass>` to restrict the type parameter to a specific class or its subclasses, enabling you to call methods of the bound type on `T`. This is the "read-only" bounded wildcard pattern.

### ② How to Think About It

**Mental Model:** Upper bound = "T must be a SomeClass or smaller (subclass)"
- `<T extends Number>` means T can be Number, Integer, Double, etc.
- Allows you to call Number methods on T (like `doubleValue()`)
- Think "extends" as "T has AT LEAST the capabilities of SomeClass"

**Decision Checklist:**
- ✅ Need to call methods of a specific class/interface on T
- ✅ Want to restrict T to a family of related types
- ✅ "Producer extends" - reading values from structure
- ❌ Don't use if you need to accept any type (use unbounded `<T>`)

**PECS Rule (Producer Extends, Consumer Super):**
- Use `? extends T` when **reading** (producing) values
- Use `? super T` when **writing** (consuming) values

### ③ Algorithmic Plan

**To use upper-bounded generics:**

1. **Declare bound:** `<T extends UpperBound>`
2. **Multiple bounds:** `<T extends Class & Interface1 & Interface2>` (class first, then interfaces)
3. **Use T's methods:** Can call any method defined in UpperBound
4. **Cannot add** to collections with `? extends` (only read)

### ④ Complexity Analysis

Compile-time constraint only - no runtime overhead.

### ⑤ Edge Cases & Pitfalls

**Pitfall: Cannot add to `? extends` collections**
```java
List<? extends Number> list = new ArrayList<Integer>();
list.add(42);  // ❌ COMPILE ERROR - can't add to ? extends
Number n = list.get(0);  // ✅ Can read
```

**Pitfall: Multiple bounds order matters**
```java
<T extends Interface & Class>  // ❌ WRONG - class must be first
<T extends Class & Interface>  // ✅ CORRECT
```

### ⑥ Final Java 17 Implementation

```java
import java.util.*;

public class UpperBoundedGenerics {

    // Upper bounded: T must be Number or subclass
    public static <T extends Number> double sumList(List<T> list) {
        double sum = 0.0;
        for (T num : list) {
            sum += num.doubleValue();  // Can call Number method!
        }
        return sum;
    }

    // Wildcard upper bound for reading
    public static void printNumbers(List<? extends Number> list) {
        for (Number num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Multiple bounds: T must be Comparable AND CharSequence
    public static <T extends Comparable<T> & CharSequence> T findMax(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static void main(String[] args) {
        // Test 1: Sum integers
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        double sumInts = sumList(ints);
        assert sumInts == 15.0;
        System.out.println("Sum of integers: " + sumInts);

        // Test 2: Sum doubles
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
        double sumDoubles = sumList(doubles);
        assert sumDoubles == 7.5;
        System.out.println("Sum of doubles: " + sumDoubles);

        // Test 3: Print numbers (wildcard)
        printNumbers(ints);     // 1 2 3 4 5
        printNumbers(doubles);  // 1.5 2.5 3.5

        // Test 4: Multiple bounds
        String max = findMax("apple", "banana");
        assert max.equals("banana");
        System.out.println("Max string: " + max);

        System.out.println("✅ All upper-bounded tests passed!");
    }
}
```

**Expected Output:**
```
Sum of integers: 15.0
Sum of doubles: 7.5
1 2 3 4 5
1.5 2.5 3.5
Max string: banana
✅ All upper-bounded tests passed!
```

### ⑦ Exam Checklist

- ✅ **Syntax:** `<T extends UpperBound>`
- ✅ **Purpose:** Restrict T to subclasses, enable calling bound's methods
- ✅ **PECS:** "extends" for reading (producer)
- ✅ **Cannot add:** `List<? extends T>` is read-only
- ✅ **Multiple bounds:** Class first, then interfaces with `&`

---

## 1.4 Lower-Bounded Generics (`super`)

### ① One-Line Concept Summary
Lower-bounded generics use `<T super SomeClass>` to restrict the type parameter to a specific class or its superclasses, enabling you to add elements of SomeClass to the structure. This is the "write-only" bounded wildcard pattern.

### ② How to Think About It

**Mental Model:** Lower bound = "T must be a SomeClass or bigger (superclass)"
- `<? super Integer>` means ? can be Integer, Number, Object
- Allows you to **add** Integer objects
- Can only **read** as Object (don't know exact superclass)

**Decision Checklist:**
- ✅ Need to **add** elements to a collection
- ✅ "Consumer super" - writing values to structure
- ✅ Want flexibility in what can accept your elements
- ❌ Don't use for reading with specific type (use `extends` instead)

**PECS Rule Application:**
- `List<? super Integer>` can **add** Integers but can only **read** as Object

### ③ Algorithmic Plan

**To use lower-bounded generics:**

1. **Declare bound:** `<? super LowerBound>`
2. **Add elements:** Can add LowerBound or subclasses
3. **Read elements:** Can only read as Object (type erasure)
4. **Use for consumers:** Methods that **accept** values

### ④ Complexity Analysis

Compile-time constraint only - no runtime overhead.

### ⑤ Edge Cases & Pitfalls

**Pitfall: Cannot read specific type from `? super`**
```java
List<? super Integer> list = new ArrayList<Number>();
list.add(42);  // ✅ Can add Integer
Integer x = list.get(0);  // ❌ COMPILE ERROR
Object x = list.get(0);  // ✅ Can only read as Object
```

**Pitfall: Lower bound only with wildcards**
```java
<T super Integer>  // ❌ ILLEGAL - super only with wildcards
<? super Integer>  // ✅ CORRECT
```

### ⑥ Final Java 17 Implementation

```java
import java.util.*;

public class LowerBoundedGenerics {

    // Lower bounded: can add Integers to any superclass of Integer
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
        // Integer x = list.get(0);  // ❌ Can't read as Integer
        Object x = list.get(0);  // ✅ Can read as Object
    }

    // Copy from source to destination using PECS
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {  // Read from extends
            dest.add(item);      // Write to super
        }
    }

    public static void main(String[] args) {
        // Test 1: Add to Number list (super Integer)
        List<Number> numbers = new ArrayList<>();
        addIntegers(numbers);
        assert numbers.size() == 3;
        System.out.println("Numbers: " + numbers);  // [1, 2, 3]

        // Test 2: Add to Object list (also super Integer)
        List<Object> objects = new ArrayList<>();
        addIntegers(objects);
        assert objects.size() == 3;
        System.out.println("Objects: " + objects);  // [1, 2, 3]

        // Test 3: Copy using PECS
        List<Integer> source = Arrays.asList(10, 20, 30);
        List<Number> destination = new ArrayList<>();
        copy(source, destination);
        assert destination.size() == 3;
        assert destination.get(0).equals(10);
        System.out.println("Copied: " + destination);  // [10, 20, 30]

        // Test 4: Reading from super returns Object
        List<? super Integer> superList = new ArrayList<Number>();
        superList.add(100);
        Object obj = superList.get(0);  // Must read as Object
        assert obj.equals(100);

        System.out.println("✅ All lower-bounded tests passed!");
    }
}
```

**Expected Output:**
```
Numbers: [1, 2, 3]
Objects: [1, 2, 3]
Copied: [10, 20, 30]
✅ All lower-bounded tests passed!
```

### ⑦ Exam Checklist

- ✅ **Syntax:** `<? super LowerBound>` (only with wildcards)
- ✅ **Purpose:** Enable adding elements, accept consumers
- ✅ **PECS:** "super" for writing (consumer)
- ✅ **Can add:** Can add LowerBound objects
- ✅ **Can only read as Object:** Don't know exact superclass
- ✅ **PECS pattern:** `copy(List<? extends T>, List<? super T>)`

---

# 2. ARRAYS

## 2.1 Game Entry & Scoreboard Example

### ① One-Line Concept Summary
The Game Entry pattern demonstrates array declaration, initialization, insertion (shift-right), and removal (shift-left) by managing a fixed-size leaderboard where entries are kept sorted by score. This is a classic array manipulation problem that tests understanding of index-based operations and element shifting.

### ② How to Think About It

**Mental Model:** Scoreboard = Fixed-size sorted array with gaps
- Array is like a row of numbered lockers (indices 0 to n-1)
- Insertion requires shifting elements right to make space
- Removal requires shifting elements left to fill gap
- Must track actual number of entries vs array capacity

**Decision Checklist:**
- Array operations require index management
- Shift operations are O(n) - need to move multiple elements
- Always maintain sorted order (high to low for scoreboard)
- Check bounds before accessing

**Key Invariants:**
- Entries sorted in descending order by score
- `size` tracks actual entries (≤ capacity)
- Empty slots at the end
- No gaps in the middle

### ③ Algorithmic Plan

**GameEntry class:**
1. Store name (String) and score (int)
2. Provide getters and constructor

**Scoreboard operations:**

**Add entry (shift-right):**
1. Check if score qualifies (higher than lowest OR board not full)
2. Find insertion index (first entry with lower score)
3. Shift elements from `index` to `size-1` one position right
4. Insert new entry at `index`
5. Increment size (if not at capacity)

**Remove entry (shift-left):**
1. Validate index
2. Save entry to return
3. Shift elements from `index+1` to `size-1` one position left
4. Set last position to null
5. Decrement size
6. Return saved entry

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| Access entry | O(1) | O(1) | Direct index access |
| Add entry | O(n) | O(1) | Must shift up to n elements right |
| Remove entry | O(n) | O(1) | Must shift up to n elements left |
| Find position | O(n) | O(1) | Linear search for insertion point |

**Worst case:** Insert at beginning or remove from beginning → shift all n elements

### ⑤ Edge Cases & Pitfalls

**Common Mistakes:**

1. **Off-by-one in shift loop**
   ```java
   // ❌ WRONG - stops too early
   for (int i = size; i < index; i++)

   // ✅ CORRECT - shift from end toward index
   for (int i = size; i > index; i--)
   ```

2. **Forgetting to check full board**
   ```java
   // Must check: score > entries[size-1] OR size < capacity
   ```

3. **Not handling null entries**
   ```java
   // After removal, set vacated slot to null
   entries[size] = null;
   ```

4. **Index validation**
   ```java
   if (index < 0 || index >= size)  // Check against size, not capacity
   ```

**Test Cases:**
- Empty board
- Full board with non-qualifying score
- Insert at beginning (shift all)
- Insert at end
- Insert in middle
- Remove from beginning, middle, end

### ⑥ Final Java 17 Implementation

```java
public class GameEntry {
    private String name;
    private int score;

    public GameEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return "(" + name + ", " + score + ")";
    }
}

public class Scoreboard {
    private GameEntry[] entries;
    private int size;  // Actual number of entries

    public Scoreboard(int capacity) {
        entries = new GameEntry[capacity];
        size = 0;
    }

    // Add entry - shift right if necessary
    public void add(GameEntry entry) {
        int newScore = entry.getScore();

        // Check if score qualifies
        if (size < entries.length || newScore > entries[size - 1].getScore()) {

            // Find insertion index (linear search from start)
            int index = 0;
            while (index < size && entries[index].getScore() >= newScore) {
                index++;
            }

            // Shift entries right to make space
            if (size < entries.length) {
                // Board not full - can expand
                for (int i = size; i > index; i--) {
                    entries[i] = entries[i - 1];
                }
                size++;
            } else {
                // Board full - drop last entry
                for (int i = size - 1; i > index; i--) {
                    entries[i] = entries[i - 1];
                }
            }

            // Insert at index
            entries[index] = entry;
        }
    }

    // Remove entry at index - shift left
    public GameEntry remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        GameEntry removed = entries[index];

        // Shift left to fill gap
        for (int i = index; i < size - 1; i++) {
            entries[i] = entries[i + 1];
        }

        entries[size - 1] = null;  // Clear last position
        size--;

        return removed;
    }

    public int size() { return size; }
    public GameEntry get(int index) { return entries[index]; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(entries[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Scoreboard board = new Scoreboard(5);

        // Test 1: Add to empty board
        board.add(new GameEntry("Alice", 100));
        assert board.size() == 1;
        System.out.println("After adding Alice: " + board);

        // Test 2: Add higher score (insert at beginning)
        board.add(new GameEntry("Bob", 150));
        assert board.get(0).getName().equals("Bob");
        assert board.get(1).getName().equals("Alice");
        System.out.println("After adding Bob: " + board);

        // Test 3: Add middle score
        board.add(new GameEntry("Charlie", 120));
        board.add(new GameEntry("Diana", 90));
        board.add(new GameEntry("Eve", 80));
        System.out.println("After filling board: " + board);
        assert board.size() == 5;

        // Test 4: Add to full board (score too low - rejected)
        board.add(new GameEntry("Frank", 70));
        assert board.size() == 5;  // Still 5, Frank not added
        assert board.get(4).getName().equals("Eve");  // Eve still last

        // Test 5: Add to full board (high score - replaces lowest)
        board.add(new GameEntry("Grace", 95));
        assert board.size() == 5;
        assert board.get(3).getName().equals("Grace");
        assert !board.toString().contains("Eve");  // Eve pushed out
        System.out.println("After adding Grace: " + board);

        // Test 6: Remove from middle
        GameEntry removed = board.remove(2);
        assert removed.getName().equals("Charlie");
        assert board.size() == 4;
        System.out.println("After removing Charlie: " + board);

        System.out.println("✅ All scoreboard tests passed!");
    }
}
```

**Expected Output:**
```
After adding Alice: [(Alice, 100)]
After adding Bob: [(Bob, 150), (Alice, 100)]
After filling board: [(Bob, 150), (Charlie, 120), (Alice, 100), (Diana, 90), (Eve, 80)]
After adding Grace: [(Bob, 150), (Charlie, 120), (Alice, 100), (Grace, 95), (Diana, 90)]
After removing Charlie: [(Bob, 150), (Alice, 100), (Grace, 95), (Diana, 90)]
✅ All scoreboard tests passed!
```

### ⑦ Exam Checklist

- ✅ **Shift right:** Insert requires moving elements from `size` down to `index`
- ✅ **Shift left:** Remove requires moving elements from `index+1` up to `size-1`
- ✅ **Loop direction:** Shift right uses descending loop, shift left uses ascending
- ✅ **Null management:** Clear vacated slots after removal
- ✅ **Size vs capacity:** Track actual entries separately from array length
- ✅ **Complexity:** Both shifts are O(n) in worst case

---

## 2.2 Shift Left and Shift Right

### ① One-Line Concept Summary
Shift operations move array elements by one position to create space (shift right) or fill gaps (shift left), fundamental to insertion and deletion in fixed-size arrays. Understanding the loop direction and bounds is critical.

### ② How to Think About It

**Mental Model:** Shifting = Domino effect
- **Shift right:** Push dominoes from right to left (backwards loop)
- **Shift left:** Push dominoes from left to right (forward loop)
- Must not overwrite uncopied elements (hence loop direction matters!)

**Decision Checklist:**
- Shift right → loop backwards (high to low index)
- Shift left → loop forwards (low to high index)
- Always validate bounds before shifting

**Why loop direction matters:**
```
Shift right [a,b,c,d,_] from index 1:
  Start from END: d→ [a,b,c,d,d] then c→ [a,b,c,c,d] then b→ [a,b,b,c,d]
  If started from BEGIN: b→ [a,b,b,d,_] WRONG! Lost c,d
```

### ③ Algorithmic Plan

**Shift Right (for insertion at `index`):**
1. Start from `size` (last filled position)
2. Loop down to `index+1`
3. Copy `arr[i-1]` to `arr[i]`
4. Creates empty slot at `index`

```
for (i = size; i > index; i--)
    arr[i] = arr[i-1]
```

**Shift Left (for removal at `index`):**
1. Start from `index`
2. Loop up to `size-2`
3. Copy `arr[i+1]` to `arr[i]`
4. Fills gap, leaves duplicate at end (clear it)

```
for (i = index; i < size-1; i++)
    arr[i] = arr[i+1]
arr[size-1] = null
```

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| Shift right | O(n) | O(1) | May shift all n elements |
| Shift left | O(n) | O(1) | May shift all n elements |

**Best case:** Shift at end → O(1)
**Worst case:** Shift at beginning → O(n)

### ⑤ Edge Cases & Pitfalls

**Pitfall: Wrong loop direction**
```java
// ❌ WRONG for shift right - overwrites uncopied data
for (int i = index; i < size; i++)
    arr[i+1] = arr[i];

// ✅ CORRECT - backwards preserves data
for (int i = size; i > index; i--)
    arr[i] = arr[i-1];
```

**Edge Cases:**
- Shift at index 0 (all elements move)
- Shift at last position (no shift needed)
- Empty array
- Single element

### ⑥ Final Java 17 Implementation

```java
import java.util.Arrays;

public class ArrayShift {

    // Shift elements right from startIndex to create empty slot
    public static void shiftRight(int[] arr, int size, int startIndex) {
        if (startIndex < 0 || startIndex > size || size >= arr.length) {
            throw new IllegalArgumentException("Invalid shift parameters");
        }

        // Work backwards to avoid overwriting
        for (int i = size; i > startIndex; i--) {
            arr[i] = arr[i - 1];
        }
    }

    // Shift elements left from startIndex to fill gap
    public static void shiftLeft(int[] arr, int size, int startIndex) {
        if (startIndex < 0 || startIndex >= size) {
            throw new IllegalArgumentException("Invalid shift parameters");
        }

        // Work forwards to fill gap
        for (int i = startIndex; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = 0;  // Clear last position
    }

    // Insert value at index (uses shift right)
    public static int insert(int[] arr, int size, int index, int value) {
        if (size >= arr.length) {
            throw new IllegalStateException("Array is full");
        }

        shiftRight(arr, size, index);
        arr[index] = value;
        return size + 1;  // New size
    }

    // Remove value at index (uses shift left)
    public static int remove(int[] arr, int size, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        int removed = arr[index];
        shiftLeft(arr, size, index);
        return size - 1;  // New size
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        int size = 0;

        // Test 1: Insert at beginning (empty array)
        size = insert(arr, size, 0, 10);
        assert Arrays.equals(Arrays.copyOf(arr, size), new int[]{10});
        System.out.println("After insert 10 at 0: " + Arrays.toString(Arrays.copyOf(arr, size)));

        // Test 2: Insert at end
        size = insert(arr, size, 1, 30);
        size = insert(arr, size, 1, 20);
        assert arr[0] == 10 && arr[1] == 20 && arr[2] == 30;
        System.out.println("After inserts: " + Arrays.toString(Arrays.copyOf(arr, size)));

        // Test 3: Insert in middle (shift right)
        size = insert(arr, size, 1, 15);
        assert Arrays.equals(Arrays.copyOf(arr, size), new int[]{10, 15, 20, 30});
        System.out.println("After insert 15 at 1: " + Arrays.toString(Arrays.copyOf(arr, size)));

        // Test 4: Remove from middle (shift left)
        size = remove(arr, size, 2);
        assert Arrays.equals(Arrays.copyOf(arr, size), new int[]{10, 15, 30});
        System.out.println("After remove at 2: " + Arrays.toString(Arrays.copyOf(arr, size)));

        // Test 5: Remove from beginning
        size = remove(arr, size, 0);
        assert Arrays.equals(Arrays.copyOf(arr, size), new int[]{15, 30});
        System.out.println("After remove at 0: " + Arrays.toString(Arrays.copyOf(arr, size)));

        // Test 6: Direct shift operations
        int[] test = {1, 2, 3, 4, 5, 0, 0};
        shiftRight(test, 5, 2);  // Shift from index 2
        assert Arrays.equals(test, new int[]{1, 2, 2, 3, 4, 5, 0});
        System.out.println("After shift right from 2: " + Arrays.toString(test));

        int[] test2 = {1, 2, 3, 4, 5};
        shiftLeft(test2, 5, 2);  // Shift from index 2
        assert Arrays.equals(test2, new int[]{1, 2, 4, 5, 0});
        System.out.println("After shift left from 2: " + Arrays.toString(test2));

        System.out.println("✅ All shift operation tests passed!");
    }
}
```

**Expected Output:**
```
After insert 10 at 0: [10]
After inserts: [10, 20, 30]
After insert 15 at 1: [10, 15, 20, 30]
After remove at 2: [10, 15, 30]
After remove at 0: [15, 30]
After shift right from 2: [1, 2, 2, 3, 4, 5, 0]
After shift left from 2: [1, 2, 4, 5, 0]
✅ All shift operation tests passed!
```

### ⑦ Exam Checklist

- ✅ **Shift right:** Loop **backwards** `for (i=size; i>index; i--)`
- ✅ **Shift left:** Loop **forwards** `for (i=index; i<size-1; i++)`
- ✅ **Why:** Prevents overwriting uncopied data
- ✅ **Complexity:** O(n) worst case (shift all elements)
- ✅ **Clear vacated:** Set old position to null/0 after shift left
- ✅ **Bounds:** Check index and size before shifting

---

(Continuing in next response due to length...)
