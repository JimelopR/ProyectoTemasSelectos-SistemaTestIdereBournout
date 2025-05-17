/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.repositories;

import com.mac.ProyectoTemasSelectos.models.PreguntaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jlopez
 */
public interface PreguntaRepository extends JpaRepository<PreguntaModel, Long>{

    public List<PreguntaModel> findBySubescalaId(Long id);
    
}
