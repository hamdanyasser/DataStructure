# CSCI316 Midterm - 1-Day Crash Plan
## Intensive 12-Hour Study Schedule

> **Target:** Master all Professor El Amouri's syllabus items in one focused day
> **Start:** 8:00 AM | **Finish:** 8:00 PM
> **Strategy:** Theory → Code → Practice → Review cycles

---

## Morning Session (8:00 AM - 12:00 PM): Foundation Building

### Hour 1: 8:00 - 9:00 AM - Generics Deep Dive
**Goal:** Master all generic patterns

- **8:00 - 8:20** Read MIDTERM_PREP_GUIDE.md Section 1 (Generics)
  - Generic class, generic method, upper/lower bounds
  - Take notes on PECS rule

- **8:20 - 8:40** Code Practice (write from scratch, no looking!)
  - [ ] Generic Box class
  - [ ] Generic findMax method with `extends Comparable`
  - [ ] PECS example: copy(List<? extends T>, List<? super T>)

- **8:40 - 9:00** Quiz Yourself
  - When to use `extends` vs `super`?
  - Why can't you do `new T[10]`?
  - What's type erasure?
  - **Check answers in QUICK_REFERENCE_EXAM_FORMULAS.md**

---

### Hour 2: 9:00 - 10:00 AM - Arrays & Shift Operations
**Goal:** Perfect shift left/right by muscle memory

- **9:00 - 9:20** Read Section 2 (Arrays)
  - GameEntry/Scoreboard pattern
  - Shift right (backwards loop!)
  - Shift left (forwards loop!)
  - Why loop direction matters

- **9:20 - 9:45** Code Practice (CRITICAL - do NOT skip!)
  - [ ] Write shiftRight from memory: `for (i=size; i>index; i--)`
  - [ ] Write shiftLeft from memory: `for (i=index; i<size-1; i++)`
  - [ ] Implement GameEntry.add() with shift right
  - [ ] Implement Scoreboard.remove() with shift left
  - **Run code, verify it works!**

- **9:45 - 10:00** 2D Arrays
  - [ ] Code row-major traversal
  - [ ] Code column-major traversal
  - [ ] Practice: rotate matrix 90 degrees

---

### Hour 3: 10:00 - 11:00 AM - Singly Linked List Mastery
**Goal:** Implement entire SLL from scratch

- **10:00 - 10:15** Read MIDTERM_PREP_PART2.md Section 3.1
  - Node structure
  - All operations: addFirst, addLast, addAt, removeFirst, removeLast, removeAt

- **10:15 - 10:50** Code Practice (WRITE FROM MEMORY!)
  - [ ] Create Node class
  - [ ] Implement addFirst (O(1))
  - [ ] Implement addLast (O(n))
  - [ ] Implement addAt(index) - remember: traverse to i-1!
  - [ ] Implement removeFirst (O(1))
  - [ ] Implement removeLast (O(n))
  - [ ] Implement get(index)
  - **Test with: empty list, single element, multiple elements**

- **10:50 - 11:00** Edge Cases
  - What if index out of bounds?
  - What if remove from empty list?
  - What if add to position size (end)?

---

### Hour 4: 11:00 AM - 12:00 PM - Doubly Linked List + Circular
**Goal:** Understand guard nodes and circularity

- **11:00 - 11:30** Doubly Linked List with Guards
  - Read Section 3.2
  - [ ] Code DNode (data, prev, next)
  - [ ] Initialize header and trailer: `header.next = trailer; trailer.prev = header`
  - [ ] Implement addFirst using addBetween helper
  - [ ] Implement addLast - **KEY: O(1) via trailer.prev!**
  - [ ] Implement removeFirst, removeLast (both O(1)!)
  - **Remember:** Update 4 pointers when inserting

- **11:30 - 12:00** Circular Linked List
  - Read Section 3.3
  - [ ] Code CNode
  - [ ] Implement with tail reference (tail.next = head!)
  - [ ] addFirst, addLast, removeFirst
  - [ ] Traversal: use counter, not null check!
  - **Test:** Single element (tail.next == tail)

---

## Lunch Break: 12:00 - 1:00 PM
**Rest, eat, review quick reference sheet while eating**

