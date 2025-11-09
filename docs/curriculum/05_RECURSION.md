# Topic 5: Recursion

## 📖 Concept Summary
Recursion is a problem-solving technique where a function calls itself with a simpler version of the problem until reaching a base case. Every recursive function must have: (1) a base case that stops recursion, and (2) a recursive case that breaks down the problem and moves toward the base case.

## 🧠 Intuition & Mental Model

**Think of recursion as a stack of tasks:**
- Each function call is a task placed on a stack
- Base case is when you start completing tasks (popping from stack)
- Each recursive call must make the problem smaller
- If no base case → infinite recursion → stack overflow!

**Visual Model - Factorial(4):**
```
factorial(4) = 4 * factorial(3)
    factorial(3) = 3 * factorial(2)
        factorial(2) = 2 * factorial(1)
            factorial(1) = 1           ← Base case! Start returning
        factorial(2) = 2 * 1 = 2      ← Return 2
    factorial(3) = 3 * 2 = 6          ← Return 6
factorial(4) = 4 * 6 = 24              ← Return 24
```

**Exam Recognition:**
- Problem can be broken into smaller identical problems → recursion
- Need to process data structure (tree, linked list) → recursion
- Base case + recursive case pattern
- Watch for: stack overflow, redundant calculations

## 💻 Key Operations with Java Implementations

### 1. Factorial

```java
public class FactorialExamples {

    /**
     * Basic factorial using recursion
     * n! = n * (n-1) * (n-2) * ... * 1
     * Time: O(n) - n recursive calls
     * Space: O(n) - call stack depth
     */
    public static int factorial(int n) {
        // Base case: stop recursion
        if (n <= 1) {
            return 1;
        }

        // Recursive case: break down problem
        return n * factorial(n - 1);

        // Trace for factorial(5):
        // factorial(5) = 5 * factorial(4)
        // factorial(4) = 4 * factorial(3)
        // factorial(3) = 3 * factorial(2)
        // factorial(2) = 2 * factorial(1)
        // factorial(1) = 1 (BASE CASE)
        // Returns: 1 → 2 → 6 → 24 → 120
    }

    /**
     * Factorial with input validation
     * Time: O(n)
     * Space: O(n)
     */
    public static long factorialSafe(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorialSafe(n - 1);
    }

    /**
     * Iterative factorial for comparison
     * Time: O(n)
     * Space: O(1) - no recursion stack!
     */
    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
```

### 2. Linear Sum

```java
public class LinearSumExamples {

    /**
     * Sum array elements using recursion
     * Time: O(n) - visits each element once
     * Space: O(n) - recursion depth
     */
    public static int linearSum(int[] arr, int n) {
        // Base case: no elements left
        if (n <= 0) {
            return 0;
        }

        // Recursive case: last element + sum of rest
        return arr[n - 1] + linearSum(arr, n - 1);

        // Example: arr = {2, 4, 6}, n = 3
        // linearSum(arr, 3) = 6 + linearSum(arr, 2)
        // linearSum(arr, 2) = 4 + linearSum(arr, 1)
        // linearSum(arr, 1) = 2 + linearSum(arr, 0)
        // linearSum(arr, 0) = 0 (BASE CASE)
        // Returns: 0 → 2 → 6 → 12
    }

    /**
     * Sum array using index parameter
     * Time: O(n)
     * Space: O(n)
     */
    public static int linearSumFromIndex(int[] arr, int index) {
        // Base case: reached end of array
        if (index >= arr.length) {
            return 0;
        }

        // Recursive case: current element + sum of rest
        return arr[index] + linearSumFromIndex(arr, index + 1);
    }

    /**
     * Sum array elements in a range
     * Time: O(high - low)
     * Space: O(high - low)
     */
    public static int linearSumRange(int[] arr, int low, int high) {
        if (low > high) {
            return 0;           // Base case: empty range
        }
        if (low == high) {
            return arr[low];    // Base case: single element
        }

        return arr[low] + linearSumRange(arr, low + 1, high);
    }
}
```

### 3. Reverse Array

