package sg.edu.nus.iss.vttp5_ssf_rev.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

   public String getTime() {
      return (new Date()).toString();
   }
   
}