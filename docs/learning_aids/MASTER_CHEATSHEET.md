# 📄 Master Cheat Sheet - Data Structures & Algorithms in Java

## 🔥 PRINT THIS! One-Page Quick Reference

---

## 1️⃣ BIG-O COMPLEXITY CHEAT SHEET

### Common Time Complexities (Best to Worst):
```
O(1)          Constant       Array access, HashMap get/put
O(log n)      Logarithmic    Binary search, balanced tree ops
O(n)          Linear         Array traversal, linear search
O(n log n)    Linearithmic   Merge sort, heap sort, quick sort (avg)
O(n²)         Quadratic      Nested loops, bubble/selection/insertion sort
O(2ⁿ)         Exponential    Recursive fibonacci, subset generation
O(n!)         Factorial      Permutation generation
```

### Rules for Calculating Time Complexity:
1. **Single loop:** O(n)
2. **Nested loops:** Multiply → O(n²)
3. **Consecutive loops:** Add → O(n + m)
4. **Binary division:** O(log n)
5. **Recursion:** Draw recursion tree, count nodes
6. **Amortized:** Average over sequence of operations

### Space Complexity:
- **Input space:** Don't count
- **Auxiliary space:** Extra memory used (count this!)
- **Recursive calls:** O(depth) for call stack

---

## 2️⃣ JAVA COLLECTIONS API QUICK REFERENCE

### ArrayList
```java
ArrayList<E> list = new ArrayList<>();
list.add(e);              // O(1) amortized
list.add(i, e);           // O(n) - shifts elements
list.get(i);              // O(1)
list.set(i, e);           // O(1)
list.remove(i);           // O(n) - shifts elements
list.size();              // O(1)
list.contains(e);         // O(n) - linear search
list.indexOf(e);          // O(n)
list.toArray(new E[0]);   // O(n)
```

### LinkedList
```java
LinkedList<E> list = new LinkedList<>();
list.addFirst(e);         // O(1)
list.addLast(e);          // O(1)
list.getFirst();          // O(1)
list.getLast();           // O(1)
list.removeFirst();       // O(1)
list.removeLast();        // O(1)
list.get(i);              // O(n) - must traverse
```

### Stack
```java
Stack<E> stack = new Stack<>();
stack.push(e);            // O(1)
stack.pop();              // O(1) - removes and returns top
stack.peek();             // O(1) - returns top without removing
stack.isEmpty();          // O(1)
stack.size();             // O(1)
```

### Queue (using LinkedList or ArrayDeque)
```java
Queue<E> queue = new LinkedList<>();
queue.offer(e);           // O(1) - add to rear
queue.poll();             // O(1) - remove from front
queue.peek();             // O(1) - view front without removing
queue.isEmpty();          // O(1)
```

### Deque (Double-Ended Queue)
```java
Deque<E> deque = new ArrayDeque<>();
deque.offerFirst(e);      // O(1)
deque.offerLast(e);       // O(1)
deque.pollFirst();        // O(1)
deque.pollLast();         // O(1)
```

### HashMap
```java
HashMap<K,V> map = new HashMap<>();
map.put(k, v);            // O(1) average
map.get(k);               // O(1) average
map.remove(k);            // O(1) average
map.containsKey(k);       // O(1) average
map.keySet();             // Returns Set<K>
map.values();             // Returns Collection<V>
map.entrySet();           // Returns Set<Map.Entry<K,V>>

// Iteration
for (Map.Entry<K,V> entry : map.entrySet()) {
    K key = entry.getKey();
    V value = entry.getValue();
}
```

### HashSet
```java
HashSet<E> set = new HashSet<>();
set.add(e);               // O(1) average
set.remove(e);            // O(1) average
set.contains(e);          // O(1) average
set.size();               // O(1)
```

### TreeMap (Sorted Map)
```java
TreeMap<K,V> map = new TreeMap<>();
map.put(k, v);            // O(log n)
map.get(k);               // O(log n)
map.firstKey();           // O(log n)
map.lastKey();            // O(log n)
```

