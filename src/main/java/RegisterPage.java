import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    private By registerButton = By.xpath("//a[normalize-space()='Register']");

    private By title = By.xpath("//h1[normalize-space()='Signing up is easy!']");

    private By firstName = By.id("customer.firstName");

    private By lastName = By.id("customer.lastName");

    private By address = By.id("customer.address.street");

    private By city = By.id("customer.address.city");

    private By state = By.id("customer.address.state");

    private By zipCode =  By.id("customer.address.zipCode");

    private By telephone = By.id("customer.phoneNumber");

    private By ssn = By.id("customer.ssn");

    private By userName = By.id("customer.username");

    private By password = By.id("customer.password");

    private By confirmPassword = By.id("repeatedPassword");

    private By buttonToRegister = By.xpath("//input[@value='Register']");

    private By successTitle = By.className("title");

    private By successParagraph = By.xpath("//p[contains(text(),'Your account was created successfully. You are now logged in.')]");

    public RegisterPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public void clickRegister() throws InterruptedException {
        click(registerButton);
    }

    public String getRegisterTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(title));
        return this.getText(title);
    }

    public void writeFirstName(String name) throws InterruptedException {
        sendText(name, firstName);
    }

    public void writeLastName(String name) throws InterruptedException {
        sendText(name, lastName);
    }

    public void writeAddress(String adrs) throws InterruptedException {
        sendText(adrs, address);
    }

    public void writeCity(String ct) throws InterruptedException {
        sendText(ct, city);
    }

    public void writeState(String stt) throws InterruptedException {
        sendText(stt, state);
    }

    public void writeZipCode(String zip) throws InterruptedException {
        sendText(zip, zipCode);
    }

    public void writeTelephone(String phone) throws InterruptedException {
        sendText(phone, telephone);
    }

    public void writeSSN(String s) throws InterruptedException {
        sendText(s, ssn);
    }

    public void writeUserName(String name) throws InterruptedException {
        sendText(name, userName);
    }

    public void writePassword(String pass) throws InterruptedException {
        sendText(pass, password);
    }

    public void writeConfirmPassword(String passConfirm) throws InterruptedException {
        sendText(passConfirm, confirmPassword);
    }

    public void selectRegister() throws InterruptedException {
        click(buttonToRegister);
    }

    public String getSuccessTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(successTitle));
        return this.getText(successTitle);
    }

    public String getSuccessParagraph() throws InterruptedException {
        System.out.println("Parrafo de la pagina: " + getText(successParagraph));
        return this.getText(successParagraph);
    }

}
