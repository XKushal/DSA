package virtusa;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public int romanToInteger(String s){
        //I iterate through the string once. For each symbol, 
        // I compare it with the next symbol. 
        // If the current value is smaller than the next one, I subtract it; otherwise, I add it. 
        // This handles both normal and subtractive Roman numeral cases in O(n) time.
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        //s = III, s= IV, s= XXI
        int total = 0;
        for(int i=0; i< s.length(); i++){
            int current = map.get(s.charAt(i));
            if(i < s.length()-1 && current < map.get(s.charAt(i + 1))){
                total -= current;
            }else{
                total += current;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        RomanToInteger obj = new RomanToInteger();
        System.out.println(obj.romanToInteger("III"));     // 3
        System.out.println(obj.romanToInteger("LVIII"));   // 58
        System.out.println(obj.romanToInteger("MCMXCIV")); // 1994
    }
}
