package org.capitalmarkets.workspaceservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dockConfig")
@CrossOrigin(origins = "*")
public class WorkspaceDockController {

    private static final Logger log = LoggerFactory.getLogger(WorkspaceDockController.class);

    private final DockConfigRepository dockConfigRepository;

    public WorkspaceDockController(DockConfigRepository dockConfigRepository) {
        this.dockConfigRepository = dockConfigRepository;
    }

    @GetMapping
    public List<DockConfig> getDockConfig() {
        log.info("GET /dockConfig - fetching all dock configs");
        List<DockConfig> configs = dockConfigRepository.findAll();
        log.info("GET /dockConfig - returned {} config(s)", configs.size());
        log.info("GET /dockConfig - configs: {}", configs);

        return configs;
    }

    @PutMapping
    public DockConfig putDockConfig(@RequestBody DockConfig dockConfig) {
        log.info("PUT /dockConfig - saving dock config id={}", dockConfig.getId());
        dockConfigRepository.findFirstById(dockConfig.getId()).ifPresent(existing ->
                dockConfig.setMongoId(existing.getMongoId()));
        DockConfig saved = dockConfigRepository.save(dockConfig);
        log.info("PUT /dockConfig - saved dock config id={}", saved.getId());
        return saved;
    }
}
