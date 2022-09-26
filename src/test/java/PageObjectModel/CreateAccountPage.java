package PageObjectModel;

import org.openqa.selenium.By;

public class CreateAccountPage {

    private By mrGenderBtn = By.id("id_gender1");
    private By firstNameTextBox = By.id("customer_firstname");
    private By lastNameTextBox = By.id("customer_lastname");
    private By passwordTextBox = By.id("passwd");

    private By addressTextBox = By.id("address1");
    private By cityTextBox = By.id("city");
    private By postcodeTextBox = By.id("postcode");
    private By phoneNumberTextBox = By.id("phone_mobile");
    private By stateButton = By.id("id_state");
    private By registerBtn = By.id("submitAccount");

    private By coloradoState = By.xpath("//*[@id=\"id_state\"]/option[8]");

    public By MrGenderButton() {
        return mrGenderBtn;
    }

    public By FirstNameTextBox() {
        return firstNameTextBox;
    }

    public By LastNameTextBox() {
        return lastNameTextBox;
    }

    public By PasswordTextBox() {
        return passwordTextBox;
    }

    public By AddressTextBox() {
        return addressTextBox;
    }

    public By CityTextBox() {
        return cityTextBox;
    }

    public By PostcodeTextBox() {
        return postcodeTextBox;
    }

    public By PhoneNumberTextBox() {
        return phoneNumberTextBox;
    }

    public By StateButton() {
        return stateButton;
    }

    public By RegisterButton() {
        return registerBtn;
    }

    public By ColoradoState() {
        return coloradoState;
    }
}
