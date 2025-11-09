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

# 5. STACKS

## 5.1 Stack Fundamentals

### ① One-Line Concept Summary
A stack is a Last-In-First-Out (LIFO) linear data structure where elements are added and removed from the same end (called the "top"), like a stack of plates where you can only access the topmost plate.

### ② How to Think About It

**Mental Model: Stack of Plates**
- Add plate on top (push)
- Remove plate from top (pop)
- Can only see/access the top plate (peek)
- Can't access plates in the middle without removing top plates
- Last plate added is first plate removed (LIFO)

**Real-World Analogies:**
- Stack of books on a desk
- Browser back button (last page visited = first to go back to)
- Undo functionality in text editors
- Function call stack in programming

**LIFO Principle:**
```
Push 1 → [1]
Push 2 → [1, 2]
Push 3 → [1, 2, 3]
Pop    → [1, 2]     (removed 3)
Pop    → [1]        (removed 2)
```

**Decision Checklist - Use stack when:**
- ✅ Need LIFO access pattern
- ✅ Reversing order of elements
- ✅ Matching pairs (parentheses, brackets, tags)
- ✅ Depth-first search (DFS) algorithms
- ✅ Backtracking problems
- ✅ Expression evaluation (postfix, infix)
- ✅ Function call management (call stack)
- ❌ Don't use if need random access by index
- ❌ Don't use if need FIFO (use queue instead)
- ❌ Don't use if need to access middle elements frequently

**Key Stack Operations:**
1. **push(item)** - Add element to top - O(1)
2. **pop()** - Remove and return top element - O(1)
3. **peek()/top()** - View top element without removing - O(1)
4. **isEmpty()** - Check if stack is empty - O(1)
5. **size()** - Get number of elements - O(1)

**Key Invariants:**
- Top always points to last added element
- Empty stack: top = -1 (array) or top = null (linked list)
- All operations happen at the top only
- Order is preserved (LIFO)

**Failure Modes:**
- **Stack Overflow:** Push to full array-based stack
- **Stack Underflow:** Pop from empty stack
- **Memory leak:** Not freeing nodes in linked list implementation

### ③ Stack Implementations Overview

**Two Main Implementations:**

1. **Array-Based Stack:**
   - Fixed or dynamic capacity
   - Top tracked by index
   - Fast O(1) operations
   - Cache-friendly (contiguous memory)
   - May waste space or need resizing

2. **Linked List-Based Stack:**
   - No capacity limit (until heap memory exhausted)
   - Top is head of linked list
   - O(1) operations
   - Extra memory for pointers
   - No resizing needed

**Comparison:**

| Feature | Array-Based | Linked List-Based |
|---------|-------------|-------------------|
| Capacity | Fixed or grows | Unlimited (heap) |
| Memory | Contiguous, cache-friendly | Scattered, pointer overhead |
| Resize | May need to copy | Never needed |
| Implementation | Simpler | Slightly more complex |
| Space Overhead | Wasted if not full | Pointer per element |
| Use When | Know max size | Size unpredictable |

### ④ Stack Applications

**1. Expression Evaluation:**
- Infix to postfix conversion
- Postfix expression evaluation
- Checking balanced parentheses

**2. Backtracking:**
- DFS traversal
- Maze solving
- N-Queens problem
- Sudoku solver

**3. Function Calls:**
- Call stack (runtime stack)
- Local variables storage
- Return addresses
- Recursion management

**4. Parsing:**
- HTML/XML tag matching
- Compiler syntax checking
- Balanced brackets validation

**5. Undo Mechanisms:**
- Text editor undo/redo
- Browser back/forward
- Game state history

**6. Reversing:**
- Reverse string
- Reverse array
- Reverse linked list

### ⑤ Recursion and Stack Relationship

**The Call Stack:**
- Every function call creates a **stack frame** (activation record)
- Stack frame contains: parameters, local variables, return address
- Pushed when function called, popped when function returns
- Recursion = series of stack frames

**Recursion IS Stack:**
```java
// Recursive factorial
int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n-1);
}

// Call stack for factorial(4):
// [factorial(4)] → n=4
// [factorial(4), factorial(3)] → n=3
// [factorial(4), factorial(3), factorial(2)] → n=2
// [factorial(4), factorial(3), factorial(2), factorial(1)] → n=1, returns 1
// [factorial(4), factorial(3), factorial(2)] → returns 2
// [factorial(4), factorial(3)] → returns 6
// [factorial(4)] → returns 24
```

**Converting Recursion to Iteration with Explicit Stack:**

Every recursive function can be converted to iterative using a stack:

```java
// Recursive
int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n-1);
}

// Iterative with stack (conceptual)
int factorialIterative(int n) {
    Stack<Integer> stack = new Stack<>();
    while (n > 1) {
        stack.push(n);
        n--;
    }
    int result = 1;
    while (!stack.isEmpty()) {
        result *= stack.pop();
    }
    return result;
}
```

**Why Convert?**
- Avoid stack overflow for deep recursion
- More control over stack size
- Sometimes more efficient (avoid function call overhead)

**Stack Overflow:**
- Too many recursive calls → call stack exceeds limit
- Java default stack size: ~1MB (varies by JVM)
- Solution: Use iteration or explicit stack with heap memory

---

## 5.2 Array-Based Stack Implementation

### ① Objective
Implement a stack using a fixed-size array with top index tracking, supporting push, pop, peek, isEmpty operations in O(1) time.

### ② Summary
**Structure:**
- Array to hold elements
- `top` index pointing to last pushed element
- `capacity` for maximum size
- `top = -1` means empty stack

**Key Operations:**
- **push:** Increment top, add at array[top]
- **pop:** Return array[top], decrement top
- **peek:** Return array[top] without changing top

### ③ Algorithm

**Initialization:**
1. Create array of given capacity
2. Set top = -1 (empty)

**push(item):**
1. Check if full (top == capacity - 1)
2. If full, throw StackOverflowException
3. Increment top
4. Set array[top] = item

**pop():**
1. Check if empty (top == -1)
2. If empty, throw StackUnderflowException
3. Save item = array[top]
4. Decrement top
5. Return item

**peek():**
1. Check if empty
2. Return array[top]

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| push | O(1) | O(1) | Direct array access |
| pop | O(1) | O(1) | Direct array access |
| peek | O(1) | O(1) | Direct array access |
| isEmpty | O(1) | O(1) | Check top == -1 |
| size | O(1) | O(1) | Return top + 1 |

**Overall Space:** O(n) where n = capacity

### ⑤ Edge Cases & Pitfalls

**Pitfall 1: Push to full stack**
```java
// ❌ WRONG - array index out of bounds
array[++top] = item;  // No check if full!

// ✅ CORRECT
if (isFull()) throw new StackOverflowError("Stack full");
array[++top] = item;
```

**Pitfall 2: Pop from empty stack**
```java
// ❌ WRONG - returns garbage or crashes
return array[top--];  // No check if empty!

// ✅ CORRECT
if (isEmpty()) throw new EmptyStackException();
return array[top--];
```

**Pitfall 3: Off-by-one in size calculation**
```java
// If top = 2, we have elements at indices 0, 1, 2 → size = 3
int size() { return top + 1; }  // ✅ CORRECT, not just 'top'
```

**Edge Cases:**
- Empty stack (top = -1)
- Full stack (top = capacity - 1)
- Single element (top = 0)
- Push-pop-push sequence

### ⑥ Java 17 Implementation

```java
import java.util.EmptyStackException;

public class ArrayStack {
    private int[] array;
    private int top;
    private int capacity;

    /**
     * Constructor with specified capacity
     */
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.array = new int[capacity];
        this.top = -1;  // Empty stack
    }

    /**
     * Push element onto stack - O(1)
     */
    public void push(int item) {
        if (isFull()) {
            throw new StackOverflowError("Stack is full (capacity: " + capacity + ")");
        }
        array[++top] = item;
    }

    /**
     * Pop element from stack - O(1)
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top--];
    }

    /**
     * Peek at top element without removing - O(1)
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    /**
     * Check if stack is empty - O(1)
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Check if stack is full - O(1)
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Get current size - O(1)
     */
    public int size() {
        return top + 1;
    }

    /**
     * Get capacity
     */
    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(array[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("] <- top");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        // Test 1: isEmpty on new stack
        assert stack.isEmpty() : "New stack should be empty";
        assert stack.size() == 0 : "New stack size should be 0";
        System.out.println("New stack: " + stack);

        // Test 2: Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assert stack.size() == 3 : "Size should be 3 after 3 pushes";
        assert !stack.isEmpty() : "Stack should not be empty";
        System.out.println("After pushes: " + stack);

        // Test 3: Peek
        int topValue = stack.peek();
        assert topValue == 30 : "Top should be 30";
        assert stack.size() == 3 : "Peek should not change size";
        System.out.println("Peek: " + topValue);

        // Test 4: Pop
        int popped = stack.pop();
        assert popped == 30 : "Popped should be 30";
        assert stack.size() == 2 : "Size should be 2 after pop";
        System.out.println("Popped: " + popped + ", Stack: " + stack);

        // Test 5: Fill to capacity
        stack.push(40);
        stack.push(50);
        stack.push(60);
        assert stack.isFull() : "Stack should be full";
        assert stack.size() == 5 : "Size should be 5 (capacity)";
        System.out.println("Full stack: " + stack);

        // Test 6: Overflow
        try {
            stack.push(70);
            assert false : "Should throw StackOverflowError";
        } catch (StackOverflowError e) {
            System.out.println("Overflow caught correctly: " + e.getMessage());
        }

        // Test 7: Empty the stack
        while (!stack.isEmpty()) {
            stack.pop();
        }
        assert stack.isEmpty() : "Stack should be empty";
        System.out.println("After emptying: " + stack);

        // Test 8: Underflow
        try {
            stack.pop();
            assert false : "Should throw EmptyStackException";
        } catch (EmptyStackException e) {
            System.out.println("Underflow caught correctly");
        }

        System.out.println("\n✅ All array stack tests passed!");
    }
}
```

