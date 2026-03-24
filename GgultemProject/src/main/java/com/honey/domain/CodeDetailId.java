package com.honey.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // 필수: 복합키 비교를 위해 필요
public class CodeDetailId implements Serializable {
    private String groupCode;
    private String codeValue;
}