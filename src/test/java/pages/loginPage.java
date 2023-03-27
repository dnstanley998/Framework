package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage {

    @FindBy(xpath = "//input[@name='username']")
    WebElement userName;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button")
    WebElement loginBtn;


    public void typeUsername(String text){
        userName.sendKeys(text);
    }

    public void typePassword(String text){
        password.sendKeys(text);
    }

    public void clickLoginButton(){
        loginBtn.click();
    }

    public void login(String user,String pass){
        typeUsername(user);
        typePassword(pass);
        clickLoginButton();
    }
}
