package microservice.punche.reporting.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.punche.reporting.services.IntervencionReportingService;
import microservice.shared_data.controller.BaseRestController;

@RestController
@AllArgsConstructor
public class IntervencionReportingController extends BaseRestController {

    private static final DateTimeFormatter FILENAME_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final IntervencionReportingService service;

    @GetMapping(path = { "/generateSesionesExcelReport" })
    public ResponseEntity<byte[]> generateSesionesExcelReport(
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIni,
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        byte[] file = this.service.generateSesionesExcelReport(fechaIni, fechaFin);
        return super.buildDownloadResponseEntity(buildFilename("rpt_sesiones", fechaIni, fechaFin), file);
    }

    @GetMapping(path = { "/generateTalleresFamiliasExcelReport" })
    public ResponseEntity<byte[]> generateTalleresFamiliasExcelReport(
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIni,
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        byte[] file = this.service.generateTalleresFamiliasExcelReport(fechaIni, fechaFin);
        return super.buildDownloadResponseEntity(buildFilename("rpt_talleres", fechaIni, fechaFin), file);
    }

    private static String buildFilename(String baseName, LocalDate fechaIni, LocalDate fechaFin) {
        if (fechaIni == null && fechaFin == null) {
            return baseName + "_todos.xlsx";
        }

        String ini = fechaIni != null ? fechaIni.format(FILENAME_DATE_FORMAT) : "todos";
        String fin = fechaFin != null ? fechaFin.format(FILENAME_DATE_FORMAT) : "todos";
        return baseName + "_" + ini + "_" + fin + ".xlsx";
    }

}
