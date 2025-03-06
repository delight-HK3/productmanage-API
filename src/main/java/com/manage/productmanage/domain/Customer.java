package com.manage.productmanage.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * customer(고객) Entity 
 */
@Entity
@Getter
@Table(name="customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "고객 ID (고유 키)")
    private Long id;                    

    @Column(name = "name", length = 255, nullable = false)
    @Comment(value = "고객 이름")
    private String name;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정 시간")
    private LocalDateTime updatedAt;
}
