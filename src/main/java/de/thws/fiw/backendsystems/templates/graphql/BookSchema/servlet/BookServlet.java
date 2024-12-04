package de.thws.fiw.backendsystems.templates.graphql.BookSchema.servlet;

import de.thws.fiw.backendsystems.templates.graphql.BookSchema.resolvers.BookManipulationResolver;
import de.thws.fiw.backendsystems.templates.graphql.BookSchema.resolvers.BookQueryResolver;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

@WebServlet(name= "BookServlet", urlPatterns={"/lib/*"}, loadOnStartup = 1)
public class BookServlet extends GraphQLHttpServlet {
    public BookServlet(){
        System.out.println("GraphQL \"BookServlet\" started");
    }

    @Override
    protected GraphQLConfiguration getConfiguration(){return GraphQLConfiguration.with(createSchema()).build();}

    private GraphQLSchema createSchema()
    {
        try
        {
            final String schemaString = IOUtils.toString( this.getClass( )
                    .getResourceAsStream( "/schema.graphqls" ) );

            return SchemaParser.newParser( )
                    .schemaString( schemaString )
                    .resolvers( new BookQueryResolver( ), new BookManipulationResolver( ) )
                    .build( )
                    .makeExecutableSchema( );
        }
        catch ( final IOException e )
        {
            e.printStackTrace( );
            return null;
        }
    }

}