```java
public class ReverseArrayExamples {

    /**
     * Reverse array in-place using recursion
     * Time: O(n/2) = O(n)
     * Space: O(n) - recursion depth
     */
    public static void reverseArray(int[] arr, int low, int high) {
        // Base case: pointers crossed or met
        if (low >= high) {
            return;
        }

        // Swap elements at low and high
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;

        // Recursive case: move toward middle
        reverseArray(arr, low + 1, high - 1);

        // Example: arr = {1, 2, 3, 4, 5}
        // reverseArray(arr, 0, 4): swap 1↔5 → {5, 2, 3, 4, 1}
        // reverseArray(arr, 1, 3): swap 2↔4 → {5, 4, 3, 2, 1}
        // reverseArray(arr, 2, 2): low == high → STOP
    }

    /**
     * Reverse array - wrapper method
     */
    public static void reverseArray(int[] arr) {
        reverseArray(arr, 0, arr.length - 1);
    }

    /**
     * Create reversed copy of array
     * Time: O(n)
     * Space: O(n) for new array + O(n) for recursion = O(n)
     */
    public static void reverseCopy(int[] source, int[] dest, int srcIndex, int destIndex) {
        if (srcIndex < 0) {
            return;                     // Base case
        }

        dest[destIndex] = source[srcIndex];
        reverseCopy(source, dest, srcIndex - 1, destIndex + 1);
    }
}
```

### 4. Binary Search (Recursive)

```java
public class BinarySearchExamples {

    /**
     * Binary search using recursion
     * REQUIRES: array must be sorted!
     * Time: O(log n) - cuts search space in half
     * Space: O(log n) - recursion depth
     */
    public static int binarySearch(int[] arr, int target, int low, int high) {
        // Base case 1: not found
        if (low > high) {
            return -1;
        }

        // Find middle index
        int mid = low + (high - low) / 2;

        // Base case 2: found target
        if (arr[mid] == target) {
            return mid;
        }

        // Recursive case: search left or right half
        if (arr[mid] > target) {
            // Target in left half
            return binarySearch(arr, target, low, mid - 1);
        } else {
            // Target in right half
            return binarySearch(arr, target, mid + 1, high);
        }

        // Example: arr = {1, 3, 5, 7, 9}, target = 7
        // binarySearch(arr, 7, 0, 4): mid=2, arr[2]=5 < 7, search right
        // binarySearch(arr, 7, 3, 4): mid=3, arr[3]=7 FOUND!
    }

    /**
     * Binary search - wrapper method
     */
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }

    /**
     * Find first occurrence of target in sorted array with duplicates
     * Time: O(log n)
     * Space: O(log n)
     */
    public static int findFirstOccurrence(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        // Found target, but check if it's the first occurrence
        if (arr[mid] == target) {
            if (mid == 0 || arr[mid - 1] != target) {
                return mid;     // This is the first occurrence
            } else {
                return findFirstOccurrence(arr, target, low, mid - 1);
            }
        } else if (arr[mid] > target) {
            return findFirstOccurrence(arr, target, low, mid - 1);
        } else {
            return findFirstOccurrence(arr, target, mid + 1, high);
        }
    }
}
```

### 5. Binary Sum

```java
public class BinarySumExamples {

    /**
     * Sum array by dividing in half (binary recursion)
     * Time: O(n) - visits each element once
     * Space: O(log n) - recursion depth is log n
     */
    public static int binarySum(int[] arr, int low, int high) {
        // Base case 1: empty range
        if (low > high) {
            return 0;
        }

        // Base case 2: single element
        if (low == high) {
            return arr[low];
        }

        // Recursive case: divide and conquer
        int mid = (low + high) / 2;
        return binarySum(arr, low, mid) + binarySum(arr, mid + 1, high);

        // Example: arr = {1, 2, 3, 4}, low=0, high=3
        //                    sum(0,3)
        //                   /        \
        //              sum(0,1)    sum(2,3)
        //              /    \      /    \
        //          sum(0,0) sum(1,1) sum(2,2) sum(3,3)
        //             1       2       3        4
        //          Returns: 3 + 7 = 10
    }

    /**
     * Binary sum - wrapper method
     */
    public static int binarySum(int[] arr) {
        return binarySum(arr, 0, arr.length - 1);
    }

    /**
     * Count elements in range using binary recursion
     * Time: O(log n) - doesn't visit all elements!
     * Space: O(log n)
     */
    public static int countElements(int low, int high) {
        if (low > high) {
            return 0;
        }
        if (low == high) {
            return 1;
        }

        int mid = (low + high) / 2;
        return countElements(low, mid) + countElements(mid + 1, high);
    }
}
```

