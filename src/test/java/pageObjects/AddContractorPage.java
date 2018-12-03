package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddContractorPage {

    public WebDriver driver;

    public AddContractorPage(WebDriver driver) {
        this.driver = driver;
    }

    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[2]/div/button[1]");
    By buttonCancelBy = By.xpath("/html/body/div[2]/form/div[2]/div/button[2]");
    By modalWindowAddedContractorConfirmationBy = By.xpath("/html/body/div[2]/div");

    public void setName(String strName) {
        WebElement inputName = driver.findElement(inputNameBy);
        inputName.click();
        inputName.sendKeys(strName);
    }

    public void clickOK() {
        WebElement buttonOK = driver.findElement(buttonOkBy);
        buttonOK.click();
    }

    public void clickCancel() {
        WebElement buttonCancel = driver.findElement(buttonCancelBy);
        buttonCancel.click();
    }

    public boolean isContractorAdded () {
        return driver.findElement(modalWindowAddedContractorConfirmationBy).getText().contains("Dodano kontrahenta");
    }
}
