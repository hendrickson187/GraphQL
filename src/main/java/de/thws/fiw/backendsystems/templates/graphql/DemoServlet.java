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

package de.thws.fiw.backendsystems.templates.graphql;

import de.thws.fiw.backendsystems.templates.graphql.resolvers.PersonMutationResolver;
import de.thws.fiw.backendsystems.templates.graphql.resolvers.PersonQueryResolver;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

@WebServlet( name = "ServletExample", urlPatterns = { "/graphql/*" }, loadOnStartup = 1 )
public class DemoServlet extends GraphQLHttpServlet
{
	public DemoServlet( )
	{
		System.out.println("GraphQL Servlet starts" );
	}

	@Override
	protected GraphQLConfiguration getConfiguration( )
	{
		return GraphQLConfiguration.with( createSchema( ) ).build( );
	}

	private GraphQLSchema createSchema( )
	{
		try
		{
			final String schemaString = IOUtils.toString( this.getClass( )
															  .getResourceAsStream( "/schema.graphqls" ) );

			return SchemaParser.newParser( )
							   .schemaString( schemaString )
							   .resolvers( new PersonQueryResolver( ), new PersonMutationResolver( ) )
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
