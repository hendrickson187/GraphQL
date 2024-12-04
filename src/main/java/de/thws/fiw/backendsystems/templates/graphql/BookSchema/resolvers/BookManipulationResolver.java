package de.thws.fiw.backendsystems.templates.graphql.BookSchema.resolvers;


import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Author;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Book;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.BookInput;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.storage.BookInMemoryStorage;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class BookManipulationResolver implements GraphQLMutationResolver {

    public Book create(final BookInput bookI){
        System.out.println("test");
        Book neu= new Book();
        Author neuA= new Author();
        neuA.setFirstName(bookI.getFirstNameAuthor());
        neuA.setLastName(bookI.getLastNameAuthor());
        neu.setAuthor(neuA);
        neu.setPublishedYear(bookI.getPublishedYear());
        neu.setTitel(bookI.getTitel());
        neu.setISBN(bookI.getiSBN());
        BookInMemoryStorage.getInstance().create(neu);
        return neu;
    }

    public Book update(final BookInput Input){
        System.out.println("update started");
        if(BookInMemoryStorage.getInstance().readById(Input.getId()).isPresent()){
            Book book=(Book)BookInMemoryStorage.getInstance().readById(Input.getId()).get();
            book.setTitel(Input.getTitel());
            book.setPublishedYear(Input.getPublishedYear());
            book.setISBN(Input.getiSBN());
            book.getAuthor().setFirstName(Input.getFirstNameAuthor());
            book.getAuthor().setLastName(Input.getLastNameAuthor());

            BookInMemoryStorage.getInstance().update(book);//unnötig
            return book;
        }
        else return null;

    }
    public Boolean delete(final int id){
        if(BookInMemoryStorage.getInstance().readById(id).isPresent()){
            BookInMemoryStorage.getInstance().delete(id);
            return true;
        }
        else return false;
    }

}
