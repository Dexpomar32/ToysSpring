package com.study.Controller;

import com.study.DTO.Records.ToyDTO;
import com.study.Model.PopularProduct;
import com.study.Model.Toy;
import com.study.Service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/toy")
public class ToyController {
    private final ToyService toyService;

    @Autowired
    public ToyController(ToyService toyService) {
        this.toyService = toyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Optional<List<ToyDTO>>> getAll() {
        Optional<List<ToyDTO>> toyDTOList = toyService.getAll();
        return toyDTOList.isPresent() ?
                new ResponseEntity<>(toyDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getOne")
    public ResponseEntity<Optional<ToyDTO>> getOne(@RequestParam String cod) {
        Optional<ToyDTO> toyDTO = toyService.getOne(cod);
        return toyDTO.isPresent() ?
                new ResponseEntity<>(toyDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<ToyDTO>> create(@RequestBody Toy newToy) {
        Optional<ToyDTO> toyDTO = toyService.create(newToy);
        return toyDTO.isPresent() ?
                new ResponseEntity<>(toyDTO, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<ToyDTO>> update(@RequestBody Toy newToy) {
        Optional<ToyDTO> toyDTO = toyService.update(newToy);
        return toyDTO.isPresent() ?
                new ResponseEntity<>(toyDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<ToyDTO>> delete(@RequestParam String cod) {
        Optional<ToyDTO> toyDTO = toyService.delete(cod);
        return toyDTO.isPresent() ?
                new ResponseEntity<>(toyDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/exclude")
    public ResponseEntity<Optional<List<ToyDTO>>> exclude() {
        Optional<List<ToyDTO>> toyDTOList = toyService.exclude();
        return toyDTOList.isPresent() ?
                new ResponseEntity<>(toyDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/expensiveCheap")
    public ResponseEntity<Optional<List<ToyDTO>>> expensiveCheap() {
        Optional<List<ToyDTO>> toyDTOList = toyService.expensiveCheap();
        return toyDTOList.isPresent() ?
                new ResponseEntity<>(toyDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/avgPrice")
    public ResponseEntity<String> avgPrice(@RequestParam String country) {
        String avgPrice = toyService.avgPrice(country);
        return (avgPrice != null) ?
                new ResponseEntity<>("Average price of toys produced in " + country + " is: " + avgPrice, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/insertMoldova")
    public ResponseEntity<Optional<List<ToyDTO>>> insertMoldova() {
        Optional<List<ToyDTO>> toyDTOList = toyService.insertMoldova();
        return toyDTOList.isPresent() ?
                new ResponseEntity<>(toyDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/filters")
    public ResponseEntity<Optional<List<ToyDTO>>> filters(
            @RequestParam double maxPrice,
            @RequestParam int minimAge,
            @RequestParam int maxAge) {
        Optional<List<ToyDTO>> toyDTOList = toyService.filters(maxPrice, minimAge, maxAge);
        return toyDTOList.isPresent() ?
                new ResponseEntity<>(toyDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/popular")
    public ResponseEntity<Optional<List<PopularProduct>>> popular() {
        Optional<List<PopularProduct>> popularProductList = toyService.popular();
        return popularProductList.isPresent() ?
                new ResponseEntity<>(popularProductList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}