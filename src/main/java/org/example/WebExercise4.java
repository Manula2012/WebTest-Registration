package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


    import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.soap.Text;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

//Created class to check the welcome message is as per the requirement
public class WebExercise4 {

    //imported webdriver
        static protected WebDriver driver;

        //Common method to run before the execution of test cases
        @Before
        public void openBrowser() {
            System.setProperty("webdriver.chrome.driver", "src/test/java/BrowswerDrivers/chromedriver.exe ");
            driver = new ChromeDriver();
            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        //Imported from Junit to run after the execution of test cases
        @After
        public void closeBrowser()
        {
            driver.quit();
        }

        //creating method to get access for the same email at each time when running the test cases using time as a variable
        public String timeStamp()
        {
            DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
            Date date = new Date();
            return (dateFormat.format(date));
        }

        //reusable method
        public void waitForClickable(By by, int time)
        {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }

        //reusable method
        public void waitForVisible(By by, int time)
        {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }

        //reusable method
        public void enterText(By by, String text)
        {
            driver.findElement(by).sendKeys(text);
            waitForClickable(by, 30);
        }

        //reusable method
        public void selectFromDropdownByVisibleText(By by, String text)
        {
            Select select = new Select(driver.findElement(by));
            select.selectByVisibleText(text);
        }

        //reusable method
        public void selectFromDropdownByIndex(By by, int index)
        {
            Select select = new Select(driver.findElement(by));
            select.selectByIndex(index);
        }

        //reusable method
        public void selectFromDropdownByValue(By by, String value)
        {
            Select select = new Select(driver.findElement(by));
            select.selectByValue(value);
        }

        //reusable method
        public void clickOnElement(By by)
        {

            driver.findElement(by).click();
        }

        //reusable method
        public String getTextFromElement(By by)
        {
            return driver.findElement(by).getText();
        }

        //reusable method
        public void waitForElementIsPresent(By by, int time)
        {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));

        }


        //Created to test the welcome message in nopcommerce website
        @Test
        public void userShouldAbleToRegisterSuccessfully() {
            //Type url
            driver.get("https://demo.nopcommerce.com/");

            //click on sign in
            clickOnElement(By.className("ico-register"));

            //wait until registration form display
            waitForClickable(By.id("register-button"), 70);

            waitForVisible(By.id("FirstName"),50);

            //enter first name
            enterText(By.id("FirstName"), "Mal");

            //enter last name
            enterText(By.id("LastName"), "Wij");

            //select day from dropdown box
            selectFromDropdownByValue(By.name("DateOfBirthDay"), "3");

            //select month from dropdown box
            selectFromDropdownByIndex(By.name("DateOfBirthMonth"), 3);

            //select year from dropdown box
            selectFromDropdownByVisibleText(By.name("DateOfBirthYear"), "1978");

            //enter email
            enterText(By.id("Email"), "manulasn2012+" + timeStamp() + "@gmail.com");

            //enter password
            enterText(By.id("Password"), "masw123");

            //enter confirm password
            enterText(By.id("ConfirmPassword"), "masw123");

            //click on register
            clickOnElement(By.id("register-button"));

            //get actual text from welcome message
            String actual = getTextFromElement(By.cssSelector("div.result"));

            //expected text
            String expected = "My registraion successfully.";

            //comparing actual and expected
            Assert.assertEquals("Failed",expected,actual);

        }


    //Created to test the welcome message in Ocado website
        @Test
        public void registerOcado()
        {
            //Type url
            driver.get("https://www.ocado.com/webshop/startWebshop.do");

            //click on register
            clickOnElement(By.id("quickReg"));

            //wait until registration form display
            waitForClickable(By.id("registration-submit-button"),10);

            //select the title from dropdown arrow
            selectFromDropdownByVisibleText(By.tagName("select"),"Mr");

            //enter first name
            enterText(By.cssSelector("input#firstName"),"Sam");

            //enter last name
            enterText(By.id("lastName"),"Amara");

            //enter email
            enterText(By.cssSelector("input#login"),"sam.amara+"+timeStamp()+"@gmail.com");

           // enter password
            enterText(By.cssSelector("input#password"),"sam123");

            //enter post code
            enterText(By.cssSelector("input#postcode"),"UB7 7LF");

            //click on register button
            clickOnElement(By.id("registration-submit-button"));

            //wait until message display
            waitForVisible(By.xpath("//div[@class='prp-wrapper']/h3"),10);

            //get the actual text from the welcome message
            String actual = getTextFromElement(By.xpath("//div[@class='prp-wrapper']/h3"));

            //expected text as per requirement
            String expected = "Thanks for registering";

            //compare actual and expected
            assertEquals("Failed",expected,actual);
        }

        //To test the display message for incorrect credentials for Next website
        @Test
        public void signInNext() {

            //Type url
            driver.get("https://www.next.co.uk/");

            //click on My account
            clickOnElement(By.linkText("My Account"));

            //wait until sign in button display
            waitForClickable(By.id("SignInNow"),5);

            //enter invalid email
            enterText(By.cssSelector("input#EmailOrAccountNumber"),"asm12+"+timeStamp()+"@gmail.com");

            //enter invalid password
            enterText(By.cssSelector("input#Password"),"abc123");

            //click on signin
            clickOnElement(By.id("SignInNow"));

            //wait until message display
            waitForElementIsPresent(By.xpath("//span[contains(text(),\"Sorry, we \")]"),10);

            //get actual text from the message
            String actual = getTextFromElement(By.xpath("//span[contains(text(),\"Sorry, we \")]"));

            //expected text on the message
            String expected = "Sorry, we have been unable to sign you in.";

            //compare expected and actual
            Assert.assertEquals("Failed",expected,actual);
        }


        //to test display message for invalid credentials for Matalan website
        @Test
        public void signInMatalan() {

            //Type url
            driver.get("https://www.matalan.co.uk/");

            //click on myaccount
            clickOnElement(By.xpath("//*[@class='o-list__item']//*[text()='My Account']"));

            //wait until 'continue securely' button display
            waitForClickable(By.xpath("//*[@id='new_account']/button"),5);

            //enter invali email
            enterText(By.cssSelector("input#account_email"),"abs123+"+timeStamp()+"@gmail.com");

           //enter invalid password
            enterText(By.cssSelector("input#account_password"),"abcd1234");

            //click on 'continue securely' button
            clickOnElement(By.xpath("//*[@id='new_account']/button"));

            //get actual text from message displayed
            String actual = getTextFromElement(By.className("help-block"));

            //expected message
            String expected = "The password you have entered does not match the criteria";

            //compare expected and actual
            assertEquals("Failed",expected,actual);
        }

        //to test welcome message on automationpractice website
        @Test
        public void RegisterEcommerce()

        {
           //Type url
           driver.get("http://automationpractice.com/index.php");

           //click on signin
           clickOnElement(By.xpath("//a[@class='login']"));

           //wait for 'create an account' button display
           waitForClickable(By.cssSelector("input#email_create"),30);

           //enter email
           enterText(By.cssSelector("input#email_create"), "masw24+"+timeStamp()+"@test.com");

           //click on 'create an account' button
           clickOnElement(By.xpath("//button[@id=\"SubmitCreate\"]/span"));

           //select gender
           clickOnElement(By.cssSelector("input#id_gender2"));

           //enter first name
           enterText(By.cssSelector("input#customer_firstname"),"Namali");

           //enter last name
           enterText(By.cssSelector("input#customer_lastname"),"Perera");

           //enter password
           enterText(By.cssSelector("input#passwd"),"np123");

           //enter first name
           enterText(By.cssSelector("input#firstname"),"Namali");

           //enter last name
           enterText(By.cssSelector("input#lastname"),"Perera");

           //enter first line of address
           enterText(By.cssSelector("input#address1"),"12, Temple Park");

           //enter city
           enterText(By.cssSelector("input#city"),"Uxbridge");

           //select state
           selectFromDropdownByVisibleText(By.cssSelector("select#id_state"),"Texas");

           //enter post code
           enterText(By.cssSelector("input#postcode"),"12345");

           //enter country
           selectFromDropdownByVisibleText(By.cssSelector("select#id_country"),"United States");

           //enter mobile number
           enterText(By.name("phone_mobile"),"07123456789");

           //enter alias
           enterText(By.cssSelector("input#alias"),"Texas");

           //click on register
           clickOnElement(By.xpath("//button[@id='submitAccount']/span"));

           //wait until message display
           waitForElementIsPresent(By.className("info-account"),10);

           //get actual text from message
           String actual = getTextFromElement(By.className("info-account"));

           //expected text in the message
           String expected = "Welcome to your account";

           //compare expected and actual
           Assert.assertEquals("Failed",expected,actual);
        }
    }