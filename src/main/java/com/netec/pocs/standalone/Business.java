package com.netec.pocs.standalone;

public class Business {

    public String toUpperCaseString(final String lowerCaseString) {

        if (lowerCaseString == null || lowerCaseString.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("Problem in uppercasing");
        } else {
            return lowerCaseString.toUpperCase();
        }
    }
}
