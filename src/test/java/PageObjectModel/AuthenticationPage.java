package PageObjectModel;

import org.openqa.selenium.By;

public class AuthenticationPage {

    private By emailTextBox = By.id("email_create");

    private By createAccountBtn = By.id("SubmitCreate");

    public By EmailTextBox() {
        return emailTextBox;
    }

    public By CreateAccountButton() {
        return createAccountBtn;
    }
}
