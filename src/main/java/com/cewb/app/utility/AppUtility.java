package com.cewb.app.utility;

import java.util.List;

public class AppUtility {
	
	/**
     * Checks if the given object is null
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return (obj == null);
    }
    
    /**
     * Concatenates the given array of String with a delimiter
     * @param stringList
     * @param delimiter
     * @return
     */
    public static String arrayToString(List<String> stringList, String delimiter) {
        if(stringList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
                
        for(String s : stringList) {
            sb.append(s);
            sb.append(delimiter);
        }
        
        /*
         * returns the concatenated string excluding the last delimiter
         */            
        return sb.substring(0, sb.length() - delimiter.length());        
    }
    
    // Add security helpers here once security dependencies are added
}
