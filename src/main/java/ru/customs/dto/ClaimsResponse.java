package ru.customs.dto;

import java.util.List;

public class ClaimsResponse {
    private final List<UserResponse> managers;
    private List<ClaimResponse> list;
    private Long total;
    private int limit;
    private int offset;



    public ClaimsResponse(List<ClaimResponse> claims, Long count, int limit, int offset, List<UserResponse> users) {
        this.list = claims;
        this.total = count;
        this.limit = limit;
        this.offset = offset;
        this.managers = users;
    }

    public List<ClaimResponse> getList() {
        return list;
    }

    public void setList(List<ClaimResponse> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<UserResponse> getManagers() {
        return managers;
    }
}
