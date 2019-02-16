package pageObjects;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.specification.ProxySpecification.host;

public class AddExpensePage {

    public WebDriver driver;
    String token1;
    String token2;
    String sessionId;

    public AddExpensePage(WebDriver driver) {
        this.driver = driver;
    }

    By inputNameBy = By.xpath("//*[@id=\"id_name\"]");
    By inputDateBy = By.xpath("//*[@id=\"id_date\"]");
    By inputAmountBy = By.xpath("//*[@id=\"id_amount\"]");
    By inputAmountRatio1By = By.xpath("//*[@id=\"id_percentage\"]");
    By inputAmountRatio2By = By.xpath("//*[@id=\"id_percentage2\"]");
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

    public String getCurrentDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    public void setDate(String strDate) {
        WebElement inputDate = driver.findElement(inputDateBy);
        inputDate.click();
        inputDate.clear();
        inputDate.sendKeys(strDate);
    }

    public void setAmount(String strAmount) {
        WebElement inputAmount = driver.findElement(inputAmountBy);
        inputAmount.click();
        inputAmount.sendKeys(strAmount);
    }


    public void selectAccount1(String accName) {
        String xpathToAccount = "//select[@id='id_account1']//option[contains(text(),'XXX')]";
        xpathToAccount = xpathToAccount.replace("XXX", accName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToAccount));
        dropdownOption.click();
    }

    public void selectAccount2(String accName) {
        String xpathToAccount = "//select[@id='id_account2']//option[contains(text(),'XXX')]";
        xpathToAccount = xpathToAccount.replace("XXX", accName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToAccount));
        dropdownOption.click();
    }

    public void selectContractor(String contractorName) {
        String xpathToContractor = "(//*[contains(text(),'XXX')])[1]";
        xpathToContractor = xpathToContractor.replace("XXX", contractorName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToContractor));
        dropdownOption.click();
    }

    public void selectSubcategory(String subcategoryName) {
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

    public boolean isExpenseAdded() {
        return driver.findElement(modalWindowAddedExpenseConfirmationBy).getText().contains("Dodano wydatek");
    }

    public double getAccountBalance(String accName) {
        String xpathToAccount = "//select[@id='id_account1']//option[contains(text(),'XXX')]";
        xpathToAccount = xpathToAccount.replace("XXX", accName);
        WebElement dropdownOption = driver.findElement(By.xpath(xpathToAccount));
        String text = dropdownOption.getText();
        String[] srt = text.split(" ");
        return Double.parseDouble(srt[2]); // zwaracamy stan konta w postaci numerycznej
    }

    public void addDefaultExpense(String accName, String strAmount) {
        selectAccount1(accName);
        setAmount(strAmount);
    }

    public void clickAddAccount() {
        WebElement buttonAddAccount = driver.findElement(buttonAddAccountBy);
        buttonAddAccount.click();
    }

    public void setRatio(String strRatio1, String strRatio2) {
        WebElement inputAmountRatio1 = driver.findElement(inputAmountRatio1By);
        inputAmountRatio1.click();
        inputAmountRatio1.clear();
        inputAmountRatio1.sendKeys(strRatio1);
        WebElement inputAmountRatio2 = driver.findElement(inputAmountRatio2By);
        inputAmountRatio2.click();
        inputAmountRatio2.clear();
        inputAmountRatio2.sendKeys(strRatio2);
    }

    public void getToken() {

        RestAssured.baseURI = "http://therda.pl/add_login/";
        RequestSpecification request = RestAssured.given();
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        request.header(header);
        Response response = request.get("");
        token1 = response.getCookie("csrftoken");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    public void loginAPI() {

        RestAssured.baseURI = "http://therda.pl";
        RequestSpecification request = RestAssured.given();
        request.contentType("application/x-www-form-urlencoded");

        Header h1 = new Header("X-CSRFToken", token1);
        Header h2 = new Header("Content-Type", "application/x-www-form-urlencoded");
        Header h3 = new Header("Cookie", "csrftoken="+token1);

        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        list.add(h3);

        Headers header = new Headers(list);
        request.headers(header);


        request.param("username", "asptanya");
        request.param("password", "12345678");

        Response response = request.post("/submitLogin/");
        token2 = response.getCookie("csrftoken");
        sessionId = response.getCookie("sessionid");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 302);

    }

    public void chargeAccount1() {

    //    RestAssured.proxy = host("127.0.0.1").withPort(8888);
        RestAssured.baseURI = "http://therda.pl";
        RequestSpecification request = RestAssured.given();
        request.contentType("application/x-www-form-urlencoded");

        Header h1 = new Header("X-CSRFToken", token2);
        Header h2 = new Header("Content-Type", "application/x-www-form-urlencoded");
        Header h3 = new Header("Cookie", "csrftoken="+token2+"; sessionid="+sessionId);
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        list.add(h3);

        Headers header = new Headers(list);
        request.headers(header);

        request.param("id", "25");
        request.param("name", "Wydatki wspolne");
        request.param("bank_Name", "Bank Millenium");
        request.param("comment", "Konto na wydatki wspolne");
        request.param("account_Number", "1");
        request.param("account_State", "10000");

        Response response = request.post("/editAccount/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 302);
    }
    public void chargeAccount2() {

        RestAssured.baseURI = "http://therda.pl";
        RequestSpecification request = RestAssured.given();
        request.contentType("application/x-www-form-urlencoded");

        Header h1 = new Header("X-CSRFToken", token2);
        Header h2 = new Header("Content-Type", "application/x-www-form-urlencoded");
        Header h3 = new Header("Cookie", "csrftoken="+token2+"; sessionid="+sessionId);
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        list.add(h3);

        Headers header = new Headers(list);
        request.headers(header);

        request.param("id", "27");
        request.param("name", "Moje konto");
        request.param("bank_Name", "ING Bank Slaski");
        request.param("comment", " ");
        request.param("account_Number", "2");
        request.param("account_State", "5500");

        Response response = request.post("/editAccount/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 302);
    }
}
