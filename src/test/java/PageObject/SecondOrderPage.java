package PageObject;

import config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class SecondOrderPage extends Page{
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentaPeriodField = By.xpath(".//div[text()='* Срок аренды']");
    private final By rentaPeriodValue;
    private final By makeOrderBtn = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By approweBtn = By.xpath(".//button[text()='Да']");
    private final By successMessage = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");


    public SecondOrderPage(WebDriver driver, String rentaPeriod) {
        super(driver);
        if(!rentaPeriod.isEmpty())
            this.rentaPeriodValue = By.xpath(".//div[text()='" + rentaPeriod + "']");
        else
            this.rentaPeriodValue = null;
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public SecondOrderPage addDeliveryDate() {
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);

        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(deliveryDateField));

        driver.findElement(deliveryDateField).sendKeys(formater.format(calendar.getTime()));
        driver.findElement(deliveryDateField).sendKeys(Keys.ENTER);
        return this;
    }

    public SecondOrderPage addRentaPeriod() {
        driver.findElement(rentaPeriodField).click();
        driver.findElement(rentaPeriodValue).click();
        return this;
    }

    public SecondOrderPage makeOrder() {
        driver.findElement(makeOrderBtn).click();
        driver.findElement(approweBtn).click();
        return this;
    }
}
