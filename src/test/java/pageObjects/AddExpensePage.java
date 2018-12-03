package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpensePage {

    public WebDriver driver;

    public AddExpensePage (WebDriver driver) {
        this.driver = driver;
    }

    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By inputDateBy = By.xpath("//*[@id=\"id_date\"]");
    By inputAmountBy = By.xpath("//*[@id=\"id_amount\"]");
    By inputAmountPercentage1By = By.xpath("//*[@id=\"id_percentage\"]");
    By inputAmountPercentage2By = By.xpath("//*[@id=\"id_percentage2\"]");
    By dropdownAccount1By = By.xpath("//*[@id=\"id_account1\"]");
    By dropdownAccount2By = By.xpath("//*[@id=\"id_account2\"]");
    By dropdownContractorBy = By.xpath("//*[@id=\"id_contractor\"]");
    By dropdownSubcategoryBy = By.xpath("//*[@id=\"id_subcategory\"]");
    By checkboxRegularExpenseBy = By.xpath("//*[@id=\"id_fixed\"]");
    By checkboxAccountedBy = By.className("//*[@id=\"id_closed\"]");
    By buttonOkBy = By.xpath("/html/body/div[2]/form/div[12]/div/button[1]");
    By buttonCancelBy = By.xpath("/html/body/div[2]/form/div[12]/div/button[2]");
    By buttonAddAccountBy = By.xpath("//*[@id=\"show_button\"]");
    By buttonHideAccountBy = By.xpath("//*[@id=\"hide_button\"]");
    By buttonHalfAndHalfBy = By.xpath("//*[@id=\"50button\"]");
    By dropdownOptionBy = By.xpath("//*[@id=\"id_account1\"]/option[2]");
    By modalWindowAddedExpenseConfirmationBy = By.xpath("/html/body/div[2]/div");

    public void setName(String strName) {
        WebElement inputName = driver.findElement(inputNameBy);
        inputName.click();
        inputName.clear();
        inputName.sendKeys(strName);
    }

    public String getCurrentDate(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    public void setDate( String strDate){
        WebElement inputDate = driver.findElement(inputDateBy);
        inputDate.click();
        inputDate.clear();
        inputDate.sendKeys(strDate);
    }

    public void setAmount(String strAmount){
        WebElement inputAmount = driver.findElement(inputAmountBy);
        inputAmount.click();
        inputAmount.sendKeys(strAmount);
    }

    public void selectAccount1(String accName){
        String xpathToAccount = "//select[@id='id_account1']//option[contains(text(),'XXX')]";
        xpathToAccount = xpathToAccount.replace("XXX", accName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToAccount));
        dropdownOption.click();
    }

    public void selectContractor (String contractorName){
        String xpathToContractor = "(//*[contains(text(),'XXX')])[1]";
        xpathToContractor = xpathToContractor.replace("XXX", contractorName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToContractor));
        dropdownOption.click();
    }

    public void selectSubcategory (String subcategoryName){
        String xpathToSubcategory = "//*[contains(text(),'XXX')]";
        xpathToSubcategory = xpathToSubcategory.replace("XXX", subcategoryName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToSubcategory));
        dropdownOption.click();

    }

    public void clickOK() {
        WebElement buttonOk = driver.findElement(buttonOkBy);
        buttonOk.click();
    }

    public void clickCancel() {
        WebElement buttonCancel = driver.findElement(buttonCancelBy);
        buttonCancel.click();
    }

    public boolean isExpenseAdded () {
        return driver.findElement(modalWindowAddedExpenseConfirmationBy).getText().contains("Dodano wydatek");
    }
}
