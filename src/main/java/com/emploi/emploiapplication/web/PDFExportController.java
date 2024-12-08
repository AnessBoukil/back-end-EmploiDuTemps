package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Seance;
import com.emploi.emploiapplication.repository.GroupeRepository;
import com.emploi.emploiapplication.services.PdfExportService;
import com.emploi.emploiapplication.services.SeanceService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class PDFExportController {


    private PdfExportService pdfExportService;
    private SeanceService seanceService;
    private GroupeRepository groupeRepository;

    @GetMapping("/getEmploi")
    public void generateEmploiCours(HttpServletResponse response , @RequestParam Long idSection ) throws IOException, DocumentException {
        List<Seance> seances = seanceService.getSeanceBySection(idSection);
        this.pdfExportService.EmploideCoursPDF(response,seances);
    }
    @GetMapping("/getEmploiProf")
    public void generateEmploiProf(HttpServletResponse response , @RequestParam Long idProf )throws IOException, DocumentException{
        List<Seance> seances = seanceService.getSeanceByProf(idProf);
        this.pdfExportService.EnseignantPDF(response,seances);
    }
    @GetMapping("/getEmploiDeGroupe")
    public void generateEmploiDeGroupe(HttpServletResponse response , @RequestParam Long idGroupe ) throws IOException, DocumentException {
        List<Seance> seances = seanceService.getSeanceByGroupe(idGroupe);
        Long idSection = groupeRepository.findSectionByGroupe(idGroupe);
        seances.addAll(seanceService.getSeanceBySection(idSection));
        this.pdfExportService.DepartementsPDF(response ,seances);
    }

}
