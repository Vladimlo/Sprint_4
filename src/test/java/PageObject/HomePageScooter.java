package PageObject;

import config.WebDriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter extends Page {
    private final String questionsXpath = ".//*[@class = 'Home_FAQ__3uVm4']/div/*";
    private final By allQuestions = By.xpath(questionsXpath);
    private final By allAnswers = By.xpath(questionsXpath + "//p");
    private final By[] startOrderBtns = {
            By.xpath(".//button[@class = 'Button_Button__ra12g']"),
            By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']")
    };

    public HomePageScooter(WebDriver driver) {
        super(driver);
    }

    public String getAnswerText(int questionIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(questionsXpath +
                        "[" + (questionIndex + 1) + "]")));

        clickOnElement(driver.findElements(allQuestions).get(questionIndex));

        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(questionsXpath +
                        "[" + (questionIndex + 1) + "]//p")));

        return driver.findElements(allAnswers).get(questionIndex).getText();
    }

    public FirstOrderPage startOrder(int startOrderBtnId, String metro) {
        By startOrderButton = startOrderBtns[startOrderBtnId];

        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(startOrderButton));

        clickOnElement(driver.findElement(startOrderButton));
        return new FirstOrderPage(driver, metro);
    }
}
