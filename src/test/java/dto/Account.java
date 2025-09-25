package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Account {
    String name;
    String LName;
    String sex;
    String weight;
    String typeWeight;
    String country;
    String region;
}
