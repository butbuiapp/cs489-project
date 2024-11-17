package miu.asd.reservationmanagement.service;

import miu.asd.reservationmanagement.common.AppointmentStatusEnum;
import miu.asd.reservationmanagement.dto.request.AppointmentRequestDto;
import miu.asd.reservationmanagement.dto.response.AppointmentResponseDto;
import miu.asd.reservationmanagement.dto.request.AppointmentSearchRequestDto;

import java.util.List;

public interface AppointmentService {
    void saveAppointment(AppointmentRequestDto appointmentRequestDto);
    void updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto);
    void cancelAppointment(Long id);
    List<AppointmentResponseDto> getAppointmentsByStatus(AppointmentStatusEnum status);
    List<AppointmentResponseDto> getAppointmentsByCustomerId(Long customerId);
    List<AppointmentResponseDto> getAppointmentsByCustomerPhone(String phoneNumber);
    List<AppointmentResponseDto> getAppointmentsByTechnician(Long technicianId);
    AppointmentResponseDto getAppointmentById(Long id);
    List<AppointmentResponseDto> searchAppointment(AppointmentSearchRequestDto dto);
}
