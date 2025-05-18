/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.repositories;

import com.mac.ProyectoTemasSelectos.models.PreguntaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimena
 */
@Repository
public interface PreguntaRepository extends JpaRepository<PreguntaModel, Long>{

    public List<PreguntaModel> findBySubescalaId(Long id);
    
    @Query("SELECT p FROM PreguntaModel p LEFT JOIN FETCH p.subescala WHERE p.id = :preguntaId")
    PreguntaModel findPreguntaWithSubescala(@Param("preguntaId") Long preguntaId);
    
}
