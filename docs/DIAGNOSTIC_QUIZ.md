# 🎯 Data Structures Diagnostic Quiz
**Time Limit:** 20 minutes
**Instructions:** Answer all 10 questions. Score yourself honestly to get personalized recommendations.

---

## Question 1: Basic Complexity Analysis (Beginner)
**What is the time complexity of the following code?**
```java
public int sum(int[] arr) {
    int total = 0;
    for (int i = 0; i < arr.length; i++) {
        total += arr[i];
    }
    return total;
}
```
**A)** O(1)
**B)** O(log n)
**C)** O(n)
**D)** O(n²)

---

## Question 2: Array Manipulation (Beginner)
**What does this code print?**
```java
int[] nums = {1, 2, 3, 4, 5};
System.out.println(nums[2]);
```
**A)** 1
**B)** 2
**C)** 3
**D)** ArrayIndexOutOfBoundsException

---

## Question 3: Linked List Concept (Beginner-Intermediate)
**Which operation is O(1) for a singly linked list with only a head pointer?**
**A)** Insert at beginning
**B)** Insert at end
**C)** Access middle element
**D)** Delete last element

---

## Question 4: Stack Application (Intermediate)
**A stack is best suited for which problem?**
**A)** Finding shortest path in a graph
**B)** Checking balanced parentheses
**C)** Sorting a large dataset
**D)** Implementing breadth-first search

---

## Question 5: Code Analysis (Intermediate)
**What is the output?**
```java
Queue<Integer> q = new LinkedList<>();
q.offer(1);
q.offer(2);
q.poll();
q.offer(3);
System.out.println(q.peek());
```
**A)** 1
**B)** 2
**C)** 3
**D)** null

---

## Question 6: Binary Search Tree Property (Intermediate)
**For a valid BST, which statement is TRUE?**
**A)** Left subtree values > root > right subtree values
**B)** Left subtree values < root < right subtree values
**C)** Parent node always greater than children
**D)** Tree must be complete

---

## Question 7: Complexity with Nested Loops (Intermediate-Advanced)
**What is the time complexity?**
```java
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        System.out.println(i + j);
    }
}
```
**A)** O(n)
**B)** O(n log n)
**C)** O(n²)
**D)** O(2ⁿ)

---

## Question 8: Hash Table Concept (Advanced)
**In separate chaining for collision resolution, what is the worst-case time complexity for search if all n keys hash to the same bucket?**
**A)** O(1)
**B)** O(log n)
**C)** O(n)
**D)** O(n²)

---

## Question 9: Recursion Analysis (Advanced)
**What is the time complexity of this Fibonacci implementation?**
```java
public int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
```
**A)** O(n)
**B)** O(n²)
**C)** O(2ⁿ)
**D)** O(n log n)

---

## Question 10: Graph Traversal (Advanced)
**Which graph traversal uses a queue data structure?**
**A)** Depth-First Search (DFS)
**B)** Breadth-First Search (BFS)
**C)** Dijkstra's algorithm
**D)** Both A and C

---

# ✅ ANSWER KEY & SCORING

| Question | Answer | Explanation |
|----------|--------|-------------|
| 1 | **C** | The loop iterates n times (length of array), performing O(1) work each iteration → O(n) |
| 2 | **C** | Arrays are 0-indexed. `nums[2]` accesses the third element (value 3) |
| 3 | **A** | Insert at beginning is O(1): create new node, point to old head, update head. Others require traversal |
| 4 | **B** | Stacks are perfect for balanced parentheses (LIFO matches nesting structure) |
| 5 | **B** | Queue is FIFO: offer(1,2) → [1,2], poll() → [2], offer(3) → [2,3], peek() → 2 |
| 6 | **B** | BST property: all left subtree values < root, all right subtree values > root |
| 7 | **C** | Inner loop runs (n-i) times. Total: n + (n-1) + ... + 1 = n(n+1)/2 = O(n²) |
| 8 | **C** | All keys in one bucket → search degrades to linear search through the chain |
| 9 | **C** | Each call spawns 2 more calls → binary tree of calls with height n → O(2ⁿ) nodes |
| 10 | **B** | BFS uses a queue to explore level by level. DFS uses a stack (or recursion call stack) |

