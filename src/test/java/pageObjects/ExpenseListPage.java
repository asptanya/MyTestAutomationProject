package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExpenseListPage {

    public WebDriver driver;

    public ExpenseListPage(WebDriver driver) {
        this.driver = driver;
    }


    public int searchThroughExpenseList(String strRandomExpense) {
        List<WebElement> expenseList = driver.findElements(By.xpath("/html/body/div[2]/div/div/div/table/tbody/tr/td[1]"));
        int licznik = 0;
        for (int i = 0; i < expenseList.size(); i++) {

            if (expenseList.get(i).getText().equals(strRandomExpense)) {
                licznik++;
            }
        }
        return licznik;
    }

    public void goToExpenseEditView() {
        WebElement expenseEdit = driver.findElement(By.xpath("(//*[text()='Na potrzeby testu'])[1]/..//button"));
        expenseEdit.click();

    }

}

