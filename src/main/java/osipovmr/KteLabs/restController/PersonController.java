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
     * @return ������ �������� (��� ��������)
     */
    @GetMapping("/findAllPerson")
    public ResponseEntity<?> findAllPerson(){
        return ResponseEntity.ok(personService.findAllPerson());
    }

    /**
     * ��������� �������������� ������ �������
     * @param dto ������� ���������: �������������, ������ 1, ������ 2
     * @return status
     */
    @PostMapping("/changeDiscount")
    public ResponseEntity<?> changeDiscount(@Valid ChangeDiscountRequest dto) {
        return ResponseEntity.ok(personService.changeDiscount(dto));
    }
}
