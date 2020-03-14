package domain;

import java.net.URL;

public class Editor {
    
    private DiaryFile diaryFile;
    private Diary diary = new Diary();

    public void onContentChanged(String content) {
        diary.setContent(content);
        System.out.println(this + " " + diary.getContent());
    }

    public Diary getDiary() {
        return diary;
    }

    public DiaryFile getDiaryFile() {
        return diaryFile;
    }

    public void loadFromFile(DiaryFile diaryFile) {
        this.diaryFile = diaryFile;
        diary.setContent(diaryFile.getContent());
    }

    public void saveToFile(DiaryFile diaryFile) {
        diaryFile.setContent(diary.getContent());
        this.diaryFile = diaryFile;
    }
}
