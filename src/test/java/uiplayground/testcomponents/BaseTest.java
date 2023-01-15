package uiplayground.testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    private WebDriver driver = null;
    WebDriverWait wait;

    //Initializing the web driver based on the configuration set in the global.prop file
    @BeforeClass
    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/org/spritecloud/globalData.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if (driver == null) {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    FirefoxOptions optionsF = new FirefoxOptions();
                    optionsF.addArguments("headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    EdgeOptions optoinsE = new EdgeOptions();
                    optoinsE.addArguments("headless");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;
    }

    // A method used in refreshing the web page
    public void refreshWebPage() {
        driver.navigate().refresh();
    }

    // preparing a wait object to be used in explicit waits
    public WebDriverWait waitElement() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait;
    }

    // closing the driver instances
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
