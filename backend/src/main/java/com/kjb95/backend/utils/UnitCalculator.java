package com.kjb95.backend.utils;

public class UnitCalculator {

    /**
     * 초를 크기에 따라 초, 분, 시간, 일, 월, 년 등 으로 단위 계산
     *
     * @param secondsUnit 계산될 초 단위
     * @param lang        다국어 설정값
     * @return ex) 22일 전
     */
    public static String calculateDurationBySeconds(long secondsUnit, String lang) {
        String number;
        String unit = null;

        if (secondsUnit < 60) {
            number = Long.toString(secondsUnit);
            if (lang.equals("ko")) {
                unit = "초 전";
            }
            else if (lang.equals("en")) {
                unit = " seconds ago";
            }
        }
        else if (secondsUnit < 3600) {
            number = Long.toString(secondsUnit / 60);
            if (lang.equals("ko")) {
                unit = "분 전";
            }
            else if (lang.equals("en")) {
                unit = " minutes ago";
            }
        }
        else if (secondsUnit < 86400) {
            number = Long.toString(secondsUnit / 3600);
            if (lang.equals("ko")) {
                unit = "시간 전";
            }
            else if (lang.equals("en")) {
                unit = " hours ago";
            }
        }
        else if (secondsUnit < 2628000) {
            number = Long.toString(secondsUnit / 86400);
            if (lang.equals("ko")) {
                unit = "일 전";
            }
            else if (lang.equals("en")) {
                unit = " days ago";
            }
        }
        else if (secondsUnit < 31536000) {
            number = Long.toString(secondsUnit / 2628000);
            if (lang.equals("ko")) {
                unit = "개월 전";
            }
            else if (lang.equals("en")) {
                unit = " months ago";
            }
        }
        else {
            number = Long.toString(secondsUnit / 31536000);
            if (lang.equals("ko")) {
                unit = "년 전";
            }
            else if (lang.equals("en")) {
                unit = " years ago";
            }
        }
        return (number + unit);
    }

    /**
     * 숫자를 크기에 따라 천, 만, 억 등 으로 단위 계산
     *
     * @param prefix          문자열의 접두사
     * @param referenceNumber 계산될 숫자
     * @param suffix          문자열의 접미사
     * @param lang            다국어 설정값
     * @return ex) 조회수 10만명
     */
    public static String calculateStringByNumber(String prefix, long referenceNumber, String suffix, String lang) {
        long number = 0;
        String unit = "";

        if (lang.equals("ko")) {
            if (referenceNumber < 1000) {
                number = referenceNumber;
            }
            else if (referenceNumber < 10000) {
                number = referenceNumber / 1000;
                unit = "천";
            }
            else if (referenceNumber < 100000000) {
                number = referenceNumber / 10000;
                unit = "만";
            }
            else {
                number = referenceNumber / 100000000;
                unit = "억";
            }
        }
        else if (lang.equals("en")) {
            if (referenceNumber < 1000) {
                number = referenceNumber;
            }
            else if (referenceNumber < 1000000) {
                number = referenceNumber / 1000;
                unit = " thousands";
            }
            else if (referenceNumber < 1000000000) {
                number = referenceNumber / 1000000;
                unit = " millions";
            }
            else {
                number = referenceNumber / 1000000000;
                unit = " billions";
            }
        }

        return (prefix + number + unit + suffix);
    }
}
