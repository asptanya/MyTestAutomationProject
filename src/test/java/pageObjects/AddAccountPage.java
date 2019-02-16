package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddAccountPage {

    public WebDriver driver;

    public AddAccountPage(WebDriver driver) { this.driver = driver; }

    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By inputBankNameBy = By.xpath("//*[@id=\"id_bank_Name\"]");
    By inputCommentBy = By.xpath("//*[@id=\"id_comment\"]");
    By inputAccountNumberBy = By.xpath("//*[@id=\"id_account_Number\"]");
    By inputAccountStateBy = By.xpath("//*[@id=\"id_account_State\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[6]/div/button[1]");
    By modalWindowAddedAccountConfirmationBy = By.xpath("/html/body/div[2]/div");

    public void setName(String strName) {
        WebElement inputName = driver.findElement(inputNameBy);
        inputName.click();
        inputName.sendKeys(strName);
    }

    public void setBankName(String strBankName) {
        WebElement inputBankName = driver.findElement(inputBankNameBy);
        inputBankName.click();
        inputBankName.sendKeys(strBankName);
    }

    public void setComment(String strComment) {
        WebElement inputComment = driver.findElement(inputCommentBy);
        inputComment.click();
        inputComment.sendKeys(strComment);
    }

    public void setAccountNumber(String accNumber) {
        WebElement inputAccountNumber = driver.findElement(inputAccountNumberBy);
        inputAccountNumber.click();
        inputAccountNumber.clear();
        inputAccountNumber.sendKeys(accNumber);
    }

    public void setAccountState(String accState) {
        WebElement inputAccountState = driver.findElement(inputAccountStateBy);
        inputAccountState.click();
        inputAccountState.sendKeys(accState);
    }

    public void clickOk() {
        WebElement buttonOk = driver.findElement(buttonOkBy);
        buttonOk.click();
    }

    public boolean isAccountAdded () {
        return driver.findElement(modalWindowAddedAccountConfirmationBy).getText().contains("Dodano nowe konto");
    }
}
