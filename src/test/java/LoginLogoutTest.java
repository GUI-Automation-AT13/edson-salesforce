import configuration.Configurations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static configuration.EnvironmentValues.obtainEnvVariables;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginLogoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void login() {
        driver.get("https://jala3-dev-ed.my.salesforce.com/");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        driver.findElement(By.id("username")).sendKeys(obtainEnvVariables("USER"));
        driver.findElement(By.id("password")).sendKeys(obtainEnvVariables("PASSWORD"));
        driver.findElement(By.id("Login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,\'Home\')]")));
    }

    @Test
    public void logout() {
        driver.get("https://jala3-dev-ed.my.salesforce.com/");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        driver.findElement(By.id("username")).sendKeys(obtainEnvVariables("USER"));
        driver.findElement(By.id("password")).sendKeys(obtainEnvVariables("PASSWORD"));
        driver.findElement(By.id("Login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,\'Home\')]")));

        driver.get("https://jala3-dev-ed.lightning.force.com/lightning/page/home");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".profileTrigger > .uiImage")));
        driver.findElement(By.cssSelector(".profileTrigger > .uiImage")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log Out")));
        driver.findElement(By.linkText("Log Out")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".usernamelabel")));
        assertThat(driver.findElement(By.cssSelector(".usernamelabel")).getText(), is("Username"));
    }

    @After
    public void quitWebDriver() {
        driver.quit();
    }
}
