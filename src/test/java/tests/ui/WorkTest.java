package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.AdminPanelPage;
import pageObjects.IndexPage;
import utils.ProxyUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.ApiUtils.*;
import static utils.properties.ConfProperties.getCommonProperty;

@Epic("Тесты работоспособности брони.")
public class WorkTest extends BaseTest {
    private IndexPage indexPage;
    private BrowserMobProxy proxy;
    private ProxyUtils proxyUtils;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        indexPage = new IndexPage(driver);
        proxyUtils = new ProxyUtils();
        proxy = proxyUtils.startProxy();
        Configuration.browserCapabilities = proxyUtils.getChromeOptionsWithProxy();

    }

    @Feature("Бронь комнаты")
    @Description("Тест бронирует комнату на определенную дату и проверяет текст в всплывающем пупапе на соответствие ожидаемому")
    @Severity(value = SeverityLevel.CRITICAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка возможности успешно забронировать комнату")
    @AllureId("UI-booking")
    public void bookingroom() {
        Selenide.open(getCommonProperty("url"));
        proxy.newHar("booking/");

        indexPage.clickBookRoomButton()
                .draganddropcalandar()
                .fillFirstnameInput()
                .fillLastnameInput()
                .fillEmailInput()
                .fillPhoneInput()
                .clickBookButton()
                .popup.shouldBe(Condition.visible);

        assertEquals("Booking Successful!", indexPage.getSuccessMessage());
        assertEquals("Congratulations! Your booking has been confirmed for:", indexPage.getInfoMessage());
        assertEquals(201, selenideUtils.getBookingResponceCode(proxy));
        deleteBooking(getSpecificBookingid(token), token);
    }


    @Feature("Форма обратной связи")
    @Description("Тест отправляет сообщение с помощью формы на сайте, проверяет выдачу уведомлений и статус код отправки формы")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Тест работоспособности формы")
    @AllureId("UI-form")
    public void sendform() {
        Selenide.open(getCommonProperty("url"));
        proxy.newHar("message/");

        indexPage.fillFormName()
                .fillFormEmail()
                .fillFormPhone()
                .fillFormSubject()
                .fillFromMessage()
                .clickSubmitFormButton();

        assertEquals("Thanks for getting in touch " + getCommonProperty("testFullName") + "!", indexPage.getFormHeadMessage());
        assertEquals("We'll get back to you about", indexPage.getFormFirstRowMessage());
        assertEquals(getCommonProperty("testSubject"), indexPage.getFormSecondRowMessage());
        assertEquals("as soon as possible.", indexPage.getFormThirdRowMessage());
        assertEquals(201, selenideUtils.getFormResponceCode(proxy));
    }


    @AfterEach
    @Step("Очистка")
    public void after() {
        proxy.stop();
    }

}
