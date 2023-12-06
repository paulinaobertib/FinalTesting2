import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public static WebDriver webDriver;

    public static WebDriverWait webDriverWait;

    private By userName = By.xpath("//input[@name='username']");

    private By password = By.xpath("//input[@name='password']");

    private By loginButton = By.xpath("(//input[@value='Log In'])[1]");

    private By title = By.xpath("//h1[normalize-space()='Accounts Overview']");

    private By subtitle = By.xpath("//h2[normalize-space()='Account Services']");

    public BasePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        BasePage.webDriver = webDriver;
        BasePage.webDriverWait = webDriverWait;
    }

    public void setUp() {
        webDriver.manage().window().maximize();
    }

    public void getUrl(String url) {
        webDriver.get(url);
    }

    public void close() {
        webDriver.quit();
    }

    protected WebElement elementFind(By locator) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return webDriver.findElement(locator);
    }

    protected void sendText(String imputText, By locator) throws InterruptedException {
        this.elementFind(locator).clear();
        this.elementFind(locator).sendKeys(imputText);
    }

    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.elementFind(locator).sendKeys(key);
    }

    protected void click(By locator) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        this.elementFind(locator).click();
    }

    protected String getText(By locator) throws InterruptedException {
        return this.elementFind(locator).getText();
    }

    protected void selectOptionByValue(String value, By locator) throws InterruptedException {
        Select select = new Select(this.elementFind(locator));
        select.selectByValue(value);
    }

    public void writeUserName(String name) throws InterruptedException {
        sendText(name, userName);
    }

    public void writePassword(String pass) throws InterruptedException {
        sendText(pass, password);
    }

    public void clickLogin() throws InterruptedException {
        click(loginButton);
    }

    public String getTitle() throws InterruptedException {
        System.out.println("Titulo de la pagina: " + getText(title));
        return this.getText(title);
    }

    public String getSubtitle() throws InterruptedException {
        System.out.println("Subtitulo de la pagina: " + getText(subtitle));
        return this.getText(subtitle);
    }
}
