package miu.asd.reservationmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.AppointmentStatusEnum;
import miu.asd.reservationmanagement.common.InvoiceStatusEnum;
import miu.asd.reservationmanagement.common.UserStatusEnum;
import miu.asd.reservationmanagement.dto.request.AppointmentRequestDto;
import miu.asd.reservationmanagement.dto.response.AppointmentResponseDto;
import miu.asd.reservationmanagement.dto.request.AppointmentSearchRequestDto;
import miu.asd.reservationmanagement.exception.NotFoundException;
import miu.asd.reservationmanagement.mapper.AppointmentMapper;
import miu.asd.reservationmanagement.model.Appointment;
import miu.asd.reservationmanagement.model.Customer;
import miu.asd.reservationmanagement.model.Invoice;
import miu.asd.reservationmanagement.repository.AppointmentRepository;
import miu.asd.reservationmanagement.repository.CustomerRepository;
import miu.asd.reservationmanagement.service.AppointmentService;
import miu.asd.reservationmanagement.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void saveAppointment(AppointmentRequestDto appointmentRequestDto) {
        // save appointment
        Appointment appointment = AppointmentMapper.MAPPER.dtoToEntity(appointmentRequestDto);
        appointment.setStatus(AppointmentStatusEnum.BOOKED);

        // get customer by phone
        Customer customer = customerRepository.findByPhoneNumberAndStatus(
                appointmentRequestDto.getCustomer().getPhoneNumber(),
                UserStatusEnum.ACTIVE).orElseThrow(() -> new NotFoundException("Customer not found"));
        appointment.setCustomer(customer);

        // Save invoice if present
        Invoice invoice = appointment.getInvoice();
        if (invoice != null) {
            invoice.setStatus(InvoiceStatusEnum.DRAFT);
            appointment.setInvoice(invoice);
        }
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto) {

    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment appointment = findById(id);
        appointment.setStatus(AppointmentStatusEnum.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByStatus(AppointmentStatusEnum status) {
        return null;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByCustomerId(Long customerId) {
        List<Appointment> appointments = appointmentRepository.findAllByCustomerIdOrderByDateDescTimeDesc(customerId);
        return appointments.stream().map(AppointmentMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByCustomerPhone(String phoneNumber) {
        List<Appointment> appointments =
                appointmentRepository.findAllByCustomerPhoneNumberOrderByDateDescTimeDesc(phoneNumber);

        List<AppointmentResponseDto> appointmentResponseDtos = appointments.stream().map(
                AppointmentMapper.MAPPER::entityToDto).collect(Collectors.toList());
        return appointmentResponseDtos;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByTechnician(Long technicianId) {
        return null;
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Long id) {
        return AppointmentMapper.MAPPER.entityToDto(findById(id));
    }

    @Override
    public List<AppointmentResponseDto> searchAppointment(AppointmentSearchRequestDto dto) {
        List<Appointment> appointments = appointmentRepository.findAllByDateOrderByTimeDesc(dto.getAppointmentDate());
        return appointments.stream().map(AppointmentMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }

    private Appointment findById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        } else {
            throw new NotFoundException("Appointment not found");
        }
    }
}
