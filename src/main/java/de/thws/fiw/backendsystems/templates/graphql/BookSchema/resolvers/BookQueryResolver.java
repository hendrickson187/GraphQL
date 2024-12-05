package de.thws.fiw.backendsystems.templates.graphql.BookSchema.resolvers;

import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Author;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.AuthorInput;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.Book;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.storage.BookInMemoryStorage;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.ArrayList;
import java.util.List;

public class BookQueryResolver implements GraphQLQueryResolver {


    public Book bookById(Long Id){

        try{
            return (Book) BookInMemoryStorage.getInstance()
                    .readById(Id)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + Id));}catch (Exception e){} catch (
                Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("null");
        return null;
    }
    public List<Book> books(){
        return BookInMemoryStorage.getInstance().readByPredicate(p -> true);
    }

    public Book bookByTitel(String titel){
        for(Book book : books()){
            if(book.getTitel().equals(titel)){
                return book;
            }
        }
        return null;
    }
    //booksByAuthor(author: Author!): [Book!]!
    public List<Book> booksByAuthor(AuthorInput author){
        /*List<Book> books = new ArrayList<>();
        for(Book book : books()){
            if(book.getAuthor().getFirstName().equals(author.getFirstName()) && book.getAuthor().getLastName().equals(author.getLastName())){
                books.add(book);
            }
        }
        return books;*/
        System.out.println("test");
        return null;
    }
}