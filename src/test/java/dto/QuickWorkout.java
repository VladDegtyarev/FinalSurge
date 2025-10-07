package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class QuickWorkout {
    String date;
    String type;
    String text;
    String distance;
    String distType;
}
