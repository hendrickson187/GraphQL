package de.thws.fiw.backendsystems.templates.graphql.BookSchema.storage;

import de.thws.fiw.backendsystems.templates.graphql.BookSchema.models.AbstractModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AbstractInMemoryStorage<T extends AbstractModel> {
    protected Map<Long, T> storage;

    private final AtomicLong nextId;

    protected AbstractInMemoryStorage( )
    {
        this.storage = new HashMap<>( );
        this.nextId = new AtomicLong( 1l );
    }

    public void create( final T model )
    {
        model.setId( nextId( ) );
        this.storage.put( model.getId( ), model );
    }

    public Optional<T> readById(final long id )
    {
        if ( this.storage.containsKey( id ) )
        {
            return Optional.of( this.storage.get( id ) );
        }
        else
        {
            return Optional.empty( );
        }
    }

    public List<T> readByPredicate(final Predicate<T> predicate )
    {
        return new LinkedList<>( filterBy( predicate ) );
    }

    private Collection<T> filterBy( final Predicate<T> predicate )
    {
        return this.storage.values( )
                .stream( )
                .filter( predicate )
                .collect( Collectors.toList( ) );
    }

    public void update( final T model )
    {
        this.storage.put( model.getId( ), model );
    }

    public void delete( final long id )
    {
        this.storage.remove( id );
    }

    private final long nextId( )
    {
        return this.nextId.getAndIncrement( );
    }

}
