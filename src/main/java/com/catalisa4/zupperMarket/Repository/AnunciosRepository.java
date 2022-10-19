package com.catalisa4.zupperMarket.Repository;

import com.catalisa4.zupperMarket.Model.AnunciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnunciosRepository extends JpaRepository<AnunciosModel, Long> {
}
