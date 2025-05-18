/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.repositories;

import com.mac.ProyectoTemasSelectos.models.TestAsignadoModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimena
 */
@Repository
public interface TestAsignadoRepository extends JpaRepository<TestAsignadoModel, Long>{

    public List<TestAsignadoModel> findByEvaluadoIdAndCompletadoFalse(Long evaluadoId);
    List<TestAsignadoModel> findByEvaluadorId(Long evaluadorId);
    public List<TestAsignadoModel> findByEvaluadoIdAndFechaRespuestaIsNotNull(Long evaluadoId);

   
}
