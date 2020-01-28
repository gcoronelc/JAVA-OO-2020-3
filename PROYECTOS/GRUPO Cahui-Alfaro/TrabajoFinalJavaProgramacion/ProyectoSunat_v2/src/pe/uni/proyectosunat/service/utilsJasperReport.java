package pe.uni.proyectosunat.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import java.util.HashMap;
import groovy.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class utilsJasperReport {

    /**
     * genera PDF
     *
     * @param destFile Archivo del reporte
     * @param report_path PATH de los archivos .jasper y jrxml en el proyecto
     * @param name_jasperreport Nombre de los archivos jasperReport
     * @param reportParams parametros de entrada del reporte
     */
    public static void reportePDF(File destFile, final String report_path, String name_jasperreport, Map reportParams) {


        String projectRoot = System.getProperty("user.dir");
        //Ruta absoluta del proyecto
        String ABSOLUTE_PATH = projectRoot;
        //ruta de los archivos .jasper y .jrxml
        String path_jr = ABSOLUTE_PATH + report_path + "\\";
        String file_jasper = path_jr + name_jasperreport + ".jasper";
        String file_jrxml = path_jr + name_jasperreport + ".jrxml";
        try {
            //JasperCompileManager.compileReportToFile(file_jrxml, file_jasper);
            JasperPrint jrprint = JasperFillManager.fillReport(file_jasper, reportParams, ProyectoSunatService.ConectarSQLServer());
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jrprint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
            exporter.exportReport();
            
            ///JasperExportManager.exportReportToPdfFile(jrprint, destFile.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    

}
