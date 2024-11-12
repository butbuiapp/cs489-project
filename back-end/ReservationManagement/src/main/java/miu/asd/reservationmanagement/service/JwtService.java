package miu.asd.reservationmanagement.service;

import miu.asd.reservationmanagement.common.RoleEnum;

public interface JwtService {
    String generateToken(String phoneNumber, String fullName, RoleEnum role);
}
