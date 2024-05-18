package pageObjects;

import com.codeborne.selenide.Selenide;


public class BasePage {

    protected Selenide browser;

    public BasePage(Selenide browser) {
        this.browser = browser;
    }

}
