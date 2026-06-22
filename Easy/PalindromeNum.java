class Solution1 {
    public boolean isPalindrome(int x){
        //121 
        //rem 21  = 21
        //rem 1

        if (x < 0) {
            return false;
        }

        int a = x;
        int rev = 0;
        while(x != 0){
            int rem = x % 10;
            rev = rev * 10 + rem;
            x = x/10;
        }
        return a == rev;
    }

    //T = O(log10 n), S = O(1)
    public boolean isPalindrome2(int x){
        // negative numbers are never palindrome
        // numbers ending in 0 cannot be palindrome unless the number itself is 0
         if(x<10 && x>=0)
            return true;
        
        //negatives cannot be palindrome nor can nums ending with zero
        if(x<0 || x%10==0)
            return false;

        int reversedHalf = 0;
        while(x > reversedHalf){
            int rem = x % 10;
            reversedHalf = reversedHalf * 10 + rem;
            x = x/10;
        }
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public boolean isStringPalindrome(String s){
        String caseInsString = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right --;
        }
        return true;
    }

    //final boss
    public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}


    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        System.out.println("Solution1: " + sol.isPalindrome(121));
        System.out.println("Solution2: " + sol.isPalindrome2(0));
        System.out.println("Solution2: " + sol.isStringPalindrome("Dad"));
    }
    
}
