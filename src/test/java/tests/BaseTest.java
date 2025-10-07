package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pages.CalendarPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SettingPage;
import utils.PropertyReader;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    LoginPage loginPage;
    SettingPage settingPage;
    CalendarPage calendarPage;
    LogoutPage logoutPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(description = "Настройка браузера", alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        if((browser.equalsIgnoreCase("chrome"))){
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
        }else if((browser.equalsIgnoreCase("firefox"))){
            Configuration.browser = "firefox";
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--incognito");
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

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
        );
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}