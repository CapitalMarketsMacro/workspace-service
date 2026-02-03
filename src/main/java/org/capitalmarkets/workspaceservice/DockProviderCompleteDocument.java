package org.capitalmarkets.workspaceservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dockProviderComplete")
public class DockProviderCompleteDocument {

    @Id
    private String id;
    private String title;
    private String icon;
    private List<String> workspaceComponents;
    private boolean disableUserRearrangement;
    private List<DockButton> buttons;

    public DockProviderCompleteDocument() {
    }

    public DockProviderCompleteDocument(String id, String title, String icon,
                                        List<String> workspaceComponents, boolean disableUserRearrangement,
                                        List<DockButton> buttons) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.workspaceComponents = workspaceComponents;
        this.disableUserRearrangement = disableUserRearrangement;
        this.buttons = buttons;
    }

    public static DockProviderCompleteDocument from(DockProvider p) {
        if (p == null) return null;
        DockProviderCompleteDocument doc = new DockProviderCompleteDocument();
        doc.setId(p.getId());
        doc.setTitle(p.getTitle());
        doc.setIcon(p.getIcon());
        doc.setWorkspaceComponents(p.getWorkspaceComponents());
        doc.setDisableUserRearrangement(p.isDisableUserRearrangement());
        doc.setButtons(p.getButtons());
        return doc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<DockButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<DockButton> buttons) {
        this.buttons = buttons;
    }
}
