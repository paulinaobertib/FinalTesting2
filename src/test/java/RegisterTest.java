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

public class RegisterTest {

    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/RegisterTest.html");

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
        RegisterPage registerPage = new RegisterPage(webDriver, webDriverWait);
        registerPage.setUp();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("All")
    @Tag("Frontend")
    @Tag("Register")
    public void successRegister() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de registro exitosa");
        test.log(Status.INFO, "Comienzo del test");
        RegisterPage registerPage = new RegisterPage(webDriver, webDriverWait);
        registerPage.clickRegister();
        Assertions.assertEquals(registerPage.getRegisterTitle(), "Signing up is easy!");
        test.log(Status.PASS, "Ingreso a la página de registro");
        test.log(Status.INFO, "Comienzo a cargar los datos");
        registerPage.writeFirstName("Acton");
        registerPage.writeLastName("Floyd");
        registerPage.writeAddress("574-2490 Integer Avenue");
        registerPage.writeCity("New York City");
        registerPage.writeState("New York");
        registerPage.writeZipCode("00042");
        registerPage.writeTelephone("1-464-716-5037");
        registerPage.writeSSN("2345");
        registerPage.writeUserName("actonfloyd");
        registerPage.writePassword("password");
        registerPage.writeConfirmPassword("password");
        test.log(Status.INFO, "Se finaliza con la carga de los datos");
        registerPage.selectRegister();
        Assertions.assertEquals(registerPage.getSuccessTitle(), "Welcome actonfloyd");
        Assertions.assertEquals(registerPage.getSuccessParagraph(), "Your account was created successfully. You are now logged in.");
        test.log(Status.PASS, "Validación del registro exitoso");
    }

    @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(webDriver, webDriverWait);
        registerPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
