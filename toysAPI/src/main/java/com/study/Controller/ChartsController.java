package com.study.Controller;

import com.study.DTO.Records.*;
import com.study.Model.EndpointDetail;
import com.study.Service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SuppressWarnings("unused")
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/charts")
public class ChartsController {
    private final ChartsService chartsService;
    @Autowired
    public ChartsController(ChartsService chartsService) {
        this.chartsService = chartsService;
    }

    @GetMapping("/categories")
    public ResponseEntity<EndpointDetail> getAllCategories() {
        Optional<List<CategoriesChartDTO>> categoriesChartDTOList = chartsService.getAllCategories();
        EndpointDetail endpointDetail = new EndpointDetail();
        endpointDetail.setName("Toys chart by categories");
        endpointDetail.setData(Collections.singletonList(categoriesChartDTOList));

        return (endpointDetail.getData() != null) ?
            new ResponseEntity<>(endpointDetail, HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/countries")
    public ResponseEntity<EndpointDetail> getAllCountries() {
        Optional<List<CountriesChartDTO>> countriesChartDTOList = chartsService.getAllCountries();
        EndpointDetail endpointDetail = new EndpointDetail();
        endpointDetail.setName("Toys chart by countries");
        endpointDetail.setData(Collections.singletonList(countriesChartDTOList));

        return (endpointDetail.getData() != null) ?
                new ResponseEntity<>(endpointDetail, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ageToy")
    public ResponseEntity<EndpointDetail> getAgeToy() {
        Optional<List<AgeToyChartDTO>> ageToyChartDTOList = chartsService.getAllAgeToy();
        EndpointDetail endpointDetail = new EndpointDetail();
        endpointDetail.setName("Toys chart by age");
        endpointDetail.setData(Collections.singletonList(ageToyChartDTOList));

        return (endpointDetail.getData() != null) ?
                new ResponseEntity<>(endpointDetail, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dateSales")
    public ResponseEntity<EndpointDetail> getDateSales() {
        Optional<List<DateSalesChartDTO>> dateSalesChartDTOList = chartsService.getAllDateSales();
        EndpointDetail endpointDetail = new EndpointDetail();
        endpointDetail.setName("Sales chart by date");
        endpointDetail.setData(Collections.singletonList(dateSalesChartDTOList));

        return (endpointDetail.getData() != null) ?
                new ResponseEntity<>(endpointDetail, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dollMaterial")
    public ResponseEntity<EndpointDetail> getDollMaterial() {
        Optional<List<DollMaterialChartDTO>> dollMaterialChartDTOList = chartsService.getAllDollMaterial();
        EndpointDetail endpointDetail = new EndpointDetail();
        endpointDetail.setName("Dolls chart by material");
        endpointDetail.setData(Collections.singletonList(dollMaterialChartDTOList));

        return (endpointDetail.getData() != null) ?
                new ResponseEntity<>(endpointDetail, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
