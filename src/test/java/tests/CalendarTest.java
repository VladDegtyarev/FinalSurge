package tests;

import dto.QuickWorkout;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {

    @Test(testName = "Создания тренировки",
            description = "Проверка создания тренировки")
    @Description("Проверка создания тренировки")
    @Owner("Degtyarev Vlad")
    public void checkCreateWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date("8/10/2025")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .checkCreateWorkout("8", "Swim");
    }

    @Test(testName = "Удаление тренировки ",
            description = "Проверка удаления тренировки")
    @Description("Проверка удаления тренировки")
    public void checkDeleteWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date("5/10/2025")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .openWorkoutDropdown("5", "Swim")
                .selectWorkoutDropdown("Delete")
                .deleteWorkout()
                .checkDeletedWorkout("5", "Swim");
    }

    @Test(testName = "Перемещение тренировки ",
            description = "Проверка перемещения тренировки")
    @Description("Проверка перемещения тренировки")
    public void checkMoveWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date("7/10/2025")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .moveWorkout("7", "Swim", "13")
                .openWorkoutDropdown("13", "Swim")
                .selectWorkoutDropdown("View")
                .checkWorkoutDetails("Monday, October 13, 2025");
    }

    @Test(testName = "Копирование тренировки ",
            description = "Проверка копирования тренировки")
    @Description("Проверка копирования тренировки")
    public void checkCopyWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date("5/10/2025")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        QuickWorkout quickCopyWorkout = QuickWorkout.builder()
                .date("21/10/2025")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .openWorkoutDropdown("5", "Swim")
                .selectWorkoutDropdown("Copy")
                .addWorkoutQuick(quickCopyWorkout)
                .save()
                .openWorkoutDropdown("21", "Swim")
                .selectWorkoutDropdown("View")
                .checkWorkoutDetails("Tuesday, October 21, 2025");
    }

    @Test(testName = "Смена месяца",
            description = "Проверка Смены месяца")
    @Description("Проверка Смены месяца")
    public void checkMonthChange() {
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .selectMonth("Aug")
                .checkSelectedMonth("August 2025");
    }

    @Test(testName = "Создание быстрой тренировки с невыбранным активным спортом",
            description = "Проверка отоброжения ошибки при создании быстрой тренировки с невыбранным активным спортом ")
    @Description("Проверка отоброжения ошибки при создании быстрой тренировки с невыбранным активным спортом ")
    @Owner("Degtyarev Vlad")
    public void testcheckMoveWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date("7/10/2025")
                .type("Select...")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .checkErrorMassage("×\n" +
                        "Please fix the following errors:\n" +
                        "*Please select a valid Activity Type.");
    }

    @Test(testName = "Создание быстрой тренировки с пустым полем Date",
            description = "Проверка отоброжения ошибки при создании быстрой тренировки с пустым полем Date ")
    @Description("Проверка отоброжения ошибки при создании быстрой тренировки с пустым полем Date ")
    @Owner("Degtyarev Vlad")
    public void testdatecheckMoveWorkout() {
        QuickWorkout quickWorkout = QuickWorkout.builder()
                .date(" ")
                .type("Swim")
                .text("Run")
                .distance("8")
                .distType("km")
                .build();
        loginPage.openPage()
                .waitTillOpened()
                .login(user, password)
                .openAddQuickWorkout()
                .addWorkoutQuick(quickWorkout)
                .save()
                .checkErrorMassage("×\n" +
                        "Please fix the following errors:\n" +
                        "*Please enter a value for Workout Date.");
    }
}

