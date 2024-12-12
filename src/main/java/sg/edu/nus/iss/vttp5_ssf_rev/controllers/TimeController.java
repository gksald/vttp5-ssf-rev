package sg.edu.nus.iss.vttp5_ssf_rev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5_ssf_rev.services.TimeService;

@RestController
@RequestMapping
public class TimeController {
    
    @Autowired
    private TimeService timeSvc;

   @GetMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> getTimeAsJson() {
      String time = timeSvc.getTime();

      JsonObject resp = Json.createObjectBuilder()
            .add("time", time)
            .build();

      return ResponseEntity.ok(resp.toString());
   }

   @GetMapping(path="/time", produces = MediaType.TEXT_HTML_VALUE)
   public ResponseEntity<String> getTime() {
      String time = timeSvc.getTime();
      String html = """
         <h1>The current time is %s</h1>
      """.formatted(time);

      return ResponseEntity.ok(html);
   }
}
