//Minimum Bit Flips to Convert Number

class Solution {
    public int minBitFlips(int start, int goal) {
        int count = 0;
        while (start > 0 || goal > 0) {
            if ((start & 1) != (goal & 1)) {
                count++;
            }
            start >>= 1;
            goal >>= 1;
        }
        return count;
    }
}

//by using xor = it compares 2 bits, 0 = same bits; 1 = diff bits
class BItFlips{

    public int minBitFlips(int start, int goal){
        int count = 0;
        int xor = start ^ goal;
        while(xor != 0){
            count += (xor & 1);
            xor >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        BItFlips bitF = new BItFlips();
        System.out.println("Solution1: " + s.minBitFlips(10, 7));
        System.out.println("Solution 2: " + bitF.minBitFlips(10, 7));
        System.out.println("Inbuilt solution: " + Integer.bitCount(10^7));
    }
}
