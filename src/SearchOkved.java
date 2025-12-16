import java.util.*;

public class SearchOkved {
    //Т.к. оквед состоит из 6 цифр(https://www.gosuslugi.ru/help/faq/registrate_ip/3048)
    // и минимум из 2 цифр, решил разбить номер на пары формата 11 11.1 11.11 и т.д.
    public static List<String> getCandidates(String phone, int tailLength) {
        List<String> candidates = new ArrayList<>();
        String tail = phone.substring(phone.length() - tailLength);
        int len = tail.length();
        if (len < 2) return candidates;

        for (int window = 2; window <= len; window++) {
            String sub = tail.substring(len - window); // берем последние window цифр
            StringBuilder sb = new StringBuilder();

            int start = 0;
            while (start < sub.length()) {
                // если осталось <2 цифр до конца, просто добавляем их и выходим, иначе . на конце будет
                if (sub.length() - start <= 2) {
                    sb.append(sub.substring(start));
                    break;
                }
                sb.append(sub.substring(start, start + 2));
                sb.append(".");
                start += 2;
            }

            candidates.add(sb.toString());
        }

        return candidates;
    }

    public static Map<String, OkvedNode> buildOkvedMap(List<OkvedNode> nodes) {
        Map<String, OkvedNode> map = new HashMap<>();
        Deque<OkvedNode> stack = new ArrayDeque<>(nodes);

        while (!stack.isEmpty()) {
            OkvedNode node = stack.pop();
            if (node.getCode().matches("\\d.*")) {
                map.put(node.getCode(), node);
            }

            if (!node.isLeaf()) {
                stack.addAll(node.getItems());
            }
        }

        return map;
    }

    public static OkvedNode getOkvedByNumber(String phone, List<OkvedNode> nodes){
        //TODO надо вынести в конфиг длину окведа 6
        var candidates = getCandidates(phone, 6);
        var okvedMap = buildOkvedMap(nodes);

        OkvedNode bestMatch = null;
        int maxLength = 0;

        for (String cand : candidates) {
            OkvedNode node = okvedMap.get(cand);
            if (node != null && cand.length() > maxLength) {
                bestMatch = node;
                maxLength = cand.length();
            }
        }

        return bestMatch;
    }
}
