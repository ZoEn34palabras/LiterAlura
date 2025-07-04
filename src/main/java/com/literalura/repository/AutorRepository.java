package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(int año1, int año2 );

}
