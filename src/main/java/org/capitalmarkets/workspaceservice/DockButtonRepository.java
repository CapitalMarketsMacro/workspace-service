package org.capitalmarkets.workspaceservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DockButtonRepository extends MongoRepository<DockButtonDocument, String> {

    List<DockButtonDocument> findByDockConfigIdAndParentButtonIdIsNullOrderBySortOrderAsc(String dockConfigId);

    List<DockButtonDocument> findByDockConfigIdAndParentButtonIdOrderBySortOrderAsc(String dockConfigId, String parentButtonId);

    void deleteByDockConfigId(String dockConfigId);
}
