package Elevence.Easy;

//return the missing number from an array of "n" numbers ranging from [0,n]
//if n = 4, [0,2,4,3], 
public class MissingNumber {
    //sort => 0,[1],2,3,4 => loop thru it, num != index, return that index => O(n^2)
    //n=4, sum = n*(n+1)/2
    public int getMissingNum(int[] nums){
        int n = nums.length;
        int sum = 0;
        int totalSum = n * (n + 1)/2;
        for(int num: nums){
            sum = sum + num;
        }
        return totalSum - sum;
    }

    public static void main(String[] args) {
        MissingNumber n = new MissingNumber();
        int [] nums = {0,1,3,4};
        System.out.println("MissingNumber: " + n.getMissingNum(nums));
    }

}
