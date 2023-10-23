package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.Categoria;
import com.proyecto.bancopreguntas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria searchNombre(String nombre) {
        return categoriaRepository.findOneByNombre(nombre);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    public Categoria list(Long id) { return categoriaRepository.findOneById(id); }

    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
