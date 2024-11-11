package miu.asd.reservationmanagement.mapper;

import miu.asd.reservationmanagement.dto.AppointmentRequestDto;
import miu.asd.reservationmanagement.dto.AppointmentResponseDto;
import miu.asd.reservationmanagement.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper MAPPER = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "technician.role.role", target = "technician.role")
    AppointmentResponseDto entityToDto(Appointment appointment);
    Appointment dtoToEntity(AppointmentRequestDto appointmentRequestDto);
}
