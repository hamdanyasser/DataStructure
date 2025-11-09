# CSCI316 Midterm Prep - Part 3: Recursion Functions

> **Continuation of MIDTERM_PREP_GUIDE.md and PART2** - Focus on recursive algorithms and thinking

---

# 4. RECURSION FUNCTIONS

## 4.1 Understanding Recursion Fundamentals

### ① One-Line Concept Summary
Recursion is a problem-solving technique where a function calls itself with a smaller subproblem, relying on a base case to terminate and combining results through the call stack to solve the original problem.

### ② How to Think About It

**Mental Model:** Russian nesting dolls (Matryoshka)
- Each doll contains a smaller version of itself
- Innermost doll (base case) has no doll inside
- To open all: open outer → open next → ... → reach innermost → close back up
- Call stack = the dolls you've opened but not closed yet

**The Recursive Leap of Faith:**
1. **Assume** the function works correctly for smaller inputs
2. **Use** that assumption to solve current problem
3. **Define** base case(s) to stop recursion
4. **Trust** the call stack to manage the details

**Decision Checklist - Use recursion when:**
- ✅ Problem naturally divides into smaller identical subproblems
- ✅ Tree/graph traversal (inherently recursive structures)
- ✅ Divide-and-conquer algorithms (merge sort, binary search)
- ✅ Backtracking problems (N-queens, sudoku)
- ✅ Mathematical definitions are recursive (factorial, Fibonacci)
- ❌ Don't use if iterative solution is clearer and more efficient
- ❌ Don't use if deep recursion causes stack overflow (use iteration or tail recursion)
- ❌ Don't use if overlapping subproblems without memoization (inefficient)

**Key Components of Every Recursive Function:**
1. **Base case(s):** Simplest case that doesn't recurse (stops infinite recursion)
2. **Recursive case:** Calls itself with smaller/simpler input
3. **Progress toward base case:** Each call must get closer to termination
4. **Combination logic:** How to use recursive results to solve current problem

**Common Recursion Patterns:**
- **Linear recursion:** Single recursive call (factorial, sum array)
- **Binary recursion:** Two recursive calls (Fibonacci, tree traversal)
- **Tail recursion:** Recursive call is last operation (can be optimized)
- **Multiple recursion:** More than two calls (Tower of Hanoi)

**Failure Modes:**
- **Stack overflow:** Too many recursive calls (no base case or wrong progress)
- **Infinite recursion:** Base case never reached
- **Inefficiency:** Redundant calculations (e.g., naive Fibonacci)
- **Wrong base case:** Returns incorrect value or doesn't cover all terminating cases

### ③ Algorithmic Plan for Writing Recursive Functions

**Step-by-Step Approach:**

1. **Identify base case(s):**
   - What's the simplest input that needs no recursion?
   - What should it return?
   - Handle edge cases (empty, zero, negative, etc.)

2. **Define recursive case:**
   - How to break problem into smaller identical problem(s)?
   - What parameters change in recursive call?
   - Are we getting closer to base case?

3. **Combine results:**
   - How to use recursive call's result?
   - What operation combines it with current level?

4. **Verify termination:**
   - Does every path eventually hit base case?
   - Is progress guaranteed?

**Template:**
```java
returnType recursiveFunction(parameters) {
    // 1. Base case(s)
    if (baseCondition) {
        return baseValue;
    }

    // 2. Recursive case
    // Make problem smaller
    smallerProblem = reduceInput(parameters);

    // 3. Recursive call (leap of faith)
    result = recursiveFunction(smallerProblem);

    // 4. Combine results
    return combineWithCurrent(result);
}
```

### ④ Complexity Analysis Framework

**Time Complexity:**
- Count number of recursive calls × work per call
- Use recurrence relations: T(n) = aT(n/b) + f(n)
- Master theorem for divide-and-conquer
- Draw recursion tree to visualize

