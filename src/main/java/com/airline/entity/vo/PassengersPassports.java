package com.airline.entity.vo;

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
public class PassengersPassports {
	private Passengers passengers;
	private List<Passports> passports;
}
