package de.thws.fiw.backendsystems.templates.graphql;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonSchemaClient {

    private static final String GRAPHQL_ENDPOINT = "http://localhost:8080/persongraphql";

    // Methode, um GraphQL-Anfragen zu senden
    public static String sendRequest(String query) throws Exception {
        // Verbindung aufbauen
        URL url = new URL(GRAPHQL_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Anfrage senden
        String requestBody = "{\"query\": \"" + query.replace("\"", "\\\"") + "\"}";
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
            outputStream.flush();
        }

        // Antwort lesen
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }

    public static String sendRequestWithVariables(String query, String variablesJson) throws Exception {
        URL url = new URL(GRAPHQL_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Request-Body erstellen
        String requestBody = "{ \"query\": \"" + query.replace("\"", "\\\"") + "\", \"variables\": " + variablesJson + " }";
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
            outputStream.flush();
        }

        // Antwort lesen
        return readResponse(connection);
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }


    public static void main(String[] args) {
        try {
            // Beispiel: Mutation ausführen
            String mutation = "mutation { create(firstName: \"Krappitz\", lastName: \"Finn\") { id firstName lastName } }";
            String mutationResponse = sendRequest(mutation);
            System.out.println("Mutation Response: " + mutationResponse);

            // Beispiel: Query ausführen
            String query = "{ persons { id firstName lastName } }";
            String queryResponse = sendRequest(query);
            System.out.println("Query Response: " + queryResponse);

            // Beispiel: Mutation ausführen
            String update = "mutation { update(person: {id:1, firstName:\"Finn\", lastName:\"Krappitz\"}){ id firstName lastName } }";
            String updateResponse = sendRequest(update);
            System.out.println("Mutation Response: " + updateResponse);

            // Beispiel: Query ausführen
            String query2 = "{ persons { id firstName lastName } }";
            String queryResponse2 = sendRequest(query2);
            System.out.println("Query Response: " + queryResponse2);

            //Beispiel: Query mit Variabeln
            String queryVariables = "query GetPerson($id: Int!) { person(id: $id) { id firstName lastName } }";
            String variables = "{\"id\": 1}";
            String response = sendRequestWithVariables(queryVariables, variables);
            System.out.println("Variables Response: " + response);

            // Beispiel: Mutation ausführen
            String delete = "mutation { delete(id:1) }";
            String deleteResponse = sendRequest(delete);
            System.out.println("Mutation Response: " + deleteResponse);

            // Beispiel: Query ausführen
            String query3 = "{ persons { id firstName lastName } }";
            String queryResponse3 = sendRequest(query3);
            System.out.println("Query Response: " + queryResponse3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
