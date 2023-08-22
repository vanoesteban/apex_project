package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BasePO {

    public LoginPO() {
        super();
    }

    @FindBy(id = "username")
    private WebElement email_input;

    @FindBy(id = "password")
    private WebElement password_input;

    @FindBy(xpath = "//button[@data-action-button-primary='true']")
    private WebElement signin_button;

    @FindBy(xpath = "//a[text()='Crear cuenta']")
    private WebElement signup_button;

    public void login_with_credentials(String email, String password) {
        explicitWait_Visibility(email_input);
        inputText(email_input, email);
        inputText(password_input, password);
        clickElement(signin_button);
    }

}
