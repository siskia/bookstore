package com.pluralsight.bookstore.util;

public class TextUtil {
	
	// ======================================
    // =          Business methods          =
    // ======================================

    public String sanitize(String textToSanitize) {
        return textToSanitize.replaceAll("\\s+", " "); //replace all double or triple spaces with a single space
    }
}
