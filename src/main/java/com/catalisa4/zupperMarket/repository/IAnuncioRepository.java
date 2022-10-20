package com.catalisa4.zupperMarket.repository;

import com.catalisa4.zupperMarket.model.AnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnuncioRepository extends JpaRepository<AnuncioModel, Long> {
}
