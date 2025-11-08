# ⚡ INTENSIVE 1-DAY CRASH PLAN
## Maximum Exam Score in Minimum Time
**Target:** Beginner → Exam-Ready in 8-12 hours
**Strategy:** High-yield topics first, spaced practice, active recall

---

## 🎯 PRINCIPLES OF THIS PLAN

1. **Pareto Principle:** 20% of topics yield 80% of exam points
2. **Active Recall:** Test yourself constantly, don't just re-read
3. **Interleaving:** Mix topics to improve retention
4. **Edge Cases:** Most exam points lost here, we'll drill them hard

---

## ⏱️ FLEXIBLE SCHEDULE (Choose Your Time Block)

### 🔥 **8-Hour Plan** (Minimum Effective Crash)
*For students with 1 day before exam*

### 📚 **10-Hour Plan** (Recommended)
*Balanced intensity + retention*

### 💪 **12-Hour Plan** (Maximum Mastery)
*Deep preparation, lowest exam anxiety*

---

# 8-HOUR CRASH PLAN

## Hour 0: Setup & Diagnostic (30 min)
- ✅ Take diagnostic quiz (20 min)
- ✅ Review answers, identify weak areas (10 min)
- 🎯 **Goal:** Know what you DON'T know

---

## Hour 1: Big-O & Complexity Analysis (60 min)
**Why First:** 20-30% of exam questions test this directly or indirectly

### Learn (30 min):
- Rules: O(1), O(log n), O(n), O(n log n), O(n²), O(2ⁿ)
- How to analyze: count loops, recursion depth
- Best/Average/Worst cases
- Space complexity basics

### Practice (30 min):
```java
// Analyze these 10 code snippets (time yourself: 3 min each)
1. Single loop → O(n)
2. Nested loops (i=0; i<n) (j=0; j<n) → O(n²)
3. Nested loops (i=0; i<n) (j=i; j<n) → O(n²) but ~n²/2 operations
4. Binary search → O(log n)
5. Merge sort → O(n log n)
6. Bubble sort → O(n²)
7. Tree traversal → O(n) where n = # nodes
8. Graph BFS/DFS → O(V + E)
9. Fibonacci recursive → O(2ⁿ)
10. Fibonacci DP → O(n)
```

**Mini Quiz:** Analyze 5 random code blocks from practice problems
**Flashcard:** Memorize Big-O cheat sheet

---

## Hour 2: Arrays & ArrayLists (60 min)
**Why:** Easiest points, foundation for everything else

### Learn (20 min):
- Declaration, initialization, access: `arr[i]`
- Common errors: IndexOutOfBounds, off-by-one
- ArrayList API: add, remove, get, size, contains
- 2D arrays: `arr[row][col]`

### Code (20 min):
```java
// Must implement these from scratch:
1. reverseArray(int[] arr)
2. findMax(int[] arr)
3. removeDuplicates(int[] sorted)
4. rotateArray(int[] arr, int k)
5. mergeSortedArrays(int[] a, int[] b)
```

### Practice (20 min):
- Solve 5 easy array problems (provided in curriculum)
- Focus on: edge cases (empty, single element, all same)

**Checkpoint:** Can you write array code without syntax errors? Test yourself!

---

## Hour 3: Linked Lists (60 min)
**Why:** High frequency on exams, tests pointer manipulation

### Learn (25 min):
- Node structure: `class Node { int data; Node next; }`
- Operations: insertHead, insertTail, delete, search
- Singly vs doubly vs circular
- Sentinel/dummy nodes (exam hack!)

### Code (25 min):
**Must implement:**
```java
class LinkedList {
    Node head;

    void insertHead(int data) { /* YOUR CODE */ }
    void insertTail(int data) { /* YOUR CODE */ }
    void delete(int data) { /* YOUR CODE */ }
    boolean search(int data) { /* YOUR CODE */ }
    void reverse() { /* YOUR CODE - EXAM FAVORITE */ }
}
```

