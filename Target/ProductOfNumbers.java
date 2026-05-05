package Target;

import java.util.ArrayList;
import java.util.List;
//applying basic property of multiplication
//if P[i] = a₀ × a₁ × ... × aᵢ, Then: P[i]/P[j]​=ai+1​×ai+2​×⋯×aj​
public class ProductOfNumbers {
    List<Integer> prefixIntegers; //store prefix products 
    int indexOflastZero; //track last zero position
    public ProductOfNumbers() {
        prefixIntegers = new ArrayList<>();
        prefixIntegers.add(1);
        indexOflastZero = -1;
    }
    
    public void add(int num) {
        if(num == 0){
            prefixIntegers = new ArrayList<>();
            prefixIntegers.add(1);
            indexOflastZero = prefixIntegers.size()-1;
        }else{
            int last = prefixIntegers.get(prefixIntegers.size()-1);
            prefixIntegers.add(last*num);
        }
    }
    
    //1,2,3,4,5,6
    public int getProduct(int k) {
        if (k >= prefixIntegers.size()) return 0; //if k crosses last zero, product is 0
        int n = prefixIntegers.size() - 1;
        return prefixIntegers.get(n)/prefixIntegers.get(n-k);
    }

    /*
        class ProductOfNumbers {
    List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            prefix.add(1);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }
    
    public int getProduct(int k) {
        int n = prefix.size();

        if (k >= n) {
            return 0;
        }

        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}
    
    */
}
