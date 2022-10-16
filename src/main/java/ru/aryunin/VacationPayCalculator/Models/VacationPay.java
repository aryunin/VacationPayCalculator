package ru.aryunin.VacationPayCalculator.Models;

import lombok.Data;

import java.io.Serializable;

@Data
public class VacationPay implements Serializable {
    private static final long serialVersionUID = 2168369073827051629L;
    private double amount;
}
