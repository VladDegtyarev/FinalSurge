package tests;

import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {

    @Test
    public void createWorkout(){
        loginPage.openPage()
                .waitTillOpened()
                .login(user,password)
                .openCalendarDropDown("9","Quick Add")
                .addWorkoutQuick("Swim","Swim in the pool","8","km")
                .save();

    }
}
