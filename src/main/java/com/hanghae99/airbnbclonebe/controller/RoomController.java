package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

//    @GetMapping("/rooms")
//    public Slice<GetRoomsResponseDto> getRooms(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                               @RequestParam String category,
//                                               Pageable pageable){
//        // 로그인이 된 사람의 wish도 같이 보여줘야 하기 때문
//        Long userId = userDetails.getUser().getId();
//
//        return roomService.getRooms(category, pageable, userId);
//    }

    @GetMapping("/rooms")
    public Slice<GetRoomsResponseDto> getRoomsFilter(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam String category,
                                               @RequestParam(required = false, defaultValue = "false") boolean parking,
                                               @RequestParam(required = false, defaultValue = "false") boolean kitchen,
                                               @RequestParam(required = false, defaultValue = "false") boolean aircon,
                                               @RequestParam(required = false, defaultValue = "false") boolean wifi,
                                               @RequestParam(required = false, defaultValue = "false") boolean washer,
                                               @RequestParam(required = false, defaultValue = "false") boolean tv,
                                               @RequestParam(required = false, defaultValue = "0") int minPrice,
                                               @RequestParam(required = false, defaultValue = "2147483647") int maxPrice,
                                               Pageable pageable){
        // 로그인이 된 사람의 wish도 같이 보여줘야 하기 때문
        Long userId = userDetails.getUser().getId();

        if(parking || kitchen || aircon || wifi || washer || tv){
            System.out.println("with filter");
            return roomService.getRoomsFilter(category, pageable, userId, parking, kitchen, aircon, wifi, washer, tv, minPrice, maxPrice);
        } else {
            System.out.println("without filter");
            return roomService.getRooms(category, pageable, userId);
        }
    }

}
