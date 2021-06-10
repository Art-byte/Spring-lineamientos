package com.example.Springlineamientos.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = 26535254362345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

}
