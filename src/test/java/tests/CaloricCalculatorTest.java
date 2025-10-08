package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class CaloricCalculatorTest extends BaseTest {

    @Test(testName = "Тест калькулятора калорийности с положительными значениями",
            description = "Тест калькулятора калорийности с положительными значениями")
    @Description("Тест калькулятора калорийности с положительными значениями")
    @Owner("Degtyarev Vlad")
    public void checkCalculatorWithPositiveCred() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .enterValuesInCalculatorCaloric("70", "kg", "179", "centimeters",
                        "29", "male", "1", "kilometers")
                .save();
        caloricCalculatorPage.checkCalculatorWithPositiveValues();
        caloricCalculatorPage.closeFrame();
    }

    @Test(testName = "Тест калькулятора калорийности с пустыми значениями",
            description = "Тест калькулятора калорийности с пустыми значениямия")
    @Description("Тест калькулятора калорийности с пустыми значениями")
    @Owner("Degtyarev Vlad")
    public void checkCalculatorWithEmptyFields() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .save();
        caloricCalculatorPage.checkErrorMassage("×\n" +
                "Please fix the following errors:\n" +
                "*Please enter an Integer value for Age.\n" +
                "*Please enter a value for Weight.\n" +
                "*Please enter a value for Run Distance.\n" +
                "*Please select a valid Gender.");
        caloricCalculatorPage.closeFrame();
    }

    @Test(testName = "Проверка сообщения при весе больше допустимого",
            description = "Проверка сообщения при весе больше допустимого")
    @Description("Проверка сообщения при весе больше допустимого")
    @Owner("Degtyarev Vlad")
    public void testWeightAboveMaximumMessage() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .enterValuesInCalculatorCaloric("501", "kg", "179", "centimeters",
                        "29", "male", "1", "kilometers")
                .save();
        caloricCalculatorPage.checkErrorMassage("×\n" +
                "Please fix the following errors:\n" +
                "*Weight cannot be greater than 500.00.");
        caloricCalculatorPage.closeFrame();
    }

    @Test(testName = "Проверка сообщения при весе меньше допустимого",
            description = "Проверка сообщения при весе меньше допустимого")
    @Description("Проверка сообщения при весе меньше допустимого")
    @Owner("Degtyarev Vlad")
    public void testWeightBelowMinimumMessage() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .enterValuesInCalculatorCaloric("29", "kg", "179", "centimeters",
                        "29", "male", "1", "kilometers")
                .save();
        caloricCalculatorPage.checkErrorMassage("×\n" +
                "Please fix the following errors:\n" +
                "*Weight cannot be less than 30.00.");
        caloricCalculatorPage.closeFrame();
    }

    @Test(testName = "Проверка сообщения при росте больше допустимого",
            description = "Проверка сообщения при росте больше допустимого")
    @Description("Проверка сообщения при росте больше допустимого")
    @Owner("Degtyarev Vlad")
    public void testHeightAboveMaximumMessage() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .enterValuesInCalculatorCaloric("60", "kg", "301", "centimeters",
                        "29", "male", "1", "kilometers")
                .save();
        caloricCalculatorPage.checkErrorMassage("×\n" +
                "Please fix the following errors:\n" +
                "*Height in Centimeters cannot be greater than 240.00.");
        caloricCalculatorPage.closeFrame();
    }

    @Test(testName = "Проверка сообщения при росте меньше допустимого",
            description = "Проверка сообщения при росте меньше допустимого")
    @Description("Проверка сообщения при росте меньше допустимого")
    @Owner("Degtyarev Vlad")
    public void testHeightBelowMinimumMessage() {
        loginPage.openPage()
                .isPageOpen()
                .login(user, password)
                .isPageOpen()
                .openCalculator()
                .openFrame()
                .isPageOpen()
                .enterValuesInCalculatorCaloric("60", "kg", "20", "centimeters",
                        "29", "male", "1", "kilometers")
                .save();
        caloricCalculatorPage.checkErrorMassage("×\n" +
                "Please fix the following errors:\n" +
                "*Height in Centimeters cannot be less than 60.00.");
        caloricCalculatorPage.closeFrame();
    }
}

