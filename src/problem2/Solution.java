package problem2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        // 테스트 케이스 1
        int[] coins1 = {1, 2, 3};
        int sum1 = 4;
        System.out.println("테스트 케이스 1:");
        printCombinations(coins1, sum1);

        System.out.println();

        // 테스트 케이스 2
        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;
        System.out.println("테스트 케이스 2:");
        printCombinations(coins2, sum2);
    }

    public static void printCombinations(int[] coins, int sum) {
        // DP 배열 정의 및 초기화
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i <= sum; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(new ArrayList<>()); // 합계 0인 경우 초기값

        Arrays.sort(coins);

        // DP 테이블 업데이트
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                for (List<Integer> combination : dp.get(i - coin)) {
                    List<Integer> newCombination = new ArrayList<>(combination);
                    newCombination.add(coin);
                    dp.get(i).add(newCombination);
                }
            }
        }

        // 결과 출력
        System.out.println("합계를 만들 수 있는 방법의 수: " + dp.get(sum).size());
        System.out.println("조합 방법:");
        List<List<Integer>> result = new ArrayList<>(dp.get(sum));

        // 최종적으로 사전 순 정렬
        result.sort((list1, list2) -> {
            int size1 = list1.size();
            int size2 = list2.size();
            for (int i = 0; i < Math.min(size1, size2); i++) {
                int cmp = Integer.compare(list1.get(i), list2.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(size1, size2);
        });


        for (List<Integer> combination : result) {
            System.out.println(combination);
        }

    }
}