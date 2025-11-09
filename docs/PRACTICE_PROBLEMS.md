# CSCI316 Midterm Practice Problems
## Graded by Difficulty: ⭐ Easy → ⭐⭐⭐⭐⭐ Very Hard

> **Instructions:** Try each problem before looking at solutions. Time yourself (suggested time shown). Solutions are at the end of each section.

---

# SECTION 1: GENERICS

## Problem 1.1 ⭐ - Generic Swap (5 minutes)
Write a generic method `swap` that takes an array and two indices, and swaps the elements at those indices.

**Signature:**
```java
public static <T> void swap(T[] arr, int i, int j)
```

**Test:**
```java
String[] words = {"apple", "banana", "cherry"};
swap(words, 0, 2);
// words → ["cherry", "banana", "apple"]
```

---

## Problem 1.2 ⭐⭐ - Generic Min/Max (10 minutes)
Create a generic class `MinMax<T>` that stores two values and provides methods to get the minimum and maximum. T must be Comparable.

**Requirements:**
- Constructor takes two values
- `getMin()` returns the smaller value
- `getMax()` returns the larger value

---

## Problem 1.3 ⭐⭐⭐ - Bounded Wildcard Method (15 minutes)
Write a method `addAll` that copies all elements from a source list to a destination list. Use proper bounded wildcards (PECS rule).

**Signature:**
```java
public static <T> void addAll(List<? extends T> source, List<? super T> dest)
```

**Why these bounds?**
- Source is producer (we read from it) → extends
- Dest is consumer (we write to it) → super

---

## Problem 1.4 ⭐⭐⭐⭐ - Generic Stack (20 minutes)
Implement a generic `Stack<E>` class with:
- `push(E element)`
- `E pop()`
- `E peek()`
- `boolean isEmpty()`

Use an array-based implementation with initial capacity. Throw exceptions for invalid operations.

---

## Problem 1.5 ⭐⭐⭐⭐⭐ - Generic Pair with Comparator (25 minutes)
Create a generic `Pair<K,V>` class where:
- K must be Comparable
- V can be any type
- Implement `compareTo` to compare pairs by their keys
- Implement `equals` and `hashCode`
- Create a static method `findMaxPair` that finds the pair with the largest key from an array of pairs

---

# SECTION 2: ARRAYS

## Problem 2.1 ⭐ - Count Zeros (5 minutes)
Write a method to count the number of zeros in an integer array.

**Complexity requirement:** O(n) time, O(1) space

---

## Problem 2.2 ⭐⭐ - Shift Array Circularly (10 minutes)
Write a method to rotate an array to the right by k positions.

**Example:**
```java
arr = [1, 2, 3, 4, 5], k = 2
→ [4, 5, 1, 2, 3]
```

**Hint:** Use reverse operations. Complexity must be O(n) time, O(1) space.

---

## Problem 2.3 ⭐⭐⭐ - 2D Array Spiral (20 minutes)
Print a 2D array in spiral order (clockwise from outside to inside).

**Example:**
```
1 2 3
4 5 6  →  Output: 1 2 3 6 9 8 7 4 5
7 8 9
```

---

## Problem 2.4 ⭐⭐⭐⭐ - Scoreboard Median (25 minutes)
Extend the GameEntry scoreboard to maintain a median score efficiently.

**Requirements:**
- Maintain sorted order (existing requirement)
- Provide `getMedian()` method in O(1) time
- Update median after each add/remove in O(1) time

**Hint:** Track median index, update when size changes.

---

## Problem 2.5 ⭐⭐⭐⭐⭐ - Matrix Rotation In-Place (30 minutes)
Rotate a square matrix 90 degrees clockwise in-place (O(1) extra space).

**Example:**
```
1 2 3      7 4 1
4 5 6  →   8 5 2
7 8 9      9 6 3
```

**Steps:**
1. Transpose matrix (swap `[i][j]` with `[j][i]`)
2. Reverse each row

