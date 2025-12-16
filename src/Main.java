import rules.NormalizationResult;

public class Main {
    public static void main(String[] args) {

        String search = null;
        OkvedConfig okvedConfig = null;
        String configPath = "application.yml";

        for (String arg : args) {
            if (arg.startsWith("--config=")) {
                configPath = arg.substring("--config=".length());
            }

            if (arg.startsWith("--search=")) {
                search = arg.substring("--search=".length());
            }
        }

        if(search == null){
            System.out.println("false. Неверный аргумент, передайте номер в аргумент --search=");
            return;
        }

        NormalizationResult result = PhoneFacadeNormalizer.normalize(search);
        if (!result.success()) {
            System.out.println("false: " + result.error());
            return;
        }

        okvedConfig = ConfigFactory.load(configPath).getOkved();

        //TODO: поудмать над тем чтоб не грузить каждый раз, а грузить каждый день например  в 00:00,
        // добавить какой-то планировщик
        var okvedTree = OkvedFactory.loadTree(okvedConfig);
        System.out.println("try get okved from url: " + okvedConfig.getUrl());
        System.out.println("normalize phone: " + result.phone());
        var okvedNodeByNumber = SearchOkved.getOkvedByNumber(result.phone(), okvedTree);
        if(okvedNodeByNumber == null){
            System.out.printf("okved  by number: %s not  found%n", result.phone());
        }
        else {
            System.out.printf("okved text: %s by number: %s; okvedCode: %s%n"
                    , okvedNodeByNumber.getName()
                    , result.phone()
                    , okvedNodeByNumber.getCode());
        }
    }
}