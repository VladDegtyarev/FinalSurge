package tests;

import dto.Account;
import dto.UserSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class SettingTest extends BaseTest {
    @Test(testName = "Проверка редактирования профиля",
            description = "Проверка редактирования профиля пользователя")
    @Description("Проверка редактирования профиля пользователя")
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
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openSetting()
                .isPageOpen()
                .editProfile(accountBuilder)
                .saveEdit();
        settingPage.checkEdit("Country", "Belarus");
        settingPage.checkEdit("Gender", "Male");
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
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openSetting()
                .isPageOpen()
                .editSettings(userSettings)
                .saveEdit();
        settingPage.checkEdit("Primary Sport", "Swimming");
        settingPage.checkEdit("Time Display", "24 hour");
    }

    @Test(testName = "Проверка перехода в настройки профиля",
            description = "Проверка перехода в настройки профиля")
    @Description("Проверка перехода в настройки профиля")
    @Owner("Degtyarev Vlad")
    public void checkOpenSettingPage() {
        loginPage.openPage()
                .openPage()
                .login(user, password)
                .isPageOpen()
                .openSetting()
                .checkOpenedPage("Edit Profile");

    }

    @Test(testName = "Сохранения профиля с пустым именем",
            description = "Проверка отоброжения ошибки при сохранении профиля с пустым именем ")
    @Description("Проверка отоброжения ошибки при сохранении профиля с пустым именем")
    @Owner("Degtyarev Vlad")
    public void checkEditProfileWithEmptyName() {
        Account accountBuilder = Account.builder()
                .name(" ")
                .sex("Male")
                .country("Belarus")
                .weight("70")
                .typeWeight("kg")
                .LName("Degtyarev")
                .region("Vitsyebskaya voblasts")
                .build();
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openSetting()
                .isPageOpen()
                .editProfile(accountBuilder)
                .saveEdit()
                .checkErrorMassage("×\n" +
                        "Please fix the following errors:\n" +
                        "*Please enter a value for First Name.");
    }
}
