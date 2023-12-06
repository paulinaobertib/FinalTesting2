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

public class TransferTest {
    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/TransferTest.html");

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
        TransferPage transferPage = new TransferPage(webDriver, webDriverWait);
        transferPage.setUp();
        transferPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("All")
    @Tag("Frontend")
    @Tag("TransferFunds")
    public void successTransferFunds() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de transferir fondos exitosa");
        test.log(Status.INFO, "Comienzo del test");
        TransferPage transferPage = new TransferPage(webDriver, webDriverWait);
        transferPage.writeUserName("actonfloyd");
        transferPage.writePassword("password");
        transferPage.clickLogin();
        Assertions.assertEquals(transferPage.getTitle(), "Accounts Overview");
        Assertions.assertEquals(transferPage.getSubtitle(), "Account Services");
        test.log(Status.PASS, "Ingreso a la página del usuario");
        test.log(Status.INFO, "Me dirijo hacia la página para transferir fondos");
        transferPage.clickTranferFundsButton();
        Assertions.assertEquals(transferPage.getPageTitle(), "Transfer Funds");
        test.log(Status.PASS, "Comienzo a llenar los campos de la transferencia");
        transferPage.writeAmount("2000");
        transferPage.selectFromAccount("24111");
        transferPage.selectToAccount("24222");
        test.log(Status.INFO, "Se finaliza con la carga de los datos");
        transferPage.clickTranferButton();
        Assertions.assertEquals(transferPage.getSuccessTitle(), "Transfer Complete!");
        test.log(Status.PASS, "Validación de transferir fondos exitosa");
    }

    @AfterEach
    public void close() {
        TransferPage transferPage = new TransferPage(webDriver, webDriverWait);
        transferPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}

