package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubProductRepository extends JpaRepository<SubProduct, Long> { }
