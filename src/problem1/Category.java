package problem1;

import java.util.ArrayList;
import java.util.List;

class CategoryNode {
    private String id;
    private String name;
    private List<CategoryNode> children;

    public CategoryNode(String id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CategoryNode> getChildren() {
        return children;
    }

    public void addChild(CategoryNode child) {
        children.add(child);
    }
}