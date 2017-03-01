package tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by valdemarrolfsen on 25.02.2017.
 */

@RestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Task>> get() {

        List<Task> tasks = this.repository.findAll();

        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Task> post(@RequestBody Task task) {
        repository.save(task);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Task> put(@PathVariable("id") String id, @RequestBody Task task) {

        Task oldTask = repository.findOne(id);

        oldTask.setCompleted(task.completed);
        repository.save(oldTask);

        return new ResponseEntity<Task>(oldTask, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> put(@PathVariable("id") String id) {
        repository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
