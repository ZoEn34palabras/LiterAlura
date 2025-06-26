package com.literalura.service;

import com.literalura.gutendex.dto.AutorDTO;
import com.literalura.gutendex.dto.LibroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Libro guardarLibroDesdeDTO(LibroDTO libroDTO) {

        if (libroDTO == null) {
            System.out.println("El libro recibido es nulo");
            return null;
        }

        String titulo = libroDTO.getTitle();
        String idioma = libroDTO.getLanguages() != null && !libroDTO.getLanguages().isEmpty()
                ? libroDTO.getLanguages().get(0)
                : "desconocido";
        int descargas = libroDTO.getDownload_count();

        List<Libro> existentes = libroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!existentes.isEmpty()) {
            System.out.println("El libro ya existe en la base de datos");
            return existentes.get(0);
        }

        AutorDTO autorDTO = libroDTO.getAutores() !=null && !libroDTO.getAutores().isEmpty()
                ? libroDTO.getAutores().get(0)
                :null;

        Autor autor = new Autor();

        if (autorDTO != null) {
            autor.setNombre(autorDTO.getName());
            autor.setNacimiento(autorDTO.getBirth_year());
            autor.setFallecimiento(autorDTO.getDeath_year());
        }

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIdioma(idioma);
        libro.setDescargas(descargas);
        libro.setAutor(autor);

        Libro guardado = libroRepository.save(libro);
        System.out.println("El libro se ha guardado correctamente");
        return guardado;
    }
}
