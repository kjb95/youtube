package com.kjb95.backend.utils;

public class Calculator {
    public static String calculateDuration(long duration) {
        long number;
        String unit;

        if (duration < 60) {
            number = duration;
            unit = "초 전";
        }
        else if (duration < 3600) {
            number = duration / 60;
            unit = "분 전";
        }
        else if (duration < 86400) {
            number = duration / 3600;
            unit = "시간 전";
        }
        else if (duration < 2628000) {
            number = duration / 86400;
            unit = "일 전";
        }
        else if (duration < 31536000) {
            number = duration / 2628000;
            unit = "개월 전";
        }
        else {
            number = duration / 31536000;
            unit = "년 전";
        }
        return (number + unit);
    }

    public static String NumberToString(String header, long viewCount, String footer) {
        long number;
        String unit;

        if (viewCount < 1000) {
            number = viewCount;
            unit = "";
        }
        else if (viewCount < 10000) {
            number = viewCount / 1000;
            unit = "천";
        }
        else if (viewCount < 100000000) {
            number = viewCount / 10000;
            unit = "만";
        }
        else {
            number = viewCount / 100000000;
            unit = "억";
        }

        return (header + number + unit + footer);
    }
}
