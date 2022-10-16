package ru.aryunin.VacationPayCalculator.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class VacationPayDTO implements Serializable {

    private static final long serialVersionUID = 7714483778358449356L;
    private double amount;
}
