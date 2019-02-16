package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver; // konstruktor
    }

    By buttonLoginBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[2]/a");
    By inputUserNameBy = By.xpath("//*[@id=\"id_username\"]");
    By inputPasswordBy = By.xpath("//*[@id=\"id_password\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[3]/div/button[1]");

    public void clickButtonLogin() {
        driver.findElement(buttonLoginBy).click();
    }

    public void setInputUserName(String strUserName) {
        WebElement inputUserName = driver.findElement(inputUserNameBy);
        inputUserName.click();
        inputUserName.sendKeys(strUserName);
    }

    public void setPassword(String strPassword) {
        WebElement inputPassword = driver.findElement(inputPasswordBy);
        inputPassword.click();
        inputPassword.sendKeys(strPassword);

    }

    public void clickOk() {
        driver.findElement(buttonOkBy).click();
    }

    public void loginToApplication(String strUserName, String strPassword) {
        clickButtonLogin();
        setInputUserName(strUserName);
        setPassword(strPassword);
        clickOk();
    }


}
 