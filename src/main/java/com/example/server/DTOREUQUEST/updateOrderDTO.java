package com.example.server.DTOREUQUEST;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class updateOrderDTO {
	Long orderId;
	String newStatus;
}
