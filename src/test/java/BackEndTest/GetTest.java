package BackEndTest;

import Reports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class GetTest {

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/GetTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @Test
    @Tag("All")
    @Tag("Backend")
    @Tag("Get")
    public void RegisterTest() {
        ExtentTest test = extent.createTest("Prueba de registro backend");
        test.log(Status.INFO, "Comienzo del test");
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        Assertions.assertEquals(response.statusCode(), 200);
        test.log(Status.PASS, "Validación del registro desde el backend exitosa");
    }

    @Test
    @Tag("All")
    @Tag("Backend")
    @Tag("Get")
    public void OverviewTest() {
        ExtentTest test = extent.createTest("Prueba de resumen de cuentas backend");
        test.log(Status.INFO, "Comienzo del test");
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/login/actonfloyd/password");
        Assertions.assertEquals(response.statusCode(), 200);
        XmlPath xmlPath = response.xmlPath();
        int clientId = xmlPath.getInt("customer.id");
        test.log(Status.PASS, "Se obtiene el id del cliente");
        test.log(Status.PASS, "Se obtiene el id del cliente");
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/" + clientId + "/accounts");
        Assertions.assertEquals(responseGet.statusCode(), 200);
        test.log(Status.PASS, "Validación de resumen de cuentas desde el backend exitosa");
    }

    @Test
    @Tag("All")
    @Tag("Backend")
    @Tag("Get")
    public void MonthlyOverviewTest() {
        ExtentTest test = extent.createTest("Prueba de resumen de cuentas mensual backend");
        test.log(Status.INFO, "Comienzo del test");
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/login/actonfloyd/password");
        Assertions.assertEquals(response.statusCode(), 200);
        XmlPath xmlPath = response.xmlPath();
        int clientId = xmlPath.getInt("customer.id");
        test.log(Status.PASS, "Se obtiene el id del cliente");
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/" + clientId + "/accounts");
        XmlPath xmlPathAccount = responseGet.xmlPath();
        int id = xmlPathAccount.getInt("accounts.account[0].id");
        Assertions.assertEquals(responseGet.statusCode(), 200);
        test.log(Status.PASS, "Se obtiene el id de la cuenta");
        Response responseActivity = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/accounts/"+ id +"/transactions/month/All/type/All");
        Assertions.assertEquals(responseActivity.statusCode(), 200);
        test.log(Status.PASS, "Validación de resumen de cuentas mensual desde el backend exitosa");
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
