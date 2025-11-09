# CSCI316 Midterm Prep - Part 2: Linked Lists, Complexity, Recursion, Stacks

> **Continuation of MIDTERM_PREP_GUIDE.md** - Focus on implementation-heavy topics

---

# 3. LINKED LISTS

## 3.1 Singly Linked List - Complete Implementation

### ① One-Line Concept Summary
A singly linked list is a linear data structure where each node contains data and a single reference (`next`) to the next node, allowing O(1) insertion/deletion at the head but O(n) for arbitrary positions since you must traverse from the beginning.

### ② How to Think About It

**Mental Model:** Chain of treasure hunt clues
- Each node is a clue pointing to the next
- Can only move forward (one-way street)
- Head is the entry point - lose it, lose the list!
- No random access - must follow the chain

**Decision Checklist - Use singly linked list when:**
- ✅ Frequent insertions/deletions at beginning
- ✅ Size unknown or highly variable
- ✅ Don't need random access
- ✅ Memory overhead of `next` pointer acceptable
- ❌ Don't use if need fast access by index
- ❌ Don't use if need to traverse backwards

**Key Invariants:**
- `head == null` means empty list
- Last node has `next == null`
- `size` tracks number of nodes
- Never lose reference to head!

**Failure Modes:**
- Null pointer exceptions if not checking `node == null`
- Losing head reference (memory leak)
- Infinite loops if circular reference created accidentally
- Off-by-one errors in index operations

### ③ Algorithmic Plan

**Node Structure:**
```
class Node {
    int data;
    Node next;
}
```

**Core Operations:**

**1. addFirst (insertHead) - O(1):**
1. Create new node with data
2. Set `newNode.next = head`
3. Set `head = newNode`
4. Increment size

**2. addLast (insertTail) - O(n):**
1. Create new node
2. If empty, set `head = newNode`
3. Else traverse to last node (`while (current.next != null)`)
4. Set `last.next = newNode`
5. Increment size

**3. addAt(index) - O(n):**
1. Validate index (0 ≤ index ≤ size)
2. If index==0, call addFirst
3. Traverse to node at `index-1`
4. Create new node
5. Set `newNode.next = prevNode.next`
6. Set `prevNode.next = newNode`
7. Increment size

**4. removeFirst - O(1):**
1. Check if empty
2. Save `head.data`
3. Set `head = head.next`
4. Decrement size
5. Return saved data

**5. removeLast - O(n):**
1. Check if empty
2. If only one node, set head=null
3. Else traverse to second-last node
4. Save `last.data`
5. Set `secondLast.next = null`
6. Decrement size
7. Return saved data

**6. removeAt(index) - O(n):**
1. Validate index
2. If index==0, call removeFirst
3. Traverse to node at `index-1`
4. Save `nodeToRemove = prevNode.next`
5. Set `prevNode.next = nodeToRemove.next`
6. Decrement size
7. Return removed data

**7. get(index) - O(n):**
1. Validate index
2. Traverse to node at `index`
3. Return `node.data`

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| addFirst | O(1) | O(1) | Direct head manipulation |
| addLast | O(n) | O(1) | Must traverse to end |
| addAt(i) | O(n) | O(1) | Must traverse to index |
| removeFirst | O(1) | O(1) | Direct head manipulation |
| removeLast | O(n) | O(1) | Must find second-last |
| removeAt(i) | O(n) | O(1) | Must traverse to index |
| get(i) | O(n) | O(1) | Must traverse to index |
| size() | O(1) | O(1) | Cached in field |

**Space:** O(n) for n nodes, each with data + next pointer

### ⑤ Edge Cases & Pitfalls

**Pitfall 1: Null pointer when empty list**
```java
// ❌ WRONG - NPE if head is null
Node last = head;
while (last.next != null) last = last.next;

// ✅ CORRECT - check head first
if (head == null) throw new NoSuchElementException();
```

