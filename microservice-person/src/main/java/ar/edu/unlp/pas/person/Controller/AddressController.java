package ar.edu.unlp.pas.person.Controller;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;
import ar.edu.unlp.pas.person.dtos.address.AddressGetDto;
import ar.edu.unlp.pas.person.models.Address;
import ar.edu.unlp.pas.person.services.implementations.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping()
    public ResponseEntity<AddressGetDto> createAddress(
            @Valid @RequestBody AddressCreateDto addressDto) {
        try {
            Address address = addressService.createAddress(addressDto);
            AddressGetDto addressGetDto = new AddressGetDto(address);
            return ResponseEntity.ok(addressGetDto);
        } catch (
                HttpClientErrorException.BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<AddressGetDto>> listAddress() {
        List<Address> addresses = addressService.listAddresses();
        List<AddressGetDto> addressesGetDto = new ArrayList<>();
        for (Address address : addresses) {
            AddressGetDto addressGetDTO = new AddressGetDto(address);
            addressesGetDto.add(addressGetDTO);
        }
        return ResponseEntity.ok(addressesGetDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressGetDto> getAddressById(@PathVariable long id) {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        AddressGetDto addressGetDTO = new AddressGetDto(address);

        return ResponseEntity.ok(addressGetDTO);
    }

    @GetMapping(params = "personId")
    public ResponseEntity<List<AddressGetDto>> getAddressByPersonId(@RequestParam(value = "personId") long personId) {
        List<Address> addresses = addressService.getAddressByPersonId(personId);
        List<AddressGetDto> addressesGetDto = new ArrayList<>();
        for (Address address : addresses) {
            AddressGetDto addressGetDTO = new AddressGetDto(address);
            addressesGetDto.add(addressGetDTO);
        }

        return ResponseEntity.ok(addressesGetDto);
    }

    @GetMapping(value = "/billingAddress", params = "personId")
    public ResponseEntity<AddressGetDto> getBillingAddressByPersonId(@RequestParam(value = "personId") long personId) {
        Address address = addressService.getBillingAddressByPersonId(personId);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        AddressGetDto addressGetDTO = new AddressGetDto(address);

        return ResponseEntity.ok(addressGetDTO);
    }

    @GetMapping(value = "/shippingAddress", params = "personId")
    public ResponseEntity<AddressGetDto> getShippingAddressByPersonId(@RequestParam(value = "personId") long personId) {
        Address address = addressService.getShippingAddressByPersonId(personId);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        AddressGetDto addressGetDTO = new AddressGetDto(address);

        return ResponseEntity.ok(addressGetDTO);
    }
}