---

# SECTION 3: LINKED LISTS

## Problem 3.1 ⭐ - Count Nodes (5 minutes)
Write a method to count the number of nodes in a singly linked list (without using a size field).

**Complexity:** O(n) time, O(1) space

---

## Problem 3.2 ⭐⭐ - Find Middle Node (10 minutes)
Find the middle node of a singly linked list in one pass (without counting first).

**Hint:** Two pointers - slow and fast.

---

## Problem 3.3 ⭐⭐⭐ - Reverse Singly Linked List (15 minutes)
Reverse a singly linked list in-place.

**Requirements:**
- O(n) time
- O(1) space (iterative, not recursive)
- Return new head

**Pattern:** Three pointers: prev, current, next

---

## Problem 3.4 ⭐⭐⭐⭐ - Merge Two Sorted Lists (20 minutes)
Merge two sorted singly linked lists into one sorted list.

**Example:**
```
L1: 1 → 3 → 5
L2: 2 → 4 → 6
→ 1 → 2 → 3 → 4 → 5 → 6
```

**Complexity:** O(n + m) time, O(1) space

---

## Problem 3.5 ⭐⭐⭐⭐⭐ - Detect and Find Cycle Start (30 minutes)
Detect if a linked list has a cycle. If yes, return the node where the cycle begins.

**Algorithm:**
1. Use Floyd's cycle detection (slow/fast pointers)
2. If cycle found, find start using mathematical property

**Bonus:** Explain why the algorithm works.

---

# SECTION 4: COMPLEXITY ANALYSIS

## Problem 4.1 ⭐ - Single Loop (3 minutes)
What is the time complexity?
```java
for (int i = 0; i < n; i++) {
    System.out.println(arr[i]);
}
```

---

## Problem 4.2 ⭐⭐ - Nested Loops (5 minutes)
What is the time complexity?
```java
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        System.out.println(arr[i] + arr[j]);
    }
}
```

**Hint:** Count total iterations: n + (n-1) + (n-2) + ... + 1

---

## Problem 4.3 ⭐⭐⭐ - Halving Loop (10 minutes)
What is the time and space complexity?
```java
void mystery(int n) {
    if (n <= 1) return;
    System.out.println(n);
    mystery(n / 2);
    mystery(n / 2);
}
```

**Trace for n=8 and count calls.**

---

## Problem 4.4 ⭐⭐⭐⭐ - Master Theorem Application (15 minutes)
Analyze using Master Theorem:

a) `T(n) = 3T(n/2) + O(n)`
b) `T(n) = 4T(n/2) + O(n²)`
c) `T(n) = T(n-1) + O(n)`

---

## Problem 4.5 ⭐⭐⭐⭐⭐ - Tricky Recursion (20 minutes)
Analyze time and space complexity:
```java
int mystery(int n) {
    if (n <= 0) return 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += i;
    }
    return sum + mystery(n / 2);
}
```

**Steps:**
1. Find recurrence relation
2. Solve using recursion tree or substitution
3. Find space complexity (stack depth)

---

# SECTION 5: RECURSION

## Problem 5.1 ⭐ - Sum of Digits (5 minutes)
Write a recursive function to find the sum of digits of a positive integer.

**Example:** `sumDigits(1234)` → 10

---

## Problem 5.2 ⭐⭐ - Power Function (10 minutes)
Write a recursive function to compute `base^exponent` efficiently in O(log n) time.

**Hint:** Use divide and conquer. If exponent is even: `(base^(exp/2))^2`

---

## Problem 5.3 ⭐⭐⭐ - All Subsets (20 minutes)
Generate all subsets of an array recursively.

**Example:** `[1, 2, 3]` → `[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]`

**Pattern:** For each element, either include it or exclude it.

---

## Problem 5.4 ⭐⭐⭐⭐ - Tower of Hanoi (25 minutes)
Solve Tower of Hanoi problem recursively. Move n disks from source to destination using auxiliary peg.