**Space Complexity:**
- Call stack depth × space per call
- Linear recursion: O(n) stack space
- Binary recursion: O(n) for depth-first traversal
- Tail recursion: O(1) if optimized by compiler (Java doesn't always optimize)

**Common Patterns:**
- T(n) = T(n-1) + O(1) → O(n) time, O(n) space (factorial)
- T(n) = 2T(n-1) + O(1) → O(2^n) time (Fibonacci naive)
- T(n) = T(n/2) + O(1) → O(log n) time (binary search)
- T(n) = 2T(n/2) + O(n) → O(n log n) time (merge sort)

### ⑤ Edge Cases & Common Pitfalls

**Pitfall 1: Missing base case**
```java
// ❌ WRONG - infinite recursion!
int factorial(int n) {
    return n * factorial(n - 1);  // No base case!
}

// ✅ CORRECT
int factorial(int n) {
    if (n == 0) return 1;  // Base case
    return n * factorial(n - 1);
}
```

**Pitfall 2: Wrong base case**
```java
// ❌ WRONG - doesn't handle n=0
int factorial(int n) {
    if (n == 1) return 1;  // What if n=0?
    return n * factorial(n - 1);
}

// ✅ CORRECT - handles n=0 and n=1
int factorial(int n) {
    if (n <= 1) return 1;  // Covers 0 and 1
    return n * factorial(n - 1);
}
```

**Pitfall 3: Not making progress**
```java
// ❌ WRONG - infinite loop, n never decreases
int bad(int n) {
    if (n == 0) return 0;
    return bad(n);  // Same n!
}
```

**Pitfall 4: Redundant calculations (overlapping subproblems)**
```java
// ❌ INEFFICIENT - fib(5) calls fib(3) twice!
int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);  // O(2^n)!
}
```

**Edge Cases to Test:**
- Empty input (n=0, empty array, empty string)
- Single element (n=1, one-element array)
- Negative numbers (if applicable)
- Very large n (stack overflow risk)
- Null inputs

---

## 4.2 Recursion Function Examples

### Example 1: Factorial

#### Objective
Calculate n! = n × (n-1) × (n-2) × ... × 1 using recursion.

#### Summary
**Recursive definition:**
- Base: 0! = 1, 1! = 1
- Recursive: n! = n × (n-1)!

**Why recursion works:**
Factorial of n depends on factorial of n-1, which is smaller.

#### Algorithm
1. **Base case:** if n ≤ 1, return 1
2. **Recursive case:** return n × factorial(n-1)
3. **Progress:** n decreases by 1 each call

#### Complexity
- **Time:** O(n) - n recursive calls
- **Space:** O(n) - call stack depth

#### Edge Cases
- n = 0 → returns 1
- n = 1 → returns 1
- Negative n → undefined (add validation)
- Large n → stack overflow or integer overflow

#### Java 17 Code

```java
public class FactorialRecursive {

    /**
     * Calculate factorial recursively
     * @param n non-negative integer
     * @return n! = n × (n-1) × ... × 1
     */
    public static int factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }

        // Recursive case: n! = n × (n-1)!
        return n * factorial(n - 1);
    }

    /**
     * Tail-recursive version (more efficient in theory, but Java doesn't optimize)
     */
    public static int factorialTail(int n, int accumulator) {
        if (n <= 1) {
            return accumulator;
        }
        return factorialTail(n - 1, n * accumulator);
    }

    public static int factorialTail(int n) {
        return factorialTail(n, 1);
    }

    /**
     * With validation for negative numbers
     */
    public static int factorialSafe(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial undefined for negative numbers");
        }
        if (n <= 1) return 1;
        return n * factorialSafe(n - 1);
    }

    public static void main(String[] args) {
        // Test base cases
        assert factorial(0) == 1 : "0! should be 1";
        assert factorial(1) == 1 : "1! should be 1";

        // Test recursive cases
        assert factorial(5) == 120 : "5! should be 120";
        assert factorial(6) == 720 : "6! should be 720";

        // Test tail recursive version
        assert factorialTail(5) == 120 : "Tail recursive 5! should be 120";

        // Test with validation
        try {
            factorialSafe(-1);
            assert false : "Should throw exception for negative";
        } catch (IllegalArgumentException e) {
            // Expected
        }

        // Display results
        System.out.println("factorial(0) = " + factorial(0));
        System.out.println("factorial(1) = " + factorial(1));
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(10) = " + factorial(10));
        System.out.println("factorialTail(5) = " + factorialTail(5));

        System.out.println("✅ All factorial tests passed!");
    }
}
```

#### Expected Output
```
factorial(0) = 1
factorial(1) = 1
factorial(5) = 120
factorial(10) = 3628800
factorialTail(5) = 120
✅ All factorial tests passed!
```

#### Checklist
- ✅ Base case: n ≤ 1 returns 1
- ✅ Recursive case: n × factorial(n-1)
- ✅ Progress: n decreases each call
- ✅ Time: O(n), Space: O(n)
- ✅ Edge cases: 0, 1, negative handled

---

### Example 2: Fibonacci Sequence

#### Objective
Calculate the nth Fibonacci number where F(n) = F(n-1) + F(n-2), with F(0)=0, F(1)=1.

#### Summary
**Recursive definition:**
- Base: F(0) = 0, F(1) = 1
- Recursive: F(n) = F(n-1) + F(n-2)

**Warning:** Naive recursion is O(2^n) due to overlapping subproblems!

**Optimization:** Use memoization or iterative approach for efficiency.

#### Algorithm
**Naive recursion:**
1. **Base cases:** if n ≤ 1, return n
2. **Recursive case:** return fib(n-1) + fib(n-2)

**With memoization:**
1. Create cache/memo array
2. If n already computed, return cached value
3. Otherwise, compute recursively and cache result

#### Complexity
**Naive recursion:**
- **Time:** O(2^n) - exponential due to redundant calls
- **Space:** O(n) - maximum call stack depth

**With memoization:**
- **Time:** O(n) - each F(i) computed once
- **Space:** O(n) - call stack + cache

#### Edge Cases
- n = 0 → returns 0
- n = 1 → returns 1
- Negative n → undefined
- Large n → naive version times out, memoized works

#### Java 17 Code

```java
import java.util.HashMap;
import java.util.Map;

public class FibonacciRecursive {

    /**
     * Naive recursive Fibonacci - O(2^n) TIME!
     * DO NOT use for n > 40!
     */
    public static int fibNaive(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }

        // Recursive case - makes TWO calls
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    /**
     * Fibonacci with memoization - O(n) time
     * Uses HashMap to cache computed values
     */
    public static int fibMemo(int n, Map<Integer, Integer> memo) {
        // Base cases
        if (n <= 1) {
            return n;
        }

        // Check if already computed
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Compute and cache
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);

        return result;
    }

    public static int fibMemo(int n) {
        return fibMemo(n, new HashMap<>());
    }

    /**
     * Fibonacci with array memoization (faster than HashMap)
     */
    public static int fibMemoArray(int n) {
        if (n <= 1) return n;

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        return fibMemoHelper(n, memo);
    }

    private static int fibMemoHelper(int n, int[] memo) {
        if (memo[n] != 0 || n == 0) {
            return memo[n];
        }

        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        // Test base cases
        assert fibNaive(0) == 0 : "fib(0) should be 0";
        assert fibNaive(1) == 1 : "fib(1) should be 1";

        // Test small values (naive is OK)
        assert fibNaive(5) == 5 : "fib(5) should be 5";
        assert fibNaive(10) == 55 : "fib(10) should be 55";

        // Test memoized version
        assert fibMemo(40) == 102334155 : "fib(40) should be 102334155";
        assert fibMemoArray(40) == 102334155 : "fib(40) array memo should match";

        // Display sequence
        System.out.println("First 10 Fibonacci numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibMemo(i) + " ");
        }
        System.out.println();

        // Performance comparison
        long startTime = System.nanoTime();
        int result1 = fibNaive(35);  // Slow!
        long naiveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int result2 = fibMemo(35);   // Fast!
        long memoTime = System.nanoTime() - startTime;

        System.out.println("\nPerformance for n=35:");
        System.out.println("Naive: " + naiveTime / 1_000_000 + " ms");
        System.out.println("Memoized: " + memoTime / 1_000_000 + " ms");
        System.out.println("Speedup: " + (naiveTime / memoTime) + "x");

        System.out.println("\n✅ All Fibonacci tests passed!");
    }
}
```

#### Expected Output
```
First 10 Fibonacci numbers:
0 1 1 2 3 5 8 13 21 34

Performance for n=35:
Naive: 45 ms
Memoized: 0 ms
Speedup: 225000x

✅ All Fibonacci tests passed!
```

#### Checklist
- ✅ Base cases: n ≤ 1 returns n
- ✅ Recursive: fib(n-1) + fib(n-2)
- ✅ Naive: O(2^n) time - exponential!
- ✅ Memoization: O(n) time, O(n) space
- ✅ Always use memoization for efficiency

---

### Example 3: Sum of Array

#### Objective
Calculate sum of all elements in an array using recursion.

#### Summary
**Recursive thinking:**
- Sum of array = first element + sum of rest of array
- Base: empty array has sum 0

**Index-based approach:**
- Process array from index to end
- Base: index == length means no elements left

#### Algorithm
1. **Base case:** index >= array.length, return 0
2. **Recursive case:** return arr[index] + sumArray(arr, index+1)
3. **Progress:** index increases each call

#### Complexity
- **Time:** O(n) - visit each element once
- **Space:** O(n) - call stack depth

#### Edge Cases
- Empty array → sum = 0
- Single element → sum = element
- All zeros → sum = 0
- Negative numbers → works correctly

#### Java 17 Code

```java
public class ArraySumRecursive {

    /**
     * Sum array recursively from given index to end
     */
    public static int sumArray(int[] arr, int index) {
        // Base case: reached end of array
        if (index >= arr.length) {
            return 0;
        }

        // Recursive case: current element + sum of rest
        return arr[index] + sumArray(arr, index + 1);
    }

    public static int sumArray(int[] arr) {
        return sumArray(arr, 0);
    }

    /**
     * Alternative: process from end to beginning
     */
    public static int sumArrayReverse(int[] arr, int index) {
        // Base case
        if (index < 0) {
            return 0;
        }

        // Recursive case
        return arr[index] + sumArrayReverse(arr, index - 1);
    }

    public static int sumArrayReverse(int[] arr) {
        return sumArrayReverse(arr, arr.length - 1);
    }

    /**
     * Using range [start, end)
     */
    public static int sumRange(int[] arr, int start, int end) {
        // Base case: empty range
        if (start >= end) {
            return 0;
        }

        // Recursive case
        return arr[start] + sumRange(arr, start + 1, end);
    }

    public static void main(String[] args) {
        // Test empty array
        int[] empty = {};
        assert sumArray(empty) == 0 : "Empty array sum should be 0";

        // Test single element
        int[] single = {42};
        assert sumArray(single) == 42 : "Single element sum incorrect";

        // Test multiple elements
        int[] arr1 = {1, 2, 3, 4, 5};
        assert sumArray(arr1) == 15 : "Sum of 1-5 should be 15";

        // Test negative numbers
        int[] arr2 = {-1, -2, 3, 4};
        assert sumArray(arr2) == 4 : "Sum with negatives incorrect";

        // Test reverse version
        assert sumArrayReverse(arr1) == 15 : "Reverse sum should match";

        // Test range version
        assert sumRange(arr1, 1, 4) == 9 : "Sum of indices 1-3 should be 9";

        // Display results
        System.out.println("Sum of [1,2,3,4,5]: " + sumArray(arr1));
        System.out.println("Sum of [-1,-2,3,4]: " + sumArray(arr2));
        System.out.println("Sum of empty array: " + sumArray(empty));
        System.out.println("Sum reverse [1,2,3,4,5]: " + sumArrayReverse(arr1));
        System.out.println("Sum range [1,4) of [1,2,3,4,5]: " + sumRange(arr1, 1, 4));

        System.out.println("✅ All array sum tests passed!");
    }
}
```

#### Expected Output
```
Sum of [1,2,3,4,5]: 15
Sum of [-1,-2,3,4]: 4
Sum of empty array: 0
Sum reverse [1,2,3,4,5]: 15
Sum range [1,4) of [1,2,3,4,5]: 9
✅ All array sum tests passed!
```

#### Checklist
- ✅ Base case: index >= length returns 0
- ✅ Recursive: arr[i] + sumArray(arr, i+1)
- ✅ Progress: index increases
- ✅ Time: O(n), Space: O(n)
- ✅ Handles empty array, negatives

---

### Example 4: Binary Search (Recursive)

#### Objective
Search for target value in sorted array using divide-and-conquer recursion.

#### Summary
**Recursive strategy:**
- Check middle element
- If found, return index
- If target < middle, search left half
- If target > middle, search right half

**Divide-and-conquer:** Problem size halves each recursion.

#### Algorithm
1. **Base case:** left > right, return -1 (not found)
2. Calculate middle: mid = (left + right) / 2
3. If arr[mid] == target, return mid
4. If arr[mid] > target, search left: binarySearch(arr, left, mid-1, target)
5. Else search right: binarySearch(arr, mid+1, right, target)

#### Complexity
- **Time:** O(log n) - halve search space each time
- **Space:** O(log n) - call stack depth

#### Edge Cases
- Empty array → return -1
- Single element (found/not found)
- Target at boundaries (first/last)
- Target not in array
- Duplicate elements (returns one index)

#### Java 17 Code

```java
public class BinarySearchRecursive {

    /**
     * Binary search recursively in sorted array
     * @param arr sorted array
     * @param left start index (inclusive)
     * @param right end index (inclusive)
     * @param target value to find
     * @return index of target, or -1 if not found
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        // Base case: search space exhausted
        if (left > right) {
            return -1;
        }

        // Calculate middle (avoid overflow)
        int mid = left + (right - left) / 2;

        // Found target
        if (arr[mid] == target) {
            return mid;
        }

        // Target in left half
        if (arr[mid] > target) {
            return binarySearch(arr, left, mid - 1, target);
        }

        // Target in right half
        return binarySearch(arr, mid + 1, right, target);
    }

    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    /**
     * Binary search that returns insertion point if not found
     * Returns index if found, or -(insertion_point + 1) if not found
     */
    public static int binarySearchInsertionPoint(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -(left + 1);  // Encode insertion point
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchInsertionPoint(arr, left, mid - 1, target);
        } else {
            return binarySearchInsertionPoint(arr, mid + 1, right, target);
        }
    }

    public static int binarySearchInsertionPoint(int[] arr, int target) {
        return binarySearchInsertionPoint(arr, 0, arr.length - 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        // Test found cases
        assert binarySearch(arr, 1) == 0 : "First element";
        assert binarySearch(arr, 19) == 9 : "Last element";
        assert binarySearch(arr, 9) == 4 : "Middle element";
        assert binarySearch(arr, 7) == 3 : "Element in first half";
        assert binarySearch(arr, 15) == 7 : "Element in second half";

        // Test not found cases
        assert binarySearch(arr, 0) == -1 : "Before first";
        assert binarySearch(arr, 20) == -1 : "After last";
        assert binarySearch(arr, 10) == -1 : "Between elements";

        // Test empty array
        int[] empty = {};
        assert binarySearch(empty, 5) == -1 : "Empty array";

        // Test single element
        int[] single = {42};
        assert binarySearch(single, 42) == 0 : "Single found";
        assert binarySearch(single, 10) == -1 : "Single not found";

        // Test insertion point version
        int insertPoint = binarySearchInsertionPoint(arr, 10);
        assert insertPoint < 0 : "Should be negative for not found";
        int actualInsertPoint = -(insertPoint + 1);
        assert actualInsertPoint == 5 : "10 should insert at index 5";

        // Display results
        System.out.println("Array: [1,3,5,7,9,11,13,15,17,19]");
        System.out.println("Search 9: index = " + binarySearch(arr, 9));
        System.out.println("Search 10: index = " + binarySearch(arr, 10));
        System.out.println("Search 1: index = " + binarySearch(arr, 1));
        System.out.println("Search 19: index = " + binarySearch(arr, 19));

        int result = binarySearchInsertionPoint(arr, 10);
        if (result < 0) {
            System.out.println("10 not found, insert at: " + (-(result + 1)));
        }

        System.out.println("✅ All binary search tests passed!");
    }
}
```

#### Expected Output
```
Array: [1,3,5,7,9,11,13,15,17,19]
Search 9: index = 4
Search 10: index = -1
Search 1: index = 0
Search 19: index = 9
10 not found, insert at: 5
✅ All binary search tests passed!
```

#### Checklist
- ✅ Base case: left > right returns -1
- ✅ Mid calculation avoids overflow
- ✅ Three cases: found, go left, go right
- ✅ Time: O(log n), Space: O(log n)
- ✅ Array must be sorted!

---

### Example 5: Power Function (x^n)

#### Objective
Calculate x raised to power n (x^n) efficiently using recursion.

#### Summary
**Naive approach:** x^n = x × x^(n-1) — O(n) time

**Efficient approach (Fast Exponentiation):**
- If n even: x^n = (x^(n/2))^2
- If n odd: x^n = x × (x^(n/2))^2
- Time: O(log n)

#### Algorithm
**Efficient version:**
1. **Base case:** n == 0, return 1
2. Calculate half = power(x, n/2)
3. If n even: return half × half
4. If n odd: return x × half × half

#### Complexity
**Naive:**
- **Time:** O(n) - n recursive calls
- **Space:** O(n) - call stack

**Efficient:**
- **Time:** O(log n) - halve n each time
- **Space:** O(log n) - call stack

#### Edge Cases
- n = 0 → returns 1 (any x^0 = 1)
- n = 1 → returns x
- Negative n → 1/x^|n|
- x = 0, n = 0 → mathematically undefined (return 1 by convention)

#### Java 17 Code

```java
public class PowerRecursive {

    /**
     * Naive recursive power - O(n) time
     */
    public static double powerNaive(double x, int n) {
        // Base case
        if (n == 0) return 1;
        if (n == 1) return x;

        // Recursive case
        return x * powerNaive(x, n - 1);
    }

    /**
     * Efficient recursive power - O(log n) time
     * Uses fast exponentiation (exponentiation by squaring)
     */
    public static double powerFast(double x, int n) {
        // Handle negative exponents
        if (n < 0) {
            return 1.0 / powerFast(x, -n);
        }

        // Base case
        if (n == 0) return 1;
        if (n == 1) return x;

        // Recursive case: divide and conquer
        double half = powerFast(x, n / 2);

        if (n % 2 == 0) {
            // Even: x^n = (x^(n/2))^2
            return half * half;
        } else {
            // Odd: x^n = x * (x^(n/2))^2
            return x * half * half;
        }
    }

    /**
     * Integer power (returns long to handle larger results)
     */
    public static long powerInt(int x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;

        long half = powerInt(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }

    public static void main(String[] args) {
        // Test base cases
        assert powerFast(5, 0) == 1.0 : "Any number^0 = 1";
        assert powerFast(7, 1) == 7.0 : "Any number^1 = itself";
        assert powerFast(0, 5) == 0.0 : "0^n = 0";

        // Test positive exponents
        assert powerFast(2, 10) == 1024.0 : "2^10 = 1024";
        assert powerFast(3, 4) == 81.0 : "3^4 = 81";
        assert powerFast(5, 3) == 125.0 : "5^3 = 125";

        // Test negative exponents
        assert Math.abs(powerFast(2, -3) - 0.125) < 0.0001 : "2^-3 = 0.125";

        // Test integer version
        assert powerInt(2, 10) == 1024 : "2^10 = 1024";
        assert powerInt(3, 5) == 243 : "3^5 = 243";

        // Compare naive vs fast performance
        long start = System.nanoTime();
        double result1 = powerNaive(2, 20);
        long naiveTime = System.nanoTime() - start;

        start = System.nanoTime();
        double result2 = powerFast(2, 20);
        long fastTime = System.nanoTime() - start;

        System.out.println("2^10 = " + powerFast(2, 10));
        System.out.println("3^4 = " + powerFast(3, 4));
        System.out.println("2^-3 = " + powerFast(2, -3));
        System.out.println("5^0 = " + powerFast(5, 0));

        System.out.println("\nPerformance for 2^20:");
        System.out.println("Naive: " + naiveTime + " ns");
        System.out.println("Fast: " + fastTime + " ns");
        System.out.println("Both results equal: " + (result1 == result2));

        System.out.println("\n✅ All power function tests passed!");
    }
}
```

#### Expected Output
```
2^10 = 1024.0
3^4 = 81.0
2^-3 = 0.125
5^0 = 1.0

Performance for 2^20:
Naive: 8400 ns
Fast: 2100 ns
Both results equal: true

✅ All power function tests passed!
```

#### Checklist
- ✅ Base cases: n=0 returns 1, n=1 returns x
- ✅ Naive: O(n) time, x × power(x, n-1)
- ✅ Fast: O(log n) time, divide n by 2
- ✅ Even: (x^(n/2))^2
- ✅ Odd: x × (x^(n/2))^2
- ✅ Handle negative exponents

---

### Example 6: String Reversal

#### Objective
Reverse a string using recursion.

#### Summary
**Recursive thinking:**
- Reverse of string = last char + reverse of all but last char
- Or: reverse of string = reverse of tail + first char

#### Algorithm (approach 1: first + rest)
1. **Base case:** empty or single char, return as is
2. **Recursive:** first char + reverse(rest of string)

#### Complexity
- **Time:** O(n) - process each character once
- **Space:** O(n) - call stack + string concatenation

#### Edge Cases
- Empty string → ""
- Single character → same character
- Palindrome → reversed equals original

#### Java 17 Code

```java
public class StringReversalRecursive {

    /**
     * Reverse string recursively (approach 1: last + rest)
     */
    public static String reverse(String str) {
        // Base case: empty or single character
        if (str.length() <= 1) {
            return str;
        }

        // Recursive case: last char + reverse of rest
        return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
    }

    /**
     * Reverse string recursively (approach 2: first + rest)
     */
    public static String reverseAlt(String str) {
        // Base case
        if (str.isEmpty()) {
            return str;
        }

        // Recursive case: reverse of tail + first char
        return reverseAlt(str.substring(1)) + str.charAt(0);
    }

    /**
     * Reverse using helper with index (more efficient)
     */
    public static String reverseEfficient(String str) {
        return reverseHelper(str, 0);
    }

    private static String reverseHelper(String str, int index) {
        // Base case
        if (index >= str.length()) {
            return "";
        }

        // Recursive case
        return reverseHelper(str, index + 1) + str.charAt(index);
    }

    /**
     * Reverse in-place using char array (most efficient)
     */
    public static String reverseInPlace(String str) {
        char[] chars = str.toCharArray();
        reverseInPlaceHelper(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private static void reverseInPlaceHelper(char[] chars, int left, int right) {
        // Base case
        if (left >= right) {
            return;
        }

        // Swap
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;

        // Recurse on smaller problem
        reverseInPlaceHelper(chars, left + 1, right - 1);
    }

    public static void main(String[] args) {
        // Test empty string
        assert reverse("").equals("") : "Empty string";

        // Test single character
        assert reverse("a").equals("a") : "Single char";

        // Test normal strings
        assert reverse("hello").equals("olleh") : "hello -> olleh";
        assert reverse("recursion").equals("noisrucer") : "recursion reversed";

        // Test palindrome
        assert reverse("racecar").equals("racecar") : "Palindrome unchanged";

        // Test all approaches give same result
        String test = "Algorithm";
        assert reverse(test).equals(reverseAlt(test)) : "Methods should match";
        assert reverse(test).equals(reverseEfficient(test)) : "Efficient should match";
        assert reverse(test).equals(reverseInPlace(test)) : "In-place should match";

        // Display results
        System.out.println("reverse('hello') = " + reverse("hello"));
        System.out.println("reverse('recursion') = " + reverse("recursion"));
        System.out.println("reverse('') = '" + reverse("") + "'");
        System.out.println("reverse('a') = " + reverse("a"));
        System.out.println("reverse('racecar') = " + reverse("racecar"));

        System.out.println("\nAll methods on 'Algorithm':");
        System.out.println("  reverse: " + reverse(test));
        System.out.println("  reverseAlt: " + reverseAlt(test));
        System.out.println("  reverseEfficient: " + reverseEfficient(test));
        System.out.println("  reverseInPlace: " + reverseInPlace(test));

        System.out.println("\n✅ All string reversal tests passed!");
    }
}
```

#### Expected Output
```
reverse('hello') = olleh
reverse('recursion') = noisrucer
reverse('') = ''
reverse('a') = a
reverse('racecar') = racecar

All methods on 'Algorithm':
  reverse: mhtirogla
  reverseAlt: mhtirogla
  reverseEfficient: mhtirogla
  reverseInPlace: mhtirogla

✅ All string reversal tests passed!
```

#### Checklist
- ✅ Base case: length ≤ 1 returns string
- ✅ Recursive: last + reverse(rest)
- ✅ Alternative: reverse(tail) + first
- ✅ Time: O(n), Space: O(n)
- ✅ In-place swap more efficient

---

## 4.3 Practice Problems

### Problem 1: Count Digits in Number
**Difficulty:** Easy

**Problem:** Write a recursive function to count the number of digits in a positive integer.

**Example:**
- Input: 12345 → Output: 5
- Input: 7 → Output: 1
- Input: 1000 → Output: 4

**Hint:** Each recursive call can divide the number by 10. The base case is when the number becomes 0 (or less than 10). Don't forget to handle the case when n = 0 (should return 1, not 0).

**Key concepts tested:**
- Simple base case identification
- Integer division for making progress
- Edge case handling (zero)

---

### Problem 2: Palindrome Check
**Difficulty:** Easy-Medium

**Problem:** Write a recursive function to check if a string is a palindrome (reads same forwards and backwards). Ignore case and spaces.

**Example:**
- Input: "racecar" → Output: true
- Input: "A man a plan a canal Panama" → Output: true (ignoring case/spaces)
- Input: "hello" → Output: false

**Hint:** Use two pointers (left and right indices). Base case: when pointers meet or cross, it's a palindrome. Recursive case: if characters at pointers match, recurse on inner substring. Remember to preprocess the string to remove spaces and convert to lowercase.

**Key concepts tested:**
- Two-pointer recursion
- String preprocessing
- Multiple base cases (pointers cross, characters don't match)

---

### Problem 3: Array Maximum Element
**Difficulty:** Medium

**Problem:** Write a recursive function to find the maximum element in an array. Do not use any loops or Math.max().

**Example:**
- Input: [3, 1, 4, 1, 5, 9, 2, 6] → Output: 9
- Input: [-5, -2, -8, -1] → Output: -1
- Input: [42] → Output: 42

**Hint:** Compare the first element with the maximum of the rest of the array. Base case is when you have only one element. Think about how to express "maximum of rest" recursively. Alternative approach: divide array in half and find max of each half, then return the larger.

**Key concepts tested:**
- Comparing current element with recursive result
- Divide-and-conquer option
- Handling negative numbers

---

### Problem 4: Tower of Hanoi
**Difficulty:** Medium-Hard

**Problem:** Solve the Tower of Hanoi puzzle recursively. Given n disks on rod A, move all disks to rod C using rod B as auxiliary, following rules: 1) Move one disk at a time, 2) Can't place larger disk on smaller disk.

