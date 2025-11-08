# 🎯 Practice Problems: Arrays & ArrayLists

## 5 Problems (Easy → Very Hard) with Complete Solutions

---

## Problem 1: Two Sum (Easy)
**Difficulty:** ⭐ Easy
**Time to Solve:** 10-15 minutes

### Problem Statement
Given an array of integers `nums` and an integer `target`, return the indices of the two numbers that add up to `target`.

You may assume that each input has exactly one solution, and you may not use the same element twice.

**Examples:**
```
Input: nums = [2, 7, 11, 15], target = 9
Output: [0, 1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9

Input: nums = [3, 2, 4], target = 6
Output: [1, 2]
```

**Constraints:**
- 2 ≤ nums.length ≤ 10⁴
- -10⁹ ≤ nums[i] ≤ 10⁹
- Only one valid answer exists

### Hint
Use a HashMap to store seen values and their indices. For each element, check if `target - element` exists in the map.

---

### Solution

#### Approach 1: Brute Force (Not Recommended)
```java
public int[] twoSumBrute(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            }
        }
    }
    return new int[]{-1, -1};
}
```
**Time:** O(n²) - nested loops
**Space:** O(1)

---

#### Approach 2: HashMap (Optimal)
```java
import java.util.HashMap;
import java.util.Map;

public int[] twoSum(int[] nums, int target) {
    // Map: value → index
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];

        // Check if complement exists in map
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }

        // Add current number to map
        map.put(nums[i], i);
    }

    return new int[]{-1, -1}; // Not found (shouldn't happen per problem statement)
}
```

**Algorithm Steps:**
1. Create empty HashMap
2. For each element at index i:
   - Calculate complement = target - nums[i]
   - Check if complement already in map
   - If yes: return [map.get(complement), i]
   - If no: add nums[i] → i to map
3. Continue until solution found

**Time:** O(n) - single pass through array, O(1) map operations
**Space:** O(n) - worst case, store all n elements in map

---

#### Test Cases
```java
public class TwoSumTest {
    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        // Test 1: Normal case
        int[] nums1 = {2, 7, 11, 15};
        int[] result1 = solver.twoSum(nums1, 9);
        assert Arrays.equals(result1, new int[]{0, 1}) : "Test 1 failed";

        // Test 2: Answer at end
        int[] nums2 = {3, 2, 4};
        int[] result2 = solver.twoSum(nums2, 6);
        assert Arrays.equals(result2, new int[]{1, 2}) : "Test 2 failed";

        // Test 3: Negative numbers
        int[] nums3 = {-1, -2, -3, -4, -5};
        int[] result3 = solver.twoSum(nums3, -8);
        assert Arrays.equals(result3, new int[]{2, 4}) : "Test 3 failed";

        // Test 4: Zero target
        int[] nums4 = {-3, 4, 3, 90};
        int[] result4 = solver.twoSum(nums4, 0);
        assert Arrays.equals(result4, new int[]{0, 2}) : "Test 4 failed";

        System.out.println("✅ All Two Sum tests passed!");
    }
}
```

---

## Problem 2: Best Time to Buy and Sell Stock (Easy-Medium)
**Difficulty:** ⭐⭐ Easy-Medium
**Time to Solve:** 15-20 minutes

### Problem Statement
You are given an array `prices` where `prices[i]` is the price of a stock on day `i`.

You want to maximize profit by choosing a single day to buy and a different day in the future to sell.

Return the maximum profit. If no profit is possible, return 0.

**Examples:**
```
Input: prices = [7, 1, 5, 3, 6, 4]
Output: 5
Explanation: Buy on day 2 (price = 1), sell on day 5 (price = 6), profit = 6 - 1 = 5

Input: prices = [7, 6, 4, 3, 1]
Output: 0
Explanation: No transactions, max profit = 0
```

### Hint
Track the minimum price seen so far. For each price, calculate potential profit if selling today.

---

