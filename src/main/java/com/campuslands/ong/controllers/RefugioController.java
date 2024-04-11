package com.campuslands.ong.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.ong.dto.RefugioDTO;
import com.campuslands.ong.repositories.entities.RefugioEntity;
import com.campuslands.ong.services.RefugioService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/refugios")
@AllArgsConstructor
public class RefugioController {
    
    private RefugioService refugioService;

    @GetMapping("/")
    @JsonView(RefugioDTO.class)
    public List<RefugioDTO> findAll(){
        return refugioService.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        RefugioDTO refugio= refugioService.findById(id);

        if(refugio!=null){
            response.put("refugio", refugio);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ningun refugio con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }


    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody RefugioDTO refugio, BindingResult result) {

        RefugioDTO refugioNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {

            refugioNew = refugioService.save(refugio);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El refugio ha sido creado con éxito");
        response.put("refugio", refugioNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody RefugioEntity refugio, BindingResult result,
            @PathVariable Long id) {

        RefugioEntity refugioUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {

            refugioUpdate = refugioService.update(id, refugio);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El refugio ha sido actualizado con éxito");
        response.put("refugio", refugioUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            refugioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El refugio ha sido elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
