# CSCI316 Midterm - Quick Reference & Formulas
## One-Page Cheat Sheet for Fast Review

> **Print this before exam** - Essential formulas, patterns, and gotchas

---

## COMPLEXITY ANALYSIS

### Big-O Classes (Fastest to Slowest)
```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
```

### Common Loop Patterns

| Pattern | Complexity | Example |
|---------|------------|---------|
| Single loop | O(n) | `for (int i = 0; i < n; i++)` |
| Nested loop (square) | O(n²) | `for (i...) for (j...)` |
| Nested loop (triangular) | O(n²) | `for (i...) for (j=0; j<i; j++)` |
| Halving loop | O(log n) | `for (i=n; i>0; i/=2)` |
| Doubling loop | O(log n) | `for (i=1; i<n; i*=2)` |
| Two sequential loops | O(n) | `for(...) then for(...)` → O(n+n) = O(n) |
| Loop × inner log | O(n log n) | Outer O(n), inner halves each time |

### Recursion Complexity

| Pattern | Time | Space (Stack) | Example |
|---------|------|---------------|---------|
| Linear recursion (1 call) | O(n) | O(n) | factorial(n-1) |
| Binary recursion (2 calls, covers all) | O(n) | O(log n) | binarySum splits array |
| Binary recursion (2 calls, redundant) | O(2ⁿ) | O(n) | naive fibonacci |
| Divide by half (1 call) | O(log n) | O(log n) | binarySearch |

**Master Theorem Shortcut:**
- T(n) = T(n/2) + O(1) → O(log n)
- T(n) = T(n/2) + O(n) → O(n)
- T(n) = 2T(n/2) + O(1) → O(n)
- T(n) = 2T(n/2) + O(n) → O(n log n)

---

## RECURSION - Required Functions

### 1. Factorial
```java
int factorial(int n) {
    if (n <= 1) return 1;          // Base case
    return n * factorial(n - 1);   // O(n) time, O(n) space
}
```

### 2. LinearSum
```java
int linearSum(int[] arr, int n) {
    if (n <= 0) return 0;
    return arr[n-1] + linearSum(arr, n-1);  // O(n) time, O(n) space
}
```

### 3. ReverseArray
```java
void reverseArray(int[] arr, int low, int high) {
    if (low >= high) return;              // Base case
    swap(arr, low, high);
    reverseArray(arr, low + 1, high - 1); // O(n/2) time, O(n) space
}
```

### 4. BinarySearch
```java
int binarySearch(int[] arr, int target, int low, int high) {
    if (low > high) return -1;            // Not found
    int mid = low + (high - low) / 2;
    if (arr[mid] == target) return mid;
    if (arr[mid] > target) return binarySearch(arr, target, low, mid-1);
    return binarySearch(arr, target, mid+1, high);
    // O(log n) time, O(log n) space
}
```

### 5. BinarySum
```java
int binarySum(int[] arr, int low, int high) {
    if (low > high) return 0;
    if (low == high) return arr[low];
    int mid = (low + high) / 2;
    return binarySum(arr, low, mid) + binarySum(arr, mid+1, high);
    // O(n) time, O(log n) space - visits all elements but log depth
}
```

### 6. BinaryFib (with memoization)
```java
int binaryFib(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];
    memo[n] = binaryFib(n-1, memo) + binaryFib(n-2, memo);
    return memo[n];
    // With memo: O(n) time, O(n) space
    // Without: O(2^n) time!
}
```

### 7. LinearFibonacci (naive - for exam understanding)
```java
int linearFibonacci(int n) {
    if (n <= 1) return n;
    return linearFibonacci(n-1) + linearFibonacci(n-2);
    // O(2^n) time, O(n) space - VERY SLOW, know why!
}
```

**KEY EXAM POINT:** BinaryFib and LinearFibonacci are both O(2ⁿ) without memoization because of redundant recursive calls!

---

## ARRAY OPERATIONS

### Shift Operations (CRITICAL FOR EXAM)

**Shift Right (for insertion):**
```java
// Work BACKWARDS to avoid overwriting
for (int i = size; i > index; i--)
    arr[i] = arr[i-1];
arr[index] = newValue;
```