### ⑦ Expected Output

```
New stack: []
After pushes: [10, 20, 30] <- top
Peek: 30
Popped: 30, Stack: [10, 20] <- top
Full stack: [10, 20, 40, 50, 60] <- top
Overflow caught correctly: Stack is full (capacity: 5)
After emptying: []
Underflow caught correctly

✅ All array stack tests passed!
```

### ⑧ Exam Checklist

- ✅ **Structure:** Array + top index + capacity
- ✅ **Empty:** top = -1
- ✅ **Full:** top = capacity - 1
- ✅ **Size:** top + 1
- ✅ **Push:** Check full, then array[++top] = item
- ✅ **Pop:** Check empty, then return array[top--]
- ✅ **Peek:** Return array[top] without changing top
- ✅ **All operations:** O(1) time
- ✅ **Edge cases:** Empty, full, underflow, overflow

---

## 5.3 Linked List-Based Stack Implementation

### ① Objective
Implement a stack using a singly linked list where the head serves as the top, providing unlimited capacity (until heap exhaustion) with O(1) operations.

### ② Summary
**Structure:**
- Linked list with nodes containing data and next pointer
- `top` reference points to head of list (most recent element)
- No capacity limit (dynamic size)
- Push = addFirst, Pop = removeFirst

**Key Insight:**
- Top of stack = head of linked list
- Push new element at head (O(1))
- Pop from head (O(1))
- No array resizing needed

### ③ Algorithm

**Node Structure:**
```
class Node {
    int data;
    Node next;
}
```

**Initialization:**
1. Set top = null (empty)
2. Set size = 0

**push(item):**
1. Create new node with data
2. Set newNode.next = top
3. Set top = newNode
4. Increment size

**pop():**
1. Check if empty (top == null)
2. Save data = top.data
3. Set top = top.next
4. Decrement size
5. Return data

**peek():**
1. Check if empty
2. Return top.data

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| push | O(1) | O(1) | Add at head |
| pop | O(1) | O(1) | Remove from head |
| peek | O(1) | O(1) | Access head |
| isEmpty | O(1) | O(1) | Check top == null |
| size | O(1) | O(1) | Return cached size |

**Overall Space:** O(n) where n = number of elements (plus pointer overhead)

### ⑤ Edge Cases & Pitfalls

**Pitfall 1: Null pointer on empty stack**
```java
// ❌ WRONG - NPE if top is null
return top.data;

// ✅ CORRECT
if (isEmpty()) throw new EmptyStackException();
return top.data;
```

**Pitfall 2: Not updating top reference**
```java
// ❌ WRONG - top not moved
Node newNode = new Node(item);
newNode.next = top;
// Missing: top = newNode;

// ✅ CORRECT
Node newNode = new Node(item);
newNode.next = top;
top = newNode;  // Must update top!
```

**Edge Cases:**
- Empty stack (top = null)
- Single element (top.next = null)
- Push-pop-push sequence
- No capacity limit (until OutOfMemoryError)

### ⑥ Java 17 Implementation

```java
import java.util.EmptyStackException;

class StackNode {
    int data;
    StackNode next;

    StackNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListStack {
    private StackNode top;
    private int size;

    /**
     * Constructor - creates empty stack
     */
    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Push element onto stack - O(1)
     */
    public void push(int item) {
        StackNode newNode = new StackNode(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Pop element from stack - O(1)
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /**
     * Peek at top element - O(1)
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    /**
     * Check if empty - O(1)
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Get size - O(1)
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        StackNode current = top;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("] <- top at left");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();

        // Test 1: Empty stack
        assert stack.isEmpty() : "New stack should be empty";
        assert stack.size() == 0 : "Size should be 0";
        System.out.println("New stack: " + stack);

        // Test 2: Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assert stack.size() == 3 : "Size should be 3";
        assert stack.peek() == 30 : "Top should be 30";
        System.out.println("After pushes: " + stack);

        // Test 3: Pop
        int popped = stack.pop();
        assert popped == 30 : "Popped should be 30";
        assert stack.size() == 2 : "Size should be 2";
        System.out.println("Popped: " + popped + ", Stack: " + stack);

        // Test 4: Push more (no capacity limit!)
        for (int i = 40; i <= 100; i += 10) {
            stack.push(i);
        }
        System.out.println("After more pushes: " + stack);
        assert stack.size() == 9 : "Size should be 9";

        // Test 5: Empty the stack
        int count = 0;
        while (!stack.isEmpty()) {
            stack.pop();
            count++;
        }
        assert count == 9 : "Should have popped 9 elements";
        assert stack.isEmpty() : "Should be empty";
        System.out.println("After emptying: " + stack);

        // Test 6: Underflow
        try {
            stack.pop();
            assert false : "Should throw exception";
        } catch (EmptyStackException e) {
            System.out.println("Underflow caught correctly");
        }

        // Test 7: Push-pop sequence
        stack.push(100);
        stack.push(200);
        assert stack.pop() == 200 : "LIFO order";
        assert stack.pop() == 100 : "LIFO order";
        assert stack.isEmpty() : "Should be empty";

        System.out.println("\n✅ All linked list stack tests passed!");
    }
}
```

### ⑦ Expected Output

```
New stack: []
After pushes: [30, 20, 10] <- top at left
Popped: 30, Stack: [20, 10] <- top at left
After more pushes: [100, 90, 80, 70, 60, 50, 40, 20, 10] <- top at left
After emptying: []
Underflow caught correctly

✅ All linked list stack tests passed!
```

### ⑧ Exam Checklist

- ✅ **Structure:** Linked list with top = head
- ✅ **Empty:** top = null
- ✅ **No capacity limit:** Only heap memory constraint
- ✅ **Push:** Create node, set next = top, update top
- ✅ **Pop:** Save data, move top = top.next
- ✅ **Peek:** Return top.data
- ✅ **All operations:** O(1)
- ✅ **Advantage:** No resizing, unlimited size
- ✅ **Disadvantage:** Pointer overhead, not cache-friendly

---

## 5.4 Stack Application: Balanced Parentheses

### ① Objective
Check if a string containing parentheses, brackets, and braces is balanced using a stack.

### ② Summary
**Problem:** Validate that every opening bracket has a matching closing bracket in correct order.

**Examples:**
- `"()"` → balanced
- `"()[]{}"` → balanced
- `"([{}])"` → balanced
- `"([)]"` → NOT balanced (wrong order)
- `"((("` → NOT balanced (no closing)

**Stack Strategy:**
- Push opening brackets onto stack
- When closing bracket found, pop and check if it matches
- At end, stack should be empty

### ③ Algorithm

1. Create empty stack
2. For each character in string:
   - If opening bracket `(`, `[`, `{`: push onto stack
   - If closing bracket `)`, `]`, `}`:
     - Check if stack is empty → return false (no matching opener)
     - Pop from stack
     - Check if popped bracket matches current closer → if not, return false
3. After processing all characters:
   - If stack empty → return true (all matched)
   - If stack not empty → return false (unmatched openers)

### ④ Complexity
- **Time:** O(n) - single pass through string
- **Space:** O(n) - worst case all opening brackets

### ⑤ Edge Cases
- Empty string → balanced (true)
- Only opening brackets → false
- Only closing brackets → false
- Mismatched types: `"([)]"` → false
- Extra closing: `"())"` → false
- Extra opening: `"(()"` → false

### ⑥ Java 17 Implementation

```java
import java.util.Stack;

public class BalancedParentheses {

    /**
     * Check if string has balanced parentheses/brackets/braces
     */
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            // Opening brackets - push to stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Closing brackets - check match
            else if (ch == ')' || ch == ']' || ch == '}') {
                // No matching opener
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check if types match
                if (ch == ')' && top != '(') return false;
                if (ch == ']' && top != '[') return false;
                if (ch == '}' && top != '{') return false;
            }
            // Ignore other characters (for general strings)
        }

        // All openers must have closers
        return stack.isEmpty();
    }

    /**
     * Helper to check if opening bracket
     */
    private static boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    /**
     * Helper to check if closing bracket
     */
    private static boolean isClosing(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    /**
     * Helper to check if brackets match
     */
    private static boolean matches(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '[' && closing == ']') ||
               (opening == '{' && closing == '}');
    }

    /**
     * Alternative cleaner implementation
     */
    public static boolean isBalancedClean(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (isOpening(ch)) {
                stack.push(ch);
            } else if (isClosing(ch)) {
                if (stack.isEmpty() || !matches(stack.pop(), ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Test balanced cases
        assert isBalanced("()") : "Simple pair";
        assert isBalanced("()[]{}") : "Multiple types";
        assert isBalanced("([{}])") : "Nested";
        assert isBalanced("{[()]}") : "Complex nesting";
        assert isBalanced("") : "Empty string";

        // Test unbalanced cases
        assert !isBalanced("(") : "Only opener";
        assert !isBalanced(")") : "Only closer";
        assert !isBalanced("([)]") : "Wrong order";
        assert !isBalanced("(()") : "Missing closer";
        assert !isBalanced("())") : "Extra closer";
        assert !isBalanced("(()()))") : "Unmatched";

        // Test with other characters
        assert isBalanced("int[] arr = new int[10];") : "Valid Java code";
        assert isBalanced("if (x > 0) { return arr[x]; }") : "Valid code";

        // Display results
        String[] tests = {"()", "()[]{}", "([{}])", "([)]", "(((", "())", ""};
        System.out.println("Balanced Parentheses Checker:\n");
        for (String test : tests) {
            boolean result = isBalanced(test);
            System.out.println("\"" + test + "\" → " + (result ? "✓ balanced" : "✗ NOT balanced"));
        }

        // Verify both implementations agree
        for (String test : tests) {
            assert isBalanced(test) == isBalancedClean(test) : "Implementations should agree";
        }

        System.out.println("\n✅ All balanced parentheses tests passed!");
    }
}
```

