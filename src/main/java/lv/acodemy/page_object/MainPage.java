package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    WebDriver driver = LocalDriverManager.getInstance();

    private final By addStudentButton = By.id("addStudentButton");
    private final By editButton = By.xpath("//*[@id=\"root\"]/section/section/main/div/div/div/div/div/div[2]/div/table/tbody/tr[4]/td[6]/div/label[2]/span[2]");

    public void openAddStudentForm() {

        driver.findElement(addStudentButton).click();
    }
    public void useEditButton(){
        driver.findElement(editButton).click();
    }

}