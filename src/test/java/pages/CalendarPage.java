package pages;

import com.codeborne.selenide.SelenideElement;
import dto.QuickWorkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class CalendarPage {
    private final String SETTING_BUTTON = "//*[normalize-space(text())='Settings']";
    private final String SAVE_BUTTON = "//*[@id='saveButton']";
    private final String CALENDAR_DAY = "//*[@data-day=%s]";
    private final String CALENDAR_WORKOUT = "//div[@data-day=%s]//descendant::div[contains(text(),'%s')]";
    private final String WORKOUT_DROPDOWN_MENU = "//*[@class='dropdown-menu pull-right']//" +
            "descendant::*[contains(text(),'%s')]";
    private final String DELETE_BUTTON = "//*[@class='btn btn-primary']";
    private final String WORKOUT_DETAILS = "//small[@class='muted']";
    private final String QUICK_WORKOUT_BUTTON = "//a[@id='QuickAddToggle']";
    private final String CALENDAR_MONTH = "//a[@id='dpMonth']";
    private final String MONTH = "//table[@class='table-condensed']//span[@class='month'and normalize-space(text())='%s']";
    private final String LOGOUT_BUTTON = "//*[normalize-space(text())='Logout']";
    private final String ERROR_MASSAGE = "div.alert.alert-error";
    private final String CALCULATOR_BUTTON = "//i[@class='icsw16-calculator']";

    public void checkErrorMassage(String error) {
        SelenideElement element = $(ERROR_MASSAGE);
        assertEquals(element.getText(), error);
    }

    @Step("Выбор месяца :'{shortMonth}'")
    public CalendarPage selectMonth(String shortMonth) {
        log.info("Select Month : {}", shortMonth);
        $x(CALENDAR_MONTH).click();
        $$x(String.format(MONTH, shortMonth))
                .findBy(visible)
                .shouldBe(visible)
                .click();
        return this;
    }

    public CalendarPage checkSelectedMonth(String month) {
        SelenideElement element = $x(CALENDAR_MONTH);
        assertEquals(element.getText(), month);
        return this;
    }

    @Step("Страница Calendar открыта")
    public CalendarPage isPageOpen() {
        log.info("Page Calendar is open");
        $x(SETTING_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Перейти в настройки")
    public SettingPage openSetting() {
        log.info("Click Setting button");
        $x(SETTING_BUTTON).click();
        return new SettingPage();
    }

    @Step("Нажать Logout")
    public LogoutPage clickLogout() {
        log.info("Click Logout button");
        $x(LOGOUT_BUTTON).click();
        return new LogoutPage();
    }

    @Step("Добавить быструю тренировку")
    public CalendarPage addWorkoutQuick(QuickWorkout quickWorkout) {
        log.info("Add quick workout : {}", quickWorkout.getType());
        new Input("Date").write(quickWorkout.getDate());
        new PickList("ActivityType").select(quickWorkout.getType());
        new Input("Workout Name").write(quickWorkout.getText());
        new Input("Distance").write(quickWorkout.getDistance());
        new PickList("DistType").select(quickWorkout.getDistType());
        return this;
    }

    @Step("Нажать Save")
    public CalendarPage save() {
        log.info("Click Save button");
        $x(SAVE_BUTTON).click();
        return this;
    }

    @Step("Открыть меню тренировки")
    public CalendarPage openWorkoutDropdown(String day, String workout) {
        log.info("Open workout dropdown");
        $x(String.format(CALENDAR_WORKOUT, day, workout)).click();
        return this;
    }

    @Step("Выбрать :'{option}'")
    public CalendarPage selectWorkoutDropdown(String option) {
        log.info("Open dropdown menu and select : {}", option);
        $$x(String.format(WORKOUT_DROPDOWN_MENU, option))
                .findBy(visible)
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Удалить тренировку")
    public CalendarPage deleteWorkout() {
        log.info("Delete workout");
        $x(DELETE_BUTTON).click();
        return this;
    }

    public CalendarPage checkDeletedWorkout(String day, String workout) {
        $x(String.format(CALENDAR_WORKOUT, day, workout)).shouldNot(visible);
        return this;
    }

    public CalendarPage checkCreateWorkout(String day, String workout) {
        $x(String.format(CALENDAR_WORKOUT, day, workout)).shouldBe(visible);
        return this;
    }

    public CalendarPage checkWorkoutDetails(String day) {
        SelenideElement element = $x(WORKOUT_DETAILS);
        assertEquals(element.getText(), day);
        return this;
    }

    @Step("Нажать Quick Add")
    public CalendarPage openAddQuickWorkout() {
        log.info("Click Quick workout button");
        $x(QUICK_WORKOUT_BUTTON).click();
        return this;
    }

    @Step("Переместить тренировку с '{day}' на '{newDay}'")
    public CalendarPage moveWorkout(String day, String workout, String newDay) {
        log.info("moving the workout from {} to {}", day, newDay);
        SelenideElement originalDay = $x(String.format(CALENDAR_WORKOUT, day, workout));
        SelenideElement moveDay = $x(String.format(CALENDAR_DAY, newDay));
        actions().clickAndHold(originalDay)
                .moveToElement(moveDay)
                .release()
                .perform();
        return this;
    }

    @Step("Открыть калькулятор")
    public CaloricCalculatorPage openCalculator() {
        log.info("Open Caloric calculator");
        $x(CALCULATOR_BUTTON).click();
        return new CaloricCalculatorPage();
    }
}

