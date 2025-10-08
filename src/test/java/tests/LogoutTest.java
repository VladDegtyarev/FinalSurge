package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    @Test(testName = "Проверка выхода из акаунта",
            description = "Проверка выхода из акаунта")
    @Description("Проверка выхода из акаунта")
    @Owner("Degtyarev Vlad")
    public void checkLogout() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .clickLogout()
                .isPageOpen()
                .checkLogout("Account Logout");
    }
}