### ⑦ Expected Output

```
Balanced Parentheses Checker:

"()" → ✓ balanced
"()[]{}" → ✓ balanced
"([{}])" → ✓ balanced
"([)]" → ✗ NOT balanced
"(((" → ✗ NOT balanced
"())" → ✗ NOT balanced
"" → ✓ balanced

✅ All balanced parentheses tests passed!
```

### ⑧ Checklist

- ✅ **Opening bracket:** Push to stack
- ✅ **Closing bracket:** Pop and check match
- ✅ **Empty stack on closer:** Return false
- ✅ **End with empty stack:** All matched
- ✅ **Time:** O(n), **Space:** O(n)
- ✅ **Edge cases:** Empty string, only openers, only closers

---

## 5.5 Practice Problems

### Problem 1: Reverse a String Using Stack
**Difficulty:** Easy

**Problem:** Use a stack to reverse a string.

**Example:**
- Input: "hello" → Output: "olleh"
- Input: "Stack" → Output: "kcatS"

**Hint:** Push all characters onto a stack, then pop them all to build the reversed string. Remember that stacks are LIFO, so the last character pushed will be the first character in the result.

**Key concepts tested:**
- Basic stack operations
- LIFO property for reversal
- String building

---

### Problem 2: Evaluate Postfix Expression
**Difficulty:** Medium

**Problem:** Evaluate a postfix (Reverse Polish Notation) expression using a stack.

**Example:**
- Input: "5 3 +" → Output: 8
- Input: "5 3 4 + *" → Output: 35 (5 * (3 + 4))
- Input: "2 3 1 * + 9 -" → Output: -4 ((2 + (3 * 1)) - 9)

**Hint:**
- Scan left to right
- If operand (number): push to stack
- If operator (+, -, *, /): pop two operands, apply operator, push result
- Final stack should have one element (the answer)
- Be careful with order for non-commutative operators: for "a b -", compute a - b (not b - a)

**Key concepts tested:**
- Stack for expression evaluation
- Operator precedence handling
- Order of operands

---

### Problem 3: Next Greater Element
**Difficulty:** Medium-Hard

**Problem:** For each element in an array, find the next greater element to its right. If none exists, return -1.

**Example:**
- Input: [4, 5, 2, 10, 8] → Output: [5, 10, 10, -1, -1]
- Input: [1, 2, 3, 4, 5] → Output: [2, 3, 4, 5, -1]
- Input: [5, 4, 3, 2, 1] → Output: [-1, -1, -1, -1, -1]

**Hint:**
- Scan from right to left
- Maintain stack of elements seen so far
- For current element: pop all smaller elements from stack (they can't be answer)
- Top of stack (if exists) is next greater element
- Push current element to stack
- Time: O(n) - each element pushed/popped once

**Key concepts tested:**
- Stack for maintaining potential candidates
- Monotonic stack pattern
- Right-to-left processing

---

### Problem 4: Valid Stack Sequences
**Difficulty:** Medium-Hard

**Problem:** Given two sequences `pushed` and `popped`, determine if they could be the result of valid push and pop operations on an initially empty stack.

**Example:**
- pushed = [1,2,3,4,5], popped = [4,5,3,2,1] → true
  - Push 1,2,3,4, pop 4, push 5, pop 5,3,2,1
- pushed = [1,2,3,4,5], popped = [4,3,5,1,2] → false
  - Can't get 1,2 at end

**Hint:**
- Simulate the operations with an actual stack
- For each element in popped sequence:
  - Keep pushing from pushed sequence until we find the target
  - If found on top, pop it
  - If we run out of elements to push and top doesn't match, return false
- If we successfully pop all elements, return true

**Key concepts tested:**
- Stack simulation
- Understanding push/pop interleaving
- Validation logic

---

### Problem 5: Min Stack (Stack with O(1) min)
**Difficulty:** Hard

**Problem:** Design a stack that supports push, pop, top, and retrieving the minimum element in O(1) time.

**Operations:**
- push(x): Push element x onto stack
- pop(): Remove element on top
- top(): Get top element
- getMin(): Retrieve minimum element

**Example:**
```
MinStack stack = new MinStack();
stack.push(5);   // stack: [5], min: 5
stack.push(2);   // stack: [5,2], min: 2
stack.push(3);   // stack: [5,2,3], min: 2
stack.getMin();  // returns 2
stack.pop();     // stack: [5,2], min: 2
stack.getMin();  // returns 2
stack.pop();     // stack: [5], min: 5
stack.getMin();  // returns 5
```

**Hint:**
- Use two stacks: one for actual values, one for minimums
- When pushing x:
  - Push x to main stack
  - Push min(x, currentMin) to min stack
- When popping: pop from both stacks
- getMin(): peek at min stack
- Alternative: Store pairs (value, min_at_this_point) in single stack

**Key concepts tested:**
- Auxiliary data structure
- Maintaining invariant
- O(1) operations
- Space-time tradeoff

---

## 5.6 How to Think About Stacks - Mental Framework

### The LIFO Mindset

**Key Question:** "Does order matter and should the last thing in be the first thing out?"

**Visual Thinking:**
```
Think of a stack like a pringles can:
- Can only access the top chip
- Must remove top chip to access ones below
- Last chip put in = first chip taken out
```

**When to Use Stack Checklist:**

1. **Reversal?** → Stack naturally reverses
2. **Matching pairs?** → Stack for tracking openers
3. **Backtracking?** → Stack stores decision points
4. **Nested structure?** → Stack tracks levels
5. **DFS traversal?** → Stack (vs queue for BFS)
6. **Undo/redo?** → Stack of states
7. **Function calls?** → Already using call stack!

### Problem-Solving Pattern

**Step 1: Identify LIFO pattern**
- Is most recent item most important?
- Need to process in reverse order?
- Nested or hierarchical structure?

**Step 2: Decide what to push**
- Opening brackets? Push them
- Indices? Push for later lookup
- Actual values? Push data
- State snapshots? Push entire state

**Step 3: Decide when to pop**
- Found matching closer?
- Reached end of nested level?
- Need to backtrack?
- Processing complete for top item?

**Step 4: What does empty stack mean?**
- All matched?
- Back to start?
- No more options?

### Common Stack Patterns

**Pattern 1: Matching/Pairing**
```
For each item:
  If opener → push
  If closer → pop and verify match
```

**Pattern 2: Monotonic Stack**
```
Maintain stack in increasing/decreasing order
For each element:
  While stack top violates order: pop
  Process with current top
  Push current element
```

**Pattern 3: State Tracking**
```
Push state before making choice
Process...
Pop state to backtrack
```

**Pattern 4: Reversal**
```
Push all elements
Pop all elements (now reversed)
```

---

## 5.7 Stack Cheat Sheet

### Quick Reference

**Core Operations:**
```java
Stack<Integer> stack = new Stack<>();
stack.push(x);       // Add to top - O(1)
int x = stack.pop(); // Remove from top - O(1)
int x = stack.peek(); // View top - O(1)
boolean empty = stack.isEmpty(); // Check empty - O(1)
int size = stack.size(); // Get size - O(1)
```

**Implementation Choice:**
```
Array-based:
  ✓ When: Known max size, cache performance matters
  ✗ When: Size unpredictable, frequent resizing

Linked List-based:
  ✓ When: Size unknown, no capacity limit needed
  ✗ When: Memory overhead matters, cache performance critical
```

**Time Complexities:**
| Operation | Array | Linked List |
|-----------|-------|-------------|
| push | O(1)* | O(1) |
| pop | O(1) | O(1) |
| peek | O(1) | O(1) |
| isEmpty | O(1) | O(1) |
| size | O(1) | O(1) |

*O(1) amortized if dynamic array with doubling

**Space Complexity:**
- Array: O(capacity) - may waste space
- Linked List: O(n) + pointer overhead

### Common Errors & Fixes

| Error | Wrong Code | Correct Code |
|-------|-----------|--------------|
| Pop empty | `stack.pop()` | `if (!stack.isEmpty()) stack.pop()` |
| Push to full (array) | `arr[++top] = x` | `if (!isFull()) arr[++top] = x` |
| Forget to update top | `arr[top] = x` | `arr[++top] = x` |
| Wrong size | `return top` | `return top + 1` |

### Problem Recognition

**"Use a stack when you see:"**
- ✅ "Balanced", "Matching", "Valid" (parentheses)
- ✅ "Reverse" anything
- ✅ "DFS", "Backtrack"
- ✅ "Next greater/smaller element"
- ✅ "Undo", "History"
- ✅ "Evaluate expression" (postfix)
- ✅ "Nested" structures

**"Don't use stack when you see:"**
- ❌ "FIFO", "First in first out" (use queue)
- ❌ "Random access", "Access ith element" (use array/list)
- ❌ "Sort" (usually not stack)
- ❌ "Find all" (unless DFS involved)

### Recursion ↔ Stack Conversion

**Every recursive function CAN be rewritten with explicit stack:**

```java
// Recursive DFS
void dfs(Node node) {
    if (node == null) return;
    process(node);
    dfs(node.left);
    dfs(node.right);
}

// Iterative with stack
void dfsIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node node = stack.pop();
        if (node == null) continue;
        process(node);
        stack.push(node.right); // Push right first!
        stack.push(node.left);  // So left is processed first
    }
}
```

### Memory Limits

**Stack Overflow:**
- Recursion too deep → switch to iterative + stack
- Java default: ~1MB stack (~1000-10000 calls)
- Iterative stack uses heap (much larger)

**Prevention:**
- Limit recursion depth
- Use iterative with explicit stack
- Tail recursion (if language optimizes)
- Increase JVM stack size: `-Xss2m`

### Exam Strategy

**When you see a stack problem:**

1. **Identify** what goes on stack
2. **Decide** when to push/pop
3. **Determine** what empty stack means
4. **Handle** edge cases:
   - Empty stack
   - Single element
   - Pop from empty (underflow)
   - Push to full (overflow for array)
5. **Analyze** complexity (usually O(n) time, O(n) space)
6. **Test** with small example by hand

**Common Exam Questions:**
- Implement stack with array/linked list
- Balanced parentheses
- Evaluate postfix expression
- Reverse using stack
- Convert recursion to iteration
- Min/max stack in O(1)
- Two stacks in one array

---

## 4.5 Additional Recursion Practice Problems

### Problem 6: Generate All Subsets (Power Set)
**Difficulty:** Medium-Hard

**Problem:** Given an array of distinct integers, generate all possible subsets (the power set) using recursion.

**Example:**
- Input: [1, 2, 3]
- Output: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]

