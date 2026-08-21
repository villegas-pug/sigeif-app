package microservice.punche.reporting.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import microservice.punche.reporting.dtos.CompromisoFamiliarPdfData;

@Service
public class CompromisoFamiliarOpenPdfService {

   private static final Color AZUL_ENCABEZADO = new Color(0x0B, 0x93, 0xBD);

   private static final Font FONT_TITULO = new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK);
   private static final Font FONT_NORMAL = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLACK);
   private static final Font FONT_BOLD = new Font(Font.HELVETICA, 11, Font.BOLD, Color.BLACK);
   private static final Font FONT_ENCABEZADO = new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE);
   private static final Font FONT_LABEL = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.BLACK);

   public byte[] generate(CompromisoFamiliarPdfData data) {
      try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
         Document document = new Document(PageSize.A4, 40, 40, 40, 40);
         PdfWriter.getInstance(document, outputStream);
         document.open();

         addTitulo(document);
         addParrafoCuidador(document, data);
         addEncabezadoAzul(document,
               "COMPROMISOS DE LA ESTRATEGIA EXTRAMUROS ESPECIALIZADA EN PREVENCIÓN PRIMARIA Y FORTALECIMIENTO DE LAS FAMILIAS");
         addCajaContenido(document,
               "La estrategia tiene como finalidad fortalecer y potenciar factores protectores, para prevenir situaciones de riesgo de desprotección que afectan a niñas, niños, adolescentes y jóvenes, en su entorno familiar.\n\n"
                     + "• Brindar el servicio de manera gratuita, a través de consejerías, talleres que se realizan a través de visitas domiciliarias o teleasistencia.\n"
                     + "• Brindar orientación a las familias para la atención de problemáticas de carácter social o derivándoles a servicios que se brinden dentro de la jurisdicción.");
         addEncabezadoAzul(document, "COMPROMISOS DE LOS INTEGRANTES DE LA FAMILIA");
         addCajaContenido(document,
               "• Participar en todas las actividades del servicio de acuerdo a las fechas y tiempo programado.\n"
                     + "• Cumplir con los compromisos asumidos durante las reuniones de acompañamiento familiar e informar sobre las dificultades que se presenten en el incumplimiento de las mismas.\n"
                     + "• Cumplir con las acciones y/o actividades contenidas en el Plan de Trabajo Familiar.\n"
                     + "• Comunicar con anticipación cuando necesite reprogramar la atención para otro momento, considerando horarios que no afecten la atención de las otras familias.");
         addSeccionFirmas(document, data);
         addFechaYHora(document, data.fechaCompromiso());

         document.close();
         return outputStream.toByteArray();
      } catch (Exception e) {
         throw new RuntimeException("Error generando PDF de Compromiso Familiar", e);
      }
   }

   private void addTitulo(Document document) throws Exception {
      Paragraph titulo = new Paragraph("Ficha de Consentimiento Informado y Compromiso Familiar", FONT_TITULO);
      titulo.setAlignment(Element.ALIGN_CENTER);
      titulo.setSpacingAfter(20);
      document.add(titulo);
   }

   private void addParrafoCuidador(Document document, CompromisoFamiliarPdfData data) throws Exception {
      String texto = "En base a lo detallado previamente, yo " + data.nombresCuidador()
            + ", Identificada(o) con DNI N° " + data.numDocCuidador()
            + ", doy fe que se me ha informado lo descrito en este documento, por ello estoy de acuerdo en participar de la estrategia.";
      Paragraph parrafo = new Paragraph(texto, FONT_NORMAL);
      parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
      parrafo.setSpacingAfter(10);
      document.add(parrafo);
   }

   private void addEncabezadoAzul(Document document, String texto) throws Exception {
      PdfPTable tabla = new PdfPTable(1);
      tabla.setWidthPercentage(100);
      tabla.setSpacingBefore(8);

      PdfPCell celda = new PdfPCell(new Phrase(texto, FONT_ENCABEZADO));
      celda.setBackgroundColor(AZUL_ENCABEZADO);
      celda.setHorizontalAlignment(Element.ALIGN_CENTER);
      celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celda.setBorder(Rectangle.NO_BORDER);
      celda.setPadding(10);
      tabla.addCell(celda);

      document.add(tabla);
   }

   private void addCajaContenido(Document document, String texto) throws Exception {
      PdfPTable tabla = new PdfPTable(1);
      tabla.setWidthPercentage(100);
      tabla.setSpacingAfter(8);

      PdfPCell celda = new PdfPCell(new Phrase(texto, FONT_NORMAL));
      celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
      celda.setBorder(Rectangle.BOX);
      celda.setBorderWidth(1f);
      celda.setPadding(10);
      tabla.addCell(celda);

      document.add(tabla);
   }

   private void addSeccionFirmas(Document document, CompromisoFamiliarPdfData data) throws Exception {
      PdfPTable tabla = new PdfPTable(2);
      tabla.setWidthPercentage(100);
      tabla.setSpacingBefore(20);

      tabla.addCell(crearCeldaFirma("Firma del Cuidador", data.nombresCuidador(), data.numDocCuidador()));
      tabla.addCell(
            crearCeldaFirma("Firma y sello del Acompañante Familiar", data.nombresAcompañante(), data.numDocAcompañante()));

      document.add(tabla);
   }

   private PdfPCell crearCeldaFirma(String titulo, String nombres, String dni) {
      PdfPCell celda = new PdfPCell();
      celda.setBorder(Rectangle.NO_BORDER);
      celda.setPadding(5);

      celda.addElement(new LineSeparator(1f, 100f, Color.BLACK, Element.ALIGN_CENTER, -2));

      Paragraph parrafoTitulo = new Paragraph(titulo, FONT_BOLD);
      parrafoTitulo.setAlignment(Element.ALIGN_CENTER);
      parrafoTitulo.setSpacingAfter(10);
      celda.addElement(parrafoTitulo);

      celda.addElement(new Paragraph("Nombres y Apellidos:", FONT_NORMAL));
      celda.addElement(new Paragraph(nombres, FONT_BOLD));
      celda.addElement(new Paragraph("DNI:", FONT_NORMAL));
      celda.addElement(new Paragraph(dni, FONT_BOLD));

      return celda;
   }

   private void addFechaYHora(Document document, String fechaCompromiso) throws Exception {
      Paragraph parrafo = new Paragraph("Fecha y hora: " + fechaCompromiso, FONT_LABEL);
      parrafo.setSpacingBefore(10);
      document.add(parrafo);
   }
}
