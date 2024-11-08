package com.apiexamples.service;


import com.apiexamples.entity.Registration;
import com.apiexamples.exception.ResourceNotFound;
import com.apiexamples.payload.RegDto;
import com.apiexamples.payload.RegistrationDto;
import com.apiexamples.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
       Registration registration= mapToEntity(registrationDto);
       Registration savedEntity = registrationRepository.save(registration);
       RegistrationDto dto = mapToDto(savedEntity);
       dto.setMessage("Registration saved");

        return dto;

    }

    @Override
    public void deleteRegistrationById(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
       Optional<Registration> opReg= registrationRepository.findById(id);
       Registration registration = opReg.get();
       registration.setName(registrationDto.getName());
       registration.setEmail(registrationDto.getEmail());
       registration.setMobile(registrationDto.getMobile());
       Registration savedEntity = registrationRepository.save(registration);
       RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    @Override
    public RegDto getAllRegistrations(int pageNo , int pageSize, String sortBy, String sortDir) {
       // List<Registration> registrations=registrationRepository.findAll();
        // Ternary Operator
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(Sort.Direction.ASC , sortBy) :Sort.by(Sort.Direction.DESC, sortBy);
       Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
       Page<Registration> all= registrationRepository.findAll(pageable);
       List<Registration> registrations=all.getContent();
       List<RegistrationDto> registrationDtos = registrations.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
       // Using Stream Api to convert Registration List to Registration Dto list.

        System.out.println("Total Pages : "+ all.getTotalPages());
        System.out.println("Is last page? "+all.isLast());//This will tell weather this is the last page or not
        System.out.println("Is first page? "+all.isFirst());//This will tell weather this is the first page or not
        System.out.println("Page size : "+pageable.getPageSize());
        System.out.println("Page number : "+pageable.getPageNumber());
        RegDto regDto= new RegDto(registrationDtos,all.getTotalPages(), all.isLast(), all.isFirst(),
                pageable.getPageSize(),
                pageable.getPageNumber()

                );
        return regDto;
    }

    @Override
    public RegistrationDto getRegistrationById(long id) {
        Registration registration= registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Registration not found with id :"+id)
        );

        //orElseThrow() method will take supplier which will generate something without taking input.

        RegistrationDto registrationDtodto = mapToDto(registration);
        return registrationDtodto;
    }

    //method for converting Dto to Entity
   Registration mapToEntity(RegistrationDto dto){
        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;

   }

   //method  for converting Entity to Dto
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }
}
