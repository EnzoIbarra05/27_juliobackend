package com.api._dejulio.service;

import com.api._dejulio.Entityes.Reporte;
import com.api._dejulio.Entityes.resultadoMensual;
import com.api._dejulio.repository.ReporteRepository;
import com.api._dejulio.repository.ResultadoMensualRepository;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResultadoMensualService {

    @Autowired
    private ResultadoMensualRepository resultadoMensualRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Value("${reportes.mensuales.path}")
    private String reportesPath;
    // Obtener todos los resultados mensuales
    public List<resultadoMensual> getAllResultados() {
        return resultadoMensualRepository.findAll();
    }

    // Obtener resultado mensual por id
    public Optional<resultadoMensual> getResultadoById(int id) {
        return resultadoMensualRepository.findById(id);
    }

    // Crear o actualizar resultado mensual
    public resultadoMensual saveResultado(resultadoMensual resultado) {
        resultado.setEstado("Alta");
        return resultadoMensualRepository.save(resultado);
    }

    // Eliminar resultado mensual
    public void deleteResultado(int id) {
        resultadoMensualRepository.deleteById(id);
    }
    public resultadoMensual updateReporte(resultadoMensual resultado) {
    return resultadoMensualRepository.save(resultado);
}

    //------------------------------------------------------------------------------------------------------------------
    public byte[] crearCaratula(resultadoMensual resultado) throws Exception {
        ByteArrayOutputStream caratulaStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(caratulaStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Ruta del logo
        String logoPath = "src/main/resources/logo.png";
        if (Files.exists(Paths.get(logoPath))) {
            byte[] logoBytes = Files.readAllBytes(Paths.get(logoPath));
            ImageData imageData = ImageDataFactory.create(logoBytes);
            Image logo = new Image(imageData);
            logo.setAutoScale(true);
            logo.setFixedPosition(50, 530); // ajustar posición
            document.add(logo);
        }

        String mesReporte = encuentraMes(resultado.getMes());

        // Título
        document.add(new Paragraph("Reporte Mensual " + resultado.getSeccion().getNombre() + " " + mesReporte + " " + resultado.getAnio())
                .setFontSize(20)
                .setBold()
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER)
                .setMarginTop(150)
                .setMarginBottom(80));

        // Información del reporte
        document.add(new Paragraph("-----------------------------------------------------------------"));
        document.add(new Paragraph("INFORMACION DE REPORTE"));
        document.add(new Paragraph("-----------------------------------------------------------------"));
        document.add(new Paragraph("- Mes de generación de reporte : " + mesReporte));
        document.add(new Paragraph("- Año: " + resultado.getAnio()));
        document.add(new Paragraph("- Porcentaje de cumplimiento: " + resultado.getPorcentaje() + "%"));
        document.add(new Paragraph("- Sección de reporte: " + resultado.getSeccion().getNombre()).setBold());
        document.add(new Paragraph("-----------------------------------------------------------------"));

        document.close();
        return caratulaStream.toByteArray();
    }

    // =======================
    // Generar reporte mensual
    // =======================
    public resultadoMensual generarReporteMensual(resultadoMensual resultadomensual) throws Exception {
        int mes = resultadomensual.getMes();
        int anio = resultadomensual.getAnio();
        int seccionId = resultadomensual.getSeccion().getId();

        // Filtrar reportes por mes, año y sección
        List<Reporte> reportes = reporteRepository.findByMesAndAnioAndSeccion(mes, anio, seccionId);
        if (reportes.isEmpty()) {
            throw new RuntimeException("No hay reportes para el mes " + mes + " del año " + anio + " en la sección " + seccionId);
        }

        // Generar carátula
        byte[] portada = crearCaratula(resultadomensual);

        // Unir PDFs
        PDFMergerUtility merger = new PDFMergerUtility();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Primero la carátula
        merger.addSource(new ByteArrayInputStream(portada));

        // Luego los reportes existentes en disco
        for (Reporte reporte : reportes) {
            if (reporte.getRutaArchivo() != null) {
                merger.addSource(new File(reporte.getRutaArchivo()));
            }
        }

        merger.setDestinationStream(outputStream);
        merger.mergeDocuments(null);

        byte[] pdfUnificado = outputStream.toByteArray();

        // Guardar PDF en carpeta
        File carpeta = new File(reportesPath);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        String nombreArchivo = "ReporteMensual_" + mes + "_" + anio + "_Seccion" + seccionId + ".pdf";
        Path rutaArchivo = Paths.get(reportesPath, nombreArchivo);
        Files.write(rutaArchivo, pdfUnificado);

        // Guardamos en DB solo la ruta
        resultadomensual.setRutaArchivo(rutaArchivo.toString());
        resultadomensual.setEstado("ALTA");
        return resultadoMensualRepository.save(resultadomensual);
    }

public Optional<resultadoMensual> buscarPorId(int id) {
    return resultadoMensualRepository.findById(id);
}

public String encuentraMes(int mes) {
    String mesDelAnio = "";

    switch (mes) {
        case 1: mesDelAnio = "Enero"; break;
        case 2: mesDelAnio = "Febrero"; break;
        case 3: mesDelAnio = "Marzo"; break;
        case 4: mesDelAnio = "Abril"; break;
        case 5: mesDelAnio = "Mayo"; break;
        case 6: mesDelAnio = "Junio"; break;
        case 7: mesDelAnio = "Julio"; break;
        case 8: mesDelAnio = "Agosto"; break;
        case 9: mesDelAnio = "Septiembre"; break;
        case 10: mesDelAnio = "Octubre"; break;
        case 11: mesDelAnio = "Noviembre"; break;
        case 12: mesDelAnio = "Diciembre"; break;
        default: mesDelAnio = "Mes inválido"; break;
    }

    return mesDelAnio;
}

}
