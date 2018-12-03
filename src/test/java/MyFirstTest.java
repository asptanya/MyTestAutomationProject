import cucumber.api.Scenario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;


public class MyFirstTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private AddContractorPage addContractorPage;
    private AddCategoryPage addCategoryPage;
    private AddSubcategoryPage addSubcategoryPage;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver); // wysylamy do konstruktora WTF
        navigationPage = new NavigationPage(driver);
        addContractorPage = new AddContractorPage(driver);
        addCategoryPage = new AddCategoryPage(driver);
        addSubcategoryPage = new AddSubcategoryPage(driver);


    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }
        //driver.close();
    }

    @Test
    public void testLogin() {
        driver.get("http://therda.pl/");
        loginPage.clickButtonLogin();
        loginPage.loginToApplication("asptanya", "12345678");

    }

    @Test
    public void testAddContractor() {
        driver.get("http://therda.pl/");
        loginPage.clickButtonLogin();
        loginPage.loginToApplication("asptanya", "12345678");
        navigationPage.goToAddContractor();
        addContractorPage.setName("Biedronka");
        addContractorPage.clickOK();
    }

    @Test
    public void testAddCategory() {
        driver.get("http://therda.pl/");
        loginPage.clickButtonLogin();
        loginPage.loginToApplication("asptanya", "12345678");
        navigationPage.goToAddCategory();
        addCategoryPage.setName("Dom");
        addCategoryPage.clickOk();
    }

    @Test
    public void testAddSubcategory() {
        driver.get("http://therda.pl/");
        loginPage.clickButtonLogin();
        loginPage.loginToApplication("asptanya", "12345678");
        navigationPage.goToAddSubcategory();
        addSubcategoryPage.selectCategory();
        addSubcategoryPage.setName("Srodki czystosci");
        addSubcategoryPage.clickOk();
    }

    @Test
    public void testLoginWithBadCredentials() {

    }
}