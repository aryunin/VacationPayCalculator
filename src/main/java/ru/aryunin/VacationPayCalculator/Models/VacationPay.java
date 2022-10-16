package ru.aryunin.VacationPayCalculator.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class VacationPay implements Serializable {
    private static final long serialVersionUID = 2168369073827051629L;
    private double amount;
}
