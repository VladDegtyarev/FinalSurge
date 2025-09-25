package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listners.PropertyReader;
import listners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.CalendarPage;
import pages.LoginPage;
import pages.SettingPage;
@Listeners(TestListener.class)
public class BaseTest {
    LoginPage loginPage;
    SettingPage settingPage;
    CalendarPage calendarPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 40000;
        Configuration.clickViaJs = true;
        //Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        loginPage=new LoginPage();
        settingPage=new SettingPage();
        calendarPage= new CalendarPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }
}