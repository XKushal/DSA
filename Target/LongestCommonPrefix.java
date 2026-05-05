package Target;

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
}
