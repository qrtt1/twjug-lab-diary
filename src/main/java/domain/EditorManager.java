package domain;

import java.util.ArrayList;
import java.util.List;

public class EditorManager {

    private List<Editor> editorList = new ArrayList<>();
    private Editor activeEditor;


    public Editor newEmptyEditor() {
        Editor editor = new Editor();
        activeEditor = editor;
        editorList.add(editor);
        return editor;
    }

    public Editor openEditor(DiaryFile file) {
        Editor editor = newEmptyEditor();
        editor.loadFromFile(file);
        return editor;
    }

    public Editor getActiveEditor() {
        return activeEditor;
    }

    public void setActiveEditor(int index) {
        activeEditor = editorList.get(index);
    }

}
