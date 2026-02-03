package org.capitalmarkets.workspaceservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dockProviderComplete")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DockProviderCompleteController {

    private static final Logger log = LoggerFactory.getLogger(DockProviderCompleteController.class);

    private final DockProviderCompleteRepository dockProviderCompleteRepository;

    public DockProviderCompleteController(DockProviderCompleteRepository dockProviderCompleteRepository) {
        this.dockProviderCompleteRepository = dockProviderCompleteRepository;
    }

    @GetMapping
    public ResponseEntity<DockProviderCompleteDocument> getDockProviderComplete() {
        log.info("GET /dockProviderComplete - fetching single document from dockProviderComplete collection");
        List<DockProviderCompleteDocument> all = dockProviderCompleteRepository.findAll();
        if (all.isEmpty()) {
            log.info("GET /dockProviderComplete - no document found, returning empty object");
            return ResponseEntity.ok(new DockProviderCompleteDocument());
        }
        DockProviderCompleteDocument doc = all.get(0);
        log.info("GET /dockProviderComplete - returned document id={}", doc.getId());
        return ResponseEntity.ok(doc);
    }

    @PutMapping
    public DockProviderCompleteDocument putDockProviderComplete(@RequestBody DockProvider dockProvider) {
        log.info("PUT /dockProviderComplete - saving to dockProviderComplete collection id={}", dockProvider.getId());
        DockProviderCompleteDocument doc = DockProviderCompleteDocument.from(dockProvider);
        DockProviderCompleteDocument saved = dockProviderCompleteRepository.save(doc);
        log.info("PUT /dockProviderComplete - saved id={}", saved.getId());
        return saved;
    }
}
