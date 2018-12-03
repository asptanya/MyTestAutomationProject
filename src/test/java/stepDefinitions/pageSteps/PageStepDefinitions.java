package stepDefinitions.pageSteps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
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


    public PageStepDefinitions() {

        String chromeDriverPath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        navigationPage = new NavigationPage(driver);
        addContractorPage = new AddContractorPage(driver);
        addExpensePage = new AddExpensePage(driver);

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

        Given("^I am on the Main page$", () -> {
            driver.get("http://therda.pl/");
            loginPage.clickButtonLogin();
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
            addExpensePage.setName(list.get(0).get("Name"));

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
}

