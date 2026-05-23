package microservice.sigesu.anexorespuesta.util;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.util.Arrays;

public class PdfGenerator {

    public static byte[] generar(Map<String, Object> data) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4, 40, 40, 60, 40);
            PdfWriter.getInstance(document, baos);
            document.open();

            // ===============================
            // FUENTES
            // ===============================
            Font tituloFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font headerFont = new Font(Font.HELVETICA, 11, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 10);

            // ===============================
            // TÍTULO
            // ===============================
            Paragraph titulo = new Paragraph("FICHA DE EVALUACIÓN", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // ===============================
            // DATOS GENERALES
            // ===============================
            PdfPTable tablaDatos = new PdfPTable(2);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.setSpacingAfter(20);

            tablaDatos.addCell(crearCeldaHeader("Unidad:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("nombreUnidad")), normalFont));

            tablaDatos.addCell(crearCeldaHeader("Servicio:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("nombreServicio")), normalFont));

            tablaDatos.addCell(crearCeldaHeader("Centro:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("nombreCentro")), normalFont));

            String tipoCentro = (String) data.get("tipoCentro");

            if (tipoCentro != null && !tipoCentro.trim().isEmpty()) {

                tablaDatos.addCell(crearCeldaHeader("Perfil:", headerFont));
                tablaDatos.addCell(crearCeldaNormal(tipoCentro, normalFont));
            }

            tablaDatos.addCell(crearCeldaHeader("Ubicación:", headerFont));
            tablaDatos.addCell(
                    crearCeldaNormal(
                            data.get("departamento") + " / " +
                                    data.get("provincia") + " / " +
                                    data.get("distrito"),
                            normalFont));
            tablaDatos.addCell(crearCeldaHeader("Responsable Supervisión:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("respSupervision")), normalFont));

            tablaDatos.addCell(crearCeldaHeader("Director/Coordinador:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("respDirector")), normalFont));

            tablaDatos.addCell(crearCeldaHeader("Supervisado (OS):", headerFont));
            // tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("nombreSupervisado")),
            // normalFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("idSupervisado")), normalFont));

            tablaDatos.addCell(crearCeldaHeader("Fecha Registro:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(String.valueOf(data.get("fechaRegistro")), normalFont));

            document.add(tablaDatos);

            // ===============================
            // TABLA DE PREGUNTAS
            // ===============================
            PdfPTable tablaPreguntas = new PdfPTable(2);
            tablaPreguntas.setWidthPercentage(100);
            tablaPreguntas.setWidths(new float[] { 3, 2 });

            // Cabeceras
            tablaPreguntas.addCell(crearCeldaEncabezadoTabla("PREGUNTA"));
            tablaPreguntas.addCell(crearCeldaEncabezadoTabla("RESPUESTA"));

            List<Map<String, Object>> respuestas = (List<Map<String, Object>>) data.get("respuestas");

            if (respuestas != null) {

                for (Map<String, Object> r : respuestas) {

                    String pregunta = String.valueOf(r.get("pregunta"));
                    String respuesta = String.valueOf(r.get("respuesta"));

                    tablaPreguntas.addCell(crearCeldaNormal(pregunta, normalFont));
                    tablaPreguntas.addCell(crearCeldaNormal(respuesta, normalFont));
                }
            }

            document.add(tablaPreguntas);

            // ===============================
            // TOTALES
            // ===============================
            String codigoAnexo = String.valueOf(data.get("codigoAnexo2"));

            if (Arrays.asList(
                    "FS_CAR01",
                    "FS_CAR02",
                    "FS_CED01",
                    "FS_CED02",
                    "FS_SEC01",
                    "FS_SEC02",
                    "FS_ACE01",
                    "FS_ACE02",
                    "FS_FAM01",
                    "FS_FAM02",
                    "FS_AEA01",
                    "FS_AEA02",
                    "FS_AEA03",
                    "FS_INA01").contains(codigoAnexo)) {

                int conforme = (int) data.getOrDefault("TOTAL_CONFORME", 0);
                int noConforme = (int) data.getOrDefault("TOTAL_NO_CONFORME", 0);
                int observacion = (int) data.getOrDefault("TOTAL_OBSERVACION", 0);
                int noAplica = (int) data.getOrDefault("TOTAL_NO_APLICA", 0);

                // Espacio
                document.add(new Paragraph(" "));

                // Título
                Paragraph tituloTotales = new Paragraph("TOTALES", headerFont);
                tituloTotales.setSpacingAfter(10);
                document.add(tituloTotales);

                // Tabla de totales
                PdfPTable tablaTotales = new PdfPTable(2);
                tablaTotales.setWidthPercentage(50);
                tablaTotales.setSpacingBefore(10);

                tablaTotales.addCell(crearCeldaHeader("CONFORME:", headerFont));
                tablaTotales.addCell(crearCeldaNormal(String.valueOf(conforme), normalFont));

                tablaTotales.addCell(crearCeldaHeader("NO CONFORME:", headerFont));
                tablaTotales.addCell(crearCeldaNormal(String.valueOf(noConforme), normalFont));

                tablaTotales.addCell(crearCeldaHeader("OBSERVACIÓN:", headerFont));
                tablaTotales.addCell(crearCeldaNormal(String.valueOf(observacion), normalFont));

                tablaTotales.addCell(crearCeldaHeader("NO APLICA:", headerFont));
                tablaTotales.addCell(crearCeldaNormal(String.valueOf(noAplica), normalFont));

                document.add(tablaTotales);
            }
            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }
    }

    // ===============================
    // MÉTODOS AUXILIARES
    // ===============================

    private static PdfPCell crearCeldaHeader(String texto, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(5);
        return cell;
    }

    private static PdfPCell crearCeldaNormal(String texto, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setPadding(5);
        return cell;
    }

    private static PdfPCell crearCeldaEncabezadoTabla(String texto) {
        Font font = new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(6);
        return cell;
    }
}