**Rules:**
- Only one disk moved at a time
- Larger disk never on top of smaller

**Print moves:** "Move disk from A to C"

---

## Problem 5.5 ⭐⭐⭐⭐⭐ - Longest Increasing Subsequence (Recursive) (30 minutes)
Find length of longest increasing subsequence using recursion with memoization.

**Example:** `[10, 9, 2, 5, 3, 7, 101, 18]` → 4 (subsequence: [2, 3, 7, 101])

**Approach:**
- For each element, decide: include or exclude
- If include, next element must be greater
- Use memoization for overlapping subproblems

---

# SECTION 6: STACKS

## Problem 6.1 ⭐ - Reverse String with Stack (5 minutes)
Use a stack to reverse a string.

---

## Problem 6.2 ⭐⭐ - Valid Parentheses (10 minutes)
Check if string with `()`, `{}`, `[]` is balanced.

**Example:**
- `"({[]})"` → true
- `"([)]"` → false

---

## Problem 6.3 ⭐⭐⭐ - Postfix Evaluation (15 minutes)
Evaluate postfix expression.

**Example:** `"5 3 + 2 *"` → 16 (i.e., (5+3)*2)

**Algorithm:** Use stack for operands.

---

## Problem 6.4 ⭐⭐⭐⭐ - Infix to Postfix Conversion (25 minutes)
Convert infix to postfix notation.

**Example:** `"3 + 5 * 2"` → `"3 5 2 * +"`

**Use:**
- Operator stack
- Precedence rules
- Parentheses handling

---

## Problem 6.5 ⭐⭐⭐⭐⭐ - Largest Rectangle in Histogram (35 minutes)
Given histogram heights, find the largest rectangle area.

**Example:** heights = `[2, 1, 5, 6, 2, 3]` → 10

**Approach:** Use stack to track indices of increasing heights.

---

# SOLUTIONS

## SECTION 1: GENERICS - SOLUTIONS

### Solution 1.1 ⭐

```java
public class GenericSwap {
    public static <T> void swap(T[] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr.length) {
            throw new IndexOutOfBoundsException();
        }
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cherry"};
        swap(words, 0, 2);
        assert words[0].equals("cherry");
        assert words[2].equals("apple");
        System.out.println("✅ Test passed");
    }
}
```

**Key Points:**
- `<T>` before return type declares generic method
- Works with any reference type
- Validate indices before swapping

---

### Solution 1.2 ⭐⭐

```java
public class MinMax<T extends Comparable<T>> {
    private T value1;
    private T value2;

    public MinMax(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getMin() {
        return value1.compareTo(value2) <= 0 ? value1 : value2;
    }

    public T getMax() {
        return value1.compareTo(value2) >= 0 ? value1 : value2;
    }

    public static void main(String[] args) {
        MinMax<Integer> mm1 = new MinMax<>(10, 5);
        assert mm1.getMin() == 5;
        assert mm1.getMax() == 10;

        MinMax<String> mm2 = new MinMax<>("zebra", "apple");
        assert mm2.getMin().equals("apple");
        assert mm2.getMax().equals("zebra");

        System.out.println("✅ All tests passed");
    }
}
```

**Key Points:**
- Upper bound `<T extends Comparable<T>>` allows compareTo
- No need to cast or use Object comparison

---

### Solution 1.3 ⭐⭐⭐

```java
import java.util.*;

public class BoundedWildcards {
    // Producer Extends, Consumer Super (PECS)
    public static <T> void addAll(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {    // Read from producer
            dest.add(item);         // Write to consumer
        }
    }

    public static void main(String[] args) {
        List<Integer> source = Arrays.asList(1, 2, 3);
        List<Number> dest = new ArrayList<>();

        addAll(source, dest);  // Integer extends Number
        assert dest.size() == 3;
        assert dest.get(0).equals(1);

        List<Object> dest2 = new ArrayList<>();
        addAll(source, dest2);  // Integer's superclass is Object
        assert dest2.size() == 3;

        System.out.println("✅ PECS test passed");
    }
}
```

