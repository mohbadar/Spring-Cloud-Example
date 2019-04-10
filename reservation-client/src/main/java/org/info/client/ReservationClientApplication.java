package org.info.client;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);

    }

}

@FeignClient(name = "reservation-service")
interface ReservationReader {

    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    Collection<Reservation> read();

    @RequestMapping(method = RequestMethod.POST, value = "/reservations")
    Reservation write(Reservation r);
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Reservation {

    private String reservationName;
}

@RestController
class ReservationController {

    @Autowired
    private ReservationReader reader;

    @GetMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Reservation> read() {
        return reader.read();
    }

    @PostMapping(value = "/api/reservations/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation write(@PathVariable("name") String name) {
        return reader.write(new Reservation(name));
    }

}
