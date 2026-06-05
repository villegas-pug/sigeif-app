package microservice.sigesu.anexorespuesta.util;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
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
            String nombreAnexo = data.get("nombreAnexo") != null ? String.valueOf(data.get("nombreAnexo")).trim()
                    : null;
            String codigoAnexo2 = data.get("codigoAnexo2") != null ? String.valueOf(data.get("codigoAnexo2")).trim()
                    : null;

            String tituloTexto = "FICHA DE EVALUACIÓN";
            if (nombreAnexo != null && !nombreAnexo.isEmpty() && !"null".equals(nombreAnexo)
                    && codigoAnexo2 != null && !codigoAnexo2.isEmpty() && !"null".equals(codigoAnexo2)) {
                tituloTexto = codigoAnexo2 + " - " + nombreAnexo;
            }

            Paragraph titulo = new Paragraph(tituloTexto, tituloFont);
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

            String nombreCentro = data.get("nombreCentro") != null ? String.valueOf(data.get("nombreCentro")).trim() : null;
            String centro = data.get("centro") != null ? String.valueOf(data.get("centro")).trim() : null;
            String centroMostrar = centro != null && !centro.isEmpty() && !"null".equals(centro) ? centro
                    : (nombreCentro != null && !nombreCentro.isEmpty() && !"null".equals(nombreCentro) ? nombreCentro : "");

            tablaDatos.addCell(crearCeldaHeader("Centro:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(centroMostrar, normalFont));

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

            String respDirector = data.get("respDirector") != null ? String.valueOf(data.get("respDirector")).trim() : null;
            if (respDirector != null && !respDirector.isEmpty() && !"null".equals(respDirector)) {
                tablaDatos.addCell(crearCeldaHeader("Director/Coordinador:", headerFont));
                tablaDatos.addCell(crearCeldaNormal(respDirector, normalFont));
            }

            String supervisados = extraerNombresSupervisados(data.get("idSupervisado"));
            if (!supervisados.isEmpty()) {
                tablaDatos.addCell(crearCeldaHeader("Supervisado (OS):", headerFont));
                tablaDatos.addCell(crearCeldaNormal(supervisados, normalFont));
            }

            tablaDatos.addCell(crearCeldaHeader("Fecha Registro:", headerFont));
            tablaDatos.addCell(crearCeldaNormal(formatearFecha(data.get("fechaRegistro")), normalFont));

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

                    String tipoControl = r.get("tipoControl") != null ? String.valueOf(r.get("tipoControl")).trim()
                            : "";
                    String pregunta = String.valueOf(r.get("pregunta"));

                    if ("cabecera".equalsIgnoreCase(tipoControl) || "label".equalsIgnoreCase(tipoControl)) {
                        PdfPCell celdaCabecera = crearCeldaNormal(pregunta, headerFont);
                        celdaCabecera.setColspan(2);
                        celdaCabecera.setBackgroundColor(new Color(220, 220, 220));
                        tablaPreguntas.addCell(celdaCabecera);
                        continue;
                    }

                    String respuesta = String.valueOf(r.get("respuesta"));

                    tablaPreguntas.addCell(crearCeldaNormal(pregunta, normalFont));
                    tablaPreguntas.addCell(crearCeldaNormal(respuesta, normalFont));

                    String respuesta2 = r.get("respuesta2") != null ? String.valueOf(r.get("respuesta2")).trim() : null;
                    if (respuesta2 != null && !respuesta2.isEmpty() && !"null".equals(respuesta2)) {
                        String pregunta2 = r.get("pregunta2") != null ? String.valueOf(r.get("pregunta2")).trim()
                                : null;
                        String etiqueta = pregunta2 != null && !pregunta2.isEmpty() && !"null".equals(pregunta2)
                                ? pregunta2.substring(0, 1).toUpperCase() + pregunta2.substring(1).toLowerCase()
                                : "Comentario";
                        tablaPreguntas.addCell(crearCeldaComentario(etiqueta + ": " + respuesta2));
                    }
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

            List<String[]> personalValida = extraerPersonalValida(data.get("idsPersonalValida"));

            if (!personalValida.isEmpty()) {
                // Espacio
                document.add(new Paragraph(" "));

                // Título de validación
                Paragraph tituloValidadoPor = new Paragraph("VALIDADO POR", headerFont);
                tituloValidadoPor.setSpacingAfter(10);
                document.add(tituloValidadoPor);

                // Tabla de validación
                PdfPTable tablaValidadoPor = new PdfPTable(3);
                tablaValidadoPor.setWidthPercentage(100);
                tablaValidadoPor.setWidths(new float[] { 3, 2, 2 });

                tablaValidadoPor.addCell(crearCeldaEncabezadoTabla("NOMBRES Y APELLIDOS"));
                tablaValidadoPor.addCell(crearCeldaEncabezadoTabla("ROL"));
                tablaValidadoPor.addCell(crearCeldaEncabezadoTabla("FECHA Y HORA"));

                for (String[] fila : personalValida) {
                    tablaValidadoPor.addCell(crearCeldaNormal(fila[0], normalFont));
                    tablaValidadoPor.addCell(crearCeldaNormalCentrada(fila[1], normalFont));
                    tablaValidadoPor.addCell(crearCeldaNormalCentrada(fila[2], normalFont));
                }

                document.add(tablaValidadoPor);
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

    private static PdfPCell crearCeldaNormalCentrada(String texto, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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

    private static PdfPCell crearCeldaComentario(String texto) {
        Font comentarioFont = new Font(Font.HELVETICA, 9, Font.ITALIC);
        PdfPCell cell = new PdfPCell(new Phrase(texto, comentarioFont));
        cell.setColspan(2);
        cell.setBackgroundColor(new Color(230, 230, 230));
        cell.setPadding(5);
        return cell;
    }

    private static String extraerNombresSupervisados(Object idSupervisadoObj) {
        if (idSupervisadoObj == null) {
            return "";
        }

        String idSupervisado = String.valueOf(idSupervisadoObj).trim();
        if (idSupervisado.isEmpty()) {
            return "";
        }

        String[] registros = idSupervisado.split("\\|");
        StringBuilder nombres = new StringBuilder();

        for (String registro : registros) {
            String item = registro.trim();
            if (item.isEmpty()) {
                continue;
            }

            String[] partes = item.split(",", 2);
            String nombre = partes.length > 1 ? partes[1].trim() : "";

            if (!nombre.isEmpty()) {
                if (nombres.length() > 0) {
                    nombres.append(", ");
                }
                nombres.append(nombre);
            }
        }

        return nombres.toString();
    }

    private static List<String[]> extraerPersonalValida(Object idsPersonalValidaObj) {
        if (idsPersonalValidaObj == null) {
            return List.of();
        }

        String idsPersonalValida = String.valueOf(idsPersonalValidaObj).trim();
        if (idsPersonalValida.isEmpty()) {
            return List.of();
        }

        List<String[]> filas = new java.util.ArrayList<>();
        String[] registros = idsPersonalValida.split("\\|");

        for (String registro : registros) {
            String item = registro.trim();
            if (item.isEmpty()) {
                continue;
            }

            String[] partes = item.split(",", 4);
            String nombres = partes.length > 1 ? partes[1].trim() : "";
            String rol = partes.length > 2 ? partes[2].trim() : "";
            String fechaHora = partes.length > 3 ? partes[3].trim().replace("T", " ") : "";

            filas.add(new String[] { nombres, rol, fechaHora });
        }

        filas.sort(Comparator
                .comparingInt((String[] fila) -> prioridadRol(fila[1]))
                .thenComparing((fila1, fila2) -> {
                    int prioridad1 = prioridadRol(fila1[1]);
                    int prioridad2 = prioridadRol(fila2[1]);

                    if (prioridad1 != 3 || prioridad2 != 3) {
                        return 0;
                    }

                    LocalDateTime fecha1 = parseFechaHora(fila1[2]);
                    LocalDateTime fecha2 = parseFechaHora(fila2[2]);

                    if (fecha1 == null && fecha2 == null) {
                        return fila1[2].compareToIgnoreCase(fila2[2]);
                    }
                    if (fecha1 == null) {
                        return 1;
                    }
                    if (fecha2 == null) {
                        return -1;
                    }
                    return fecha1.compareTo(fecha2);
                }));

        return filas;
    }

    private static int prioridadRol(String rol) {
        String valorRol = rol == null ? "" : rol.trim().toUpperCase();

        if ("DIRECTOR".equals(valorRol)) {
            return 1;
        }

        if ("RESPONSABLE SUPERVISIÓN".equals(valorRol) || "RESPONSABLE SUPERVISION".equals(valorRol)) {
            return 2;
        }

        return 3;
    }

    private static LocalDateTime parseFechaHora(String fechaHora) {
        if (fechaHora == null || fechaHora.trim().isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(fechaHora.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static String formatearFecha(Object fechaObj) {
        if (fechaObj == null) {
            return "";
        }

        if (fechaObj instanceof java.time.LocalDate) {
            return ((java.time.LocalDate) fechaObj).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        if (fechaObj instanceof java.time.LocalDateTime) {
            return ((java.time.LocalDateTime) fechaObj).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        if (fechaObj instanceof java.sql.Date) {
            return ((java.sql.Date) fechaObj).toLocalDate()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        if (fechaObj instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) fechaObj).toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        if (fechaObj instanceof java.util.Date) {
            return ((java.util.Date) fechaObj).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        String fechaStr = String.valueOf(fechaObj).trim();
        if (fechaStr.isEmpty() || "null".equals(fechaStr)) {
            return "";
        }

        // Try parsing common formats and reformat to dd/MM/yyyy
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd'T'HH:mm:ss.SSS",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm",
                "yyyy-MM-dd HH:mm:ss.SSS",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd HH:mm",
                "dd/MM/yyyy HH:mm:ss",
                "dd/MM/yyyy");

        for (String pattern : patterns) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern(pattern));
                return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                // try next pattern
            }
        }

        // If all parsing fails, return original string as fallback
        return fechaStr;
    }
}
