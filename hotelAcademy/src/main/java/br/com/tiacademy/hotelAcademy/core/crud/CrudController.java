package br.com.tiacademy.hotelAcademy.core.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

public abstract class CrudController<T, ID> {

    @Autowired
    public CrudService<T, ID> service;

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        List<T> listEntity = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") ID id) {
        T serviceResponse = service.findById(id);

        if (Objects.isNull(serviceResponse)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(serviceResponse);
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<T> editSave(@RequestBody T entity, @PathVariable("id") ID id) {
        return ResponseEntity.ok(service.editSave(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") ID id) {
         service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}