package org.capitalmarkets.workspaceservice;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dockConfig")
public class DockConfig {

    /** MongoDB document _id (auto-generated if null on insert). */
    @Id
    private ObjectId mongoId;
    /** Business id (e.g. "CapitalMarketsDock"), distinct from MongoDB _id. */
    private String id;
    private String title;
    private String icon;
    private List<String> workspaceComponents;
    private boolean disableUserRearrangement;

    public DockConfig() {
    }

    public DockConfig(String id, String title, String icon, List<String> workspaceComponents, boolean disableUserRearrangement) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.workspaceComponents = workspaceComponents;
        this.disableUserRearrangement = disableUserRearrangement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getMongoId() {
        return mongoId;
    }

    public void setMongoId(ObjectId mongoId) {
        this.mongoId = mongoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getWorkspaceComponents() {
        return workspaceComponents;
    }

    public void setWorkspaceComponents(List<String> workspaceComponents) {
        this.workspaceComponents = workspaceComponents;
    }

    public boolean isDisableUserRearrangement() {
        return disableUserRearrangement;
    }

    public void setDisableUserRearrangement(boolean disableUserRearrangement) {
        this.disableUserRearrangement = disableUserRearrangement;
    }

    @Override
    public String toString() {
        return "DockConfig{" +
                "mongoId=" + mongoId +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", workspaceComponents=" + workspaceComponents +
                ", disableUserRearrangement=" + disableUserRearrangement +
                '}';
    }
}
