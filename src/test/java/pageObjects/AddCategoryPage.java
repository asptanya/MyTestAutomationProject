package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddCategoryPage {

    public WebDriver driver;

    public AddCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[2]/div/button[1]");
    By buttonCancelBy = By.xpath("/html/body/div[2]/form/div[2]/div/button[2]");

    public void setName(String strName) {
        WebElement inputName = driver.findElement(inputNameBy);
        inputName.click();
        inputName.sendKeys(strName);
    }

    public void clickOk() {
        WebElement buttonOk = driver.findElement(buttonOkBy);
        buttonOk.click();
    }

    public void clickCancel() {
        WebElement buttonCancel = driver.findElement(buttonCancelBy);
        buttonCancel.click();
    }
}
