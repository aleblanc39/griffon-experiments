import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {
    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "editor")
                .e("startupGroups", asList("container"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("container", map()
                    .e("model", "editor.ContainerModel")
                    .e("view", "editor.ContainerView")
                    .e("controller", "editor.ContainerController")
                )
            );
    }
}