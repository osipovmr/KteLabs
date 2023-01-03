package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.ChangeDiscountRequest;
import osipovmr.KteLabs.service.personService.PersonService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    /**
     * @return список клиентов (все атрибуты)
     */
    @GetMapping("/findAllPerson")
    public ResponseEntity<?> findAllPerson(){
        return ResponseEntity.ok(personService.findAllPerson());
    }

    /**
     * изменение индивидуальных скидок клиента
     * @param dto входные параметры: идентификатор, скидка 1, скидка 2
     * @return status
     */
    @PostMapping("/changeDiscount")
    public ResponseEntity<?> changeDiscount(@Valid ChangeDiscountRequest dto) {
        return ResponseEntity.ok(personService.changeDiscount(dto));
    }
}
