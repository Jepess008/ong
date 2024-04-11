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

import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.repositories.entities.SocioEntity;
import com.campuslands.ong.services.SocioService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/socios")
@AllArgsConstructor
public class SocioController {
    
    private SocioService socioService;

     @GetMapping("/")
     @JsonView(SocioController.class)
    public List<SocioDTO> findAll(){
        return socioService.findAllS();
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id) throws Exception{
        
        Map<String,Object> response=new HashMap<>();

        SocioDTO socio= socioService.findById(id);

        if(socio!=null){
            response.put("socio", socio);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            
            response.put("mensaje", new String("No existe ningun socio con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }


    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SocioDTO socio, BindingResult result) {

        SocioDTO socioNew = null;

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

            socioNew = socioService.save(socio);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El socio ha sido creado con éxito");
        response.put("socio", socioNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody SocioEntity socio, BindingResult result,
            @PathVariable Long id) {

        SocioEntity socioUpdate = null;

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

            socioUpdate = socioService.update(id, socio);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("socio", socioUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            socioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El socio se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    
}