**Pitfall 2: Losing head reference**
```java
// ❌ WRONG - permanently lost head!
head = head.next;  // Without saving old head data

// ✅ CORRECT - save data before moving head
int data = head.data;
head = head.next;
return data;
```

**Pitfall 3: Off-by-one in traversal**
```java
// To insert at index 2, traverse to index 1 (previous node)
Node prev = head;
for (int i = 0; i < index - 1; i++)  // index-1, not index!
    prev = prev.next;
```

**Edge Cases to Test:**
- Empty list (size=0, head=null)
- Single element (size=1, head.next=null)
- Operations at beginning (index=0)
- Operations at end (index=size or size-1)
- Operations in middle
- Invalid indices (negative, ≥ size)

### ⑥ Final Java 17 Implementation

```java
import java.util.NoSuchElementException;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Add at beginning - O(1)
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add at end - O(n)
    public void addLast(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add at index - O(n)
    public void addAt(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    // Remove first - O(1)
    public int removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        int data = head.data;
        head = head.next;
        size--;
        return data;
    }

    // Remove last - O(n)
    public int removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        if (head.next == null) {
            int data = head.data;
            head = null;
            size--;
            return data;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        int data = current.next.data;
        current.next = null;
        size--;
        return data;
    }

    // Remove at index - O(n)
    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        }

        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        int data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return data;
    }

    // Get element at index - O(n)
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        if (head == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Test 1: Add first
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        assert list.toString().equals("[30 -> 20 -> 10]");
        System.out.println("After addFirst: " + list);

        // Test 2: Add last
        list.addLast(40);
        list.addLast(50);
        assert list.size() == 5;
        assert list.get(4) == 50;
        System.out.println("After addLast: " + list);

        // Test 3: Add at index
        list.addAt(2, 25);
        assert list.get(2) == 25;
        assert list.size() == 6;
        System.out.println("After addAt(2, 25): " + list);

        // Test 4: Remove first
        int removed = list.removeFirst();
        assert removed == 30;
        assert list.size() == 5;
        System.out.println("After removeFirst: " + list);

        // Test 5: Remove last
        removed = list.removeLast();
        assert removed == 50;
        System.out.println("After removeLast: " + list);

        // Test 6: Remove at index
        removed = list.removeAt(1);
        assert removed == 20;
        System.out.println("After removeAt(1): " + list);

        // Test 7: Get
        assert list.get(0) == 25;
        assert list.get(2) == 40;

        // Test 8: Edge case - empty list
        SinglyLinkedList empty = new SinglyLinkedList();
        assert empty.isEmpty();
        try {
            empty.removeFirst();
            assert false : "Should throw exception";
        } catch (NoSuchElementException e) {
            // Expected
        }

        System.out.println("✅ All singly linked list tests passed!");
    }
}
```

**Expected Output:**
```
After addFirst: [30 -> 20 -> 10]
After addLast: [30 -> 20 -> 10 -> 40 -> 50]
After addAt(2, 25): [30 -> 20 -> 25 -> 10 -> 40 -> 50]
After removeFirst: [20 -> 25 -> 10 -> 40 -> 50]
After removeLast: [20 -> 25 -> 10 -> 40]
After removeAt(1): [20 -> 10 -> 40]
✅ All singly linked list tests passed!
```

### ⑦ Exam Checklist

- ✅ **Node structure:** `data` + `next` pointer
- ✅ **addFirst:** O(1) - manipulate head directly
- ✅ **addLast:** O(n) - must traverse to end
- ✅ **addAt:** O(n) - traverse to index-1, adjust pointers
- ✅ **removeFirst:** O(1) - move head forward
- ✅ **removeLast:** O(n) - find second-last node
- ✅ **Pointer order:** Set `new.next` first, then `prev.next = new`
- ✅ **Edge cases:** Empty list, single element, boundaries

---

## 3.2 Doubly Linked List (with Guards/Sentinels)

### ① One-Line Concept Summary
A doubly linked list has nodes with both `next` and `prev` pointers, enabling bidirectional traversal; using sentinel/guard nodes (dummy header and trailer) eliminates special cases for empty lists and boundary operations.

