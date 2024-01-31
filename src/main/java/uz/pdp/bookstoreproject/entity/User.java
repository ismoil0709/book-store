package uz.pdp.bookstoreproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(name = "get_all_users",query = "select u from User u"),
                @NamedQuery(name = "get_by_username",query = "select u from User u where username=:username"),
                @NamedQuery(name = "get_by_email",query = "select u from User u where email=:email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Pattern(regexp = "")
    @Column(nullable = false)
    private String password;
    private boolean isAdmin;
}
