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

import com.campuslands.ong.repositories.entities.CiudadEntity;
import com.campuslands.ong.services.CiudadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Controlador de Ciudad", description = "Metodos disponibles para Ciudad")
@RestController
@RequestMapping("/ciudades")
@AllArgsConstructor
public class CiudadController {
    
    private CiudadService ciudadService;

    @Operation(summary = "Genera una lista de todas las Ciudades Disponibles")
    @GetMapping("/")
    public List<CiudadEntity> findAll(){
        return ciudadService.findAll();
    }

    @Operation(summary = "Busca un id de Ciudad por su id")
    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        CiudadEntity ciudad= ciudadService.findById(id);

        if(ciudad!=null){
            response.put("ciudad", ciudad);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ninguna ciudad con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }

    @Operation(summary = "Crea o registra una Ciudad")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CiudadEntity ciudad, BindingResult result) {

        CiudadEntity ciudadNew = null;

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

            ciudadNew = ciudadService.save(ciudad);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La ciudad ha sido creado con éxito");
        response.put("ciudad", ciudadNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Actualiza elementos de Ciudad")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody CiudadEntity ciudad, BindingResult result,
            @PathVariable Long id) {

        CiudadEntity ciudadUpdate = null;

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

            ciudadUpdate = ciudadService.update(id, ciudad);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", ciudadUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @Operation(summary = "Elimina una Ciudad x id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            ciudadService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La ciudad se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
