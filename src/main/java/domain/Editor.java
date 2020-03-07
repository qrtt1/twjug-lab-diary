package domain;

import java.net.URL;

public class Editor {

    private Diary diary = new Diary();

    public void onContentChanged(String content) {
        diary.setContent(content);
        System.out.println(this + " " + diary.getContent());
    }

    public Diary getDiary() {
        return diary;
    }
}