**Example:**
- Input: n=3, from='A', to='C', aux='B'
- Output: sequence of moves:
  ```
  Move disk 1 from A to C
  Move disk 2 from A to B
  Move disk 1 from C to B
  Move disk 3 from A to C
  Move disk 1 from B to A
  Move disk 2 from B to C
  Move disk 1 from A to C
  ```

**Hint:** To move n disks from A to C using B:
1. Move n-1 disks from A to B (using C as auxiliary)
2. Move disk n from A to C
3. Move n-1 disks from B to C (using A as auxiliary)

Base case: n=1, just move the disk directly. The total number of moves is 2^n - 1.

**Key concepts tested:**
- Multiple recursive calls
- Understanding problem decomposition
- Exponential time complexity O(2^n)

---

### Problem 5: Greatest Common Divisor (GCD)
**Difficulty:** Medium

**Problem:** Implement Euclid's algorithm recursively to find the GCD of two positive integers.

**Example:**
- Input: gcd(48, 18) → Output: 6
- Input: gcd(100, 25) → Output: 25
- Input: gcd(17, 19) → Output: 1 (coprime)

**Hint:** Euclid's algorithm states:
- gcd(a, 0) = a (base case)
- gcd(a, b) = gcd(b, a % b) (recursive case)

Make sure to handle the case where b might be larger than a initially. The algorithm naturally handles this through the modulo operation.

