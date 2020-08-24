package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.services.api.MeterService;

/**
 * Сервис для функционала(действий) по расписанию.
 */

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final MeterService meterService;

    /**
     * Метод для вызова генерации стандартных значений счетчиков (срабатывает каждый месяц с момента запуска системы).
     * Не используется в MVP0
     */
    @Scheduled(cron = "0 0 0 0 * *")
    public void submitSchedulerMeterData() {
        try {
            meterService.generateDefaultMeterData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
