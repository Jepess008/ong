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

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.repositories.entities.AyudaHumanitariaEntity;
import com.campuslands.ong.services.AyudaHumanitariaService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Controlador de Ayuda Humanitaria", description = "Metodos disponibles para Ayuda Humanitaria")
@RestController
@RequestMapping("/ayudashumanitarias")
@AllArgsConstructor
public class AyudaHumanitariaController {
    
    private AyudaHumanitariaService ayudaHumanitariaService;

    @Operation(summary = "Genera una lista de todas las Ayudas Humanitarias Disponibles")
    @GetMapping("/")
    @JsonView(AyudaHumanitariaDTO.class)
    public List<AyudaHumanitariaDTO> findAll(){
        return ayudaHumanitariaService.findAll();
    }

    @Operation(summary = "Busca un id de Ayuda Humanitaria por su id")
    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        AyudaHumanitariaDTO ayudaHumanitaria= ayudaHumanitariaService.findById(id);

        if(ayudaHumanitaria!=null){
            response.put("ayudaHumanitaria", ayudaHumanitaria);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ningun Ayuda Humanitaria con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }

    @Operation(summary = "Crea o registra una Ayuda Humanitaria")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody AyudaHumanitariaDTO ayudaHumanitaria, BindingResult result) {

        AyudaHumanitariaDTO ayudaHumanitariaNew = null;

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

            ayudaHumanitariaNew = ayudaHumanitariaService.save(ayudaHumanitaria);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La Ayuda Humanitaria ha sido creado con éxito");
        response.put("ayudaHumanitaria", ayudaHumanitariaNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Actualiza elementos de Ayuda Humanitaria")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody AyudaHumanitariaEntity ayudaHumanitaria, BindingResult result,
            @PathVariable Long id) {

        AyudaHumanitariaEntity ayudaHumanitariaUpdate = null;

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

            ayudaHumanitariaUpdate = ayudaHumanitariaService.update(id, ayudaHumanitaria);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La ayuda Humanitaria ha sido actualizado con éxito");
        response.put("ayuda humanitaria", ayudaHumanitariaUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @Operation(summary = "Elimina una Ayuda Humanitaria x id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            ayudaHumanitariaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Ayuda Humanitaria se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
