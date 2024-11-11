package miu.asd.reservationmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.model.NailService;
import miu.asd.reservationmanagement.service.NailServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.SERVICE_URL)
public class NailServiceController {
    private final NailServiceService nailServiceService;

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody NailService nailService) {
        NailService createdService = nailServiceService.saveService(nailService);
        return ResponseEntity.ok().body(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Integer id, @RequestBody NailService nailService) {
        nailServiceService.updateService(id, nailService);
        return ResponseEntity.ok().body("Nail service updated successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        List<NailService> services = nailServiceService.getAllServices();
        return ResponseEntity.ok().body(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Integer id) {
        NailService nailService = nailServiceService.getServiceById(id);
        return ResponseEntity.ok().body(nailService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Integer id) {
        nailServiceService.deleteServiceById(id);
        return ResponseEntity.ok().body("Nail service deleted successfully");
    }
}
