package microservice.shared_data.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateHelper {

   private static final List<DateTimeFormatter> SUPPORTED_FORMATTERS = List.of(
         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
         DateTimeFormatter.ISO_LOCAL_DATE);

   public LocalDate parseToLocalDate(Object value, LocalDate fallback) {
      if (value == null) {
         return fallback;
      }

      String text = value.toString().trim();
      if (text.isEmpty()) {
         return fallback;
      }

      for (DateTimeFormatter formatter : SUPPORTED_FORMATTERS) {
         try {
            return LocalDate.parse(text, formatter);
         } catch (DateTimeParseException ignored) {
            // * Probar siguiente formato
         }
      }

      return fallback;
   }

}
