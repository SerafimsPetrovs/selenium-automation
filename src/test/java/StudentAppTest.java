import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notifications;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.time.Duration.*;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class StudentAppTest {

    Faker fakeData=new Faker();

    WebDriverWait wait=new WebDriverWait(LocalDriverManager.getInstance(), ofSeconds(getConfiguration().getLong("wait.time")));
    AddStudentPage addStudentPage=new AddStudentPage();

    Notifications notifications=new Notifications(LocalDriverManager.getInstance(),wait);


    @Test
    public void createStudentTest(){

        LocalDriverManager.getInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LocalDriverManager.getInstance().manage().timeouts().implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));

        logger.info("Will open now: "+ getConfiguration().getString("app.url"));
        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));

        

        addStudentPage.setNameField(fakeData.name().fullName());

        addStudentPage.setEmailField(fakeData.internet().emailAddress());


        addStudentPage.setGender("Female");


        addStudentPage.submitStrudent();
        notifications.getNotificationLocator();
        assertThat(notifications.getNotificationSuccessMessage());



    }
    @AfterMethod
    public void tearDown(){

        LocalDriverManager.closeDriver();
    }
}