---

## Afternoon Session (1:00 PM - 5:00 PM): Complexity, Recursion, Stacks

### Hour 5: 1:00 - 2:00 PM - Complexity Analysis
**Goal:** Analyze any loop/recursion instantly

- **1:00 - 1:20** Review Complexity Patterns
  - Read QUICK_REFERENCE Complexity section
  - Memorize: O(1), O(log n), O(n), O(n log n), O(n²), O(2^n)
  - Loop patterns: single=O(n), nested=O(n²), halving=O(log n)

- **1:20 - 1:50** Practice Problems
  - [ ] Find complexity: `for (i=0; i<n; i++) for (j=0; j<i; j++)`
  - [ ] Find complexity: `for (i=1; i<n; i*=2) for (j=0; j<n; j++)`
  - [ ] Recursion: T(n) = T(n/2) + O(1) → ?
  - [ ] Recursion: T(n) = 2T(n/2) + O(n) → ?
  - [ ] Why is naive Fib O(2^n)?
  - **Check answers:** O(n²), O(n log n), O(log n), O(n log n), redundant calls

- **1:50 - 2:00** Master Theorem Quick Reference
  - Write out shortcuts on paper
  - Space complexity of recursion = max stack depth!

---

### Hour 6: 2:00 - 3:00 PM - Recursion Functions (ALL 7!)
**Goal:** Code all required functions from memory

- **2:00 - 2:45** Code All 7 Functions (NO LOOKING AT NOTES!)
  - [ ] 1. factorial(n)
  - [ ] 2. linearSum(arr, n)
  - [ ] 3. reverseArray(arr, low, high)
  - [ ] 4. binarySearch(arr, target, low, high)
  - [ ] 5. binarySum(arr, low, high)
  - [ ] 6. binaryFib(n, memo) - with memoization!
  - [ ] 7. linearFibonacci(n) - naive version

- **2:45 - 3:00** Test Each Function
  - Run with sample inputs
  - Check complexity of each
  - **Memorize:** Which are O(n), O(log n), O(2^n)?

---

### Hour 7: 3:00 - 4:00 PM - Stack Implementations
**Goal:** Implement stack 3 ways + understand applications

- **3:00 - 3:30** Stack Implementations
  - [ ] Array-based stack (push, pop, peek)
    - Remember: `arr[++top] = val` for push
    - Remember: `val = arr[top--]` for pop
  - [ ] Linked list stack (push at head, pop from head)
  - [ ] Know complexity: all O(1)!

- **3:30 - 4:00** Stack Theory
  - LIFO principle
  - When to use each implementation?
  - Fixed array vs dynamic array vs linked list
  - **Draw diagrams** of push/pop operations

---

### Hour 8: 4:00 - 5:00 PM - Stack Applications
**Goal:** Master matching and expression evaluation

- **4:00 - 4:25** Parenthesis Matching
  - [ ] Code from scratch:
    ```
    for each char:
      if opening → push
      if closing → check match with pop
    return stack.isEmpty()  // CRITICAL!
    ```
  - [ ] Test: "()", "([)]", "((("

- **4:25 - 4:50** Expression Evaluation
  - [ ] Infix evaluation (two stacks)
  - [ ] Operator precedence: * / > + -
  - [ ] Parenthesis handling
  - [ ] Test: "3 + 5 * 2" → 13

- **4:50 - 5:00** HTML Tag Matching (quick review)
  - Same principle as parenthesis
  - Extract tag names, match opening/closing

---

## Evening Session (5:00 PM - 8:00 PM): Practice & Review

### Hour 9: 5:00 - 6:00 PM - Mixed Practice Problems

**Solve these without looking at solutions first:**

1. **Array Problem:** Implement a resizable array that doubles capacity when full
   - Should handle: add, get, remove
   - Track size vs capacity

2. **Linked List Problem:** Reverse a singly linked list in-place
   - Three pointer technique: prev, current, next
   - O(n) time, O(1) space

3. **Recursion Problem:** Print all permutations of string
   - Think: fix first char, permute rest
   - How many total permutations? n!

4. **Stack Problem:** Check if string is palindrome using stack
   - Push first half, compare with second half
   - Handle odd/even length

