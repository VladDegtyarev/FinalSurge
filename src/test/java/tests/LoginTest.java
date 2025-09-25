package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(testName = "Проверка входа с позитивными даннами",
            description ="Проверка входа  в аккаунт позитивными даннами" )
    @Description("Проверка входа  в аккаунт позитивными даннами")
    @Owner("Degtyarev Vlad")
    public void checkPositiveLogin() {
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .waitTillOpened();
    }

    @Test(testName = "Проверка входа с негативными даннами",
            description ="Проверка входа  в аккаунт негативными даннами" )
    @Description("Проверка входа  в аккаунт негативными даннами")
    @Owner("Degtyarev Vlad")
    public void checkLoginWithNegativeCred() {
        loginPage.openPage()
                .waitTillOpened()
                .login("vrev@gmail.com", "test");
        loginPage.checkErrorMessage("Invalid login credentials. Please try again.");
    }

    @Test(testName = "Проверка входа с пустым email ",
            description ="Проверка входа  в аккаунт с пустым email" )
    @Description("Проверка входа  в аккаунт с пустым email")
    @Owner("Degtyarev Vlad")
    public void checkLoginWithEmptyLogin() {
        loginPage.openPage()
                .waitTillOpened()
                .login("", password);
        loginPage.checkEmailErrorMessage("Please enter your e-mail address.");
    }

    @Test(testName = "Проверка входа с пустым password ",
            description ="Проверка входа  в аккаунт с пустым password" )
    @Description("Проверка входа  в аккаунт с пустым password")
    @Owner("Degtyarev Vlad")
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage();
        loginPage.login(user, "");
        loginPage.checkPasswordErrorMessage("Please enter a password.");
    }
}
