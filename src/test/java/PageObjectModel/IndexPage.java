package PageObjectModel;

import org.openqa.selenium.By;

public class IndexPage {

    private By signInBtn = By.className("login");

    public By SignInButton() {
        return signInBtn;
    }
}
