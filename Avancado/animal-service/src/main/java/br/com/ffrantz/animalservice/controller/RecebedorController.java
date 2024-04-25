package br.com.ffrantz.animalservice.controller;

import br.com.ffrantz.animalservice.DTO.RecebedorDTO;
import br.com.ffrantz.animalservice.entity.Recebedor;
import br.com.ffrantz.animalservice.repository.RecebedorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recebedores")
public class RecebedorController {

    private RecebedorRepository repository;

    public RecebedorController(RecebedorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private List<RecebedorDTO> findAll() {
        List<Recebedor> recebedores = repository.findAll();
        List<RecebedorDTO> recebedoresDTO = new ArrayList<>();

        contarAnimais(recebedores, recebedoresDTO);

        return recebedoresDTO;
    }

    private void contarAnimais(List<Recebedor> recebedores, List<RecebedorDTO> recebedoresDTO) {
        for (Recebedor recebedor : recebedores) {
            RecebedorDTO recebedorDTO = new RecebedorDTO();
            recebedorDTO.setId(recebedor.getId());
            recebedorDTO.setNome(recebedor.getNome());
            recebedorDTO.setQuantidadeAnimais(recebedor.getAnimaisRecebidos().size());
            recebedoresDTO.add(recebedorDTO);
        }
    }
}