**Key concepts tested:**
- Mathematical recursion
- Modulo operation for progress
- Classic algorithm implementation
- Time complexity: O(log(min(a,b)))

---

## 4.4 Final Exam Checklist for Recursion

### Core Concepts
- ✅ **Definition:** Function calls itself with smaller problem
- ✅ **Base case:** Terminating condition (no recursion)
- ✅ **Recursive case:** Calls itself with modified input
- ✅ **Progress:** Each call must move toward base case
- ✅ **Call stack:** Understand how calls stack up and unwind

### Writing Recursive Functions
- ✅ **Step 1:** Identify base case(s) - simplest input
- ✅ **Step 2:** Define how to make problem smaller
- ✅ **Step 3:** Assume recursion works on smaller problem (leap of faith)
- ✅ **Step 4:** Combine recursive result with current level
- ✅ **Step 5:** Verify all paths reach base case

### Common Patterns
- ✅ **Linear recursion:** Single recursive call (factorial, sum)
- ✅ **Binary recursion:** Two recursive calls (Fibonacci, tree traversal)
- ✅ **Tail recursion:** Recursive call is last operation
- ✅ **Divide-and-conquer:** Split problem in half (binary search, merge sort)
- ✅ **Multiple recursion:** More than two calls (Tower of Hanoi)

