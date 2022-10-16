package ru.aryunin.VacationPayCalculator.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VacationPayDTO implements Serializable {

    private static final long serialVersionUID = 7714483778358449356L;
    private double amount;
}
