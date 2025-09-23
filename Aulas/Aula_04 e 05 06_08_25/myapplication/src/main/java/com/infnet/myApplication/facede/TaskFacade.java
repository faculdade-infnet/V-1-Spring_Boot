package com.infnet.myApplication.facede;

import com.infnet.myApplication.models.TaskModels;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskFacade {
    private static final Map<Long, TaskModels> tasks = new HashMap<>();

    // Set
    public TaskModels create(TaskModels taskModels) {
        Long nextId = tasks.keySet().size() + 1l;
        taskModels.setId(nextId);
        tasks.put(nextId, taskModels);
        return taskModels;
    }

     // Get by Id
     public TaskModels getById(Long taskId) {
        return tasks.get(taskId);
     }

     // Get All
     public List<TaskModels> getAll() {
        return new ArrayList<>(tasks.values());
     }

    // Update
    public TaskModels update(TaskModels taskModels, Long taskId) {
        tasks.put(taskId, taskModels);
        return taskModels;
    }

     // Delete
     public String delete(Long taskId) {
        tasks.remove(taskId);
        return "DELETE";
     }
}
