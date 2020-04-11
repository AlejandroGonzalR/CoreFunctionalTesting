public class Constants {

    // Build constants
    public static String BASE_URL = "http://localhost:8080/";// "https://vuesimpleform.web.app/";
    public static String DRIVER_PATH = "src/main/resources/chromedriver";

    // Test constants
    public static String BASE_SUREFIRE_OUT_PATH = "test-output/";
    public static String SEND_MESSAGE_PATH = BASE_SUREFIRE_OUT_PATH + "send-message";
    public static String VALIDATE_LAYOUT_PATH = BASE_SUREFIRE_OUT_PATH + "validate-layout";
    public static String REQUIRED_FIELDS_PATH = BASE_SUREFIRE_OUT_PATH + "required-fields";
}
