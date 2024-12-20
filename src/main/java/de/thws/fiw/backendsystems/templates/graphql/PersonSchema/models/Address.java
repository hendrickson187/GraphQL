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

public class Address
{
	private String name;

	private String street;

	private String city;

	private String zipCode;

	public String getName( )
	{
		return name;
	}

	public void setName( final String name )
	{
		this.name = name;
	}

	public String getStreet( )
	{
		return street;
	}

	public void setStreet( String street )
	{
		this.street = street;
	}

	public String getCity( )
	{
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getZipCode( )
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}
}
