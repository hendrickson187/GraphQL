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

package de.thws.fiw.backendsystems.templates.graphql.resolvers;

import de.thws.fiw.backendsystems.templates.graphql.models.Gender;
import de.thws.fiw.backendsystems.templates.graphql.models.Person;
import de.thws.fiw.backendsystems.templates.graphql.storage.PersonInMemoryStorage;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public class PersonQueryResolver implements GraphQLQueryResolver
{
	public Person person( final long id )
	{
		return PersonInMemoryStorage.getInstance( ).readById( id ).orElseGet( null );
	}

	public List<Person> persons( )
	{
		return PersonInMemoryStorage.getInstance( ).readByPredicate( p -> true );
	}

	public List<Person> personsByName( final String lastName )
	{
		return PersonInMemoryStorage.getInstance( ).readByPredicate( p -> p.getLastName( ).equals( lastName ) );
	}

//	public List<Person> personsByGender(final Gender gender ){
//		return PersonInMemoryStorage.getInstance().readByPredicate(p -> p.getGender().equals(gender));
//	} personsByGender(gender: Gender!): [Person!]!
}
