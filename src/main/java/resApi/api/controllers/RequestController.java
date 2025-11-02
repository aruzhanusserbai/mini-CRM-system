package resApi.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import resApi.api.entities.ApplicationRequest;
import resApi.api.services.RequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        List<ApplicationRequest> requests = requestService.getAll();

        if(requests.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(requests, HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addRequest(ApplicationRequest request, @RequestParam Long courseId){
        ApplicationRequest createdRequest = requestService.addNewReq(request, courseId);

        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailsPage(@PathVariable Long id){
        ApplicationRequest request =  requestService.getRequestById(id);

        if(Objects.isNull(request)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(request);
        }
    }

    @PutMapping("/{id}/operators/add")
    public ResponseEntity<?> addOperators(@PathVariable Long id, @RequestParam ArrayList<Long> operatorIds ){
        ApplicationRequest updateRequest = requestService.update(id, operatorIds);

        if(Objects.isNull(updateRequest)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(updateRequest);
        }
    }

    @DeleteMapping("/delete/operator/{operatorId}")
    public ResponseEntity<?> deleteOperator(@PathVariable Long operatorId, @RequestParam Long requestId){
        boolean removed = requestService.deleteOperatorId(operatorId, requestId);

        if(removed){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
