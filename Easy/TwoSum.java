import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TwoSum {
    public int[] twoSum(int[] nums, int target){

      //  nums = [2, 7, 11, 15]
      //  target = 26
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i < nums.length; i++){//i=0, i=1, i=2, i=3
            int complement = target - nums[i]; //26 - 2 = 24, 26 - 7 = 19, 26 - 11 = 15, 26 - 15 = 11

            if(map.containsKey(complement)){ //is 24 present?, is 19 present?, is 15 present?, is 11 present?
                return new int[] {map.get(complement), i}; //{2:3}
            }
            map.put(nums[i], i); //{2:0, 7:1, 11:2}
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int[] twoSum = ts.twoSum(nums, 100);
        System.out.println("Two sum: " + Arrays.toString(twoSum));
    }
}
