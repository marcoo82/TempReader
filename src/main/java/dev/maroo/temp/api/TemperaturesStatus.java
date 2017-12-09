package dev.maroo.temp.api;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperaturesStatus {
	private Boolean isError;
}
