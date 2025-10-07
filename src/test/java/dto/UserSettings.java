package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserSettings {
    String primarySport;
    String hour;
    String dateFormat;
    String startWeek;
}