### 6. Fibonacci

```java
public class FibonacciExamples {

    /**
     * Naive Fibonacci - VERY SLOW!
     * Fib(n) = Fib(n-1) + Fib(n-2)
     * Time: O(2^n) - exponential!
     * Space: O(n) - maximum recursion depth
     */
    public static int fibonacciNaive(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }

        // Recursive case: lots of repeated work!
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);

        // Problem: massive redundant calculations
        //              fib(5)
        //            /        \
        //        fib(4)      fib(3)      ← fib(3) calculated twice!
        //       /    \       /    \
        //   fib(3) fib(2) fib(2) fib(1)  ← fib(2) calculated 3 times!
    }

    /**
     * Fibonacci with memoization - FAST!
     * Time: O(n) - each value calculated once
     * Space: O(n) - memo array + recursion stack
     */
    public static int fibonacciMemo(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        // Check if already calculated
        if (memo[n] != -1) {
            return memo[n];
        }

        // Calculate and store
        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    }

    public static int fibonacciMemo(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);      // -1 means not calculated yet
        return fibonacciMemo(n, memo);
    }

    /**
     * Fibonacci iterative - BEST!
     * Time: O(n)
     * Space: O(1) - only two variables!
     */
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int prev = 0;       // fib(0)
        int curr = 1;       // fib(1)

        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;
    }

    /**
     * Linear Fibonacci using only addition (no arrays)
     * Time: O(2^n) - similar to naive
     * Space: O(n)
     */
    public static int linearFibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        // Two recursive calls make this exponential
        return linearFibonacci(n - 1) + linearFibonacci(n - 2);
    }
}
```

### 7. Advanced Recursion Examples

```java
public class AdvancedRecursion {

    /**
     * Power function: base^exponent
     * Time: O(n) - linear recursion
     * Space: O(n)
     */
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1.0 / power(base, -exponent);
        }

        return base * power(base, exponent - 1);
    }

    /**
     * Fast power using binary recursion
     * Time: O(log n) - much faster!
     * Space: O(log n)
     */
    public static double powerFast(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1.0 / powerFast(base, -exponent);
        }

        double half = powerFast(base, exponent / 2);

        if (exponent % 2 == 0) {
            return half * half;         // Even: x^8 = (x^4)^2
        } else {
            return base * half * half;  // Odd: x^9 = x * (x^4)^2
        }
    }

    /**
     * GCD (Greatest Common Divisor) - Euclidean algorithm
     * Time: O(log(min(a,b)))
     * Space: O(log(min(a,b)))
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;       // Base case
        }
        return gcd(b, a % b);

        // Example: gcd(48, 18)
        // gcd(48, 18) → gcd(18, 12) → gcd(12, 6) → gcd(6, 0) → 6
    }

    /**
     * Check if string is palindrome
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean isPalindrome(String str, int left, int right) {
        if (left >= right) {
            return true;    // Base case: all checked
        }

        if (str.charAt(left) != str.charAt(right)) {
            return false;   // Mismatch found
        }

        return isPalindrome(str, left + 1, right - 1);
    }

    public static boolean isPalindrome(String str) {
        return isPalindrome(str, 0, str.length() - 1);
    }

    /**
     * Convert decimal to binary string
     * Time: O(log n)
     * Space: O(log n)
     */
    public static String decimalToBinary(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }

        return decimalToBinary(n / 2) + (n % 2);

        // Example: decimalToBinary(13)
        // 13/2=6 r1 → decimalToBinary(6) + "1"
        //   6/2=3 r0 → decimalToBinary(3) + "0"
        //     3/2=1 r1 → decimalToBinary(1) + "1"
        //       1 → "1"
        // Returns: "1" + "1" + "0" + "1" = "1101"
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: No Base Case → Stack Overflow
```java
// ❌ INFINITE RECURSION - No base case!
public static int badRecursion(int n) {
    return n + badRecursion(n - 1);
    // Will crash with StackOverflowError
}

