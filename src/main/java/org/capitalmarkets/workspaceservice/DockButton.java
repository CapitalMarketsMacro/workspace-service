package org.capitalmarkets.workspaceservice;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DockButton {

    private DockButtonNames type;
    private String tooltip;
    private String iconUrl;
    private DockAction action;
    private List<DockButton> options;

    public DockButton() {
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

    public List<DockButton> getOptions() {
        return options;
    }

    public void setOptions(List<DockButton> options) {
        this.options = options;
    }
}
