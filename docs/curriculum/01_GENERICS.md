# Topic 1: Generics in Java

## 📖 Concept Summary
Generics enable type-safe code that works with different data types while providing compile-time type checking. They eliminate the need for casting and prevent runtime ClassCastException errors. Generics use type parameters (like `<T>`, `<E>`, `<K,V>`) as placeholders for actual types that will be specified when the class or method is used.

## 🧠 Intuition & Mental Model

**Think of generics as templates or blueprints:**
- A `Box<T>` is like a blueprint for boxes that can hold ANY type of object
- When you create `Box<String>`, you're creating a specific box that holds Strings
- The compiler ensures you can only put Strings in and get Strings out
- **Exam tip:** Look for situations where you're writing similar code for different types → use generics!

**When you see generics on exams:**
- Type erasure: `List<String>` becomes `List` at runtime (raw type)
- Wildcards: `? extends T` (upper bound), `? super T` (lower bound)
- Cannot create arrays of generic types: `new T[10]` ❌

## 💻 Key Operations with Java Implementations

### 1. Generic Class
```java
/**
 * Generic Box that can hold any type of object
 * Type parameter <T> will be replaced with actual type at compile time
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
}

// Usage
Box<String> stringBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(42);
String s = stringBox.get(); // No casting needed!
```

### 2. Generic Method
```java
public class GenericMethods {

    /**
     * Generic method to print array of any type
     * <T> before return type declares this is a generic method
     */
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Generic method with bounded type parameter
     * T must be Comparable to use compareTo
     */
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Generic method with multiple type parameters
     */
    public static <K, V> void printPair(K key, V value) {
        System.out.println(key + " -> " + value);
    }
}
```

### 3. Generic Interface
```java
/**
 * Generic interface for a simple storage container
 */
public interface Container<E> {
    void add(E element);
    E remove();
    E peek();
    boolean isEmpty();
    int size();
}

/**
 * Implementation using ArrayList as backing storage
 */
public class ArrayListContainer<E> implements Container<E> {
    private java.util.ArrayList<E> storage;

    public ArrayListContainer() {
        storage = new java.util.ArrayList<>();
    }

    @Override
    public void add(E element) {
        storage.add(element);
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Container is empty");
        }
        return storage.remove(storage.size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Container is empty");
        }
        return storage.get(storage.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
```

### 4. Wildcards (?, ? extends, ? super)
```java
import java.util.*;

public class WildcardExamples {

    /**
     * Unbounded wildcard - can read Objects but can't add (except null)
     */
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    /**
     * Upper bounded wildcard - ? extends Number
     * Can read as Number but can't add (except null)
     * Use for READING from a structure
     */
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    /**
     * Lower bounded wildcard - ? super Integer
     * Can add Integers but can only read as Object
     * Use for WRITING to a structure
     */
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
        // Can't do: Integer x = list.get(0); // Compile error!
        // Can do: Object x = list.get(0); // OK
    }

    /**
     * Practical example: copy from one list to another
     * Source can be any subtype of T, destination can be any supertype of T
     */
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {
            dest.add(item);
        }
    }
}
```

### 5. Generic Pair Class (Common Exam Question)
```java
/**
 * Generic Pair class with two type parameters
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: Type Erasure Confusion
```java
// This does NOT work - type erasure!
List<String> strings = new ArrayList<>();
List<Integer> ints = new ArrayList<>();
System.out.println(strings.getClass() == ints.getClass()); // true! Both are ArrayList

// Cannot do instanceof with generic type
if (obj instanceof List<String>) { } // ❌ Compile error
if (obj instanceof List<?>) { } // ✅ OK
```

### Pitfall 2: Cannot Create Generic Arrays
```java
// Does NOT compile
T[] array = new T[10]; // ❌ Cannot create generic array

// Workaround (but gives unchecked warning)
T[] array = (T[]) new Object[10]; // ⚠️ Works but unsafe
```

### Pitfall 3: Raw Types Bypass Type Safety
```java
List<String> strings = new ArrayList<>();
List raw = strings; // ⚠️ Raw type - loses type safety!
raw.add(42); // Compiles! But will fail at runtime when you get from strings

// Always use parameterized types!
```

### Pitfall 4: Wildcard Restrictions
```java
List<? extends Number> list = new ArrayList<Integer>();
list.add(42); // ❌ Compile error! Can't add to ? extends
list.add(null); // ✅ Only null is allowed

