import java.util.Arrays;

class MergeSortedArrays {
   // Why we start from the back
   // Because nums1 already contains elements in the front.
   // If we start filling from index 0, we may overwrite values we still need.
   // So we compare the biggest elements first and place them at the last position.

    public int[] mergeSortedArrays(int[] nums1, int[] nums2, int n1, int n2){
        //nums1 = [1,2,3,0,0,0], n1 = 3 , length = n1 + n2
        //nums2 = [2,5,6], n2 = 3
        //return nums1 as a final sorted array
        //nums3 = [1,2,3,2,5,6] = NO
        //nums1 = [1,2,3,2,5,6] = YES

        int a = n1 - 1; //2 
        int b = n2 - 1; //2 
        int k = n1 + n2 - 1; //5

        while(a >= 0 && b >= 0){ //[2,2],[2,1], [2,0], [1,0]
            //nums1[2]=3, nums1[2]=3, nums1[2]=3, nums1[1]=2
            //nums2[2]=6, nums2[1]=5, nums2[0]=2, nums1[1]=2
            if (nums1[a] > nums2[b]) { //3 > 6, 3 > 5, 3 > 2, 2 > 2
                nums1[k] = nums1[a]; //[1,2,3,3,4,6]
                a--;//1
            }else{
                nums1[k] = nums2[b];//[1,2,3,0,0,6], [1,2,3,0,4,6], [1,2,2,3,4,6]
                b--; //1,0,-1
            }
            k--;//4,3,2,1
        }

        while(b>=0){
            nums1[k] = nums2[b];
            b--;
            k--;
        }
        return nums1;
    }

    public static void main(String[] args) {
        MergeSortedArrays m = new MergeSortedArrays();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        System.out.println("Merge Sorted Arrays: " + Arrays.toString(m.mergeSortedArrays(nums1, nums2, 3, 3)));
    }
    
}
