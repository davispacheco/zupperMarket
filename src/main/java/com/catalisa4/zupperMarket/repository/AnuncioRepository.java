package com.catalisa4.zupperMarket.Repository;

import com.catalisa4.zupperMarket.model.AnunciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<AnunciosModel, Long> {
}
