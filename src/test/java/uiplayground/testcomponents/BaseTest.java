package uiplayground.testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;
    }

    public void goBackward() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void refreshWebPage() {
        driver.navigate().refresh();
    }

    public void waitForPageToLoad() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
public WebDriverWait waitElement(){
    wait= new WebDriverWait(driver,Duration.ofSeconds(5));
    return wait;
}
    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
