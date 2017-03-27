package editor;

import griffon.core.artifact.GriffonController;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

@ArtifactProviderFor(GriffonController.class)
public class ContainerController extends AbstractGriffonController {
    private ContainerModel model;
    private ContainerView view;

    public void open() {

    }

    public void save() {

    }

    public void close() {

    }

    public void quit() {
        getApplication().shutdown();
    }
}
