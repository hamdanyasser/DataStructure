# Topic 2: Arrays & ArrayLists

## 📖 Concept Summary
Arrays are fixed-size, contiguous memory blocks storing elements of the same type with O(1) random access via index. ArrayLists are dynamic arrays (resizable) built on top of arrays, automatically growing when capacity is exceeded. Both provide efficient index-based access but differ in mutability and memory management.

## 🧠 Intuition & Mental Model

**Think of arrays as numbered parking spaces:**
- Fixed number of spots (size cannot change)
- Each spot has an address (index 0, 1, 2, ...)
- You can instantly jump to any spot (O(1) access)
- But you can't add more spots without building a new parking lot

**ArrayList is a self-expanding parking lot:**
- Starts with initial capacity (default 10)
- When full, builds a bigger lot (usually 1.5x size) and moves all cars
- Expensive to expand, but happens rarely (amortized O(1))

**Exam Recognition:**
- Need fixed size + primitives → array
- Need dynamic size + objects → ArrayList
- Need to add/remove from end frequently → ArrayList
- Need to add/remove from middle → consider LinkedList

## 💻 Key Operations with Java Implementations

### 1. Array Declaration & Initialization
```java
public class ArrayBasics {

    public static void main(String[] args) {
        // Method 1: Declare then initialize
        int[] arr1;
        arr1 = new int[5]; // Default values: 0 for int

        // Method 2: Declare and initialize together
        int[] arr2 = new int[5];

        // Method 3: Initialize with values
        int[] arr3 = {1, 2, 3, 4, 5};

        // Method 4: Using new with values
        int[] arr4 = new int[]{1, 2, 3, 4, 5};

        // Reference types (default null)
        String[] strings = new String[3]; // [null, null, null]

        // Multi-dimensional arrays
        int[][] matrix = new int[3][4]; // 3 rows, 4 columns
        int[][] jaggedArray = {{1,2}, {3,4,5}, {6}}; // Jagged array
    }
}
```

### 2. Array Access & Modification
```java
public class ArrayOperations {

    /**
     * Safe array access with bounds checking
     * Time: O(1), Space: O(1)
     */
    public static int safeGet(int[] arr, int index) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
        }
        return arr[index];
    }

    /**
     * Find maximum element
     * Time: O(n), Space: O(1)
     */
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Find minimum element
     * Time: O(n), Space: O(1)
     */
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * Reverse array in-place
     * Time: O(n), Space: O(1)
     */
    public static void reverse(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    /**
     * Rotate array to the right by k positions
     * Example: [1,2,3,4,5], k=2 → [4,5,1,2,3]
     * Time: O(n), Space: O(1)
     */
    public static void rotateRight(int[] arr, int k) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Handle k > arr.length
        k = k % arr.length;

        // Reverse whole array
        reverse(arr, 0, arr.length - 1);
        // Reverse first k elements
        reverse(arr, 0, k - 1);
        // Reverse remaining elements
        reverse(arr, k, arr.length - 1);
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
}
```

### 3. Array Searching
```java
public class ArraySearching {

    /**
     * Linear search (unsorted array)
     * Time: O(n), Space: O(1)
     */
    public static int linearSearch(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index
            }
        }
        return -1; // Not found
    }

    /**
     * Binary search (sorted array only!)
     * Time: O(log n), Space: O(1)
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow!

            if (arr[mid] == target) {
                return mid; // Found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Not found
    }

    /**
     * Binary search recursive version
     * Time: O(log n), Space: O(log n) due to call stack
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
}
```

### 4. Array Manipulation Algorithms
```java
public class ArrayManipulation {

    /**
     * Remove duplicates from SORTED array in-place
     * Returns new length
     * Time: O(n), Space: O(1)
     */
    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int writeIndex = 1; // Position to write next unique element

        for (int readIndex = 1; readIndex < arr.length; readIndex++) {
            if (arr[readIndex] != arr[readIndex - 1]) {
                arr[writeIndex] = arr[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // New length
    }

    /**
     * Merge two sorted arrays into first array
     * arr1 has enough space to hold all elements
     * Time: O(m + n), Space: O(1)
     */
    public static void mergeSortedArrays(int[] arr1, int m, int[] arr2, int n) {
        int i = m - 1; // Last element of arr1
        int j = n - 1; // Last element of arr2
        int k = m + n - 1; // Last position in merged array

        // Merge from back to front
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }

        // Copy remaining from arr2 (if any)
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }
        // No need to copy from arr1, already in place
    }

    /**
     * Move all zeros to end while maintaining relative order
     * Time: O(n), Space: O(1)
     */
    public static void moveZerosToEnd(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int writeIndex = 0; // Position for next non-zero

        // Move all non-zeros to front
        for (int readIndex = 0; readIndex < arr.length; readIndex++) {
            if (arr[readIndex] != 0) {
                arr[writeIndex++] = arr[readIndex];
            }
        }

        // Fill rest with zeros
        while (writeIndex < arr.length) {
            arr[writeIndex++] = 0;
        }
    }

    /**
     * Two sum problem: find two indices that sum to target
     * Assumes there is exactly one solution
     * Time: O(n), Space: O(n)
     */
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // value → index

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }

        return new int[]{-1, -1}; // Not found
    }
}
```

