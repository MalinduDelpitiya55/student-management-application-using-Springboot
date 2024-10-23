package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String stdName;
    private String stdAge;
    private String stdContactNo;
    private String guardianName;
    private String guardianAddress;
    private String guardianContactNo;
    private boolean isActive;
}
