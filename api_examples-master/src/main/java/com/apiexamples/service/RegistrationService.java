package com.apiexamples.service;

import com.apiexamples.payload.RegDto;
import com.apiexamples.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    public RegistrationDto createRegistration(RegistrationDto registrationdto);
    public void deleteRegistrationById(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    RegDto getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getRegistrationById(long id);
}
