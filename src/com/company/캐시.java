package com.company;

import java.util.ArrayList;
import java.util.Locale;

public class 캐시 {
    public static void main(String[] args) {
        int answer = solution(0, new String[] {"LA", "LA"});
        System.out.println(answer);
    }

    public static int solution(int cacheSize, String[] cities) {
        ArrayList<String> cache = new ArrayList<>();
        int answer = 0;
        for (String city : cities) {
            city = city.toLowerCase(Locale.ROOT);
            boolean isHit = false;
            for (int i = 0; i < cache.size(); i++) {
                String cachedCity = cache.get(i);
                if(cachedCity.equals(city)) {
                    cache.remove(i);
                    cache.add(city);
                    isHit = true;
                    answer += 1;
                    break;
                }
            }
            if(!isHit) {
                answer += 5;
                if(cacheSize == 0) continue;
                if(cache.size() >= cacheSize && cache.size() > 0) {
                    cache.remove(0);
                }
                cache.add(city);
            }
        }
        return answer;
    }
}
