package madium;
/*
    Given an array of integers nums, sort the array in ascending order and return it.
    You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
    and with the smallest space complexity possible.

    Example 1:
    Input: nums = [5,2,3,1]
    Output: [1,2,3,5]
    Explanation: After sorting the array, the positions of some numbers are not changed (for example,
    2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
 */

import java.util.Arrays;

public class Exc24_SortAnArray {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // Merge two sorted halves
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Copy remaining elements from the left half
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // Copy remaining elements from the right half
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // Copy sorted elements back into the original array
        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc24_SortAnArray solution = new Exc24_SortAnArray();
        int[] nums1 = {5, 2, 3, 1};
        int[] nums2 = {5, 1, 1, 2, 0, 0};

        System.out.println(Arrays.toString(solution.sortArray(nums1))); // Output: [1, 2, 3, 5]
        System.out.println(Arrays.toString(solution.sortArray(nums2))); // Output: [0, 0, 1, 1, 2, 5]
    }
}
