/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.repositories;

import com.mac.ProyectoTemasSelectos.models.ResultadoTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimena
 */
@Repository
public interface ResultadoTestRepository extends JpaRepository<ResultadoTestModel, Long>{

    public ResultadoTestModel findByTestAsignadoId(Long idTestAsignado);
    
}
