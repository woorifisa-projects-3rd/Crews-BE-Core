package org.baas.baascore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInitRequestDto {
    private String name;
    private String phoneNumber;

    public MemberInitRequestDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
