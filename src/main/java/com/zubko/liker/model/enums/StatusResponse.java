package com.zubko.liker.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Getter
@AllArgsConstructor
public enum StatusResponse {

    SUCCESS(0),
    ERROR(1);

    private int value;
}
