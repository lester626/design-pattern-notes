package memento;

import java.util.*;

public class EditorText {
    private String str;

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr(){
        return str;
    }

    public EditorTextMemento storePointMemento() {
        System.out.println("Text saved in memento.");
        return new EditorTextMemento(this.str);
    }

    public String restorePointMemento(EditorTextMemento editorTextMemento) {
        str = editorTextMemento.getStr();
        return str;
    }
}

class EditorTextMemento {
    private static String str;

    public EditorTextMemento(final String str) {
        this.str = str;
    }

    public static String getStr() {
        return str;
    }
}

class EditorTextMementoManager {
    private Deque<String> saves = new ArrayDeque<>();

    public void addSave(String memento) {
        System.out.println("Text saved in deque.");
        saves.offer(memento);
    }

    public String getMemento() {
        return saves.getLast();
    }
}

class MementoMain {
    public static void main(String[] args) {
        EditorText editorTextA = new EditorText();
        EditorTextMementoManager editorTextMementoManagerA = new EditorTextMementoManager();

        editorTextA.setStr("Hello IntelliJ");
        editorTextA.storePointMemento();
        editorTextMementoManagerA.addSave(editorTextA.getStr());

        editorTextA.setStr("Hello World");
        editorTextA.storePointMemento();
        editorTextMementoManagerA.addSave(editorTextA.getStr());

        editorTextA.setStr("Hello Java");
        editorTextA.storePointMemento();
        editorTextMementoManagerA.addSave(editorTextA.getStr());

        System.out.println(EditorTextMemento.getStr());
        System.out.println(editorTextMementoManagerA.getMemento());
    }
}