**Shift Left (for removal):**
```java
// Work FORWARDS to fill gap
for (int i = index; i < size-1; i++)
    arr[i] = arr[i+1];
arr[size-1] = null;  // Clear last
```

**Why direction matters:** Prevents overwriting uncopied data!

### 2D Array Traversal
```java
// Row-major (row by row)
for (int i = 0; i < rows; i++)
    for (int j = 0; j < cols; j++)
        process(matrix[i][j]);

// Column-major (column by column)
for (int j = 0; j < cols; j++)
    for (int i = 0; i < rows; i++)
        process(matrix[i][j]);
```

---

## LINKED LIST OPERATIONS

### Singly Linked List

| Operation | Time | Code Pattern |
|-----------|------|--------------|
| addFirst | O(1) | `new.next = head; head = new` |
| addLast | O(n) | Traverse to end, `last.next = new` |
| removeFirst | O(1) | `data = head.data; head = head.next` |
| removeLast | O(n) | Find second-last, `secondLast.next = null` |
| get(i) | O(n) | Traverse i nodes |

**KEY PATTERN - Insert at index i:**
```java
// Traverse to node at index i-1 (previous node)
Node prev = head;
for (int k = 0; k < i-1; k++)
    prev = prev.next;

// Insert after prev
new.next = prev.next;
prev.next = new;
```

### Doubly Linked List (with Guards)

| Operation | Time | Key Advantage |
|-----------|------|---------------|
| addFirst | O(1) | Via header.next |
| addLast | O(1) | **Via trailer.prev** (vs O(n) in singly!) |
| removeFirst | O(1) | Via header.next |
| removeLast | O(1) | **Via trailer.prev** (vs O(n) in singly!) |

**KEY PATTERN - Insert between two nodes:**
```java
// Update 4 pointers (order matters!)
new.next = successor;
new.prev = predecessor;
predecessor.next = new;
successor.prev = new;
```

**Guards/Sentinels:**
- Empty list: `header.next == trailer`, `trailer.prev == header`
- Never null! Always have header ↔ trailer
- Size counts real nodes only (exclude guards)

### Circular Linked List

**Key Points:**
- Tail.next points to head (no null!)
- Store `tail` reference (not head) for O(1) both ends
- Traversal uses counter, not null check

```java
// Add first
new.next = tail.next;  // Point to current head
tail.next = new;       // Head is now new node

// Add last
addFirst(data);        // Add as first
tail = tail.next;      // Move tail forward
```

---

## STACK OPERATIONS

### Stack Basics
- **LIFO:** Last In, First Out
- **Core ops:** push, pop, peek - all O(1)
- **Implementations:** Array (fixed/resizable), Linked List

### Array Stack
```java
// Push
if (size == capacity) throw StackFullException();
arr[++top] = element;  // Increment top, then add

// Pop
if (isEmpty()) throw StackEmptyException();
element = arr[top];
arr[top--] = null;     // Clear, then decrement
```

### Linked Stack
```java
// Push (add at head)
new.next = top;
top = new;

// Pop (remove from head)
element = top.data;
top = top.next;
```

### Parenthesis Matching Algorithm
```
for each character ch:
    if ch is opening bracket:
        push(ch)
    if ch is closing bracket:
        if stack.isEmpty():
            return false  // Unmatched closing
        if !matches(pop(), ch):
            return false  // Mismatched pair
return stack.isEmpty()  // Must be empty at end!
```

### Expression Evaluation (Infix)

**Two stacks:** operands and operators

```
for each token:
    if digit:
        push to operands
    if '(':
        push to operators
    if ')':
        while top != '(':
            apply operator
        pop '('
    if operator:
        while precedence(top) >= precedence(current):
            apply operator
        push current operator
```

**Operator Precedence:**
- `*`, `/` → 2
- `+`, `-` → 1
- `(` → 0

---

## GENERICS