### PriorityQueue (Min Heap by default)
```java
PriorityQueue<E> pq = new PriorityQueue<>();
PriorityQueue<E> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

pq.offer(e);              // O(log n)
pq.poll();                // O(log n) - removes min
pq.peek();                // O(1) - view min
```

---

## 3️⃣ DATA STRUCTURE COMPARISON

| Structure | Access | Search | Insert | Delete | Space | Notes |
|-----------|--------|--------|--------|--------|-------|-------|
| **Array** | O(1) | O(n) | N/A | N/A | O(n) | Fixed size |
| **ArrayList** | O(1) | O(n) | O(n) | O(n) | O(n) | Dynamic, O(1) add at end |
| **LinkedList** | O(n) | O(n) | O(1)* | O(1)* | O(n) | *If have reference to node |
| **Stack** | O(n) | O(n) | O(1) | O(1) | O(n) | LIFO |
| **Queue** | O(n) | O(n) | O(1) | O(1) | O(n) | FIFO |
| **HashMap** | N/A | O(1)† | O(1)† | O(1)† | O(n) | †Average case |
| **TreeMap** | N/A | O(log n) | O(log n) | O(log n) | O(n) | Sorted keys |
| **Heap** | O(1)‡ | O(n) | O(log n) | O(log n) | O(n) | ‡Only min/max |
| **BST** | O(log n)† | O(log n)† | O(log n)† | O(log n)† | O(n) | †If balanced |

---

## 4️⃣ SORTING ALGORITHMS

| Algorithm | Best | Average | Worst | Space | Stable? | Notes |
|-----------|------|---------|-------|-------|---------|-------|
| **Bubble** | O(n) | O(n²) | O(n²) | O(1) | Yes | Never use |
| **Selection** | O(n²) | O(n²) | O(n²) | O(1) | No | Simple but slow |
| **Insertion** | O(n) | O(n²) | O(n²) | O(1) | Yes | Good for small/nearly sorted |
| **Merge** | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | Guaranteed performance |
| **Quick** | O(n log n) | O(n log n) | O(n²) | O(log n) | No | Average case optimal |
| **Heap** | O(n log n) | O(n log n) | O(n log n) | O(1) | No | In-place |

**Stable:** Preserves relative order of equal elements

---

## 5️⃣ TREE TRAVERSALS

```java
// Inorder (Left, Root, Right) → Sorted for BST
void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    visit(root);
    inorder(root.right);
}

// Preorder (Root, Left, Right) → Copy tree
void preorder(TreeNode root) {
    if (root == null) return;
    visit(root);
    preorder(root.left);
    preorder(root.right);
}

// Postorder (Left, Right, Root) → Delete tree
void postorder(TreeNode root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    visit(root);
}

// Level-order (BFS)
void levelOrder(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        visit(node);
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
}
```

**Mnemonic:** IN-order → IN sorted order (for BST)

---

## 6️⃣ GRAPH ALGORITHMS

### BFS (Breadth-First Search) - Uses Queue
```java
void bfs(Node start) {
    Queue<Node> q = new LinkedList<>();
    Set<Node> visited = new HashSet<>();
    q.offer(start);
    visited.add(start);

    while (!q.isEmpty()) {
        Node node = q.poll();
        visit(node);

        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                q.offer(neighbor);
            }
        }
    }
}
```
**Use for:** Shortest path (unweighted), level-order traversal

### DFS (Depth-First Search) - Uses Stack/Recursion
```java
void dfs(Node node, Set<Node> visited) {
    visited.add(node);
    visit(node);

    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited);
        }
    }
}
```
**Use for:** Cycle detection, topological sort, connected components

---

## 7️⃣ RECURSION TEMPLATE

```java
returnType recursiveFunction(parameters) {
    // 1. BASE CASE (stopping condition)
    if (baseCondition) {
        return baseValue;
    }

    // 2. RECURSIVE CASE
    // - Reduce problem size
    // - Make recursive call with smaller problem
    // - Combine results
    return recursiveFunction(smallerProblem);
}
```

