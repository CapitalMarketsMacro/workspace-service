package org.capitalmarkets.workspaceservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dockButton")
public class DockButtonDocument {

    @Id
    private String id;
    private String dockConfigId;
    private String parentButtonId;
    private DockButtonNames type;
    private String tooltip;
    private String iconUrl;
    private DockAction action;
    private int sortOrder;

    public DockButtonDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDockConfigId() {
        return dockConfigId;
    }

    public void setDockConfigId(String dockConfigId) {
        this.dockConfigId = dockConfigId;
    }

    public String getParentButtonId() {
        return parentButtonId;
    }

    public void setParentButtonId(String parentButtonId) {
        this.parentButtonId = parentButtonId;
    }

    public DockButtonNames getType() {
        return type;
    }

    public void setType(DockButtonNames type) {
        this.type = type;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public DockAction getAction() {
        return action;
    }

    public void setAction(DockAction action) {
        this.action = action;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
