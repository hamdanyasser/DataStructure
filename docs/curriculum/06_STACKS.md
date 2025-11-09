# Topic 6: Stack Data Structure

## 📖 Concept Summary
A stack is a linear data structure that follows the Last-In-First-Out (LIFO) principle. Elements are added and removed from the same end, called the "top" of the stack. The two primary operations are push (add element) and pop (remove element). Stacks can be implemented using arrays, singly linked lists, or doubly linked lists.

## 🧠 Intuition & Mental Model

**Think of a stack as a stack of plates:**
- You add new plates on top (push)
- You remove plates from the top (pop)
- You can only see the top plate (peek)
- Last plate added is the first one removed (LIFO)
- Cannot access plates in the middle without removing top plates

**Real-World Examples:**
- Browser back button (stack of visited pages)
- Undo feature in editors (stack of actions)
- Function call stack (stack of function calls)
- Reversing data (push all, then pop all)

**Exam Recognition:**
- LIFO order required → use stack
- Matching pairs (parentheses, tags) → use stack
- Reversing sequence → use stack
- Backtracking problems → use stack
- Expression evaluation → use stack

## 💻 Key Operations with Java Implementations

### 1. Stack Interface

```java
/**
 * Generic Stack interface
 * Defines the contract for all stack implementations
 */
public interface Stack<E> {
    /**
     * Adds element to top of stack
     * @param element the element to add
     */
    void push(E element);

    /**
     * Removes and returns top element
     * @return the top element
     * @throws IllegalStateException if stack is empty
     */
    E pop();

    /**
     * Returns top element without removing it
     * @return the top element
     * @throws IllegalStateException if stack is empty
     */
    E peek();

    /**
     * Returns number of elements in stack
     * @return size of stack
     */
    int size();

    /**
     * Checks if stack is empty
     * @return true if empty, false otherwise
     */
    boolean isEmpty();
}
```

### 2. Array-Based Stack Implementation

```java
/**
 * Stack implementation using array
 * Fixed capacity (can be made dynamic)
 *
 * Time Complexity:
 * - push: O(1)
 * - pop: O(1)
 * - peek: O(1)
 * - size: O(1)
 * - isEmpty: O(1)
 *
 * Space Complexity: O(n) where n is capacity
 */
public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int top;        // Index of top element (-1 if empty)

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        data = (E[]) new Object[capacity];
        top = -1;
    }

    /**
     * Push element onto stack
     * Time: O(1)
     */
    @Override
    public void push(E element) {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++top] = element;      // Increment top, then add
    }

    /**
     * Pop element from stack
     * Time: O(1)
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E element = data[top];
        data[top] = null;           // Help garbage collection
        top--;
        return element;
    }

    /**
     * Peek at top element
     * Time: O(1)
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[top];
    }

    @Override
    public int size() {
        return top + 1;             // top is index, so size is top + 1
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
```

### 3. Resizable Array Stack

```java
/**
 * Stack with dynamic resizing (like ArrayList)
 * Grows automatically when full
 *
 * Time Complexity:
 * - push: O(1) amortized (occasionally O(n) for resize)
 * - pop: O(1)
 * - peek: O(1)
 *
 * Space: O(n)
 */
public class ResizableArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public ResizableArrayStack() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    @Override
    public void push(E element) {
        if (size() == data.length) {
            resize(2 * data.length);    // Double capacity
        }
        data[++top] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        E element = data[top];
        data[top] = null;
        top--;

        // Shrink if too empty (optional optimization)
        if (size() > 0 && size() == data.length / 4) {
            resize(data.length / 2);
        }

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Resize internal array
     * Time: O(n)
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i <= top; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
```

### 4. Singly Linked List Stack

```java
/**
 * Stack implementation using singly linked list
 * No capacity limit, grows dynamically
 *
 * Time Complexity: All operations O(1)
 * Space Complexity: O(n)
 */
public class LinkedStack<E> implements Stack<E> {

    /**
     * Node class for singly linked list
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> top;        // Reference to top node
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    /**
     * Push element onto stack
     * Add new node at head of linked list
     * Time: O(1)
     */
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element, top);
        top = newNode;
        size++;
    }

    /**
     * Pop element from stack
     * Remove node from head of linked list
     * Time: O(1)
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        E element = top.element;
        top = top.next;         // Move top to next node
        size--;
        return element;
    }

    /**
     * Peek at top element
     * Time: O(1)
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
```

### 5. Doubly Linked List Stack

