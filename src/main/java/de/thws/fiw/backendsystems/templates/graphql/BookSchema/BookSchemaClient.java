package de.thws.fiw.backendsystems.templates.graphql.BookSchema;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookSchemaClient {

    private static final String GRAPHQL_ENDPOINT = "http://localhost:8080/booksgraphql";

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
            // Beispiel: Create ausführen
            String mutation = "mutation { create(Input: { lastNameAuthor: \"Finn\", firstNameAuthor: \"Krappitz\", titel: \"Book One\", iSBN: \"blabla\" }) { titel iSBN } }";
            String mutationResponse = sendRequest(mutation);
            System.out.println("Create Response: " + mutationResponse);

            // Beispiel: Query ausführen
            String query = "{ books { titel id } }";
            String queryResponse = sendRequest(query);
            System.out.println("Query Response: " + queryResponse);

            // Beispiel: Update ausführen
            String updateString = "mutation { update(Input: { lastNameAuthor: \"Krappitz\", firstNameAuthor: \"Finn\", titel: \"Oasch\", iSBN: \"toptoptop\", id:1}) { titel iSBN } }";
            String updateResponse = sendRequest(updateString);
            System.out.println("Update Response: " + updateResponse);

            //Beispiel: Query mit Variabeln
            String queryVariables = "query bookById($id: Int!) { bookById(id: $id) { id iSBN } }";
            String variables = "{\"id\": 1}";
            String response = sendRequestWithVariables(queryVariables, variables);
            System.out.println("Variables Response: " + response);

            //bookByTitel
            String byTitel = "query bookByTitel{ bookByTitel(titel:\"Oasch\") { id titel } }";
            String byTitelResponse = sendRequest(byTitel);
            System.out.println("By Titel Response: " + byTitelResponse);


            // Beispiel: Delete ausführen
            String deleteString = "mutation deleteId($id: Int!) { delete(id: $id) }";
            String deleteResponse = sendRequestWithVariables(deleteString, variables);
            System.out.println("Update Response: " + deleteResponse);

            // Beispiel: Query ausführen
            String query2 = "{ books { titel id } }";
            String queryResponse2 = sendRequest(query2);
            System.out.println("Query Response: " + queryResponse2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

