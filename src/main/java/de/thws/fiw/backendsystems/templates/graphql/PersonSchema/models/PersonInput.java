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

package de.thws.fiw.backendsystems.templates.graphql.PersonSchema.models;

public class PersonInput
{
	private long id;

	private String firstName;

	private String lastName;

	public String getFirstName( )
	{
		return firstName;
	}

	public void setFirstName( final String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}

	public void setLastName( final String lastName )
	{
		this.lastName = lastName;
	}

	public long getId( )
	{
		return id;
	}

	public void setId( final long id )
	{
		this.id = id;
	}
}