**Key Points:**
- Always have base case first!
- Problem must get smaller each call
- Trust the recursion (don't trace in your head)

---

## 8️⃣ COMMON PATTERNS & TECHNIQUES

### Two Pointers (Arrays)
```java
// Opposite ends
int left = 0, right = arr.length - 1;
while (left < right) {
    // Process
    left++;
    right--;
}

// Same direction (fast/slow)
int slow = 0, fast = 0;
while (fast < arr.length) {
    // Process
    slow++;
    fast++;
}
```

### Sliding Window
```java
int left = 0;
for (int right = 0; right < arr.length; right++) {
    // Expand window: add arr[right]
    while (/* window invalid */) {
        // Shrink window: remove arr[left]
        left++;
    }
    // Process valid window [left, right]
}
```

### Binary Search
```java
int left = 0, right = arr.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2; // Avoid overflow!
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) left = mid + 1;
    else right = mid - 1;
}
return -1; // Not found
```

### Fast/Slow Pointers (Linked List)
```java
Node slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true; // Cycle detected
}
// slow is at middle when fast reaches end
```

---

## 9️⃣ EDGE CASES TO ALWAYS TEST

1. **Null input:** `arr == null`, `node == null`
2. **Empty:** `arr.length == 0`, `list.isEmpty()`
3. **Single element:** `arr.length == 1`
4. **Two elements:** Often edge case for algorithms
5. **All same values:** `[5, 5, 5, 5]`
6. **Already sorted:** `[1, 2, 3, 4]`
7. **Reverse sorted:** `[4, 3, 2, 1]`
8. **Duplicates:** `[1, 2, 2, 3]`
9. **Negative numbers:** `[-5, -1, 0, 3]`
10. **Large values:** Test for integer overflow

---

## 🔟 EXAM TIPS & TRICKS

### Time Management:
1. **Read all questions first** (2 min)
2. **Do easy questions first** (build time bank)
3. **Leave 5 min to review** for off-by-one, null checks

### Coding Questions:
1. **Write pseudocode first** (shows logic even if code wrong)
2. **Think out loud** (partial credit!)
3. **Test with small example** (trace through)
4. **Check edge cases** before submitting

### Common Mistakes to Avoid:
- ❌ `arr.length()` → ✅ `arr.length` (no parentheses!)
- ❌ `list.length` → ✅ `list.size()` (method, not field!)
- ❌ `(left + right) / 2` → ✅ `left + (right - left) / 2` (avoid overflow)
- ❌ `while (left < right)` for binary search → ✅ `while (left <= right)`
- ❌ Forgot to check null → ✅ `if (arr == null) return;`

### Complexity Analysis:
- Count loops: 1 loop = O(n), 2 nested = O(n²)
- Binary division = O(log n)
- Recursion tree: count nodes
- Amortized: ArrayList add is O(1) amortized (not worst-case)

---

## 🎯 MEMORIZE THESE!

**Array:** Fixed size, O(1) access, `.length`
**ArrayList:** Dynamic, O(1) access, `.size()`
**LinkedList:** O(1) insert/delete at ends, O(n) access
**Stack:** LIFO, `push()`, `pop()`, `peek()`
**Queue:** FIFO, `offer()`, `poll()`, `peek()`
**HashMap:** O(1) average get/put, unordered
**TreeMap:** O(log n) get/put, sorted by key
**Heap:** O(1) peek min/max, O(log n) insert/delete

**Sorting:**
- Use **Merge Sort** for guaranteed O(n log n)
- Use **Quick Sort** for average O(n log n) in-place
- **Insertion Sort** good for small/nearly sorted

**Searching:**
- **Linear Search:** O(n), works on unsorted
- **Binary Search:** O(log n), MUST be sorted!

**Tree:**
- **BST:** Left < Root < Right
- **Balanced:** Height = O(log n)
- **Inorder traversal of BST = sorted!**

---

**GOOD LUCK! 🍀**

**Remember:** Stay calm, read carefully, test edge cases, and believe in yourself!
