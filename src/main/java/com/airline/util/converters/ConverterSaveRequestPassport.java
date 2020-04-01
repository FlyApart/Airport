package com.airline.util.converters;

import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Passports;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestPassport extends ConverterRequestPassport <PassportSaveRequest, Passports>  {


    @Override
    public Passports convert (PassportSaveRequest request) {
        Passports passports = new Passports ();
        return doConvert (passports,request);
    }
}
