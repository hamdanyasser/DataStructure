# Topic 4: Complexity Analysis

## 📖 Concept Summary
Complexity analysis is the study of algorithm efficiency in terms of time (operations) and space (memory). Big-O notation describes the worst-case growth rate of resources needed as input size increases. This allows us to compare algorithms independently of hardware or implementation details.

## 🧠 Intuition & Mental Model

**Think of complexity as scalability:**
- O(1): Constant - like looking up a phone number by speed dial
- O(log n): Logarithmic - like finding a word in a dictionary (binary search)
- O(n): Linear - like reading every page in a book
- O(n log n): Linearithmic - like efficient sorting (merge sort)
- O(n²): Quadratic - like comparing every person with every other person
- O(2ⁿ): Exponential - like trying all possible combinations

**Exam Recognition:**
- Single loop → O(n)
- Nested loops → O(n²) or O(n*m)
- Dividing problem in half → O(log n)
- Recursion with two calls → Often O(2ⁿ)
- Drop constants and lower order terms: O(3n + 5) → O(n)

## 💻 Key Operations with Java Implementations

### 1. Linear Complexity O(n)

```java
public class LinearComplexity {

    /**
     * Sum all elements in array
     * Time: O(n) - visits each element once
     * Space: O(1) - uses constant extra space
     */
    public static int linearSum(int[] arr) {
        int sum = 0;                    // O(1)
        for (int i = 0; i < arr.length; i++) {  // Loops n times
            sum += arr[i];              // O(1) per iteration
        }
        return sum;                     // O(1)
        // Total: O(1) + O(n) + O(1) = O(n)
    }

    /**
     * Find maximum element
     * Time: O(n) - must check all elements
     * Space: O(1)
     */
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int max = arr[0];               // O(1)
        for (int i = 1; i < arr.length; i++) {  // O(n)
            if (arr[i] > max) {
                max = arr[i];           // O(1)
            }
        }
        return max;
        // Total: O(n)
    }

    /**
     * Two separate loops - still O(n)
     * Time: O(n + n) = O(2n) = O(n)
     * Space: O(1)
     */
    public static int sumAndMax(int[] arr) {
        int sum = 0;
        for (int num : arr) {           // First loop: O(n)
            sum += num;
        }

        int max = arr[0];
        for (int num : arr) {           // Second loop: O(n)
            if (num > max) {
                max = num;
            }
        }

        return sum + max;
        // Total: O(n) + O(n) = O(2n) = O(n)
    }
}
```

### 2. Logarithmic Complexity O(log n)

```java
public class LogarithmicComplexity {

    /**
     * Binary search in sorted array
     * Time: O(log n) - cuts search space in half each iteration
     * Space: O(1) - iterative version
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        // Each iteration cuts problem size in half
        while (left <= right) {         // O(log n) iterations
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;         // Discard left half
            } else {
                right = mid - 1;        // Discard right half
            }
        }
        return -1;
        // Each iteration: n → n/2 → n/4 → n/8 → ... → 1
        // Number of iterations: log₂(n)
    }

    /**
     * Find power using repeated squaring
     * Time: O(log n)
     * Space: O(1)
     */
    public static long power(int base, int exponent) {
        if (exponent == 0) return 1;

        long result = 1;
        long currentBase = base;
        int currentExp = exponent;

        while (currentExp > 0) {        // O(log n) iterations
            if (currentExp % 2 == 1) {
                result *= currentBase;
            }
            currentBase *= currentBase; // Square the base
            currentExp /= 2;            // Halve the exponent
        }

        return result;
        // Example: 2^10 → 2^5 → 2^2 → 2^1 → 2^0
        // Steps: log₂(10) ≈ 3.32 ≈ 4 iterations
    }
}
```

### 3. Quadratic Complexity O(n²)

```java
public class QuadraticComplexity {

    /**
     * Bubble sort - nested loops
     * Time: O(n²) - worst and average case
     * Space: O(1) - sorts in place
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {           // Outer loop: n times
            for (int j = 0; j < n - i - 1; j++) {   // Inner loop: n times
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        // Iterations: n * n = n²
    }

    /**
     * Print all pairs in array
     * Time: O(n²) - two nested loops
     * Space: O(1)
     */
    public static void printAllPairs(int[] arr) {
        for (int i = 0; i < arr.length; i++) {      // O(n)
            for (int j = 0; j < arr.length; j++) {  // O(n) for each i
                System.out.println("(" + arr[i] + ", " + arr[j] + ")");
            }
        }
        // Total pairs: n * n = n²
    }

    /**
     * Selection sort
     * Time: O(n²) - even in best case
     * Space: O(1)
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  // O(n)
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {  // O(n)
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        // Total: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 = O(n²)
    }
}
```

### 4. Exponential Complexity O(2ⁿ)