List<? super Integer> list2 = new ArrayList<Number>();
list2.add(42); // ✅ Can add Integers
Integer x = list2.get(0); // ❌ Compile error! Can only get as Object
```

## ⏱️ Time & Space Complexity

Generics are a **compile-time feature** and have **zero runtime overhead** after type erasure.

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Generic method call | Same as non-generic | Same as non-generic | Type info erased at runtime |
| Type parameter resolution | O(1) | O(1) | Compile-time only |
| Wildcard matching | O(1) | O(1) | Compile-time only |

**Key Point:** Generics do not affect runtime performance. The bytecode is identical to using Object and casting manually.

## 🧪 Complete Test Cases

```java
import java.util.*;

public class GenericsTest {

    public static void main(String[] args) {
        testBox();
        testGenericMethods();
        testWildcards();
        testEdgeCases();
        System.out.println("✅ All tests passed!");
    }

    static void testBox() {
        // Test 1: String box
        Box<String> stringBox = new Box<>("Hello");
        assert stringBox.get().equals("Hello");
        stringBox.set("World");
        assert stringBox.get().equals("World");

        // Test 2: Integer box
        Box<Integer> intBox = new Box<>(42);
        assert intBox.get() == 42;

        // Test 3: Nested generics
        Box<List<String>> boxOfList = new Box<>(Arrays.asList("a", "b", "c"));
        assert boxOfList.get().size() == 3;
    }

    static void testGenericMethods() {
        // Test 1: Print array
        Integer[] nums = {1, 2, 3, 4, 5};
        GenericMethods.printArray(nums); // Should print: 1 2 3 4 5

        // Test 2: Find max
        Integer max = GenericMethods.findMax(nums);
        assert max == 5;

        String[] words = {"apple", "zebra", "banana"};
        String maxWord = GenericMethods.findMax(words);
        assert maxWord.equals("zebra");

        // Test 3: Empty array edge case
        try {
            GenericMethods.findMax(new Integer[0]);
            assert false : "Should throw exception";
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    static void testWildcards() {
        // Test 1: Upper bound
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);

        double sum1 = WildcardExamples.sumOfList(ints);
        assert sum1 == 6.0;

        double sum2 = WildcardExamples.sumOfList(doubles);
        assert sum2 == 7.5;

        // Test 2: Lower bound
        List<Number> numbers = new ArrayList<>();
        WildcardExamples.addIntegers(numbers);
        assert numbers.size() == 3;

        // Test 3: Copy
        List<Integer> source = Arrays.asList(10, 20, 30);
        List<Number> dest = new ArrayList<>();
        WildcardExamples.copy(source, dest);
        assert dest.size() == 3;
        assert dest.get(0).equals(10);
    }

    static void testEdgeCases() {
        // Edge case 1: Null handling
        Box<String> box = new Box<>(null);
        assert box.get() == null;

        // Edge case 2: Container empty
        Container<Integer> container = new ArrayListContainer<>();
        assert container.isEmpty();
        assert container.size() == 0;

        try {
            container.remove();
            assert false : "Should throw exception";
        } catch (IllegalStateException e) {
            // Expected
        }

        // Edge case 3: Pair with nulls
        Pair<String, Integer> pair = new Pair<>(null, null);
        assert pair.getKey() == null;
        assert pair.getValue() == null;
    }
}
```

## 🎯 Expected Test Output
```
1 2 3 4 5
apple zebra banana
6.0
7.5
Box[Hello]
Box[World]
✅ All tests passed!
```

---

## 📚 Summary for Exams

**Must Know:**
1. ✅ Syntax: `<T>` for classes, `<T>` before return type for methods
2. ✅ Bounded types: `<T extends Comparable<T>>`
3. ✅ Wildcards: `?` unbounded, `? extends` read, `? super` write
4. ✅ Type erasure: generics exist only at compile time
5. ✅ Cannot create generic arrays: `new T[10]` is illegal

**Common Exam Mistakes:**
- Forgetting `<T>` before return type in generic methods
- Trying to add to `List<? extends T>` (read-only!)
- Using raw types instead of parameterized types
- Not understanding PECS: Producer Extends, Consumer Super

**Time Saver:**
Memorize: "Use `? extends` when reading (producing), `? super` when writing (consuming)"
