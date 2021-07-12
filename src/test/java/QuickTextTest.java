import configuration.Configurations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static configuration.EnvironmentValues.obtainEnvVariables;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuickTextTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);

        driver.get("https://jala3-dev-ed.my.salesforce.com/");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        driver.findElement(By.id("username")).sendKeys(obtainEnvVariables("USER"));
        driver.findElement(By.id("password")).sendKeys(obtainEnvVariables("PASSWORD"));
        driver.findElement(By.id("Login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,\'Home\')]")));
    }

    @Test
    public void newQuickText() {
        driver.get("https://jala3-dev-ed.lightning.force.com/lightning/o/QuickText/list?filterName=Recent");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".forceActionLink > div")));
        driver.findElement(By.cssSelector(".forceActionLink > div")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div/div/div/div/div/div/div/input")));
        driver.findElement(By.xpath("//div/div/div/div/div/div/div/div/div/input")).sendKeys(Configurations.QUICK_TEXT_NAME.toEndpoint());
        driver.findElement(By.id("quickTextMessageInputTextArea")).sendKeys(Configurations.QUICK_TEXT_MESSAGE.toEndpoint());
        driver.findElement(By.cssSelector(".slds-button:nth-child(4) > .label")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slds-grid:nth-child(1) > .slds-col > .slds-form-element .uiOutputText")));
        assertThat(driver.findElement(By.cssSelector(".slds-grid:nth-child(1) > .slds-col > .slds-form-element .uiOutputText")).getText(), is(Configurations.QUICK_TEXT_NAME.toEndpoint()));
    }

    @Test
    public void newQuickTextComplet() {
        driver.get("https://jala3-dev-ed.lightning.force.com/lightning/o/QuickText/list?filterName=Recent");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".forceActionLink > div")));
        driver.findElement(By.cssSelector(".forceActionLink > div")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div/div/div/div/div/div/div/input")));
        driver.findElement(By.xpath("//div/div/div/div/div/div/div/div/div/input")).sendKeys(Configurations.QUICK_TEXT_NAME.toEndpoint());
        driver.findElement(By.linkText("Choose...")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Account")));
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.linkText("Choose...")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Account Number")));
        driver.findElement(By.linkText("Account Number")).click();
        driver.findElement(By.id("quickTextMessageInputTextArea")).sendKeys(Configurations.QUICK_TEXT_MESSAGE.toEndpoint());
        driver.findElement(By.linkText("Greetings")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FAQ")));
        driver.findElement(By.linkText("FAQ")).click();
        {
            WebElement dropdown = driver.findElement(By.xpath("//select"));
            dropdown.findElement(By.xpath("//option[. = 'Phone']")).click();
        }
        driver.findElement(By.cssSelector(".actionsContainer:nth-child(2) > .slds-button:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".slds-button:nth-child(4) > .label")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slds-grid:nth-child(1) > .slds-col > .slds-form-element .uiOutputText")));
        assertThat(driver.findElement(By.cssSelector(".slds-grid:nth-child(1) > .slds-col > .slds-form-element .uiOutputText")).getText(), is(Configurations.QUICK_TEXT_NAME.toEndpoint()));
    }

    @Test
    public void deleteAQuickText() {
        driver.get("https://jala3-dev-ed.lightning.force.com/lightning/o/QuickText/list?filterName=Recent");
        driver.manage().window().setSize(new Dimension(Integer.parseInt(Configurations.WIDTH.toEndpoint()), Integer.parseInt(Configurations.HEIGHT.toEndpoint())));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr:nth-child(1) .slds-icon_container > span:nth-child(1)")));
        driver.findElement(By.cssSelector("tr:nth-child(1) .slds-icon_container > span:nth-child(1)")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Delete")));
        driver.findElement(By.linkText("Delete")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".uiButton--brand:nth-child(2) > .label")));
        driver.findElement(By.cssSelector(".uiButton--brand:nth-child(2) > .label")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slds-theme--success")));
    }

    @After
    public void quitWebDriver() {
        driver.quit();
    }
}
