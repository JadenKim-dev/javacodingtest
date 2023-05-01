package com.company;

public class 바탕화면정리 {
    public static void main(String[] args) {

    }
    public int[] solution(String[] wallpaper) {
        int minh = wallpaper.length;
        int maxh = -1;
        int minw = wallpaper[0].length();
        int maxw = -1;

        for(int i=0 ; i<wallpaper.length; i++) {
            String rowInfo = wallpaper[i];
            for(int j=0; j<rowInfo.length(); j++) {
                char status = rowInfo.charAt(j);
                if (status == '#') {
                    minh = Math.min(minh, i);
                    maxh = Math.max(maxh, i);
                    minw = Math.min(minw, j);
                    maxw = Math.max(maxw, j);
                }
            }
        }
        return new int[] { minh, minw, maxh, maxw };
    }
}