### 5. ArrayList Operations
```java
import java.util.*;

public class ArrayListExamples {

    public static void main(String[] args) {
        // Creation
        ArrayList<String> list = new ArrayList<>(); // Initial capacity 10
        ArrayList<String> listWithCapacity = new ArrayList<>(100); // Custom capacity
        ArrayList<String> listFromCollection = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // Add elements - O(1) amortized
        list.add("apple");
        list.add("banana");
        list.add(0, "cherry"); // Insert at index - O(n)

        // Access - O(1)
        String first = list.get(0);

        // Modify - O(1)
        list.set(1, "blueberry");

        // Remove - O(n)
        list.remove(0); // Remove by index
        list.remove("banana"); // Remove by value

        // Search - O(n)
        boolean contains = list.contains("apple");
        int index = list.indexOf("apple");

        // Size
        int size = list.size();
        boolean isEmpty = list.isEmpty();

        // Iteration
        for (String fruit : list) {
            System.out.println(fruit);
        }

        // Convert to array
        String[] array = list.toArray(new String[0]);

        // Clear all
        list.clear();
    }

    /**
     * Custom resizable array implementation (simplified ArrayList)
     */
    static class SimpleArrayList<E> {
        private Object[] data;
        private int size;
        private static final int DEFAULT_CAPACITY = 10;

        public SimpleArrayList() {
            data = new Object[DEFAULT_CAPACITY];
            size = 0;
        }

        public void add(E element) {
            ensureCapacity();
            data[size++] = element;
        }

        public E get(int index) {
            checkIndex(index);
            return (E) data[index];
        }

        public void set(int index, E element) {
            checkIndex(index);
            data[index] = element;
        }

        public int size() {
            return size;
        }

        private void ensureCapacity() {
            if (size == data.length) {
                // Grow by 1.5x
                int newCapacity = data.length + (data.length >> 1);
                data = Arrays.copyOf(data, newCapacity);
            }
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
        }
    }
}
```

### 6. 2D Arrays (Matrices)
```java
public class MatrixOperations {

    /**
     * Print matrix in spiral order
     * Time: O(m*n), Space: O(1) not counting output
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // Traverse right column
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // Traverse bottom row (if exists)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // Traverse left column (if exists)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    /**
     * Rotate matrix 90 degrees clockwise in-place
     * Time: O(n²), Space: O(1)
     */
    public static void rotateMatrix90(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: Off-by-One Errors
```java
// ❌ WRONG: goes out of bounds
for (int i = 0; i <= arr.length; i++) { // Should be i < arr.length
    System.out.println(arr[i]);
}

// ✅ CORRECT
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

### Pitfall 2: Integer Overflow in Mid Calculation
```java
// ❌ WRONG: can overflow for large indices
int mid = (left + right) / 2;

// ✅ CORRECT
int mid = left + (right - left) / 2;
```

### Pitfall 3: Null and Empty Array
```java
// Always check both!
if (arr == null || arr.length == 0) {
    // Handle edge case
}
```

### Pitfall 4: Array vs ArrayList Confusion
```java
int[] arr = {1, 2, 3};
// ❌ arr.length() - arrays use .length (field, not method!)
// ✅ arr.length

ArrayList<Integer> list = new ArrayList<>();
// ❌ list.length - ArrayList uses .size() method!
// ✅ list.size()
```

## ⏱️ Time & Space Complexity

### Array Operations
| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Access by index | O(1) | O(1) | Direct memory address calculation |
| Search (unsorted) | O(n) | O(1) | Must check all elements |
| Search (sorted, binary) | O(log n) | O(1) | Divide and conquer |
| Insert at end | N/A | N/A | Arrays are fixed size |
| Insert at middle | N/A | N/A | Arrays are fixed size |
| Delete | N/A | N/A | Arrays are fixed size |

