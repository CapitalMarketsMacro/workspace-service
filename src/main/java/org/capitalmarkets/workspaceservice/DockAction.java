package org.capitalmarkets.workspaceservice;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DockAction {

    private String id;
    private String customData;

    public DockAction() {
    }

    public DockAction(String id, String customData) {
        this.id = id;
        this.customData = customData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }
}
