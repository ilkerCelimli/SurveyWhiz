package com.surveywizz.surveyservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.surveywizz.surveyservice.entity.Prize;
import com.surveywizz.surveyservice.services.PrizeService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("prize/")
@RequiredArgsConstructor
public class PrizeController {

    private final PrizeService prizeService;

    @GetMapping("/")
    public ResponseEntity<List<Prize>> findAll(@RequestParam Integer page,@RequestParam Integer size){
        return ResponseEntity.ok(this.prizeService.findAll(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prize> findById(@PathVariable String id){
        return ResponseEntity.ok(this.prizeService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Prize> save(@JsonInclude(JsonInclude.Include.NON_NULL) @RequestParam Prize prize ){
        try {
            return ResponseEntity.ok(this.prizeService.saveEntity(prize));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

     @PutMapping("/{id}")
    public ResponseEntity<Prize> update(@JsonInclude(JsonInclude.Include.NON_NULL)
                                             @RequestBody Prize prize){
         try {
             return ResponseEntity.ok(this.prizeService.updatedEntity(prize));

         } catch (SQLException e) {
             return new ResponseEntity<Void>(null,404);
         }

     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
         try {
             this.prizeService.deleteById(id);
             return ResponseEntity.ok().build();
         } catch (SQLException e) {
             return ResponseEntity.badRequest().build();
         }

     }


}