// ✅ CORRECT - Always have base case
public static int goodRecursion(int n) {
    if (n <= 0) {           // Base case!
        return 0;
    }
    return n + goodRecursion(n - 1);
}
```

### Pitfall 2: Not Moving Toward Base Case
```java
// ❌ WRONG - Never reaches base case!
public static int badCountdown(int n) {
    if (n == 0) return 0;
    return n + badCountdown(n + 1);  // Going AWAY from base case!
}

// ✅ CORRECT - Move toward base case
public static int goodCountdown(int n) {
    if (n == 0) return 0;
    return n + goodCountdown(n - 1);  // Going TOWARD base case
}
```

### Pitfall 3: Inefficient Fibonacci
```java
// ❌ VERY SLOW - O(2^n) time
int result = fibonacciNaive(40);  // Takes several seconds!

// ✅ FAST - O(n) time with memoization
int result = fibonacciMemo(40);   // Instant!

// ✅ FASTEST - O(n) time, O(1) space
int result = fibonacciIterative(40);  // Instant and minimal memory!
```

### Pitfall 4: Forgetting Edge Cases
```java
// ❌ Doesn't handle empty array
public static int sum(int[] arr, int n) {
    if (n == 0) return 0;
    return arr[n-1] + sum(arr, n-1);  // Crashes if arr is null!
}

// ✅ Handle edge cases
public static int sum(int[] arr, int n) {
    if (arr == null || n <= 0) {
        return 0;
    }
    return arr[n-1] + sum(arr, n-1);
}
```

## ⏱️ Time & Space Complexity

### Common Recursive Patterns

| Pattern | Time | Space | Example |
|---------|------|-------|---------|
| Linear recursion (one call) | O(n) | O(n) | Factorial, linear sum |
| Binary recursion (two calls) | O(2^n) | O(n) | Naive Fibonacci |
| Binary recursion (divide & conquer) | O(n) | O(log n) | Binary sum |
| Logarithmic recursion | O(log n) | O(log n) | Binary search, GCD |
| Multiple recursion | O(branches^depth) | O(depth) | Tree traversal |

### Recursion vs Iteration

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| Space | O(n) call stack | O(1) typically |
| Readability | Often clearer | Can be complex |
| Performance | Overhead of calls | Faster (no calls) |
| Stack overflow | Possible | No |
| Use when | Tree/graph, divide & conquer | Simple loops, space critical |

## 🧪 Complete Test Cases

```java
public class RecursionTest {

    public static void main(String[] args) {
        testFactorial();
        testLinearSum();
        testReverseArray();
        testBinarySearch();
        testBinarySum();
        testFibonacci();
        testAdvancedRecursion();
        testEdgeCases();
        System.out.println("✅ All recursion tests passed!");
    }

    static void testFactorial() {
        assert FactorialExamples.factorial(0) == 1;
        assert FactorialExamples.factorial(1) == 1;
        assert FactorialExamples.factorial(5) == 120;
        assert FactorialExamples.factorial(10) == 3628800;

        // Compare with iterative
        assert FactorialExamples.factorialIterative(5) == FactorialExamples.factorial(5);
    }

    static void testLinearSum() {
        int[] arr = {1, 2, 3, 4, 5};

        assert LinearSumExamples.linearSum(arr, 5) == 15;
        assert LinearSumExamples.linearSum(arr, 3) == 6;
        assert LinearSumExamples.linearSumFromIndex(arr, 0) == 15;
        assert LinearSumExamples.linearSumRange(arr, 1, 3) == 9; // 2+3+4
    }

    static void testReverseArray() {
        int[] arr1 = {1, 2, 3, 4, 5};
        ReverseArrayExamples.reverseArray(arr1);
        assert Arrays.equals(arr1, new int[]{5, 4, 3, 2, 1});

        int[] arr2 = {10, 20};
        ReverseArrayExamples.reverseArray(arr2);
        assert Arrays.equals(arr2, new int[]{20, 10});
    }

    static void testBinarySearch() {
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};

