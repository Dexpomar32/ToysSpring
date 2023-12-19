package com.study.Controller;

import com.study.DTO.Records.DollDTO;
import com.study.Model.Doll;
import com.study.Service.DollService;
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
@RequestMapping("/doll")
public class DollController {
    private final DollService dollService;

    @Autowired
    public DollController(DollService dollService) {
        this.dollService = dollService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Optional<List<DollDTO>>> getAll() {
        Optional<List<DollDTO>> dollDTOList = dollService.getAll();
        return dollDTOList.isPresent() ?
                new ResponseEntity<>(dollDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getOne")
    public ResponseEntity<Optional<DollDTO>> getOne(@RequestParam String cod) {
        Optional<DollDTO> dollDTO = dollService.getOne(cod);
        return dollDTO.isPresent() ?
                new ResponseEntity<>(dollDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<DollDTO>> create(@RequestBody Doll newDoll) {
        Optional<DollDTO> dollDTO = dollService.create(newDoll);
        return dollDTO.isPresent() ?
                new ResponseEntity<>(dollDTO, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<DollDTO>> update(@RequestBody Doll newDoll) {
        Optional<DollDTO> dollDTO = dollService.update(newDoll);
        return dollDTO.isPresent() ?
                new ResponseEntity<>(dollDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<DollDTO>> delete(@RequestParam String cod) {
        Optional<DollDTO> dollDTO = dollService.delete(cod);
        return dollDTO.isPresent() ?
                new ResponseEntity<>(dollDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ascending")
    public ResponseEntity<Optional<List<DollDTO>>> ascending() {
        Optional<List<DollDTO>> dollDTOList = dollService.ascending();
        return dollDTOList.isPresent() ?
                new ResponseEntity<>(dollDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}