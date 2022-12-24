package br.com.jackson.todo.service;

import br.com.jackson.todo.model.Todo;
import br.com.jackson.todo.repository.TodoRepository;
import br.com.jackson.todo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo findById(Integer id) {
        Optional<Todo> obj = todoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nâo encontrado! id:" + id + ", tipo" + Todo.class.getName()));
    }

    public List<Todo> findAllOpen() {
        List<Todo> list = todoRepository.findAllOpen();
        return list;
    }

    public List<Todo> findAllClose() {
        List<Todo> list = todoRepository.findAllClose();
        return list;
    }

    public List<Todo> findAll() {
        List<Todo> list = todoRepository.findAll();
        return list;
    }

    public Todo create(Todo obj) {
        obj.setId(null);
        return todoRepository.save(obj);
    }

    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }

    public Todo update(Integer id, Todo obj) {
        Todo newObj = findById(id);
        newObj.setTitulo(obj.getTitulo());
        newObj.setDescricao(obj.getDescricao());
        newObj.setFinalizado(obj.getFinalizado());
        return todoRepository.save(newObj);
    }
}
