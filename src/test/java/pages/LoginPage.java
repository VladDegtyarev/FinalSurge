package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage {
    private final String USER_FIELD= "//*[@id='login_name']";
    private final String PASSWORD_FIELD="//*[@id='login_password']";
    private final String ERROR_MASSAGE="//div[@class='alert alert-error']";
    private final String EMAIL_MASSAGE="//*[@for='login_name'and @class='error']";
    private final String PASSWORD_MASSAGE= "//*[@for='login_password'and @class='error']";

    public LoginPage openPage (){
        open("/login");
        return this;
    }

    public LoginPage waitTillOpened(){
        $x(USER_FIELD);
        return this;
    }

    @Step("Вход в аккаунт с именем пользователя:'{user}' и поролем: '{password}'")
    public CalendarPage login(String user, String password) {
        log.info("Log in with cred {}, {}",user,password);
        $x(USER_FIELD).setValue(user);
        $x(PASSWORD_FIELD).setValue(password).submit();
        return new CalendarPage();
    }

    public void checkErrorMessage(String error) {
        $x(ERROR_MASSAGE).shouldHave(Condition.text(error));
    }

    public void checkEmailErrorMessage(String error){$x(EMAIL_MASSAGE).shouldHave(Condition.text(error));}

    public void checkPasswordErrorMessage(String error){$x(PASSWORD_MASSAGE).shouldHave(Condition.text(error));}
}
