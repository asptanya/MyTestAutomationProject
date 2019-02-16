package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditExpensePage {

    public WebDriver driver;

    public EditExpensePage (WebDriver driver) {
        this.driver = driver;}

    By inputAmountBy = By.xpath("//*[@id=\"id_amount\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[9]/div/button");


    public double getExpenseValue(){
        String strExpenseValue = driver.findElement(inputAmountBy).getAttribute("value");
        double doubleExpenseValue = Double.parseDouble(strExpenseValue);
        return doubleExpenseValue;

    }

    public void setAmount(String strAmount) {
        WebElement inputAmount = driver.findElement(inputAmountBy);
        inputAmount.click();
        inputAmount.clear();
        inputAmount.sendKeys(strAmount);
    }

    public void clickOK() {
        WebElement buttonOk = driver.findElement(buttonOkBy);
        buttonOk.click();
    }

}