### Solution

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length <= 1) {
        return 0;
    }

    int minPrice = prices[0];
    int maxProfit = 0;

    for (int i = 1; i < prices.length; i++) {
        // Update max profit if selling today
        int potentialProfit = prices[i] - minPrice;
        maxProfit = Math.max(maxProfit, potentialProfit);

        // Update minimum price seen so far
        minPrice = Math.min(minPrice, prices[i]);
    }

    return maxProfit;
}
```

**Algorithm Steps:**
1. Initialize minPrice to first element
2. Initialize maxProfit to 0
3. For each day i (starting from day 1):
   - Calculate profit if selling today: prices[i] - minPrice
   - Update maxProfit if this profit is larger
   - Update minPrice if today's price is lower
4. Return maxProfit

**Time:** O(n) - single pass
**Space:** O(1) - constant extra space

**Trace Example:** [7, 1, 5, 3, 6, 4]

| Day | Price | minPrice | Profit Today | maxProfit |
|-----|-------|----------|--------------|-----------|
| 0 | 7 | 7 | - | 0 |
| 1 | 1 | 1 | 0 | 0 |
| 2 | 5 | 1 | 4 | 4 |
| 3 | 3 | 1 | 2 | 4 |
| 4 | 6 | 1 | 5 | 5 ✓ |
| 5 | 4 | 1 | 3 | 5 |

---

## Problem 3: Product of Array Except Self (Medium)
**Difficulty:** ⭐⭐⭐ Medium
**Time to Solve:** 20-30 minutes

### Problem Statement
Given an integer array `nums`, return an array `answer` where `answer[i]` is the product of all elements except `nums[i]`.

**Constraint:** You must solve it in O(n) time **without using division**.

**Examples:**
```
Input: nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
Explanation:
answer[0] = 2 * 3 * 4 = 24
answer[1] = 1 * 3 * 4 = 12
answer[2] = 1 * 2 * 4 = 8
answer[3] = 1 * 2 * 3 = 6

Input: nums = [-1, 1, 0, -3, 3]
Output: [0, 0, 9, 0, 0]
```

### Hint
For each position i, the result is (product of all elements to the left) × (product of all elements to the right). Use two passes: one for left products, one for right products.

---

### Solution

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];

    // Step 1: Calculate left products
    // answer[i] = product of all elements to the left of i
    answer[0] = 1; // No elements to the left of index 0
    for (int i = 1; i < n; i++) {
        answer[i] = answer[i - 1] * nums[i - 1];
    }

    // Step 2: Calculate right products and multiply
    // rightProduct = product of all elements to the right
    int rightProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        answer[i] = answer[i] * rightProduct;
        rightProduct *= nums[i];
    }

    return answer;
}
```

**Algorithm Steps:**
1. **Left pass:** Build array where answer[i] = product of all elements left of i
2. **Right pass:** Multiply each answer[i] by product of all elements right of i

**Trace Example:** [1, 2, 3, 4]

**After left pass:**
| i | nums[i] | answer[i] (left product) |
|---|---------|--------------------------|
| 0 | 1 | 1 (nothing to left) |
| 1 | 2 | 1 (1) |
| 2 | 3 | 2 (1×2) |
| 3 | 4 | 6 (1×2×3) |

**Right pass with multiplication:**
| i | rightProduct before | answer[i] before | answer[i] after | rightProduct after |
|---|---------------------|------------------|------------------|--------------------|
| 3 | 1 | 6 | 6×1 = 6 | 1×4 = 4 |
| 2 | 4 | 2 | 2×4 = 8 | 4×3 = 12 |
| 1 | 12 | 1 | 1×12 = 12 | 12×2 = 24 |
| 0 | 24 | 1 | 1×24 = 24 | 24×1 = 24 |

**Result:** [24, 12, 8, 6] ✓

**Time:** O(n) - two passes
**Space:** O(1) - output array doesn't count as extra space per problem definition

---

## Problem 4: Longest Consecutive Sequence (Medium-Hard)
**Difficulty:** ⭐⭐⭐⭐ Medium-Hard
**Time to Solve:** 25-35 minutes

### Problem Statement
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

**Constraint:** Your algorithm must run in O(n) time.

**Examples:**
```
Input: nums = [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: Longest consecutive sequence is [1, 2, 3, 4], length = 4

Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
Output: 9
Explanation: [0, 1, 2, 3, 4, 5, 6, 7, 8]
```

### Hint
Use a HashSet for O(1) lookups. For each number, check if it's the start of a sequence (i.e., num-1 doesn't exist). If so, count consecutive numbers.

---

### Solution

```java
import java.util.HashSet;
import java.util.Set;

public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }

    // Put all numbers in set for O(1) lookup
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums) {
        numSet.add(num);
    }

    int longestStreak = 0;

    for (int num : numSet) {
        // Only start counting if this is the beginning of a sequence
        if (!numSet.contains(num - 1)) {
            int currentNum = num;
            int currentStreak = 1;

            // Count consecutive numbers
            while (numSet.contains(currentNum + 1)) {
                currentNum++;
                currentStreak++;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }
    }

    return longestStreak;
}
```

**Algorithm Steps:**
1. Add all numbers to HashSet (for O(1) contains check)
2. For each number in set:
   - Check if it's the START of a sequence (num-1 not in set)
   - If yes, count consecutive numbers: num, num+1, num+2, ...
   - Update longestStreak
3. Return longestStreak

