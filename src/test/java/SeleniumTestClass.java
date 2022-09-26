import PageObjectModel.AuthenticationPage;
import PageObjectModel.CreateAccountPage;
import PageObjectModel.IndexPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Test
public class SeleniumTestClass {

    WebDriver driver;
    WebDriverWait wait;
    IndexPage index = new IndexPage();
    AuthenticationPage aut = new AuthenticationPage();
    CreateAccountPage createAccount = new CreateAccountPage();
    StringBuilder randomNumbers;

    private final String AUTOMATION_PRACTICE = "http://automationpractice.com/index.php";
    private final String IFRAME_TESTING = "http://demo.guru99.com/test/guru99home/";

    @BeforeMethod
    public void PreConditions() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @AfterMethod
    public void PostConditions() {
        //driver.quit();
    }

    @Test
    public void Case1() {
        createAccount(AUTOMATION_PRACTICE, generateRandomEmail());
        fillPersonalInformation("FirstNameText", "LastNameText", "password12345678");
        fillAddressInformation("Address 123.", "New York", "Colorado", "11111", generatePhone());

        driver.findElement(createAccount.RegisterButton()).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("logout"))));

        Assert.assertTrue(driver.findElement(By.className("info-account")).getText()
                .contains("Welcome to your account. Here you can manage all of your personal information and orders."));
        Assert.assertTrue(driver.findElement(By.className("logout")).getText().contains("Sign out"));
    }

    @Test
    public void Case2() {
        createAccount(AUTOMATION_PRACTICE, generateRandomEmail());
        driver.findElement(createAccount.RegisterButton()).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol"));
        List<WebElement> errorList=element.findElements(By.tagName("li"));
        Assert.assertEquals(errorList.size(), 8);
        //Design
        int i = 1;
        for (WebElement li : errorList) {
            System.out.println("Error shown: " + i + ". " + li.getText());
            i++;
        }
    }

    @Test
    public void Case3() throws InterruptedException {
        driver.get(IFRAME_TESTING);

        Thread.sleep(5000);

        try {
            driver.switchTo().frame("gdpr-consent-notice");
            driver.findElement(By.id("save")).click();
        }
        catch (Exception e) {
            System.out.println("GDPR Consent not visible!");
        }

        driver.switchTo().frame("a077aa5e");
        driver.findElement(By.xpath("html/body/a/img")).click();

        Thread.sleep(5000);

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        if(driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).isDisplayed()){
            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();
        }

        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.className("entry-title")).getText()
                .equals("Selenium Live Project: FREE Real Time Project for Practice"));
        driver.close();
        driver.switchTo().window(newTab.get(0));
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/tbody/tr/td[2]/a[1]/img")).click();

        Thread.sleep(5000);

        try {
            List<WebElement> iframes = driver.findElements(By.xpath("//iframe|//frame"));
            for(int i = 0; i<iframes.size(); i++) {
                if (iframes.get(i).getAttribute("id").contains("google_ads_iframe")){
                    driver.switchTo().frame(iframes.get(i).getAttribute("id"));
                    System.out.println("BREAK");
                    break;
                }
            }
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div/span")).click();
        }
        catch (Exception e) {
            System.out.println("AD not visible!");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                By.xpath("//*[@id=\"cbox\"]/div[1]/div/div/div/div/div[2]/div/div/div/div[2]/div[4]/div/div/form/div[2]/button"))));
        Assert.assertTrue(driver.findElement(
                By.xpath("//*[@id=\"cbox\"]/div[1]/div/div/div/div/div[2]/div/div/div/div[2]/div[4]/div/div/form/div[2]/button")).isDisplayed());
    }

    @Test
    public void Case4() {
        driver.get(AUTOMATION_PRACTICE);
        WebElement element = driver.findElement(By.id("footer"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int pixel = 100;
        while(pixel <= element.getLocation().getY()) {
            js.executeScript("window.scrollBy(0, 100)");
            pixel+=100;
        }
        System.out.println("We are in to bottom of the page.");
    }

    private void createAccount(String url, String emailAddress) {
        driver.get(url);
        driver.findElement(index.SignInButton()).click();
        wait.until(ExpectedConditions.elementToBeClickable(aut.EmailTextBox()));
        driver.findElement(aut.EmailTextBox()).sendKeys(emailAddress);
        driver.findElement(aut.CreateAccountButton()).click();
        wait.until(ExpectedConditions.elementToBeClickable(createAccount.MrGenderButton()));
    }

    private void fillPersonalInformation(String firstName, String lastName, String password) {
        driver.findElement(createAccount.MrGenderButton()).click();
        driver.findElement(createAccount.FirstNameTextBox()).sendKeys(firstName);
        driver.findElement(createAccount.LastNameTextBox()).sendKeys(lastName);
        driver.findElement(createAccount.PasswordTextBox()).sendKeys(password);
    }

    private void fillAddressInformation(String address, String city, String state, String postcode, String phoneNumber) {
        driver.findElement(createAccount.AddressTextBox()).sendKeys(address);
        driver.findElement(createAccount.CityTextBox()).sendKeys(city);
        driver.findElement(createAccount.StateButton()).sendKeys(state);
        driver.findElement(createAccount.PostcodeTextBox()).sendKeys(postcode);
        driver.findElement(createAccount.PhoneNumberTextBox()).sendKeys(phoneNumber);
    }

    private String generateRandomEmail() {
        randomNumbers = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            randomNumbers.append(rand.nextInt(9));
        }
        return  "random" + randomNumbers.toString() + "@gmail.com";
    }

    private String generatePhone() {
        randomNumbers = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            randomNumbers.append(rand.nextInt(9));
        }
        return  "+" + randomNumbers.toString();
    }
}
