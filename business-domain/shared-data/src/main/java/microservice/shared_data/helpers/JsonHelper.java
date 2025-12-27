package microservice.shared_data.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import microservice.shared_data.exceptions.JsonConversionException;

@Component
@AllArgsConstructor
public class JsonHelper {

   private final ObjectMapper objectMapper;

   public String toJson(Object obj) {
      try {
         return this.objectMapper.writeValueAsString(obj);
      } catch (JsonProcessingException e) {
         throw new JsonConversionException("Ocurrió un error al convertir el objeto a JSON", e);
      }

   }

   public <T> T fromJson(String jsonStr, Class<T> clazz) {
      try {
         return this.objectMapper.readValue(jsonStr, clazz);
      } catch (JsonProcessingException e) {
         throw new JsonConversionException("Ocurrió un erro al convertir un JSON a objeto", e);
      }

   }

}
