# 📝 Midterm Exam #1 (Medium Difficulty)
**Course:** Data Structures and Algorithms
**Time Limit:** 60 minutes
**Total Points:** 100

---

## 📋 Instructions

1. **Read all questions first** before starting (2 minutes recommended)
2. **Show your work** for partial credit
3. **Write clean, compilable code** with proper syntax
4. **Analyze time/space complexity** where asked
5. **Test with edge cases** when appropriate
6. **No use of built-in sort methods** unless explicitly allowed

---

## Question 1: Complexity Analysis (15 points)

### Part A (5 points)
Analyze the time complexity of the following code. Show your work.

```java
public void mystery1(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            System.out.println(i + j);
        }
    }
}
```

**Answer:** ____________

**Justification:**


---

### Part B (5 points)
Analyze the time complexity. Show your work.

```java
public void mystery2(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            System.out.println(i + j);
        }
    }
}
```

**Answer:** ____________

**Justification:**


---

### Part C (5 points)
Analyze the time complexity. Show your work.

```java
public int mystery3(int n) {
    if (n <= 1) return 1;
    return mystery3(n / 2) + mystery3(n / 2);
}
```

**Answer:** ____________

**Justification:**


---

## Question 2: Arrays (20 points)

### Part A: Implementation (15 points)
Write a method that rotates an array to the right by k positions **in-place** (without using extra array).

**Example:**
```
Input: arr = [1, 2, 3, 4, 5], k = 2
Output: [4, 5, 1, 2, 3]
```

**Requirements:**
- Handle k > arr.length
- O(n) time, O(1) space
- Modify the original array

```java
public static void rotateRight(int[] arr, int k) {
    // YOUR CODE HERE









}
```

---

### Part B: Testing (5 points)
Write 3 test cases for your rotateRight method, including at least one edge case.

```java
// Test 1:


// Test 2:


// Test 3 (Edge case):


```

---

## Question 3: Linked Lists (25 points)

### Part A: True/False (5 points)
Mark each statement as True (T) or False (F).

1. ____ Inserting at the head of a singly linked list is O(1)
2. ____ Inserting at the tail of a singly linked list (with only head pointer) is O(1)
3. ____ Accessing the middle element of a linked list is O(1)
4. ____ A doubly linked list uses more memory than a singly linked list
5. ____ Linked lists allow random access like arrays

---

### Part B: Implementation (20 points)
Implement a method to **reverse a singly linked list** iteratively.

**Given:**
```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

**Implement:**
```java
/**
 * Reverses a singly linked list iteratively
 * @param head The head of the original list
 * @return The head of the reversed list
 */
public static Node reverse(Node head) {
    // YOUR CODE HERE
















}
```

**Time Complexity of your solution:** ____________

**Space Complexity of your solution:** ____________

---

## Question 4: Stacks & Queues (20 points)

### Part A: Concept (5 points)
Match each problem with the most appropriate data structure:

| Problem | Data Structure (Stack/Queue) |
|---------|------------------------------|
| Undo operation in text editor | ________ |
| Print jobs in order received | ________ |
| Check balanced parentheses | ________ |
| Breadth-First Search (BFS) | ________ |
| Depth-First Search (DFS) | ________ |

---

### Part B: Implementation (15 points)
Write a method to check if a string of parentheses is balanced using a Stack.

**Examples:**
- `"()"` → true
- `"()[]{}"` → true
- `"(]"` → false
- `"([)]"` → false
- `"{[]}"` → true

```java
import java.util.Stack;

/**
 * Checks if parentheses in string are balanced
 * @param s String containing only '(', ')', '[', ']', '{', '}'
 * @return true if balanced, false otherwise
 */
public static boolean isBalanced(String s) {
    // YOUR CODE HERE























}
```

---

## Question 5: Binary Search Trees (20 points)

### Part A: Traversal (8 points)
Given the following Binary Search Tree:

```
        5
       / \
      3   8
     / \   \
    1   4   9
```

Write the output for each traversal:

1. **Inorder:** ________________________________

2. **Preorder:** ________________________________

3. **Postorder:** ________________________________

4. **Level-order:** ________________________________

---

### Part B: Implementation (12 points)
Implement a method to **search** for a value in a BST recursively.

**Given:**
```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}
```

**Implement:**
```java
/**
 * Searches for a value in BST recursively
 * @param root The root of the BST
 * @param target The value to search for
 * @return true if found, false otherwise
 */