### Complexity Analysis
- ✅ **Time:** Count recursive calls × work per call
- ✅ **Space:** Call stack depth (usually matches time for linear recursion)
- ✅ **Recurrence relations:** T(n) = aT(n/b) + f(n)
- ✅ **Common complexities:**
  - O(n): Linear recursion like factorial
  - O(log n): Binary search, fast power
  - O(n log n): Merge sort
  - O(2^n): Naive Fibonacci, subsets

### Edge Cases & Pitfalls
- ✅ **No base case:** Infinite recursion, stack overflow
- ✅ **Wrong base case:** Incorrect results or doesn't terminate
- ✅ **Not making progress:** Infinite loop (same input each time)
- ✅ **Stack overflow:** Too many recursive calls (use iteration for deep recursion)
- ✅ **Overlapping subproblems:** Use memoization (Fibonacci)
- ✅ **Empty input:** Array[], string "", n=0
- ✅ **Single element:** One item in collection
- ✅ **Negative inputs:** May need special handling

### Problem-Solving Strategy
- ✅ **Understand:** What's the problem asking?
- ✅ **Examples:** Work through small examples by hand
- ✅ **Base case:** What's simplest version?
- ✅ **Recursive case:** How to reduce problem?
- ✅ **Combine:** How to use recursive result?
- ✅ **Test:** Empty, single, typical, edge cases
- ✅ **Optimize:** Can we add memoization? Use tail recursion?

### Exam Tips
- ✅ **Trace execution:** Draw call stack for small inputs
- ✅ **Check termination:** Verify base case is reachable
- ✅ **Count operations:** For complexity analysis
- ✅ **Compare to iteration:** Sometimes iteration is clearer
- ✅ **Memoization:** Remember to suggest it for overlapping subproblems
- ✅ **Write clean code:** Clear base case, good variable names
- ✅ **Handle errors:** Validate inputs, throw exceptions for invalid cases

---

**End of MIDTERM_PREP_PART3.md**

*For Stacks and Queue content, see MIDTERM_PREP_PART2.md*
*For Complexity Analysis theory, see curriculum/complexity_analysis.md*
*For general study guide, see MIDTERM_PREP_GUIDE.md*