```java
/**
 * Stack implementation using doubly linked list
 * Allows traversal in both directions (though not typically needed for stack)
 *
 * Time Complexity: All operations O(1)
 * Space Complexity: O(n) - slightly more than singly linked due to prev pointers
 */
public class DoublyLinkedStack<E> implements Stack<E> {

    /**
     * Node class for doubly linked list
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> top;        // Reference to top node
    private int size;

    public DoublyLinkedStack() {
        top = null;
        size = 0;
    }

    /**
     * Push element onto stack
     * Time: O(1)
     */
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element, null, top);
        if (top != null) {
            top.prev = newNode;
        }
        top = newNode;
        size++;
    }

    /**
     * Pop element from stack
     * Time: O(1)
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        E element = top.element;
        top = top.next;
        if (top != null) {
            top.prev = null;    // Remove reference to popped node
        }
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}
```

### 6. Application: Parenthesis Matching

```java
/**
 * Check if parentheses are balanced using stack
 * Valid: ( ), { }, [ ], ( { } ), [ ( ) ]
 * Invalid: ( [ ) ], ( ( (, } } }
 *
 * Time: O(n)
 * Space: O(n) worst case - all opening parentheses
 */
public class ParenthesisMatching {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new LinkedStack<>();

        for (char ch : expression.toCharArray()) {
            // If opening bracket, push to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If closing bracket, check if matches top
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;       // Closing bracket with no opening
                }

                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;       // Mismatched pair
                }
            }
            // Ignore other characters
        }

        // Stack should be empty if all brackets matched
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }

    /**
     * Test cases
     */
    public static void testParenthesisMatching() {
        assert isBalanced("()");
        assert isBalanced("()[]{}");
        assert isBalanced("({[]})");
        assert isBalanced("((()))");

        assert !isBalanced("(");
        assert !isBalanced(")");
        assert !isBalanced("([)]");
        assert !isBalanced("(()");
        assert !isBalanced("())");

        System.out.println("✅ Parenthesis matching tests passed!");
    }
}
```

### 7. Application: HTML Tag Matching

```java
/**
 * Check if HTML tags are properly matched
 * Valid: <html></html>, <div><p></p></div>
 * Invalid: <html></body>, <div><p></div></p>
 *
 * Time: O(n)
 * Space: O(n)
 */
public class HTMLTagMatching {

    public static boolean areTagsBalanced(String html) {
        Stack<String> stack = new LinkedStack<>();
        int i = 0;

        while (i < html.length()) {
            // Find opening '<'
            if (html.charAt(i) == '<') {
                int j = i + 1;

                // Find closing '>'
                while (j < html.length() && html.charAt(j) != '>') {
                    j++;
                }

                if (j == html.length()) {
                    return false;       // Incomplete tag
                }

                // Extract tag name
                String tag = html.substring(i + 1, j);

                // Check if opening or closing tag
                if (tag.startsWith("/")) {
                    // Closing tag
                    String tagName = tag.substring(1);

                    if (stack.isEmpty()) {
                        return false;   // Closing tag with no opening
                    }

                    String openingTag = stack.pop();
                    if (!openingTag.equals(tagName)) {
                        return false;   // Mismatched tags
                    }
                } else if (!tag.endsWith("/")) {
                    // Opening tag (not self-closing like <br/>)
                    // Extract tag name (remove attributes)
                    String tagName = tag.split("\\s+")[0];
                    stack.push(tagName);
                }

                i = j + 1;
            } else {
                i++;
            }
        }

        return stack.isEmpty();         // All tags should be matched
    }

    /**
     * Test cases
     */
    public static void testHTMLMatching() {
        assert areTagsBalanced("<html></html>");
        assert areTagsBalanced("<html><body></body></html>");
        assert areTagsBalanced("<div><p>Text</p></div>");
        assert areTagsBalanced("<html><body><h1>Title</h1></body></html>");

        assert !areTagsBalanced("<html></body>");
        assert !areTagsBalanced("<div><p></div></p>");
        assert !areTagsBalanced("<html>");
        assert !areTagsBalanced("</html>");

        System.out.println("✅ HTML tag matching tests passed!");
    }
}
```

### 8. Application: Expression Evaluation

