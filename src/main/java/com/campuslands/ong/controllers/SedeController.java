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

import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.repositories.entities.SedeEntity;
import com.campuslands.ong.services.SedeService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Controlador de Sede", description = "Metodos disponibles para Sede")
@RestController
@RequestMapping("/sedes")
@AllArgsConstructor

public class SedeController {
    
    private SedeService sedeService;

    @Operation(summary = "Genera una lista de todas las Sedes Disponibles")
    @GetMapping("/")
    @JsonView(SedeController.class)
    public List<SedeDTO> findAll(){
        return sedeService.findAllSe();
    }

    @Operation(summary = "Busca un Envio por su id") 
    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        SedeDTO sede= sedeService.findById(id);

        if(sede!=null){
            response.put("sede", sede);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ninguna sede con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }

    @Operation(summary = "Crea o registra un Envio") 
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SedeDTO sede, BindingResult result) {

        SedeDTO sedeNew = null;

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

            sedeNew = sedeService.save(sede);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La sede ha sido creada con éxito");
        response.put("sede", sedeNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Actualiza elementos de Sede")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody SedeEntity sede, BindingResult result,
            @PathVariable Long id) {

        SedeEntity sedeUpdate = null;

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

            sedeUpdate = sedeService.update(id, sede);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La sede ha sido actualizada con éxito");
        response.put("sede", sedeUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Elimina una Sede x id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            sedeService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La sede se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
