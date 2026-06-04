/*
Given a positive integer n, write a function that returns the number of 
set bits in its binary representation (also known as the Hamming weight).

This is also called the Hamming weight. 
For example, 11 in binary is 1011, so the answer is 3
 */

//Brian Kernighan’s Algorithm

//trick: n = n & (n - 1); (This removes the rightmost 1 bit from n)
//When you subtract 1 from a number, the rightmost 1 becomes 0, and all bits after it flip.

//Time: O(k)
//Space: O(1)
//where k is the number of 1 bits in n
public class hammingWeight {
    
    public int hummingWeight(int n){
        int count = 0;

        for(int i = 0; i < 32; i++){
            count += n & 1;
            n = n >>> 1; //unsigned right shift
        }

        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;

        while (n != 0) {
            n &= (n - 1);
            count++;
        }

        return count;
    }
}
