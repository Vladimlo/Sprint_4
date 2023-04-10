package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {
    static WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    public Page clickOnElement(WebElement element) {
        scroll(element).click();
        return this;
    }
}
