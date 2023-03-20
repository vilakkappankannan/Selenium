package utils;

public class TemplateValueNotFoundException extends Exception {
    public TemplateValueNotFoundException(String templateValue) {
        super("The given template value: \"" + templateValue + "\" is not present in this template!");
    }
}
