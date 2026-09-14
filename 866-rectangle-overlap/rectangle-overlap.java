class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1[2] > rec2[0] &&  // right1 > left2
        rec2[2] > rec1[0]  && // right2 > left1
        rec1[3] > rec2[1]  &&// top1 > bottom2
        rec2[3] > rec1[1]) {  // top2 > bottom1
            return true;    
        }
    return false;    
    }
}