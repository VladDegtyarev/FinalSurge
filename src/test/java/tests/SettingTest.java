package tests;

import dto.Account;
import dto.UserSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class SettingTest extends BaseTest {
    @Test(testName = "Проверка редактирования профеля",
    description = "Проверка редактирования профеля пользователя")
    @Description("Проверка редактирования профеля пользователя")
    @Owner("Degtyarev Vlad")
    public void checkEditProfile() {
        Account accountBuilder = Account.builder()
                .name("Vlad")
                .sex("Male")
                .country("Belarus")
                .weight("70")
                .typeWeight("kg")
                .LName("Degtyarev")
                .region("Vitsyebskaya voblasts")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .waitTillOpened()
                .openSetting()
                .waitTillOpened()
                .editProfile(accountBuilder);
        settingPage.check("Country", "Belarus");
        settingPage.check("Gender", "Male");
    }

    @Test(testName = "Проверка изменения настроек ",
            description = "Проверка изменения настроек пользователя")
    @Description("Проверка изменения настроек пользователя")
    @Owner("Degtyarev Vlad")
    public void CheckEditSettings() {
        UserSettings userSettings = UserSettings.builder()
                .hour("24 hour")
                .primarySport("Swimming")
                .dateFormat("DD/MM/YYYY")
                .startWeek("Monday")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .waitTillOpened()
                .openSetting()
                .waitTillOpened()
                .editSettings(userSettings);
        settingPage.check("Primary Sport", "Swimming");
        settingPage.check("Time Display", "24 hour");
    }
}
