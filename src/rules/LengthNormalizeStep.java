package rules;

public final class LengthNormalizeStep implements NormalizationStep {

    @Override
    public void apply(PhoneContext ctx) throws NormalizationException {

        if (ctx.digits.length() == 10) {
            ctx.digits = "7" + ctx.digits;
            return;
        }

        if (ctx.digits.length() == 11) {
            if (ctx.digits.startsWith("8")) {
                ctx.digits = "7" + ctx.digits.substring(1);
            }
            return;
        }

        throw new NormalizationException("invalid length");
    }
}
