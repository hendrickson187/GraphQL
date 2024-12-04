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

import de.thws.fiw.backendsystems.templates.graphql.models.Address;
import de.thws.fiw.backendsystems.templates.graphql.models.Gender;
import de.thws.fiw.backendsystems.templates.graphql.models.Person;
import de.thws.fiw.backendsystems.templates.graphql.models.PersonInput;
import de.thws.fiw.backendsystems.templates.graphql.storage.PersonInMemoryStorage;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.util.Optional;

public class PersonMutationResolver implements GraphQLMutationResolver {
	public Person create(final String firstName, final String lastName) {
		final Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);

		final Address address = new Address();
		address.setName("Work");
		address.setStreet("Secret Street");
		address.setCity("London");

		person.setAddress(address);

		PersonInMemoryStorage.getInstance().create(person);

		return person;
	}

	public Person update(final PersonInput personInput) {

		PersonInMemoryStorage.getInstance().readById(personInput.getId()).ifPresent(person -> {
			person.setFirstName(personInput.getFirstName());
			person.setLastName(personInput.getLastName());
		});
		if(PersonInMemoryStorage.getInstance().readById(personInput.getId()).isPresent()){
			return PersonInMemoryStorage.getInstance().readById(personInput.getId()).get();
		}
		return null;
	}

	public Boolean delete(final long id) {
		if (PersonInMemoryStorage.getInstance().readById(id).isPresent()) {
			PersonInMemoryStorage.getInstance().delete(id);
			return true;
		}
		return false;
	}

//	public Person updateGender(final int id, final String gender) {
//		PersonInMemoryStorage.getInstance().readById(id).ifPresent(person -> {
//			Gender g = new Gender();
//			g.setGender(gender);
//			person.setGender(g);
//		});
//		if(PersonInMemoryStorage.getInstance().readById(id).isPresent()){
//			return PersonInMemoryStorage.getInstance().readById(id).get();
//		}
//		return null;
//	}    updateGender( id: Int!, gender: String! ): Person
}