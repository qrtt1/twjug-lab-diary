package domain;

import org.junit.Assert;
import org.junit.Test;

public class EditorManagerTest {

    @Test
    public void testEditorManagerCreateDiaries() {
        EditorManager m = new EditorManager();
        Editor editor = m.newEmptyEditor();
        // view -> event -> editor -> diary
        editor.onContentChanged("第1篇日記");

        Assert.assertEquals("第1篇日記", editor.getDiary().getContent());

        Editor anotherEditor = m.newEmptyEditor();
        anotherEditor.onContentChanged("第2篇日記");
        Assert.assertEquals("第2篇日記", m.getActiveEditor().getDiary().getContent());

        m.setActiveEditor(0);
        Assert.assertEquals("第1篇日記", m.getActiveEditor().getDiary().getContent());
    }

    public void testOpenEditor() {

    }
}
