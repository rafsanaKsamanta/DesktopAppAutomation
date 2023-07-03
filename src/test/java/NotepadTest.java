import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

// class create and give appropriate name

public class NotepadTest {
    private static WindowsDriver notepadSession = null;

    public static String getDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Windows\\system32\\notepad"); // define path of the application
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            notepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void cleanApp() {
        notepadSession.quit();
        setUp();
    }

    @AfterSuite
    public void tearDown() {
        notepadSession.quit();
    }

    @Test
    public void checkAboutWindow() {
        notepadSession.findElementByName("Settings").click();
        notepadSession.findElementByName("Help").click();
//        notepadSession.findElementByName("OK").click();
    }

    @Test
    public void sendTestText() {
        notepadSession.findElementByName("Edit").click();
        notepadSession.findElementByName("Time/Date").click();
    }
}
