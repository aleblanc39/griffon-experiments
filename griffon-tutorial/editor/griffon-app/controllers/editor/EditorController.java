package editor;

import griffon.core.artifact.GriffonController;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Map;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;

@ArtifactProviderFor(GriffonController.class)
public class EditorController extends AbstractGriffonController {
    @MVCMember @Nonnull
    private EditorModel model;
    @MVCMember @Nonnull
    private EditorView view;

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        model.setDocument((Document) args.get("document"));
        runOutsideUI(() -> {
            try {
                final String content = readFileToString(model.getDocument().getFile());
                runInsideUIAsync(() -> model.getDocument().setContents(content));
            } catch (IOException e) {
                getLog().warn("Can't open file", e);
            }
        });
    }

    public void saveFile() {
        try {
            writeStringToFile(model.getDocument().getFile(), view.getEditor().getText());
            runInsideUIAsync(() -> model.getDocument().setContents(view.getEditor().getText()));
        } catch (IOException e) {
            getLog().warn("Can't save file", e);
        }
    }

    public void closeFile() {
        destroyMVCGroup(getMvcGroup().getMvcId());
    }
}


/**
Old stuff below

package editor;

import griffon.core.artifact.GriffonController;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;

@ArtifactProviderFor(GriffonController.class)
public class EditorController extends AbstractGriffonController {
    private EditorModel model;

    public void setModel(EditorModel model) {
        this.model = model;
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void click() {
        int count = Integer.parseInt(model.getClickCount());
        model.setClickCount(String.valueOf(count + 1));
    }
}
**/
