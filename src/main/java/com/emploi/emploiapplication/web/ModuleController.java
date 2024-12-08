package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.services.ModuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class ModuleController {
    private ModuleService moduleService;

    @PostMapping("/module/ajouter")
    public Module createModule(@RequestBody Module module){
        return moduleService.addModule(module);
    }

    @GetMapping("/modules")
    public List<Module> moduleList (){
        return moduleService.getAllModules();
    }

    @GetMapping("/module/{id}")
    public Module getModuleById(@RequestParam Long id){
        return moduleService.getModuleById(id);
    }

    @GetMapping("/module/chercher")
    public List<Module> chercherModule(@RequestParam String keyword){
        return moduleService.chercherModule(keyword);
    }

    @PutMapping("/module/modifier")
    public Module updateModule(@RequestParam Long id,
                               @RequestBody Module module){
        return moduleService.updateModule(id,module);
    }

    @DeleteMapping("/module/{id}")
    public String deleteModule(@PathVariable Long id){
        return moduleService.deleteModule(id);
    }

}
