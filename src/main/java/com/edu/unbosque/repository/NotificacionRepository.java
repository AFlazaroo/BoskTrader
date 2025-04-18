package com.edu.unbosque.repository;


import com.edu.unbosque.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

   // List<Notificacion> findByUsuarioId(Integer usuarioId); // Para obtener notificaciones por usuario
}