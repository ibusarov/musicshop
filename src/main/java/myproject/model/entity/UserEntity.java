package myproject.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity{

    private String email;
    private String password;
    private Set<Role> roles;


    public UserEntity() {
    }

    @Column(name="email",unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> role) {
        this.roles = role;
    }

}
