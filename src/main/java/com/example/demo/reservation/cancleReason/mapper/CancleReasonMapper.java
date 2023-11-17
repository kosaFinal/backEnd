package com.example.demo.reservation.cancleReason.mapper;

import com.example.demo.reservation.cancleReason.entity.CancleReason;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CancleReasonMapper {
    CancleReason getOneCancleReason(String cancleReasonId);
}
