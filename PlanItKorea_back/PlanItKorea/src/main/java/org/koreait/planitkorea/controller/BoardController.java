package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.board.Response.BoardResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.BoardType;
import org.koreait.planitkorea.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOARD)
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    public static final String FIND_BY_CATEGORY = "/type/{boardType}";

    @GetMapping(FIND_BY_CATEGORY)
    public ResponseEntity<ResponseDto<List<BoardResponseDto>>> getBoardsByBoardType(@PathVariable BoardType boardType) {
        ResponseDto<List<BoardResponseDto>> response = boardService.getBoardByBoardType(boardType);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
