package faly.tuto.todo.api.controller;

import java.util.List;
import faly.tuto.todo.api.model.Todo;
import faly.tuto.todo.api.repository.ITodoRepository;
import faly.tuto.todo.api.exception.TodoNotFoundException;
import faly.tuto.todo.api.validator.ITodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/todos")
public class TodoController {
    @Autowired
    private ITodoRepository repository;

    @Autowired
    private ITodoValidator validator;

    @GetMapping
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        validator.validate(todo);
        return repository.save(todo);
    }

    @PutMapping("{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        validator.validate(todo);
        var entity = repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.getCompleted());
        repository.save(entity);
        return entity;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