### Syntax Quick Reference
```java
// Generic class
class Box<T> { private T content; }

// Generic method (note <T> before return type!)
public <T> void method(T param) { }

// Upper bound
<T extends Number>
List<? extends Number> list;  // Read-only producer

// Lower bound (wildcards only!)
List<? super Integer> list;   // Write-only consumer

// Multiple bounds
<T extends Class & Interface1 & Interface2>
```

**PECS Rule:**
- **P**roducer **E**xtends: `List<? extends T>` - read from
- **C**onsumer **S**uper: `List<? super T>` - write to

**Cannot do:**
- `new T[10]` - cannot create generic array
- `new T()` - cannot instantiate type parameter
- Use primitives: `Box<int>` ❌ → use `Box<Integer>` ✅

---

## EXAM GOTCHAS & COMMON MISTAKES

### Off-by-One Errors
```java
// ❌ WRONG
for (int i = 0; i <= arr.length; i++)  // Goes one past!

// ✅ CORRECT
for (int i = 0; i < arr.length; i++)
```

### Integer Overflow in Binary Search
```java
// ❌ WRONG (can overflow for large values)
int mid = (left + right) / 2;

// ✅ CORRECT
int mid = left + (right - left) / 2;
```

### Forgetting to Check Empty
```java
// Always check before pop/peek/remove!
if (stack.isEmpty()) throw new EmptyStackException();
if (head == null) throw new NoSuchElementException();
```

### Generic Method Syntax
```java
// ❌ WRONG - missing <T>
public void print(T value) { }

// ✅ CORRECT - <T> before return type
public <T> void print(T value) { }
```

### Shift Direction
```java
// Shift right → backwards loop
for (i = size; i > index; i--)

// Shift left → forwards loop
for (i = index; i < size-1; i++)
```

### Stack Matching Final Check
```java
// ❌ WRONG - missing final check
return true;  // Forgets to check if stack empty

// ✅ CORRECT
return stack.isEmpty();  // Must be empty for balanced!
```

---

## QUICK COMPLEXITY LOOKUP

| Data Structure | Operation | Time |
|----------------|-----------|------|
| Array | Access | O(1) |
| Array | Insert/Delete | O(n) |
| Singly LL | addFirst | O(1) |
| Singly LL | addLast | O(n) |
| Doubly LL (guards) | addLast | O(1) |
| Doubly LL (guards) | removeLast | O(1) |
| Circular LL | addFirst/Last | O(1) |
| Stack | push/pop/peek | O(1) |
| Binary Search | Search | O(log n) |
| Merge Sort | Sort | O(n log n) |
| Bubble/Selection Sort | Sort | O(n²) |

---

## PRE-EXAM CHECKLIST

**Before the exam, can you:**

### Generics
- [ ] Create generic class with type parameter `<T>`
- [ ] Write generic method with `<T>` before return type
- [ ] Use `extends` for upper bound
- [ ] Use `super` for lower bound (wildcards only)
- [ ] Apply PECS rule

### Arrays
- [ ] Shift right backwards: `for (i=size; i>index; i--)`
- [ ] Shift left forwards: `for (i=index; i<size-1; i++)`
- [ ] Implement Game Entry insertion/removal
- [ ] Traverse 2D array row-major and column-major

### Linked Lists
- [ ] Implement singly LL: add/remove at first/last/index
- [ ] Implement doubly LL with guards
- [ ] Know guard advantage: O(1) addLast/removeLast
- [ ] Implement circular LL with tail reference
- [ ] Update both prev and next in doubly LL

### Complexity
- [ ] Analyze single/nested loops
- [ ] Analyze recursion (linear, binary, exponential)
- [ ] Apply Master Theorem shortcuts
- [ ] Calculate space complexity (stack depth)

### Recursion
- [ ] Write all 7 required functions from memory
- [ ] Identify base case and recursive case
- [ ] Know complexity of each function
- [ ] Explain why naive Fib is O(2^n)

### Stacks
- [ ] Implement stack with array and linked list
- [ ] Solve parenthesis matching
- [ ] Evaluate infix expression (two stacks)
- [ ] Remember to check isEmpty() before pop!

---

**PRINT THIS PAGE BEFORE EXAM - REVIEW NIGHT BEFORE**
