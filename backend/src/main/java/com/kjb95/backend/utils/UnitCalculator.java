package com.kjb95.backend.utils;

public class UnitCalculator {

    /**
     * 초를 크기에 따라 초, 분, 시간, 일, 월, 년 등 으로 단위 계산
     *
     * @param secondsUnit 계산될 초 단위
     * @param language    다국어 설정값
     * @return ex) 22일 전
     */
    public static String calculateDurationBySeconds(long secondsUnit, String language) {
        String calculatedNumber;
        String calculatedUnit = null;

        if (secondsUnit < 60) {
            calculatedNumber = Long.toString(secondsUnit);
            if (language.equals("ko")) {
                calculatedUnit = "초 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " seconds ago";
            }
        }
        else if (secondsUnit < 3600) {
            calculatedNumber = Long.toString(secondsUnit / 60);
            if (language.equals("ko")) {
                calculatedUnit = "분 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " minutes ago";
            }
        }
        else if (secondsUnit < 86400) {
            calculatedNumber = Long.toString(secondsUnit / 3600);
            if (language.equals("ko")) {
                calculatedUnit = "시간 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " hours ago";
            }
        }
        else if (secondsUnit < 2628000) {
            calculatedNumber = Long.toString(secondsUnit / 86400);
            if (language.equals("ko")) {
                calculatedUnit = "일 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " days ago";
            }
        }
        else if (secondsUnit < 31536000) {
            calculatedNumber = Long.toString(secondsUnit / 2628000);
            if (language.equals("ko")) {
                calculatedUnit = "개월 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " months ago";
            }
        }
        else {
            calculatedNumber = Long.toString(secondsUnit / 31536000);
            if (language.equals("ko")) {
                calculatedUnit = "년 전";
            }
            else if (language.equals("en")) {
                calculatedUnit = " years ago";
            }
        }
        return (calculatedNumber + calculatedUnit);
    }

    /**
     * 숫자를 크기에 따라 천, 만, 억 등 으로 단위 계산
     *
     * @param prefix          문자열의 접두사
     * @param referenceNumber 계산될 숫자
     * @param suffix          문자열의 접미사
     * @param language        다국어 설정값
     * @return ex) 조회수 10만명
     */
    public static String calculateStringByNumber(String prefix, long referenceNumber, String suffix, String language) {
        long calculatedNumber = 0;
        String calculatedUnit = "";

        if (language.equals("ko")) {
            if (referenceNumber < 1000) {
                calculatedNumber = referenceNumber;
            }
            else if (referenceNumber < 10000) {
                calculatedNumber = referenceNumber / 1000;
                calculatedUnit = "천";
            }
            else if (referenceNumber < 100000000) {
                calculatedNumber = referenceNumber / 10000;
                calculatedUnit = "만";
            }
            else {
                calculatedNumber = referenceNumber / 100000000;
                calculatedUnit = "억";
            }
        }
        else if (language.equals("en")) {
            if (referenceNumber < 1000) {
                calculatedNumber = referenceNumber;
            }
            else if (referenceNumber < 1000000) {
                calculatedNumber = referenceNumber / 1000;
                calculatedUnit = " thousands";
            }
            else if (referenceNumber < 1000000000) {
                calculatedNumber = referenceNumber / 1000000;
                calculatedUnit = " millions";
            }
            else {
                calculatedNumber = referenceNumber / 1000000000;
                calculatedUnit = " billions";
            }
        }

        return (prefix + calculatedNumber + calculatedUnit + suffix);
    }
}
