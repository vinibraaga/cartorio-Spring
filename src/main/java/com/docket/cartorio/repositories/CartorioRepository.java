package com.docket.cartorio.repositories;

import com.docket.cartorio.entities.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartorioRepository extends JpaRepository<Cartorio, Long> {
}
