package com.example.wktechnology.api.controller;

import com.example.wktechnology.api.dto.LimiteDTO;
import com.example.wktechnology.api.dto.PessoaDTO;
import com.example.wktechnology.api.mappers.PessoaMapper;
import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.service.PessoaService;
import com.example.wktechnology.utils.CompatibilidadeSanguinea;
import com.example.wktechnology.utils.enums.EstadoBrasileiro;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wktechnology.exception.RegraNegocioException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private CompatibilidadeSanguinea compatibilidadeSanguinea;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasAsPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/countEstados")
    public ResponseEntity<Map<EstadoBrasileiro,Long>> contarEstados() {
        List<Pessoa> pessoas = pessoaService.listarTodasAsPessoas();

        Map<EstadoBrasileiro, Long> contagemEstados = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getEstado, Collectors.counting()));


        return ResponseEntity.ok(contagemEstados);
    }

    @GetMapping("/imcMedioPorFaixaEtaria")
    public ResponseEntity<Map<String,Double>> imcMedioPorFaixaEtaria() {
        return ResponseEntity.ok(pessoaService.listarImcMedioAgrupadoPorFaixaEtaria());
    }

    @PostMapping("/imcMaiorQuePorSexo")
    public ResponseEntity<Map<Sexo,Double>> imcMaiorQuePorSexo(@RequestBody LimiteDTO limiteDTO) {
        try {
            return ResponseEntity.ok(pessoaService.listarImcMaiorQuePorSexo( limiteDTO.getLimite()));
        } catch (Exception e) {
            String mensagemErro = "Ocorreu um erro ao calcular o imc limite por sexo: " + e.getMessage();
            Map<String, String> response = new HashMap<>();
            response.put("mensagemErro", mensagemErro);

            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mediaDeIdadePorSexo")
    public ResponseEntity<Map<TipoSanguineo,Double>> mediaDeIdadeAgrupadaPorSexo() {
        try {
            return ResponseEntity.ok(pessoaService.listarIdadeMediaPorSexo());
        } catch (Exception e) {
            String mensagemErro = "Ocorreu um erro ao calcular a idade média por sexo: " + e.getMessage();
            Map<String, String> response = new HashMap<>();
            response.put("mensagemErro", mensagemErro);

            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quantidadeDeDoadoresPorRecptor")
    public ResponseEntity <Map<TipoSanguineo, Integer>> quantidadeDeDoadoresPorRecptor() {
        try {
            return ResponseEntity.ok(pessoaService.quantidadeDeDoadoresPorReceptor(compatibilidadeSanguinea));
        } catch (Exception e) {
            String mensagemErro = "Ocorreu um erro ao calcular a quantidade de doadores por receptor: " + e.getMessage();
            Map<String, String> response = new HashMap<>();
            response.put("mensagemErro", mensagemErro);

            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getPessoaById(@PathVariable Long id) {

        Optional<Pessoa> pessoa = pessoaService.encontrarPessoaPorId(id);
        if (pessoa.isEmpty()) {
            return new ResponseEntity("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Optional<Pessoa>> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoa newPessoa = pessoaService.salvarPessoa(PessoaMapper.toModel(pessoaDTO));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Optional.ofNullable(newPessoa));
        } catch (Exception e) {
            String mensagemErro = "Ocorreu um erro ao criar a pessoa: " + e.getMessage();
            Map<String, String> response = new HashMap<>();
            response.put("mensagemErro", mensagemErro);

            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.encontrarPessoaPorId(id);
        if (!pessoa.isPresent()) {
            return new ResponseEntity("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            pessoaService.deletarPessoa(pessoa.get().getId());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoa = pessoaService.encontrarPessoaPorId(id);
        if (pessoa.isEmpty()) {
            return new ResponseEntity("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Pessoa newPessoa = PessoaMapper.toModel(pessoaDTO);
            newPessoa.setId(id);
            pessoaService.salvarPessoa(newPessoa);
            return ResponseEntity.ok(newPessoa);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

