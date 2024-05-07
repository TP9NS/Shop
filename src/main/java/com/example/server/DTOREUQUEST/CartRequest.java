package com.example.server.DTOREUQUEST;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CartRequest {
	private Long customerId;
    private List<Long> cartIds;
}
