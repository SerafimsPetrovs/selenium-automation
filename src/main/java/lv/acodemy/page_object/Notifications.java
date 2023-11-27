package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Notifications {
    WebDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;

    public Notifications(WebDriverWait wait) {
        this.waiter = wait;
    }

    private final By notificationMessage = By.className("ant-notification-notice-message");
   //новый errorMessage(тоже проверика гита)
    private final By errorMessage= By.className("ant-form-item-explain-error");
    private final By nameChangeMessage= By.className("ant-notification-notice-description");

    public static void waiter() {
    }


    public WebElement getNotificationLocator() {

        return driver.findElement(notificationMessage);
    }
    public WebElement getErrorMessage(){
        return driver.findElement(errorMessage);
    }
    public WebElement getNameChangeMessage(){
        return driver.findElement(nameChangeMessage);
    }

    public String getErrorMessageText() {
        waiter.until(textToBePresentInElement(getErrorMessage(), "Please enter student name"));
        return getErrorMessage().getText();
    }
    public String getNotificationSuccessMessage() {
        waiter.until(textToBePresentInElement(getNotificationLocator(), "Student successfully added"));
        return getNotificationLocator().getText();
    }
    public String getNotification() {
        waiter.until(textToBePresentInElement(getNameChangeMessage(), "Student update was successfully "));
        return getNotificationLocator().getText();
    }


}
