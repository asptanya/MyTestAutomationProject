package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationPage {

    public WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver; // nie rozumiem konstruktora
    }

    By buttonMenuBudgetBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[1]/a");
    By buttonSubmenuCreateBudgetBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[1]/ul/li[1]/a");
    By buttonSubmenuAddBudgetMemberBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[1]/ul/li[2]/a");
    By buttonMenuAccountsBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[2]/a");
    By buttonSubmenuCreateAccountBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[2]/ul/li[1]/a");
    By buttonSubmenuAddAccountUserBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[2]/ul/li[2]/a");
    By buttonSubmenuShowAccountBalanceBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[2]/ul/li[3]/a");
    By buttonMenuExpensesBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[3]/a");
    By buttonSubmenuAddExpenseBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[3]/ul/li[1]/a");
    By buttonSubmenuExpenseListBy = By.xpath ("/html/body/div[1]/div/div[2]/ul[1]/li[3]/ul/li[3]/a");
    //
    //
    By buttonMenuCategoriesBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[4]/a");
    By buttonSubmenuAddCategoryBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[4]/ul/li[1]/a");
    By buttonSubmenuAddSubcategoryBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[4]/ul/li[2]/a");
    //
    //
    By buttonMenuContractorsBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[5]/a");
    By buttonSubmenuAddContractorBy = By.xpath("/html/body/div[1]/div/div[2]/ul[1]/li[5]/ul/li/a");
    By modalWindowLoginConfirmationBy = By.xpath ("/html/body/div[2]/div");
    By modalWindowLoginErrorConfirmationBy = By.xpath ("/html/body/div[2]/form/div[1]");

    public void goToAddContractor() {
        driver.findElement(buttonMenuContractorsBy).click();
        driver.findElement(buttonSubmenuAddContractorBy).click();
    }

    public void goToAddCategory() {
        driver.findElement(buttonMenuCategoriesBy).click();
        driver.findElement(buttonSubmenuAddCategoryBy).click();
    }

    public void goToAddSubcategory() {
        driver.findElement(buttonMenuCategoriesBy).click();
        driver.findElement(buttonSubmenuAddSubcategoryBy).click();
    }

    public boolean isUserLoggedin(){
        return driver.findElement(modalWindowLoginConfirmationBy).getText().contains("Witaj");

    }

    public boolean isUserNotLoggedin(){
        return driver.findElement(modalWindowLoginErrorConfirmationBy).getText().contains("Podano błędny login lub hasło, spróbuj ponownie");

    }

    public void goToAddExpense(){
        driver.findElement(buttonMenuExpensesBy).click();
        driver.findElement(buttonSubmenuAddExpenseBy).click();
    }

    public void goToExpenseList(){
        driver.findElement(buttonMenuExpensesBy).click();
        driver.findElement(buttonSubmenuExpenseListBy).click();
    }

    public void goToAddAccount(){
        driver.findElement(buttonMenuAccountsBy).click();
        driver.findElement(buttonSubmenuCreateAccountBy).click();
    }
}
