package org.capitalmarkets.workspaceservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DockProviderCompleteRepository extends MongoRepository<DockProviderCompleteDocument, String> {
}