public static boolean search(TreeNode root, int target) {
    // YOUR CODE HERE











}
```

**Time Complexity (best case):** ____________

**Time Complexity (worst case):** ____________

---

# 📊 MARKING RUBRIC

## Question 1: Complexity Analysis (15 points)
- **Part A:** 5 points (Answer: O(n²), 3 pts; Justification: 2 pts)
- **Part B:** 5 points (Answer: O(n²), 3 pts; Justification: 2 pts)
- **Part C:** 5 points (Answer: O(n), 3 pts; Justification: 2 pts)

## Question 2: Arrays (20 points)
- **Part A:** 15 points
  - Handles k > arr.length: 2 pts
  - Correct algorithm: 10 pts
  - O(n) time, O(1) space: 3 pts
- **Part B:** 5 points (1-2 pts per valid test case)

## Question 3: Linked Lists (25 points)
- **Part A:** 5 points (1 pt each: T, F, F, T, F)
- **Part B:** 20 points
  - Correct logic: 12 pts
  - Handles edge cases (null, single node): 4 pts
  - Correct complexity analysis: 4 pts

## Question 4: Stacks & Queues (20 points)
- **Part A:** 5 points (1 pt each: Stack, Queue, Stack, Queue, Stack)
- **Part B:** 15 points
  - Correct algorithm: 10 pts
  - Handles all cases: 3 pts
  - Clean code: 2 pts

## Question 5: BST (20 points)
- **Part A:** 8 points (2 pts each traversal)
  - Inorder: 1, 3, 4, 5, 8, 9
  - Preorder: 5, 3, 1, 4, 8, 9
  - Postorder: 1, 4, 3, 9, 8, 5
  - Level-order: 5, 3, 8, 1, 4, 9
- **Part B:** 12 points
  - Correct recursive logic: 8 pts
  - Base case: 2 pts
  - Complexity analysis: 2 pts (Best: O(log n), Worst: O(n))

---

# ✅ MODEL SOLUTIONS

## Question 1: Complexity Analysis

### Part A
**Answer:** O(n²)

**Justification:**
Outer loop runs n times. Inner loop runs n times for each outer iteration.
Total operations: n × n = n²
Therefore, time complexity is O(n²)

---

### Part B
**Answer:** O(n²)

**Justification:**
Outer loop: i goes from 0 to n-1 (n iterations)
Inner loop: j goes from i to n-1
- When i=0: n iterations
- When i=1: n-1 iterations
- ...
- When i=n-1: 1 iteration

Total: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 = O(n²)

---

### Part C
**Answer:** O(n)

**Justification:**
Recursion tree analysis:
- Height of tree: log₂(n) levels (dividing by 2 each time)
- Number of nodes at each level: 2^k where k is level
- But nodes merge: T(n) = 2T(n/2) + O(1)
- Total nodes ≈ 2^(log n) = n
Therefore, time complexity is O(n)

Alternative: Master Theorem: a=2, b=2, f(n)=1
log₂(2) = 1, so O(n^1) = O(n)

---

## Question 2: Arrays

### Part A: Implementation
```java
public static void rotateRight(int[] arr, int k) {
    if (arr == null || arr.length <= 1) {
        return; // Nothing to rotate
    }

    int n = arr.length;
    k = k % n; // Handle k > n

    if (k == 0) {
        return; // No rotation needed
    }

    // Reverse entire array
    reverse(arr, 0, n - 1);

    // Reverse first k elements
    reverse(arr, 0, k - 1);

    // Reverse remaining elements
    reverse(arr, k, n - 1);
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
```

**Explanation:**
1. Original: [1, 2, 3, 4, 5], k=2
2. Reverse all: [5, 4, 3, 2, 1]
3. Reverse first k=2: [4, 5, 3, 2, 1]
4. Reverse remaining: [4, 5, 1, 2, 3] ✓

**Time:** O(n) - each element reversed at most twice
**Space:** O(1) - in-place

---

### Part B: Testing
```java
// Test 1: Normal case
int[] arr1 = {1, 2, 3, 4, 5};
rotateRight(arr1, 2);
// Expected: [4, 5, 1, 2, 3]
assert Arrays.equals(arr1, new int[]{4, 5, 1, 2, 3});

// Test 2: k > arr.length
int[] arr2 = {1, 2, 3};
rotateRight(arr2, 5); // 5 % 3 = 2
// Expected: [2, 3, 1]
assert Arrays.equals(arr2, new int[]{2, 3, 1});

// Test 3: Edge case - k = 0
int[] arr3 = {1, 2, 3};
rotateRight(arr3, 0);
// Expected: [1, 2, 3] (no change)
assert Arrays.equals(arr3, new int[]{1, 2, 3});
```

---

## Question 3: Linked Lists

### Part A: True/False
1. **T** - Inserting at head is O(1)
2. **F** - Inserting at tail is O(n) without tail pointer
3. **F** - Accessing middle is O(n), must traverse
4. **T** - Doubly has two pointers (prev, next) vs one
5. **F** - Linked lists have no random access, must traverse

---

### Part B: Implementation
```java
public static Node reverse(Node head) {
    // Handle empty or single node
    if (head == null || head.next == null) {
        return head;
    }

    Node prev = null;
    Node current = head;
    Node next = null;

    while (current != null) {
        // Save next node
        next = current.next;

        // Reverse the link
        current.next = prev;

        // Move prev and current forward
        prev = current;
        current = next;
    }

    // prev is new head
    return prev;
}
```

**Trace Example:** 1 → 2 → 3 → null

| Iteration | prev | current | next | Action |
|-----------|------|---------|------|--------|
| Initial | null | 1 | null | |
| 1 | null | 1 | 2 | 1→null, prev=1, current=2 |
| 2 | 1 | 2 | 3 | 2→1, prev=2, current=3 |
| 3 | 2 | 3 | null | 3→2, prev=3, current=null |
| End | 3 | null | null | Return prev (3) |

**Result:** 3 → 2 → 1 → null ✓

**Time Complexity:** O(n) - visit each node once
**Space Complexity:** O(1) - only a few pointers

---

## Question 4: Stacks & Queues

### Part A: Matching
1. Undo operation: **Stack** (LIFO)
2. Print jobs: **Queue** (FIFO - first come, first served)
3. Balanced parentheses: **Stack** (last opened, first closed)
4. BFS: **Queue** (level by level)
5. DFS: **Stack** (go deep first)

---

### Part B: Implementation
```java
public static boolean isBalanced(String s) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        // If opening bracket, push to stack
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        }
        // If closing bracket
        else if (c == ')' || c == ']' || c == '}') {
            // Stack empty → unmatched closing bracket
            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();

            // Check if brackets match
            if (c == ')' && top != '(') return false;
            if (c == ']' && top != '[') return false;
            if (c == '}' && top != '{') return false;
        }
    }

    // If stack not empty → unmatched opening brackets
    return stack.isEmpty();
}
```

**Test Cases:**
- `"()"` → Stack: [(] → [empty] → true ✓
- `"([)]"` → Stack: [(, [] → pop [ get ], mismatch → false ✓
- `"{[]}"` → Stack: [{, [[], [] → [empty] → true ✓

---

## Question 5: Binary Search Trees

### Part A: Traversals
```
        5
       / \
      3   8
     / \   \
    1   4   9