**Hint:** For each element, you have two choices: include it or exclude it. Use recursion to make this choice for each element. Base case: when you've processed all elements, add current subset to result. Recursive case: recursively call with (1) current element excluded, (2) current element included. Think of it as a binary decision tree of depth n.

**Key concepts tested:**
- Binary recursion (two choices per element)
- Building result through backtracking
- Time complexity: O(2^n) - each element has 2 choices

---

### Problem 7: Count Occurrences in Array
**Difficulty:** Easy-Medium

**Problem:** Write a recursive function to count how many times a target value appears in an array.

**Example:**
- Input: arr = [1, 2, 3, 2, 4, 2], target = 2
- Output: 3

**Hint:** Base case: empty array or index out of bounds returns 0. Recursive case: if current element equals target, return 1 + count in rest of array; otherwise return 0 + count in rest of array. Use index-based recursion starting from 0.

**Key concepts tested:**
- Linear recursion with index
- Conditional counting
- Array traversal recursively

---

### Problem 8: Merge Sort
**Difficulty:** Hard

**Problem:** Implement the merge sort algorithm recursively to sort an array in ascending order.

**Example:**
- Input: [38, 27, 43, 3, 9, 82, 10]
- Output: [3, 9, 10, 27, 38, 43, 82]

**Hint:** Divide and conquer approach. Base case: array of size 0 or 1 is already sorted. Recursive case: (1) divide array into two halves, (2) recursively sort each half, (3) merge the two sorted halves. The merge step is key: use two pointers to combine sorted arrays. Time complexity: O(n log n).

**Key concepts tested:**
- Divide and conquer recursion
- Multiple recursive calls
- Merging sorted arrays
- Classic sorting algorithm

---

### Problem 9: Path Sum in Binary Tree
**Difficulty:** Medium-Hard

**Problem:** Given a binary tree and a target sum, determine if there exists a root-to-leaf path where the sum of node values equals the target.

**Example:**
```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```
- Input: target = 22
- Output: true (path: 5→4→11→2)

**Hint:** Base case: if node is null, return false; if leaf node, check if value equals remaining sum. Recursive case: subtract current node's value from target, then recursively check left subtree OR right subtree with new target. Think: "Can I make this sum using left path? If not, can I make it using right path?"

**Key concepts tested:**
- Tree recursion
- Path tracking
- Boolean return with OR logic
- Requires tree node structure: `class TreeNode { int val; TreeNode left, right; }`

---

### Problem 10: Print All Permutations of String
**Difficulty:** Hard

**Problem:** Generate all permutations of a string recursively.

**Example:**
- Input: "ABC"
- Output: ["ABC", "ACB", "BAC", "BCA", "CAB", "CBA"]

**Hint:** Backtracking approach. For each position, try every unused character. Base case: when you've used all characters, add current permutation to result. Recursive case: for each remaining character, (1) add it to current permutation, (2) mark as used, (3) recurse on remaining positions, (4) backtrack by unmarking. Alternative: swap approach - swap each character with first position, recurse on rest, swap back. Time: O(n!).

**Key concepts tested:**
- Backtracking recursion
- State management (used/unused)
- Combinatorial explosion
- Classic interview problem

---

## 5.8 Additional Stack Practice Problems

### Problem 6: Implement Queue Using Two Stacks
**Difficulty:** Medium

**Problem:** Implement a queue using only two stacks. Support enqueue (add to rear) and dequeue (remove from front) operations.

**Example:**
```
Queue q = new Queue();
q.enqueue(1);
q.enqueue(2);
q.enqueue(3);
q.dequeue(); // returns 1
q.dequeue(); // returns 2
```

**Hint:** Use two stacks: stack1 for enqueue, stack2 for dequeue. Enqueue: always push to stack1 (O(1)). Dequeue: if stack2 is empty, pop all from stack1 and push to stack2 (this reverses order), then pop from stack2. Think: stack1 is input stack, stack2 is output stack. Amortized O(1) for both operations.

**Key concepts tested:**
- Multiple stack coordination
- LIFO to FIFO conversion
- Amortized analysis
- Data structure design

---

### Problem 7: Largest Rectangle in Histogram
**Difficulty:** Hard

**Problem:** Given an array representing heights of bars in a histogram, find the area of the largest rectangle that can be formed.

**Example:**
- Input: heights = [2, 1, 5, 6, 2, 3]
- Output: 10 (rectangle with height 5-6, width 2)

**Hint:** Use a stack to store indices of bars. Maintain an increasing stack (by height). When you encounter a bar shorter than stack top, it means bars in stack can't extend further right. Pop stack and calculate area with popped bar as height: width = current index - index after new top - 1. Push current index to stack. After processing all bars, pop remaining and calculate. Time: O(n).

**Key concepts tested:**
- Monotonic stack (increasing)
- Complex stack manipulation
- Index storage vs value storage
- Classic hard problem

---

### Problem 8: Simplify Unix Path
**Difficulty:** Medium

**Problem:** Given an absolute path for a file (Unix-style), simplify it by resolving `.` (current directory) and `..` (parent directory).

**Example:**
- Input: "/a/./b/../../c/"
- Output: "/c"
- Input: "/../"
- Output: "/"
- Input: "/home//foo/"
- Output: "/home/foo"

**Hint:** Split path by '/'. Use stack for directory names. For each component: if empty or ".", skip; if "..", pop stack (if not empty); otherwise push to stack. After processing all components, build path from stack. Handle edge cases: stay at root if ".." at root level, ignore multiple slashes.

**Key concepts tested:**
- Stack for path tracking
- String parsing
- State management
- Real-world application

---

### Problem 9: Daily Temperatures
**Difficulty:** Medium

**Problem:** Given an array of daily temperatures, return an array where each element tells you how many days you have to wait until a warmer temperature. If there's no future warmer day, put 0.

**Example:**
- Input: [73, 74, 75, 71, 69, 72, 76, 73]
- Output: [1, 1, 4, 2, 1, 1, 0, 0]
- Explanation: Day 0 (73°): next warmer is day 1 (74°), wait 1 day

**Hint:** Use a stack to store indices (not temperatures!). Iterate through temperatures. While stack is not empty and current temp > temp at stack top index: this means we found a warmer day for that index, so pop index, calculate days difference (current i - popped index), store in result. Push current index to stack. At end, remaining indices in stack have no warmer day (result = 0). Time: O(n).

**Key concepts tested:**
- Monotonic stack pattern
- Index tracking
- Stack of indices vs values
- Next greater element variation

---

### Problem 10: Remove K Digits to Make Smallest Number
**Difficulty:** Hard

**Problem:** Given a string representing a non-negative integer and an integer k, remove k digits from the number to make it the smallest possible number.

**Example:**
- Input: num = "1432219", k = 3
- Output: "1219"
- Explanation: Remove digits 4, 3, 2 to get smallest

**Hint:** Use a monotonic increasing stack. Iterate through digits. While k > 0 and stack not empty and stack top > current digit: pop stack (remove larger digit), decrement k. Push current digit. After processing, if k > 0, remove last k digits. Build result from stack, removing leading zeros. Edge case: if result is empty, return "0".

**Key concepts tested:**
- Monotonic stack (increasing)
- Greedy algorithm with stack
- String manipulation
- Edge case handling (leading zeros, all removed)

---

# 5.9 Practice Problem Test Drivers

> **📌 IMPORTANT NOTE:** Complete solution implementations are available upon explicit request. The test drivers below provide method signatures and comprehensive test cases for you to implement and verify your solutions.

## How to Use These Test Drivers

1. **Copy the entire test driver class** to a new `.java` file
2. **Implement the empty methods** marked with `// TODO: Implement this`
3. **Run the program** with assertions enabled: `java -ea ClassName`
4. **All tests should pass** if your implementation is correct
5. **If stuck**, review the hints in the problem description above
6. **Request solutions** if needed after attempting the problem yourself

---

## Recursion Test Drivers

### Test Driver 1: Subsets (Power Set)

