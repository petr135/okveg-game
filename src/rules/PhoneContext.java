package rules;

public class PhoneContext {
    String raw;
    String digits;

    public PhoneContext(String raw) {
        this.raw = raw;
    }

    public String getDigits() {
        return digits;
    }
}
