import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage {

    private By newAccountButton = By.xpath("//a[normalize-space()='Open New Account']");

    private By newAccountTitle = By.xpath("//h1[normalize-space()='Open New Account']");

    private By question = By.xpath("//b[normalize-space()='What type of Account would you like to open?']");

    private By typeAccount = By.xpath("(//select[@id='type'])[1]");

    private By openNewAccountButton = By.xpath("//input[@value='Open New Account']");

    private By successTitle = By.xpath("//h1[normalize-space()='Account Opened!']");

    private By successParagraph = By.xpath("//p[normalize-space()='Congratulations, your account is now open.']");

    public NewAccountPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public void clickNewAccount() throws InterruptedException {
        click(newAccountButton);
    }

    public String getNewAccountTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(newAccountTitle));
        return this.getText(newAccountTitle);
    }

    public String getQuestion() throws InterruptedException {
        System.out.println("Pregunta: " + getText(question));
        return this.getText(question);
    }

    public void selectTypeAccount(String option) throws InterruptedException {
        selectOptionByValue(option, typeAccount);
    }

    public void clickOpenNewAccount() throws InterruptedException {
        click(openNewAccountButton);
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
