package com.example.pad.service;

import com.example.pad.persistence.entity.CourierEntity;

public interface CourierService {
    CourierEntity getLeastBusy();
}
