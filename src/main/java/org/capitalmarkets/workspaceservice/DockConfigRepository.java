package org.capitalmarkets.workspaceservice;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DockConfigRepository extends MongoRepository<DockConfig, ObjectId> {

    /** Find by business id (the "id" field), not MongoDB _id. */
    Optional<DockConfig> findFirstById(String id);
}
