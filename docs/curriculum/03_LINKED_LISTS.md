# Topic 3: Linked Lists (Singly, Doubly, Circular, Sentinel-Based)

## 📖 Concept Summary
A linked list is a linear data structure where elements (nodes) are connected via pointers/references rather than being stored contiguously. Each node contains data and a reference to the next node. Variants include: singly linked (one direction), doubly linked (bidirectional), circular (last points to first), and sentinel-based (dummy head node for simpler edge case handling).

## 🧠 Intuition & Mental Model

**Think of a treasure hunt:**
- Each clue (node) tells you where the next clue is
- You can't jump to clue #5 directly; must follow the chain
- Easy to add new clues in the middle (just change references)
- Hard to find a specific clue (must walk the chain)

**Singly vs Doubly vs Circular:**
- **Singly:** One-way street → can only go forward
- **Doubly:** Two-way street → can go forward AND backward
- **Circular:** Circular track → last node points back to first
- **Sentinel:** Starting post that never moves → simplifies insertion/deletion

**Exam Recognition Patterns:**
- "Add/remove at beginning" → O(1) with linked list (vs O(n) for array)
- "Don't know size in advance" → linked list
- "Need O(1) insert/delete in middle" → linked list (if you have the node reference)
- "Need random access" → array, NOT linked list

## 💻 Key Operations with Java Implementations

### 1. Singly Linked List - Complete Implementation
```java
/**
 * Node class for singly linked list
 */
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Singly Linked List with all core operations
 */
public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Insert at beginning - O(1)
     * EXAM FAVORITE: Know this by heart!
     */
    public void insertHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Insert at end - O(n)
     * Must traverse to find last node
     */
    public void insertTail(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    /**
     * Insert at specific position (0-indexed)
     * Time: O(n), Space: O(1)
     */
    public void insertAt(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            insertHead(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        // Move to node BEFORE insertion point
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Delete first occurrence of value
     * Time: O(n), Space: O(1)
     */
    public boolean delete(int data) {
        if (head == null) {
            return false;
        }

        // Special case: deleting head
        if (head.data == data) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next; // Skip the node
                size--;
                return true;
            }
            current = current.next;
        }

        return false; // Not found
    }

    /**
     * Delete node at specific index
     * Time: O(n), Space: O(1)
     */
    public void deleteAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
        size--;
    }

    /**
     * Search for value - O(n)
     */
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Get value at index - O(n)
     */
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

    /**
     * Reverse the linked list - O(n)
     * EXAM FAVORITE! Know iterative AND recursive versions
     */
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;  // Save next
            current.next = prev;  // Reverse pointer
            prev = current;       // Move prev forward
            current = next;       // Move current forward
        }

        head = prev;
    }

    /**
     * Reverse recursively
     * Time: O(n), Space: O(n) due to recursion stack
     */
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);
    }

    private Node reverseRecursiveHelper(Node node) {
        // Base case: empty or single node
        if (node == null || node.next == null) {
            return node;
        }

        // Reverse rest of list
        Node newHead = reverseRecursiveHelper(node.next);

        // Fix pointers
        node.next.next = node;
        node.next = null;

        return newHead;
    }

    /**
     * Find middle element using slow/fast pointer technique
     * Time: O(n), Space: O(1)
     * EXAM TIP: This pattern appears everywhere!
     */
    public Integer findMiddle() {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.data;
    }

    /**
     * Detect cycle using Floyd's algorithm
     * Time: O(n), Space: O(1)
     */
    public boolean hasCycle() {
        if (head == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false;
    }

    /**
     * Get nth node from end
     * Time: O(n), Space: O(1)
     * Use two pointers n steps apart
     */
    public Integer getNthFromEnd(int n) {
        if (n <= 0 || head == null) {
            return null;
        }

        Node first = head;
        Node second = head;

        // Move first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (first == null) {
                return null; // n is larger than list size
            }
            first = first.next;
        }

        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Print list for debugging
     */
    public void print() {
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}
```

