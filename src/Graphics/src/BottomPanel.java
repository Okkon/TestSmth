import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import logic.GEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BottomPanel extends BorderPane {
    private final TextArea gameLog = new TextArea();
    private final TreeItem<String> tree;
    private final List<GEvent> eventList = new ArrayList<>();
    private final Map<GEvent, TreeItem> eventTreeItemMap = new HashMap<>();

    public BottomPanel() {
        gameLog.setEditable(false);

        tree = new TreeItem<>("Root Node");
        tree.setExpanded(true);

        TreeView<String> treeView = new TreeView<>(tree);
        treeView.setPrefWidth(450);
        setLeft(treeView);
        setCenter(gameLog);
    }

    public void log(Object obj) {
        gameLog.appendText(obj.toString() + "\n");
    }

    public void openNode(GEvent event) {
        TreeItem<String> tree = getLastNotClosedEvent();
        TreeItem<String> treeItem = new TreeItem<>(event.toString());
        tree.getChildren().add(treeItem);
        eventList.add(event);
        eventTreeItemMap.put(event, treeItem);
    }

    private TreeItem<String> getLastNotClosedEvent() {
        int size = eventList.size();
        return size > 0 ? eventTreeItemMap.get(eventList.get(size - 1)) : tree;
    }

    public void closeNode(GEvent event) {
        eventTreeItemMap.get(event).setValue(event.toString());
        eventTreeItemMap.remove(event);
        eventList.remove(event);
    }
}
