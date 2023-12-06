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

public class PostTest {

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reports/PostTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @Test
    @Tag("All")
    @Tag("Backend")
    @Tag("Post")
    public void NewAccountTest() {
        ExtentTest test = extent.createTest("Prueba de abrir una nueva cuenta exitosa");
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
        Response responsePost = RestAssured.post("https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=" + clientId + "&newAccountType=1&fromAccountId=" + id);
        Assertions.assertEquals(responsePost.statusCode(), 200);
        test.log(Status.PASS, "Validación de abrir una nueva cuenta desde el backend exitosa");
    }

    @Test
    @Tag("All")
    @Tag("Backend")
    @Tag("Post")
    public void TransferTest() {
        ExtentTest test = extent.createTest("Prueba de transferencia de fondos exitosa");
        test.log(Status.INFO, "Comienzo del test");
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/login/actonfloyd/password");
        Assertions.assertEquals(response.statusCode(), 200);
        XmlPath xmlPath = response.xmlPath();
        int clientId = xmlPath.getInt("customer.id");
        test.log(Status.PASS, "Se obtiene el id del cliente");
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/" + clientId + "/accounts");
        XmlPath xmlPathAccount = responseGet.xmlPath();
        int from = xmlPathAccount.getInt("accounts.account[0].id");
        int to = xmlPathAccount.getInt("accounts.account[1].id");
        Assertions.assertEquals(responseGet.statusCode(), 200);
        test.log(Status.PASS, "Se obtienen los ids de las cuentas");
        System.out.println("https://parabank.parasoft.com/parabank/services/bank/transfer?fromAccountId="+ from +"&toAccountId="+ to +"&amount=200");
        Response responsePost = RestAssured.post("https://parabank.parasoft.com/parabank/services/bank/transfer?fromAccountId="+ from +"&toAccountId="+ to +"&amount=200");
        Assertions.assertEquals(responsePost.statusCode(), 200);
        test.log(Status.PASS, "Validación de transferencia de fondos desde el backend exitosa");
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
