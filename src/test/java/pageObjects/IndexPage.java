package pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.properties.ConfProperties.getCommonProperty;


public class IndexPage extends BasePage {
    private SelenideElement bookRoomButton = $(".openBooking");
    private SelenideElement firstnameInput = $(".room-firstname");
    private SelenideElement lastnameInput = $(".room-lastname");
    private SelenideElement emailInput = $(".room-email");
    private SelenideElement phoneInput = $(".room-phone");
    private SelenideElement submitBookingButton = $(".btn-outline-primary.book-room");
    private SelenideElement cancelButton = $(".btn-outline-danger.book-room");

    private SelenideElement firstDate = $(By.xpath("//button[contains(text(), '12')]"));
    private SelenideElement secondDate = $(By.xpath("//button[contains(text(), '15')]"));
    public SelenideElement popup = $(".col-sm-6.text-center");

    public SelenideElement successMessage = $(".col-sm-6.text-center h3");
    public SelenideElement infoMessage = $(".col-sm-6.text-center p:nth-child(3)");
    public SelenideElement dateMessage = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formNameInput = $("#name");
    public SelenideElement formEmailInput = $("#email");
    public SelenideElement formPhoneInput = $("#phone");
    public SelenideElement formSubjectInput = $("#subject");
    public SelenideElement formMessageInput = $("#description");
    private SelenideElement submitFormButton = $("#submitContact");
    public SelenideElement formMessageHead = $(".contact h2");
    public SelenideElement formMessageFirstRow = $(".contact :nth-child(2) :nth-child(2)");
    public SelenideElement formMessageSecondRow = $(".contact :nth-child(2) :nth-child(3)");
    public SelenideElement formMessageThirdRow = $(".contact :nth-child(2) :nth-child(4)");

    public IndexPage(Selenide browser) {
        super(browser);
    }

    @Step("Клик по кнопке 'Book this room'")
    public IndexPage clickBookRoomButton() {
        bookRoomButton.click();
        return this;
    }

    @Step("Клик по кнопке 'Book'")
    public IndexPage clickBookButton() {
        submitBookingButton.click();
        return this;
    }

    @Step("Заполнение поля 'Firstname' данными: '{ConfProperties.getCommonProperty(\"testFirstName\")}'")
    public IndexPage fillFirstnameInput() {
        firstnameInput.sendKeys(getCommonProperty("testFirstName"));
        return this;
    }

    @Step("Заполнение поля 'Lastname' данными: '{ConfProperties.getCommonProperty(\"testLastName\")}'")
    public IndexPage fillLastnameInput() {
        $(lastnameInput).sendKeys(getCommonProperty("testLastsName"));
        return this;
    }

    @Step("Заполнение поля 'Email' данными: '{ConfProperties.getCommonProperty(\"testEmail\")}'")
    public IndexPage fillEmailInput() {
        $(emailInput).sendKeys(getCommonProperty("testEmail"));
        return this;
    }

    @Step("Заполнение поля 'Email' данными: '{ConfProperties.getCommonProperty(\"testPhone\")}'")
    public IndexPage fillPhoneInput() {
        $(phoneInput).sendKeys(getCommonProperty("testPhone"));
        return this;
    }

    @Step("Drag and drop элемента")
    public IndexPage draganddropcalandar() {
        Actions actions = new Actions(getWebDriver());
        actions.clickAndHold(firstDate).moveByOffset(10, 0).moveToElement(secondDate).release().perform();
        return this;
    }

    @Step("Заполнение поля 'Name' в форме данными: '{ConfProperties.getCommonProperty(\"testFirstName\")}'")
    public IndexPage fillFormName() {
        $(formNameInput).sendKeys(getCommonProperty("testFullName"));
        return this;
    }

    @Step("Заполнение поля 'Email' в форме данными: '{ConfProperties.getCommonProperty(\"testEmail\")}'")
    public IndexPage fillFormEmail() {
        $(formEmailInput).sendKeys(getCommonProperty("testEmail"));
        return this;
    }

    @Step("Заполнение поля 'Phone' в форме данными: '{ConfProperties.getCommonProperty(\"testPhone\")}'")
    public IndexPage fillFormPhone() {
        $(formPhoneInput).sendKeys(getCommonProperty("testPhone"));
        return this;
    }

    @Step("Заполнение поля 'Subject' в форме данными: '{ConfProperties.getCommonProperty(\"testSubject\")}'")
    public IndexPage fillFormSubject() {
        $(formSubjectInput).sendKeys(getCommonProperty("testSubject"));
        return this;
    }

    @Step("Заполнение поля 'Message' в форме данными: '{ConfProperties.getCommonProperty(\"testMessage\")}'")
    public IndexPage fillFromMessage() {
        $(formMessageInput).sendKeys(getCommonProperty("testMessage"));
        return this;
    }

    @Step("Клик по кнопке 'Submit'")
    public IndexPage clickSubmitFormButton() {
        submitFormButton.click();
        return this;
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getInfoMessage() {
        return infoMessage.getText();
    }

    public String getDateMessage() {
        return dateMessage.getText();
    }
    public String getFormHeadMessage() {
        return formMessageHead.getText();
    }

    public String getFormFirstRowMessage() {
        return formMessageFirstRow.getText();
    }

    public String getFormSecondRowMessage() {
        return formMessageSecondRow.getText();
    }

    public String getFormThirdRowMessage() {
        return formMessageThirdRow.getText();
    }
}