```java
import java.util.*;

public class SubsetGenerator {

    /**
     * Generate all subsets of the given array
     * TODO: Implement this method using recursion
     *
     * @param nums array of distinct integers
     * @return list of all subsets
     */
    public static List<List<Integer>> generateSubsets(int[] nums) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Helper method signature (optional - you may need this)
    private static void backtrack(int[] nums, int index, List<Integer> current,
                                   List<List<Integer>> result) {
        // TODO: Implement helper if needed
    }

    public static void main(String[] args) {
        // Test 1: Empty array
        int[] test1 = {};
        List<List<Integer>> result1 = generateSubsets(test1);
        assert result1.size() == 1 : "Empty array should have 1 subset";
        System.out.println("Test 1 passed: " + result1);

        // Test 2: Single element
        int[] test2 = {1};
        List<List<Integer>> result2 = generateSubsets(test2);
        assert result2.size() == 2 : "Single element should have 2 subsets";
        System.out.println("Test 2 passed: " + result2);

        // Test 3: Two elements
        int[] test3 = {1, 2};
        List<List<Integer>> result3 = generateSubsets(test3);
        assert result3.size() == 4 : "Two elements should have 4 subsets";
        System.out.println("Test 3 passed: " + result3);

        // Test 4: Three elements
        int[] test4 = {1, 2, 3};
        List<List<Integer>> result4 = generateSubsets(test4);
        assert result4.size() == 8 : "Three elements should have 8 subsets (2^3)";
        System.out.println("Test 4 passed: " + result4);

        // Test 5: Four elements
        int[] test5 = {1, 2, 3, 4};
        List<List<Integer>> result5 = generateSubsets(test5);
        assert result5.size() == 16 : "Four elements should have 16 subsets (2^4)";
        System.out.println("Test 5 passed: " + result5);

        System.out.println("\n✅ All subset generation tests passed!");
    }
}
```

---

### Test Driver 2: Count Occurrences

```java
public class CountOccurrences {

    /**
     * Count occurrences of target in array using recursion
     * TODO: Implement this method
     *
     * @param arr the array to search
     * @param target value to count
     * @param index starting index
     * @return count of target in array
     */
    public static int countOccurrences(int[] arr, int target, int index) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Wrapper method for convenience
    public static int countOccurrences(int[] arr, int target) {
        return countOccurrences(arr, target, 0);
    }

    public static void main(String[] args) {
        // Test 1: Empty array
        int[] test1 = {};
        assert countOccurrences(test1, 5) == 0 : "Empty array should return 0";
        System.out.println("Test 1 passed: empty array");

        // Test 2: No occurrences
        int[] test2 = {1, 2, 3, 4};
        assert countOccurrences(test2, 5) == 0 : "Should return 0 when not found";
        System.out.println("Test 2 passed: no occurrences");

        // Test 3: Single occurrence
        int[] test3 = {1, 2, 3, 4, 5};
        assert countOccurrences(test3, 3) == 1 : "Should return 1";
        System.out.println("Test 3 passed: single occurrence");

        // Test 4: Multiple occurrences
        int[] test4 = {1, 2, 3, 2, 4, 2};
        assert countOccurrences(test4, 2) == 3 : "Should return 3";
        System.out.println("Test 4 passed: multiple occurrences");

        // Test 5: All same value
        int[] test5 = {7, 7, 7, 7, 7};
        assert countOccurrences(test5, 7) == 5 : "Should return 5";
        System.out.println("Test 5 passed: all same value");

        // Test 6: First and last
        int[] test6 = {5, 1, 2, 3, 5};
        assert countOccurrences(test6, 5) == 2 : "Should return 2";
        System.out.println("Test 6 passed: first and last");

        System.out.println("\n✅ All count occurrences tests passed!");
    }
}
```

---

### Test Driver 3: Merge Sort

```java
import java.util.Arrays;

public class MergeSortRecursive {

    /**
     * Sort array using merge sort (recursive)
     * TODO: Implement this method
     *
     * @param arr array to sort
     * @param left starting index
     * @param right ending index
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Merge two sorted subarrays
     * TODO: Implement this helper method
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Wrapper method for convenience
    public static void mergeSort(int[] arr) {
        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        }
    }

    public static void main(String[] args) {
        // Test 1: Empty array
        int[] test1 = {};
        mergeSort(test1);
        assert Arrays.equals(test1, new int[]{}) : "Empty array unchanged";
        System.out.println("Test 1 passed: " + Arrays.toString(test1));

        // Test 2: Single element
        int[] test2 = {42};
        mergeSort(test2);
        assert Arrays.equals(test2, new int[]{42}) : "Single element unchanged";
        System.out.println("Test 2 passed: " + Arrays.toString(test2));

        // Test 3: Already sorted
        int[] test3 = {1, 2, 3, 4, 5};
        mergeSort(test3);
        assert Arrays.equals(test3, new int[]{1, 2, 3, 4, 5}) : "Already sorted";
        System.out.println("Test 3 passed: " + Arrays.toString(test3));

        // Test 4: Reverse sorted
        int[] test4 = {5, 4, 3, 2, 1};
        mergeSort(test4);
        assert Arrays.equals(test4, new int[]{1, 2, 3, 4, 5}) : "Reverse sorted";
        System.out.println("Test 4 passed: " + Arrays.toString(test4));

        // Test 5: Random order
        int[] test5 = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(test5);
        assert Arrays.equals(test5, new int[]{3, 9, 10, 27, 38, 43, 82}) : "Random order";
        System.out.println("Test 5 passed: " + Arrays.toString(test5));

        // Test 6: Duplicates
        int[] test6 = {4, 2, 4, 1, 3, 2};
        mergeSort(test6);
        assert Arrays.equals(test6, new int[]{1, 2, 2, 3, 4, 4}) : "With duplicates";
        System.out.println("Test 6 passed: " + Arrays.toString(test6));

        System.out.println("\n✅ All merge sort tests passed!");
    }
}
```

---

### Test Driver 4: Path Sum in Binary Tree

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PathSumBinaryTree {

    /**
     * Check if there exists a root-to-leaf path with given sum
     * TODO: Implement this method
     *
     * @param root root of binary tree
     * @param targetSum target sum to find
     * @return true if path exists, false otherwise
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        // Test 1: Empty tree
        assert !hasPathSum(null, 0) : "Null tree should return false";
        System.out.println("Test 1 passed: null tree");

        // Test 2: Single node matching
        TreeNode test2 = new TreeNode(5);
        assert hasPathSum(test2, 5) : "Single node with matching value";
        System.out.println("Test 2 passed: single node match");

        // Test 3: Single node not matching
        TreeNode test3 = new TreeNode(5);
        assert !hasPathSum(test3, 10) : "Single node not matching";
        System.out.println("Test 3 passed: single node no match");

        // Test 4: Left path exists
        //     5
        //    / \
        //   4   8
        //  /
        // 11
        TreeNode test4 = new TreeNode(5);
        test4.left = new TreeNode(4);
        test4.right = new TreeNode(8);
        test4.left.left = new TreeNode(11);
        assert hasPathSum(test4, 20) : "Path 5->4->11 = 20";
        System.out.println("Test 4 passed: left path");

        // Test 5: Right path exists
        assert hasPathSum(test4, 13) : "Path 5->8 = 13";
        System.out.println("Test 5 passed: right path");

        // Test 6: Complex tree
        //       5
        //      / \
        //     4   8
        //    /   / \
        //   11  13  4
        //  /  \      \
        // 7    2      1
        TreeNode test6 = new TreeNode(5);
        test6.left = new TreeNode(4);
        test6.right = new TreeNode(8);
        test6.left.left = new TreeNode(11);
        test6.left.left.left = new TreeNode(7);
        test6.left.left.right = new TreeNode(2);
        test6.right.left = new TreeNode(13);
        test6.right.right = new TreeNode(4);
        test6.right.right.right = new TreeNode(1);

        assert hasPathSum(test6, 22) : "Path 5->4->11->2 = 22";
        assert hasPathSum(test6, 26) : "Path 5->8->13 = 26";
        assert hasPathSum(test6, 18) : "Path 5->8->4->1 = 18";
        assert !hasPathSum(test6, 100) : "No path with sum 100";
        System.out.println("Test 6 passed: complex tree");

        System.out.println("\n✅ All path sum tests passed!");
    }
}
```

---

### Test Driver 5: String Permutations

```java
import java.util.*;

public class StringPermutations {

    /**
     * Generate all permutations of a string
     * TODO: Implement this method
     *
     * @param str input string
     * @return list of all permutations
     */
    public static List<String> generatePermutations(String str) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Helper method signature (optional)
    private static void backtrack(char[] chars, boolean[] used,
                                   StringBuilder current, List<String> result) {
        // TODO: Implement helper if needed
    }

    public static void main(String[] args) {
        // Test 1: Empty string
        List<String> test1 = generatePermutations("");
        assert test1.size() == 1 && test1.get(0).equals("") : "Empty string";
        System.out.println("Test 1 passed: " + test1);

        // Test 2: Single character
        List<String> test2 = generatePermutations("A");
        assert test2.size() == 1 && test2.contains("A") : "Single char";
        System.out.println("Test 2 passed: " + test2);

        // Test 3: Two characters
        List<String> test3 = generatePermutations("AB");
        assert test3.size() == 2 : "Two chars should have 2! = 2 permutations";
        assert test3.contains("AB") && test3.contains("BA") : "Should contain AB and BA";
        System.out.println("Test 3 passed: " + test3);

        // Test 4: Three characters
        List<String> test4 = generatePermutations("ABC");
        assert test4.size() == 6 : "Three chars should have 3! = 6 permutations";
        Set<String> expected4 = Set.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA");
        assert new HashSet<>(test4).equals(expected4) : "Should contain all 6 permutations";
        System.out.println("Test 4 passed: " + test4);

        // Test 5: Four characters (larger test)
        List<String> test5 = generatePermutations("ABCD");
        assert test5.size() == 24 : "Four chars should have 4! = 24 permutations";
        assert new HashSet<>(test5).size() == 24 : "All should be unique";
        System.out.println("Test 5 passed: " + test5.size() + " permutations generated");

        System.out.println("\n✅ All string permutation tests passed!");
    }
}
```

---

## Stack Test Drivers

### Test Driver 6: Queue Using Two Stacks

```java
import java.util.Stack;

