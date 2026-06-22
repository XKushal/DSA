package Target;

import java.util.Arrays;
//T= O(n * m), S= O(1)
public class LongestCommonPrefix {
    
    public String longestCommonPrefix(String[] s){

        //edge case
        if(s == null || s.length == 0) return "";
        String prefix = s[0];
        for(int i = 1; i < s.length; i++){//flow, flo, fl
            //reduce prefix until it matches the start of the s[i]
            while(s[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    //non optimal, T = O(n log n * m), S = O(m)
    public String longestPrefix(String[] str){
        //flower, flow, flight

        Arrays.sort(str); //sorts internally by size and accending order of letter
        //For eg, After sorting, flsw, flight, flower
        //here flow is smallest size, and in between flight and flower, i comes before o.

        char [] firstLetters = str[0].toCharArray();
        char [] lastLetters = str[str.length-1].toCharArray();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length; i++){
            if(firstLetters[i] != lastLetters[i]){
                break;
            }
            sb.append(firstLetters[i]);
        }
        return sb.toString();
    }
}
