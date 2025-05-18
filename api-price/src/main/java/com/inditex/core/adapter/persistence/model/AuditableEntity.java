package com.inditex.core.adapter.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Clase base que contiene campos de auditoría (creación y modificación).
 * <p>
 * Puede extenderse por entidades que requieran timestamps de auditoría.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    /**
     * Obtiene la fecha de creación.
     *
     * @return timestamp de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Obtiene la fecha de actualizacion.
     *
     * @return timestamp de actualizacion
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
