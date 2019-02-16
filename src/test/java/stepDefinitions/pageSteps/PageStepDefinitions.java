package stepDefinitions.pageSteps;

import  cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;

import java.util.List;
import java.util.Map;


public class PageStepDefinitions implements En {

    private WebDriver driver;
    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private AddContractorPage addContractorPage;
    private AddExpensePage addExpensePage;
    private ExpenseListPage expenseListPage;
    private String generatedRandomExpenseName;
    private AddAccountPage addAccountPage;
    private EditExpensePage editExpensePage;
    double accountBalance = -1;
    double accountBalance2 = -1;
    double lastAddedExpense = -1;
    double expensePart1 = -1;
    double expensePart2 = -1;

    public PageStepDefinitions() {

        String chromeDriverPath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        navigationPage = new NavigationPage(driver);
        addContractorPage = new AddContractorPage(driver);
        addExpensePage = new AddExpensePage(driver);
        expenseListPage = new ExpenseListPage(driver);
        addAccountPage = new AddAccountPage(driver);
        editExpensePage = new EditExpensePage(driver);


        Given("^I open login page$", () -> {
            driver.get("http://therda.pl/");
        });

        When("^I login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String userName, String password) -> {
            loginPage.clickButtonLogin();
            loginPage.loginToApplication(userName, password);
        });


        Then("^I am logged in$", () -> {
            Assert.assertTrue(navigationPage.isUserLoggedin());
        });

        Then("^I am not logged in$", () -> {
            Assert.assertTrue(navigationPage.isUserNotLoggedin());
        });

        Given("^I am on the Main page$", () -> {
            driver.get("http://therda.pl/");
            loginPage.loginToApplication("asptanya", "12345678");

        });

        When("^I go to Add contractor page$", () -> {
            navigationPage.goToAddContractor();
        });

        When("^I enter contractor name \"([^\"]*)\"$", (String contractorName) -> {
            addContractorPage.setName(contractorName);
        });

        When("^I click OK button$", () -> {
            addContractorPage.clickOK();
        });

        Then("^new contractor is created$", () -> {
            Assert.assertTrue(addContractorPage.isContractorAdded());
        });

        When("^I go to Add expense page$", () -> {
            navigationPage.goToAddExpense();
        });

        When("^I create new expense with the following data$", (DataTable arg1) -> {
            List<Map<String, String>> list = arg1.asMaps(String.class, String.class);

            this.generatedRandomExpenseName = generateExpenseName(); // przypisanie wygenerowanej nazwy przez funkcje do zmiennej u gory
            addExpensePage.setName(generatedRandomExpenseName);

            if (list.get(0).get("Date").equals("Current date")) {
                addExpensePage.setDate(addExpensePage.getCurrentDate());
            } else {
                addExpensePage.setDate(list.get(0).get("Date"));
            }

            addExpensePage.setAmount(list.get(0).get("Amount"));
            addExpensePage.selectAccount1(list.get(0).get("Account nr1"));
            addExpensePage.selectContractor(list.get(0).get("Contractor"));
            addExpensePage.selectSubcategory(list.get(0).get("Subcategory"));

        });

        When("^I click OK button on Add expense page$", () -> {
            addExpensePage.clickOK();
        });

        Then("^new expense is created$", () -> {
            Assert.assertTrue(addExpensePage.isExpenseAdded());
        });

        Then("^new expense is added to Expense list as single row$", () -> {
            navigationPage.goToExpenseList();
            int var = expenseListPage.searchThroughExpenseList(generatedRandomExpenseName);
            Assert.assertEquals(1, var);
        });

        Given("^I have charged bank account$", () -> {
            addExpensePage.getToken();
            addExpensePage.loginAPI();
            addExpensePage.chargeAccount1();
        });

        Given("^I have charged bank accounts$", () -> {
            addExpensePage.getToken();
            addExpensePage.loginAPI();
            addExpensePage.chargeAccount1();
            addExpensePage.chargeAccount2();
        });

        Given("^I am on the Add expense page$", () -> {
            driver.get("http://therda.pl/");
            loginPage.loginToApplication("asptanya", "12345678");
            navigationPage.goToAddExpense();

        });

        When("^I add new expense for account \"([^\"]*)\" for value of \"([^\"]*)\"$", (String account, String amount) -> {
            accountBalance = addExpensePage.getAccountBalance(account);
            addExpensePage.addDefaultExpense(account, amount);
            double doubleAmount = Double.parseDouble(amount);
            lastAddedExpense = doubleAmount;
            addExpensePage.clickOK();
        });

        Then("^\"([^\"]*)\" account balance is recalculated correctly$", (String account) -> {
            double var = addExpensePage.getAccountBalance(account);
            Assert.assertTrue(accountBalance - lastAddedExpense == var);

        });

