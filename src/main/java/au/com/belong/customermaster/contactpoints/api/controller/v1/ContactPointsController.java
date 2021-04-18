package au.com.belong.customermaster.contactpoints.api.controller.v1;

import au.com.belong.customermaster.contactpoints.exception.ResourceNotFoundException;
import au.com.belong.customermaster.contactpoints.model.PhoneContactPointStatus;
import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto;
import au.com.belong.customermaster.contactpoints.service.PhoneContactPointsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/contactpoints")
@Validated
@RequiredArgsConstructor
public class ContactPointsController {

    private final PhoneContactPointsService phoneContactPointsService;

    @GetMapping("")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "No results found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 503, message = "Service unavailable")
    })
    public ResponseEntity<PhoneContactPointsDto> get(){
        return new ResponseEntity(phoneContactPointsService.fetchAllContactPoints(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "No results found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 503, message = "Service unavailable")
    })
    public ResponseEntity<PhoneContactPointsDto> get(
            @PathVariable("customerId") String customerId) throws ResourceNotFoundException {
        return new ResponseEntity(phoneContactPointsService.fetchContactPointsByCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "No results found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 503, message = "Service unavailable")
    })
    @ApiOperation(value = "Change the state of a given contact point for a given customer like Active, Inactive, Block etc")
    public ResponseEntity<PhoneContactPointsDto> changeState(@Valid @RequestBody PhoneContactPointsDto phoneContactPointsDto) throws ResourceNotFoundException {
        return new ResponseEntity(phoneContactPointsService.changeState(phoneContactPointsDto), HttpStatus.OK);
    }


}
