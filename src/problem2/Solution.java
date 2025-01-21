package problem2;

import java.util.ArrayList;
import java.util.List;

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

    // 모든 가능한 조합을 저장하고 출력하는 함수
    public static void printCombinations(int[] coins, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        findCombinations(coins, sum, 0, combination, results);

        // 결과 출력
        System.out.println("총 가짓수: " + results.size());
        System.out.println("조합 방법:");
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }

    // 재귀적으로 조합을 찾는 백트래킹 함수
    private static void findCombinations(int[] coins, int remaining, int start, List<Integer> combination, List<List<Integer>> results) {
        if (remaining == 0) { // 합계가 0이 되면 현재 조합 저장
            results.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < coins.length; i++) {
            if (remaining >= coins[i]) {
                combination.add(coins[i]);
                findCombinations(coins, remaining - coins[i], i, combination, results);
                combination.remove(combination.size() - 1);
            }
        }
    }
}