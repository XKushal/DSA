package Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {
    
    //using priority queue
    public String sortCharacterByFrequency(String s){
        //aabbccc, //aaAbCC
        Map<Character, Integer> f = new HashMap<>();
        
        //Returns the value to which the specified key is mapped, or 
        // defaultValue if this map contains no mapping for the key.
        for(char ch: s.toCharArray()){
            f.put(ch, f.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(
            (a,b) -> f.get(b) - f.get(a)
        );
        priorityQueue.addAll(f.keySet());

        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            char c = priorityQueue.poll();
            for(int i = 0; i< f.get(c); i++){
                sb.append(c);
            }
        }

        return sb.toString();
    }

    //using bucket sort

    public String sortFrequency(String s){
        //tree, t:1, r:1, e:2 bucket: [1[t,r]][2[e]]
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //{(t,1), (r, 1), (e, 2)}
        //size is length + 1, considering a string is "aaaa", so 
        // that makes the highest frequency 4

        List<Character>[] buckeLists = new List[s.length() + 1]; 
        //right now, [ null, null, null, null, ... ]
        /*bucket[0] = null
        bucket[1] = null
        bucket[2] = null
        bucket[3] = null
        */

        for( char ch: map.keySet()){
            if(buckeLists[map.get(ch)] == null) 
                //before initialization:
                /* bucket[1] = null, bucket[2] = null, bucket[3] = null, bucket[4] = null*/
                buckeLists[map.get(ch)] = new ArrayList<>();
                //after initialization:
                /* bucket[1] = [], bucket[2] = [], bucket[3] = [], bucket[4] = []*/
            buckeLists[map.get(ch)].add(ch);
        }

        //finally building the string 
        //1 - t, r
        //2 - e
        //3
        //4
        StringBuilder finalString = new StringBuilder();
        for (int i = buckeLists.length -1; i > 0; i--){
            if(buckeLists[i] != null){
                for(char ch: buckeLists[i]){
                    for(int j = 0; j < i; j++){
                        finalString.append(ch);
                    }
                }
            }
        }
        return finalString.toString();
    }
}
