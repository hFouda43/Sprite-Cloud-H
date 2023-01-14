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
import java.util.Properties;

public class Utils {

    public static RequestSpecification request;
    public RequestSpecification requestSpecification() throws IOException {
        if(request==null) {
            PrintStream logFile = new PrintStream(new FileOutputStream("testresults/api/logs/logging.txt"));
            request = new RequestSpecBuilder().
                    setBaseUri(getGlobalValues("baseUrl")).
                    setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                    addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                    .build();
        }
        return request;
    }


    public static String getGlobalValues(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fileInputStream=new FileInputStream("src/main/java/org/spritecloud/globalData.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }
}
