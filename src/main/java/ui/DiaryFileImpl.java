package ui;

import domain.DiaryFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DiaryFileImpl implements DiaryFile {

    File file;

    public DiaryFileImpl(File file) {
        this.file = file;
    }

    @Override
    public String getContent() {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
