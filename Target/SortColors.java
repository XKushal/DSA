package Target;

public class SortColors {
    
    private void sortColors(int[] nums){
        int low = 0, mid = 0, high = nums.length-1;

        while(mid <= high){
            if(nums[mid] == 0){
                int temp = nums[low];
                nums[low++] = nums[mid];
                nums[mid++] = temp;
            }else if(nums[mid] == 1){
                mid++;
            }else{
                int temp = nums[high];
                nums[high--] = nums[mid];
                nums[mid] = temp;
            }
        }
    }

    //nested loop

    public void sort(int[] nums){
        int n = nums.length;
        int index = 0;
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j < n; j++){
                if(nums[j] == i){
                    nums[index++] = i;
                }
            }
        }
    }
}
