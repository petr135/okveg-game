import rules.NormalizationResult;

public class Main {
    public static void main(String[] args) {

        String search = null;
        OkvedConfig okvedConfig = null;

        for (String arg : args) {
            if (arg.startsWith("--search=")) {
                search = arg.substring("--search=".length());
                break;
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

        okvedConfig = ConfigFactory.load().getOkved();

        var okvedTree = OkvedFactory.loadTree(okvedConfig);
        System.out.println("try get okved from url: " + okvedConfig.getUrl());

    }
}