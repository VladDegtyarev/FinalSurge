package pages;

import com.codeborne.selenide.SelenideElement;
import dto.Account;
import dto.UserSettings;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

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
    public SettingPage waitTillOpened() {
        log.info("Page setting is open");
        $x(EDIT_PROFILE_BUTTON);
        return this;
    }

    @Step("Редактирования профиля ")
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

    @Step("Изменения настроек профиля")
    public SettingPage editSettings(UserSettings userSettings) {
        log.info("Edit setting");
        $x(EDIT_SETTINGS_BUTTON).click();
        new PickList("PSport").select(userSettings.getPrimarySport());
        new CheckBox("TDisplay").select(userSettings.getHour());
        new CheckBox("DDisplay").select(userSettings.getDateFormat());
        new CheckBox("StartWeek").select(userSettings.getStartWeek());
        return this;
    }

    public void check(String edit, String expected) {
        SelenideElement element = $x(String.format(EDIT, edit));
        String fullText = element.text();
        String text = fullText.replace(edit + ":", "").trim();
        assertEquals(text, expected);
    }

    public void checkOpenedPage(String value) {
        SelenideElement element = $x(EDIT_PROFILE_BUTTON);
        assertEquals(element.getText(), value);
    }

    public void checkErrorMassage(String error) {
        SelenideElement element = $(ERROR_MASSAGE);
        assertEquals(element.getText(), error);
    }
}
