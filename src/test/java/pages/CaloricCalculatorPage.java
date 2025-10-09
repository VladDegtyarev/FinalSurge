package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.CheckBox;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class CaloricCalculatorPage {
    private final String RMR = "//a[@class='pop-over-html']";
    private final String SAVE_BUTTON = "//input[@name='btnSubmitSettings']";
    private final String ERROR_MASSAGE = "//div[@class='alert alert-error']";
    private final String IFRAME = "//iframe[@id='OtherCalciFrame']";

    public CaloricCalculatorPage openFrame() {
        switchTo().frame($x(IFRAME));
        return this;
    }

    @Step("Страница Calculator открыта")
    public CaloricCalculatorPage isPageOpen() {
        log.info("Page Calculator is open");
        $x(SAVE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Ввод значений в калькулятор калорий")
    public CaloricCalculatorPage enterValuesInCalculatorCaloric(String weight, String weightType, String height, String heightType
            , String age, String gender, String distance, String distanceType) {
        log.info("Entering values into the calorie calculator");
        new Input("Weight").writeCalculatorCaloric(weight);
        new CheckBox("Weight").select(weightType);
        new Input("Height").writeCalculatorCaloric(height);
        new CheckBox("Height").select(heightType);
        new Input("Age").writeCalculatorCaloric(age);
        new CheckBox("Gender").select(gender);
        new Input("Run").writeCalculatorCaloric(distance);
        new CheckBox("DistType").select(distanceType);
        return this;
    }

    @Step("Проверка видимости результата после ввода данных в калькулятор калорий")
    public void checkCalculatorWithPositiveValues() {
        log.info("Checking the visibility of the result after entering data into the calorie calculator");
        $x(RMR).shouldBe(visible);
    }

    @Step("Сохранить")
    public CaloricCalculatorPage save() {
        log.info("Save");
        $x(SAVE_BUTTON).click();
        return this;
    }

    @Step("Проверка сообщения")
    public void checkErrorMassage(String error) {
        log.info("Checking the error message");
        SelenideElement element = $x(ERROR_MASSAGE);
        assertEquals(element.getText(), error);
    }

    public CaloricCalculatorPage closeFrame() {
        switchTo().defaultContent();
        return this;
    }
}