### ② How to Think About It

**Mental Model:** Two-way street with fixed landmarks
- Each node points forward AND backward
- Sentinels = permanent street signs at beginning/end
- Never remove sentinels - they mark boundaries
- Eliminates null checks for head/tail

**Without Guards:**
- head and tail can be null (special cases!)
- Must check null before accessing prev/next

**With Guards (Sentinels):**
- header.next = first real node (or trailer if empty)
- trailer.prev = last real node (or header if empty)
- Never null! Always have header ↔ trailer
- Simplifies insertion/deletion logic

**Decision Checklist:**
- ✅ Use doubly linked when need bidirectional traversal
- ✅ Use guards to simplify boundary logic (exam favorite!)
- ✅ Better than singly for removing arbitrary nodes (don't need previous)
- ❌ Higher memory overhead (2 pointers per node + sentinels)

**Key Invariants with Guards:**
- header.prev = null always
- trailer.next = null always
- Empty list: header.next = trailer, trailer.prev = header
- Size counts real nodes only (excludes sentinels)
- Every node has non-null prev and next (thanks to guards)

### ③ Algorithmic Plan

**Node Structure:**
```
class Node {
    int data;
    Node prev;
    Node next;
}
```

**Initialization with Guards:**
1. Create header sentinel
2. Create trailer sentinel
3. Set header.next = trailer
4. Set trailer.prev = header
5. Set size = 0

**Core Operations (with Guards):**

**1. addFirst - O(1):**
1. Create new node
2. Set `new.next = header.next`
3. Set `new.prev = header`
4. Set `header.next.prev = new`
5. Set `header.next = new`
6. Increment size

**2. addLast - O(1):**  (Not O(n) like singly! We have trailer!)
1. Create new node
2. Set `new.prev = trailer.prev`
3. Set `new.next = trailer`
4. Set `trailer.prev.next = new`
5. Set `trailer.prev = new`
6. Increment size

**3. addAt(index) - O(n):**
1. Validate index
2. Traverse to node at index (or index-1)
3. Insert before that node using addBefore helper

**addBefore(node, data) - O(1):** (Helper for insertion)
1. Create new node
2. Set `new.next = node`
3. Set `new.prev = node.prev`
4. Set `node.prev.next = new`
5. Set `node.prev = new`
6. Increment size

**4. removeFirst - O(1):**
1. Check if empty (header.next == trailer)
2. Save `first = header.next`
3. Set `header.next = first.next`
4. Set `first.next.prev = header`
5. Decrement size
6. Return first.data

**5. removeLast - O(1):**  (O(1)! Not O(n) like singly!)
1. Check if empty
2. Save `last = trailer.prev`
3. Set `trailer.prev = last.prev`
4. Set `last.prev.next = trailer`
5. Decrement size
6. Return last.data

**6. remove(node) - O(1):**  (If you have node reference!)
1. Set `node.prev.next = node.next`
2. Set `node.next.prev = node.prev`
3. Decrement size

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| addFirst | O(1) | O(1) | Direct header manipulation |
| addLast | O(1) | O(1) | Direct trailer manipulation (!) |
| addAt(i) | O(n) | O(1) | Must traverse to index |
| removeFirst | O(1) | O(1) | Direct header manipulation |
| removeLast | O(1) | O(1) | Direct trailer manipulation (!) |
| remove(node) | O(1) | O(1) | If have node reference |
| get(i) | O(n) | O(1) | Must traverse |

**Key Advantage:** addLast and removeLast are O(1) (vs O(n) in singly linked)

**Space:** O(n) but ~50% more per node than singly (prev + next vs just next)

### ⑤ Edge Cases & Pitfalls

**Pitfall 1: Forgetting to update both prev and next**
```java
// ❌ WRONG - only updated next pointers
new.next = header.next;
header.next = new;

// ✅ CORRECT - update both directions
new.next = header.next;
new.prev = header;
header.next.prev = new;  // Update backward link!
header.next = new;
```

**Pitfall 2: Wrong order of pointer updates**
```java
// ❌ WRONG - loses reference before using it
header.next = new;
new.next = header.next;  // new.next points to itself!

// ✅ CORRECT - read old values before overwriting
new.next = header.next;  // Read old header.next first
header.next = new;       // Then update
```

**Pitfall 3: Including sentinels in size**
```java
// Size should count ONLY real nodes, not header/trailer
```

**Edge Cases:**
- Empty list (header.next == trailer)
- Single element (header.next.next == trailer)
- Removing last element
- Adding to empty list

### ⑥ Final Java 17 Implementation

```java
import java.util.NoSuchElementException;

class DNode {
    int data;
    DNode prev;
    DNode next;

    DNode(int data) {
        this.data = data;
    }

    // Constructor for sentinels (no data)
    DNode() { }
}

public class DoublyLinkedListWithGuards {
    private DNode header;   // Sentinel at beginning
    private DNode trailer;  // Sentinel at end
    private int size;

    public DoublyLinkedListWithGuards() {
        header = new DNode();
        trailer = new DNode();
        header.next = trailer;
        trailer.prev = header;
        size = 0;
    }

    // Add at beginning - O(1)
    public void addFirst(int data) {
        addBetween(data, header, header.next);
    }

    // Add at end - O(1) [KEY ADVANTAGE over singly linked!]
    public void addLast(int data) {
        addBetween(data, trailer.prev, trailer);
    }

    // Add at index - O(n)
    public void addAt(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        DNode current = header.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        addBetween(data, current.prev, current);
    }

    // Helper: Add between two nodes - O(1)
    private void addBetween(int data, DNode predecessor, DNode successor) {
        DNode newNode = new DNode(data);
        newNode.prev = predecessor;
        newNode.next = successor;
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    // Remove first - O(1)
    public int removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return remove(header.next);
    }

    // Remove last - O(1) [KEY ADVANTAGE!]
    public int removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return remove(trailer.prev);
    }

    // Remove at index - O(n)
    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        DNode current = header.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return remove(current);
    }

    // Helper: Remove specific node - O(1)
    private int remove(DNode node) {
        DNode predecessor = node.prev;
        DNode successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        return node.data;
    }

    // Get element at index - O(n)
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        DNode current = header.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        DNode current = header.next;
        while (current != trailer) {
            sb.append(current.data);
            if (current.next != trailer) sb.append(" <-> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedListWithGuards list = new DoublyLinkedListWithGuards();

        // Test 1: Add first
        list.addFirst(20);
        list.addFirst(10);
        assert list.toString().equals("[10 <-> 20]");
        System.out.println("After addFirst: " + list);

        // Test 2: Add last (O(1) - KEY FEATURE!)
        list.addLast(30);
        list.addLast(40);
        assert list.size() == 4;
        System.out.println("After addLast: " + list);

        // Test 3: Add at index
        list.addAt(2, 25);
        assert list.get(2) == 25;
        System.out.println("After addAt(2, 25): " + list);

        // Test 4: Remove first
        int removed = list.removeFirst();
        assert removed == 10;
        System.out.println("After removeFirst: " + list);

        // Test 5: Remove last (O(1) - KEY FEATURE!)
        removed = list.removeLast();
        assert removed == 40;
        System.out.println("After removeLast: " + list);

        // Test 6: Remove at index
        removed = list.removeAt(1);
        assert removed == 25;
        System.out.println("After removeAt(1): " + list);

        // Test 7: Empty list operations
        while (!list.isEmpty()) {
            list.removeFirst();
        }
        assert list.isEmpty();
        assert list.size() == 0;
        System.out.println("After clearing: " + list);

        // Test 8: Add to empty list
        list.addFirst(100);
        assert list.size() == 1;
        assert list.get(0) == 100;
        System.out.println("After adding to empty: " + list);

        System.out.println("✅ All doubly linked list (with guards) tests passed!");
    }
}
```

**Expected Output:**
```
After addFirst: [10 <-> 20]
After addLast: [10 <-> 20 <-> 30 <-> 40]
After addAt(2, 25): [10 <-> 20 <-> 25 <-> 30 <-> 40]
After removeFirst: [20 <-> 25 <-> 30 <-> 40]
After removeLast: [20 <-> 25 <-> 30]
After removeAt(1): [20 <-> 30]
After clearing: []
After adding to empty: [100]
✅ All doubly linked list (with guards) tests passed!
```

### ⑦ Exam Checklist

- ✅ **Node structure:** `data`, `prev`, `next` (3 fields)
- ✅ **Guards/Sentinels:** header and trailer dummy nodes
- ✅ **Empty list:** `header.next == trailer`, `trailer.prev == header`
- ✅ **KEY ADVANTAGE:** addLast and removeLast are **O(1)** (not O(n)!)
- ✅ **Update both:** Always update both `prev` and `next` pointers
- ✅ **Order matters:** Read old values before overwriting
- ✅ **Size:** Count real nodes only (exclude sentinels)
- ✅ **No null checks:** Guards eliminate boundary null checks

---

## 3.3 Circular Linked List

### ① One-Line Concept Summary
A circular linked list has its tail's `next` pointer pointing back to the head (forming a loop), requiring a `tail` reference for efficiency and special care to avoid infinite loops during traversal.

### ② How to Think About It

**Mental Model:** Circular race track
- No real "end" - last node points back to first
- Use `tail` reference (not head) for O(1) access to both ends
- `tail.next` is the head!
- Must use counter or sentinel to stop traversal (no null to detect)

**Why store tail instead of head?**
- `tail.next` = head → O(1) access to first element
- `tail` = direct access to last element
- Both ends accessible in O(1)!

**Decision Checklist:**
- ✅ Use when need efficient access to both ends
- ✅ Use for round-robin scheduling
- ✅ Use for circular buffers
- ⚠️ Must be careful with traversal (no null terminator!)

**Key Invariants:**
- If not empty: `tail.next` points to head
- If single element: `tail.next == tail` (points to itself)
- Empty list: `tail == null`

**Failure Modes:**
- Infinite loops if not counting iterations
- Breaking the circular link accidentally

### ③ Algorithmic Plan

**Initialization:**
- `tail = null`
- `size = 0`

**Core Operations:**

**1. addFirst - O(1):**
1. Create new node
2. If empty: `tail = new`, `tail.next = tail`
3. Else:
   - Set `new.next = tail.next` (current head)
   - Set `tail.next = new`
4. Increment size

**2. addLast - O(1):**
1. addFirst(data)  // Add as first
2. tail = tail.next  // Move tail forward
3. (Size already incremented)

**3. removeFirst - O(1):**
1. Check if empty
2. Save `head = tail.next`
3. If single element: `tail = null`
4. Else: `tail.next = head.next`
5. Decrement size
6. Return head.data

**4. Traversal pattern:**
```
if (tail == null) return;  // Empty

Node current = tail.next;  // Start at head
int count = 0;
do {
    // Process current
    current = current.next;
    count++;
} while (count < size);  // Use counter, not null check!
```

### ④ Complexity Analysis

| Operation | Time | Space | Explanation |
|-----------|------|-------|-------------|
| addFirst | O(1) | O(1) | Via tail.next |
| addLast | O(1) | O(1) | Move tail forward |
| removeFirst | O(1) | O(1) | Via tail.next |
| removeLast | O(n) | O(1) | Must find second-last |
| get(i) | O(n) | O(1) | Traverse i nodes |

**Space:** O(n) same as singly linked

### ⑤ Edge Cases & Pitfalls

**Pitfall 1: Infinite loop in traversal**
```java
// ❌ WRONG - infinite loop! (no null to stop)
Node current = tail.next;
while (current != null) {  // Never null in circular!
    current = current.next;
}

// ✅ CORRECT - use counter
Node current = tail.next;
for (int i = 0; i < size; i++) {
    // Process current
    current = current.next;
}
```

**Pitfall 2: Single element self-loop**
```java
// When size == 1, tail.next == tail (points to itself)
// Must handle this in remove operations
```

**Edge Cases:**
- Empty list (tail == null)
- Single element (tail.next == tail)
- Breaking/restoring circularity

### ⑥ Final Java 17 Implementation

```java
import java.util.NoSuchElementException;

class CNode {
    int data;
    CNode next;

    CNode(int data) {
        this.data = data;
    }
}

public class CircularLinkedList {
    private CNode tail;  // Points to last node (tail.next = head)
    private int size;

    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    // Add at beginning - O(1)
    public void addFirst(int data) {
        CNode newNode = new CNode(data);

        if (tail == null) {
            // Empty list - node points to itself
            tail = newNode;
            tail.next = tail;
        } else {
            // Insert before head (which is tail.next)
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    // Add at end - O(1)
    public void addLast(int data) {
        addFirst(data);     // Add as first
        tail = tail.next;   // Move tail forward to new node
    }

    // Remove first - O(1)
    public int removeFirst() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        CNode head = tail.next;
        int data = head.data;

        if (tail == head) {
            // Single element
            tail = null;
        } else {
            tail.next = head.next;
        }

        size--;
        return data;
    }

    // Get element at index - O(n)
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        CNode current = tail.next;  // Start at head
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Rotate: move head to end (useful for round-robin)
    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return tail == null; }

    @Override
    public String toString() {
        if (tail == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        CNode current = tail.next;  // Start at head
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            if (i < size - 1) sb.append(" -> ");
            current = current.next;
        }
        sb.append(" -> (circular)]");
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        // Test 1: Add first
        list.addFirst(20);
        list.addFirst(10);
        assert list.size() == 2;
        System.out.println("After addFirst: " + list);

        // Test 2: Add last
        list.addLast(30);
        list.addLast(40);
        assert list.size() == 4;
        assert list.get(3) == 40;
        System.out.println("After addLast: " + list);

        // Test 3: Verify circularity (tail.next should be head)
        assert list.tail.next.data == 10;  // Head is 10
        assert list.tail.data == 40;        // Tail is 40

        // Test 4: Remove first
        int removed = list.removeFirst();
        assert removed == 10;
        assert list.size() == 3;
        System.out.println("After removeFirst: " + list);

        // Test 5: Rotate (round-robin)
        list.rotate();  // Moves head to tail
        System.out.println("After rotate: " + list);

        // Test 6: Single element circularity
        CircularLinkedList single = new CircularLinkedList();
        single.addFirst(99);
        assert single.tail.next == single.tail;  // Points to itself
        System.out.println("Single element: " + single);

        single.removeFirst();
        assert single.isEmpty();

        System.out.println("✅ All circular linked list tests passed!");
    }
}
```

**Expected Output:**
```
After addFirst: [10 -> 20 -> (circular)]
After addLast: [10 -> 20 -> 30 -> 40 -> (circular)]
After removeFirst: [20 -> 30 -> 40 -> (circular)]
After rotate: [30 -> 40 -> 20 -> (circular)]
Single element: [99 -> (circular)]
✅ All circular linked list tests passed!
```

### ⑦ Exam Checklist

- ✅ **Structure:** Last node's `next` points to first (circular)
- ✅ **Reference:** Store `tail` (not head) for O(1) both ends
- ✅ **Head access:** `tail.next` is the head
- ✅ **addFirst/addLast:** Both O(1) (key advantage)
- ✅ **Traversal:** Use counter, not null check (no null!)
- ✅ **Single element:** `tail.next == tail` (self-loop)
- ✅ **Use cases:** Round-robin, circular buffers

---

**[Continuing with Complexity Analysis, Recursion, and Stacks sections in next response...]**
