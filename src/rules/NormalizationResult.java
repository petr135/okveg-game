package rules;

public record NormalizationResult(boolean success, String phone, String error) {

    public static NormalizationResult ok(String value) {
        return new NormalizationResult(true, value, null);
    }

    public static NormalizationResult error(String error) {
        return new NormalizationResult(false, null, error);
    }
}
