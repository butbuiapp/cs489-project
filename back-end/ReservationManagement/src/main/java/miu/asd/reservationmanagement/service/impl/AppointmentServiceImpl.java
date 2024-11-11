package miu.asd.reservationmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.AppointmentStatusEnum;
import miu.asd.reservationmanagement.common.InvoiceStatusEnum;
import miu.asd.reservationmanagement.dto.AppointmentRequestDto;
import miu.asd.reservationmanagement.dto.AppointmentResponseDto;
import miu.asd.reservationmanagement.dto.AppointmentSearchRequestDto;
import miu.asd.reservationmanagement.exception.NotFoundException;
import miu.asd.reservationmanagement.mapper.AppointmentMapper;
import miu.asd.reservationmanagement.model.Appointment;
import miu.asd.reservationmanagement.model.Invoice;
import miu.asd.reservationmanagement.repository.AppointmentRepository;
import miu.asd.reservationmanagement.repository.InvoiceRepository;
import miu.asd.reservationmanagement.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public void saveAppointment(AppointmentRequestDto appointmentRequestDto) {
        // save appointment
        Appointment appointment = AppointmentMapper.MAPPER.dtoToEntity(appointmentRequestDto);
        appointment.setStatus(AppointmentStatusEnum.BOOKED);

        // Save invoice if present
        Invoice invoice = appointment.getInvoice();
        if (invoice != null) {
            invoice.setStatus(InvoiceStatusEnum.DRAFT);
            appointment.setInvoice(invoice);
        }
        Appointment createdAppointment = appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto) {

    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByStatus(AppointmentStatusEnum status) {
        return null;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByCustomer(Long customerId) {
        List<Appointment> appointments = appointmentRepository.findAllByCustomerIdOrderByDateDescTimeDesc(customerId);
        return appointments.stream().map(AppointmentMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByTechnician(Long technicianId) {
        return null;
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            return AppointmentMapper.MAPPER.entityToDto(optionalAppointment.get());
        } else {
            throw new NotFoundException("Nail service not found");
        }
    }

    @Override
    public List<AppointmentResponseDto> searchAppointment(AppointmentSearchRequestDto dto) {
        System.out.println("dto.getAppointmentDate()===" + dto.getAppointmentDate());
        List<Appointment> appointments = appointmentRepository.findAllByDateOrderByTimeDesc(dto.getAppointmentDate());
        return appointments.stream().map(AppointmentMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }
}
