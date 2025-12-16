import rules.*;

import java.util.List;
public class PhoneFacadeNormalizer {
    private static final List<NormalizationStep> PIPELINE = List.of(
            new SanitizeStep(),
            new LengthNormalizeStep(),
            new ValidationStep(),
            new FormatWithPlusStep()
    );

    static NormalizationResult normalize(String input) {

        PhoneContext ctx = new PhoneContext(input);

        try {
            for (NormalizationStep step : PIPELINE) {
                step.apply(ctx);
            }
            return NormalizationResult.ok(ctx.getDigits());

        } catch (NormalizationException e) {
            return NormalizationResult.error(e.getMessage());
        }
    }
}
