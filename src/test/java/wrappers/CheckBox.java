package wrappers;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBox {
    String label;

    public CheckBox(String label) {
        this.label = label;
    }

    public void select(String select) {
        $x(String.format("//input[contains(@name,'%s') " +
                "and following-sibling::text()[normalize-space(.)='%s']]", label, select)).click();

    }

}
