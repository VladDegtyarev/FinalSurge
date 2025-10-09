package pages;

import com.codeborne.selenide.SelenideElement;
import dto.Account;
import dto.UserSettings;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;

@Log4j2
public class SettingPage {
    private final String EDIT_PROFILE_BUTTON = "//*[normalize-space(text())='Edit Profile']";
    private final String EDIT_SETTINGS_BUTTON = "//*[normalize-space(text())='Edit Settings']";
    private final String SAVE_BUTTON = "//*[@value='Save Changes']";
    private final String EDIT = "//p[contains(., '%s')]";
    private final String ERROR_MASSAGE = "div.alert.alert-error";

    @Step("Страница Setting открыта")
    public SettingPage isPageOpen() {
        log.info("Page setting is open");
        $x(EDIT_PROFILE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Редактирование профиля ")
    public SettingPage editProfile(Account account) {
        log.info("Edit profile: {} ", account.getName());
        $x(EDIT_PROFILE_BUTTON).click();
        new Input("First Name").write(account.getName());
        new Input("Last Name").write(account.getLName());
        new CheckBox("Gender").select(account.getSex());
        new Input("Weight").write(account.getWeight());
        new CheckBox("Weight").select(account.getTypeWeight());
        new PickList("Country").select(account.getCountry());
        new PickList("Region").select(account.getRegion());
        return this;
    }

    @Step("Сохранить")
    public SettingPage saveEdit() {
        log.info("Click Save");
        $x(SAVE_BUTTON).click();
        return this;
    }

    @Step("Изменение настроек профиля")
    public SettingPage editSettings(UserSettings userSettings) {
        log.info("Edit setting");
        $x(EDIT_SETTINGS_BUTTON).click();
        new PickList("PSport").select(userSettings.getPrimarySport());
        new CheckBox("TDisplay").select(userSettings.getHour());
        new CheckBox("DDisplay").select(userSettings.getDateFormat());
        new CheckBox("StartWeek").select(userSettings.getStartWeek());
        return this;
    }

    @Step("Проверка изменения настроек")
    public void checkEdit(String edit, String expected) {
        log.info("Checking for changes to settings");
        SelenideElement element = $x(String.format(EDIT, edit));
        String fullText = element.text();
        String text = fullText.replace(edit + ":", "").trim();
        assertEquals(text, expected);
    }

    @Step("Проверить открытие страницы настроек")
    public void checkOpenedPage(String value) {
        log.info("Сheck opening the settings page");
        SelenideElement element = $x(EDIT_PROFILE_BUTTON);
        assertEquals(element.getText(), value);
    }

    @Step("Проверка сообщения об ошибки")
    public void checkErrorMassage(String error) {
        log.info("Checking the error message");
        SelenideElement element = $(ERROR_MASSAGE);
        assertEquals(element.getText(), error, "Сообщение об ошибки не соответсвует");
    }
}
