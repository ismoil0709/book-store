import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.repo.impl.BookRepoImpl;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;
public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-orm")) {
            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                Book book = entityManager.find(Book.class, 1L);
                System.out.println(book.toString());
            }
        }
        }
}
