import java.util.List;

public class OkvedNode {
    private String code;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<OkvedNode> items) {
        this.items = items;
    }

    private List<OkvedNode> items;

    public String getCode() { return code; }
    public String getName() { return name; }
    public List<OkvedNode> getItems() { return items; }

    public boolean isLeaf() {
        return items == null || items.isEmpty();
    }

    public void setCode(String code) {
        this.code = code;
    }
}
