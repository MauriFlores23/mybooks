package com.mauriflores.mybooks.service;

import com.mauriflores.mybooks.model.Categoria;
import com.mauriflores.mybooks.model.dao.ICategoriaDao;
import com.mauriflores.mybooks.response.CategoriaResponseRest;
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
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoria() {
        log.info("Iniciando metodo buscarCategorias");
        CategoriaResponseRest responseRest = new CategoriaResponseRest();
        try{
            List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
            responseRest.getCategoriaResponse().setCategorias(categorias);
            responseRest.setMetadata("Respuesta OK","00","Satisfactorio");
        }catch (Exception e){
            responseRest.setMetadata("Respuesta NOK","-1","Error");
            log.error("Error al buscar categoria:",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return new ResponseEntity<CategoriaResponseRest>(responseRest, HttpStatus.OK) ;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        log.info("Iniciando metodo buscar por ID");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try{
            Optional<Categoria> categoria = categoriaDao.findById(id);
            if(categoria.isPresent()){
                list.add(categoria.get());
                response.setMetadata("Respuesta OK","00","Satisfactorio");
                response.getCategoriaResponse().setCategorias(list);
            } else {
                log.error("Error en consultar categoria");
                response.setMetadata("Respuesta NOK","-1","Error");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            log.error("Error en consultar categoria");
            response.setMetadata("Respuesta NOK","-1","Error");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        log.info("Iniciando metodo Crear Categoria");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try{
            Categoria categoriaGuardada = categoriaDao.save(categoria);
            if(categoriaGuardada != null){
                list.add(categoria);
                response.setMetadata("Respuesta OK","00","Satisfactorio");
                response.getCategoriaResponse().setCategorias(list);
            } else {
                log.error("No se pudo guardar la categoria");
                response.setMetadata("Respuesta NOK","-1","Error guardar");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error en crear categoria",e.getMessage());
            response.setMetadata("Respuesta NOK","-1","Error");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        log.info("Inicio metodo actualizar Categoria");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try{
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
            if(categoriaBuscada.isPresent()){
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
                categoriaBuscada.get().setNombre(categoria.getNombre());
                Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
                if(categoriaActualizar!=null){
                    list.add(categoriaActualizar);
                    response.setMetadata("Respuesta OK","00","Satisfactorio");
                    response.getCategoriaResponse().setCategorias(list);
                } else {
                    log.error("No se pudo actualizar la categoria");
                    response.setMetadata("Respuesta NOK","-1","Error guardar");
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }

            } else {
                log.error("No se pudo actualizar la categoria");
                response.setMetadata("Respuesta NOK","-1","Error guardar");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error en actualizar categoria",e.getMessage());
            response.setMetadata("Respuesta NOK","-1","Error");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("Inicio metodo actualizar Categoria");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try{
            categoriaDao.deleteById(id);
            response.setMetadata("Respuesta OK","00","Satisfactorio");
        }catch (Exception e){
            log.error("Error en eliminar categoria",e.getMessage());
            response.setMetadata("Respuesta NOK","-1","Error");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
