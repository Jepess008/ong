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

import com.campuslands.ong.repositories.entities.Tipo_cuotaEntity;
import com.campuslands.ong.services.Tipo_CuotaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tipos_cuotas")
@AllArgsConstructor
public class Tipo_CuotaController {
 
    private Tipo_CuotaService tipo_CuotaService;

    @GetMapping("/")
    public List<Tipo_cuotaEntity> findAll(){
        return tipo_CuotaService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        Tipo_cuotaEntity tipo_cuota= tipo_CuotaService.findById(id);

        if(tipo_cuota!=null){
            response.put("tipo_cuota", tipo_cuota);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ningun tipo de cuota con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }


    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Tipo_cuotaEntity tipo_cuota, BindingResult result) {

        Tipo_cuotaEntity tipo_cuotaNew = null;

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

            tipo_cuotaNew = tipo_CuotaService.save(tipo_cuota);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El Tipo de cuota ha sido creado con éxito");
        response.put("tipo_cuota", tipo_cuotaNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Tipo_cuotaEntity tipo_cuota, BindingResult result,
            @PathVariable Long id) {

        Tipo_cuotaEntity tipo_cuotaUpdate = null;

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

            tipo_cuotaUpdate = tipo_CuotaService.update(id, tipo_cuota);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El tipo de cuota ha sido actualizado con éxito");
        response.put("tipo_cuota", tipo_cuotaUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            tipo_CuotaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El tipo de cuota elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