### 2. Doubly Linked List
```java
/**
 * Node for doubly linked list
 */
class DNode {
    int data;
    DNode prev;
    DNode next;

    DNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

/**
 * Doubly Linked List
 * Advantage: Can traverse backward, easier deletion
 */
public class DoublyLinkedList {
    private DNode head;
    private DNode tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Insert at head - O(1)
     */
    public void insertHead(int data) {
        DNode newNode = new DNode(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Insert at tail - O(1) (unlike singly linked!)
     */
    public void insertTail(int data) {
        DNode newNode = new DNode(data);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Delete node (given node reference) - O(1)
     * This is the BIG advantage of doubly linked!
     */
    public void deleteNode(DNode node) {
        if (node == null) {
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; // Deleting head
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev; // Deleting tail
        }

        size--;
    }

    /**
     * Traverse forward
     */
    public void printForward() {
        DNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    /**
     * Traverse backward
     */
    public void printBackward() {
        DNode current = tail;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println("]");
    }
}
```

### 3. Circular Linked List
```java
/**
 * Circular Linked List
 * Last node points back to first
 * Useful for: round-robin scheduling, buffers
 */
public class CircularLinkedList {
    private Node tail; // Store tail, not head!
    private int size;

    /**
     * Insert at beginning - O(1)
     */
    public void insertHead(int data) {
        Node newNode = new Node(data);

        if (tail == null) {
            tail = newNode;
            tail.next = tail; // Point to itself
        } else {
            newNode.next = tail.next; // Point to old head
            tail.next = newNode;      // Tail points to new head
        }
        size++;
    }

    /**
     * Insert at end - O(1)
     */
    public void insertTail(int data) {
        insertHead(data);
        tail = tail.next; // Move tail forward
    }

    /**
     * Print circular list (careful not to infinite loop!)
     */
    public void print() {
        if (tail == null) {
            System.out.println("[]");
            return;
        }

        Node current = tail.next; // Start at head
        System.out.print("[");
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != tail.next) {
                System.out.print(" -> ");
            }
        } while (current != tail.next);
        System.out.println(" -> (circular)]");
    }
}
```

### 4. Sentinel-Based List (Dummy Head)
```java
/**
 * Linked list with sentinel (dummy) head node
 * Advantage: No special cases for empty list!
 */
public class SentinelLinkedList {
    private Node sentinel; // Dummy node, always exists
    private int size;

    public SentinelLinkedList() {
        sentinel = new Node(0); // Value doesn't matter
        sentinel.next = null;
        size = 0;
    }

    /**
     * Insert at head - O(1)
     * No need to check if list is empty!
     */
    public void insertHead(int data) {
        Node newNode = new Node(data);
        newNode.next = sentinel.next;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Delete first occurrence - O(n)
     * No need to special-case head deletion!
     */
    public boolean delete(int data) {
        Node current = sentinel; // Start at sentinel

        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Print list
     */
    public void print() {
        Node current = sentinel.next; // Skip sentinel
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: Null Pointer Exceptions
```java
// ❌ WRONG: Didn't check for null
public void insertTail(int data) {
    Node current = head;
    while (current.next != null) { // CRASH if head is null!
        current = current.next;
    }
}

// ✅ CORRECT: Always check null first
public void insertTail(int data) {
    if (head == null) {
        head = new Node(data);
        return;
    }
    // Now safe to traverse...
}
```

### Pitfall 2: Losing Head Reference
```java
// ❌ WRONG: Lost original head!
public void reverse() {
    Node current = head;
    while (current != null) {
        // ... reverse logic ...
        head = current; // Keeps changing head!
    }
}

