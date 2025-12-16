package rules;

public interface NormalizationStep {
    void apply(PhoneContext ctx) throws NormalizationException;
}
