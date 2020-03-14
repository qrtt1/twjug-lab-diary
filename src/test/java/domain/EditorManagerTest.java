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

    @Test
    public void testOpenEditor() {
        EditorManager m = new EditorManager();
        Editor editor = m.openEditor(new DiaryFile() {

            @Override
            public String getContent() {
                return "舊日記";
            }

            @Override
            public void setContent(String content) {

            }
        });
        Assert.assertEquals("舊日記", m.getActiveEditor().getDiary().getContent());
    }

    @Test
    public void testSaveFile() {
        EditorManager m = new EditorManager();
        Editor editor = m.newEmptyEditor();
        editor.onContentChanged("實作存檔");
        editor.saveToFile(new DiaryFile() {

            private String storage;

            @Override
            public String getContent() {
                return storage;
            }

            @Override
            public void setContent(String content) {
                this.storage = content;
            }
        });

        Assert.assertEquals("實作存檔", m.openEditor(editor.getDiaryFile()).getDiary().getContent());
    }
}
