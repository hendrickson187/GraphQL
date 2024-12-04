package PersonTest;

import de.thws.fiw.backendsystems.templates.graphql.PersonSchemaClient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphQLPersonSchemaClientTest {

    // Basis-URL des GraphQL-Endpunkts
    private static final String GRAPHQL_ENDPOINT = "http://localhost:8080/graphql";

    // Test für die Query: persons
    @Test
    public void testGetPersons() throws Exception {
        String query = "{ persons { id firstName lastName } }";
        String response = PersonSchemaClient.sendRequest(query);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("persons"), "Response should contain 'persons'");
    }

    // Test für die Query: person(id)
    @Test
    public void testGetPersonById() throws Exception {
        String query = "{ person(id: 1) { id firstName lastName } }";
        String response = PersonSchemaClient.sendRequest(query);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("firstName"), "Response should contain 'firstName'");
        assertTrue(response.contains("lastName"), "Response should contain 'lastName'");
    }

    // Test für die Query: personsByName
    @Test
    public void testGetPersonsByName() throws Exception {
        String query = "{ personsByName(lastName: \"Doe\") { id firstName lastName } }";
        String response = PersonSchemaClient.sendRequest(query);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("personsByName"), "Response should contain 'personsByName'");
    }

    // Test für die Mutation: create
    @Test
    public void testCreatePerson() throws Exception {
        String mutation = "mutation { create(firstName: \"John\", lastName: \"Doe\") { id firstName lastName } }";
        String response = PersonSchemaClient.sendRequest(mutation);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("id"), "Response should contain 'id'");
        assertTrue(response.contains("John"), "Response should contain 'John'");
        assertTrue(response.contains("Doe"), "Response should contain 'Doe'");
    }

    // Test für die Mutation: update
    @Test
    public void testUpdatePerson() throws Exception {
        String mutation = "mutation { update(person: { id: 1, firstName: \"Jane\", lastName: \"Doe\" }) { id firstName lastName } }";
        String response = PersonSchemaClient.sendRequest(mutation);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("id"), "Response should contain 'id'");
        assertTrue(response.contains("Jane"), "Response should contain 'Jane'");
        assertTrue(response.contains("Doe"), "Response should contain 'Doe'");
    }

    // Test für die Mutation: delete
    @Test
    public void testDeletePerson() throws Exception {
        String mutation = "mutation { delete(id: 1) }";
        String response = PersonSchemaClient.sendRequest(mutation);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("true") || response.contains("false"), "Response should contain a boolean value");
    }
}
