package Checks;

import PageObject.HomePageScooter;
import PageObject.SecondOrderPage;
import config.AppConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CheckMakeOrder extends Check {

    private final int startOrderBtnId;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String telephone;
    private final String rentaPeriod;
    private final String expectedMessage;

    public CheckMakeOrder(
            int startOrderBtnId,
            String name,
            String surname,
            String address,
            String metro,
            String telephone,
            String rentaPeriod,
            String expectedMessage) {
        this.startOrderBtnId = startOrderBtnId;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.telephone = telephone;
        this.rentaPeriod = rentaPeriod;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[][] checkMakeOrderData() {
        return new Object[][]{
                {0, "Дмитрий", "Лодыгин", "г. Вологда, ул. Мира, д. 82, кв. 56", "Черкизовская", "89969454880",
                        "сутки", "Заказ оформлен"},
                {1, "Людмила", "Елизарова", "г. Вологда, ул. Мира, д. 82, кв. 57", "Сокольники", "89969454870",
                        "двое суток", "Заказ оформлен"}
        };
    }

    @Before
    public void classSetup() {
        driver = new ChromeDriver();
        driver.get(AppConfig.ORDER_URL);
    }

    @Test
    public void checkMakeOrder() {
        SecondOrderPage secondPage = new HomePageScooter(driver)
                .startOrder(startOrderBtnId, metro)
                .addName(name)
                .addSurname(surname)
                .addAddress(address)
                .chooseMerto()
                .addTelephone(telephone)
                .nextStep(rentaPeriod)
                .addDeliveryDate()
                .addRentaPeriod()
                .makeOrder();
        assertTrue("Не удалось создать заказ", secondPage.getSuccessMessage().contains(expectedMessage));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