### ArrayList Operations
| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| get(i) | O(1) | O(1) | Index access |
| set(i, val) | O(1) | O(1) | Index access |
| add(val) | O(1) amortized | O(1) | Resize happens rarely |
| add(i, val) | O(n) | O(1) | Shift elements |
| remove(i) | O(n) | O(1) | Shift elements |
| contains(val) | O(n) | O(1) | Linear search |

**Best Case:** All O(1) operations when accessing by index
**Average Case:** Search O(n), Binary search O(log n) if sorted
**Worst Case:** ArrayList resize O(n) but amortized O(1)

## 🧪 Complete Test Cases

```java
import java.util.*;

public class ArrayTest {
    public static void main(String[] args) {
        testBasicOperations();
        testSearching();
        testManipulation();
        testEdgeCases();
        test2DArrays();
        System.out.println("✅ All array tests passed!");
    }

    static void testBasicOperations() {
        int[] arr = {5, 2, 8, 1, 9};

        // Test max/min
        assert ArrayOperations.findMax(arr) == 9;
        assert ArrayOperations.findMin(arr) == 1;

        // Test reverse
        ArrayOperations.reverse(arr);
        assert Arrays.equals(arr, new int[]{9, 1, 8, 2, 5});

        // Test rotate
        int[] arr2 = {1, 2, 3, 4, 5};
        ArrayOperations.rotateRight(arr2, 2);
        assert Arrays.equals(arr2, new int[]{4, 5, 1, 2, 3});
    }

    static void testSearching() {
        int[] arr = {2, 5, 7, 10, 15};

        // Linear search
        assert ArraySearching.linearSearch(arr, 7) == 2;
        assert ArraySearching.linearSearch(arr, 99) == -1;

        // Binary search
        assert ArraySearching.binarySearch(arr, 10) == 3;
        assert ArraySearching.binarySearch(arr, 3) == -1;
    }

    static void testManipulation() {
        // Remove duplicates
        int[] arr1 = {1, 1, 2, 2, 3, 4, 4};
        int newLen = ArrayManipulation.removeDuplicates(arr1);
        assert newLen == 4;
        assert arr1[0] == 1 && arr1[1] == 2 && arr1[2] == 3 && arr1[3] == 4;

        // Move zeros
        int[] arr2 = {0, 1, 0, 3, 12};
        ArrayManipulation.moveZerosToEnd(arr2);
        assert Arrays.equals(arr2, new int[]{1, 3, 12, 0, 0});

        // Two sum
        int[] arr3 = {2, 7, 11, 15};
        int[] result = ArrayManipulation.twoSum(arr3, 9);
        assert result[0] == 0 && result[1] == 1;
    }

    static void testEdgeCases() {
        // Null array
        assert ArraySearching.linearSearch(null, 5) == -1;

        // Empty array
        int[] empty = {};
        ArrayOperations.reverse(empty); // Should not crash

        // Single element
        int[] single = {42};
        assert ArrayOperations.findMax(single) == 42;
        ArrayOperations.reverse(single);
        assert single[0] == 42;
    }

    static void test2DArrays() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Spiral order
        List<Integer> spiral = MatrixOperations.spiralOrder(matrix);
        List<Integer> expected = Arrays.asList(1,2,3,6,9,8,7,4,5);
        assert spiral.equals(expected);

        // Rotate 90
        int[][] toRotate = {{1,2}, {3,4}};
        MatrixOperations.rotateMatrix90(toRotate);
        assert toRotate[0][0] == 3 && toRotate[0][1] == 1;
        assert toRotate[1][0] == 4 && toRotate[1][1] == 2;
    }
}
```

---

## 📚 Summary for Exams

**Arrays - Must Know:**
- Fixed size, O(1) access
- .length is a field (not method!)
- Cannot resize, must create new array
- Primitive or object types

**ArrayList - Must Know:**
- Dynamic size, backed by array
- .size() is a method
- add() is O(1) amortized (resize cost spread out)
- add(i, val) and remove(i) are O(n)

**Common Exam Patterns:**
1. Two pointers (reverse, remove duplicates)
2. Binary search (sorted arrays)
3. Sliding window
4. Prefix sum
5. Matrix traversal (spiral, rotate)

**Edge Cases to Always Test:**
- null, empty, single element
- All same values
- Already sorted/reversed
- Negative numbers, zero
- Duplicate values
