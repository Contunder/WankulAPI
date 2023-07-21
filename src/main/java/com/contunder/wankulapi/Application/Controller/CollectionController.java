package com.contunder.wankulapi.Application.Controller;

import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Application.Security.JwtAuthenticationFilter;
import com.contunder.wankulapi.Application.Security.JwtTokenProvider;
import com.contunder.wankulapi.Application.Service.CollectionService;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.USER_NOT_FOUND;
import static com.contunder.wankulapi.Application.Utils.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.contunder.wankulapi.Application.Utils.AppConstants.DEFAULT_PAGE_SIZE;
import static com.contunder.wankulapi.Application.Utils.AppConstants.DEFAULT_SORT_BY;
import static com.contunder.wankulapi.Application.Utils.AppConstants.DEFAULT_SORT_DIRECTION;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final CollectionService deckService;

    public CollectionController(JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider, CollectionService deckService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
        this.deckService = deckService;
    }

    @GetMapping("/")
    public ResponseEntity<CardResponse> getAllResources(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            HttpServletRequest request
    ){

        return ResponseEntity.ok(deckService.getAllMyCard(new Pageable(pageNo, pageSize, sortBy, sortDir), getEmail(request)));
    }

    @PostMapping("/add/{cardNumber}")
    public ResponseEntity<String> getActivityByResource(@PathVariable(value = "cardNumber") int cardNumber, HttpServletRequest request) {

        return ResponseEntity.ok(deckService.addCardByCardNumber(cardNumber, getEmail(request)));
    }

    private String getEmail(HttpServletRequest request){

        return jwtTokenProvider.getEmail(jwtAuthenticationFilter.getTokenFromRequest(request))
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

}
