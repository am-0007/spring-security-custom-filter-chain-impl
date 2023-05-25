package com.springsecuirty2023.entities;

import com.springsecuirty2023.entities.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(String userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
