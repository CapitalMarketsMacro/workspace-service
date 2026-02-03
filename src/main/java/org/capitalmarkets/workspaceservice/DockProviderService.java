package org.capitalmarkets.workspaceservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DockProviderService {

    private final DockConfigRepository dockConfigRepository;
    private final DockButtonRepository dockButtonRepository;

    public DockProviderService(DockConfigRepository dockConfigRepository, DockButtonRepository dockButtonRepository) {
        this.dockConfigRepository = dockConfigRepository;
        this.dockButtonRepository = dockButtonRepository;
    }

    public List<DockProvider> findAll() {
        List<DockConfig> configs = dockConfigRepository.findAll();
        List<DockProvider> result = new ArrayList<>();
        for (DockConfig config : configs) {
            DockProvider provider = toProvider(config);
            provider.setButtons(buildButtonTree(config.getId(), null));
            result.add(provider);
        }
        return result;
    }

    public DockProvider save(DockProvider provider) {
        String id = provider.getId();
        DockConfig config = dockConfigRepository.findFirstById(id)
                .orElse(new DockConfig());
        config.setId(id);
        config.setTitle(provider.getTitle());
        config.setIcon(provider.getIcon());
        config.setWorkspaceComponents(provider.getWorkspaceComponents() != null ? provider.getWorkspaceComponents() : new ArrayList<>());
        config.setDisableUserRearrangement(provider.isDisableUserRearrangement());
        dockConfigRepository.save(config);

        dockButtonRepository.deleteByDockConfigId(id);
        if (provider.getButtons() != null && !provider.getButtons().isEmpty()) {
            flattenAndSaveButtons(id, null, provider.getButtons(), 0);
        }

        DockProvider saved = new DockProvider();
        saved.setId(config.getId());
        saved.setTitle(config.getTitle());
        saved.setIcon(config.getIcon());
        saved.setWorkspaceComponents(config.getWorkspaceComponents());
        saved.setDisableUserRearrangement(config.isDisableUserRearrangement());
        saved.setButtons(buildButtonTree(id, null));
        return saved;
    }

    private DockProvider toProvider(DockConfig config) {
        DockProvider p = new DockProvider();
        p.setId(config.getId());
        p.setTitle(config.getTitle());
        p.setIcon(config.getIcon());
        p.setWorkspaceComponents(config.getWorkspaceComponents());
        p.setDisableUserRearrangement(config.isDisableUserRearrangement());
        return p;
    }

    private List<DockButton> buildButtonTree(String dockConfigId, String parentButtonId) {
        List<DockButtonDocument> docs = parentButtonId == null
                ? dockButtonRepository.findByDockConfigIdAndParentButtonIdIsNullOrderBySortOrderAsc(dockConfigId)
                : dockButtonRepository.findByDockConfigIdAndParentButtonIdOrderBySortOrderAsc(dockConfigId, parentButtonId);
        List<DockButton> buttons = new ArrayList<>();
        for (DockButtonDocument doc : docs) {
            List<DockButton> options = buildButtonTree(dockConfigId, doc.getId());
            DockButton btn = toButton(doc, options);
            buttons.add(btn);
        }
        return buttons;
    }

    private DockButton toButton(DockButtonDocument doc, List<DockButton> options) {
        DockButton btn = new DockButton();
        btn.setType(doc.getType());
        btn.setTooltip(doc.getTooltip());
        btn.setIconUrl(doc.getIconUrl());
        if (DockButtonNames.DropdownButton.equals(doc.getType())) {
            btn.setOptions(options != null ? options : new ArrayList<>());
        } else {
            btn.setAction(doc.getAction());
        }
        return btn;
    }

    private void flattenAndSaveButtons(String dockConfigId, String parentButtonId, List<DockButton> buttons, int startOrder) {
        for (int i = 0; i < buttons.size(); i++) {
            DockButton btn = buttons.get(i);
            DockButtonDocument doc = new DockButtonDocument();
            doc.setId(UUID.randomUUID().toString());
            doc.setDockConfigId(dockConfigId);
            doc.setParentButtonId(parentButtonId);
            doc.setType(btn.getType());
            doc.setTooltip(btn.getTooltip());
            doc.setIconUrl(btn.getIconUrl());
            if (DockButtonNames.DropdownButton.equals(btn.getType())) {
                doc.setAction(null);
            } else {
                doc.setAction(btn.getAction());
            }
            doc.setSortOrder(startOrder + i);
            dockButtonRepository.save(doc);

            if (btn.getOptions() != null && !btn.getOptions().isEmpty()) {
                flattenAndSaveButtons(dockConfigId, doc.getId(), btn.getOptions(), 0);
            }
        }
    }
}