        When("^I add new expense for amount \"([^\"]*)\" with the ratio \"([^\"]*)\" for account \"([^\"]*)\" and ratio \"([^\"]*)\" for account \"([^\"]*)\"$", (String amount, String ratio1, String account1, String ratio2, String account2) -> {
            accountBalance = addExpensePage.getAccountBalance(account1);
            accountBalance2 = addExpensePage.getAccountBalance(account2);
            addExpensePage.addDefaultExpense(account1, amount);
            double doubleAmount = Double.parseDouble(amount);
            expensePart1 = doubleAmount * Double.parseDouble(ratio1);
            expensePart2 = doubleAmount * Double.parseDouble(ratio2);
            lastAddedExpense = doubleAmount;
            addExpensePage.clickAddAccount();
            addExpensePage.selectAccount2(account2);
            addExpensePage.setRatio(ratio1, ratio2);
            addExpensePage.clickOK();

        });

        Then("^account balance for \"([^\"]*)\" is recalculated correctly$", (String account1) -> {
            double var = addExpensePage.getAccountBalance(account1);
            Assert.assertTrue(accountBalance-expensePart1 == var);
        });

        Then("^\"([^\"]*)\" account balance is also recalculated correctly$", (String account2) -> {
            double var = addExpensePage.getAccountBalance(account2);
            Assert.assertTrue(accountBalance2-expensePart2 == var);
        });

        Then("^sum of amounts taken from two accounts equal \"([^\"]*)\"$", (String amount) -> {
            double doubleAmount = Double.parseDouble(amount);
            Assert.assertTrue(expensePart1+expensePart2 == doubleAmount);
        });

        When("^I go to Add account page$", () -> {
            navigationPage.goToAddAccount();
        });

        When("^I create new account with the following data$", (DataTable arg1) -> {
            List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
            addAccountPage.setName(list.get(0).get("Name"));
            addAccountPage.setBankName(list.get(0).get("Bank name"));
            addAccountPage.setComment(list.get(0).get("Comment"));
            addAccountPage.setAccountNumber(list.get(0).get("Account number"));
            addAccountPage.setAccountState(list.get(0).get("Account balance"));
        });

        When("^I click OK button on Add account page$", () -> {
            addAccountPage.clickOk();
        });

        Then("^new account is created$", () -> {
            Assert.assertTrue(addAccountPage.isAccountAdded());
        });

        Given("^I know the \"([^\"]*)\" account balance state$", (String account) -> {
            driver.get("http://therda.pl/");
            loginPage.loginToApplication("asptanya", "12345678");
            navigationPage.goToAddExpense();
            accountBalance = addExpensePage.getAccountBalance(account);
        });

        Given("^I am on the Expense list page$", () -> {
            navigationPage.goToExpenseList();
        });

        When("^I go to expense edit view$", () -> {
            expenseListPage.goToExpenseEditView();
        });

        When("^I increase expense amount by \"([^\"]*)\"$", (String amount) -> {
            double expenseValue = editExpensePage.getExpenseValue();
            double doubleAmount = Double.parseDouble(amount);
            double increasedValue = expenseValue+doubleAmount;
            String strIncreasedValue = String.valueOf(increasedValue);
            editExpensePage.setAmount(strIncreasedValue);
            editExpensePage.clickOK();

        });

        When("^I decrease expense amount by \"([^\"]*)\"$", (String amount) -> {
            double expenseValue = editExpensePage.getExpenseValue();
            double doubleAmount = Double.parseDouble(amount);
            double decreasedValue = expenseValue-doubleAmount;
            String strDecreasedValue = String.valueOf(decreasedValue);
            editExpensePage.setAmount(strDecreasedValue);
            editExpensePage.clickOK();

        });

        Then("^\"([^\"]*)\" account balance is recalculated correctly after increase by \"([^\"]*)\"$", (String account, String amount) -> {
            navigationPage.goToAddExpense();
            double var = addExpensePage.getAccountBalance(account);
            double doubleAmount = Double.parseDouble(amount);
            Assert.assertTrue(accountBalance-doubleAmount == var);
        });

        Then("^\"([^\"]*)\" account balance is recalculated correctly after decrease by \"([^\"]*)\"$", (String account, String amount) -> {
            navigationPage.goToAddExpense();
            double var = addExpensePage.getAccountBalance(account);
            double doubleAmount = Double.parseDouble(amount);
            Assert.assertTrue(accountBalance+doubleAmount == var);
        });



    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }
        driver.quit();
    }

    public String generateExpenseName() {
        String alphabet = "abc";
        String randomExpenseName = RandomStringUtils.random(8, alphabet);
        return randomExpenseName;
    }
}

