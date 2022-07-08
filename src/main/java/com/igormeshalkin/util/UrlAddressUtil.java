package com.igormeshalkin.util;

public class UrlAddressUtil {

    public static String getAddressForResetMainPage(String query) {
        if(query == null || !query.contains("&")) {
            return "/";
        }

        int index = query.indexOf("&");
        return "/?" + query.substring(0, index);
    }

    public static String getAddressForResetUsersPage(String query) {
        if(query == null || !query.contains("&")) {
            return "/api/admin/users";
        }

        int index = query.indexOf("&");
        return "/api/admin/users?" + query.substring(0, index);
    }

    public static boolean isEmpty(String query) {
        if(query == null || !query.contains("&")) {
            return true;
        }

        int index = query.indexOf("&");
        boolean result = true;
        while(true) {
            index = query.indexOf("=", index + 1);

            if(query.length() - 1 == index) {
                break;
            }

            if (query.charAt(index + 1) != '&') {
                result = false;
                break;
            }
        }

        return result;
    }
}
