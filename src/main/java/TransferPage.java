import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferPage extends BasePage {

    private By transferFundsButton = By.xpath("//a[normalize-space()='Transfer Funds']");

    private By title = By.xpath("//h1[normalize-space()='Transfer Funds']");

    private By amount = By.xpath("//input[@id='amount']");

    private By fromAccount = By.xpath("(//select[@id='fromAccountId'])[1]");

    private By toAccount = By.xpath("//select[@id='toAccountId']");

    private By transferButton = By.xpath("//input[@value='Transfer']");

    private By successTitle = By.xpath("//h1[normalize-space()='Transfer Complete!']");

    public TransferPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public void clickTranferFundsButton() throws InterruptedException {
        click(transferFundsButton);
    }

    public String getPageTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(title));
        return this.getText(title);
    }

    public void writeAmount(String amnt) throws InterruptedException {
        sendText(amnt, amount);
    }

    public void selectFromAccount(String option) throws InterruptedException {
        selectOptionByValue(option, fromAccount);
    }

    public void selectToAccount(String option) throws InterruptedException {
        selectOptionByValue(option, toAccount);
    }

    public void clickTranferButton() throws InterruptedException {
        click(transferButton);
    }

    public String getSuccessTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(successTitle));
        return this.getText(successTitle);
    }
}
