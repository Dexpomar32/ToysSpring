package com.study.Controller;

import com.study.DTO.Records.SaleDTO;
import com.study.Model.Sale;
import com.study.Model.SaleRequest;
import com.study.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Optional<List<SaleDTO>>> getAll() {
        Optional<List<SaleDTO>> saleDTOList = saleService.getAll();
        return saleDTOList.isPresent() ?
                new ResponseEntity<>(saleDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getOne")
    public ResponseEntity<Optional<SaleDTO>> getOne(@RequestParam String cod) {
        Optional<SaleDTO> saleDTO = saleService.getOne(cod);
        return saleDTO.isPresent() ?
                new ResponseEntity<>(saleDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<SaleDTO>> create(@RequestBody Sale newSale) {
        Optional<SaleDTO> saleDTO = saleService.create(newSale);
        return saleDTO.isPresent() ?
                new ResponseEntity<>(saleDTO, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<SaleDTO>> update(@RequestBody Sale newSale) {
        Optional<SaleDTO> saleDTO = saleService.update(newSale);
        return saleDTO.isPresent() ?
                new ResponseEntity<>(saleDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<SaleDTO>> delete(@RequestParam String cod) {
        Optional<SaleDTO> saleDTO = saleService.delete(cod);
        return saleDTO.isPresent() ?
                new ResponseEntity<>(saleDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sales")
    public ResponseEntity<String> sales(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Integer sales = saleService.sales(date);
        return (sales != null) ?
                new ResponseEntity<>("Sales in " + date.toString() + ": '" + sales + "'", HttpStatus.ACCEPTED):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sell")
    public ResponseEntity<Optional<SaleDTO>> performSale(@RequestBody SaleRequest saleRequest) {
        saleService.updateToyQuantityAndSales(saleRequest.getCod(), saleRequest.getQuantity());
        Optional<SaleDTO> saleDTO = saleService.getOne(saleRequest.getCod());
        return saleDTO.isPresent() ?
                new ResponseEntity<>(saleDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}