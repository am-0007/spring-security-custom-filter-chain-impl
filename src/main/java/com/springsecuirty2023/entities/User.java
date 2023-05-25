package com.springsecuirty2023.entities;

import com.springsecuirty2023.entities.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
