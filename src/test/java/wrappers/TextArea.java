package wrappers;

import static com.codeborne.selenide.Selenide.$x;

public class TextArea {
    String label;

    public TextArea(String label) {
        this.label = label;
    }

    public void write(String text) {
        $x(String.format("//label[normalize-space(text())='%s']/following-sibling::textarea", label)).setValue(text);
    }
}
