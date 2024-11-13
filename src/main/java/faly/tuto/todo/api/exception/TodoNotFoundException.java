package faly.tuto.todo.api.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Todo with id %s does not exist".formatted(id));
    }
}