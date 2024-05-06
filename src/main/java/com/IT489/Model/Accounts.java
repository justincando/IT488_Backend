package com.IT489.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Optional;


/**
 * This class stores the User account model
 */

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer accountType;
    private Integer userId;
    private float account;

}