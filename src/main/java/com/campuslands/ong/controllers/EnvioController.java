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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.ong.dto.EnvioDTO;
import com.campuslands.ong.services.EnvioService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Controlador de Envio", description = "Metodos disponibles para Envio")
@RestController
@RequestMapping("/envios")
@AllArgsConstructor
public class EnvioController {
    
    private EnvioService envioService;

    @Operation(summary = "Genera una lista de todas los Envios Disponibles")
    @GetMapping("/")
    @JsonView(EnvioDTO.class)
    public List<EnvioDTO> findAll(){
        return envioService.findAll();
    }

    @Operation(summary = "Busca un Envio por su id")
    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        
        Map<String,Object> response=new HashMap<>();

        EnvioDTO envio= envioService.findById(id);

        if(envio!=null){
            response.put("envio", envio);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("mensaje", new String("No existe ningun envio con ese id"));
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
    
    }

    @Operation(summary = "Crea o registra un Envio")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody EnvioDTO envio, BindingResult result) {

        EnvioDTO envioNew = null;

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

            envioNew = envioService.save(envio);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El envio ha sido creado con éxito");
        response.put("envio", envioNew);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @Operation(summary = "Elimina un Envio x id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            envioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El envio se ha elimando con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
