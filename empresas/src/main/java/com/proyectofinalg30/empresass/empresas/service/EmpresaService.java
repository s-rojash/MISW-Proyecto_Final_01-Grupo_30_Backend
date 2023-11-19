package com.proyectofinalg30.empresass.empresas.service;

import com.proyectofinalg30.empresass.empresas.model.Empresa;
import com.proyectofinalg30.empresass.empresas.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa searchEmail(String email) {
        return empresaRepository.findOneByEmail(email);
    }

    public Empresa searchToken(String token) {
        return empresaRepository.findOneByToken(token);
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listAll() {
        return empresaRepository.findAll();
    }

    public Empresa list(Long id) { return empresaRepository.findOneById(id); }

    public void delete(Empresa empresa) {
        empresaRepository.delete(empresa);
    }
}
