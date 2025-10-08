package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import wrappers.CheckBox;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.AssertJUnit.assertEquals;

public class CaloricCalculatorPage {
    private final String RMR = "//a[@class='pop-over-html']";
    private final String SAVE_BUTTON = "//input[@name='btnSubmitSettings']";
    private final String ERROR_MASSAGE = "//div[@class='alert alert-error']";
    private final String IFRAME="//iframe[@id='OtherCalciFrame']";

    public CaloricCalculatorPage openFrame() {
        switchTo().frame($x(IFRAME));
        return this;
    }

    @Step("Страница Login открыта")
    public CaloricCalculatorPage isPageOpen(){
        $x(SAVE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Ввод значений в калькулятор калорий")
    public CaloricCalculatorPage enterValuesInCalculatorCaloric(String weight, String weightType, String height, String heightType
            , String age, String gender, String distance, String distanceType) {
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
        $x(RMR).shouldBe(visible);
    }

    @Step("Сохранить")
    public CaloricCalculatorPage save() {
        $x(SAVE_BUTTON).click();
        return this;
    }

    @Step("Проверка сообщения")
    public void checkErrorMassage(String error) {
        SelenideElement element = $x(ERROR_MASSAGE);
        assertEquals(element.getText(), error);
    }

    public CaloricCalculatorPage closeFrame() {
        switchTo().defaultContent();
        return this;
    }
}
