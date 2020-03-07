package domain;

import java.util.ArrayList;
import java.util.List;

public class EditorManager {

    private List<Editor> editorList = new ArrayList<>();
    private Editor acitveEditor;


    public Editor newEmptyEditor() {
        Editor editor = new Editor();
        acitveEditor = editor;
        editorList.add(editor);
        return editor;
    }

    public Editor getActiveEditor() {
        return acitveEditor;
    }

    public void setActiveEditor(int index) {
        acitveEditor = editorList.get(index);
    }
}
