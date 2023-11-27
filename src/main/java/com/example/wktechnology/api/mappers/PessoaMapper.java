package com.example.wktechnology.api.mappers;

import com.example.wktechnology.api.dto.PessoaDTO;
import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.DateFormatter;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;

public class PessoaMapper {

    public static Pessoa toModel(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setRg(pessoaDTO.getRg());

        pessoa.setDataNascimento(DateFormatter.parseDataNascimento(pessoaDTO.getDataNasc()) );

        pessoa.setSexo(Sexo.fromDescricao(pessoaDTO.getSexo()));

        pessoa.setMae(pessoaDTO.getMae());
        pessoa.setPai(pessoaDTO.getPai());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setNumero(pessoaDTO.getNumero());
        pessoa.setBairro(pessoaDTO.getBairro());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setEstado(pessoaDTO.getEstado());
        pessoa.setTelefoneFixo(pessoaDTO.getTelefoneFixo());
        pessoa.setCelular(pessoaDTO.getCelular());
        pessoa.setAltura(pessoaDTO.getAltura());
        pessoa.setPeso(pessoaDTO.getPeso());
        pessoa.setTipoSanguineo(TipoSanguineo.fromDescricao( pessoaDTO.getTipoSanguineo()));
        return pessoa;
    }

    public static PessoaDTO toDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setCpf(pessoa.getCpf());
        pessoaDTO.setRg(pessoa.getRg());
        pessoaDTO.setDataNasc(pessoa.getDataNascimento().toString());
        pessoaDTO.setSexo(pessoa.getSexo());
        pessoaDTO.setMae(pessoa.getMae());
        pessoaDTO.setPai(pessoa.getPai());
        pessoaDTO.setEmail(pessoa.getEmail());
        pessoaDTO.setCep(pessoa.getCep());
        pessoaDTO.setEndereco(pessoa.getEndereco());
        pessoaDTO.setNumero(pessoa.getNumero());
        pessoaDTO.setBairro(pessoa.getBairro());
        pessoaDTO.setCidade(pessoa.getCidade());
        pessoaDTO.setEstado(pessoa.getEstado());
        pessoaDTO.setTelefoneFixo(pessoa.getTelefoneFixo());
        pessoaDTO.setCelular(pessoa.getCelular());
        pessoaDTO.setAltura(pessoa.getAltura());
        pessoaDTO.setPeso(pessoa.getPeso());
        pessoaDTO.setTipoSanguineo( pessoa.getTipoSanguineo() );
        return pessoaDTO;
    }
}
