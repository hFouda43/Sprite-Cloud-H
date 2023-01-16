package org.spritecloud.petstoreapi.objects.requestpreparation;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {
    public static RequestSpecification request;
    // Formatting the log file name to include timestamp
    private static String FILE_PATH = "testresults/api/logs/APITestsLog_";
    private static String FILE_EXTENSION = "txt";
    static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    static String filename = FILE_PATH + dateFormat.format(new Date()) + "." + FILE_EXTENSION;
    static PrintStream logFile = createLogFile();

    //Implementing request specifications to be used by the Tests
    //Add Pet Request spec
    public static RequestSpecification addPetRequestSpecification() throws IOException {
        request = new RequestSpecBuilder().
                setBaseUri(getGlobalValues("baseUrl")).
                setContentType(ContentType.JSON).
                addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .build();

        return request;
    }

    //Get Pet Request spec
    public static RequestSpecification getPetRequestSpecification(int pathParameter) throws IOException {
        request = new RequestSpecBuilder().
                setBaseUri(getGlobalValues("baseUrl")).
                addPathParam("pet_Id", pathParameter).
                addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .build();
        return request;
    }

    ////Update Pet Request spec
    public static RequestSpecification updatePetRequestSpecification(int pathParameter) throws IOException {
        request = new RequestSpecBuilder().
                setBaseUri(getGlobalValues("baseUrl")).
                addPathParam("pet_Id", pathParameter).
                setContentType("application/x-www-form-urlencoded").
                addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .build();
        return request;
    }

    //Delete Pet Request
    public static RequestSpecification deletePetRequestSpecification(int pathParameter) throws IOException {
        request = new RequestSpecBuilder().
                setBaseUri(getGlobalValues("baseUrl")).
                addPathParam("pet_Id", pathParameter).
                addHeader("api_key", "special-key").
                addFilter(RequestLoggingFilter.logRequestTo(logFile)).
                addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .build();
        return request;
    }


    //a generic method getting global values from global.properties file
    public static String getGlobalValues(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/org/spritecloud/globalData.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }


    //Creating the log file
    public static PrintStream createLogFile() {
        try {
            logFile = new PrintStream(new FileOutputStream(filename));
            return logFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return logFile;
    }

}
