package faly.tuto.todo.api.validator;
import faly.tuto.todo.api.exception.TodoBadRequestException;
import faly.tuto.todo.api.model.Todo;
import faly.tuto.todo.api.utils.StringUtils;
import faly.tuto.todo.api.validator.ITodoValidator;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TodoValidator implements ITodoValidator{

    @Override
    public void validate(Todo todo) {
        if (StringUtils.isBlank(todo.getTitle())) {
            //if (todo.getTitle() == null || todo.getTitle().isBlank()) {
            throw new TodoBadRequestException("title is mandatory");
        }
        if (StringUtils.isBlank(todo.getDescription())) {
            throw new TodoBadRequestException("description is mandatory");
        }
        if (Objects.isNull(todo.getCompleted())) {
            throw new TodoBadRequestException("completed is mandatory");
        }
    }
}
