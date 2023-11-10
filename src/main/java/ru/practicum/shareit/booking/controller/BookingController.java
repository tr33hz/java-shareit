package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private static final String USER_ID_HEAD = "X-Sharer-User-Id";


    @PostMapping
    public BookingDto addBooking(@Valid @RequestBody BookingDto booking,
                                 @RequestHeader(USER_ID_HEAD) Long userId) {
        return bookingService.addBooking(booking, userId);
    }

    @DeleteMapping("/{bookingId}")
    public BookingDto deleteBooking(@PathVariable Long bookingId,
                                    @RequestHeader(USER_ID_HEAD) Long userId) {
        return bookingService.deleteBooking(bookingId, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@RequestHeader(USER_ID_HEAD) Long userId,
                                     @PathVariable Long bookingId) {
        return bookingService.getBookingById(userId, bookingId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto setApprove(@RequestHeader(USER_ID_HEAD) Long userId,
                                 @PathVariable Long bookingId,
                                 @RequestParam String approved) {
        return bookingService.setApprove(userId, bookingId, approved);
    }

    @GetMapping
    public List<BookingDto> getBookingForCurrentUser(@RequestHeader(USER_ID_HEAD) Long userId,
                                                     @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getBookingForCurrentUser(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getBookingForOwner(@RequestHeader(USER_ID_HEAD) Long userId,
                                               @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getBookingForOwner(userId, state);
    }
}
