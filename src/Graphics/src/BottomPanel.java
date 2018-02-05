import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class BottomPanel extends BorderPane {
    private final TextArea gameLog = new TextArea();
    private final TreeItem<String> tree;

    public BottomPanel() {
        gameLog.setEditable(false);

        tree = new TreeItem<>("Root Node");
        tree.setExpanded(true);
        final TreeItem<String> stringTreeItem = new TreeItem<>("Item 1");
        stringTreeItem.getChildren().addAll(
                new TreeItem<>("Item 12"),
                new TreeItem<>("Item 13")
        );
        tree.getChildren().addAll(
                stringTreeItem,
                new TreeItem<>("Item 2"),
                new TreeItem<>("Item 3")
        );
        TreeView<String> treeView = new TreeView<>(tree);

        setLeft(treeView);
        setCenter(gameLog);
    }

    public void log(Object obj) {
        gameLog.appendText(obj.toString() + "\n");
    }
}
