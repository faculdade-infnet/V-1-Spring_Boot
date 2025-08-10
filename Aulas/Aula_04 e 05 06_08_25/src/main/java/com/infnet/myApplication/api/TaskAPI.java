package com.infnet.myApplication.api;
import com.infnet.myApplication.*;

import com.infnet.myApplication.facede.TaskFacade;
import com.infnet.myApplication.models.TaskModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskAPI {

    @Autowired
    private TaskFacade taskFacade;

    @PostMapping
    @ResponseBody
    public TaskModels create(@RequestBody TaskModels taskModels) {
        return taskFacade.create(taskModels);
    }

    @GetMapping
    @ResponseBody
    public List<TaskModels> getAll(){
        return taskFacade.getAll();
    }

    @GetMapping("/{taskId}")
    @ResponseBody
    public TaskModels getById(@PathVariable Long taskId) {
        return taskFacade.getById(taskId);
    }

    @PutMapping("/{taskId}")
    @ResponseBody
    public TaskModels update(@PathVariable("taskId") Long taskId, @RequestBody TaskModels taskModels) {
        return taskFacade.update(taskModels, taskId);
    }

    @DeleteMapping("/{taskId}")
    @ResponseBody
    public String delete(@PathVariable("taskId") Long taskId) {
        return taskFacade.delete(taskId);
    }
}
