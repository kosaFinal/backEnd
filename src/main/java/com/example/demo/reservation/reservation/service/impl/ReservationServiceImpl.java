package com.example.demo.reservation.reservation.service.impl;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafeTable.entity.CafeTable;
import com.example.demo.cafeTable.mapper.CafeTableMapper;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import com.example.demo.reservation.reservation.mapper.ReservationMapper;
import com.example.demo.reservation.reservation.service.ReservationService;
import com.example.demo.userss.entity.Users;
import com.example.demo.userss.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    private final CafeMapper cafeMapper;
    private final CafeTableMapper cafeTableMapper;
    private final UsersMapper usersMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createReservation(ReservationDto.UserReservationRequestDto userReservationRequestDto, String userName) {
        log.info("서비스 시작");
        log.info(String.valueOf(userReservationRequestDto));

        // 존재하는 카페테이블인지 확인
        CafeTable cafeTable = cafeTableMapper.getOneCafeTable(userReservationRequestDto.getTableId());
        if(cafeTable == null){
            throw new GeneralException(CustomResponseCode.CAFETABLE_NOT_FOUND);
        }

        // 존재하는 유저인지 확인
        Users user = usersMapper.getOneUsers(userName);
        if(user == null){
            throw new GeneralException(CustomResponseCode.USER_NOT_FOUND);
        }


        for (Reservation.TimeSlot timeSlot : userReservationRequestDto.getReserveTime()) {
            Reservation reservation = Reservation.builder()
                    .tableId(userReservationRequestDto.getTableId())
                    .userId(user.getUserId())
                    .cafeId(cafeTable.getCafeId())
                    .reserveStart(timeSlot.getReserveStart())
                    .reserveEnd(timeSlot.getReserveEnd())
                    .personCnt(userReservationRequestDto.getPersonCnt())
                    .status("A")
                    .reserveDate(userReservationRequestDto.getReserveDate())
                    .build();

            log.info(String.valueOf(reservation));

            try {
                reservationMapper.createReservation(reservation);
            } catch (Exception e) {
                log.info(e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ReservationDto.TimeSlotResponseDto> getAvailableTimeSlots(String reserveDate, int tableId) {

        // tableId로 cafeId 검색
        CafeTable cafeTable = cafeTableMapper.getOneCafeTable(tableId);
        if(cafeTable == null){
            throw new GeneralException(CustomResponseCode.CAFETABLE_NOT_FOUND);
        }
        int cafeId = cafeTable.getCafeId();

        // 검색한 cafeId로 카페 오픈,마감시간 가져오기
        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        CafeDto.CafeTimeResponseDto cafeTimeResponseDto = CafeDto.CafeTimeResponseDto.builder()
                .startTime(cafe.getStartTime())
                .endTime(cafe.getEndTime())
                .build();

        log.info(String.valueOf(cafeTimeResponseDto));

        // 오픈시간, 마감시간 형식 변환
        String startTime = cafeTimeResponseDto.getStartTime();
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        String endTime = cafeTimeResponseDto.getEndTime();
        int endHour = Integer.parseInt(endTime.split(":")[0]);

        log.info("시작시간 "+String.valueOf(startTime));
        log.info("마감시간 "+String.valueOf(endTime));
        log.info("변환시작시간 "+String.valueOf(startHour));
        log.info("변환마감시간 "+String.valueOf(endHour));

        // 오픈시간부터 마감시간까지의 timeslot 만들기(default avilable이 Y)
        List<ReservationDto.TimeSlotResponseDto> newTimeslot = new ArrayList<>();
        for(int i=startHour; i<endHour; i++){
            String start = String.format("%02d:00", i); // "%02d"는 두 자리 정수로 포맷팅하는 역할을 합니다.
            String end = String.format("%02d:00", i + 1);

            ReservationDto.TimeSlotResponseDto timeslot = ReservationDto.TimeSlotResponseDto.builder()
                    .reserveStart(start)
                    .reserveEnd(end)
                    .available("Y")
                    .build();

            newTimeslot.add(timeslot);
        }

        log.info(String.valueOf(newTimeslot));

        // date 형식 변환
        String formatDate = reserveDate.substring(0, 4) + "-" + reserveDate.substring(4, 6) + "-" + reserveDate.substring(6);
        log.info(formatDate);

        // 예약날짜와 tableId로 예약 내역 가져오기
        List<Reservation> reservations = reservationMapper.findReservation(formatDate, tableId);
        log.info(String.valueOf(reservations));

        if(reservations == null){
            return  newTimeslot;
        }

        for (Reservation reservation : reservations) {
            String reserveStartTime = reservation.getReserveStart();
            String formatReserveStartTime = reserveStartTime.split(":")[0];

            for (ReservationDto.TimeSlotResponseDto timeSlot : newTimeslot) {
                String timeSlotStartTime = timeSlot.getReserveStart();

                if (formatReserveStartTime.equals(timeSlotStartTime)) {
                    timeSlot.setAvailable("N");
                }
            }
        }

        log.info(newTimeslot.toString());


        return newTimeslot;

    }
}
