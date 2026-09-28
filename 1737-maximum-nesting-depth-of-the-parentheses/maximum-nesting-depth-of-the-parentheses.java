class Solution {
    public int maxDepth(String s) {
        int count =0;
        int greater = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == '('){
                count= count+1;
                if(count>greater){
                    greater = count;
                }  
            }      
            if (s.charAt(i)==')'){
                count = count -1;
            }
                
            

        }
    return greater;    
        
    }
}
