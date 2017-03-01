package tasks;

/**
 * Created by valdemarrolfsen on 24.02.2017.
 */

import org.springframework.data.annotation.Id;

public class Task {

    @Id
    public String id;

    public String name;

    public boolean completed;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%s, name='%s']",
                id, name);
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