---

# 📊 SCORE INTERPRETATION & PERSONALIZED RECOMMENDATIONS

## 🔴 Score 0-3: **Fundamentals Review Needed**
**Current Level:** Beginner
**Priority:** Master the basics before tackling exams

**Immediate Actions:**
1. ✅ **Start with 8-week mastery plan** — you need solid foundations
2. 📖 Focus on topics: Arrays, Complexity Analysis, Basic Data Structure Concepts
3. 💻 Code along with every example (no skipping!)
4. ⏰ Spend 1 hour daily on fundamentals for the next week

**Today's Focus (Next 3 hours):**
- Hour 1: Arrays and basic iteration (read topic guide + run examples)
- Hour 2: Understanding Big-O (watch complexity examples, quiz yourself)
- Hour 3: Implement simple LinkedList from scratch with tests

**Resources to Use:**
- Read curriculum sections: Generics, Arrays, Complexity Analysis
- Complete all "Easy" practice problems for Arrays
- Use flashcards for Big-O notation daily

---

## 🟡 Score 4-6: **Intermediate - Needs Practice**
**Current Level:** Intermediate (some gaps)
**Priority:** Fill knowledge gaps and build problem-solving speed

**Immediate Actions:**
1. ✅ **Use 2-week rapid plan** — intensive practice with targeted review
2. 📖 Review topics you missed, then immediately solve 3 related problems
3. 💻 Focus on: Linked Lists, Stacks, Queues, Trees, Recursion
4. ⏰ Dedicate 2 hours daily: 1hr concepts + 1hr practice problems

**Today's Focus (Next 4 hours):**
- Hour 1: Linked List operations (especially edge cases like empty list, single node)
- Hour 2: Stack/Queue applications (solve 5 problems)
- Hour 3: Binary Search Trees (implementation + traversals)
- Hour 4: Practice exam #1 (medium difficulty) under timed conditions

**Weak Areas to Strengthen:**
- Topics you got wrong in quiz (revisit those curriculum sections)
- Time complexity analysis (practice estimating before checking answers)
- Recursion patterns (master base case + recursive case thinking)

---

## 🟢 Score 7-10: **Advanced - Exam Ready (almost!)**
**Current Level:** Advanced
**Priority:** Polish weak spots, maximize speed, master hard problems

**Immediate Actions:**
1. ✅ **1-day crash plan works for you** — maximize retention and exam tactics
2. 📖 Quick review of all topics (use cheat sheet)
3. 💻 Focus on: Hard problems, optimization, edge cases, Graph algorithms
4. ⏰ Take full mock exams (all 5) under strict time limits

**Today's Focus (Next 6 hours):**
- Hour 1: Speed review with flashcards + cheat sheet (all topics)
- Hour 2-3: Solve 10 hard practice problems (mixed topics)
- Hour 4: Full mock midterm exam #3 (hard) - timed, closed book
- Hour 5: Review mistakes, understand why you got them wrong
- Hour 6: Cram session on your 2 weakest topics + memorize edge case patterns

**Optimization Tips:**
- For any topic scoring < 80%, do 5 extra problems in that area
- Practice writing code on paper (no IDE autocomplete in exams!)
- Memorize Java API methods for common structures (ArrayList, HashMap, etc.)
- Review "Thinking Templates" for systematic problem-solving

---

# 🎓 GENERAL TIPS FOR ALL LEVELS

1. **Active Coding:** Type every example. Never just read code.
2. **Test Everything:** Write test cases for edge cases (empty, single element, null).
3. **Explain Out Loud:** After solving, explain the solution to an imaginary student.
4. **Time Yourself:** Always solve practice problems under time pressure.
5. **Mistakes Journal:** Keep a list of every mistake type you make (off-by-one, null pointer, etc.).

---

**Next Step:** See the 1-Day Crash Plan below for hour-by-hour study guide!
