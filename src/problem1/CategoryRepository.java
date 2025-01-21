package problem1;

import java.util.*;

public class CategoryRepository {
    private final Map<Integer, Category> categoryById = new HashMap<>();
    private final Map<String, List<Category>> categoryByName = new HashMap<>();

    public void addCategory(Category category) {
        if (!categoryById.containsKey(category.getCategoryId())) {
            categoryById.put(category.getCategoryId(), category);

            List<Category> categoryList = categoryByName.getOrDefault(category.getName(), new ArrayList<>());
            categoryList.add(category);
            categoryByName.put(category.getName(), categoryList);
        }
        for (Category child : category.getChildren()) {
            addCategory(child); // 재귀적으로 하위 카테고리도 추가
        }
    }

    public Category searchById(int id) {
        return categoryById.get(id);
    }

    public List<Category> searchByName(String name) {
        return categoryByName.get(name);
    }
}
