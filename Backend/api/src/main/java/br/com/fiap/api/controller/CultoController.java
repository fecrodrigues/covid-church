package br.com.fiap.api.controller;

import br.com.fiap.api.dto.CultoPartialUpdateDTO;
import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.repository.CultoRepository;
import br.com.fiap.api.repository.InstituicaoRepository;
import br.com.fiap.api.utils.JsonNullableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CultoController {
    @Autowired
    private CultoModel cultoModel;

    @Autowired
    private CultoRepository cultoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

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

    @GetMapping("/cultos/{idInstituicao}")
    public List<CultoModel> getCultoByIdInstituicao(@PathVariable String idInstituicao) {
        return cultoRepository.findByIdInstituicao(idInstituicao);
    }

    @PatchMapping("/culto/{idCulto}")
    public ResponseEntity<Void> pathCulto(@PathVariable String idCulto, @RequestBody CultoPartialUpdateDTO cultoPartialUpdateDTO) {
        Optional<CultoModel> dbCultoModel = cultoRepository.findById(idCulto);
        if (dbCultoModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            CultoModel cultoModelToEdit = dbCultoModel.get();
            JsonNullableUtils.changeIfPresent(cultoPartialUpdateDTO.getIdInstituicao(), cultoModelToEdit::setIdInstituicao);
            JsonNullableUtils.changeIfPresent(cultoPartialUpdateDTO.getDescricao(), cultoModelToEdit::setDescricao);
            JsonNullableUtils.changeIfPresent(cultoPartialUpdateDTO.getData(), cultoModelToEdit::setData);
            JsonNullableUtils.changeIfPresent(cultoPartialUpdateDTO.getDuracao(), cultoModelToEdit::setDuracao);
            JsonNullableUtils.changeIfPresent(cultoPartialUpdateDTO.getCapacidade(), cultoModelToEdit::setCapacidade);

            cultoRepository.save(cultoModelToEdit);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

/*    @DeleteMapping("/culto/{idCulto}")
    public ResponseEntity<Void> delCulto(@PathVariable String idCulto) {
        CultoModel
    }*/

}
