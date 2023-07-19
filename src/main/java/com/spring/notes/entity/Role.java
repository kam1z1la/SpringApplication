package com.spring.notes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString(exclude = {"person"})
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 30, message = "role too big!")
    private String role;

    @ManyToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<Person> person;

    @Override
    public String getAuthority() {
        return role;
    }
}
