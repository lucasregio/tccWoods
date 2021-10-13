package com.woods.enums;

import lombok.Getter;

public enum RoleEnum {
	BUYER(1),
	SALER(2),
	ARCHITECT(3);
	
	@Getter private int value;
	
	RoleEnum(int value) {
        this.value = value;
    }
}
