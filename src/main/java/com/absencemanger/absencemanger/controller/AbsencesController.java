package com.absencemanger.absencemanger.controller;

import com.absencemanger.absencemanger.dto.ApproveRejectDTO;
import com.absencemanger.absencemanger.dto.SearchDTO;
import com.absencemanger.absencemanger.model.Absence;
import com.absencemanger.absencemanger.service.AbsencesService;
import com.absencemanger.absencemanger.service.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "absence")
@RequiredArgsConstructor
@RestController
public class AbsencesController {
    private final AbsencesService absencesService;

    @PostMapping()
    public ResponseEntity<ApiResponse<Page<Absence>>> getAbsences(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestBody(required = false) SearchDTO searchDTO){
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithPayload(absencesService.getAbsences(searchDTO,pageable),"Success"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.failure(e.getMessage()));
        }
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse<Absence>> updateAbsence(@PathVariable Long id, @RequestBody ApproveRejectDTO approveRejectDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithPayload(absencesService.updateAbsence(id,approveRejectDTO),"Absence request updated"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.failure("Cannot update your request right now"));
        }
    }
}
