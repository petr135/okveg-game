package rules;

public final class SanitizeStep implements NormalizationStep {

    @Override
    public void apply(PhoneContext ctx) {
        ctx.digits = ctx.raw.replaceAll("[^0-9]", "");
    }
}