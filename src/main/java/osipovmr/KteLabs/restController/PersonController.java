package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.service.PersonService;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/findAllPerson")
    public ResponseEntity<?> findAllPerson(){
        return ResponseEntity.ok(personService.findAllPerson());
    }
}
