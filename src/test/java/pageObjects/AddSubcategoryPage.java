package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddSubcategoryPage {
    public WebDriver driver;

    public AddSubcategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    By dropdownSelectCategoryBy = By.xpath("//*[@id=\"id_category\"]");
    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[3]/div/button[1]");
    By buttonCancelBy = By.xpath("/html/body/div[2]/form/div[3]/div/button[2]");
    By dropdownOptionBy = By.xpath("//*[@id=\"id_category\"]/option");

    public void selectCategory() {
        WebElement dropdownSelectCategory = driver.findElement(dropdownSelectCategoryBy);
        dropdownSelectCategory.click();
        WebElement dropdownOption = driver.findElement(dropdownOptionBy);
        dropdownOption.click();
    }

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
