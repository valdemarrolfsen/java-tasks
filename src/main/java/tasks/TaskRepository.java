package tasks;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByName(@Param("name") String name);

}