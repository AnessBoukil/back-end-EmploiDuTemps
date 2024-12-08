package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService{
    private ModuleRepository moduleRepository;
    @Override
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce module n'existe pas."));
    }

    @Override
    public Module updateModule(Long id, Module module) {
        module.setIdModule(id);
        return moduleRepository.save(module);
    }

    @Override
    public String deleteModule(Long id) {
        try{
            moduleRepository.deleteById(id);
            return "l'operation est bien effectuee";
        }catch(Exception e){
            return e.getMessage() ;
        }
    }

    @Override
    public List<Module> chercherModule(String keyword) {
        return moduleRepository.chercherModuleByKeyWord(keyword);
    }
}
