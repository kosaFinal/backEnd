package com.example.demo.cafeTable.service;

import java.util.List;

public interface CafeTableService {
    List<String> getTableNumbers(int cafeId, String tableType);
}
