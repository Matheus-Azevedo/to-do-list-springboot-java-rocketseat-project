package br.com.matheuseduardo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import br.com.matheuseduardo.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    taskModel.setIdUser((UUID) request.getAttribute("idUser"));

    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The start date must be greater than the current date");
    }
    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The start date must be less than the end date");
    }

    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }

  @GetMapping("/")
  public ResponseEntity findAll(HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser(idUser);
    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable UUID id, @RequestBody TaskModel taskModel, HttpServletRequest request) throws Exception {
    var task = this.taskRepository.findById(id).orElse(null);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    }

    var idUser = (UUID) request.getAttribute("idUser");

    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update this task");
    }
    
    if (taskModel.getTitle() != null) {
      task.setTitle(taskModel.getTitle());
    }
    if (taskModel.getDescription() != null) {
      task.setDescription(taskModel.getDescription());
    }
    if (taskModel.getStartAt() != null) {
      task.setStartAt(taskModel.getStartAt());
    }
    if (taskModel.getEndAt() != null) {
      task.setEndAt(taskModel.getEndAt());
    }
    if (taskModel.getPriority() != null) {
      task.setPriority(taskModel.getPriority());
    }
    
    // Utils.copyNonNullProperties(taskModel, task);
    var taskUpdated = this.taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
  }
}
