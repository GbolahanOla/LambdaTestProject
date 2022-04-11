package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class TestNGTodo {
    String username = System.getenv("LT_USERNAME") == null ? "gbolahan.ola" : System.getenv("LT_USERNAME");

    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "8JCXeqV503GAGLGFyg6g884BAW49DFKoOVwR1og0TXLlAS0IuI" : System.getenv("LT_ACCESS_KEY");

    public static RemoteWebDriver driver = null;

    public String gridURL = "@hub.lambdatest.com/wd/hub";

    public String status = "failed";
    public String Status;


    @BeforeClass

    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", "Chrome");

        capabilities.setCapability("version", "92.0");

        capabilities.setCapability("platform", "Windows 10"); // If this cap isn't specified, it will just get the any available one

        capabilities.setCapability("resolution", "1024x768");

        capabilities.setCapability("build", "MyLambdaTestNG(IntelliJ)");

        capabilities.setCapability("name", "TestNGJavaSampleTest-01");

        capabilities.setCapability("network", true); // To enable network logs

        capabilities.setCapability("visual", true); // To enable step by step screenshot

        capabilities.setCapability("video", true); // To enable video recording

        capabilities.setCapability("console", true); // To capture console logs

        try {

            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);

        } catch (MalformedURLException e) {

            System.out.println("Invalid grid URL");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }


    @Test

    public void testSimple() throws Exception {

        try {

            // Change it to production page

            driver.get("https://lambdatest.github.io/sample-todo-app/");


            // Let's mark done first two items in the list.

            driver.findElement(By.name("li1")).click();

            driver.findElement(By.name("li2")).click();


            // Let's add an item in the list.

            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");

            driver.findElement(By.id("addbutton")).click();


            // Let's check that the item we added is added in the list.

            String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
            if (enteredText.equals("Yey, Let's add it to list")) {

                status = "true";

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }


    @AfterClass

    public void tearDown() throws Exception {

        if (driver != null) {

            ((JavascriptExecutor) driver) .executeScript("lambda-status=" + status);

            driver.quit();

        }

    }



}