5. **Complexity Problem:** Analyze this:
   ```java
   for (int i = 1; i < n; i *= 2) {
       for (int j = 0; j < i; j++) {
           System.out.println(i + j);
       }
   }
   ```
   - Outer: log n iterations
   - Inner: 1, 2, 4, 8, ... iterations → total 2^(log n) - 1 = n - 1
   - Answer: O(n)

---

### Hour 10: 6:00 - 7:00 PM - Exam Simulation

**Time yourself: 60 minutes**

**Section A: Short Answer (15 min)**
1. What is type erasure? When does it happen?
2. Why shift right backwards and shift left forwards?
3. What's the advantage of doubly linked list with guards over without?
4. Explain PECS rule with example
5. Why is naive Fibonacci O(2^n)?

**Section B: Code Writing (30 min)**
1. Implement generic Pair<K,V> class with equals method
2. Write function to insert into sorted singly linked list
3. Write recursive function to find nth Fibonacci with memoization
4. Implement parenthesis matching

**Section C: Complexity Analysis (15 min)**
1. Analyze time and space for:
   ```java
   int mystery(int n) {
       if (n <= 1) return 1;
       return mystery(n/2) + mystery(n/2);
   }
   ```
2. Compare: singly LL vs doubly LL with guards - all operations
3. Stack space for: factorial(1000)?

**Check answers in prep guides!**

---

### Hour 11: 7:00 - 7:45 PM - Review Weak Areas

Based on exam simulation, focus on weakest topics:

**If struggled with Generics:**
- Re-code all generic examples
- Practice PECS rule scenarios

**If struggled with Linked Lists:**
- Draw pointer diagrams on paper
- Code add/remove operations step-by-step

**If struggled with Recursion:**
- Draw recursion tree for each function
- Trace with small inputs (n=3, n=4)

**If struggled with Stacks:**
- Draw stack state after each operation
- Practice matching algorithms by hand

**If struggled with Complexity:**
- Work through more loop examples
- Practice master theorem

---

### Hour 12: 7:45 - 8:00 PM - Final Review

**Speed round through QUICK_REFERENCE_EXAM_FORMULAS.md:**

- [ ] Read all Big-O classes
- [ ] Review all loop patterns
- [ ] Check all recursion formulas
- [ ] Verify array shift direction
- [ ] Confirm linked list complexities
- [ ] Review stack algorithms
- [ ] Check generic syntax rules
- [ ] Review common gotchas

**Print QUICK_REFERENCE and keep for exam!**

---

## After 8 PM: REST!

**Do NOT study late:**
- Your brain needs sleep to consolidate
- Get 7-8 hours of sleep
- Set alarm with buffer time
- Have breakfast before exam
- Bring printed quick reference (if allowed)

---

## Prioritization If Running Out of Time

**If you can only do 6 hours, focus on:**
1. **Hour 2:** Arrays & Shift (exam favorite!)
2. **Hour 3:** Singly Linked List (most tested)
3. **Hour 6:** All 7 recursion functions (required!)
4. **Hour 7-8:** Stack implementations & applications
5. **Hour 5:** Complexity analysis
6. **Hour 1:** Generics

**If you can only do 3 hours:**
1. Code all 7 recursion functions (1 hour)
2. Singly linked list from scratch (1 hour)
3. Array shift operations + Stack matching (1 hour)

---

## Exam Day Checklist

**Bring:**
- [ ] Printed QUICK_REFERENCE_EXAM_FORMULAS.md
- [ ] Pen/pencil + backup
- [ ] Student ID
- [ ] Calculator (if allowed)
- [ ] Water bottle

**Before exam starts:**
- [ ] Write Big-O classes on scratch paper
- [ ] Write shift patterns: backwards vs forwards
- [ ] Write PECS rule
- [ ] Write recursion base/recursive case reminder

**During exam:**
- [ ] Read ALL questions first, do easiest first
- [ ] For code: write algorithm in comments first
- [ ] Check edge cases: empty, single element, boundaries
- [ ] Check syntax: `<T>` before method return type, loop bounds, null checks
- [ ] Leave time to review answers

---

**GOOD LUCK! You've got this! 🚀**
