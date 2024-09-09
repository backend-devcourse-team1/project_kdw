package org.programers.grids_and_circles.services.scheduling;

public interface ScheduledTaskService {
    void updateOrderStatusToInProgress();

    void updateOrderStatusToDelivered();
}
