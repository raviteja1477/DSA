class Solution {
    public int totalNumbers(int[] digits) {
        int first ;
        int second ;
        int third ;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i< digits.length;i++){
            if(digits[i] != 0){
                 for(int j=0;j<digits.length;j++){
                    if (j != i){
                        for(int k=0;k< digits.length;k++){
                            if (k != i && k != j && digits[k] % 2 == 0){
                                int number = digits[i] * 100 + digits[j] * 10 + digits[k];
set.add(number);
                            }
                        }
                    }
                }    
            }
           
        } 
    return set.size();    
        
    }
}