package pages;

import com.codeborne.selenide.SelenideElement;
import dto.Account;
import dto.UserSettings;
import lombok.extern.log4j.Log4j2;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;

@Log4j2
public class SettingPage {
   private final String EDIT_PROFILE_BUTTON ="//*[normalize-space(text())='Edit Profile']";
   private final String EDIT_SETTINGS_BUTTON ="//*[normalize-space(text())='Edit Settings']";
   private final String SAVE_BUTTON="//*[@value='Save Changes']";
   private final String EDIT="//p[contains(., '%s')]";

   public SettingPage waitTillOpened() {
        $(byText("Customize your training log and preferences."));
        return this;
    }

    public SettingPage editProfile(Account account){
       log.info("Edit profile");
       $x(EDIT_PROFILE_BUTTON).click();
       new Input("First Name").write(account.getName());
       new Input("Last Name").write(account.getLName());
       new CheckBox("Gender").select(account.getSex());
       new Input("Weight").write(account.getWeight());
       new CheckBox("Weight").select(account.getTypeWeight());
       new PickList("Country").select(account.getCountry());
       new PickList("Region").select(account.getRegion());
       $x(SAVE_BUTTON).click();
       return this;
    }

    public SettingPage editSettings(UserSettings userSettings){
       log.info("Edit setting");
       $x(EDIT_SETTINGS_BUTTON).click();
       new PickList("PSport").select(userSettings.getPrimarySport());
       new CheckBox("TDisplay").select(userSettings.getHour());
       new CheckBox("DDisplay").select(userSettings.getDateFormat());
       new CheckBox("StartWeek").select(userSettings.getStartWeek());
       $x(SAVE_BUTTON).click();
       return this;
    }

    public void check(String edit,String expected){
        SelenideElement element= $x(String.format(EDIT,edit));
        String fullText = element.text();
        String text = fullText.replace(edit+":", "").trim();
        assertEquals(text,expected);
    }
}
