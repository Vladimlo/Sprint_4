package PageObject;

import config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstOrderPage extends Page{

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By selectedMetroField;
    private final By telephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextStepBtn = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public FirstOrderPage(WebDriver driver, String selectedMetro) {
        super(driver);
        if(!selectedMetro.isEmpty())
            this.selectedMetroField = By.xpath(".//div[text() = '" + selectedMetro + "']");
        else
            this.selectedMetroField = null;
    }

    public FirstOrderPage addName(String name){
        if(!name.isEmpty())
            driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public FirstOrderPage addSurname(String surname) {
        if(!surname.isEmpty())
            driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    public FirstOrderPage addAddress(String address) {
        if(!address.isEmpty())
            driver.findElement(addressField).sendKeys(address);
        return this;
    }

    public FirstOrderPage chooseMerto() {
        if(selectedMetroField != null) {
            driver.findElement(metroField).click();
            driver.findElement(selectedMetroField).click();
        }
        return this;
    }

    public FirstOrderPage addTelephone(String telephone) {
        driver.findElement(telephoneField).sendKeys(telephone);
        return this;
    }

    public SecondOrderPage nextStep(String rentaPeriod) {
        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(nextStepBtn));

        driver.findElement(nextStepBtn).click();

        return new SecondOrderPage(driver, rentaPeriod);
    }
}
