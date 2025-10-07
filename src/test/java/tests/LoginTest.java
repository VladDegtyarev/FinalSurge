package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
        loginPage.getErrorMessageWithNegativeEmail("Invalid login credentials. Please try again.");
    }

    @DataProvider(name = "Проверка логина с негативными данными")
    public Object[][] loginData() {
        return new Object[][] {
                {"test@gmail.com", "", "Please enter a password."},
                {"test", "test", "Please enter a valid email address."},
                {"", "password", "Please enter your e-mail address."},
                {"test@gmail.com", "", "Please enter a password."}
        };
    }

    @Test(dataProvider = "Проверка логина с негативными данными")
    @Description("Проверка входа  в аккаунт негативными даннами")
    @Owner("Degtyarev Vlad")
    public void paramNegativeTest(String user,String password,String expectedErrorMessage){
        loginPage.openPage();
        loginPage.login(user,password);
        assertEquals(loginPage.checkErrorMessage(),expectedErrorMessage,"Сообщение об ошибки не соответсвует");

    }
}
