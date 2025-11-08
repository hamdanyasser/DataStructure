/**
 * Complete Data Structures Demonstration
 * Runnable examples for all core data structures
 *
 * Compile: javac DataStructuresDemo.java
 * Run: java DataStructuresDemo
 */

import java.util.*;

public class DataStructuresDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("    DATA STRUCTURES COMPLETE DEMONSTRATION");
        System.out.println("=".repeat(60) + "\n");

        // Run all demonstrations
        demonstrateArrays();
        demonstrateLinkedLists();
        demonstrateStacks();
        demonstrateQueues();
        demonstrateTrees();
        demonstrateHashMaps();
        demonstrateSorting();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("    ALL DEMONSTRATIONS COMPLETED SUCCESSFULLY! ✓");
        System.out.println("=".repeat(60));
    }

    // ========== ARRAYS DEMONSTRATION ==========
    static void demonstrateArrays() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("ARRAYS & ARRAYLISTS");
        System.out.println("▶".repeat(30));

        // 1. Basic array operations
        int[] arr = {5, 2, 8, 1, 9};
        System.out.println("\nOriginal array: " + Arrays.toString(arr));

        // Find max
        int max = findMax(arr);
        System.out.println("Maximum element: " + max);

        // Reverse array
        reverse(arr);
        System.out.println("Reversed array: " + Arrays.toString(arr));

        // Binary search (on sorted array)
        int[] sorted = {1, 2, 5, 8, 9};
        int index = binarySearch(sorted, 5);
        System.out.println("\nBinary search for 5 in " + Arrays.toString(sorted) + ": index " + index);

        // ArrayList demo
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("\nArrayList: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Get index 1: " + list.get(1));
    }

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // ========== LINKED LIST DEMONSTRATION ==========
    static void demonstrateLinkedLists() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("LINKED LISTS");
        System.out.println("▶".repeat(30));

        SimpleLinkedList list = new SimpleLinkedList();

        // Insert operations
        list.insertHead(3);
        list.insertHead(2);
        list.insertHead(1);
        list.insertTail(4);
        list.insertTail(5);

        System.out.println("\nLinked List after insertions:");
        list.print();

        // Reverse
        list.reverse();
        System.out.println("\nAfter reversing:");
        list.print();

        // Find middle
        Integer middle = list.findMiddle();
        System.out.println("\nMiddle element: " + middle);
    }

    // Simple Linked List implementation
    static class SimpleLinkedList {
        private Node head;

        static class Node {
            int data;
            Node next;
            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        void insertHead(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        void insertTail(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        void reverse() {
            Node prev = null;
            Node current = head;
            Node next = null;

            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }

        Integer findMiddle() {
            if (head == null) return null;
            Node slow = head;
            Node fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.data;
        }

        void print() {
            Node current = head;
            System.out.print("[");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println("]");
        }
    }

    // ========== STACK DEMONSTRATION ==========
    static void demonstrateStacks() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("STACKS (LIFO)");
        System.out.println("▶".repeat(30));

        Stack<Integer> stack = new Stack<>();

        // Push operations
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("\nStack after pushes: " + stack);

        // Peek (view top without removing)
        System.out.println("Top element (peek): " + stack.peek());

        // Pop (remove and return top)
        System.out.println("Popped: " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        // Balanced parentheses example
        String expr1 = "()[]{}";
        String expr2 = "([)]";
        System.out.println("\n'" + expr1 + "' is balanced? " + isBalanced(expr1));
        System.out.println("'" + expr2 + "' is balanced? " + isBalanced(expr2));
    }

    static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    // ========== QUEUE DEMONSTRATION ==========
    static void demonstrateQueues() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("QUEUES (FIFO)");
        System.out.println("▶".repeat(30));

        Queue<String> queue = new LinkedList<>();

        // Offer (add to rear)
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("\nQueue after offers: " + queue);

        // Peek (view front without removing)
        System.out.println("Front element (peek): " + queue.peek());

        // Poll (remove and return front)
        System.out.println("Polled: " + queue.poll());
        System.out.println("Queue after poll: " + queue);
    }

    // ========== TREE DEMONSTRATION ==========
    static void demonstrateTrees() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("BINARY TREES & BST");
        System.out.println("▶".repeat(30));

        // Build sample BST:
        //       5
        //      / \
        //     3   8
        //    / \   \
        //   1   4   9

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(9);

        System.out.println("\nTree structure:");
        System.out.println("       5");
        System.out.println("      / \\");
        System.out.println("     3   8");
        System.out.println("    / \\   \\");
        System.out.println("   1   4   9");

        // Traversals
        System.out.print("\nInorder (sorted): ");
        inorderTraversal(root);

        System.out.print("\nPreorder: ");
        preorderTraversal(root);

        System.out.print("\nPostorder: ");
        postorderTraversal(root);

        System.out.print("\nLevel-order (BFS): ");
        levelOrderTraversal(root);

        // BST search
        System.out.println("\n\nSearch for 4: " + searchBST(root, 4));
        System.out.println("Search for 7: " + searchBST(root, 7));
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static void inorderTraversal(TreeNode root) {
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    static void preorderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    static void postorderTraversal(TreeNode root) {
        if (root == null) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    static void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    static boolean searchBST(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        if (target < root.val) return searchBST(root.left, target);
        else return searchBST(root.right, target);
    }

    // ========== HASHMAP DEMONSTRATION ==========
    static void demonstrateHashMaps() {
        System.out.println("\n\n" + "▶".repeat(30));
        System.out.println("HASHMAPS & HASHSETS");
        System.out.println("▶".repeat(30));

        // HashMap demo
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);

        System.out.println("\nHashMap: " + ages);
        System.out.println("Alice's age: " + ages.get("Alice"));
        System.out.println("Contains 'Bob'? " + ages.containsKey("Bob"));

        // Iteration
        System.out.println("\nIterating over HashMap:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Two Sum problem using HashMap
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("\nTwo Sum problem:");
        System.out.println("Array: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
        System.out.println("Values: " + nums[result[0]] + " + " + nums[result[1]] + " = " + target);

        // HashSet demo
        HashSet<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(10); // Duplicate, won't be added
        System.out.println("\nHashSet (no duplicates): " + set);
    }

    static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // ========== SORTING DEMONSTRATION ==========
    static void demonstrateSorting() {
        System.out.println("\n" + "▶".repeat(30));
        System.out.println("SORTING ALGORITHMS");
        System.out.println("▶".repeat(30));

        int[] original = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nOriginal array: " + Arrays.toString(original));

        // Bubble Sort
        int[] arr1 = original.clone();
        bubbleSort(arr1);
        System.out.println("Bubble Sort:    " + Arrays.toString(arr1));

        // Selection Sort
        int[] arr2 = original.clone();
        selectionSort(arr2);
        System.out.println("Selection Sort: " + Arrays.toString(arr2));

        // Insertion Sort
        int[] arr3 = original.clone();
        insertionSort(arr3);
        System.out.println("Insertion Sort: " + Arrays.toString(arr3));

        // Merge Sort
        int[] arr4 = original.clone();
        mergeSort(arr4, 0, arr4.length - 1);
        System.out.println("Merge Sort:     " + Arrays.toString(arr4));

        // Quick Sort
        int[] arr5 = original.clone();
        quickSort(arr5, 0, arr5.length - 1);
        System.out.println("Quick Sort:     " + Arrays.toString(arr5));
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
