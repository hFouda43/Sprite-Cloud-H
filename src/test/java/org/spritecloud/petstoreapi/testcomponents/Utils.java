package org.spritecloud.petstoreapi.testcomponents;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {

    public static RequestSpecification request;

    // Formatting the log file name to include timestamp
    private static String FILE_PATH = "testresults/api/logs/APITestsLog_";
    private static String FILE_EXTENSION = "txt";
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    String filename = FILE_PATH + dateFormat.format(new Date()) + "." + FILE_EXTENSION;

    //Implementing common request specification to be used by the requests

    public RequestSpecification requestSpecification() throws IOException {
        if (request == null) {
            PrintStream logFile = new PrintStream(new FileOutputStream(filename));
            request = new RequestSpecBuilder().
                    setBaseUri(getGlobalValues("baseUrl")).
                    setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                    addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                    .build();
        }
        return request;
    }

//a generic method getting global values from global.properties file
    public static String getGlobalValues(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/org/spritecloud/globalData.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }
}
