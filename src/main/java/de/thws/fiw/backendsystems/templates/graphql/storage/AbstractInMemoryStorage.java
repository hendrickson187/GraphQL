/*
 * MIT License
 *
 * Copyright (c) 2024 Peter Braun <mail@peter-braun.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.thws.fiw.backendsystems.templates.graphql.storage;

import de.thws.fiw.backendsystems.templates.graphql.models.AbstractModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractInMemoryStorage<T extends AbstractModel>
{
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

	public Optional<T> readById( final long id )
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

	public List<T> readByPredicate( final Predicate<T> predicate )
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