```java
public class ExponentialComplexity {

    /**
     * Naive Fibonacci - exponential time
     * Time: O(2ⁿ) - each call makes 2 more calls
     * Space: O(n) - maximum recursion depth
     */
    public static int fibonacciNaive(int n) {
        if (n <= 1) {
            return n;                   // Base case
        }
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);

        // Recursion tree grows exponentially:
        //           fib(5)
        //         /        \
        //     fib(4)      fib(3)
        //     /    \      /    \
        // fib(3) fib(2) fib(2) fib(1)
        // ... exponential growth!
        // Number of calls ≈ 2^n
    }

    /**
     * Generate all subsets of a set
     * Time: O(2ⁿ) - 2 choices for each element
     * Space: O(n) for recursion depth
     */
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsetsHelper(int[] nums, int index,
                                             List<Integer> current,
                                             List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Exclude current element
        generateSubsetsHelper(nums, index + 1, current, result);

        // Include current element
        current.add(nums[index]);
        generateSubsetsHelper(nums, index + 1, current, result);
        current.remove(current.size() - 1);

        // For n elements: 2^n subsets
    }
}
```

### 5. Linearithmic Complexity O(n log n)

```java
public class LinearithmicComplexity {

    /**
     * Merge sort - divide and conquer
     * Time: O(n log n) - always
     * Space: O(n) - temporary arrays
     */
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);                // T(n/2)
        mergeSort(right);               // T(n/2)
        merge(arr, left, right);        // O(n)

        // Recurrence: T(n) = 2T(n/2) + O(n)
        // Solution: T(n) = O(n log n)
        // Tree height: log n
        // Work per level: n
        // Total: n * log n
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
```

### 6. Analyzing Recursion

```java
public class RecursionAnalysis {

    /**
     * Factorial - linear recursion
     * Time: O(n) - n recursive calls
     * Space: O(n) - call stack depth
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;                   // Base case: O(1)
        }
        return n * factorial(n - 1);    // Recursive case: O(n)

        // Call stack:
        // factorial(5) → factorial(4) → factorial(3) → ... → factorial(1)
        // Depth: n, so space = O(n)
    }

    /**
     * Binary search recursive
     * Time: O(log n) - cuts in half each call
     * Space: O(log n) - recursion depth
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;                  // Base case
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }

        // Recurrence: T(n) = T(n/2) + O(1)
        // Solution: T(n) = O(log n)
    }

    /**
     * Binary sum - divide array in half recursively
     * Time: O(n) - visits each element once
     * Space: O(log n) - recursion depth
     */
    public static int binarySum(int[] arr, int low, int high) {
        if (low > high) {
            return 0;                   // Base case
        } else if (low == high) {
            return arr[low];            // Base case: single element
        }

        int mid = (low + high) / 2;
        return binarySum(arr, low, mid) + binarySum(arr, mid + 1, high);

        // Recurrence: T(n) = 2T(n/2) + O(1)
        // Solution: T(n) = O(n)
        // Despite log n depth, we visit all n elements
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: Confusing Loops with Complexity
```java
// This is O(n), NOT O(log n)!
// Even though loop variable doubles
for (int i = 1; i <= n; i *= 2) {
    for (int j = 0; j < n; j++) {   // Inner loop runs n times!
        // Do something
    }
}
// Outer loop: O(log n) iterations
// Inner loop: O(n) per iteration
// Total: O(n log n)
```

### Pitfall 2: Ignoring Hidden Complexity
```java
// This looks like O(n) but is actually O(n²)!
for (int i = 0; i < arr.length; i++) {
    String str = "";
    for (int j = 0; j < i; j++) {
        str += "a";                 // String concatenation is O(j)!
    }
}
// String += creates new string each time: O(1 + 2 + 3 + ... + n) = O(n²)
// Use StringBuilder instead!
```

### Pitfall 3: Best vs Average vs Worst Case
```java
// Binary search: O(log n) average and worst case
// But O(1) best case if element is at middle!

// Quick sort: O(n log n) average case
//            O(n²) worst case (already sorted with bad pivot)

