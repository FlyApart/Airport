package com.airline.entity.vo;

import com.airline.entity.Country;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class PassengersInfo {
	private Passengers passengers;
	private Passports passports;
	private Country country;
}
