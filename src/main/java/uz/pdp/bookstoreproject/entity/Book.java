package uz.pdp.bookstoreproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "get_all_books",query = "select b from Book b"),
                @NamedQuery(name = "get_by_title",query = "select b from Book b where title=:title"),
                @NamedQuery(name = "get_by_price",query = "select b from Book b where price=:price"),
                @NamedQuery(name = "get_by_rating",query = "select b from Book b where rating=:rating")
        }
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String cover_path;
    @Column(nullable = false)
    private String path;
    private Double price;
    private float rating = 0F;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Author> authors;
}
