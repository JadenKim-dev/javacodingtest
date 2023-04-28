package com.company;

import java.util.HashMap;
import java.util.Locale;

public class 뉴스클러스터링 {
    public static void main(String[] args) {
        int answer = solution("FRANCE", "french");
        System.out.println(answer);
    }

    public static int solution(String str1, String str2) {
        str1 = str1.toLowerCase(Locale.ROOT);
        str2 = str2.toLowerCase(Locale.ROOT);

        HashMap<String, Integer> elementToCntMap1 = convertStringToMap(str1);
        HashMap<String, Integer> elementToCntMap2 = convertStringToMap(str2);

        if(elementToCntMap1.size() == 0 && elementToCntMap2.size() == 0) {
            return 65536;
        }

        HashMap<String, Integer> intersectedData = new HashMap<>();
        HashMap<String, Integer> unionedData = new HashMap<>();
        for (String key : elementToCntMap1.keySet()) {
            int cnt = elementToCntMap1.get(key);
            if (elementToCntMap2.containsKey(key)) {
                intersectedData.put(
                        key,
                        Math.min(cnt, elementToCntMap2.get(key))
                );
                unionedData.put(
                        key,
                        Math.max(cnt, elementToCntMap2.get(key))
                );
            } else {
                unionedData.put(key, cnt);
            }
        }

        for (String key : elementToCntMap2.keySet()) {
            if (intersectedData.containsKey(key)) continue;
            int cnt = elementToCntMap2.get(key);
            unionedData.put(key, cnt);
        }

        int intersectScore = intersectedData.values().stream().reduce(0, Integer::sum);
        int unionScore = unionedData.values().stream().reduce(0, Integer::sum);

        double answer = intersectScore / (double) unionScore * 65536;
        return (int) answer;
    }

    private static HashMap<String, Integer> convertStringToMap(String str) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i++) {
            if (!Character.isAlphabetic(str.charAt(i))
                    || !Character.isAlphabetic(str.charAt(i + 1))) continue;
            String substr = str.substring(i, i + 2);
            if (map.containsKey(substr)) {
                map.put(substr, map.get(substr) + 1);
            } else {
                map.put(substr, 1);
            }
        }
        return map;
    }
}
