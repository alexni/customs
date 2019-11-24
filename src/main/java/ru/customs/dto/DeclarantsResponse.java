package ru.customs.dto;

import ru.customs.entity.DeclarantEntity;

import java.util.List;

public class DeclarantsResponse {
    List<DeclarantEntity> list;
    Long total;

    public DeclarantsResponse(List<DeclarantEntity> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public List<DeclarantEntity> getList() {
        return list;
    }

    public Long getTotal() {
        return total;
    }
}