// Always analyze worst case for Big-O unless stated otherwise
```

### Pitfall 4: Space Complexity of Recursion
```java
// Iterative factorial: O(1) space
public static int factorialIterative(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

// Recursive factorial: O(n) space due to call stack!
public static int factorialRecursive(int n) {
    if (n <= 1) return 1;
    return n * factorialRecursive(n - 1);
}
// Each call adds to call stack!
```

## ⏱️ Time & Space Complexity Reference

### Common Complexity Classes (from best to worst)
| Notation | Name | Example | n=10 | n=100 | n=1000 |
|----------|------|---------|------|-------|--------|
| O(1) | Constant | Array access | 1 | 1 | 1 |
| O(log n) | Logarithmic | Binary search | 3 | 7 | 10 |
| O(n) | Linear | Linear search | 10 | 100 | 1000 |
| O(n log n) | Linearithmic | Merge sort | 30 | 664 | 9966 |
| O(n²) | Quadratic | Bubble sort | 100 | 10,000 | 1,000,000 |
| O(2ⁿ) | Exponential | Fibonacci naive | 1024 | ~10³⁰ | ~10³⁰¹ |
| O(n!) | Factorial | Permutations | 3,628,800 | Impossible | Impossible |

### Rules for Big-O Analysis
1. **Drop constants:** O(3n) → O(n)
2. **Drop lower terms:** O(n² + n) → O(n²)
3. **Different inputs, different variables:** O(a + b) or O(a * b)
4. **Nested loops multiply:** Two nested loops = O(n²)
5. **Sequential operations add:** Two separate loops = O(n + n) = O(n)

## 🧪 Complete Test Cases

```java
public class ComplexityTest {

    public static void main(String[] args) {
        testLinearComplexity();
        testLogarithmicComplexity();
        testQuadraticComplexity();
        testExponentialComplexity();
        testRecursionComplexity();
        System.out.println("✅ All complexity tests passed!");
    }

    static void testLinearComplexity() {
        int[] arr = {1, 2, 3, 4, 5};

        // Linear sum: O(n)
        assert LinearComplexity.linearSum(arr) == 15;

        // Find max: O(n)
        assert LinearComplexity.findMax(arr) == 5;

        // Sum and max: O(n)
        assert LinearComplexity.sumAndMax(arr) == 20; // 15 + 5
    }

    static void testLogarithmicComplexity() {
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};

        // Binary search: O(log n)
        assert LogarithmicComplexity.binarySearch(sorted, 7) == 3;
        assert LogarithmicComplexity.binarySearch(sorted, 2) == -1;

        // Power: O(log n)
        assert LogarithmicComplexity.power(2, 10) == 1024;
        assert LogarithmicComplexity.power(3, 5) == 243;
    }

    static void testQuadraticComplexity() {
        int[] arr1 = {5, 2, 8, 1, 9};

        // Bubble sort: O(n²)
        QuadraticComplexity.bubbleSort(arr1);
        assert Arrays.equals(arr1, new int[]{1, 2, 5, 8, 9});

        // Selection sort: O(n²)
        int[] arr2 = {64, 25, 12, 22, 11};
        QuadraticComplexity.selectionSort(arr2);
        assert Arrays.equals(arr2, new int[]{11, 12, 22, 25, 64});
    }

    static void testExponentialComplexity() {
        // Fibonacci: O(2^n) - only test small values!
        assert ExponentialComplexity.fibonacciNaive(0) == 0;
        assert ExponentialComplexity.fibonacciNaive(1) == 1;
        assert ExponentialComplexity.fibonacciNaive(5) == 5;
        assert ExponentialComplexity.fibonacciNaive(7) == 13;
        // Don't test large values - too slow!

        // Generate subsets: O(2^n)
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = ExponentialComplexity.generateSubsets(nums);
        assert subsets.size() == 8; // 2^3 = 8 subsets
    }

    static void testRecursionComplexity() {
        // Factorial: O(n) time, O(n) space
        assert RecursionAnalysis.factorial(5) == 120;
        assert RecursionAnalysis.factorial(0) == 1;

        // Binary search recursive: O(log n) time, O(log n) space
        int[] arr = {1, 3, 5, 7, 9};
        assert RecursionAnalysis.binarySearchRecursive(arr, 5, 0, 4) == 2;

        // Binary sum: O(n) time, O(log n) space
        int[] sumArr = {1, 2, 3, 4, 5};
        assert RecursionAnalysis.binarySum(sumArr, 0, 4) == 15;
    }
}
```

## 🎯 Expected Test Output
```
✅ All complexity tests passed!
```

---

## 📚 Summary for Exams

**Must Know Complexities:**
1. ✅ O(1) - Constant: array access, hash table lookup
2. ✅ O(log n) - Logarithmic: binary search, balanced tree operations
3. ✅ O(n) - Linear: single loop, linear search
4. ✅ O(n log n) - Linearithmic: efficient sorting (merge, heap, quick sort average)
5. ✅ O(n²) - Quadratic: nested loops, bubble/selection/insertion sort
6. ✅ O(2ⁿ) - Exponential: recursive fibonacci, generate all subsets

**Analysis Techniques:**
- **Loops:** Count iterations, nested loops multiply
- **Recursion:** Draw recursion tree, identify branching factor and depth
- **Master Theorem:** For divide-and-conquer recurrences
- **Amortized Analysis:** Average cost over sequence of operations

**Common Exam Mistakes:**
- Forgetting to drop constants and lower-order terms
- Confusing best/average/worst case
- Missing hidden complexity (string concatenation, list operations)
- Ignoring space complexity of recursion (call stack!)

**Quick Recognition:**
- Halving problem size → O(log n)
- Single loop → O(n)
- Two nested loops → O(n²)
- Divide and conquer → Usually O(n log n)
- Multiple recursive calls → Often exponential
