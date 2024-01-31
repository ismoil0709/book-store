package uz.pdp.bookstoreproject.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.bookstoreproject.service.BookService;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "books")
@Builder
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "get_all_authors",query = "select a from Author a"),
                @NamedQuery(name = "get_by_name",query = "select a from Author a where a.full_name=:name")
        }
)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;
    @ManyToMany(mappedBy = "authors",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Book> books;
}