// ✅ CORRECT: Save new head outside loop
```

### Pitfall 3: Forgetting to Update Size
```java
// Always increment/decrement size in insert/delete operations!
```

### Pitfall 4: Off-by-One in Traversal
```java
// To insert at index i, stop at node i-1, not i!
```

## ⏱️ Time & Space Complexity

| Operation | Singly | Doubly | Circular | Sentinel | Notes |
|-----------|--------|--------|----------|----------|-------|
| Insert at head | O(1) | O(1) | O(1) | O(1) | Just update head |
| Insert at tail | O(n) | O(1) | O(1) | O(n) | Doubly has tail pointer |
| Delete head | O(1) | O(1) | O(1) | O(1) | Just update head |
| Delete tail | O(n) | O(1) | O(n) | O(n) | Doubly can go backward |
| Delete given node | O(n) | O(1) | O(n) | O(n) | Doubly doesn't need to find prev |
| Search | O(n) | O(n) | O(n) | O(n) | Must traverse |
| Access by index | O(n) | O(n) | O(n) | O(n) | No random access |

**Space Complexity:**
- Singly: O(n) for n nodes, 1 reference per node
- Doubly: O(n) for n nodes, 2 references per node (~2x memory)

## 🧪 Complete Test Cases

```java
public class LinkedListTest {
    public static void main(String[] args) {
        testSinglyLinkedList();
        testDoublyLinkedList();
        testCircularLinkedList();
        testSentinelLinkedList();
        testAdvancedOperations();
        System.out.println("✅ All linked list tests passed!");
    }

    static void testSinglyLinkedList() {
        SinglyLinkedList list = new SinglyLinkedList();

        // Test insert head
        list.insertHead(3);
        list.insertHead(2);
        list.insertHead(1);
        assert list.size() == 3;
        assert list.get(0) == 1;

        // Test insert tail
        list.insertTail(4);
        assert list.get(3) == 4;

        // Test delete
        assert list.delete(2) == true;
        assert list.size() == 3;
        assert list.delete(99) == false;

        // Test reverse
        list.reverse();
        assert list.get(0) == 4;
        assert list.get(2) == 1;
    }

    static void testDoublyLinkedList() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.insertHead(2);
        list.insertHead(1);
        list.insertTail(3);

        // Should be: 1 <-> 2 <-> 3
        list.printForward();  // Check output
        list.printBackward(); // Should print: 3 <-> 2 <-> 1
    }

    static void testCircularLinkedList() {
        CircularLinkedList list = new CircularLinkedList();

        list.insertHead(2);
        list.insertHead(1);
        list.insertTail(3);

        // Should be circular: 1 -> 2 -> 3 -> (back to 1)
        list.print();
    }

    static void testSentinelLinkedList() {
        SentinelLinkedList list = new SentinelLinkedList();

        list.insertHead(1);
        list.insertHead(2);
        assert list.delete(2) == true;
        assert list.delete(99) == false;
    }

    static void testAdvancedOperations() {
        SinglyLinkedList list = new SinglyLinkedList();

        // Build list: 1 -> 2 -> 3 -> 4 -> 5
        for (int i = 5; i >= 1; i--) {
            list.insertHead(i);
        }

        // Test find middle
        assert list.findMiddle() == 3;

        // Test nth from end
        assert list.getNthFromEnd(1) == 5; // Last element
        assert list.getNthFromEnd(2) == 4;
        assert list.getNthFromEnd(5) == 1; // First element

        // Test cycle detection (no cycle)
        assert list.hasCycle() == false;
    }
}
```

---

## 📚 Summary for Exams

**Linked List - Must Know:**
1. ✅ Insert/delete at head is O(1)
2. ✅ Access by index is O(n) (no random access!)
3. ✅ Reverse list (iterative & recursive)
4. ✅ Fast/slow pointer technique (find middle, detect cycle)
5. ✅ Always check for null before dereferencing!

**Singly vs Doubly:**
- Singly: Less memory, can only traverse forward
- Doubly: More memory, can traverse both ways, O(1) delete with node reference

**When to use Sentinel:**
- Eliminates special cases for empty list
- Cleaner code, fewer bugs
- Slightly more memory (one extra node)

**Common Exam Problems:**
- Reverse linked list ⭐⭐⭐
- Detect cycle (Floyd's algorithm) ⭐⭐⭐
- Find middle (slow/fast pointers) ⭐⭐
- Merge two sorted lists ⭐⭐
- Remove nth from end ⭐⭐

**Edge Cases:**
- Empty list (head == null)
- Single node
- Two nodes (test reverse!)
- Deleting head/tail
- Circular references