```java
/**
 * Evaluate arithmetic expressions using stacks
 * Supports: +, -, *, /, parentheses
 * Uses two stacks: one for operands, one for operators
 */
public class ExpressionEvaluator {

    /**
     * Evaluate infix expression
     * Example: "3 + 5 * 2" → 13
     * Time: O(n)
     * Space: O(n)
     */
    public static int evaluateInfix(String expression) {
        Stack<Integer> operands = new LinkedStack<>();
        Stack<Character> operators = new LinkedStack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Skip whitespace
            if (ch == ' ') {
                continue;
            }

            // If digit, parse complete number
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;    // Adjust for loop increment
                operands.push(num);
            }
            // If opening parenthesis, push to operators
            else if (ch == '(') {
                operators.push(ch);
            }
            // If closing parenthesis, evaluate until opening
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    processOperator(operands, operators);
                }
                operators.pop();    // Remove '('
            }
            // If operator
            else if (isOperator(ch)) {
                // Process operators with higher or equal precedence
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    processOperator(operands, operators);
                }
                operators.push(ch);
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            processOperator(operands, operators);
        }

        return operands.pop();
    }

    private static void processOperator(Stack<Integer> operands, Stack<Character> operators) {
        char op = operators.pop();
        int b = operands.pop();
        int a = operands.pop();
        int result = applyOperator(a, b, op);
        operands.push(result);
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;   // For '('
    }

    private static int applyOperator(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    /**
     * Convert infix to postfix (Reverse Polish Notation)
     * Example: "3 + 5 * 2" → "3 5 2 * +"
     * Time: O(n)
     * Space: O(n)
     */
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new LinkedStack<>();

        for (char ch : infix.toCharArray()) {
            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch)) {
                postfix.append(ch).append(' ');
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop()).append(' ');
                }
                operators.pop();    // Remove '('
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    postfix.append(operators.pop()).append(' ');
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    /**
     * Evaluate postfix expression
     * Example: "3 5 2 * +" → 13
     * Time: O(n)
     * Space: O(n)
     */
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new LinkedStack<>();

        for (String token : postfix.split("\\s+")) {
            if (token.isEmpty()) {
                continue;
            }

            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOperator(a, b, token.charAt(0));
                stack.push(result);
            }
        }

        return stack.pop();
    }
}
```

## 🚨 Typical Pitfalls & Edge Cases

### Pitfall 1: Pop/Peek on Empty Stack
```java
// ❌ WRONG - No empty check
Stack<Integer> stack = new LinkedStack<>();
int top = stack.pop();      // Throws exception!

// ✅ CORRECT - Always check if empty
if (!stack.isEmpty()) {
    int top = stack.pop();
} else {
    // Handle empty case
}
```

### Pitfall 2: Fixed Array Size
```java
// ❌ Can run out of space
ArrayStack<Integer> stack = new ArrayStack<>(10);
for (int i = 0; i < 100; i++) {
    stack.push(i);          // Throws exception after 10!
}

// ✅ Use resizable implementation
ResizableArrayStack<Integer> stack = new ResizableArrayStack<>();
for (int i = 0; i < 100; i++) {
    stack.push(i);          // Grows automatically
}
```

### Pitfall 3: Not Handling All Cases in Matching
```java
// ❌ Forgets to check final stack state
public static boolean badBalance(String s) {
    Stack<Character> stack = new LinkedStack<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(') stack.push(ch);
        else if (ch == ')') {
            if (stack.isEmpty()) return false;
            stack.pop();
        }
    }
    return true;        // ❌ Should return stack.isEmpty()!
}
// badBalance("((") returns true incorrectly!

// ✅ CORRECT
public static boolean goodBalance(String s) {
    // ... same logic ...
    return stack.isEmpty();     // ✅ Check stack is empty
}
```

### Pitfall 4: Expression Evaluation Edge Cases
```java
// Edge cases to handle:
// - Division by zero
// - Unbalanced parentheses
// - Invalid operators
// - Empty expression
// - Multiple digit numbers: "12 + 34" not just "1 + 2"
```

## ⏱️ Time & Space Complexity

### Stack Operations

| Implementation | Push | Pop | Peek | Space |
|---------------|------|-----|------|-------|
| Array (fixed) | O(1) | O(1) | O(1) | O(n) |
| Array (resizable) | O(1) amortized | O(1) | O(1) | O(n) |
| Singly Linked | O(1) | O(1) | O(1) | O(n) |
| Doubly Linked | O(1) | O(1) | O(1) | O(n) more overhead |

### Stack Applications

| Application | Time | Space | Notes |
|-------------|------|-------|-------|
| Parenthesis matching | O(n) | O(n) | Worst: all opening |
| HTML tag matching | O(n) | O(n) | Parse entire document |
| Infix evaluation | O(n) | O(n) | Two stacks |
| Postfix evaluation | O(n) | O(n) | Single stack |
| Infix to postfix | O(n) | O(n) | Single stack |

## 🧪 Complete Test Cases

