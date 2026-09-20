class Solution {
    public int reverseDegree(String s) {
        int reverse=0;
        int output=0;
        for(int i=0;i < s.length();i++){
            char ch = s.charAt(i);
            reverse = 26 - (ch - 'a');

            output= output + reverse * (i+1) ;



        }
    return output;    
        
    }
}