class QueueUsingStacks {

    // TODO: Declare your instance variables here
    // Hint: You need two stacks

    /**
     * Initialize your data structure
     */
    public QueueUsingStacks() {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Add element to rear of queue
     * TODO: Implement this - should be O(1)
     */
    public void enqueue(int x) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Remove element from front of queue
     * TODO: Implement this - amortized O(1)
     */
    public int dequeue() {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Get front element without removing
     * TODO: Implement this
     */
    public int peek() {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Check if queue is empty
     * TODO: Implement this
     */
    public boolean isEmpty() {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        QueueUsingStacks q = new QueueUsingStacks();

        // Test 1: Empty queue
        assert q.isEmpty() : "New queue should be empty";
        System.out.println("Test 1 passed: empty queue");

        // Test 2: Single enqueue and dequeue
        q.enqueue(1);
        assert !q.isEmpty() : "Queue should not be empty";
        assert q.dequeue() == 1 : "Should return 1";
        assert q.isEmpty() : "Queue should be empty after dequeue";
        System.out.println("Test 2 passed: single enqueue/dequeue");

        // Test 3: FIFO order
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assert q.dequeue() == 1 : "First in, first out";
        assert q.dequeue() == 2 : "Second element";
        assert q.dequeue() == 3 : "Third element";
        System.out.println("Test 3 passed: FIFO order");

        // Test 4: Interleaved operations
        q.enqueue(1);
        q.enqueue(2);
        assert q.dequeue() == 1 : "Dequeue 1";
        q.enqueue(3);
        q.enqueue(4);
        assert q.dequeue() == 2 : "Dequeue 2";
        assert q.dequeue() == 3 : "Dequeue 3";
        assert q.dequeue() == 4 : "Dequeue 4";
        System.out.println("Test 4 passed: interleaved operations");

        // Test 5: Peek
        q.enqueue(5);
        q.enqueue(6);
        assert q.peek() == 5 : "Peek should return 5";
        assert q.peek() == 5 : "Peek should not remove";
        assert q.dequeue() == 5 : "Dequeue should return 5";
        assert q.peek() == 6 : "Peek should now return 6";
        System.out.println("Test 5 passed: peek operation");

        System.out.println("\n✅ All queue using stacks tests passed!");
    }
}
```

---

### Test Driver 7: Largest Rectangle in Histogram

```java
import java.util.Stack;

public class LargestRectangleHistogram {

    /**
     * Find area of largest rectangle in histogram
     * TODO: Implement this using a stack
     *
     * @param heights array of bar heights
     * @return area of largest rectangle
     */
    public static int largestRectangleArea(int[] heights) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        // Test 1: Empty array
        assert largestRectangleArea(new int[]{}) == 0 : "Empty should return 0";
        System.out.println("Test 1 passed: empty histogram");

        // Test 2: Single bar
        assert largestRectangleArea(new int[]{5}) == 5 : "Single bar";
        System.out.println("Test 2 passed: single bar");

        // Test 3: All same height
        assert largestRectangleArea(new int[]{2, 2, 2, 2}) == 8 : "All same height";
        System.out.println("Test 3 passed: uniform height");

        // Test 4: Increasing heights
        assert largestRectangleArea(new int[]{1, 2, 3, 4, 5}) == 9 : "Increasing";
        System.out.println("Test 4 passed: increasing heights");

        // Test 5: Decreasing heights
        assert largestRectangleArea(new int[]{5, 4, 3, 2, 1}) == 9 : "Decreasing";
        System.out.println("Test 5 passed: decreasing heights");

        // Test 6: Example from problem
        assert largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}) == 10 : "Example case";
        System.out.println("Test 6 passed: example case");

        // Test 7: Large rectangle in middle
        assert largestRectangleArea(new int[]{2, 4}) == 4 : "Two bars";
        System.out.println("Test 7 passed: two bars");

        System.out.println("\n✅ All largest rectangle tests passed!");
    }
}
```

---

### Test Driver 8: Simplify Unix Path

```java
import java.util.Stack;

public class SimplifyPath {

    /**
     * Simplify Unix-style file path
     * TODO: Implement this using a stack
     *
     * @param path absolute path to simplify
     * @return simplified canonical path
     */
    public static String simplifyPath(String path) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        // Test 1: Simple path
        assert simplifyPath("/home/").equals("/home") : "Remove trailing slash";
        System.out.println("Test 1 passed: /home/ -> " + simplifyPath("/home/"));

        // Test 2: Current directory
        assert simplifyPath("/a/./b/").equals("/a/b") : "Remove current directory";
        System.out.println("Test 2 passed: /a/./b/ -> " + simplifyPath("/a/./b/"));

        // Test 3: Parent directory
        assert simplifyPath("/a/b/../c/").equals("/a/c") : "Handle parent directory";
        System.out.println("Test 3 passed: /a/b/../c/ -> " + simplifyPath("/a/b/../c/"));

        // Test 4: Multiple parent directories
        assert simplifyPath("/a/./b/../../c/").equals("/c") : "Multiple parent dirs";
        System.out.println("Test 4 passed: /a/./b/../../c/ -> " + simplifyPath("/a/./b/../../c/"));

        // Test 5: Parent at root
        assert simplifyPath("/../").equals("/") : "Parent at root stays at root";
        System.out.println("Test 5 passed: /../ -> " + simplifyPath("/../"));

        // Test 6: Multiple slashes
        assert simplifyPath("/home//foo/").equals("/home/foo") : "Multiple slashes";
        System.out.println("Test 6 passed: /home//foo/ -> " + simplifyPath("/home//foo/"));

        // Test 7: Complex path
        assert simplifyPath("/a/../../b/../c//.//").equals("/c") : "Complex path";
        System.out.println("Test 7 passed: complex path -> " + simplifyPath("/a/../../b/../c//.//"));

        // Test 8: Root only
        assert simplifyPath("/").equals("/") : "Root only";
        System.out.println("Test 8 passed: / -> " + simplifyPath("/"));

