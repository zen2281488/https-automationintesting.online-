package pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static utils.ApiUtils.getIndexByName;
import static utils.properties.ConfProperties.getCommonProperty;

public class AdminPanelPage extends BasePage {
    private SelenideElement messagesListButton = $(".fa-inbox");
    private SelenideElement fromNameMessageDetail = $(".col-10 > p");
    private SelenideElement emailMessageDetail = $(".ReactModalPortal  :nth-child(2)  div  p");
    private SelenideElement phoneMessageDetail = $(".col-2 p");
    private SelenideElement subjectMessageDetail = $(".ReactModalPortal :nth-child(3) p");
    private SelenideElement messageMessageDetail = $(".ReactModalPortal :nth-child(4) p");
    public AdminPanelPage(Selenide browser) {
        super(browser);
    }

    @Step("Клик по кнопке открытия списка сообщений")
    public AdminPanelPage clickMessagesListButton() {
        messagesListButton.click();
        return this;
    }

    public String getFromNameMessageDetail(String token) {
        return fromNameMessageDetail.getText();
    }
    public String getEmailMessageDetail(String token) {
        return emailMessageDetail.getText();
    }
    public String getPhoneMessageDetail(String token) {
        return phoneMessageDetail.getText();
    }
    public String getSubjectMessageDetail(String token) {
        return subjectMessageDetail.getText();
    }

    public String getMessageMessageDetail(String token) {
        return messageMessageDetail.getText();
    }
}
