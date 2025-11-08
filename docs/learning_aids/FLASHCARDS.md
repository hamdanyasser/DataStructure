# 🎴 Spaced Repetition Flashcards (30 Cards)
## Data Structures & Algorithms in Java

**Instructions:** Review daily! Test yourself by covering the answer. Mark cards you get wrong and review them twice as often.

---

## CARD 1: Array Access
**Q:** What is the time complexity of accessing an element by index in an array?
**A:** O(1) - Direct memory address calculation: `base_address + (index * element_size)`

---

## CARD 2: ArrayList vs Array
**Q:** What is the key difference between an Array and an ArrayList in Java?
**A:** Array: Fixed size, can hold primitives, `.length` field
ArrayList: Dynamic size, only objects, `.size()` method, auto-resizes

---

## CARD 3: Binary Search Requirement
**Q:** What is the precondition for binary search?
**A:** The array/list MUST be sorted in ascending (or descending) order. Binary search doesn't work on unsorted data!

---

## CARD 4: Linked List Head Insert
**Q:** What is the time complexity of inserting at the head of a singly linked list?
**A:** O(1)
```java
newNode.next = head;
head = newNode;
```

---

## CARD 5: Linked List Tail Insert
**Q:** What is the time complexity of inserting at the tail of a singly linked list (with only head pointer)?
**A:** O(n) - Must traverse entire list to find last node
(O(1) if you maintain a tail pointer)

---

## CARD 6: Stack LIFO
**Q:** What does LIFO mean for a Stack?
**A:** Last In, First Out - The most recently added element is the first one removed
Like a stack of plates: add/remove from top only

---

## CARD 7: Queue FIFO
**Q:** What does FIFO mean for a Queue?
**A:** First In, First Out - The first element added is the first one removed
Like a line at a store: join at back, leave from front

---

## CARD 8: Stack Operations
**Q:** Name the three main Stack operations and their time complexity.
**A:**
- `push(e)` - Add to top - O(1)
- `pop()` - Remove from top - O(1)
- `peek()` - View top without removing - O(1)

---

## CARD 9: When to Use Stack
**Q:** Name 3 classic problems that use a Stack.
**A:**
1. Balanced parentheses checking
2. Expression evaluation (postfix/infix)
3. Depth-First Search (DFS)
4. Undo operations
5. Backtracking

---

## CARD 10: Binary Search Tree Property
**Q:** What is the BST (Binary Search Tree) property?
**A:** For every node:
- ALL left subtree values < node value
- ALL right subtree values > node value
This applies recursively to all subtrees!

---

## CARD 11: Tree Height
**Q:** What is the height of a tree with n nodes in the best and worst cases?
**A:**
- Best case (balanced): O(log n)
- Worst case (skewed/linear): O(n)

---

## CARD 12: Tree Traversals
**Q:** What are the 4 main tree traversal orders?
**A:**
1. **Inorder:** Left, Root, Right → Sorted output for BST!
2. **Preorder:** Root, Left, Right
3. **Postorder:** Left, Right, Root
4. **Level-order:** BFS level by level (uses queue)

---

## CARD 13: HashMap Complexity
**Q:** What is the average and worst-case time complexity for HashMap get/put operations?
**A:**
- **Average:** O(1) - direct hash lookup
- **Worst:** O(n) - all keys hash to same bucket (rare with good hash function)

---

## CARD 14: Hash Collision Resolution
**Q:** Name two methods for handling hash collisions.
**A:**
1. **Separate Chaining:** Each bucket is a linked list of entries
2. **Open Addressing:** Find next available slot (linear probing, quadratic probing, double hashing)

---

## CARD 15: Heap Property
**Q:** What is the min-heap property?
**A:** Every parent node has value ≤ its children
(Root is minimum element)
Max-heap: Every parent ≥ children (root is maximum)

---

## CARD 16: Heap Operations
**Q:** What are the time complexities for heap insert and extract-min?
**A:**
- **Insert:** O(log n) - add at bottom, bubble up
- **Extract-min/max:** O(log n) - remove root, move last to root, bubble down
- **Peek min/max:** O(1) - just look at root

---

## CARD 17: Priority Queue Implementation
**Q:** What data structure is commonly used to implement a Priority Queue in Java?
**A:** **Heap** (binary heap)
Java's `PriorityQueue` uses a min-heap by default

---

## CARD 18: BFS vs DFS
**Q:** What data structure does BFS use? What about DFS?
**A:**
- **BFS:** Queue (FIFO) - explore level by level
- **DFS:** Stack or recursion (LIFO) - go deep first

