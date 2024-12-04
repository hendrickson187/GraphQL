package de.thws.fiw.backendsystems.templates.graphql.BookSchema.storage;

import com.github.javafaker.Faker;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Author;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Book;

import java.util.Set;

public class BookInMemoryStorage extends AbstractInMemoryStorage {

    private static BookInMemoryStorage INSTANCE;

    public static final BookInMemoryStorage getInstance(){
        if(INSTANCE==null){
            INSTANCE= new BookInMemoryStorage();
            return INSTANCE;
        }
        else return INSTANCE;
    }
    public void populateDatabase(){
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            Book book = new Book();
            book.setTitel(faker.book().title());
            Author a= new Author();
            a.setFirstName(faker.name().firstName());
            a.setLastName(faker.name().lastName());
            book.setAuthor(a);
            book.setISBN(faker.code().isbn10());
            book.setPublishedYear(faker.number().numberBetween(1900,2021));
            if(!ISBNExists(book.getISBN()))
            {
                getInstance().create(book);
            }
        }
    }
    public void printAll(){
        Set<Long> keys = getInstance().storage.keySet();
        for (Long key : keys) {
            System.out.println(getInstance().storage.get(key));
        }
    }
    public boolean ISBNExists(String ISBN){
        Set<Long> keys = getInstance().storage.keySet();
        for (Long key : keys) {
            if( ((Book) getInstance().storage.get(key)).getISBN().equals(ISBN))
                return true;
        }
        return false;
    }
}
