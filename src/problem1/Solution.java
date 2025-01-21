package problem1;

public class Solution {
    public static void main(String[] args) {
        CategoryRepository repository = new CategoryRepository();
        setupCategories(repository);

        System.out.println("Search by ID (1): " + repository.searchById(1));
        System.out.println("Search by Name (\"로제\"): " + repository.searchByName("로제"));
        System.out.println("Search by Name (\"공지사항\"): " + repository.searchByName("공지사항"));
    }

    private static void setupCategories(CategoryRepository repository) {
        // 최상위 남 여 카테고리
        Category maleCategory = Category.of(1, "남자");
        Category femaleCategory = Category.of(2, "여자");

        // 남 아이돌 카테고리 추가
        Category exo = Category.of(3, "엑소");
        Category bts = Category.of(4, "방탄소년단");
        maleCategory.addChild(exo);
        maleCategory.addChild(bts);

        // 여 아이돌 카테고리 추가
        Category blackpink = Category.of(5, "블랙핑크");
        femaleCategory.addChild(blackpink);

        // exo 추가
        Category exoNotice = Category.ofBoard(11, "공지사항", 1);
        exo.addChild(exoNotice);

        Category chen = Category.ofBoard(6, "첸", 2);
        Category baekhyun = Category.ofBoard(7, "백현", 3);
        Category xiumin = Category.ofBoard(8, "시우민", 4);
        exo.addChild(chen);
        exo.addChild(baekhyun);
        exo.addChild(xiumin);

        // bts 추가
        Category btsNotice = Category.ofBoard(12, "공지사항", 5);
        bts.addChild(btsNotice);

        Category v = Category.ofBoard(9, "뷔", 7);
        bts.addChild(v);

        // blackpink 추가
        Category blackpinkNotice = Category.ofBoard(13, "공지사항", 8);
        blackpink.addChild(blackpinkNotice);

        Category rose = Category.ofBoard(10, "로제", 9);
        blackpink.addChild(rose);

        // 익명게시판 추가
        Category anonymousBoard = Category.ofBoard(14, "익명게시판", 6);
        bts.addChild(anonymousBoard);
        blackpink.addChild(anonymousBoard);

        // 카테고리 저장
        repository.addCategory(maleCategory);
        repository.addCategory(femaleCategory);
    }
}