        System.out.println("\n✅ All simplify path tests passed!");
    }
}
```

---

### Test Driver 9: Daily Temperatures

```java
import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    /**
     * Calculate days until warmer temperature for each day
     * TODO: Implement this using a stack
     *
     * @param temperatures array of daily temperatures
     * @return array of days to wait for warmer temperature
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        // Test 1: Single day
        int[] test1 = {30};
        assert Arrays.equals(dailyTemperatures(test1), new int[]{0}) : "Single day";
        System.out.println("Test 1 passed: " + Arrays.toString(dailyTemperatures(test1)));

        // Test 2: All increasing
        int[] test2 = {30, 40, 50, 60};
        assert Arrays.equals(dailyTemperatures(test2), new int[]{1, 1, 1, 0}) : "All increasing";
        System.out.println("Test 2 passed: " + Arrays.toString(dailyTemperatures(test2)));

        // Test 3: All decreasing
        int[] test3 = {60, 50, 40, 30};
        assert Arrays.equals(dailyTemperatures(test3), new int[]{0, 0, 0, 0}) : "All decreasing";
        System.out.println("Test 3 passed: " + Arrays.toString(dailyTemperatures(test3)));

        // Test 4: Example from problem
        int[] test4 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected4 = {1, 1, 4, 2, 1, 1, 0, 0};
        assert Arrays.equals(dailyTemperatures(test4), expected4) : "Example case";
        System.out.println("Test 4 passed: " + Arrays.toString(dailyTemperatures(test4)));

        // Test 5: Mixed temperatures
        int[] test5 = {30, 60, 90};
        assert Arrays.equals(dailyTemperatures(test5), new int[]{1, 1, 0}) : "Mixed temps";
        System.out.println("Test 5 passed: " + Arrays.toString(dailyTemperatures(test5)));

        System.out.println("\n✅ All daily temperatures tests passed!");
    }
}
```

---

### Test Driver 10: Remove K Digits

```java
import java.util.Stack;

public class RemoveKDigits {

    /**
     * Remove k digits to make smallest possible number
     * TODO: Implement this using a monotonic stack
     *
     * @param num string representing number
     * @param k number of digits to remove
     * @return smallest possible number as string
     */
    public static String removeKdigits(String num, int k) {
        // TODO: Implement this
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        // Test 1: Remove leading larger digits
        assert removeKdigits("1432219", 3).equals("1219") : "Example case";
        System.out.println("Test 1 passed: 1432219, k=3 -> " + removeKdigits("1432219", 3));

        // Test 2: Remove all digits
        assert removeKdigits("10", 2).equals("0") : "Remove all";
        System.out.println("Test 2 passed: 10, k=2 -> " + removeKdigits("10", 2));

        // Test 3: Already optimal
        assert removeKdigits("123456", 0).equals("123456") : "k=0";
        System.out.println("Test 3 passed: 123456, k=0 -> " + removeKdigits("123456", 0));

        // Test 4: Leading zeros
        assert removeKdigits("10200", 1).equals("200") : "Leading zeros";
        System.out.println("Test 4 passed: 10200, k=1 -> " + removeKdigits("10200", 1));

        // Test 5: All same digits
        assert removeKdigits("1111", 2).equals("11") : "All same";
        System.out.println("Test 5 passed: 1111, k=2 -> " + removeKdigits("1111", 2));

        // Test 6: Decreasing digits
        assert removeKdigits("9876543210", 5).equals("43210") : "Decreasing";
        System.out.println("Test 6 passed: 9876543210, k=5 -> " + removeKdigits("9876543210", 5));

        // Test 7: Result is zero
        assert removeKdigits("10", 1).equals("0") : "Result zero";
        System.out.println("Test 7 passed: 10, k=1 -> " + removeKdigits("10", 1));

        System.out.println("\n✅ All remove k digits tests passed!");
    }
}
```

---

# 6. COMPREHENSIVE EXAM CHECKLISTS

## 6.1 Recursion Exam Checklist

### Must-Know Concepts
- [ ] **Definition:** Function calls itself with smaller/simpler input
- [ ] **Two parts:** Base case (stops recursion) + Recursive case (makes progress)
- [ ] **Call stack:** Each call creates stack frame, popped when function returns
- [ ] **Progress:** Must move toward base case every call
- [ ] **Trust:** Assume recursion works for smaller input (leap of faith)

### Implementation Checklist
- [ ] Identify simplest input (base case)
- [ ] Define what base case returns
- [ ] Determine how to make problem smaller
- [ ] Ensure progress toward base case
- [ ] Combine recursive result with current level
- [ ] Handle edge cases (empty, zero, negative, single element)

### Common Patterns to Memorize
- [ ] **Linear:** One recursive call (factorial, sum, search)
- [ ] **Binary:** Two recursive calls (Fibonacci, tree traversal)
- [ ] **Tail:** Recursive call is last operation (can optimize)
- [ ] **Divide-and-conquer:** Split in half (binary search, merge sort)

### Complexity Quick Reference
- [ ] T(n) = T(n-1) + O(1) → **O(n)** time, **O(n)** space
- [ ] T(n) = 2T(n-1) + O(1) → **O(2^n)** time (naive Fibonacci)
- [ ] T(n) = T(n/2) + O(1) → **O(log n)** time (binary search)
- [ ] T(n) = 2T(n/2) + O(n) → **O(n log n)** time (merge sort)

### Common Mistakes to Avoid
- [ ] ❌ Missing base case → infinite recursion
- [ ] ❌ Wrong base case → incorrect result or doesn't terminate
- [ ] ❌ Not making progress → infinite loop
- [ ] ❌ Overlapping subproblems without memoization → exponential time
- [ ] ❌ Deep recursion → stack overflow

### Exam Problem Types
- [ ] Factorial/power calculations
- [ ] Array sum/max/min
- [ ] String reversal/palindrome
- [ ] Binary search
- [ ] Fibonacci sequence
- [ ] Count digits/characters
- [ ] GCD (Euclid's algorithm)
- [ ] Tower of Hanoi

---

## 6.2 Stack Exam Checklist

### Must-Know Concepts
- [ ] **LIFO:** Last In, First Out
- [ ] **Top:** Only access point (like stack of plates)
- [ ] **Core ops:** push, pop, peek, isEmpty - all **O(1)**
- [ ] **Two implementations:** Array-based (fixed size) vs Linked List (unlimited)

### Array-Based Stack
- [ ] Structure: `int[] array`, `int top`, `int capacity`
- [ ] Empty: `top = -1`
- [ ] Full: `top = capacity - 1`
- [ ] Size: `top + 1`
- [ ] Push: Check full, then `array[++top] = item`
- [ ] Pop: Check empty, then `return array[top--]`
- [ ] Peek: `return array[top]` (no size change)

### Linked List-Based Stack
- [ ] Structure: `Node top`, `int size`
- [ ] Empty: `top = null`
- [ ] No capacity limit (until heap exhaustion)
- [ ] Push: Create node, set `next = top`, update `top`
- [ ] Pop: Save data, `top = top.next`, return data
- [ ] Peek: `return top.data`

### Applications to Know
- [ ] **Balanced parentheses** - push openers, pop on closers
- [ ] **Postfix evaluation** - push operands, pop for operators
- [ ] **Reversing** - push all, pop all
- [ ] **DFS/Backtracking** - push states, pop to backtrack
- [ ] **Function calls** - recursion uses call stack
- [ ] **Undo/Redo** - stack of states

### Recursion ↔ Stack
- [ ] Recursion IS implicit stack (call stack)
- [ ] Can convert any recursion to iteration with explicit stack
- [ ] Stack overflow from too-deep recursion
- [ ] Explicit stack uses heap (larger than call stack)

### Common Mistakes to Avoid
- [ ] ❌ Pop from empty → underflow
- [ ] ❌ Push to full array → overflow
- [ ] ❌ Forget to check isEmpty before peek/pop
- [ ] ❌ Wrong size calculation: `return top` instead of `top + 1`
- [ ] ❌ Not updating top pointer in linked list

### Exam Problem Types
- [ ] Implement stack (array or linked list)
- [ ] Balanced parentheses/brackets
- [ ] Evaluate postfix expression
- [ ] Reverse string/array using stack
- [ ] Next greater element (monotonic stack)
- [ ] Min/Max stack with O(1) operations
- [ ] Valid push/pop sequences
- [ ] Convert recursion to iteration

---

## 6.3 Quick Decision Trees

### "Should I use recursion?"

```
Is problem naturally recursive (trees, divide-and-conquer)?
├─ YES → Use recursion
│   └─ Will recursion be deep (>1000 calls)?
│       ├─ YES → Use iteration with explicit stack OR tail recursion
│       └─ NO → Recursion is fine
│
└─ NO → Is iterative solution clearer?
    ├─ YES → Use iteration
    └─ NO → Recursion might be cleaner
```

### "Should I use a stack?"

```
What access pattern do I need?
├─ LIFO (last in, first out) → Stack
├─ FIFO (first in, first out) → Queue
├─ Random access by index → Array/ArrayList
└─ Fast search → HashSet/HashMap

Do I need to:
├─ Reverse something? → Stack
├─ Match pairs (parentheses)? → Stack
├─ Backtrack? → Stack
├─ DFS? → Stack (BFS uses queue)
└─ Track nested structure? → Stack
```

### "Array-based or Linked List stack?"

```
Do I know the maximum size?
├─ YES → Array-based (simpler, faster, cache-friendly)
└─ NO → Linked List (no resize, unlimited*)
    *until heap exhausted
