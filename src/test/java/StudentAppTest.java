import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notifications;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;
import static lv.acodemy.utils.LocalDriverManager.closeDriver;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StudentAppTest {

    WebDriver driver = LocalDriverManager.getInstance();
    Faker fakeData;
    WebDriverWait wait;
    MainPage mainPage;
    AddStudentPage addStudentPage;
    Notifications notifications;

    @BeforeMethod
    public void beforeTest() {
        wait = new WebDriverWait(driver, ofSeconds(getConfiguration().getLong("wait.time")));
        fakeData = new Faker();
        mainPage = new MainPage();
        addStudentPage = new AddStudentPage();
        notifications = new Notifications(wait);
    }

    @Test(invocationCount = 2)
    public void createStudentTest() {
        driver.manage().timeouts().implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));

        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));

        mainPage.openAddStudentForm();

        addStudentPage.setNameField(fakeData.name().fullName());
        addStudentPage.setMailField(fakeData.internet().emailAddress());
        addStudentPage.setGender("female");
        addStudentPage.submitStudent();

        assertThat(notifications.getNotificationSuccessMessage()).isEqualTo("Student successfully added");
    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
    }
}

//@Slf4j
//public class StudentAppTest {
//
//    Faker fakeData = new Faker();
//    WebDriverWait wait = new WebDriverWait(LocalDriverManager.getInstance(), ofSeconds(getConfiguration().getLong("wait.time")));
//    MainPage mainPage = new MainPage();
//    AddStudentPage addStudentPage = new AddStudentPage();
//    Notifications notifications = new Notifications(wait);
//
//    @Test()
//    public void createStudentTest() {
//        LocalDriverManager.getInstance().manage().timeouts().implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
//
//        logger.info("Will open now: " + getConfiguration().getString("app.url"));
//        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
//
//        mainPage.openAddStudentForm();
//
//        addStudentPage.setNameField(fakeData.name().fullName());
//        addStudentPage.setMailField(fakeData.internet().emailAddress());
//        addStudentPage.setGender("female");
//        addStudentPage.submitStudent();
//
//        assertThat(notifications.getNotificationSuccessMessage()).isEqualTo("Student successfully added");
//    }
//    @Test
//    public void emptyTest(){
//        LocalDriverManager.getInstance().manage().timeouts().implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
//
//        logger.info("Will open now: " + getConfiguration().getString("app.url"));
//        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
//        mainPage.openAddStudentForm();
//        addStudentPage.submitStudent();
//        assertThat(notifications.getErrorMessageText()).isEqualTo("Please enter student name");
//        System.out.println(" ");
//    }
//    @Test
//    public void editTest() {
//        LocalDriverManager.getInstance().manage().timeouts().implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
//        logger.info("Will open now: " + getConfiguration().getString("app.url"));
//        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
//
//        mainPage.useEditButton();
//
//        addStudentPage.clearNameField();
//        addStudentPage.useChangeName("Quentin Dick");
//
//        addStudentPage.submitStudent();
//        //assertThat(notifications.getNotification()).isEqualTo("Student update was successful");
//    }
//
//
//
//
//    @AfterMethod
//    public void tearDown() {
//
//        LocalDriverManager.closeDriver();
//    }
//    //проверяю работает ли гитхаб
//    //span[text()='Submit']//parent::button
//    // еще один тест гита
//}