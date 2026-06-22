package Target;

//using Kadane’s Algorithm, O(n)
public class MaxSum {
    
    public int maxSum(int[] nums){
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            //if currentSum is negative, start new subassray from nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            //update the maxSum if currentSum is greater
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }

    //divide and conquer

    private int max(int[] nums){

        return maxSubArray(nums, 0, nums.length-1);
    }

    private int maxSubArray(int[] nums, int left, int right){
        if(left == right) return nums[left]; //base case: one element 
        int mid = left + (right - left)/2;
        int leftMax = maxSubArray(nums, left, mid);
        int rightMax = maxSubArray(nums, mid + 1, right);
        int crossMax = maxCross(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCross(int[] nums, int left, int mid, int right){
        int leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE, sum = 0;
        for(int i = mid; i >= left; i--){
            sum += nums[i];
            leftSum = Math.max(sum, leftSum);
        }

        sum = 0;
        for(int i = mid + 1; i <= right; i++){
            sum += nums[i];
            rightSum = Math.max(sum, rightSum);
        }

        return leftSum + rightSum;
    }
}
