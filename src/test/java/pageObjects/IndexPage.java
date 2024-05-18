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
    private SelenideElement submitButton = $(".btn-outline-primary.book-room");
    private SelenideElement cancelButton = $(".btn-outline-danger.book-room");

    private SelenideElement firstDate = $(By.xpath("//button[contains(text(), '12')]"));
    private SelenideElement secondDate = $(By.xpath("//button[contains(text(), '15')]"));
    public SelenideElement popup = $(".col-sm-6.text-center");

    public SelenideElement successMessage = $(".col-sm-6.text-center h3");
    public SelenideElement infoMessage = $(".col-sm-6.text-center p:nth-child(3)");
    public SelenideElement dateMessage = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formNameInput = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formEmailInput = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formPhoneInput = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formSubjectInput = $(".col-sm-6.text-center p:nth-child(4)");
    public SelenideElement formMessageInput = $(".col-sm-6.text-center p:nth-child(4)");
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
        submitButton.click();
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

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getInfoMessage() {
        return infoMessage.getText();
    }

    public String getDateMessage() {
        return dateMessage.getText();
    }
}