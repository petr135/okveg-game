package rules;

public final class ValidationStep implements NormalizationStep {
    @Override
    public void apply(PhoneContext ctx) throws NormalizationException {
        if(!ctx.digits.startsWith("79")) {
            throw new NormalizationException("not mobile format number must be 79 or 89");
        }
    }
}
