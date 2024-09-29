package com.mauriflores.mybooks.service;

import com.mauriflores.mybooks.model.Libro;
import com.mauriflores.mybooks.model.dao.ILibroDao;
import com.mauriflores.mybooks.response.LibroResponseRest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LibroServiceImpl implements ILibroService {

    @Autowired
    private ILibroDao iLibroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {

        log.info("Ingresando metodo Buscar Libros");
        LibroResponseRest responseRest = new LibroResponseRest();
        try {
            List<Libro> libros = (List<Libro>) iLibroDao.findAll();
            responseRest.getLibroResponse().setLibros(libros);
            responseRest.setMetadata("OK", "0000", "Satisfactorio");
        }catch (Exception e){
            log.error("Error al buscar libros:",e.getMessage());
            responseRest.setMetadata("NOK", "-1", "Error");
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
        log.info("Ingresando a buscar libro por ID");
        LibroResponseRest responseRest = new LibroResponseRest();
        List<Libro> libroList = new ArrayList<>();
        try{
            Optional<Libro> libro = iLibroDao.findById(id);
            if(libro.isPresent()){
                libroList.add(libro.get());
                responseRest.getLibroResponse().setLibros(libroList);
                responseRest.setMetadata("OK", "0000", "Satisfactorio");
            } else {
                responseRest.setMetadata("NOK", "-1", "Error");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al buscar libro por ID:",e.getMessage());
            responseRest.setMetadata("NOK", "-1", "Error");
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> crear(Libro libro) {
        log.info("Ingresar metodo Crear Libro");
        LibroResponseRest responseRest = new LibroResponseRest();
        List<Libro> libroList = new ArrayList<>();
        try{
            Libro libroCreado = iLibroDao.save(libro);
            if(libroCreado != null){
            libroList.add(libroCreado);
            responseRest.getLibroResponse().setLibros(libroList);
            responseRest.setMetadata("OK", "0000", "Satisfactorio");
            } else {
                responseRest.setMetadata("NOK", "-1", "Error");
                return new ResponseEntity<>(responseRest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            log.error("Error al crear Libro:",e.getMessage());
            responseRest.setMetadata("NOK", "-1", "Error");
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {

        log.info("Actualizar Libro>>>");
        LibroResponseRest responseRest = new LibroResponseRest();
        List<Libro> libroList = new ArrayList<>();
        try{
            Optional<Libro> libroEncontrado = iLibroDao.findById(id);
            if(libroEncontrado.isPresent()){
                libroEncontrado.get().setNombre(libro.getNombre());
                libroEncontrado.get().setDescripcion(libro.getDescripcion());
                libroEncontrado.get().setCategoria(libro.getCategoria());
                Libro libroActualizado = iLibroDao.save(libroEncontrado.get());
                if(libroActualizado != null){
                    libroList.add(libroActualizado);
                    responseRest.getLibroResponse().setLibros(libroList);
                    responseRest.setMetadata("OK", "0000", "Satisfactorio");
                } else {
                    log.error("Error al actualizar libro!");
                    responseRest.setMetadata("NOK", "-1", "Error");
                    return new ResponseEntity<>(responseRest, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Libro no encontrado!");
                responseRest.setMetadata("NOK", "-1", "Error");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            log.error("Error al actualizar Libro:",e.getMessage());
            responseRest.setMetadata("NOK", "-1", "Error");
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> eliminar(Long id) {
        LibroResponseRest responseRest = new LibroResponseRest();
        try{
            iLibroDao.deleteById(id);
            responseRest.setMetadata("OK", "0000", "Satisfactorio");
        } catch (Exception e){
            log.error("Error al eliminar Libro:",e.getMessage());
            responseRest.setMetadata("NOK", "-1", "Error");
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }
}
