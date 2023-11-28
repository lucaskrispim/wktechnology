package com.example.wktechnology.model.repository;

import com.example.wktechnology.model.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Page<Pessoa> findAll(Pageable pageable);
}
