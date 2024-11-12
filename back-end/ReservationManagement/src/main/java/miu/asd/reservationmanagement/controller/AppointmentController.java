package miu.asd.reservationmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.dto.request.AppointmentRequestDto;
import miu.asd.reservationmanagement.dto.request.AppointmentSearchRequestDto;
import miu.asd.reservationmanagement.dto.response.AppointmentResponseDto;
import miu.asd.reservationmanagement.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.APPOINTMENT_URL)
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        appointmentService.saveAppointment(appointmentRequestDto);
        return ResponseEntity.ok().body(Map.of("message", "Appointment created successfully"));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAppointmentsByCustomer(@PathVariable Long customerId) {
        List<AppointmentResponseDto> appointmentResponseDtos = appointmentService.getAppointmentsByCustomer(customerId);
        return ResponseEntity.ok().body(appointmentResponseDtos);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getAppointmentsByDate(@RequestBody AppointmentSearchRequestDto searchRequestDto) {
        List<AppointmentResponseDto> appointmentResponseDtos =
                appointmentService.searchAppointment(searchRequestDto);
        return ResponseEntity.ok().body(appointmentResponseDtos);
    }
}