        assert BinarySearchExamples.binarySearch(sorted, 7) == 3;
        assert BinarySearchExamples.binarySearch(sorted, 1) == 0;
        assert BinarySearchExamples.binarySearch(sorted, 15) == 7;
        assert BinarySearchExamples.binarySearch(sorted, 6) == -1;
        assert BinarySearchExamples.binarySearch(sorted, 100) == -1;

        // Test with duplicates
        int[] withDups = {1, 2, 2, 2, 3, 4};
        assert BinarySearchExamples.findFirstOccurrence(withDups, 2, 0, 5) == 1;
    }

    static void testBinarySum() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        assert BinarySumExamples.binarySum(arr) == 36;
        assert BinarySumExamples.binarySum(arr, 0, 3) == 10; // 1+2+3+4
        assert BinarySumExamples.countElements(0, 7) == 8;
    }

    static void testFibonacci() {
        // Test first 10 Fibonacci numbers: 0,1,1,2,3,5,8,13,21,34
        int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

        for (int i = 0; i < expected.length; i++) {
            assert FibonacciExamples.fibonacciNaive(i) == expected[i];
            assert FibonacciExamples.fibonacciMemo(i) == expected[i];
            assert FibonacciExamples.fibonacciIterative(i) == expected[i];
        }

        // Test larger value (only with optimized versions)
        assert FibonacciExamples.fibonacciMemo(20) == 6765;
        assert FibonacciExamples.fibonacciIterative(20) == 6765;
    }

    static void testAdvancedRecursion() {
        // Power
        assert AdvancedRecursion.power(2, 5) == 32.0;
        assert AdvancedRecursion.powerFast(2, 10) == 1024.0;

        // GCD
        assert AdvancedRecursion.gcd(48, 18) == 6;
        assert AdvancedRecursion.gcd(100, 35) == 5;

        // Palindrome
        assert AdvancedRecursion.isPalindrome("racecar");
        assert AdvancedRecursion.isPalindrome("a");
        assert !AdvancedRecursion.isPalindrome("hello");

        // Decimal to binary
        assert AdvancedRecursion.decimalToBinary(13).equals("1101");
        assert AdvancedRecursion.decimalToBinary(8).equals("1000");
    }

    static void testEdgeCases() {
        // Empty array
        int[] empty = {};
        assert LinearSumExamples.linearSum(empty, 0) == 0;

        // Single element
        int[] single = {42};
        assert LinearSumExamples.linearSum(single, 1) == 42;

        // Null handling
        assert LinearSumExamples.linearSum(null, 0) == 0;

        // Zero cases
        assert FactorialExamples.factorial(0) == 1;
        assert FibonacciExamples.fibonacciNaive(0) == 0;
    }
}
```

## 🎯 Expected Test Output
```
✅ All recursion tests passed!
```

---

## 📚 Summary for Exams

**Recursion Essentials:**
1. ✅ **Base case:** Condition to stop recursion (REQUIRED!)
2. ✅ **Recursive case:** Break problem into smaller version
3. ✅ **Progress:** Each call must move toward base case
4. ✅ **Trust the recursion:** Assume recursive call works correctly

**Common Recursive Functions:**
- **Factorial:** O(n) time, O(n) space
- **Linear sum:** O(n) time, O(n) space
- **Binary search:** O(log n) time, O(log n) space
- **Binary sum:** O(n) time, O(log n) space
- **Fibonacci (naive):** O(2^n) time - TOO SLOW!
- **Fibonacci (memo):** O(n) time, O(n) space

**When to Use Recursion:**
- Tree/graph traversal
- Divide and conquer algorithms
- Backtracking problems
- Problems with recursive structure (factorial, Fibonacci)

**When to Avoid Recursion:**
- Simple iteration works better
- Space is critical (recursion uses stack)
- Problem has overlapping subproblems (use DP instead)
- Very deep recursion (risk stack overflow)

**Common Exam Mistakes:**
- Missing base case → infinite recursion
- Not moving toward base case
- Using naive Fibonacci (too slow!)
- Forgetting space complexity of call stack
- Off-by-one errors in indices

**Optimization Tricks:**
- **Memoization:** Cache results to avoid recomputation
- **Tail recursion:** Can be optimized by compiler
- **Convert to iteration:** Often faster and uses less space