---

## CARD 19: Graph Representations
**Q:** What are the two main ways to represent a graph in memory?
**A:**
1. **Adjacency Matrix:** 2D array, O(V²) space, O(1) edge lookup
2. **Adjacency List:** Array of lists, O(V + E) space, O(degree) edge lookup

---

## CARD 20: Sorting - Merge Sort
**Q:** What are the time and space complexities of Merge Sort?
**A:**
- **Time:** O(n log n) for best, average, and worst cases (guaranteed!)
- **Space:** O(n) - requires additional array for merging
- **Stable:** Yes

---

## CARD 21: Sorting - Quick Sort
**Q:** What are the time complexities of Quick Sort?
**A:**
- **Best/Average:** O(n log n)
- **Worst:** O(n²) - when pivot is always min/max (rare with good pivot selection)
- **Space:** O(log n) - recursion stack
- **Stable:** No

---

## CARD 22: Stable Sorting
**Q:** What does it mean for a sorting algorithm to be "stable"?
**A:** Stable sorting preserves the relative order of equal elements
Example: If two elements have same value, stable sort keeps them in original order

---

## CARD 23: Recursion Base Case
**Q:** What happens if you forget the base case in a recursive function?
**A:** **Stack Overflow!** The function calls itself infinitely until the call stack is exhausted
Always define clear stopping conditions first!

---

## CARD 24: Fast/Slow Pointer
**Q:** How do you find the middle element of a linked list in one pass?
**A:** Use **two pointers:**
- Slow: moves 1 step at a time
- Fast: moves 2 steps at a time
When fast reaches end, slow is at middle!

---

## CARD 25: Cycle Detection
**Q:** How do you detect a cycle in a linked list?
**A:** **Floyd's Cycle Detection (Tortoise & Hare):**
- Use slow (1 step) and fast (2 steps) pointers
- If they ever meet → cycle exists
- If fast reaches null → no cycle
Time: O(n), Space: O(1)

---

## CARD 26: Two Pointers Pattern
**Q:** When should you consider using the two-pointer technique?
**A:**
- Array/string problems
- Finding pairs with certain sum
- Removing duplicates in-place
- Reversing
- Sliding window problems

---

## CARD 27: Dynamic Programming
**Q:** What are the two key characteristics of problems suitable for Dynamic Programming?
**A:**
1. **Optimal Substructure:** Optimal solution contains optimal solutions to subproblems
2. **Overlapping Subproblems:** Same subproblems are solved multiple times
(Use memoization or tabulation to avoid recomputation)

---

## CARD 28: Big-O Drop Constants
**Q:** Simplify: O(2n + 5)
**A:** O(n)
In Big-O, we drop constants and lower-order terms. Only keep the fastest-growing term.

---

## CARD 29: Big-O Nested Loops
**Q:** What is the time complexity?
```java
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        // O(1) work
    }
}
```
**A:** O(n²)
Inner loop runs: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 = O(n²)

---

## CARD 30: Amortized Analysis
**Q:** Why is ArrayList add() operation O(1) amortized even though resizing is O(n)?
**A:** Resizing happens rarely (when capacity is full)
If we add n elements:
- Most operations: O(1)
- Few operations: O(n) resize
Average over all operations: (n × 1 + few × n) / n = O(1) amortized

---

## 📊 STUDY SCHEDULE FOR FLASHCARDS

### Daily Review (5-10 minutes):
- **Day 1:** Cards 1-10
- **Day 2:** Cards 11-20
- **Day 3:** Cards 21-30
- **Day 4:** All cards you got wrong on Days 1-3
- **Day 5:** Random 15 cards
- **Day 6:** All 30 cards (speed review)
- **Day 7:** Only cards you consistently miss

### Spaced Repetition Schedule:
- ✅ **Correct on first try:** Review in 3 days
- ⚠️ **Correct on second try:** Review in 1 day
- ❌ **Incorrect:** Review immediately, then next day

---

## 💡 TIPS FOR USING FLASHCARDS

1. **Active Recall:** Say answer out loud before flipping
2. **Don't Peek:** Really try to remember, even if it takes time
3. **Explain to Others:** Best way to internalize
4. **Make Your Own:** Add cards for topics you struggle with
5. **Review Before Bed:** Better retention while sleeping

---

**Print these out, cut them into cards, and carry them with you!**

**Next:** Review MASTER_CHEATSHEET.md for comprehensive quick reference
