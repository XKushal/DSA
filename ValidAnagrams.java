class ValidAnagrams{
    //both strings use the same letters, same number of times, order can be different
    public boolean isValidAnagrams(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }

        int[] count = new int[26];

        //anagrams //ramsana
        //a,-,cc,-,-,f, 
        //cafc //facc
        for(int i = 0; i< s1.length(); i++){
            int c = s1.charAt(i) - 'a'; //a-'a'=0;//3=1=1,1=1,6=1
            int d = s2.charAt(i) - 'a'; // r-'1'=5, //3=2-1-1, 1=1-1, 6=1-1
            count[c] ++;
            count[d]--;
        }

        for(int num: count){
            if(num != 0)
                return false;
        }

        return true;

    }
}