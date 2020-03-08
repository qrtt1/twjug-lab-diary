package ui;

import domain.DiaryFile;
import domain.Editor;
import domain.EditorManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class DiaryGUI extends JFrame {
    private JPanel panel;
    private JTabbedPane tabs;
    private EditorManager editorManager = new EditorManager();

    public DiaryGUI() {
        super("DiaryGUI");
        configureMenu();
        configureUI();
    }

    private void configureMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("檔案");
        menuBar.add(fileMenu);

        JMenuItem openEmptyFile = new JMenuItem("開新檔案");
        fileMenu.add(openEmptyFile);
        bindEditorEvent(openEmptyFile, () -> editorManager.newEmptyEditor());

        JMenuItem openFile = new JMenuItem("開啟舊檔");
        fileMenu.add(openFile);
        bindEditorEvent(openFile, () -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            int returnVal = chooser.showOpenDialog(DiaryGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return editorManager.openEditor(new DiaryFileImpl(chooser.getSelectedFile()));
            }
            return null;
        });

    }

    private void bindEditorEvent(JMenuItem jMenuItem, EditorCreator creator) {
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor editor = creator.create();
                if (editor == null) {
                    return;
                }
                // add JTextArea to tabs
                // open empty editor
                // bind event
                JTextArea textArea = new JTextArea();

                // 更新 view 的內容
                textArea.setText(editor.getDiary().getContent());
                tabs.add(textArea);
                tabs.setSelectedComponent(textArea);
                textArea.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        try {
                            Document document = e.getDocument();
                            editor.onContentChanged(document.getText(0, document.getLength()));
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        try {
                            Document document = e.getDocument();
                            editor.onContentChanged(document.getText(0, document.getLength()));
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });

            }
        });
    }

    private void configureUI() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            new DiaryGUI();
        });
    }
}
