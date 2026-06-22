package virtusa;

import java.util.HashMap;
import java.util.Map;

//Given a string s, find the length of the longest substring without duplicate characters.
public class LongestSubstring {
    public int longestSubstringLength(String s){
        //abcabcbb = 3, bbbbb = 1, pwwkew = 3
        //using the sliding window technique
        Map<Character, Integer> map = new HashMap<>();

        int maxLength = 0;
        int left = 0;

        for(int right = 0; right < s.length(); right++){//pwwkew
            char ch = s.charAt(right);//p
            if(map.containsKey(ch) && map.get(ch) >= left){
                left = map.get(ch) + 1;
            }
            map.put(ch, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public String longestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (map.containsKey(ch) && map.get(ch) >= left) {
                left = map.get(ch) + 1;
            }

            map.put(ch, right);

            int currentLength = right - left + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
                start = left;
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        LongestSubstring obj = new LongestSubstring();
        System.out.println(obj.longestSubstringLength("pwwkew")); // 3
        System.out.println(obj.longestSubstring("pwwkew")); // 3
    }
}
