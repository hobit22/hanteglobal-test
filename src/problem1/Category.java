package problem1;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Category {
    private final int categoryId;
    private final String name;
    private List<Category> children = new ArrayList<>();
    private Integer boardNumber = null; // 게시판 번호

    private Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
        this.children = new ArrayList<>();
        this.boardNumber = null;
    }

    private Category(int categoryId, String name, Integer boardNumber) {
        this.categoryId = categoryId;
        this.name = name;
        this.children = new ArrayList<>();
        this.boardNumber = boardNumber;
    }

    public static Category of(int id, String name) {
        return new Category(id, name);
    }

    public static Category ofBoard(int id, String name, int boardNumber) {
        return new Category(id, name, boardNumber);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public Integer getBoardNumber() {
        return boardNumber;
    }

    public void addChild(Category child) {
        children.add(child);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
