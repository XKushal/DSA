package Target;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {
    
    public String sortCharacterByFrequency(String s){
        //aabbccc, //aaAbCC
        Map<Character, Integer> f = new HashMap<>();
        for(char ch: s.toCharArray()){
            f.put(ch, f.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Character> heap = new PriorityQueue<>(
            (a,b) -> f.get(b) - f.get(a)
        );
        heap.addAll(f.keySet());

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            char c = heap.poll();
            for(int i = 0; i< f.get(c); i++){
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
