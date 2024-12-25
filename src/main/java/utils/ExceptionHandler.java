package utils;

public final class ExceptionHandler {

    // Generic method to handle exceptions
    public static void handleException(String message, Exception e) {
        throw new RuntimeException(message, e);
    }

    // Generic method for handling Selenium-specific exceptions
    public static void handleWebDriverException(String action, Exception e) {
        String errorMessage = "Error occurred while performing action: " + action;
        throw new RuntimeException(errorMessage, e); // Re-throw or wrap in a custom exception
    }
}
