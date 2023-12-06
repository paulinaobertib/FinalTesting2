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

public class NewAccountTest {
    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/NewAccountTest.html");

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
        NewAccountPage newAccountPage = new NewAccountPage(webDriver, webDriverWait);
        newAccountPage.setUp();
        newAccountPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("All")
    @Tag("Frontend")
    @Tag("NewAccount")
    public void successOpenNewAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de abrir una nueva cuenta exitosa");
        test.log(Status.INFO, "Comienzo del test");
        NewAccountPage newAccountPage = new NewAccountPage(webDriver, webDriverWait);
        newAccountPage.writeUserName("actonfloyd");
        newAccountPage.writePassword("password");
        newAccountPage.clickLogin();
        Assertions.assertEquals(newAccountPage.getTitle(), "Accounts Overview");
        Assertions.assertEquals(newAccountPage.getSubtitle(), "Account Services");
        test.log(Status.PASS, "Ingreso a la página del usuario");
        test.log(Status.INFO, "Me dirijo hacia la página para abrir una cuenta nueva");
        newAccountPage.clickNewAccount();
        Assertions.assertEquals(newAccountPage.getNewAccountTitle(), "Open New Account");
        test.log(Status.PASS, "Ingreso a la página para abrir una cuenta nueva");
        Assertions.assertEquals(newAccountPage.getQuestion(), "What type of Account would you like to open?");
        test.log(Status.PASS, "Ingreso a elegir el tipo de cuenta");
        newAccountPage.selectTypeAccount("1");
        test.log(Status.INFO, "Se finaliza con la carga de los datos");
        newAccountPage.clickOpenNewAccount();
        Assertions.assertEquals(newAccountPage.getSuccessTitle(), "Account Opened!");
        Assertions.assertEquals(newAccountPage.getSuccessParagraph(), "Congratulations, your account is now open.");
        test.log(Status.PASS, "Validación de abrir una nueva cuenta exitosa");
    }

    @AfterEach
    public void close() {
        NewAccountPage newAccountPage = new NewAccountPage(webDriver, webDriverWait);
        newAccountPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
