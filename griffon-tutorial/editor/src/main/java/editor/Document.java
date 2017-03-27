package editor;

import org.codehaus.griffon.runtime.core.AbstractObservable;

import java.io.File;

public class Document extends AbstractObservable {
    private String title;
    private String contents;
    private boolean dirty;
    private File file;

    public Document() {
    }

    public Document(File file, String title) {
        this.file = file;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        firePropertyChange("title", this.title, this.title = title);
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        firePropertyChange("contents", this.contents, this.contents = contents);
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        firePropertyChange("dirty", this.dirty, this.dirty = dirty);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        firePropertyChange("file", this.file, this.file = file);
    }

    public void copyTo(Document doc) {
        doc.title = title;
        doc.contents = contents;
        doc.dirty = dirty;
        doc.file = file;
    }
}
