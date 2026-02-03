package org.capitalmarkets.workspaceservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dockProvider")
@CrossOrigin(origins = "*")
public class DockProviderController {

    private static final Logger log = LoggerFactory.getLogger(DockProviderController.class);

    private final DockProviderService dockProviderService;

    public DockProviderController(DockProviderService dockProviderService) {
        this.dockProviderService = dockProviderService;
    }

    @GetMapping
    public List<DockProvider> getDockProvider() {
        log.info("GET /dockProvider - fetching all dock providers (config + buttons)");
        List<DockProvider> providers = dockProviderService.findAll();
        log.info("GET /dockProvider - returned {} provider(s)", providers.size());
        return providers;
    }

    @PutMapping
    public DockProvider putDockProvider(@RequestBody DockProvider dockProvider) {
        log.info("PUT /dockProvider - saving dock provider id={} (DockConfig + DockButtons)", dockProvider.getId());
        DockProvider saved = dockProviderService.save(dockProvider);
        log.info("PUT /dockProvider - saved dock provider id={}", saved.getId());
        return saved;
    }
}