**Key Points:**
- Source is `? extends T` - can read as T
- Dest is `? super T` - can write T to it
- Flexible: works with supertypes and subtypes

---

### Solution 1.4 ⭐⭐⭐⭐

```java
import java.util.NoSuchElementException;

public class GenericStack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public GenericStack() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    public void push(E element) {
        if (top == data.length - 1) {
            throw new IllegalStateException("Stack is full");
        }
        data[++top] = element;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        E element = data[top];
        data[top--] = null;
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assert stack.peek().equals("C");
        assert stack.pop().equals("C");
        assert stack.pop().equals("B");
        assert !stack.isEmpty();
        assert stack.pop().equals("A");
        assert stack.isEmpty();

        System.out.println("✅ Generic stack test passed");
    }
}
```

**Key Points:**
- Cannot create `new E[]` - use `(E[]) new Object[]`
- Increment top BEFORE adding in push
- Decrement top AFTER removing in pop
- Clear slot after pop (help GC)

---

### Solution 1.5 ⭐⭐⭐⭐⭐

```java
import java.util.Objects;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public int compareTo(Pair<K, V> other) {
        return this.key.compareTo(other.key);
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

    public static <K extends Comparable<K>, V> Pair<K, V> findMaxPair(Pair<K, V>[] pairs) {
        if (pairs == null || pairs.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        Pair<K, V> max = pairs[0];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i].compareTo(max) > 0) {
                max = pairs[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(10, "ten");
        Pair<Integer, String> p2 = new Pair<>(20, "twenty");
        Pair<Integer, String> p3 = new Pair<>(15, "fifteen");

        Pair<Integer, String>[] arr = new Pair[]{p1, p2, p3};
        Pair<Integer, String> max = findMaxPair(arr);

        assert max.getKey() == 20;
        assert max.getValue().equals("twenty");

        System.out.println("✅ Pair with comparator test passed");
    }
}
```

---

## SECTION 2: ARRAYS - SOLUTIONS

### Solution 2.1 ⭐

```java
public class CountZeros {
    public static int countZeros(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num == 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 0, 5, 0};
        assert countZeros(arr) == 3;
        System.out.println("✅ Test passed");
    }
}
```

---

### Solution 2.2 ⭐⭐

```java
import java.util.Arrays;

public class RotateArray {
    public static void rotate(int[] arr, int k) {
        k = k % arr.length;  // Handle k > length
        reverse(arr, 0, arr.length - 1);     // Reverse whole
        reverse(arr, 0, k - 1);               // Reverse first k
        reverse(arr, k, arr.length - 1);     // Reverse rest
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        rotate(arr, 2);
        assert Arrays.equals(arr, new int[]{4, 5, 1, 2, 3});
        System.out.println("✅ Rotate test passed");
    }
}
```

**Complexity:** O(n) time, O(1) space

---

### Solution 2.3 ⭐⭐⭐

```java
import java.util.*;

public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Top row
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // Right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Bottom row
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // Left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        List<Integer> spiral = spiralOrder(matrix);
        List<Integer> expected = Arrays.asList(1,2,3,6,9,8,7,4,5);
        assert spiral.equals(expected);
        System.out.println("✅ Spiral test passed");
    }
}
```

---

**[Additional solutions continue in similar format...]**

---

# EXAM TIPS

1. **Read the question carefully** - note constraints (time/space complexity, in-place, etc.)
2. **Write algorithm first** - pseudocode or comments before coding
3. **Test edge cases** - empty, single element, duplicates, boundaries
4. **Check syntax** - `<T>` before return type, loop bounds, null checks
5. **Manage time** - if stuck, move on and come back

**Good luck on your midterm! 🎯**
