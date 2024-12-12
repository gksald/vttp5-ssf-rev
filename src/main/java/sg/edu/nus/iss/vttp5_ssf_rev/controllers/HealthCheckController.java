package sg.edu.nus.iss.vttp5_ssf_rev.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
public class HealthCheckController {
    @Value("${csv.file.path}")
   private String csvFile;

   @GetMapping("/health")
   public ResponseEntity<String> getMethodName() {
      File f = new File(csvFile);
      System.out.printf("Exists: %b, IsFile: %b\n", f.exists(), f.isFile());
      if (f.exists() && f.isFile())
         return ResponseEntity.ok("{}");

      return ResponseEntity.status(400).body("{}");
   }
}