**Key Insight:** Only count from sequence starts to avoid redundant work!

**Trace Example:** [100, 4, 200, 1, 3, 2]

Set: {100, 4, 200, 1, 3, 2}

| num | Is start? | Sequence counted | Length |
|-----|-----------|------------------|--------|
| 100 | ✓ (99 not in set) | 100 | 1 |
| 4 | ✓ (3 in set but we check later) | - | - |
| 200 | ✓ (199 not in set) | 200 | 1 |
| 1 | ✓ (0 not in set) | 1, 2, 3, 4 | 4 ✓ |
| 3 | ✗ (2 in set) | - | - |
| 2 | ✗ (1 in set) | - | - |

**Result:** 4

**Time:** O(n) - each number visited at most twice (once in outer loop, once in while loop for counting)
**Space:** O(n) - HashSet

---

## Problem 5: Trapping Rain Water (Hard)
**Difficulty:** ⭐⭐⭐⭐⭐ Very Hard
**Time to Solve:** 30-45 minutes

### Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

**Examples:**
```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation:
    █
█   █ █ █
█ █ █████
Trapped water: 6 units

Input: height = [4,2,0,3,2,5]
Output: 9
```

### Hint
Water trapped at position i = min(max height to left, max height to right) - height[i]. Use two pointers from both ends, tracking max heights.

---

### Solution

#### Approach 1: Brute Force (Not recommended)
For each position, find max left and max right. Time: O(n²)

#### Approach 2: Two Arrays (Good but uses extra space)
Precompute max left and max right for all positions. Time: O(n), Space: O(n)

#### Approach 3: Two Pointers (Optimal)
```java
public int trap(int[] height) {
    if (height == null || height.length <= 2) {
        return 0; // Need at least 3 bars to trap water
    }

    int left = 0;
    int right = height.length - 1;
    int leftMax = 0;
    int rightMax = 0;
    int waterTrapped = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            // Process left side
            if (height[left] >= leftMax) {
                leftMax = height[left]; // Update max
            } else {
                waterTrapped += leftMax - height[left]; // Trap water
            }
            left++;
        } else {
            // Process right side
            if (height[right] >= rightMax) {
                rightMax = height[right]; // Update max
            } else {
                waterTrapped += rightMax - height[right]; // Trap water
            }
            right--;
        }
    }

    return waterTrapped;
}
```

**Algorithm Steps:**
1. Use two pointers: left (start), right (end)
2. Track leftMax and rightMax (highest bars seen so far from each side)
3. Move pointer with smaller height:
   - If current height ≥ max: update max (no water trapped here)
   - If current height < max: trap water = max - current height
4. Continue until pointers meet

**Key Insight:** Water level at position i is limited by the SMALLER of (max left, max right). By moving from the side with smaller height, we ensure the water level is determined.

**Trace Example:** [0,1,0,2,1,0,1,3,2,1,2,1]

| Step | left | right | h[L] | h[R] | lMax | rMax | Water | Total |
|------|------|-------|------|------|------|------|-------|-------|
| 1 | 0 | 11 | 0 | 1 | 0 | 0 | 0 | 0 |
| 2 | 1 | 11 | 1 | 1 | 1 | 1 | 0 | 0 |
| 3 | 2 | 11 | 0 | 1 | 1 | 1 | 1 | 1 |
| ... | ... | ... | ... | ... | ... | ... | ... | ... |

**Time:** O(n) - single pass with two pointers
**Space:** O(1) - constant extra space

---

## Summary of All 5 Problems

| Problem | Difficulty | Key Technique | Time | Space |
|---------|-----------|---------------|------|-------|
| Two Sum | Easy | HashMap | O(n) | O(n) |
| Best Time to Buy/Sell Stock | Easy-Medium | Single pass tracking | O(n) | O(1) |
| Product Except Self | Medium | Prefix/suffix products | O(n) | O(1) |
| Longest Consecutive Sequence | Medium-Hard | HashSet, smart iteration | O(n) | O(n) |
| Trapping Rain Water | Very Hard | Two pointers | O(n) | O(1) |

---

## Practice Tips

1. **Start with Easy:** Master Two Sum and Stock before moving on
2. **Understand WHY:** Don't just memorize, understand the logic
3. **Code from Scratch:** Don't copy-paste, type it out yourself
4. **Test Edge Cases:** Empty, single element, all same values
5. **Time Yourself:** Set a timer, simulate exam conditions
6. **Explain Out Loud:** Teach the solution to someone (or yourself!)

**Next Steps:**
- Solve all 5 problems without looking at solutions
- Implement in different ways (brute force → optimal)
- Create your own test cases
- Explain each solution to someone else

Good luck! 🚀
