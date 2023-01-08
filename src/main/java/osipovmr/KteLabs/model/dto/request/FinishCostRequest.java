package osipovmr.KteLabs.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinishCostRequest {
    Integer personId;
    List<ProductValue> list;
}
