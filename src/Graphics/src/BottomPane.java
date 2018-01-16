import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

public class BottomPane extends Pane {
    public BottomPane() {
        TreeItem<String> tree = new TreeItem<>("Root Node");
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
        getChildren().add(treeView);
    }
}
