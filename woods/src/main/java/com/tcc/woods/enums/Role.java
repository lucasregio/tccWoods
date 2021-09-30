package com.tcc.woods.enums;


import lombok.Getter;


public enum Role {

	BUYER(1),
	SALER(2),
	ARCHITECT(3);
	
	@Getter private int value;
	
	Role(int value) {
        this.value = value;
    }
	
}
