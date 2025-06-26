package com.literalura.gutendex;

import com.literalura.gutendex.dto.LibroDTO;
import com.literalura.gutendex.dto.ResultadoBusquedaDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexClient {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public LibroDTO buscarLibroPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+") ;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ResultadoBusquedaDTO resultado = mapper.readValue(response.body(), ResultadoBusquedaDTO.class);

            if (resultado.getResults() ==null || resultado.getResults().isEmpty()){
                System.out.println("No se encontró ningún libro el título " + titulo);
                return null;
            }

            return resultado.getResults().get(0);//obtener solo el primer resultado

        } catch (IOException | InterruptedException e) {
                System.out.println("Error al conectarse a la API " + e.getMessage());
                return null;
        }
    }
}
