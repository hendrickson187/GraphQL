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

import com.github.javafaker.Faker;
import de.thws.fiw.backendsystems.templates.graphql.models.Address;
import de.thws.fiw.backendsystems.templates.graphql.models.Person;

public class PersonInMemoryStorage extends AbstractInMemoryStorage<Person>
{
	private static PersonInMemoryStorage INSTANCE;

	public static final PersonInMemoryStorage getInstance( )
	{
		if ( INSTANCE == null )
		{
			INSTANCE = new PersonInMemoryStorage( );
		}

		return INSTANCE;
	}

	public void populateDatabase()
	{
		final Faker faker = new Faker( );
		for( int i=0; i<100; i++ )
		{
			final Person person = new Person();
			person.setFirstName( faker.address( ).firstName( ) );
			person.setLastName( faker.address( ).lastName( ) );

			final Address address = new Address();
			person.setAddress( address );

			address.setName("Home");
			address.setCity( faker.address( ).cityName( ) );
			address.setStreet( faker.address().streetAddress() );
			address.setZipCode( faker.address().zipCode( ) );

			create( person );
		}
	}
}
