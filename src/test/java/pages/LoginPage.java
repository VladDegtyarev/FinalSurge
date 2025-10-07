package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {
    private final String USER_FIELD = "//*[@id='login_name']";
    private final String PASSWORD_FIELD = "//*[@id='login_password']";
    private final String ERROR_MASSAGE = "//div[@class='alert alert-error']";
    private final String EMAIL_MASSAGE = "//label[@class='error']";

@Step("Открыть страницу")
    public LoginPage openPage() {
    log.info("Open the Login page");
        open("/login");
        return this;
    }

@Step("Страница Login открыта")
    public LoginPage waitTillOpened() {
    log.info("Page Login is open");
        $x(USER_FIELD);
        return this;
    }

    @Step("Вход в аккаунт с именем пользователя:'{user}' и поролем: '{password}'")
    public CalendarPage login(String user, String password) {
        log.info("Log in with cred {}, {}", user, password);
        $x(USER_FIELD).setValue(user);
        $x(PASSWORD_FIELD).setValue(password).submit();
        return new CalendarPage();
    }

    public void getErrorMessageWithNegativeEmail(String error) {
        $x(ERROR_MASSAGE).shouldHave(Condition.text(error));
    }

    public String checkErrorMessage() {
        SelenideElement error=  $x(EMAIL_MASSAGE);
        error.shouldBe(visible);
       return error.getText();

    }


}
