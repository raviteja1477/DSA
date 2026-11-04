class Solution {
    public int lengthOfLongestSubstring(String s) {

        int result=0;
        Map<Character,Integer> mp=new HashMap<>();
        int left = 0;
        for(int right=0; right<s.length(); right++){
            if(mp.containsKey(s.charAt(right))){
                if(mp.get(s.charAt(right))>=left){
                    left=mp.get(s.charAt(right))+1;
                }
            }

            mp.put(s.charAt(right),right);
            result=Math.max(result,right-left+1);
        }

        return result;
    }
}