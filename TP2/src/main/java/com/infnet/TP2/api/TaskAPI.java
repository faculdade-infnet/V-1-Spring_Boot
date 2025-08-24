package com.infnet.TP2.api;

import com.infnet.TP2.facade.TaskFacade;
import com.infnet.TP2.models.TaskModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskAPI {

    // Ativa a injeção de dependência automática
    @Autowired
    private TaskFacade taskFacade;

    // Adiciona uma tarefa
    @PostMapping
    @ResponseBody
    public ResponseEntity<TaskModels> create(@RequestBody TaskModels taskModels) {
        TaskModels created = taskFacade.create(taskModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // Obtém todas as tarefas
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TaskModels>> getAll(){
        List<TaskModels> tasks = taskFacade.getAll();
        return ResponseEntity.ok(tasks); // 200 OK
    }

    // Obtém uma tarefa por id
    @GetMapping("/{taskId}")
    @ResponseBody
    public ResponseEntity<TaskModels> getById(@PathVariable Long taskId) {
        TaskModels task = taskFacade.getById(taskId);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.ok(task); // 200 OK
    }

    // Atualiza uma tarefa por id
    @PutMapping("/{taskId}")
    @ResponseBody
    public ResponseEntity<TaskModels> update(@PathVariable("taskId") Long taskId, @RequestBody TaskModels taskModels) {
        TaskModels existing = taskFacade.getById(taskId);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        taskModels.setId(taskId);
        TaskModels updated = taskFacade.update(taskModels, taskId);
        return ResponseEntity.ok(updated); // 200 OK
    }

    // Deleta uma tarefa por id
    @DeleteMapping("/{taskId}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("taskId") Long taskId) {
        TaskModels existing = taskFacade.getById(taskId);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        taskFacade.delete(taskId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