```

1. **Inorder (Left, Root, Right):** 1, 3, 4, 5, 8, 9 ← sorted!
2. **Preorder (Root, Left, Right):** 5, 3, 1, 4, 8, 9
3. **Postorder (Left, Right, Root):** 1, 4, 3, 9, 8, 5
4. **Level-order (BFS):** 5, 3, 8, 1, 4, 9

---

### Part B: Implementation
```java
public static boolean search(TreeNode root, int target) {
    // Base case 1: Empty tree
    if (root == null) {
        return false;
    }

    // Base case 2: Found target
    if (root.val == target) {
        return true;
    }

    // Recursive case: search left or right based on BST property
    if (target < root.val) {
        return search(root.left, target);  // Search left subtree
    } else {
        return search(root.right, target); // Search right subtree
    }
}
```

**Time Complexity:**
- **Best case:** O(log n) - balanced tree, divide search space by 2 each time
- **Worst case:** O(n) - skewed tree (linear chain), must check all nodes

**Space Complexity:** O(h) where h is height, due to recursion stack
- Best: O(log n) for balanced tree
- Worst: O(n) for skewed tree

---

# 🎯 EXAM CHEAT SHEET FOR MIDTERM #1

## Quick Complexity Reference
- Single loop: O(n)
- Nested loops (both 0→n): O(n²)
- Binary division: O(log n)
- Array access: O(1)
- Linked list traversal: O(n)

## Common Mistakes to Avoid
1. ❌ `arr.length()` → ✅ `arr.length`
2. ❌ `(left + right)/2` → ✅ `left + (right - left)/2`
3. ❌ Forget null check → ✅ Always check `if (node == null)`
4. ❌ Off-by-one in loops → ✅ Test with n=0, n=1, n=2

## Time Savers
- **Arrays:** Use three-reversal trick for rotation
- **Linked List:** Three-pointer technique for reversal
- **Stacks:** Use Stack for matching/balancing problems
- **Trees:** Inorder traversal of BST → sorted!

---

**Expected Score Distribution:**
- 90-100: Excellent (A)
- 80-89: Very Good (B+/A-)
- 70-79: Good (B)
- 60-69: Satisfactory (C+/B-)
- <60: Needs improvement

**Time Management:**
- Q1 (15 pts): 10 min
- Q2 (20 pts): 12 min
- Q3 (25 pts): 15 min
- Q4 (20 pts): 12 min
- Q5 (20 pts): 11 min

**Good luck! 🍀**
