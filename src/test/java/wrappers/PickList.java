package wrappers;

import static com.codeborne.selenide.Selenide.*;

public class PickList {
    String label;
    public PickList(String label){
        this.label= label;
    }
    public void select(String option){
        $x(String.format("//select[@id='%s']",label)).selectOption(option);
    }
}
