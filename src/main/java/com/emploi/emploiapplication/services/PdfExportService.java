package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Seance;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.List;

@Service
public class PdfExportService {


    Periode[] timeslots;
    List<DayOfWeek> days;

    public void AddGroupeEmploi(Document myPDFDoc,List<Seance> seanceList) throws  DocumentException {
// Set TimePeriods in Timetable
        timeslots = Periode.values();
        days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        //1) Let's create a Table object
        Table myTable = new Table(6); // 3 columns


        Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);
        Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);
        Font Calibri3 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 20, Font.BOLD);
        Font Calibri4 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 16, Font.BOLD,Color.red);

        float[] columnWidths = {20f, 50f, 50f, 20f, 50f, 50f}; // Adjust the values as needed
        myTable.setWidths(columnWidths);
        myTable.setPadding(5f);
        myTable.setWidth(100f);

        List<String> periodes = new ArrayList<>();
        periodes.add("08h30-10h15");
        periodes.add("10h30-12h15");
        periodes.add("12h-14h");
        periodes.add("14h30-16h15");
        periodes.add("16h30-18h15");

        String nS = seanceList.get(0).getGroupe().getSection().getPartie().getSemestre().getNumSemestre();
        Date anneuniv=new Date();
        anneuniv=seanceList.get(0).getGroupe().getSection().getPartie().getSemestre().getDateDebut();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(anneuniv);
        int year = calendar.get(Calendar.YEAR);
        String anneUniv;
        if (nS.equals("S1") || nS.equals("S3") || nS.equals("S5")){
            anneUniv = Integer.toString(year)+"-"+Integer.toString(year+1);
        }else {
            anneUniv = Integer.toString(year-1)+"-"+Integer.toString(year);
        }
        Paragraph universite = new Paragraph("UNIVERSITE SULTAN MOULAY SLIMANE " +
                "                                                                                      " +
                "                                                       Année Universitaire : "+anneUniv+"\n"+
                "FACULTE DES SCIENCES ET TECHNIQUES \n"+
                "           BENI MELLAL", Calibri1);
        universite.setAlignment(Element.ALIGN_LEFT);
        myPDFDoc.add(universite);

        Paragraph titleParagraph = new Paragraph("Parcours: "+seanceList.get(0).getGroupe().getSection().getPartie().getSemestre().getFormation().getLibelleFormation(), Calibri3);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(titleParagraph);
        Paragraph informations = new Paragraph("SEMESTRE : "+seanceList.get(0).getGroupe().getSection().getPartie().getSemestre().getNumSemestre()+"           "+seanceList.get(0).getGroupe().getSection().getPartie().getLibellePartie()+"           "+"Groupe: "+seanceList.get(0).getGroupe().getLibelleGroupe(), Calibri4);
        informations.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(informations);
        Paragraph datepartie = new Paragraph("A partir du "+seanceList.get(0).getGroupe().getSection().getPartie().getDateDebut(), Calibri3);
        datepartie.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(datepartie);

        //2) Create the header of the table
        ArrayList<String> headerTable = new ArrayList<>();
        headerTable.add("");
        headerTable.add("08h:30 - 10h:15");
        headerTable.add("10h:30 - 12h:15");
        headerTable.add("12h-14h");
        headerTable.add("14h:30 - 16h:15");
        headerTable.add("16h:30 - 18h:15");
        headerTable.forEach(e -> {
            Paragraph paragraph = new Paragraph(e, Calibri1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            Cell current;
            try {
                current = new Cell(paragraph);
            } catch (BadElementException ex) {
                throw new RuntimeException(ex);
            }
            current.setHeader(true);
            current.setHorizontalAlignment(Element.ALIGN_CENTER);
            current.setBackgroundColor(Color.white);
            myTable.addCell(current);
        });
        ArrayList<String> days = new ArrayList<>();
// Add your days to the list
        days.add("lundi");
        days.add("mardi");
        days.add("mercredi");
        days.add("jeudi");
        days.add("vendredi");
        days.add("samedi");
// Add more days as needed

// Iterate over the days list and add each day as a row to the table
        for (String day : days) {// Create a paragraph for the day
            Paragraph dayParagraph = new Paragraph(day, Calibri2);
            dayParagraph.setAlignment(Element.ALIGN_CENTER);

            // Create a cell with the day paragraph
            Cell dayCell;
            try {
                dayCell = new Cell(dayParagraph);
            } catch (BadElementException e) {
                throw new RuntimeException(e);
            }
            dayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add the cell to the table
            myTable.addCell(dayCell);
            // Add empty cells for the time slots
            for (String periode : periodes) {
                boolean sequenceFound = false;
                // Iterate over the sequences list to find a matching sequence
                for (Seance seance : seanceList) {
                    // Check if the sequence matches the current day and time slot
                    if (seance.getJourSeance().equals(day) && seance.getPeriode().equals(periode)) {
                        // Create a paragraph for the sequence information
                        if(seance.getTypeSeance().equals("Cours")){
                            Paragraph module = new Paragraph(seance.getTypeSeance()+" "+seance.getModule().getLibelleModule(),Calibri1);
                            Paragraph prof = new Paragraph(seance.getEnseignant().getNom()+" "+seance.getEnseignant().getPrenom(),Calibri1);
                            Paragraph salle = new Paragraph(seance.getSalle().getLibelleSalle(),Calibri1);
                            // Create a cell with the sequence paragraph
                            Cell sequenceCell = new Cell();
                            sequenceCell.add(module);
                            sequenceCell.add(prof);
                            sequenceCell.add(salle);
                            sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            sequenceCell.setBackgroundColor(Color.cyan);
                            myTable.addCell(sequenceCell);
                        }else if(seance.getTypeSeance().equals("Td")) {
                            Paragraph seanceCell = new Paragraph(seance.getTypeSeance()+" "+seance.getModule().getLibelleModule()+" "+seance.getSalle().getLibelleSalle(),Calibri1);
                            Paragraph prof = new Paragraph(seance.getEnseignant().getNom()+" "+seance.getEnseignant().getPrenom(),Calibri1);
                            // Create a cell with the sequence paragraph
                            Cell sequenceCell = new Cell();
                            sequenceCell.add(seanceCell);
                            sequenceCell.add(prof);
                            sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            sequenceCell.setBackgroundColor(Color.red);
                            myTable.addCell(sequenceCell);
                        }else{
                            Paragraph seanceCell = new Paragraph(seance.getTypeSeance()+" "+seance.getModule().getLibelleModule()+" "+seance.getSalle().getLibelleSalle(),Calibri1);
                            Paragraph prof = new Paragraph(seance.getEnseignant().getNom()+" "+seance.getEnseignant().getPrenom(),Calibri1);
                            // Create a cell with the sequence paragraph
                        Cell sequenceCell = new Cell();
                        sequenceCell.add(seanceCell);
                        sequenceCell.add(prof);
                        sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        sequenceCell.setBackgroundColor(Color.orange);
                        myTable.addCell(sequenceCell);
                        }
                        sequenceFound = true;
                        break; // Exit the loop once a sequence is found for this time slot
                    }
                }
                // If no sequence was found for this time slot, add an empty cell
                if (!sequenceFound) {
                    if (periode.equals("12h-14h")) {
                        Cell emptyCell1 = new Cell("");
                        emptyCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell1.setBackgroundColor(Color.gray);
                        myTable.addCell(emptyCell1);
                    }else {
                        Cell emptyCell = new Cell("");
                        emptyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell.setBackgroundColor(Color.white);
                        myTable.addCell(emptyCell);
                    }
                }

            }
        }


        // 4)Finally add the table to the document
        myPDFDoc.add(myTable);
        myPDFDoc.newPage();

    }
    public void DepartementsPDF(HttpServletResponse response , List<Seance> seanceList) throws IOException, DocumentException {

        Document myPDFDoc = new Document(PageSize.A4.rotate(),
                40f,   // left
                40f,   // right
                10f,  // top
                20f); // down
        final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());
        myPDFDoc.open();  // Open the Document

        AddGroupeEmploi(myPDFDoc ,seanceList);
        myPDFDoc.close();
        pdfWriter.close();
}
    public void AddPageEnseignant(Document myPDFDoc,List<Seance> seanceList) throws DocumentException {
        // Set TimePeriods in Timetable
        timeslots = Periode.values();
        days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        //1) Let's create a Table object
        Table myTable = new Table(6); // 3 columns


        Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);
        Font Calibri5 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 14, Font.BOLD);
        Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);

        float[] columnWidths = {30f, 50f, 50f, 20f, 50f, 50f}; // Adjust the values as needed
        myTable.setWidths(columnWidths);
        myTable.setPadding(2f);
        myTable.setWidth(100f);

        List<String> periodes = new ArrayList<>();
        periodes.add("08h30-10h15");
        periodes.add("10h30-12h15");
        periodes.add("12h-14h");
        periodes.add("14h30-16h15");
        periodes.add("16h30-18h15");

        String nS = seanceList.get(0).getSection().getPartie().getSemestre().getNumSemestre();
        Date anneuniv=new Date();
        anneuniv=seanceList.get(0).getSection().getPartie().getSemestre().getDateDebut();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(anneuniv);
        int year = calendar.get(Calendar.YEAR);
        String anneUniv;
        if (nS.equals("S1") || nS.equals("S3") || nS.equals("S5")){
            anneUniv = Integer.toString(year)+"-"+Integer.toString(year+1);
        }else {
            anneUniv = Integer.toString(year-1)+"-"+Integer.toString(year);
        }
        Paragraph universite = new Paragraph("UNIVERSITE SULTAN MOULAY SLIMANE " +
                "                                                                                      " +
                "                                                       Année Universitaire : "+anneUniv+"\n"+
                "FACULTE DES SCIENCES ET TECHNIQUES \n"+
                "           BENI MELLAL", Calibri1);
        universite.setAlignment(Element.ALIGN_LEFT);
        myPDFDoc.add(universite);
        universite.setAlignment(Element.ALIGN_LEFT);
        myPDFDoc.add(universite);

        Paragraph ens = new Paragraph("Enseignant : "+seanceList.get(0).getEnseignant().getNom()+" "+seanceList.get(0).getEnseignant().getPrenom(), Calibri5);
        ens.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(ens);

        //2) Create the header of the table
        ArrayList<String> headerTable = new ArrayList<>();
        headerTable.add("");
        headerTable.add("08h:30 - 10h:15");
        headerTable.add("10h:30 - 12h:15");
        headerTable.add("12h-14h");
        headerTable.add("14h:30 - 16h:15");
        headerTable.add("16h:30 - 18h:15");
        headerTable.forEach(e -> {
            Paragraph paragraph = new Paragraph(e, Calibri1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            Cell current;
            try {
                current = new Cell(paragraph);
            } catch (BadElementException ex) {
                throw new RuntimeException(ex);
            }
            current.setHeader(true);
            current.setHorizontalAlignment(Element.ALIGN_CENTER);
            current.setBackgroundColor(Color.lightGray);
            myTable.addCell(current);
        });

        ArrayList<String> days = new ArrayList<>();
// Add your days to the list
        days.add("lundi");
        days.add("mardi");
        days.add("mercredi");
        days.add("jeudi");
        days.add("vendredi");
        days.add("samedi");
// Add more days as needed

// Iterate over the days list and add each day as a row to the table
        for (String day : days) {// Create a paragraph for the day
            Paragraph dayParagraph = new Paragraph(day, Calibri2);
            dayParagraph.setAlignment(Element.ALIGN_CENTER);

            // Create a cell with the day paragraph
            Cell dayCell;
            try {
                dayCell = new Cell(dayParagraph);
            } catch (BadElementException e) {
                throw new RuntimeException(e);
            }
            dayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add the cell to the table
            myTable.addCell(dayCell);
            // Add empty cells for the time slots
            for (String periode : periodes) {
                boolean sequenceFound = false;
                // Iterate over the sequences list to find a matching sequence
                for (Seance seance : seanceList) {
                    // Check if the sequence matches the current day and time slot
                    if (seance.getJourSeance().equals(day) && seance.getPeriode().equals(periode)) {
                        if (seance.getTypeSeance().equals("Cours")) {
                            // Create a paragraph for the sequence information
                            Paragraph formation = new Paragraph("Formation: " + seance.getSection().getPartie().getSemestre().getFormation().getLibelleFormation(),Calibri1);
                            Paragraph module = new Paragraph(seance.getTypeSeance() + " " + seance.getModule().getLibelleModule(), Calibri1);
                            Paragraph salle = new Paragraph("Salle: " + seance.getSalle().getLibelleSalle(), Calibri1);
                            // Create a cell with the sequence paragraph
                            Cell sequenceCell = new Cell();
                            sequenceCell.add(formation);
                            sequenceCell.add(module);
                            sequenceCell.add(salle);
                            sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            // Add the sequence cell to the table
                            myTable.addCell(sequenceCell);
                        }else{
                            Paragraph formation = new Paragraph("Formation: " + seance.getGroupe().getSection().getPartie().getSemestre().getFormation().getLibelleFormation(),Calibri1 );
                            Paragraph module = new Paragraph(seance.getTypeSeance() + " " + seance.getModule().getLibelleModule(), Calibri1);
                            Paragraph groupe = new Paragraph("groupe: "+seance.getGroupe().getLibelleGroupe(),Calibri1);
                            Paragraph salle = new Paragraph("Salle: " + seance.getSalle().getLibelleSalle(), Calibri1);
                            // Create a cell with the sequence paragraph
                            Cell sequenceCell = new Cell();
                            sequenceCell.add(formation);
                            sequenceCell.add(module);
                            sequenceCell.add(groupe);
                            sequenceCell.add(salle);
                            sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            // Add the sequence cell to the table
                            myTable.addCell(sequenceCell);
                        }
                        sequenceFound = true;
                        break;
                    }
                }
                // If no seance was found for this time slot, add an empty cell
                if (!sequenceFound) {
                    if (periode.equals("12h-14h")) {
                        Cell emptyCell1 = new Cell("");
                        emptyCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell1.setBackgroundColor(Color.gray);
                        myTable.addCell(emptyCell1);
                    }else {
                        Cell emptyCell = new Cell("");
                        emptyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell.setBackgroundColor(Color.lightGray);
                        myTable.addCell(emptyCell);
                    }
                }
            }
        }


        // 4)Finally add the table to the document
        myPDFDoc.add(myTable);
        myPDFDoc.newPage();
    }
    public void EnseignantPDF(HttpServletResponse response , List<Seance> seanceList) throws IOException, DocumentException {

        Document myPDFDoc = new Document(PageSize.A4.rotate(),
                40f,   // left
                40f,   // right
                30f,  // top
                70f); // down
        final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());
        myPDFDoc.open();  // Open the Document

        AddPageEnseignant(myPDFDoc ,seanceList);
        myPDFDoc.close();
        pdfWriter.close();
    }

    public void emploiCours(Document myPDFDoc,List<Seance> seanceList) throws DocumentException {
        // Set TimePeriods in Timetable
        timeslots = Periode.values();
        days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);

        //1) Let's create a Table object
        Table myTable = new Table(6); // 3 columns


        Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);
        Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10, Font.BOLD);
        Font Calibri3 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 20, Font.BOLD);
        Font Calibri4 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 16, Font.BOLD,Color.red);

        float[] columnWidths = {20f, 50f, 50f, 20f, 50f, 50f}; // Adjust the values as needed
        myTable.setWidths(columnWidths);
        myTable.setPadding(5f);
        myTable.setWidth(100f);

        List<String> periodes = new ArrayList<>();
        periodes.add("08h30-10h15");
        periodes.add("10h30-12h15");
        periodes.add("12h-14h");
        periodes.add("14h30-16h15");
        periodes.add("16h30-18h15");

        List<String> jours=new ArrayList<>();
        jours.add("lundi");
        jours.add("mardi");
        jours.add("mercredi");
        jours.add("jeudi");
        jours.add("vendredi");
        jours.add("samedi");


        String nS = seanceList.get(0).getSection().getPartie().getSemestre().getNumSemestre();
        Date anneuniv=new Date();
        anneuniv=seanceList.get(0).getSection().getPartie().getSemestre().getDateDebut();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(anneuniv);
        int year = calendar.get(Calendar.YEAR);
        String anneUniv;
        if (nS.equals("S1") || nS.equals("S3") || nS.equals("S5")){
            anneUniv = Integer.toString(year)+"-"+Integer.toString(year+1);
        }else {
            anneUniv = Integer.toString(year-1)+"-"+Integer.toString(year);
        }
        Paragraph universite = new Paragraph("UNIVERSITE SULTAN MOULAY SLIMANE " +
                "                                                                                      " +
                "                                                       Année Universitaire : "+anneUniv+"\n"+
                "FACULTE DES SCIENCES ET TECHNIQUES \n"+
                "           BENI MELLAL", Calibri1);
        universite.setAlignment(Element.ALIGN_LEFT);
        myPDFDoc.add(universite);

      /*  Paragraph yearParagraph = new Paragraph("Année Universitaire : 2023-2024", Calibri1);
        yearParagraph.setAlignment(Element.ALIGN_RIGHT);
        myPDFDoc.add(yearParagraph);*/

        Paragraph titleParagraph = new Paragraph("Parcours: "+seanceList.get(0).getSection().getPartie().getSemestre().getFormation().getLibelleFormation(), Calibri3);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(titleParagraph);
        Paragraph informations = new Paragraph("SEMESTRE : "+seanceList.get(0).getSection().getPartie().getSemestre().getNumSemestre()+"                   "+seanceList.get(0).getSection().getPartie().getLibellePartie(), Calibri4);
        informations.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(informations);
        Paragraph datepartie = new Paragraph("A partir du "+seanceList.get(0).getSection().getPartie().getDateDebut(), Calibri1);
        datepartie.setAlignment(Element.ALIGN_CENTER);
        myPDFDoc.add(datepartie);

        //2) Create the header of the table
        ArrayList<String> headerTable = new ArrayList<>();
        headerTable.add("");
        headerTable.add("08h:30 - 10h:15");
        headerTable.add("10h:30 - 12h:15");
        headerTable.add("12h-14h");
        headerTable.add("14h:30 - 16h:15");
        headerTable.add("16h:30 - 18h:15");
        headerTable.forEach(e -> {
            Paragraph paragraph = new Paragraph(e, Calibri1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            Cell current = null;
            try {
                current = new Cell(paragraph);
            } catch (BadElementException ex) {
                throw new RuntimeException(ex);
            }
            current.setHeader(true);
            current.setHorizontalAlignment(Element.ALIGN_CENTER);
            current.setBackgroundColor(Color.white);
            myTable.addCell(current);
        });

        // 3) Then create a list of rows and add them to the table
        LinkedHashMap<Integer, List<List<Paragraph>>> listRows = new LinkedHashMap<>();
        int rowNumber = 1;


        ArrayList<String> days = new ArrayList<>();
// Add your days to the list
        days.add("lundi");
        days.add("mardi");
        days.add("mercredi");
        days.add("jeudi");
        days.add("vendredi");
        days.add("samedi");
// Add more days as needed

// Iterate over the days list and add each day as a row to the table
        for (String day : days) {// Create a paragraph for the day
            Paragraph dayParagraph = new Paragraph(day, Calibri2);
            dayParagraph.setAlignment(Element.ALIGN_CENTER);

            // Create a cell with the day paragraph
            Cell dayCell = null;
            try {
                dayCell = new Cell(dayParagraph);
            } catch (BadElementException e) {
                throw new RuntimeException(e);
            }
            dayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add the cell to the table
            myTable.addCell(dayCell);
            // Add empty cells for the time slots
            for (String periode : periodes) {
                boolean sequenceFound = false;
                // Iterate over the sequences list to find a matching sequence
                for (Seance seance : seanceList) {
                    // Check if the sequence matches the current day and time slot
                    if (seance.getJourSeance().equals(day) && seance.getPeriode().equals(periode)) {
                        // Create a paragraph for the sequence information
                        Paragraph module = new Paragraph(seance.getTypeSeance() + " " + seance.getModule().getLibelleModule(), Calibri1);
                        Paragraph prof = new Paragraph(seance.getEnseignant().getNom() + " " + seance.getEnseignant().getPrenom(), Calibri1);
                        Paragraph salle = new Paragraph(seance.getSalle().getLibelleSalle(), Calibri1);
                        // Create a cell with the sequence paragraph
                        Cell sequenceCell = new Cell();
                        sequenceCell.add(module);
                        sequenceCell.add(prof);
                        sequenceCell.add(salle);
                        sequenceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        sequenceCell.setBackgroundColor(Color.cyan);
                        myTable.addCell(sequenceCell);
                        sequenceFound = true;
                        break; // Exit the loop once a sequence is found for this time slot
                    }
                }
                // If no sequence was found for this time slot, add an empty cell
                if (!sequenceFound) {
                    if (periode.equals("12h-14h")) {
                        Cell emptyCell1 = new Cell("");
                        emptyCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell1.setBackgroundColor(Color.gray);
                        myTable.addCell(emptyCell1);
                    }else {
                        Cell emptyCell = new Cell("");
                        emptyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        emptyCell.setBackgroundColor(Color.lightGray);
                        myTable.addCell(emptyCell);
                    }
                }

            }
        }
        // 4)Finally add the table to the document
        myPDFDoc.add(myTable);
        myPDFDoc.newPage();
    }

    public void EmploideCoursPDF(HttpServletResponse response , List<Seance> seanceList) throws IOException, DocumentException {
        Document myPDFDoc = new Document(PageSize.A4.rotate(),
                40f,   // left
                40f,   // right
                10f,  // top
                20f); // down
        final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());
        myPDFDoc.open();  // Open the Document

        emploiCours(myPDFDoc,seanceList);
        myPDFDoc.close();
        pdfWriter.close();
    }

}