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

import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.repositories.entities.AyudaMateriialEntity;
import com.campuslands.ong.services.AyudaMaterialService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Controlador de Ayuda Material", description = "Metodos disponibles para Ayuda Material")
@RestController
@RequestMapping("/ayudasMateriales")
@AllArgsConstructor
public class AyudaMaterialController {
    

    private AyudaMaterialService ayudaMaterialService;

    @Operation(summary = "Genera una lista de todas las ayudas Materiales Disponibles")
    @GetMapping("/")
    @JsonView(AyudaMaterialDTO.class)
    public List<AyudaMaterialDTO> findAll(){
        return ayudaMaterialService.findAll();
    }

    @Operation(summary = "Busca un id de Ayuda Material por su id")
    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        AyudaMaterialDTO ayudaMaterial= ayudaMaterialService.findById(id);

        if(ayudaMaterial!=null){
            response.put("ayudaMaterial", ayudaMaterial);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ninguna Ayuda Material con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }

    @Operation(summary = "Crea o registra una Ayuda Material")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody AyudaMaterialDTO ayudaMaterial, BindingResult result) {

        AyudaMaterialDTO ayudaMaterialNew = null;

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

            ayudaMaterialNew = ayudaMaterialService.save(ayudaMaterial);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La Ayuda Material ha sido creado con éxito");
        response.put("ayudaMaterial", ayudaMaterialNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Actualiza elementos de Ayuda Material")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody AyudaMateriialEntity ayudaMaterial, BindingResult result,
            @PathVariable Long id) {

        AyudaMateriialEntity ayudaMaterialUpdate = null;

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

            ayudaMaterialUpdate = ayudaMaterialService.update(id, ayudaMaterial);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el upate en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La Ayuda Material ha sido actualizado con éxito");
        response.put("ayudaMaterial", ayudaMaterialUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @Operation(summary = "Elimina una Ayuda Material x id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            ayudaMaterialService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Ayuda Material se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
