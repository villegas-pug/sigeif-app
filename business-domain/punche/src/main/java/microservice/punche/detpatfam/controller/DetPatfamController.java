package microservice.punche.detpatfam.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.punche.detpatfam.service.DetPatfamService;
import microservice.punche.patfam.models.DetPatfam;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class DetPatfamController {

      private final DetPatfamService service;

      @GetMapping(path = { "/findDetPatfamById" })
      public ResponseEntity<ApiResponse<DetPatfam>> findDetPatfamById(@RequestParam Long idDetPatfam) {
            return ResponseEntity.ok(
                        ApiResponse.<DetPatfam>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findDetPatfamById(idDetPatfam))
                                    .build());
      }

      @GetMapping(path = { "/findDetPatfamByIdTaller" })
      public ResponseEntity<ApiResponse<List<DetPatfam>>> findDetPatfamByIdTaller(@RequestParam Integer idTaller) {
            return ResponseEntity.ok(
                        ApiResponse.<List<DetPatfam>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findDetPatfamByIdTaller(idTaller))
                                    .build());
      }

      @GetMapping(path = { "/findDetPatfamByParams" })
      public ResponseEntity<ApiResponse<List<DetPatfam>>> findDetPatfamByParams(@RequestParam Integer idServicio,
                  @RequestParam Integer idDynamic, @RequestParam(required = false) Long idZona) {
            return ResponseEntity.ok(
                        ApiResponse.<List<DetPatfam>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findDetPatfamByParams(idServicio, idDynamic, idZona))
                                    .build());
      }

      @DeleteMapping(path = { "/deleteDetPatfamById" })
      public ResponseEntity<ApiResponse<Void>> deleteDetPatfamById(@RequestParam Long idDetPatfam) {
            ApiResponseStatus apiStatus = ApiResponseStatus.SUCCESS_DELETE;
            apiStatus.setMessage(idDetPatfam);
            this.service.deleteDetPatfamById(idDetPatfam);
            return ResponseEntity.ok(
                        ApiResponse.<Void>builder().message(apiStatus.getMessage()).build());
      }

}
