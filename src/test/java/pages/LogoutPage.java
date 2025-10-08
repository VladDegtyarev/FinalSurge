package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class LogoutPage {
    private final String LOGOUT_MASSAGE = "//p[@class='heading_main']";

    @Step("Страница Logout открыта")
    public LogoutPage isPageOpen() {
        log.info("Page logout is open");
        $x(LOGOUT_MASSAGE).shouldBe(visible);
        return this;
    }

    public void checkLogout(String massage) {
        SelenideElement element = $x(LOGOUT_MASSAGE);
        assertEquals(element.getText(), massage);
    }
}
