package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listners.TestListener;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    LoginPage loginPage;
    SettingPage settingPage;
    CalendarPage calendarPage;
    LogoutPage logoutPage;
    CaloricCalculatorPage caloricCalculatorPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(description = "Настройка браузера", alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        if ((browser.equalsIgnoreCase("chrome"))) {
            Configuration.browser = "chrome";
        } else if ((browser.equalsIgnoreCase("firefox"))) {
            Configuration.browser = "firefox";
        }
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 40000;
        Configuration.clickViaJs = true;
        //Configuration.headless = true;
        Configuration.browserSize = "1600x900";
        loginPage = new LoginPage();
        settingPage = new SettingPage();
        calendarPage = new CalendarPage();
        logoutPage = new LogoutPage();
        caloricCalculatorPage = new CaloricCalculatorPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}