### Practice (10 min):
- Reverse linked list (recursive + iterative)
- Find middle element (slow/fast pointer)
- Detect cycle (Floyd's algorithm)

**Checkpoint:** Draw linked list operations on paper, trace pointers

---

## Hour 4: Stacks & Queues (60 min)
**Why:** 100% chance of appearing, easy points if you know patterns

### Learn (20 min):
- Stack: LIFO, push/pop/peek, array vs linked implementation
- Queue: FIFO, offer/poll/peek
- Deque: both ends
- Java API: `Stack<>`, `Queue<>`, `Deque<>`

### Pattern Recognition (20 min):
**When to use Stack:**
- Balanced parentheses
- Undo operations
- DFS (depth-first search)
- Expression evaluation (postfix, infix)
- Backtracking

**When to use Queue:**
- BFS (breadth-first search)
- Level-order traversal
- Task scheduling

### Code (20 min):
```java
// Implement these classic problems:
1. isValidParentheses(String s)
2. evaluatePostfix(String expr)
3. implementQueueUsingStacks()
4. implementStackUsingQueues()
```

**Checkpoint:** Solve balanced parentheses in < 5 minutes

---

## Hour 5: Trees - Part 1 (60 min)
**Why:** Core topic, builds to graphs, 30%+ of exam

### Learn (25 min):
- Tree terminology: root, leaf, height, depth, parent, child
- Binary tree structure:
```java
class TreeNode {
    int val;
    TreeNode left, right;
}
```
- Traversals: Inorder, Preorder, Postorder (recursive + iterative)
- Level-order traversal (BFS with queue)

### Code (25 min):
**Must implement:**
```java
// Recursive traversals
void inorder(TreeNode root) { /* left, root, right */ }
void preorder(TreeNode root) { /* root, left, right */ }
void postorder(TreeNode root) { /* left, right, root */ }

// BFS
void levelOrder(TreeNode root) { /* use queue */ }

// Common exam questions
int height(TreeNode root)
int countNodes(TreeNode root)
boolean isSymmetric(TreeNode root)
```

### Practice (10 min):
- Trace traversals on paper for a sample tree
- Memorize traversal orders

**Checkpoint:** Given a tree, write all 4 traversal outputs instantly

---

## Hour 6: Trees - Part 2 (BST) + Hash Tables (60 min)
**Split:** 30 min BST + 30 min Hash

### BST (30 min):
- **Property:** left < root < right (all nodes in subtrees)
- **Search:** O(log n) average, O(n) worst (skewed tree)
- **Insert:** Maintain BST property
- **Delete:** 3 cases (no child, one child, two children → find inorder successor)

**Code:**
```java
TreeNode insert(TreeNode root, int val) { /* recursive */ }
TreeNode search(TreeNode root, int val) { /* recursive */ }
boolean isValidBST(TreeNode root) { /* inorder → should be sorted */ }
```

### Hash Tables (30 min):
- **Concept:** Key → hash function → index
- **Collision resolution:** Chaining (linked list), open addressing
- **Java API:** `HashMap<K,V>`, `HashSet<E>`
- **Operations:** put, get, remove, containsKey → O(1) average

**Common exam questions:**
```java
// Two sum problem (array + target)
int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    // YOUR CODE
}

// Find first non-repeating character
char firstUniqChar(String s) { /* use HashMap */ }
```

**Checkpoint:** Explain hash collision resolution methods

---

## Hour 7: Recursion & Sorting (60 min)
**Split:** 25 min recursion + 35 min sorting

### Recursion (25 min):
- **Pattern:** Base case + recursive case
- **Common mistakes:** Forgetting base case, wrong recursive call
- **Practice:**
  - Factorial
  - Fibonacci
  - Power(x, n)
  - Binary search (recursive)

### Sorting (35 min):
**Must know:** Bubble, Selection, Insertion, Merge, Quick

| Algorithm | Time (Best/Avg/Worst) | Space | Stable? | When to Use |
|-----------|----------------------|-------|---------|-------------|
| Bubble | O(n)/O(n²)/O(n²) | O(1) | Yes | Never (teaching only) |
| Selection | O(n²)/O(n²)/O(n²) | O(1) | No | Small arrays |
| Insertion | O(n)/O(n²)/O(n²) | O(1) | Yes | Nearly sorted |
| Merge | O(n log n) all | O(n) | Yes | Guaranteed performance |
| Quick | O(n log n)/O(n log n)/O(n²) | O(log n) | No | Average case optimal |

**Code:**
```java
// Implement merge sort (exam favorite)
void mergeSort(int[] arr, int left, int right) { /* YOUR CODE */ }
void merge(int[] arr, int left, int mid, int right) { /* YOUR CODE */ }
```

**Checkpoint:** Trace merge sort on [5,2,8,1,9], show all steps

---

## Hour 8: Mock Exam + Review (60 min)

### Mock Exam (40 min):
- Take Medium Midterm #1 (from midterms folder)
- Strict time limit: 40 minutes
- No notes, no IDE autocomplete

### Review (20 min):
- Check answers
- Understand every mistake
- Note time management (did you run out of time?)

---

# 10-HOUR PLAN (8-Hour + Extensions)

**Add these blocks:**

### Hour 8: Heaps & Priority Queues (60 min)
- Min heap, max heap structure
- Operations: insert O(log n), extractMin O(log n), peek O(1)
- Java: `PriorityQueue<>`
- Use cases: Find kth largest, merge k sorted lists

### Hour 9: Graphs - Basics (60 min)
- Representations: adjacency matrix vs list
- BFS (queue), DFS (stack/recursion)
- Connected components
- Cycle detection

### Hour 10: Final Cramming (60 min)
- Flashcards speed review
- Cheat sheet memorization
- Edge case checklist
- Hard Midterm #3

---

# 12-HOUR PLAN (10-Hour + Extensions)

**Add these blocks:**

### Hour 10: Advanced Trees (60 min)
- AVL trees (self-balancing)
- Red-Black trees (conceptual understanding)
- Trie (prefix tree for strings)
- Applications: autocomplete, spell check

### Hour 11: Advanced Graphs (60 min)
- Shortest path: Dijkstra's algorithm
- Topological sort
- Union-Find (Disjoint Set)
- Applications: network routing, dependencies

### Hour 12: Final Review & Hard Problems (60 min)
- Solve 5 hardest practice problems (mixed topics)
- Review all midterm solutions
- Mental walkthrough of every data structure
- Confidence builder: re-solve problems you got right earlier (boost morale!)

---

# 🎯 EXAM DAY CHECKLIST

## Night Before:
- [ ] Sleep 7-8 hours (no all-nighters!)
- [ ] Review flashcards (20 min)
- [ ] Skim cheat sheet (10 min)

## Morning Of:
- [ ] Light breakfast (brain needs glucose)
- [ ] 10-min flashcard review
- [ ] Bring: Pen, pencil, eraser, water
- [ ] Arrive 10 min early (settle nerves)

## During Exam:
- [ ] Read ALL questions first (2 min)
- [ ] Do easy questions first (build confidence + time bank)
- [ ] For coding: write pseudocode first, then code
- [ ] Leave 5 min at end to check for: off-by-one errors, null checks, edge cases

---

# 📝 HOURLY BREAK SCHEDULE

**Every 60 minutes:**
- Stand up, stretch (2 min)
- Close eyes, breathe deeply (1 min)
- Quick water/snack (2 min)
- **Total break:** 5 min

**After 4 hours:**
- Longer break (15 min)
- Walk outside if possible
- Eat proper meal

**Brain Science:** Breaks improve retention! Don't skip them.

---

# 🧠 ACTIVE LEARNING TECHNIQUES (Use Throughout)

1. **Feynman Technique:** Explain concepts out loud in simple terms
2. **Pomodoro:** 25 min focus + 5 min break
3. **Spaced Repetition:** Review earlier topics every 2-3 hours
4. **Interleaving:** Mix topics (don't do 3 hours straight on one thing)
5. **Testing Effect:** Quiz yourself constantly (don't just re-read)

---

# 🚨 COMMON EXAM PITFALLS (Avoid These!)

1. **Off-by-One Errors:** Test with small inputs (n=0, n=1, n=2)
2. **Null Pointer:** Always check `if (node == null)` or `if (arr == null)`
3. **Edge Cases:** Empty input, single element, duplicate values, negative numbers
4. **Time Complexity:** Count ALL loops, don't forget hidden costs (e.g., String concatenation)
5. **API Confusion:** `size()` vs `length` vs `length()`, `add()` vs `offer()`

---

# 🎓 CONFIDENCE BUILDERS

Before exam, remind yourself:
- ✅ "I've coded every major data structure from scratch"
- ✅ "I can analyze time complexity of any algorithm"
- ✅ "I've solved 50+ practice problems"
- ✅ "I know how to handle edge cases"
- ✅ "I've taken full mock exams under time pressure"

**You've got this!** 🚀

---

**Adapted Version Based on Available Hours:**
*After you tell me how many hours you have, I'll give you a customized schedule with specific topics and problems for each hour block.*

**How many hours do you have available today for studying?**
