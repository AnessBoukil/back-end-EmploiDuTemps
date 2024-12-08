package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Module;

import java.util.List;

public interface ModuleService {
    Module addModule(Module module);

    List<Module> getAllModules();

    Module getModuleById(Long id);

    Module updateModule(Long id, Module module);

    String deleteModule(Long id);

    List<Module> chercherModule(String keyword);
}
