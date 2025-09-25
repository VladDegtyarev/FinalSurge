package pages;

import lombok.extern.log4j.Log4j2;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selenide.$x;
@Log4j2
public class CalendarPage {
    private final String SETTING_BUTTON = "//*[normalize-space(text())='Settings']";
    private final String PLUS_BUTTON="//*[@data-day=%s]//descendant::*[@class='icon-plus']";
    private final String DROPDOWN_MENU="//ul[@role='menu']//" +
            "descendant::*[normalize-space(text())='%s'and @data-day='%s']";
    private final String SAVE_BUTTON="//*[@id='saveButton']";


    public CalendarPage waitTillOpened() {
        $x(SETTING_BUTTON);
        return this;
    }

    public SettingPage openSetting(){
        log.info("Click Setting button");
        $x(SETTING_BUTTON).click();
        return new SettingPage();
    }

    public CalendarPage openCalendarDropDown(String day,String option){
        log.info("Open dropdown menu and select : {}",option);
        $x(String.format(PLUS_BUTTON,day)).hover().click();
        $x(String.format(DROPDOWN_MENU,option,day)).click();
        return this;
    }

    public CalendarPage addWorkoutQuick(String type,String text,String distance,String distType){
        log.info("Add quick workout");
        new PickList("ActivityType").select(type);
        new Input("Workout Name").write(text);
        new Input("Distance").write(distance);
        new PickList("DistType").select(distType);
        return this;
    }

    public CalendarPage save(){
        log.info("Click Save button");
        $x(SAVE_BUTTON).click();
        return this;
    }

}