```java
public class StackTest {

    public static void main(String[] args) {
        testArrayStack();
        testLinkedStack();
        testDoublyLinkedStack();
        testParenthesisMatching();
        testHTMLMatching();
        testExpressionEvaluation();
        testEdgeCases();
        System.out.println("✅ All stack tests passed!");
    }

    static void testArrayStack() {
        Stack<Integer> stack = new ArrayStack<>();

        assert stack.isEmpty();
        assert stack.size() == 0;

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assert stack.size() == 3;
        assert stack.peek() == 30;
        assert stack.pop() == 30;
        assert stack.pop() == 20;
        assert stack.size() == 1;
        assert stack.pop() == 10;
        assert stack.isEmpty();
    }

    static void testLinkedStack() {
        Stack<String> stack = new LinkedStack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        assert stack.peek().equals("C");
        assert stack.pop().equals("C");
        assert stack.pop().equals("B");
        assert stack.pop().equals("A");
        assert stack.isEmpty();
    }

    static void testDoublyLinkedStack() {
        Stack<Integer> stack = new DoublyLinkedStack<>();

        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }

        assert stack.size() == 5;

        for (int i = 5; i >= 1; i--) {
            assert stack.pop() == i;
        }

        assert stack.isEmpty();
    }

    static void testParenthesisMatching() {
        assert ParenthesisMatching.isBalanced("()");
        assert ParenthesisMatching.isBalanced("({[]})");
        assert ParenthesisMatching.isBalanced("((()))");

        assert !ParenthesisMatching.isBalanced("(");
        assert !ParenthesisMatching.isBalanced("([)]");
        assert !ParenthesisMatching.isBalanced("())");
    }

    static void testHTMLMatching() {
        assert HTMLTagMatching.areTagsBalanced("<html></html>");
        assert HTMLTagMatching.areTagsBalanced("<div><p></p></div>");

        assert !HTMLTagMatching.areTagsBalanced("<html></body>");
        assert !HTMLTagMatching.areTagsBalanced("<div><p></div></p>");
    }

    static void testExpressionEvaluation() {
        // Infix evaluation
        assert ExpressionEvaluator.evaluateInfix("3 + 5") == 8;
        assert ExpressionEvaluator.evaluateInfix("3 + 5 * 2") == 13;
        assert ExpressionEvaluator.evaluateInfix("(3 + 5) * 2") == 16;
        assert ExpressionEvaluator.evaluateInfix("10 - 2 * 3") == 4;

        // Postfix evaluation
        assert ExpressionEvaluator.evaluatePostfix("3 5 +") == 8;
        assert ExpressionEvaluator.evaluatePostfix("3 5 2 * +") == 13;
        assert ExpressionEvaluator.evaluatePostfix("3 5 + 2 *") == 16;

        // Infix to postfix
        assert ExpressionEvaluator.infixToPostfix("3 + 5").equals("3 5 +");
        assert ExpressionEvaluator.infixToPostfix("3 + 5 * 2").equals("3 5 2 * +");
    }

    static void testEdgeCases() {
        Stack<Integer> stack = new LinkedStack<>();

        // Pop from empty stack
        try {
            stack.pop();
            assert false : "Should throw exception";
        } catch (IllegalStateException e) {
            // Expected
        }

        // Peek at empty stack
        try {
            stack.peek();
            assert false : "Should throw exception";
        } catch (IllegalStateException e) {
            // Expected
        }

        // Push and pop null
        Stack<String> stringStack = new LinkedStack<>();
        stringStack.push(null);
        assert stringStack.peek() == null;
        assert stringStack.pop() == null;
    }
}
```

## 🎯 Expected Test Output
```
✅ Parenthesis matching tests passed!
✅ HTML tag matching tests passed!
✅ All stack tests passed!
```

---

## 📚 Summary for Exams

**Stack Essentials:**
1. ✅ **LIFO:** Last In, First Out
2. ✅ **Operations:** push, pop, peek (all O(1))
3. ✅ **Implementations:** Array (fixed/dynamic), Linked List
4. ✅ **Applications:** Matching, evaluation, reversal, backtracking

**Implementation Comparison:**

| Aspect | Array | Linked List |
|--------|-------|-------------|
| Memory | Fixed (or resize overhead) | Dynamic, more per element |
| Cache | Better locality | Worse locality |
| Size limit | Fixed or resize cost | No limit |
| Complexity | All O(1) | All O(1) |

**Common Applications:**
- **Parenthesis/Bracket Matching:** O(n) time, O(n) space
- **HTML Tag Matching:** O(n) time, O(n) space
- **Expression Evaluation:** O(n) time, O(n) space
- **Reverse Data:** Push all, pop all
- **Undo/Redo:** Stack of states/actions

**Must Know Algorithms:**
1. **Balanced Parentheses:**
   - Push opening brackets
   - Pop and match closing brackets
   - Stack empty at end → balanced

2. **Postfix Evaluation:**
   - Push operands
   - Pop two operands for operators
   - Push result back

3. **Infix Evaluation:**
   - Two stacks (operands and operators)
   - Respect operator precedence
   - Handle parentheses

**Common Exam Mistakes:**
- Forgetting to check isEmpty() before pop/peek
- Not returning stack.isEmpty() in matching problems
- Wrong precedence in expression evaluation
- Off-by-one errors in array implementation (top index)
- Forgetting to handle multi-digit numbers in expressions

**When to Use Stack:**
- LIFO access pattern needed
- Matching/balancing problems
- Expression parsing/evaluation
- Backtracking (DFS, undo)
- Function call management
