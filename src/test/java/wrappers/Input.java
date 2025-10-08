package wrappers;

import static com.codeborne.selenide.Selenide.$x;

public class Input {
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void write(String text) {
        $x(String.format("//label[text()='%s']/following-sibling::input[@type='text']", label)).setValue(text);
    }

    public void writeCalculatorCaloric(String text) {
        $x(String.format("//input[contains(@id,'%s')]", label)).setValue(text);
    }

}
