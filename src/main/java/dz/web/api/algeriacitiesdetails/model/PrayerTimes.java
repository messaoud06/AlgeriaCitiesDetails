package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author Messaoud GUERNOUTI on 2/26/2024
 */
@Schema(name = "Prayer Times",
        description = "All Day prayer times"
)
@JsonPropertyOrder({"Fajr","Sunrise","Dhuhr","Asr","Maghrib","Isha","Midnight"})
public record PrayerTimes(
        String fajr,
        String sunrise,
        String dhuhr,
        String asr,
        String maghrib,
        String isha,
        String midnight
) {
    public static PrayerTimes build(Root root) {

        return new PrayerTimes(
                root.data.timings.Fajr,
                root.data.timings.Sunrise,
                root.data.timings.Dhuhr,
                root.data.timings.Asr,
                root.data.timings.Maghrib,
                root.data.timings.Isha,
                root.data.timings.Midnight
                );
    }
}