```

---

# 7. SENTENCE TEMPLATES FOR EXAM ANSWERS

## 7.1 Recursion Templates

### Explaining Base Case
> "The base case occurs when **[condition]**, at which point we return **[value]** because **[reason - this is the simplest case]**."

**Example:**
> "The base case occurs when **n ≤ 1**, at which point we return **1** because **the factorial of 0 or 1 is defined as 1, and this is the simplest case that doesn't require further recursion**."

### Explaining Recursive Case
> "In the recursive case, we **[action on current level]** and then **[recursive call with smaller input]**. This works because **[why the smaller problem helps solve the larger one]**."

**Example:**
> "In the recursive case, we **multiply n by** the result of **factorial(n-1)**. This works because **n! = n × (n-1)!, so solving for (n-1)! and multiplying by n gives us n!**."

### Explaining Complexity
> "The time complexity is **O([complexity])** because **[reason: number of calls × work per call]**. The space complexity is **O([complexity])** due to **[call stack depth]**."

**Example:**
> "The time complexity is **O(n)** because **we make n recursive calls, each doing O(1) work**. The space complexity is **O(n)** due to **the call stack storing n stack frames at maximum depth**."

### Explaining Why Recursion Works
> "This recursive solution works by **[breaking down the problem]**. At each level, we **[reduce the problem size by X]**, ensuring we eventually reach the base case of **[base condition]**. The results are combined by **[how we use the recursive return value]**."

---

## 7.2 Stack Templates

### Explaining Stack Choice
> "A stack is appropriate for this problem because **[LIFO property/matching pairs/backtracking/reversal]**, which aligns with **[specific requirement of the problem]**."

**Example:**
> "A stack is appropriate for this problem because **we need to match opening and closing parentheses**, which aligns with **the LIFO property: the most recently seen opening bracket should match the next closing bracket**."

### Describing Stack Operations
> "We **push [what]** onto the stack when **[condition]**, and **pop** when **[condition]**. An empty stack at the end indicates **[meaning]**."

**Example:**
> "We **push opening brackets** onto the stack when **we encounter '(', '[', or '{'**, and **pop** when **we encounter a closing bracket to verify it matches the most recent opener**. An empty stack at the end indicates **all brackets were properly matched**."

### Array-based Implementation Explanation
> "For the array-based implementation, we maintain a **top index initialized to -1** indicating an empty stack. When pushing, we **increment top and place the element at array[top]**. When popping, we **return array[top] and decrement top**. The stack is full when **top == capacity - 1**."

### Linked List Implementation Explanation
> "For the linked list implementation, **top points to the head of the list**. Pushing creates a new node and **sets its next to the current top, then updates top to the new node**. Popping **saves top's data, moves top to top.next, and returns the saved data**. The stack is empty when **top == null**."

### Complexity Explanation
> "All stack operations (push, pop, peek, isEmpty) are **O(1)** because **[reason: direct access to top, no traversal needed]**. The space complexity is **O(n)** where n is **[number of elements in stack]**."

---

## 7.3 Problem-Solving Templates

### For "Implement X" Questions
1. "I will implement **[data structure/algorithm]** using **[approach]**."
2. "The key components are: **[list main parts]**."
3. "The algorithm works as follows: **[step-by-step]**."
4. "Edge cases to handle: **[list edge cases]**."
5. "Time complexity: **O([X])**, Space complexity: **O([Y])**."

### For "Analyze Complexity" Questions
1. "Let n = **[what n represents]**."
2. "The algorithm performs **[number of operations]** operations."
3. "Each operation takes **O([X])**."
4. "Total time complexity: **O([result])**."
5. "Space complexity is **O([Y])** due to **[reason: recursion depth/stack usage/auxiliary space]**."

### For "Trace Execution" Questions
1. "Initial state: **[describe]**."
2. "Step 1: **[action]** → Result: **[state]**."
3. "Step 2: **[action]** → Result: **[state]**."
4. (Continue for all steps)
5. "Final state: **[result]**."

### For "Compare Approaches" Questions
1. "**Approach 1 ([name])**: **[brief description]**. Time: **O([X])**, Space: **O([Y])**. Advantage: **[X]**. Disadvantage: **[Y]**."
2. "**Approach 2 ([name])**: **[brief description]**. Time: **O([X])**, Space: **O([Y])**. Advantage: **[X]**. Disadvantage: **[Y]**."
3. "I would choose **[approach]** because **[reasoning based on requirements]**."

---

# 8. CONCISE MEMORIZABLE CHEAT SHEET

## 8.1 Recursion - The 5 Essentials

### 1️⃣ Structure Template
```java
returnType func(params) {
    // BASE: Simplest case
    if (baseCondition) return baseValue;

    // RECURSIVE: Smaller problem
    result = func(smallerInput);

    // COMBINE: Use result
    return combineLogic(result);
}
```

### 2️⃣ The 3 Questions
1. **What's the simplest input?** → Base case
2. **How to make it smaller?** → Recursive call
3. **How to combine results?** → Return logic

### 3️⃣ Complexity Patterns
| Pattern | Time | Space | Example |
|---------|------|-------|---------|
| `T(n) = T(n-1) + c` | O(n) | O(n) | factorial |
| `T(n) = 2T(n-1) + c` | O(2^n) | O(n) | naive fib |
| `T(n) = T(n/2) + c` | O(log n) | O(log n) | binary search |
| `T(n) = 2T(n/2) + n` | O(n log n) | O(log n) | merge sort |

### 4️⃣ Common Patterns
- **Sum array:** `base: i >= len → 0`, `rec: arr[i] + sum(i+1)`
- **Factorial:** `base: n ≤ 1 → 1`, `rec: n * fact(n-1)`
- **Power:** `base: n=0 → 1`, `rec: x * pow(x,n-1)` or `pow(x,n/2)²`
- **Binary search:** `base: left > right → -1`, `rec: search half`

### 5️⃣ Red Flags
- ❌ No base case = infinite recursion
- ❌ Not progressing = infinite loop
- ❌ Deep recursion = stack overflow
- ❌ Overlapping subproblems = use memoization!

---

## 8.2 Stack - The 4 Pillars

### 1️⃣ Core Principle: **LIFO**
```
push(3) → [3]
push(5) → [3, 5]
pop()   → [3]     returns 5
peek()  → [3]     returns 3 (no change)
```

### 2️⃣ Two Implementations

**Array:**
```java
int[] arr; int top = -1; int capacity;
push: arr[++top] = x         // O(1)
pop:  return arr[top--]      // O(1)
size: top + 1
empty: top == -1
full: top == capacity - 1
```

**Linked List:**
```java
Node top; int size;
push: new.next=top; top=new  // O(1)
pop:  data=top.data; top=top.next  // O(1)
size: size field
empty: top == null
full: never (until heap exhausted)
```

### 3️⃣ When to Use
| Problem Keywords | Use Stack? |
|-----------------|------------|
| "Balanced", "Valid", "Matching" | ✅ YES |
| "Reverse" | ✅ YES |
| "DFS", "Backtrack" | ✅ YES |
| "Next greater/smaller" | ✅ YES (monotonic) |
| "Undo/Redo" | ✅ YES |
| "FIFO", "Queue" | ❌ NO |
| "Random access by index" | ❌ NO |

### 4️⃣ Classic Algorithms

**Balanced Parentheses:**
```
for each char:
  if opener → push
  if closer → pop & check match
end: stack must be empty
```

**Postfix Eval:**
```
for each token:
  if number → push
  if operator → pop 2, compute, push result
end: stack has 1 element (answer)
```

**Monotonic Stack (Next Greater):**
```
result = [-1] * n
stack = []
for i from n-1 to 0:
  while stack not empty and stack.top ≤ arr[i]:
    stack.pop()
  if stack not empty: result[i] = stack.top
  stack.push(arr[i])
```

---

## 8.3 Critical Facts to Memorize

### Recursion
- **Stack frame:** params + local vars + return address
- **Stack overflow:** ~1000-10000 calls in Java (default ~1MB)
- **Tail recursion:** Last operation is recursive call (optimizable)
- **Memoization:** Cache results to avoid recomputation

### Stack
- **Underflow:** Pop from empty → `EmptyStackException`
- **Overflow:** Push to full array → `StackOverflowError`
- **Recursion ≡ Stack:** Can always convert recursion to iteration with explicit stack
- **DFS vs BFS:** DFS uses stack, BFS uses queue

### Complexity
- **Array stack:** O(1) all ops, O(capacity) space
- **Linked stack:** O(1) all ops, O(n) space + pointer overhead
- **Recursion space:** O(depth) for call stack
- **Array-based faster:** Cache-friendly, contiguous memory

---

## 8.4 The Ultimate 1-Page Cheat Sheet

### RECURSION
```
BASE CASE → Simplest input, no recursion
RECURSIVE CASE → Smaller input, trust it works
PROGRESS → Must move toward base
COMBINE → Use result from smaller problem

PATTERNS:
  Factorial: n * f(n-1)             O(n)
  Fibonacci: f(n-1) + f(n-2)        O(2^n) → memoize!
  BinSearch: search(left/right half) O(log n)
  Sum: arr[i] + sum(arr,i+1)        O(n)
```

### STACK (LIFO)
```
ARRAY: top=-1, push: arr[++top]=x, pop: arr[top--]
LINKED: top=head, push: new→top→..., pop: top=top.next

APPLICATIONS:
  Balance ( ) [ ] { } → push opener, pop & match closer
  Postfix → push numbers, pop for operators
  Reverse → push all, pop all
  DFS → push neighbors, pop to visit

ERRORS:
  Empty: top=-1 (array) or top=null (linked)
  Full: top=capacity-1 (array only)
```

### COMPLEXITY QUICK GUIDE
```
RECURSION:
  Linear (T(n)=T(n-1)+c): O(n) time, O(n) space
  Binary (T(n)=2T(n-1)+c): O(2^n) time, O(n) space
  Divide (T(n)=T(n/2)+c): O(log n) time, O(log n) space

STACK:
  All operations: O(1)
  Space: O(n) for n elements
```

### EXAM DAY CHECKLIST
```
RECURSION:
  ☑ Base case defined?
  ☑ Makes progress toward base?
  ☑ Handles edge cases (0, 1, empty, negative)?
  ☑ Complexity analyzed?

STACK:
  ☑ Check empty before pop/peek?
  ☑ Check full before push (array)?
  ☑ Update top correctly?
  ☑ Size = top + 1 (array)?
  ☑ Correct LIFO behavior?
```

---

## 8.5 Memory Hooks & Mnemonics

### RECURSION = "BBC"
- **B**ase case: Where it stops
- **B**reak down: Make problem smaller
- **C**ombine: Use recursive result

### STACK = "LIFO-DUMPR"
- **L**ast **I**n **F**irst **O**ut
- **D**epth-first search
- **U**ndo operations
- **M**atching pairs
- **P**ostfix evaluation
- **R**eversal

### Complexity Memory Trick
- **"Minus one, N":** T(n) = T(n-**1**) + c → O(**n**)
- **"Two times, TWO power":** T(n) = **2**T(n-1) + c → O(**2^n**)
- **"Divide two, LOG":** T(n) = T(n/**2**) + c → O(**log n**)

### Stack Implementation Memory Trick
**"Array: Negative one, Plus one"**
- Empty when top = **-1**
- Size = top **+ 1**

**"Linked: Null, Head"**
- Empty when top = **null**
- Top = **head** of list

---

## 8.6 Last-Minute Review (5 Minutes Before Exam)

### Recursion Essentials
1. Base case stops recursion (e.g., `n ≤ 1`, `index >= length`, `empty`)
2. Recursive case makes problem smaller (e.g., `n-1`, `index+1`, `n/2`)
3. Must progress toward base case every call
4. Time = (# calls) × (work per call)
5. Space = max call stack depth

**Common mistakes:** No base, wrong base, no progress, deep recursion

### Stack Essentials
1. LIFO: Last in, first out
2. Operations: push, pop, peek, isEmpty - all O(1)
3. Array: top=-1 (empty), top=capacity-1 (full), size=top+1
4. Linked: top=null (empty), top=head, no capacity limit
5. Uses: balance ( ), postfix eval, reverse, DFS, backtrack

**Common mistakes:** Pop empty, push full, forget isEmpty check, wrong size

### Quick Patterns
- **Factorial:** `if (n ≤ 1) return 1; return n * factorial(n-1);`
- **Sum array:** `if (i >= len) return 0; return arr[i] + sum(i+1);`
- **Binary search:** `if (L > R) return -1; mid = (L+R)/2; if found return mid; else search half;`
- **Balance ( ):** `for ch: if ( push; if ) pop&match; end: empty?`
- **Postfix:** `for token: if # push; if op: pop 2, compute, push;`

### If You Forget Something
- **Recursion:** Think "What's smallest case? How to get smaller?"
- **Stack:** Think "LIFO like plates - last on top, first off"
- **Complexity:** Count operations, recursion = depth of tree

---

**End of MIDTERM_PREP_PART3.md**

*For Linked Lists content, see MIDTERM_PREP_PART2.md*
*For Queues content, see MIDTERM_PREP_PART2.md*
*For Complexity Analysis theory, see curriculum/complexity_analysis.md*
*For general study guide, see MIDTERM_PREP_GUIDE.md*
