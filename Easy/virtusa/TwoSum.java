package virtusa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int [] nums, int target){
        // we will save the num's value as key, and their index as value in a hash map
        //take the complement of target and each nums at each index
        //if complement value is in the map, then return the value of that key
        //also return the index at which complement's value is found in map
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i< nums.length; i++){
            int compelement = target - nums[i];
            if(map.containsKey(compelement)){
                return new int[] {map.get(compelement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
         TwoSum ts = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int[] twoSum = ts.twoSum(nums, 26);
        System.out.println("Two sum: " + Arrays.toString(twoSum));
        
    }
}
