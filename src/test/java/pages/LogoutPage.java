package pages;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$x;

public class LogoutPage {
    private final String LOGOUT_MASSAGE= "//p[@class='heading_main']";

    public void checkLogout(String massage){
        SelenideElement element =$x(LOGOUT_MASSAGE);
        Assert.assertEquals(element.getText(),massage);
    }
}
