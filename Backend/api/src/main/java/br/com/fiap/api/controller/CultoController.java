package br.com.fiap.api.controller;

import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.repository.CultoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CultoController {
    @Autowired
    private CultoModel cultoModel;

    @Autowired
    private CultoRepository cultoRepository;

    @PostMapping("/culto")
    public ResponseEntity<CultoModel> addCulto(@RequestBody CultoModel culto) {
        CultoModel dbCulto = cultoRepository.findByIdInstituicaoAndData(culto.getIdInstituicao(), culto.getData());

        if (dbCulto == null) {
            cultoModel = cultoRepository.save(culto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cultoModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    


}
