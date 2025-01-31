package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.board.Response.BoardResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.BoardType;

import java.util.List;

public interface BoardService {
    ResponseDto<List<BoardResponseDto>> getBoardByBoardType(BoardType boardType);
}
