package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов различных типов платежей
 */
@Getter
@RequiredArgsConstructor
public enum BillType {

    METERS("Платеж по счетчикам"),
    MEMBERSHIP_FEE_FIXED("Членский взнос фиксированный"),
    MEMBERSHIP_FEE_CALCULATED("Членский взнос рассчитанный"),
    OTHER_FEE_FIXED("Прочий платеж фиксированный"),
    OTHER_FEE_CALCULATED("Прочий платеж рассчитанный");

    // Описание счетчика
    private final String description;
}
