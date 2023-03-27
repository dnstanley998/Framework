package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class dashboardPage {

    @FindBy(xpath = "//span/h6")
    WebElement pageTitle;

    public String getTitle(){
        return pageTitle.getText();
    }
}
