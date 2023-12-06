import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage extends BasePage{

    private By accountsOverviewButton = By.xpath("//a[normalize-space()='Accounts Overview']");

    private By title = By.xpath("//h1[normalize-space()='Accounts Overview']");

    private By paragraph = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to holds')]");

    private By detailsTitle = By.xpath("//h1[normalize-space()='Account Details']");

    private By accountNumber = By.id("accountId");

    private By activityTitle = By.xpath("//h1[normalize-space()='Account Activity']");

    private By activityPeriod = By.id("month");

    private By type = By.id("transactionType");

    private By goButton = By.xpath("//input[@value='Go']");

    public OverviewPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public void clickAccountsOverview() throws InterruptedException {
        click(accountsOverviewButton);
    }

    public String getPageTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(title));
        return this.getText(title);
    }

    public String getPageParagraph() throws InterruptedException {
        System.out.println("Parrafo de la pagina: " + getText(paragraph));
        return this.getText(paragraph);
    }

    public void clickSelectAccount(String accountNumber) throws InterruptedException {
        By accountLocator = By.xpath("//a[normalize-space()='" + accountNumber + "']");
        click(accountLocator);
    }


    public String getDetailsTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(detailsTitle));
        return this.getText(detailsTitle);
    }

    public String getAccountNumber() throws InterruptedException {
        System.out.println("Numero de la cuenta: " + getText(accountNumber));
        return this.getText(accountNumber);
    }

    public String getActivityTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(activityTitle));
        return this.getText(activityTitle);
    }

    public void selectActivityPeriod(String option) throws InterruptedException {
        selectOptionByValue(option, activityPeriod);
    }

    public void selectType(String option) throws InterruptedException {
        selectOptionByValue(option, type);
    }

    public void clickGo() throws InterruptedException {
        click(goButton);
    }
}
