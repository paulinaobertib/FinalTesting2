import Reports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OverviewTest {

    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/OverviewTest.html");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(3000));
        OverviewPage overviewPage = new OverviewPage(webDriver, webDriverWait);
        overviewPage.setUp();
        overviewPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("All")
    @Tag("Frontend")
    @Tag("AccountsOverview")
    @Tag("GeneralAccountOverview")
    public void successAccountsOverview() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de ver el resumen de cuentas");
        test.log(Status.INFO, "Comienzo del test");
        OverviewPage overviewPage = new OverviewPage(webDriver, webDriverWait);
        overviewPage.writeUserName("actonfloyd");
        overviewPage.writePassword("password");
        overviewPage.clickLogin();
        Assertions.assertEquals(overviewPage.getTitle(), "Accounts Overview");
        Assertions.assertEquals(overviewPage.getSubtitle(), "Account Services");
        test.log(Status.PASS, "Ingreso a la página del usuario");
        test.log(Status.INFO, "Me dirijo hacia la página para ver el resumen de cuentas");
        overviewPage.clickAccountsOverview();
        Assertions.assertEquals(overviewPage.getPageTitle(), "Accounts Overview");
        Assertions.assertEquals(overviewPage.getPageParagraph(), "*Balance includes deposits that may be subject to holds");
        test.log(Status.PASS, "Validación de ver el resumen de cuentas exitosa");
    }

    @Test
    @Tag("All")
    @Tag("Frontend")
    @Tag("AccountsOverview")
    @Tag("MonthlyOverview")
    public void successMonthlyAccountsOverview() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de ver el resumen de cuentas de cada mes");
        test.log(Status.INFO, "Comienzo del test");
        OverviewPage overviewPage = new OverviewPage(webDriver, webDriverWait);
        overviewPage.writeUserName("actonfloyd");
        overviewPage.writePassword("password");
        overviewPage.clickLogin();
        Assertions.assertEquals(overviewPage.getTitle(), "Accounts Overview");
        Assertions.assertEquals(overviewPage.getSubtitle(), "Account Services");
        test.log(Status.PASS, "Ingreso a la página del usuario");
        test.log(Status.INFO, "Me dirijo hacia la página para ver el resumen de cuentas");
        overviewPage.clickAccountsOverview();
        Assertions.assertEquals(overviewPage.getPageTitle(), "Accounts Overview");
        Assertions.assertEquals(overviewPage.getPageParagraph(), "*Balance includes deposits that may be subject to holds");
        test.log(Status.PASS, "Me encuentro en la página del resumen de cuentas");
        overviewPage.clickSelectAccount("24111");
        Assertions.assertEquals(overviewPage.getDetailsTitle(), "Account Details");
        Assertions.assertEquals(overviewPage.getActivityTitle(), "Account Activity");
        Assertions.assertEquals(overviewPage.getAccountNumber(), "24111");
        test.log(Status.PASS, "Me encuentro en los detalles de la cuenta seleccionada");
        overviewPage.selectActivityPeriod("All");
        overviewPage.selectType("All");
        overviewPage.clickGo();
        test.log(Status.PASS, "Validación de ver el resumen de cuentas de cada mes exitosa");
    }

    @AfterEach
    public void close() {
        OverviewPage overviewPage = new OverviewPage(webDriver, webDriverWait);
        overviewPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
