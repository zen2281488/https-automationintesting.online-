package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.AdminPanelPage;
import pageObjects.IndexPage;
import utils.SelenideUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static utils.ApiUtils.*;
import static utils.properties.ConfProperties.getCommonProperty;

@Epic("Тесты работоспособности брони.")
public class WorkTest extends BaseTest {
    private IndexPage indexPage;
    private AdminPanelPage adminPanelPage;
    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        indexPage = new IndexPage(driver);
        adminPanelPage = new AdminPanelPage(driver);

    }

    @Feature("Бронь комнаты")
    @Description("Тест бронирует комнату на определенную дату и проверяет текст в всплывающем пупапе на соответствие ожидаемому")
    @Severity(value = SeverityLevel.CRITICAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка возможности успешно забронировать комнату")
    @AllureId("UI-booking")
    public void bookingroom() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Selenide.open(getCommonProperty("url"));
        indexPage.clickBookRoomButton()
                .draganddropcalandar()
                .fillFirstnameInput()
                .fillLastnameInput()
                .fillEmailInput()
                .fillPhoneInput()
                .clickBookButton()
                .popup.shouldBe(Condition.visible);

        Assertions.assertEquals("Booking Successful!", indexPage.getSuccessMessage());
        Assertions.assertEquals("Congratulations! Your booking has been confirmed for:", indexPage.getInfoMessage());
        Assertions.assertEquals(getBookingDates(token), indexPage.getDateMessage());

    }
    @Feature("Форма обратной связи")
    @Description("Тест отправляет сообщение с помощью формы на сайте и проверяет пришло ли сообщение администратору")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Отправка формы")
    @AllureId("UI-form")
    public void sendform() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Selenide.open(getCommonProperty("url"));
        indexPage.fillFormName().fillFormEmail().fillFormPhone().fillFormSubject().fillFromMessage().clickSubmitFormButton();
        Assertions.assertEquals("Thanks for getting in touch "+getCommonProperty("testFullName")+"!", indexPage.getFormHeadMessage());
        Assertions.assertEquals("We'll get back to you about", indexPage.getFormFirstRowMessage());
        Assertions.assertEquals(getCommonProperty("testSubject"), indexPage.getFormSecondRowMessage());
        Assertions.assertEquals("as soon as possible.", indexPage.getFormThirdRowMessage());
    }


    @AfterEach
    @Step("Очистка")
    public void after() {
        deleteAllBooking(getBookingid(token), token);
    }

}
