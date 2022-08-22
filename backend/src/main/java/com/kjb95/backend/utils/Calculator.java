package com.kjb95.backend.utils;

public class Calculator {

    public static String calculateDuration(long duration, String lang) {
        String number;
        String unit = null;

        if (duration < 60) {
            number = Long.toString(duration);
            if (lang.equals("ko")) {
                unit = "초 전";
            }
            else if (lang.equals("en")) {
                unit = " seconds ago";
            }
        }
        else if (duration < 3600) {
            number = Long.toString(duration / 60);
            if (lang.equals("ko")) {
                unit = "분 전";
            }
            else if (lang.equals("en")) {
                unit = " minutes ago";
            }
        }
        else if (duration < 86400) {
            number = Long.toString(duration / 3600);
            if (lang.equals("ko")) {
                unit = "시간 전";
            }
            else if (lang.equals("en")) {
                unit = " hours ago";
            }
        }
        else if (duration < 2628000) {
            number = Long.toString(duration / 86400);
            if (lang.equals("ko")) {
                unit = "일 전";
            }
            else if (lang.equals("en")) {
                unit = " days ago";
            }
        }
        else if (duration < 31536000) {
            number = Long.toString(duration / 2628000);
            if (lang.equals("ko")) {
                unit = "개월 전";
            }
            else if (lang.equals("en")) {
                unit = " months ago";
            }
        }
        else {
            number = Long.toString(duration / 31536000);
            if (lang.equals("ko")) {
                unit = "년 전";
            }
            else if (lang.equals("en")) {
                unit = " years ago";
            }
        }
        return (number + unit);
    }

    public static String NumberToString(String header, long viewCount, String footer, String lang) {
        long number = 0;
        String unit = "";

        if (lang.equals("ko")) {
            if (viewCount < 1000) {
                number = viewCount;
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
        }
        else if (lang.equals("en")) {
            if (viewCount < 1000) {
                number = viewCount;
            }
            else if (viewCount < 1000000) {
                number = viewCount / 1000;
                unit = " thousands";
            }
            else if (viewCount < 1000000000) {
                number = viewCount / 1000000;
                unit = " millions";
            }
            else {
                number = viewCount / 1000000000;
                unit = " billions";
            }
        }

        return (header + number + unit + footer);
    }
}
