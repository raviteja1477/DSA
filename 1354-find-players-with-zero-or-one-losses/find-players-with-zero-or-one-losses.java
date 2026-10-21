import java.util.*;

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        int[] losses = new int[100001];

        for (int[] match : matches) {
            losses[match[1]]++;
            if (losses[match[0]] == 0) {
                losses[match[0]] = 0;
            }
        }

        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>();

        boolean[] played = new boolean[100001];

        for (int[] match : matches) {
            played[match[0]] = true;
            played[match[1]] = true;
        }

        for (int i = 1; i <= 100000; i++) {
            if (played[i]) {
                if (losses[i] == 0) {
                    zero.add(i);
                } else if (losses[i] == 1) {
                    one.add(i);
                }
            }
        }

        List<List<Integer>> answer = new ArrayList<>();
        answer.add(zero);
        answer.add(one);

        return answer;
    }
}