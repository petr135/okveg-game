package rules;

public class FormatWithPlusStep implements NormalizationStep {
    @Override
    public void apply(PhoneContext ctx) {
        ctx.digits = "+" + ctx.digits;
    }
}
