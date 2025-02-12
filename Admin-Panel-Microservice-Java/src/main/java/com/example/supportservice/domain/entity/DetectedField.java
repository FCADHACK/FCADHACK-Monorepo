package com.example.supportservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class DetectedField {
    private String field;
    private String regex;
    private FilterMode mode; // Новое поле для режима

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DetectedField(String field, String pattern, FilterMode mode) {
        this.field = field;
        this.regex = pattern;
        this.mode = mode; // Инициализируем режим
    }
}
